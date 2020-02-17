package com.example.smartpay;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback  {

    float cameraZoom = 11;

    private static final LatLng CITYLOCATION = new LatLng(23.071993, 72.593466);
    private static final LatLng ISCONMART = new LatLng(23.025359, 72.507440);
    private static final LatLng NIKOLMART = new LatLng(23.051216, 72.667364);
    private static final LatLng GOTAMART = new LatLng(23.102316, 72.536064);


    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        /*LatLng ahmedabadLatLng = new LatLng(23.071993, 72.593466);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ahmedabadLatLng, cameraZoom));*/

        /*LatLng osia1 = new LatLng(23.025359, 72.507440);
        mMap.addMarker(new MarkerOptions().position(osia1).title(Hypermart+",Iscon"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(osia1));

        LatLng osia2 = new LatLng(23.051216, 72.667364);
        mMap.addMarker(new MarkerOptions().position(osia2).title(Hypermart+",Nikol"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(osia2));

        LatLng osia3 = new LatLng(23.102316, 72.536064);
        mMap.addMarker(new MarkerOptions().position(osia3).title(Hypermart+",Gota"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(osia3));

        LatLng osia4 = new LatLng(23.102984, 72.595210);
        mMap.addMarker(new MarkerOptions().position(osia4).title(Hypermart+",Chandkheda"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(osia4));*/

        mMap = map;
        LatLng ahmedabadLatLng = new LatLng(23.071993, 72.593466);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ahmedabadLatLng, cameraZoom));

        // Add some markers to the map, and add a data object to each marker.

        mPerth = mMap.addMarker(new MarkerOptions()
                .position(ISCONMART)
                .title("ISCON"));
        mPerth.setTag(0);

        mSydney = mMap.addMarker(new MarkerOptions()
                .position(NIKOLMART)
                .title("NIKOL"));
        mSydney.setTag(0);

        mBrisbane = mMap.addMarker(new MarkerOptions()
                .position(GOTAMART)
                .title("GOTA"));
        mBrisbane.setTag(0);

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(searchIntent);
        return false;
    }

}
