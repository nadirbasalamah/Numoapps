package nadirbasalamah.android.com.numoapps.admin.ui.nutritionists

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_nutritionist_detail.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Nutritionist
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel

class NutritionistDetailActivity : AppCompatActivity() {
    private lateinit var nutritionist: Nutritionist
    private lateinit var adminViewModel: AdminViewModel
    companion object {
        const val EXTRA_NUTRITIONIST = "EXTRA_NUTRITIONIST"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutritionist_detail)

        nutritionist = intent?.getParcelableExtra(EXTRA_NUTRITIONIST) as Nutritionist
        tv_nut_detail_fullname.text = nutritionist.fullname
        tv_nut_detail_birthdate.text = nutritionist.birthdate
        tv_nut_detail_gender.text = nutritionist.gender
        tv_nut_detail_phone_number.text = nutritionist.phone_number
        tv_nut_detail_email.text = nutritionist.email
        tv_nut_detail_address.text = nutritionist.address
        tv_nut_detail_nip.text = nutritionist.nip

        btn_to_edit_nutritionist.setOnClickListener {
            val intent = Intent(this,EditNutritionistActivity::class.java)
            intent.putExtra(EditNutritionistActivity.EXTRA_NUTRITIONIST_DATA,nutritionist)
            startActivity(intent)
        }

        btn_delete_nutritionist.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.delete_confirmation)
                .setPositiveButton(R.string.confirmation_yes
                ) { dialog, _ ->
                    adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
                    adminViewModel.setContext(applicationContext)
                    adminViewModel.deleteNutritionist(nutritionist.id)?.observe(this, Observer {result ->
                        if(result?.status == true) {
                            finish()
                        }
                    })
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.confirmation_no
                ) { dialog, _ ->
                    dialog.cancel()
                }
            // Create the AlertDialog object and return it
            builder.create()
            builder.show()
        }
    }
}
