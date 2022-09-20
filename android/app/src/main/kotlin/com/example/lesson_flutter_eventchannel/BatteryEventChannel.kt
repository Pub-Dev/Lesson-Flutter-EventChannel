package com.example.lesson_flutter_eventchannel

import android.content.*
import android.os.BatteryManager
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink

class BatteryLevelEventChannel(context: Context): EventChannel.StreamHandler {
    private var chargingStateChangeReceiver: BroadcastReceiver? = null
    private var applicationContext: Context = context

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        chargingStateChangeReceiver = createChargingStateChangeReceiver(events!!)
        applicationContext.registerReceiver(
            chargingStateChangeReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        )
    }

    override fun onCancel(arguments: Any?) {
        applicationContext.unregisterReceiver(chargingStateChangeReceiver)
        chargingStateChangeReceiver = null
    }

    private fun createChargingStateChangeReceiver(events: EventSink): BroadcastReceiver? {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val batteryPct = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 / 
                     intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                events.success(batteryPct);
            }
        }
    }
}