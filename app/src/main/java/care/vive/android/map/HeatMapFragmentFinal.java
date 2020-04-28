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
    double pointX[]={40.712772, 40.712775, 40.709504, 40.709137, 40.709137, 40.711114,
            40.708977, 40.715036, 40.706762, 40.70792};
    double pointY[]={-74.006058, -74.005973, -74.014671, -74.013651, -74.013651, -74.010333,
            -74.009123, -74.01584, -74.008945, -74.007214};
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
