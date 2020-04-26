package care.vive.android.map;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.vive.android.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HeatMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeatMapFragment extends MapFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    HeatMapFragment() {
        createHeatMapFragment();
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeatMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HeatMapFragment newInstance(String param1, String param2) {
        HeatMapFragment fragment = createHeatMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static HeatMapFragment createHeatMapFragment() {
        return new HeatMapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            createHeatMapFragment();
            addHeatMap();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_heat_map, container, false);
    }


    private static final int ALT_HEATMAP_RADIUS = 10;

    private static final double ALT_HEATMAP_OPACITY = 0.4;

    private static final int[] ALT_HEATMAP_GRADIENT_COLORS = {
            Color.argb(0, 0, 255, 255),// transparent
            Color.argb(255 / 3 * 2, 0, 255, 255),
            Color.rgb(0, 191, 255),
            Color.rgb(0, 0, 127),
            Color.rgb(255, 0, 0)
    };
    public static final float[] ALT_HEATMAP_GRADIENT_START_POINTS = {
            0.0f, 0.10f, 0.20f, 0.60f, 1.0f
    };
    public static final Gradient ALT_HEATMAP_GRADIENT = new Gradient(ALT_HEATMAP_GRADIENT_COLORS,
            ALT_HEATMAP_GRADIENT_START_POINTS);
    private HeatmapTileProvider mProvider;
    private TileOverlay mOverlay;
    double pointY[]={105.45526,105.57364,105.53505,105.45523,105.51962,105.77320};
    double pointX[]={9.99222,9.88347,9.84184,9.77197,9.55501,9.67768,9};
    private LatLng latlng = new LatLng(40.7484, 73.9857);

    public void addHeatMap() {
        List<LatLng> coordinates=new ArrayList<LatLng>();
        for (int i = 0 ; i < pointX.length - 1; i++){
            coordinates.add(new LatLng(pointX[i],pointY[i]));
        };
        mProvider = new HeatmapTileProvider.Builder().data(coordinates).build();
        mProvider.setRadius(ALT_HEATMAP_RADIUS);
        mOverlay.clearTileCache();
        getmMap().addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }
}
