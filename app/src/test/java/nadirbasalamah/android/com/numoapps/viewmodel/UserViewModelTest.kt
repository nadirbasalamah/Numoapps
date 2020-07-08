package nadirbasalamah.android.com.numoapps.viewmodel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class UserViewModelTest {
    private lateinit var userViewModel: UserViewModel

    @Before
    fun before() {
        userViewModel = UserViewModel()
    }

    @Test
    fun ViewNutRecord() {
       val result = userViewModel.getNutRecordByUserId(3)
       assertEquals(true, result?.value?.status)
    }

    @Test
    fun ViewEmptyNutRecord() {
        val result = userViewModel.getNutRecordByUserId(99)
        assertEquals(false, result?.value?.status)
    }

}