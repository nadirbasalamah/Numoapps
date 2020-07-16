package nadirbasalamah.android.com.numoapps.integration

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.jraska.livedata.TestLifecycle
import com.jraska.livedata.TestObserver
import com.jraska.livedata.test
import kotlinx.coroutines.runBlocking
import nadirbasalamah.android.com.numoapps.model.response.NutritionRecordResponse
import nadirbasalamah.android.com.numoapps.patient.ui.home.HomeFragment
import nadirbasalamah.android.com.numoapps.viewmodel.UserViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*
import org.mockito.Spy
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.coroutines.coroutineContext

class UserIntegrationTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userViewModel: UserViewModel
    private lateinit var patientHomeFragment: HomeFragment

    @Before
    fun setup() {
        userViewModel = UserViewModel()
        patientHomeFragment = mock(HomeFragment::class.java)
    }

    @Test
    fun IntViewNutRecord() {
        val testObserver = userViewModel.getNutRecordByUserId(6)
            ?.test()
            ?.assertNoValue()
        testObserver?.assertNoValue()
            ?.awaitValue()
            ?.assertHasValue()
            ?.assertValue { it?.status == true }
    }

    @Test
    fun IntViewEmptyNutRecord() {
        val testObserver = userViewModel.getNutRecordByUserId(43)
            ?.test()
            ?.assertNoValue()
        testObserver?.assertNoValue()
            ?.awaitValue()
            ?.assertHasValue()
            ?.assertValue { it?.status == null }
    }

}