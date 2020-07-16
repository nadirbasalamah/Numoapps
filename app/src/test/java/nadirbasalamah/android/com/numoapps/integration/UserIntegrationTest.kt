package nadirbasalamah.android.com.numoapps.integration

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import nadirbasalamah.android.com.numoapps.patient.ui.home.HomeFragment
import nadirbasalamah.android.com.numoapps.viewmodel.UserViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

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