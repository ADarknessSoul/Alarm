package com.example.alarm2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.widget.Button
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {

    private var isRinging = false
    private var ringtone: Ringtone? = null
    private var mediaPlayer: MediaPlayer? = null


    fun stopRinging() {
        if (!isRinging) {
            println("hola")
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            isRinging = false
        }
    }


    override fun onReceive(context: Context, intent: Intent) {


        if (!isRinging) {
            // Show a toast message
            Toast.makeText(context, "La alarma está sonando!", Toast.LENGTH_LONG).show()

            // Play the alarm sound
            val alarmUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            mediaPlayer = MediaPlayer.create(context, alarmUri)
            mediaPlayer?.isLooping = false
            mediaPlayer?.start()

            isRinging = true
        }
    }

}