package nadirbasalamah.android.com.numoapps.patient.ui.home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.main.MainActivity
import nadirbasalamah.android.com.numoapps.viewmodel.UserViewModel

class HomeFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private var nutPhoneNumber: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appContext = MainActivity.getAppContext()
        val loginData = appContext.getSharedPreferences("Login", MODE_PRIVATE)
        val userId = loginData.getInt("id_user",0)

        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
        userViewModel.setContext(context)
        userViewModel.getNutRecordByUserId(userId)?.observe(viewLifecycleOwner, Observer {result ->
            if(result?.status == true) {
                tv_body_weight.text = result.antropometry_data.bb.toString()
                tv_body_height.text = result.antropometry_data.tb.toString()
                tv_hc.text = result.antropometry_data.lila.toString()
                tv_bmi.text = result.antropometry_data.imt.toString()
                tv_bwi.text = result.antropometry_data.bbi.toString()
                tv_nut_status.text = result.antropometry_data.status
                tv_energy.text = result.interenvention_data.energi.toString()
                tv_carbo.text = result.interenvention_data.persen_karbohidrat.toString()
                tv_carbo_gram.text = result.interenvention_data.gram_karbohidrat.toString()
                tv_protein.text = result.interenvention_data.persen_protein.toString()
                tv_protein_gram.text = result.interenvention_data.gram_protein.toString()
                tv_fat.text = result.interenvention_data.persen_lemak.toString()
                tv_fat_gram.text = result.interenvention_data.gram_lemak.toString()
                tv_diet_type.text = result.interenvention_data.keterangan_inter
                nutPhoneNumber = result.nutritionist_data.phone_number?.replaceFirst("0","62",true)
                Toast.makeText(context, "Data gizi berhasil ditemukan!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Data gizi tidak ditemukan!", Toast.LENGTH_SHORT).show()
            }
        })

        btn_to_foodmenu_detail.setOnClickListener {
            val intent = Intent(context,FoodMenuDetailActivity::class.java)
            startActivity(intent)
        }

        fab_chat_to_whatsapp.setOnClickListener{
            val intent = Intent(context,SendMessageActivity::class.java)
            intent.putExtra(SendMessageActivity.PHONE_NUMBER,nutPhoneNumber)
            startActivity(intent)
        }
    }
}