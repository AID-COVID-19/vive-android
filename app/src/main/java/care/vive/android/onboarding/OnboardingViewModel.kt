package care.vive.android.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import care.vive.android.covid.CovidStatus
import com.soywiz.klock.Date

class OnboardingViewModel : ViewModel() {

    var covidSelectedId: Int = -1
    lateinit var covidStatus: CovidStatus
    var dateSymptomsLocal: Date? = null

    private val _text = MutableLiveData<String>().apply {
        value = "This is Check Fragment"
    }
    val text: LiveData<String> = _text
}