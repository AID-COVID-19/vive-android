package care.vive.android.map;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.vive.android.R;

import java.util.ArrayList;
import java.util.List;


public class HeatMapFragmentFinal extends MapFragment {

    public HeatMapFragmentFinal() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            createHeatMapFragment();
            addHeatMap();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_heat_map_final, container, false);
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

    public static HeatMapFragmentFinal createHeatMapFragment() {
        return new HeatMapFragmentFinal();
    }

    public static final Gradient ALT_HEATMAP_GRADIENT = new Gradient(ALT_HEATMAP_GRADIENT_COLORS,
            ALT_HEATMAP_GRADIENT_START_POINTS);
    private HeatmapTileProvider mProvider;
    private TileOverlay mOverlay;
    double pointX[]={40.712772, 40.712775, 40.709504, 40.709137, 40.709137, 40.711114, 40.708977,
            40.715036, 40.706762, 40.70792, 40.704642, 40.70395, 40.709797, 40.705129, 40.705562,
            40.715947, 40.705063, 40.704129, 40.715534, 40.706877, 40.717755, 40.717755,
            40.7127753, 40.7098307, 40.7095039, 40.7091366, 40.711114, 40.708976700000,
            40.7150355, 40.7067619, 40.7079177, 40.7046417, 40.7039466, 40.709796800000,
            40.7051285, 40.7055616, 40.7159471, 40.705063, 40.704128999999, 40.715534100000,
            40.706877, 40.7177545, 40.70598, 40.70441, 40.70555, 40.70398, 40.70271, 40.70216,
            40.70801, 40.70687, 40.70706, 40.70515, 40.7057, 40.70639, 40.70436, 40.70326,
            40.70343, 40.70394, 40.70429, 40.70427, 40.70486, 40.71191, 40.71274, 40.712,
            40.71284, 40.71466, 40.71814, 40.70944, 40.71444, 40.71131, 40.71368, 40.71517,
            40.71453, 40.71915, 40.71474, 40.72126, 40.72266, 40.72708, 40.72755, 40.72332,
            40.723, 40.72254, 40.72214, 40.72246, 40.72273, 40.72315, 40.72237, 40.72325,
            40.72309, 40.72298, 40.72285, 40.72294, 40.72291, 40.72289, 40.72296, 40.72724,
            40.72592, 40.73582, 40.73082, 40.73199, 40.73383, 40.7327};
    double pointY[]={-74.006058, -74.005973, -74.014671, -74.013651, -74.013651, -74.010333,
            -74.009123, -74.01584, -74.008945, -74.007214, -74.010322, -74.012354, -74.013888,
            -74.007936, -74.007189,  -74.007367, -74.006177, -74.010336, -74.012052, -74.011265,
            -74.043143, -74.043143, -74.0059728, -74.0140195, -74.0146714999, -74.0136508,
            -74.010333, -74.0091231, -74.0158397, -74.0089447, -74.0072143, -74.0103215,
            -74.0123539, -74.01388779999, -74.0079362, -74.00718909999, -74.0073669, -74.006177,
            -74.010336, -74.0120518, -74.0112654, -74.0431435, -74.01854, -74.0179, -74.01344,
            -74.01368, -74.01072, -74.01502, -74.015, -74.01126, -74.00354, -74.00396, -74.00308,
            -74.00944, -74.01004, -74.01102, -74.00919, -74.01235, -74.01295, -74.01187,
            -74.01253, -74.01261, -74.01216, -74.01524, -74.00773, -74.01114, -74.00481,
            -73.99569, -73.99825, -74.00188, -73.99718, -74.00178, -74.00446, -74.00493,
            -73.99355, -73.98384, -73.99822, -73.989, -74.0081, -74.00442, -74.00304, -74.00379,
            -74.00462, -74.00481, -74.00469, -74.0029, -74.00397, -74.0037, -74.0037,
            -74.00346, -74.00437, -74.00467, -74.0044, -74.00452, -74.00462, -74.00753,
            -74.00948, -73.99052, -73.99733, -73.99622, -74.00216, -74.00431};
    private Point mPoint;
    private double mIntensity;

    public void addHeatMap() {
        /*List<WeightedLatLng> list = new ArrayList<>();
        LatLng sydney = new LatLng(33.8688, 151.2093);
        WeightedLatLng pass = new WeightedLatLng(sydney);
        list.add(pass);

         */
        List<LatLng> coordinates=new ArrayList<LatLng>();
        for (int i = 0 ; i < pointX.length - 1; i++){
            coordinates.add(new LatLng(pointX[i],pointY[i]));
        };
        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                .data(coordinates)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        TileOverlay mOverlay = getmMap().addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
        mOverlay.clearTileCache();
    }
}
