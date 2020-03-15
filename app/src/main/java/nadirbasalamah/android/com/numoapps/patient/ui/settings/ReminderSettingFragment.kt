package nadirbasalamah.android.com.numoapps.patient.ui.settings

import android.content.SharedPreferences
import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import nadirbasalamah.android.com.numoapps.R

/**
 * A simple [Fragment] subclass.
 */
class ReminderSettingFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var FOOD_SCHEDULE_REMINDER: String
    private lateinit var RETURN_DATE_REMINDER: String
    private var isFoodScheduleChecked: Boolean = false
    private var isReturnDateChecked: Boolean = false

    private lateinit var foodScheduleReminderPreference: SwitchPreference
    private lateinit var returnDatePreference: SwitchPreference
//    private lateinit var alarmReceiver: AlarmReceiver
//    private lateinit var currentDate: String

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
//        alarmReceiver = AlarmReceiver()
//        AndroidThreeTen.init(context)
//        currentDate = LocalDate.now().toString()
        init()
        setSummaries()
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun init() {
        FOOD_SCHEDULE_REMINDER = resources.getString(R.string.food_schedule_key)
        RETURN_DATE_REMINDER = resources.getString(R.string.return_schedule_key)

        foodScheduleReminderPreference = findPreference<SwitchPreference>(FOOD_SCHEDULE_REMINDER) as SwitchPreference
        returnDatePreference = findPreference<SwitchPreference>(RETURN_DATE_REMINDER) as SwitchPreference
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences

        foodScheduleReminderPreference.isChecked = sh.getBoolean(FOOD_SCHEDULE_REMINDER,false)
        returnDatePreference.isChecked = sh.getBoolean(RETURN_DATE_REMINDER,false)
    }

    override fun onSharedPreferenceChanged(sharedPreference: SharedPreferences, key: String) {
        when(key) {
            FOOD_SCHEDULE_REMINDER ->{
                foodScheduleReminderPreference.isChecked = sharedPreference.getBoolean(FOOD_SCHEDULE_REMINDER, false)
                isFoodScheduleChecked = sharedPreference.getBoolean(FOOD_SCHEDULE_REMINDER, false)
//                alarmReceiver.setReleaseAlarm(context,AlarmReceiver.TYPE_RELEASE,currentDate,resources.getString(R.string.release_reminder_notif))
                if(!isFoodScheduleChecked) {
//                    alarmReceiver.cancelAlarm(context,AlarmReceiver.TYPE_RELEASE)
                }
            }
            RETURN_DATE_REMINDER -> {
                returnDatePreference.isChecked = sharedPreference.getBoolean(RETURN_DATE_REMINDER, false)
                isReturnDateChecked = sharedPreference.getBoolean(RETURN_DATE_REMINDER, false)
//                alarmReceiver.setReminderAlarm(context, AlarmReceiver.TYPE_REMINDER,
//                    resources.getString(R.string.daily_reminder_notif))
                if(!isReturnDateChecked) {
//                    alarmReceiver.cancelAlarm(context,AlarmReceiver.TYPE_REMINDER)
                }
            }
        }
    }
}