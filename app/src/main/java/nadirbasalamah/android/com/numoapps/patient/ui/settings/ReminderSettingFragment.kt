package nadirbasalamah.android.com.numoapps.patient.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.jakewharton.threetenabp.AndroidThreeTen
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.main.MainActivity
import nadirbasalamah.android.com.numoapps.util.AlarmReceiver
import nadirbasalamah.android.com.numoapps.viewmodel.UserViewModel
import org.threeten.bp.LocalDate

/**
 * A simple [Fragment] subclass.
 */
class ReminderSettingFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var FOOD_SCHEDULE_REMINDER: String
    private lateinit var RETURN_DATE_REMINDER: String
    private lateinit var userViewModel: UserViewModel
    private var isFoodScheduleChecked: Boolean = false
    private var isReturnDateChecked: Boolean = false
    private var userId: Int? = 0
    private var returnDate: String? = ""
    private val foodMenu: HashMap<String, String> = HashMap()

    private lateinit var foodScheduleReminderPreference: SwitchPreference
    private lateinit var returnDatePreference: SwitchPreference
    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var currentDate: String

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val appContext = MainActivity.getAppContext()
        val loginData = appContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        userId = loginData.getInt("id_user",0)

        addPreferencesFromResource(R.xml.preferences)

        alarmReceiver = AlarmReceiver()
        AndroidThreeTen.init(context)
        currentDate = LocalDate.now().toString()
        init()
        setSummaries()
    }

    override fun onResume() {
        super.onResume()
        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            UserViewModel::class.java)
        userViewModel.setContext(context)
        userViewModel.getNutRecordByUserId(userId)?.observe(viewLifecycleOwner, Observer {result ->
            if(result?.status == true) {
                returnDate = result.monitoring_data.return_date
            }
        })

        userViewModel.getFoodMenuByUserId(userId)?.observe(viewLifecycleOwner, Observer { result ->
            if(result?.status == true) {
                foodMenu.put("brekfast",result.data.breakfast.toString())
                foodMenu.put("brekfast_time",result.data.breakfast_time.toString())
                foodMenu.put("lunch",result.data.lunch.toString())
                foodMenu.put("lunch_time",result.data.lunch_time.toString())
                foodMenu.put("dinner",result.data.dinner.toString())
                foodMenu.put("dinner_time",result.data.dinner_time.toString())
            }
        })
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

                val breakfast = resources.getString(R.string.breakfast_notif) + foodMenu["breakfast"]
                val lunch = resources.getString(R.string.lunch_notif) + foodMenu["lunch"]
                val dinner = resources.getString(R.string.dinner_notif) + foodMenu["dinner"]

                alarmReceiver.setFoodMenuAlarm(context,AlarmReceiver.TYPE_FOOD_SCHEDULE,currentDate,foodMenu["breakfast_time"].toString(),breakfast)
                alarmReceiver.setFoodMenuAlarm(context,AlarmReceiver.TYPE_FOOD_SCHEDULE,currentDate,foodMenu["lunch_time"].toString(),lunch)
                alarmReceiver.setFoodMenuAlarm(context,AlarmReceiver.TYPE_FOOD_SCHEDULE,currentDate,foodMenu["dinner_time"].toString(),dinner)

                if(!isFoodScheduleChecked) {
                    alarmReceiver.cancelAlarm(context,AlarmReceiver.TYPE_FOOD_SCHEDULE)
                }
            }
            RETURN_DATE_REMINDER -> {
                returnDatePreference.isChecked = sharedPreference.getBoolean(RETURN_DATE_REMINDER, false)
                isReturnDateChecked = sharedPreference.getBoolean(RETURN_DATE_REMINDER, false)
                alarmReceiver.setReturnAlarm(context, AlarmReceiver.TYPE_RETURN_SCHEDULE,
                    returnDate.toString(),resources.getString(R.string.return_date_notif))
                if(!isReturnDateChecked) {
                    alarmReceiver.cancelAlarm(context,AlarmReceiver.TYPE_RETURN_SCHEDULE)
                }
            }
        }
    }
}