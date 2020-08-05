package com.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.testapp.dto.LocationDTO;

import java.util.ArrayList;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    View view;
    private MapView mMapView;
    private Marker marker;
    private GoogleMap googleMap;
    Context mContext;
    private ArrayList<MarkerOptions> optionsList = new ArrayList<>();
    private static ArrayList<LocationDTO> locationDTOS ;
    private Hashtable<String, LocationDTO> markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext= MainActivity.this;
        mMapView=(MapView)findViewById(R.id.mapView);

        mMapView.onCreate(savedInstanceState);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        markers = new Hashtable<String, LocationDTO>();
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(Double.parseDouble("22.7145446"), Double.parseDouble("75.8882503"));
                //  googleMap.addMarker(new MarkerOptions().position(sydney).title(userDTO.getName()).title("My Location").snippet(userDTO.getUser_id()));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(14).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }
        });


    }

    private void addData() {
        locationDTOS = new ArrayList<LocationDTO>();

        locationDTOS.add(new LocationDTO("22.7454534"," 75.8935588","JadeBlue"));
        locationDTOS.add(new LocationDTO("22.750702"," 75.8948628","Nagar Nigam"));
        locationDTOS.add(new LocationDTO("22.7443792"," 75.8720681","Post Office"));
        locationDTOS.add(new LocationDTO("22.7423607"," 75.8727547","DNS Hospital"));
        locationDTOS.add(new LocationDTO("22.7226112"," 75.8855428","Varda Vihar"));

        for (int i = 0; i < locationDTOS.size(); i++) {

            optionsList.add(new MarkerOptions().position(new LatLng(Double.parseDouble(locationDTOS.get(i).getLatitude()), Double.parseDouble(locationDTOS.get(i).getLongitude()))).title(locationDTOS.get(i).getName()));

        }

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }else
        setData();


    }





    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();

        if (NetworkManager.isConnectToInternet(mContext)) {

            addData();


        } else {

        }

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


    public void setData() {


        Log.e("TAG", "setData: "+locationDTOS.size());



            mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(mContext.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "setDatainit: "+e );
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the fabcustomer grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);




                for (MarkerOptions options : optionsList) {

                    options.position(options.getPosition());
                    options.title(options.getTitle());
                    options.snippet(options.getSnippet());
                    options.draggable(false);
                    final Marker hamburg = googleMap.addMarker(options);


                                    CameraPosition cameraPositio1n = new CameraPosition.Builder().target(options.getPosition()).zoom(12).build();
                                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPositio1n));

                    for (int i = 0; i < locationDTOS.size(); i++) {

                        markers.put(hamburg.getId(), locationDTOS.get(i));
                         CameraPosition cameraPosition = new CameraPosition.Builder().target(options.getPosition()).zoom(12).build();
                         googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                        googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

                    }
                }

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(final Marker marker) {

                        marker.showInfoWindow();

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                marker.showInfoWindow();

                            }
                        }, 200);

                        return false;
                    }
                });

                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                    @Override
                    public void onInfoWindowClick(Marker arg0) {

                    }
                });


            }
        });


    }


    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private View view;

        public CustomInfoWindowAdapter() {
            view = getLayoutInflater().inflate(R.layout.custom_info_window,
                    null);

        }


        @Override
        public View getInfoContents(Marker marker) {

            if (marker != null
                    && marker.isInfoWindowShown()) {
                marker.hideInfoWindow();
                marker.showInfoWindow();
            }
            return null;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            marker = marker;

            String name = null;

            if (marker.getId() != null && markers != null && markers.size() > 0) {
                if (markers.get(marker.getId()) != null &&
                        markers.get(marker.getId()) != null) {

                    name = markers.get(marker.getId()).getName();
                }
            }


            //final String title = marker.getTitle();
            final TextView titleUi = ((TextView) view.findViewById(R.id.title));
            if (name != null) {
                titleUi.setText(name);
            } else {
                titleUi.setText("");
            }


            return view;
        }
    }
}

