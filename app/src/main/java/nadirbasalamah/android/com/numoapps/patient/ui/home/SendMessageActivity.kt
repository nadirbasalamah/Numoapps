package nadirbasalamah.android.com.numoapps.patient.ui.home

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_send_message.*
import nadirbasalamah.android.com.numoapps.R
import java.net.URLEncoder

class SendMessageActivity : AppCompatActivity() {
    private var nutPhoneNumber: String? = ""
    companion object {
        const val PHONE_NUMBER = "PHONE_NUMBER_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)

        nutPhoneNumber = intent.getStringExtra(PHONE_NUMBER)

        btn_send_message.setOnClickListener {
            val messageText = et_message.text.toString()
            try {
                val packageManager: PackageManager = packageManager
                val intent = Intent(Intent.ACTION_VIEW)
                val url = "https://api.whatsapp.com/send?phone="+ nutPhoneNumber +"&text=" + URLEncoder.encode(messageText, "UTF-8")
                intent.setPackage("com.whatsapp")
                intent.setData(Uri.parse(url))
                if(intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            } catch (e: PackageManager.NameNotFoundException) {
                Toast.makeText(this,"WhatsApp belum terinstall!",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
