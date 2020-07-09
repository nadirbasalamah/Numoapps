package nadirbasalamah.android.com.numoapps.viewmodel

import androidx.lifecycle.Observer
import nadirbasalamah.android.com.numoapps.model.response.AntropometryResponse
import nadirbasalamah.android.com.numoapps.util.NutritionistApiInterface
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NutritionistViewModelTest {
    private var mockWebServer = MockWebServer()
    private lateinit var nutApiInterface: NutritionistApiInterface

    @Before
    fun before() {
        mockWebServer.start()
        nutApiInterface = Retrofit.Builder()
            .baseUrl(mockWebServer.url("http://192.168.1.84:8080/klinikapps-api/public/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NutritionistApiInterface::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testUpdateAntropometry() {
        val data: HashMap<String, String> = HashMap()
        data.put("id", "3")
        data.put("bb","25")
        data.put("tb","25")
        data.put("lila","25")
        data.put("bbi","25")
        data.put("fat","25")
        data.put("visceral_fat","25")
        data.put("muscle","25")
        data.put("body_age","25")

        val result = nutApiInterface.postUpdateAntropometry(
            data["id"]?.toInt(),
            data["bb"]?.toFloat(),
            data["tb"]?.toFloat(),
            data["lila"]?.toFloat(),
            data["bbi"]?.toFloat(),
            data["fat"]?.toFloat(),
            data["visceral_fat"]?.toFloat(),
            data["muscle"]?.toFloat(),
            data["body_age"]?.toFloat()
        )?.execute()

        val response = result?.body()
        assertEquals(true, response?.status)
    }

    @Test
    fun testUpdateEmptyAntropometry() {
        val data: HashMap<String, String> = HashMap()
        data.put("id", "3")
        data.put("tb","25")
        data.put("lila","25")
        data.put("bbi","25")
        data.put("fat","25")
        data.put("visceral_fat","25")
        data.put("muscle","25")
        data.put("body_age","25")

        val result = nutApiInterface.postUpdateAntropometry(
            data["id"]?.toInt(),
            null,
            data["tb"]?.toFloat(),
            data["lila"]?.toFloat(),
            data["bbi"]?.toFloat(),
            data["fat"]?.toFloat(),
            data["visceral_fat"]?.toFloat(),
            data["muscle"]?.toFloat(),
            data["body_age"]?.toFloat()
        )?.execute()

        val response = result?.body()
        assertEquals(null, response?.status)
    }

    @Test
    fun testAddFoodMenu() {
        val data: HashMap<String, String> = HashMap()
        data.put("id","3")
        data.put("breakfast","Nasi")
        data.put("breakfast_time","07:00")
        data.put("lunch","Nasi Padang")
        data.put("lunch_time","14:00")
        data.put("dinner","Salad")
        data.put("dinner_time","18:30")

        val result = nutApiInterface.postAddFoodMenu(
            data["id"]?.toInt(),
            data["breakfast"],
            data["breakfast_time"],
            data["lunch"],
            data["lunch_time"],
            data["dinner"],
            data["dinner_time"]
        )?.execute()

        val response = result?.body()
        assertEquals(true, response?.status)
    }

    @Test
    fun testAddEmptyFoodMenu() {
        val data: HashMap<String, String> = HashMap()
        data.put("id","3")
        data.put("breakfast","Nasi")
        data.put("breakfast_time","07:00")
        data.put("lunch_time","14:00")
        data.put("dinner","Salad")
        data.put("dinner_time","18:30")

        val result = nutApiInterface.postAddFoodMenu(
            data["id"]?.toInt(),
            data["breakfast"],
            data["breakfast_time"],
            null,
            data["lunch_time"],
            data["dinner"],
            data["dinner_time"]
        )?.execute()

        val response = result?.body()
        assertEquals(null, response?.status)
    }

}