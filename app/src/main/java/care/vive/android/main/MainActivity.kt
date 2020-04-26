package care.vive.android.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import care.vive.android.onboarding.OnboardingActivity
import com.vive.android.R
import kotlinx.android.synthetic.main.activity_main.*
import zendesk.core.AnonymousIdentity
import zendesk.core.Identity
import zendesk.core.Zendesk
import zendesk.support.Support

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        zendeskSetup()
        // Finding the Navigation Controller
        val navController = findNavController(R.id.nav_host_fragment)

        // Setting Navigation Controller with the BottomNavigationView
        bottomNavigationView.setupWithNavController(navController)
    }

    fun openAlarmActivity(view: View) {
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
    }

    fun zendeskSetup() {
        Zendesk.INSTANCE.init(this,
            "https://vivecare.zendesk.com",
            "91f4318a41083b9e9621d967538ddefd21f0e234cf7d974d",
            "mobile_sdk_client_fec8e602e358369790a9")
        val identity: Identity = AnonymousIdentity()
        Zendesk.INSTANCE.setIdentity(identity)
        Support.INSTANCE.init(Zendesk.INSTANCE)
    }

}
