package nadirbasalamah.android.com.numoapps.util

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.jakewharton.threetenabp.AndroidThreeTen
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.response.FoodMenuResponse
import nadirbasalamah.android.com.numoapps.model.response.NutritionRecordResponse
import org.json.JSONObject
import org.threeten.bp.LocalDate
import retrofit2.Call
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        const val TYPE_FOOD_SCHEDULE = "FoodAlarm"
        const val TYPE_RETURN_SCHEDULE = "ReturnAlarm"
        const val EXTRA_MESSAGE = "message"
        const val EXTRA_TYPE = "type"

        //2 jenis alarm
        private var ID_ONETIME = 0
        private const val ID_REPEATING = 101
    }

    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getStringExtra(EXTRA_TYPE)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        AndroidThreeTen.init(context)

        val title = if(type.equals(TYPE_FOOD_SCHEDULE, ignoreCase = true)) context.resources.getString(R.string.food_schedule_title) else context.resources.getString(R.string.return_date_title)
        val notifId = if(type.equals(TYPE_FOOD_SCHEDULE, ignoreCase = true)) ID_REPEATING else ID_ONETIME

        showToast(context, title, message)
        showAlarmNotification(context, title, message, notifId)

    }

    private fun showToast(context: Context, title: String, message: String?) {
        Toast.makeText(context, "$title : $message", Toast.LENGTH_LONG).show()
    }

    fun setReturnAlarm(context: Context?, type: String, date: String, message: String) {
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)

        val dateArray = date.split("-").toTypedArray()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, Integer.parseInt(dateArray[2]))
        calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1]))
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[0]))
        calendar.set(Calendar.HOUR_OF_DAY, 7)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND,0)

        val pendingIntent = PendingIntent.getBroadcast(context, ID_ONETIME, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent)
    }

    private fun showAlarmNotification(context: Context, title: String, message: String, notifId: Int) {

        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "AlarmManager channel"

        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            builder.setChannelId(CHANNEL_ID)
            notificationManagerCompat.createNotificationChannel(channel)
        }
        val notification = builder.build()

        notificationManagerCompat.notify(notifId, notification)
    }

    fun setFoodMenuAlarm(context: Context?, type: String, date: String, time: String, message: String) {
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)

        val dateArray = date.split("-").toTypedArray()
        val timeArray = time.split(":").toTypedArray()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, Integer.parseInt(dateArray[0]))
        calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1]))
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[2]))

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)

    }

    fun cancelAlarm(context: Context?, type: String) {
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode = if (type.equals(TYPE_FOOD_SCHEDULE, ignoreCase = true)) ID_REPEATING else ID_ONETIME
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

    }


}
