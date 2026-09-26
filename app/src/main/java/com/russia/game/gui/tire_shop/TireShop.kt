package com.russia.game.gui.tire_shop

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.russia.game.R
import com.russia.game.core.Samp
import com.russia.game.core.Samp.Companion.activity
import com.russia.game.databinding.StylingCenterBinding
import com.russia.game.gui.NativeGui
import com.russia.game.gui.SelectArrow
import com.russia.game.gui.styling.Styling
import com.russia.game.gui.tuning.TuningAdapter
import com.russia.game.gui.tuning.TuningAdapterListener
import com.russia.game.gui.tuning.TuningItem
import com.russia.game.gui.tuning.TuningSelectMenu
import com.russia.game.gui.tuning.TuningSelectMenuItem
import com.russia.game.gui.tuning.TuningSelectMenuListener

class TireShop : NativeGui<StylingCenterBinding>(StylingCenterBinding::class), CustomSeekListener, TuningAdapterListener, TuningSelectMenuListener {

    private val TYPE_BUY = 1
    private val TYPE_SUSPENSION = 2
    private val TYPE_WHEEL_OFFSET = 3
    private val TYPE_TIRE_WIDTH = 4
    private val TYPE_DISK_RAD   = 5
    private val TYPE_DISK   = 6
    private val TYPE_EXIT_BUTTON = 7;
    private val TYPE_RAZVAL_FRONT = 11
    private val TYPE_RAZVAL_BACK = 12

    private val items = arrayListOf(
        TuningItem("Suspension Height", R.drawable.suspension_icon, TYPE_SUSPENSION),
        TuningItem("Spacers", R.drawable.wheel_offset_icon, TYPE_WHEEL_OFFSET),
        TuningItem("Wheel Alignment (front)", R.drawable.razval_icon, TYPE_RAZVAL_FRONT),
        TuningItem("Wheel Alignment (rear)", R.drawable.razval_icon, TYPE_RAZVAL_BACK),
        TuningItem("Tire Width", R.drawable.tire_width_icon, TYPE_TIRE_WIDTH),
        TuningItem("Rim Radius", R.drawable.disk_rad_icon, TYPE_DISK_RAD),
        TuningItem("Change Rims", R.drawable.disk_group, TYPE_DISK),
    )
    private val adapter = TuningAdapter(items, this)

    private var valueType: Int = 0

    external fun native_getCurrentValue(type: Int): Float
    external fun native_onChange(type: Int, value: Float)
    external fun nativeSendClick(type: Int)

    init {
        activity.runOnUiThread {
            binding.recycle.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            binding.recycle.adapter = adapter

            // buy
            binding.buyButton.setOnClickListener {
                nativeSendClick(TYPE_BUY)
            }

            // exit
            binding.exitButt.setOnClickListener {
                destroy()
            }
        }
    }

    private fun showCustomSeekBar(caption: String, valueType: Int, min: Float, max: Float, step: Float) {
        this.valueType = valueType

        CustomSeekBar(this).showSeek(
            caption,
            native_getCurrentValue(valueType),
            min,
            max,
            step
        )

        binding.mainLayout.visibility = View.GONE
    }
    override fun onChangeProgress(value: Float) {
        native_onChange(valueType, value);
    }

    override fun onCancel() {
        binding.mainLayout.visibility = View.VISIBLE
    }

    override fun onSucces(value: Float) {
        binding.mainLayout.visibility = View.VISIBLE
        nativeSendClick(valueType)
    }

    fun update(to_pay: Int, balance: Int) {
        activity.runOnUiThread {
            binding.balanceText.text = String.format("%s rub.", Samp.formatter.format(balance))
            binding.priceText.text = String.format("%s rub.", Samp.formatter.format(to_pay))
        }
    }

    private fun showSelectMenu(type: Int, list: List<TuningSelectMenuItem>) {
        activity.runOnUiThread {
            binding.mainLayout.visibility = View.GONE

            TuningSelectMenu(native_getCurrentValue(type).toInt(), list, this);
        }

        valueType = type
    }

    override fun onChangeSelectMenu(newValue: Int) {
        native_onChange(TYPE_DISK, newValue.toFloat())
    }

    override fun onExitSelectMenu() {
        binding.mainLayout.visibility = View.VISIBLE
    }

    override fun onSelectedSelectMenu() {
        nativeSendClick(valueType)
        binding.mainLayout.visibility = View.VISIBLE
    }

    override fun onClickItem(pos: Int) {
        when(pos) {
            TYPE_DISK -> {
                val diskList = listOf(
                    TuningSelectMenuItem("Stock", 0, 0),
                    TuningSelectMenuItem("Rims 1", 0, 1025),
                    TuningSelectMenuItem("Rims 2", 0, 1073),
                    TuningSelectMenuItem("Rims 3", 0, 1074),
                    TuningSelectMenuItem("Rims 4", 0, 1075),
                    TuningSelectMenuItem("Rims 5", 0, 1076),
                    TuningSelectMenuItem("Rims 6", 0, 1077),
                    TuningSelectMenuItem("Rims 7", 0, 1078),
                    TuningSelectMenuItem("Rims 8", 0, 1079),
                    TuningSelectMenuItem("Rims 9", 0, 1080),
                    TuningSelectMenuItem("Rims 10", 0, 1081),
                    TuningSelectMenuItem("Rims 11", 0, 1082),
                    TuningSelectMenuItem("Rims 12", 0, 1083),
                    TuningSelectMenuItem("Rims 13", 0, 1084),
                    TuningSelectMenuItem("Rims 14", 0, 1085),
                    TuningSelectMenuItem("Rims 15", 0, 1096),
                    TuningSelectMenuItem("Rims 16", 0, 1097),
                    TuningSelectMenuItem("Rims 17", 0, 1098)

                )
                showSelectMenu(pos, diskList)
                return
            }
            TYPE_SUSPENSION -> {
                showCustomSeekBar(
                    "Suspension Height",
                    TYPE_SUSPENSION,
                    -0.35f,
                    0.15f,
                    0.01f
                )
                return
            }

            TYPE_RAZVAL_FRONT -> {
                showCustomSeekBar(
                    "Wheel Alignment (front)",
                    TYPE_RAZVAL_FRONT,
                    -20.0f,
                    20.0f,
                    1.0f
                )
            }

            TYPE_RAZVAL_BACK -> {
                showCustomSeekBar(
                    "Wheel Alignment (rear)",
                    TYPE_RAZVAL_BACK,
                    -20.0f,
                    20.0f,
                    1.0f
                )
            }

            TYPE_TIRE_WIDTH -> {
                showCustomSeekBar(
                    "Tire Width",
                    TYPE_TIRE_WIDTH,
                    0.0f,
                    200.0f,
                    1.0f
                )
            }

            TYPE_DISK_RAD -> {
                showCustomSeekBar(
                    "Rim Radius",
                    TYPE_DISK_RAD,
                    0.50f,
                    1.4f,
                    0.05f
                )
            }

            TYPE_WHEEL_OFFSET -> {
                showCustomSeekBar(
                    "Spacers",
                    TYPE_WHEEL_OFFSET,
                    -20.0f,
                    10.0f,
                    1.0f
                )
            }
        }
    }

    override fun destroy() {
        super.destroy()
        nativeSendClick(TYPE_EXIT_BUTTON)
    }

    override fun receivePacket(actionId: Int, data: String) {
        TODO("Not yet implemented")
    }
}