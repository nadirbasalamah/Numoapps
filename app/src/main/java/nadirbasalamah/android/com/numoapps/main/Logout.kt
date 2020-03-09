package nadirbasalamah.android.com.numoapps.main

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class Logout: AppCompatActivity() {
    companion object {
        fun logout() {
            val appContext = MainActivity.getAppContext()

            val loginData = appContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
            val loginDataEdit = loginData.edit()

            loginDataEdit.clear()
            loginDataEdit.apply()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        logout()
    }

    private fun logout() {
        val appContext = MainActivity.getAppContext()

        val loginData = appContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val loginDataEdit = loginData.edit()

        loginDataEdit.clear()
        loginDataEdit.apply()

    }
}