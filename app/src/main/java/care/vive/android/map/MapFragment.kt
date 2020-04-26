package care.vive.android.map

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vive.android.R
import com.vive.android.databinding.FragmentMapBinding

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapViewModel: MapViewModel
    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapBinding

    companion object {
        var mapFragment : SupportMapFragment?=null
    }

    var initial_latitude  = -34.0
    var initial_longitude = 151.0
    var initial_marker    = "Seed nay"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(this)

        return binding.root
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(initial_latitude, initial_longitude)
        mMap.addMarker(MarkerOptions().position(sydney).title(initial_marker))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        Toast.makeText(context, "OnMapReady end", Toast.LENGTH_LONG).show()
    }

}
