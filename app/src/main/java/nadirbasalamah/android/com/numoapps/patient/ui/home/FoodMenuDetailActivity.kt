package nadirbasalamah.android.com.numoapps.patient.ui.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_food_menu_detail.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.main.MainActivity
import nadirbasalamah.android.com.numoapps.viewmodel.UserViewModel

class FoodMenuDetailActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_menu_detail)

        val appContext = MainActivity.getAppContext()
        val loginData = appContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        userId = loginData.getInt("id_user",0)

        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
        userViewModel.setContext(applicationContext)
        userViewModel.getFoodMenuByUserId(userId)?.observe(this, Observer {result ->
            if(result?.status == true) {
                tv_breakfast.text = result.data.breakfast
                tv_breakfast_time.text = result.data.breakfast_time
                tv_lunch.text = result.data.lunch
                tv_lunch_time.text = result.data.lunch_time
                tv_dinner.text = result.data.dinner
                tv_dinner_time.text = result.data.dinner_time
            }
        })
    }
}
