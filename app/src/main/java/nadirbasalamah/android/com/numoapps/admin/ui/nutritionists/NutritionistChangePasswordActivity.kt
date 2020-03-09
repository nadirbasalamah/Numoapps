package nadirbasalamah.android.com.numoapps.admin.ui.nutritionists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_nutritionist_change_password.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Nutritionist
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel

class NutritionistChangePasswordActivity : AppCompatActivity() {
    private lateinit var nutritionist: Nutritionist
    private lateinit var adminViewModel: AdminViewModel

    companion object {
        const val NUT_DATA = "NUT_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutritionist_change_password)
        nutritionist = intent.getParcelableExtra(NUT_DATA) as Nutritionist

        btn_edit_nutritionist_save_password.setOnClickListener {
            var data: HashMap<String, String> = HashMap<String, String>()
            val old_password = et_edit_nutritionist_oldpassword.text.toString()
            val new_password = et_edit_nutritionist_newpassword.text.toString()

            data.put("id",nutritionist.id.toString())
            data.put("old_password",old_password)
            data.put("new_password",new_password)

            adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
            adminViewModel.setContext(applicationContext)
            adminViewModel.editNutritionistPassword(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    finish()
                }
            })
        }
    }
}