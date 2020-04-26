package care.vive.android.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates

class OnboardingViewModel : ViewModel() {

    var riskScore: Double = 0.0

    var timeStampLongId by Delegates.notNull<Long>()

    var howYouFeelSelectedId: Int = -1
    var temperatureRangeSelectedId: Int = -1
    var breathRangeSelectedId: Int = -1

    var howYouFeel: String? = null

    var generalDiscomfort: Boolean = false
    var itchyOrSoreThroat: Boolean = false
    var diarrhea: Boolean = false
    var badTasteInTheMouth: Boolean = false
    var lossOfTasteInFood: Boolean = false
    var lossOfSmell: Boolean = false
    var musclePains: Boolean = false
    var chestOrBackPain: Boolean = false
    var headache: Boolean = false
    var wetCoughWithPhlegm: Boolean = false
    var dryCough: Boolean = false
    var chill: Boolean = false
    var fever: Boolean = false
    var fatigueWhenWalkingOrClimbingStairs: Boolean = false
    var feelingShortOfBreathWithDailyActivities: Boolean = false
    var respiratoryDistress: Boolean = false
    var newConfusionOrInabilityToArouse: Boolean = false
    var bluishLipsOrFace: Boolean = false
    var otherSymptomsOrDiscomfort: String? = null

    var temperatureRange: String? = null
    var breathsPerMinuteRange: String? = null
    var haveYouBeenNervousOrAnxious: String? = null
    var couldntStopBeingWorried: String? = null
    var haveYouWorriedTooMuchAboutDifferentThings: String? = null
    var haveYouHadDifficultyRelaxing: String? = null
    var haveYouBeenSoRestlessThatItIsDifficultToStayStill: String? = null
    var haveYouBecomeEasilyAnnoyedOrIrritable: String? = null
    var haveYouFeltFearAsIfSomethingHorribleWasGoingToHappen: String? = null
    var areYouTakingYourMedications: String? = null

    init {
        // AWSMobileClient.getInstance().userAttributes[""]
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is Check Fragment"
    }
    val text: LiveData<String> = _text
}