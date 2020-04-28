package care.vive.android.map

import android.content.Intent
import android.os.Bundle
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
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.vive.android.R
import com.vive.android.databinding.FragmentMapBinding
import java.util.*


open class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mapViewModel: MapViewModel
    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapBinding

    companion object {
        var mapFragment : SupportMapFragment?=null
    }

    var initial_latitude  = 40.7128
    var initial_longitude = -74.0061
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
    fun getmMap(): GoogleMap? {
        return mMap
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
        val newyork = LatLng(initial_latitude, initial_longitude)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newyork))
        addHeatMap();
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10.0F))
        Toast.makeText(context, "OnMapReady end", Toast.LENGTH_LONG).show()
    }

    open fun addHeatMap() {
        /*List<WeightedLatLng> list = new ArrayList<>();
        LatLng sydney = new LatLng(33.8688, 151.2093);
        WeightedLatLng pass = new WeightedLatLng(sydney);
        list.add(pass);

         */
        val pointX = doubleArrayOf(
            40.712772, 40.712775, 40.709504, 40.709137, 40.709137, 40.711114,
            40.708977, 40.715036, 40.706762, 40.70792
        )
        val pointY = doubleArrayOf(
            -74.006058, -74.005973, -74.014671, -74.013651, -74.013651, -74.010333,
            -74.009123, -74.01584, -74.008945, -74.007214
        )
        val coordinates: MutableList<LatLng> = ArrayList()
        for (i in 0 until pointX.size - 1) {
            coordinates.add(LatLng(pointX[i], pointY[i]))
        }
        val mProvider = HeatmapTileProvider.Builder()
            .data(coordinates)
            .build()
        // Add a tile overlay to the map, using the heat map tile provider.
        val mOverlay =
            mMap.addTileOverlay(TileOverlayOptions().tileProvider(mProvider))
        mOverlay.clearTileCache()
    }

}
