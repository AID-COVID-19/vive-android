package care.vive.android.onboarding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import care.vive.android.util.BaseFragment
import care.vive.android.util.Utils
import com.vive.android.databinding.FragmentStartBinding

class StartFragment : BaseFragment() {

    private val viewModelOnboarding: OnboardingViewModel by activityViewModels()
    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val utils: Utils = Utils()
        binding = FragmentStartBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listener: (View) -> Unit = {
            val directions = StartFragmentDirections.actionStartFragmentToShareCovidStatusFragment()
            navigate(directions)
        }
        binding.btnNext.setOnClickListener(listener)
    }

}
