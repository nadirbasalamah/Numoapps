package nadirbasalamah.android.com.numoapps.admin.ui.nutritionists

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_edit_nutritionist.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Nutritionist
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel

class EditNutritionistActivity : AppCompatActivity() {
    private lateinit var nutritionist: Nutritionist
    private lateinit var adminViewModel: AdminViewModel

    companion object {
        const val EXTRA_NUTRITIONIST_DATA = "EXTRA_NUTRITIONIST_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_nutritionist)

        nutritionist = intent.getParcelableExtra(EXTRA_NUTRITIONIST_DATA)
        et_nutritionist_edit_username.setText(nutritionist.username)
        et_nutritionist_edit_phone_number.setText(nutritionist.phone_number)
        et_nutritionist_edit_email.setText(nutritionist.email)
        et_nutritionist_edit_address.setText(nutritionist.address)

        btn_nutritionist_edit_save.setOnClickListener {
            var data: HashMap<String, String> = HashMap<String, String>()
            val username = et_nutritionist_edit_username.text.toString()
            val phone_number = et_nutritionist_edit_phone_number.text.toString()
            val email = et_nutritionist_edit_email.text.toString()
            val address = et_nutritionist_edit_address.text.toString()

            data.put("id",nutritionist.id.toString())
            data.put("username",username)
            data.put("phone_number",phone_number)
            data.put("email",email)
            data.put("address",address)

            adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
            adminViewModel.setContext(applicationContext)
            adminViewModel.editNutritionist(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    finish()
                }
            })
        }

        btn_to_nutritionist_change_password.setOnClickListener {
            val intent = Intent(this,NutritionistChangePasswordActivity::class.java)
            intent.putExtra(NutritionistChangePasswordActivity.NUT_DATA,nutritionist)
            startActivity(intent)
        }
    }
}
