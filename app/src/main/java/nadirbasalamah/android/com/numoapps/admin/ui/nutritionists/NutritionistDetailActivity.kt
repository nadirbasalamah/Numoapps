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

        adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
        adminViewModel.setContext(applicationContext)
        adminViewModel.getNutritionistById(nutritionist.id)?.observe(this, Observer {result ->
            if(result?.status == true) {
                tv_nut_detail_fullname.text = result.data.fullname
                tv_nut_detail_birthdate.text = result.data.birthdate
                tv_nut_detail_gender.text = result.data.gender
                tv_nut_detail_phone_number.text = result.data.phone_number
                tv_nut_detail_email.text = result.data.email
                tv_nut_detail_address.text = result.data.address
                tv_nut_detail_nip.text = result.data.nip
            }
        })

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
