package nadirbasalamah.android.com.numoapps.main

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.rizmaulana.sheenvalidator.lib.SheenValidator
import kotlinx.android.synthetic.main.activity_register.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.UserViewModel
import java.util.*


class RegisterActivity : AppCompatActivity() {
    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0
    private var age: Int = 0
    private var gender: String = ""
    private var id_type:String = ""
    private lateinit var userViewModel: UserViewModel
    private lateinit var sheenValidator: SheenValidator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        sheenValidator = SheenValidator(this)

        btn_datepicker.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    tv_birthdate.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                    age = calendar.get(Calendar.YEAR) - year
                }, year, month, day
            )
            datePickerDialog.show()
        }

        rg_gender.setOnCheckedChangeListener{_, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            gender = radio.text.toString()
        }

        rg_id_card_type.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            id_type = radio.text.toString()
        }

        sheenValidator.setOnValidatorListener {
            Toast.makeText(this,"Validasi sukses!",Toast.LENGTH_SHORT).show()
        }

        sheenValidator.registerAsRequired(et_fullname)
        sheenValidator.registerAsRequired(et_username)
        sheenValidator.registerAsRequired(et_password)
        sheenValidator.registerHasMinLength(et_password,6)
        sheenValidator.registerAsRequired(et_phone_number)
        sheenValidator.registerAsRequired(et_email)
        sheenValidator.registerAsEmail(et_email)
        sheenValidator.registerAsRequired(et_address)
        sheenValidator.registerAsRequired(et_id_number)

        btn_register.setOnClickListener {
            sheenValidator.validate()
            val data : HashMap<String, String> = HashMap<String, String> ()
            val fullname = et_fullname.text.toString()
            val username = et_username.text.toString()
            val password = et_password.text.toString()
            val birthdate = tv_birthdate.text.toString()
            val gender = gender
            val age = age
            val phone_number = et_phone_number.text.toString()
            val email = et_email.text.toString()
            val address = et_address.text.toString()
            val id_number = et_id_number.text.toString()
            val id_type = id_type

            data.put("fullname",fullname)
            data.put("username",username)
            data.put("password",password)
            data.put("birthdate",birthdate)
            data.put("gender",gender)
            data.put("age",age.toString())
            data.put("phone_number",phone_number)
            data.put("email",email)
            data.put("address",address)
            data.put("id_number",id_number)
            data.put("id_type",id_type)

            userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
            userViewModel.setContext(applicationContext)
            userViewModel.register(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    finish()
                }
            })
        }
    }
}
