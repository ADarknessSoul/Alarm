package com.example.alarm2

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TimePicker

class MainActivity : AppCompatActivity(){

    private lateinit var stopAlarmButton: Button
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timePicker: TimePicker = findViewById(R.id.timePicker)
        val setAlarmButton: Button = findViewById(R.id.setAlarmButton)
        stopAlarmButton = findViewById(R.id.stopAlarmButton)

        alarmReceiver = AlarmReceiver()

        setAlarmButton.setOnClickListener {
            stopAlarmButton.visibility = View.VISIBLE
            val selectedHour = timePicker.hour
            val selectedMinute = timePicker.minute

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
            calendar.set(Calendar.MINUTE, selectedMinute)
            calendar.set(Calendar.SECOND, 0)

            setAlarm(calendar.timeInMillis)
        }
        stopAlarmButton.setOnClickListener {
            alarmReceiver.stopRinging()
            stopAlarmButton.visibility = View.GONE
        }
    }

    private fun setAlarm(timeInMillis: Long) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
    }

}