package nadirbasalamah.android.com.numoapps.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit_profile.*
import nadirbasalamah.android.com.numoapps.R

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        btn_to_change_password.setOnClickListener {
            val intent = Intent(applicationContext,ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }
}
