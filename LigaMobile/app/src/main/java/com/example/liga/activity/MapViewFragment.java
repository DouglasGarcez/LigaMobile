package com.example.liga.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.liga.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapViewFragment extends Fragment{

    MapView mMapView;

    private GoogleMap googleMap;
    private GoogleMap mMap;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final double log;
        final double lat;
        Bundle bundle = getArguments();
        if (bundle != null) {
            log = bundle.getDouble("log");
            lat = bundle.getDouble("lat");
        }
        else {
            log = 0;
            lat = 0;
        }


        View rootView = inflater.inflate(R.layout.location_fragment, container, false);
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        //mMapView.onStop();
       // mMapView.onDestroy();
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately
        mMapView.onLowMemory();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady (GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button

                //googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng local = new LatLng(log, lat);
                googleMap.addMarker(new MarkerOptions().position(local).title("Local da Partida").snippet(""));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(local).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }

        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public Intent getIntent () {

        return null;
    }
}