package nadirbasalamah.android.com.numoapps.nutritionist.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_nut_setting.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.main.Logout
import nadirbasalamah.android.com.numoapps.main.MainActivity

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nut_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cv_edit_profile.setOnClickListener {
            val intent = Intent(context,NutEditProfileActivity::class.java)
            startActivity(intent)
        }

        cv_logout.setOnClickListener {
            Logout.logout()
            val logoutIntent = Intent(MainActivity.getAppContext(), MainActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(logoutIntent)
        }
    }
}