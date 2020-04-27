package care.vive.android.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import care.vive.android.covid.CovidStatus
import care.vive.android.util.BaseFragment
import com.vive.android.R
import com.vive.android.databinding.FragmentCovidStatusBinding

class CovidStatusFragment  : BaseFragment() {

    private val viewModelOnboarding: OnboardingViewModel by activityViewModels()
    private lateinit var binding: FragmentCovidStatusBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCovidStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listener: (View) -> Unit = {
            binding.btnNext.showLoading()
            binding.btnNext.isEnabled = false
            val directions = CovidStatusFragmentDirections.actionCovidStatusFragmentToDateStatusFragment()
            navigate(directions)
        }
        binding.btnNext.setOnClickListener(listener)
        setupControls()
    }

    private fun setupControls(){
        binding.radioGroupCovidStatus.setOnCheckedChangeListener { _, checkedId ->
            binding.btnNext.isEnabled = true
            viewModelOnboarding.covidSelectedId = checkedId
            viewModelOnboarding.covidStatus = when (checkedId) {
                R.id.radioPossibleHaveCovid -> CovidStatus.HAVE_OR_POSSIBLE_HAVE_COVID
                R.id.radioHaveCovidDiagnosed -> CovidStatus.HAD_COVID_DIAGNOSED_BY_PHYSICIAN
                R.id.radioPossibleIHaveHadCovid -> CovidStatus.POSSIBLE_HAVE_HAD_COVID
                R.id.radioPositiveAntibodies -> CovidStatus.TESTED_POSITIVE_ANTIBODIES
                R.id.radioNotHadDoNotKnow -> CovidStatus.HAVE_NOT_HAD_COVID_OR_DO_NOT_KNOW_STATUS
                else -> CovidStatus.HAD_COVID_DIAGNOSED_BY_PHYSICIAN
            }
        }
    }

}