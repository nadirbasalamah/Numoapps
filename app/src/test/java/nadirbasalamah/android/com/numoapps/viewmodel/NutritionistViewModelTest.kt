package nadirbasalamah.android.com.numoapps.viewmodel

import androidx.lifecycle.Observer
import nadirbasalamah.android.com.numoapps.model.response.AntropometryResponse
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NutritionistViewModelTest {
    private lateinit var nutritionistViewModel: NutritionistViewModel

    @Before
    fun before() {
        nutritionistViewModel = NutritionistViewModel()
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
        val result = nutritionistViewModel.updateAntropometry(data)
        assertEquals(true, result?.value?.status)
    }

    @Test
    fun testUpdateEmptyAntropometry() {
        val data: HashMap<String, String> = HashMap()
        val result = nutritionistViewModel.updateAntropometry(data)
        assertEquals(false, result?.value?.status)
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
        val result = nutritionistViewModel.addFoodMenu(data)
        assertEquals(true, result?.value?.status)
    }

    @Test
    fun testAddEmptyFoodMenu() {
        val data: HashMap<String, String> = HashMap()
        val result = nutritionistViewModel.addFoodMenu(data)
        assertEquals(false, result?.value?.status)
    }

}