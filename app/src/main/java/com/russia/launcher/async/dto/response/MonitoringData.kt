package com.russia.launcher.async.dto.response

import android.app.Activity
import android.content.Context
import com.russia.launcher.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ObjectInputStream
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.SocketTimeoutException
import java.io.ObjectOutputStream
import java.io.Serializable

interface MonitoringDataLoaderListener {
    fun monitoringDataLoadedSuccess()
}


data class MonitoringData(
    var news: List<News> = listOf(),
    var servers: List<Servers> = listOf()
) : Serializable

object ServersList {
    val file = "servers.dat"

    var news: List<News> = listOf()
    var servers: List<Servers> = listOf()

    // Built-in server list (no network). Edit these to change the server.
    private const val SERVER_NAME = "ONE NATION RP"
    private const val SERVER_IP = "51.83.49.125"
    private const val SERVER_PORT = 17045

    fun load(activity: Activity, net: NetworkService, listener: MonitoringDataLoaderListener) {
        news = listOf()
        servers = listOf(
            Servers(
                color = "#2E7DFF",
                lock = 0,
                serverID = "0",
                mult = "",
                name = SERVER_NAME,
                online = 0,
                maxonline = 0,
                port = SERVER_PORT,
                ip = SERVER_IP
            )
        )

        // Ask the game server for its real player count, then continue (max ~2.5 s if it is silent)
        Thread {
            refreshOnline()
            activity.runOnUiThread { listener.monitoringDataLoadedSuccess() }
        }.start()
    }

    // Updates online/maxonline of every server. Blocking: call it from a background thread.
    fun refreshOnline() {
        servers = servers.map { s ->
            val r = queryServer(s.ip, s.port)
            if (r != null) s.copy(online = r[0], maxonline = r[1], onlinePer30Min = r[0]) else s
        }
    }

    // SA-MP / open.mp UDP query ("i" packet). Returns [players, maxPlayers] or null if no answer.
    fun queryServer(ip: String, port: Int): IntArray? {
        val octets = try {
            ip.split(".").map { it.toInt() }
        } catch (e: Exception) {
            return null
        }
        if (octets.size != 4) return null

        val packet = ByteArray(11)
        "SAMP".toByteArray(Charsets.US_ASCII).copyInto(packet, 0)
        for (i in 0..3) packet[4 + i] = octets[i].toByte()
        packet[8] = (port and 0xFF).toByte()
        packet[9] = ((port shr 8) and 0xFF).toByte()
        packet[10] = 'i'.code.toByte()

        val socket = DatagramSocket()
        try {
            socket.soTimeout = 1200
            val address = InetAddress.getByName(ip)
            for (attempt in 1..2) {
                try {
                    socket.send(DatagramPacket(packet, packet.size, address, port))
                    val buf = ByteArray(1024)
                    val reply = DatagramPacket(buf, buf.size)
                    socket.receive(reply)
                    if (reply.length < 16) return null
                    val players = (buf[12].toInt() and 0xFF) or ((buf[13].toInt() and 0xFF) shl 8)
                    val max = (buf[14].toInt() and 0xFF) or ((buf[15].toInt() and 0xFF) shl 8)
                    return intArrayOf(players, max)
                } catch (e: SocketTimeoutException) {
                    // no answer yet, try once more
                }
            }
            return null
        } catch (e: Exception) {
            return null
        } finally {
            socket.close()
        }
    }

    fun saveToFile(context: Context, data: MonitoringData) {
        try {
            val fileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)
            objectOutputStream.writeObject(data)
            objectOutputStream.close()
            fileOutputStream.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


    fun loadFromFile(context: Context, listener: MonitoringDataLoaderListener) {
        try {
            val fileInputStream = context.openFileInput(file)
            val objectInputStream = ObjectInputStream(fileInputStream)
            val data = objectInputStream.readObject() as MonitoringData
            objectInputStream.close()
            fileInputStream.close()

            news = data.news
            servers = data.servers

            listener.monitoringDataLoadedSuccess()
        } catch (ex: Exception) {
            ex.printStackTrace()
            listener.monitoringDataLoadedSuccess()
        }
    }
}
