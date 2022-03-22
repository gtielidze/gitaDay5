package com.myapplication.gitaday5


import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat


class SaveActivity : AppCompatActivity() {

    private var listView: ListView? = null
    private var arrayList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        adapter()
        dataFromFirsScreen()

        listView?.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                parent.getItemAtPosition(1)

                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.SEND_SMS),
                    1
                )
                sms()
                showNotification()
            }
    }

    private fun dataFromFirsScreen() {
        intent.getStringExtra("number")?.let { arrayList.add(it) }
        intent.getStringExtra("text")?.let { arrayList.add(it) }
    }

    private fun adapter() {
        listView = findViewById(R.id.listView)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
        listView?.adapter = arrayAdapter
    }

    private fun sms() {
        val number = arrayList[0]
        val smsText = arrayList[1]
        sendSms(number, smsText)
        Toast.makeText(this, "Sending Sms", Toast.LENGTH_SHORT).show()
    }

    private fun sendSms(n: String?, m: String?) {
        val smsManager: SmsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(
            n,
            null,
            m,
            null,
            null
        )
    }

    private fun showNotification() {
        val notificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1
        val channelId = "channel-01"
        val channelName = "Channel Name"
        val importance = NotificationManager.IMPORTANCE_HIGH
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                channelId, channelName, importance
            )
            notificationManager.createNotificationChannel(mChannel)
        }
        val mBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, channelId)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_launcher_background
                    )
                )
                .setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("")
                .setContentText("sms is send")
        val intent = Intent(this, AnimationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        mBuilder.setContentIntent(pendingIntent)
        notificationManager.notify(notificationId, mBuilder.build())
    }
}