package nadirbasalamah.android.com.numoapps.admin.ui.nutritionists

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_add_nutritionist.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel
import java.util.*

class AddNutritionistActivity : AppCompatActivity() {
    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0
    private var age: Int = 0
    private var gender: String = ""
    private lateinit var adminViewModel: AdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_nutritionist)

        btn_nutritionist_birthdate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    tv_nutritionist_birthdate.text =
                        dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                    age = calendar.get(Calendar.YEAR) - year
                }, year, month, day
            )
            datePickerDialog.show()
        }

        rg_nutritionist_gender.setOnCheckedChangeListener{_, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            gender = radio.text.toString()
        }

        btn_nutritionist_save.setOnClickListener {
            val data : HashMap<String, String> = HashMap<String, String> ()
            val fullname = et_nutritionist_fullname.text.toString()
            val username = et_nutritionist_username.text.toString()
            val password = et_nutritionist_password.text.toString()
            val birthdate = tv_nutritionist_birthdate.text.toString()
            val gender = gender
            val age  = age
            val phone_number = et_nutritionist_phone_number.text.toString()
            val email = et_nutritionist_email.text.toString()
            val address = et_nutritionist_address.text.toString()
            val nip = et_nutritionist_nip.text.toString()

            data.put("fullname",fullname)
            data.put("username",username)
            data.put("password",password)
            data.put("birthdate",birthdate)
            data.put("gender",gender)
            data.put("age",age.toString())
            data.put("phone_number",phone_number)
            data.put("email",email)
            data.put("address",address)
            data.put("nip",nip)

            adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
            adminViewModel.setContext(applicationContext)
            adminViewModel.addNutritionist(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    sendSMS(result.data.phone_number, result.data.username,data["password"])
                }
            })
        }
    }

    private fun sendSMS(phonenum: String?, name: String?, password: String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("smsto:"))
        intent.setType("vnd.android-dir/mms-sms")
        intent.putExtra("address", phonenum)
        val sms_body =
            "Berikut adalah akun ahli gizi untuk mengakses sistem \nusername : $name\npassword : $password"
        intent.putExtra("sms_body",sms_body)

        try {
            startActivity(intent)
            finish()
        } catch(e: Exception) {
            Toast.makeText(this,"Pengiriman sms gagal",Toast.LENGTH_SHORT).show()
        }
    }
}