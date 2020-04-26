package care.vive.android.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import care.vive.android.util.BaseFragment
import com.vive.android.databinding.FragmentSplashBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashFragment : BaseFragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch {
            delay(2500)
            withContext(Dispatchers.Main){
                val directions = SplashFragmentDirections.actionSplashFragmentToStartFragment()
                navigate(directions)
            }
        }
    }
}
