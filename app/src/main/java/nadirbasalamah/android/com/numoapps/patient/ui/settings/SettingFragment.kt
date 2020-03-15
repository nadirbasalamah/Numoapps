package nadirbasalamah.android.com.numoapps.patient.ui.settings


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_setting.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.main.Logout
import nadirbasalamah.android.com.numoapps.main.MainActivity
import nadirbasalamah.android.com.numoapps.user.EditProfileActivity

/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cv_logout.setOnClickListener {
            Logout.logout()
            val logoutIntent = Intent(MainActivity.getAppContext(), MainActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(logoutIntent)
        }
        cv_edit_profile.setOnClickListener {
            val intent = Intent(context,EditProfileActivity::class.java)
            startActivity(intent)
        }
        cv_notif_settings.setOnClickListener{
            val intent = Intent(context,PatientReminderSettingActivity::class.java)
            startActivity(intent)
        }
    }
}
