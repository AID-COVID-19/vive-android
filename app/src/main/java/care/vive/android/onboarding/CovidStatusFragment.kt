package care.vive.android.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import care.vive.android.util.BaseFragment
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
            val directions = CovidStatusFragmentDirections.actionCovidStatusFragmentToDateStatusFragment()
            navigate(directions)
        }
        binding.btnNext.setOnClickListener(listener)
    }

}