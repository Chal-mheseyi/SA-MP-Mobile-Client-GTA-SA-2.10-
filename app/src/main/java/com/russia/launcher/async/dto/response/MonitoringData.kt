package com.russia.launcher.async.dto.response

import android.app.Activity
import android.content.Context
import com.russia.launcher.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ObjectInputStream
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
        listener.monitoringDataLoadedSuccess()
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
