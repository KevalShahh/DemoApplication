package com.example.demoapplication

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.demoapplication.databinding.ActivityNotificationBinding
import kotlinx.android.synthetic.main.activity_notification.*


class Notification : AppCompatActivity() {
    private lateinit var notificationManager: NotificationManager
    lateinit var viewBinding: ActivityNotificationBinding

    private var currentNotificationID = 0
    private var combinedNotificationCounter = 0

    @SuppressLint("RemoteViewLayout")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNotificationBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channel_name"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("CHANNEL_ID", name, importance)

            notificationManager.createNotificationChannel(channel)
        }
        val intent = Intent(this, AnswerReceiveActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        var remote = RemoteViews(packageName, R.layout.custom_notification)
        var remoteExtend=RemoteViews(packageName,R.layout.custom_notification_extend)
        remoteExtend.setTextViewText(R.id.tv_custom_notification,viewBinding.etMainNotificationText.text)
        remoteExtend.setImageViewResource(R.id.imgview_notification,R.drawable.dance)
        remote.setImageViewResource(R.drawable.ic_baseline_circle_notifications_24, 1)
        remote.setTextViewText(R.id.tv_title_notification, viewBinding.etMainNotificationTitle.text)
        remote.setTextViewText(R.id.tv_text_notification, viewBinding.etMainNotificationText.text)
        val intent1 = Intent(this, AnswerReceiveActivity::class.java)
        intent.putExtra("title", "strtitle")
        intent.putExtra("text", "strtext")
        val pIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
        var notificationBuilder = NotificationCompat.Builder(this,"CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(remote)
            .setCustomBigContentView(remoteExtend)
            .setContentIntent(pIntent)
            .setAutoCancel(true)
        //  .build().also { notificationBuilder = it }
//            .setContentTitle(viewBinding.etMainNotificationTitle.text)
//            .setContentText(viewBinding.etMainNotificationText.text)
//            .setStyle(
//                NotificationCompat.BigTextStyle().bigText(viewBinding.etMainNotificationText.text)
//            )
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setContentIntent(pendingIntent)
        viewBinding.btnMainSendSimpleNotification.setOnClickListener {
            currentNotificationID += 1
            var notificationId: Int = currentNotificationID
            if (notificationId == Int.MAX_VALUE - 1) notificationId = 0
            notificationManager.notify(notificationId, notificationBuilder.build())
            with(NotificationManagerCompat.from(this)) {
                notify(notificationId, notificationBuilder.build())
            }
        }
//        val answerIntent = Intent(this, AnswerReceiveActivity::class.java)
//        answerIntent.action = "Yes"
//        val pendingIntentYes =
//            PendingIntent.getActivity(this, 1, answerIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//        notificationBuilder!!.addAction(R.drawable.ic_baseline_thumb_up_24, "Yes", pendingIntentYes)
//        answerIntent.action = "No"
//        val pendingIntentNo =
//            PendingIntent.getActivity(this, 1, answerIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//        notificationBuilder!!.addAction(R.drawable.ic_baseline_thumb_down_24, "No", pendingIntentNo)
    }

    fun clearAllNotifications(view: View) {
        currentNotificationID = 0
        notificationManager.cancelAll()
    }
/*

    fun setDataForCombinedNotification(view: View) {
        setNotificationData()
        combinedNotificationCounter += 1
        notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
            .setContentTitle(notificationTitle)
            .setGroup("group_emails")
            .setGroupSummary(true)
            .setContentText("$combinedNotificationCounter new messages")
        val inboxStyle: NotificationCompat.InboxStyle = NotificationCompat.InboxStyle()
        inboxStyle.setBigContentTitle(notificationTitle)
        inboxStyle.setSummaryText("mehulrughani@gmail.com")
        for (i in 0 until combinedNotificationCounter) {
            inboxStyle.addLine("This is Test$i")
        }
        currentNotificationID = 500
        notificationBuilder!!.setStyle(inboxStyle)
        sendNotification()
    }
    */
}