package care.vive.android.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import care.vive.android.util.BaseFragment
import com.vive.android.databinding.FragmentDateStatusBinding

class DateStatusFragment  : BaseFragment() {

    private val viewModelOnboarding: OnboardingViewModel by activityViewModels()
    private lateinit var binding: FragmentDateStatusBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDateStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listener: (View) -> Unit = {
            val directions = DateStatusFragmentDirections.actionDateStatusFragmentToBeContributingFragment()
            navigate(directions)
        }
        binding.btnNext.setOnClickListener(listener)
    }
}