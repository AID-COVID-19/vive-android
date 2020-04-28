package care.vive.android.onboarding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import care.vive.android.util.BaseFragment
import com.soywiz.klock.Date
import com.vive.android.databinding.FragmentDateStatusBinding
import java.util.*

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
        setupCalendar()
    }

    private lateinit var calendar: Calendar

    private fun setupCalendar(){
        val listener: (View) -> Unit = {
            binding.datePicker.visibility = View.VISIBLE
        }
        binding.editTextDate.setOnClickListener(listener)
        // Get the Calendar current year, month and day of month
        calendar = Calendar.getInstance()
        var thisYear = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)

        if(viewModelOnboarding.dateSymptomsLocal != null) {
            // Get the Calendar current year, month and day of month
            thisYear = viewModelOnboarding.dateSymptomsLocal!!.year
            month = viewModelOnboarding.dateSymptomsLocal!!.month.ordinal
            day = viewModelOnboarding.dateSymptomsLocal!!.day
        }

        // Initialize the date picker widget with system current date
        binding.run {
            // Initialize the date picker widget with system current date
            datePicker.init(
                thisYear,
                month,
                day
            ) { view, year, monthOfYear, dayOfMonth ->
                val date = Date.invoke(year, monthOfYear, dayOfMonth)
                viewModelOnboarding.dateSymptomsLocal = date
                val stringDate = date.format(format = "EEE, dd MMM yyyy")
                binding.editTextDate.setText(stringDate)
                binding.editTextDate.visibility = View.GONE
            }
        }
    }
}



