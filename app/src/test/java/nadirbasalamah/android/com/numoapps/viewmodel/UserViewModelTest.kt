package nadirbasalamah.android.com.numoapps.viewmodel

import nadirbasalamah.android.com.numoapps.util.UserApiInterface
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class UserViewModelTest {
    private var mockWebServer = MockWebServer()
    private lateinit var userApiInterface: UserApiInterface


    @Before
    fun setup() {
        mockWebServer.start()
        userApiInterface = Retrofit.Builder()
            .baseUrl(mockWebServer.url("http://192.168.1.84:8080/klinikapps-api/public/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiInterface::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }


    @Test
    fun ViewNutRecord() {
        val result = userApiInterface.getNutRecordById(6)?.execute()
        val response = result?.body()
        assertEquals(true, response?.status)
    }

    @Test
    fun ViewEmptyNutRecord() {
        val result = userApiInterface.getNutRecordById(43)?.execute()
        val response = result?.body()
        assertEquals(null, response?.status)
    }

}