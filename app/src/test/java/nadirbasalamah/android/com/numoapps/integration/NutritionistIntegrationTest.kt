package nadirbasalamah.android.com.numoapps.integration

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class NutritionistIntegrationTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var nutViewModel: NutritionistViewModel

    @Before
    fun setup() {
        nutViewModel = NutritionistViewModel()
    }

    @Test
    fun IntAddNutRecord() {
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
        val testObserver = nutViewModel.updateAntropometry(data)
            ?.test()
            ?.assertNoValue()
        testObserver?.assertNoValue()
            ?.awaitValue()
            ?.assertHasValue()
            ?.assertValue { it?.status == true }
    }

    @Test
    fun IntAddEmptyNutRecord() {
        val data: HashMap<String, String> = HashMap()
        data.put("id", "3")
        data.put("tb","25")
        data.put("lila","25")
        data.put("bbi","25")
        data.put("fat","25")
        data.put("visceral_fat","25")
        data.put("muscle","25")
        data.put("body_age","25")
        val testObserver = nutViewModel.updateAntropometry(data)
            ?.test()
            ?.assertNoValue()
        testObserver?.assertNoValue()
            ?.awaitValue()
            ?.assertHasValue()
            ?.assertValue { it?.status == null }
    }

    @Test
    fun IntAddFoodMenu() {
        val data: HashMap<String, String> = HashMap()
        data.put("id","3")
        data.put("breakfast","Nasi")
        data.put("breakfast_time","07:00")
        data.put("lunch","Nasi Padang")
        data.put("lunch_time","14:00")
        data.put("dinner","Salad")
        data.put("dinner_time","18:30")
        val testObserver = nutViewModel.addFoodMenu(data)
            ?.test()
            ?.assertNoValue()
        testObserver?.assertNoValue()
            ?.awaitValue()
            ?.assertHasValue()
            ?.assertValue { it?.status == true }
    }

    @Test
    fun IntAddEmptyFoodMenu() {
        val data: HashMap<String, String> = HashMap()
        data.put("id","3")
        data.put("breakfast","Nasi")
        data.put("breakfast_time","07:00")
        data.put("lunch_time","14:00")
        data.put("dinner","Salad")
        data.put("dinner_time","18:30")
        val testObserver = nutViewModel.addFoodMenu(data)
            ?.test()
            ?.assertNoValue()
        testObserver?.assertNoValue()
            ?.awaitValue()
            ?.assertHasValue()
            ?.assertValue { it?.status == null }
    }

}