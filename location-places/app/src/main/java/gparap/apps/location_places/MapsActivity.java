package gparap.apps.location_places;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gparap on 2020-09-30.
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    final int REQUEST_CODE = 999;                           //request code passed in requestPermissions
    final long LOCATION_REQUEST_INTERVAL = 5000L;           //interval for location updates (milliseconds)
    final int DEFAULT_ZOOM_LEVEL = 12;                      //default map camera zoom level
    final int CITY_ZOOM_LEVEL = 10;                         //map camera zoom level (city)
    final int RADIUS = 5000;                                //user proximity radius from location
    private GoogleMap map;                                  //google map
    double latitude, longitude;                             //location coordinates
    FusedLocationProviderClient fusedLocationProviderClient;//main entry point for interacting with the fused location provider
    private LocationRequest locationRequest;                //object used to request location updates
    private LocationCallback locationCallback;              //used for receiving notifications when device location has changed
    EditText editTextSearch;                                //used for custom search of named location
    ImageButton imageButtonSearchText,                      //button for custom search
                imageButtonSearchSpinner;                   //button for spinner search
    Spinner spinnerSearch;                                  //spinner
    String[] spinnerTypes;                                  //places api types
    Marker markerMyLocation;                                //user location marker
    ArrayList<Marker> markers;                              //holds all markers except my location
    HttpURLConnection httpURLConnection;                    //URLConnection for HTTP places api data requests
    StringBuilder stringBuilder;                            //helper to hold url connection data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check device gms (google play services)
        if (!verifyGoogleApiAvailability()) {
            finish();
            return;
        }

        setContentView(R.layout.activity_maps);
        init();

        //notify when map is ready for use
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    //Initialization
    private void init() {
        latitude = 0.0;
        longitude = 0.0;
        markerMyLocation = null;
        markers = new ArrayList<>();

        //create a location request object
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(LOCATION_REQUEST_INTERVAL);

        //get widgets
        editTextSearch = findViewById(R.id.editTextSearch);
        imageButtonSearchText = findViewById(R.id.imageButtonSearchText);
        imageButtonSearchSpinner = findViewById(R.id.imageButtonSearchSpinner);
        spinnerSearch = findViewById(R.id.spinnerSearch);

        //initialize spinner with points of interest
        ArrayAdapter<CharSequence> spinnerArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.points_of_interest_array, android.R.layout.simple_spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSearch.setAdapter(spinnerArrayAdapter);
        spinnerSearch.setSelection(1);  //display airports instead of 1st item (accounting)

        //initialize array of all Place API type values
        spinnerTypes = new String[]{
                "accounting", "airport", "amusement_park", "aquarium", "art_gallery", "atm",
                "bakery", "bank", "bar", "beauty_salon", "bicycle_store", "book_store", "bowling_alley", "bus_station",
                "cafe", "campground", "car_dealer", "car_rental", "car_repair", "car_wash", "casino", "cemetery", "church", "city_hall", "clothing_store", "convenience_store", "courthouse",
                "dentist", "department_store", "doctor", "drugstore",
                "electrician", "electronics_store", "embassy",
                "fire_station", "florist", "funeral_home", "furniture_store",
                "gas_station", "gym",
                "hair_care", "hardware_store", "hindu_temple", "home_goods_store", "hospital",
                "insurance_agency", "jewelry_store",
                "laundry", "lawyer", "library", "light_rail_station", "liquor_store", "local_government_office", "locksmith", "lodging",
                "meal_delivery", "meal_takeaway", "mosque", "movie_rental", "movie_theater", "moving_company", "museum",
                "night_club",
                "painter", "park", "parking", "pet_store", "pharmacy", "physiotherapist", "plumber", "police", "post_office", "primary_school",
                "real_estate_agency", "restaurant", "roofing_contractor", "rv_park",
                "school", "secondary_school", "shoe_store", "shopping_mall", "spa", "stadium", "storage", "store", "subway_station", "supermarket", "synagogue",
                "taxi_stand", "tourist_attraction", "train_station", "transit_station", "travel_agency", "university",
                "veterinary_care",
                "zoo"
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //check location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);
            //noinspection UnnecessaryReturnStatement
            return;
        } else {
            getLastLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    //Verify that the Google Play services APK is available and up-to-date on this device
    private boolean verifyGoogleApiAvailability() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int statusCode = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (statusCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(statusCode)) {
                //user needs to install an update
                Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(
                        MapsActivity.this, statusCode, REQUEST_CODE,
                        new android.content.DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                finishAndRemoveTask();
                                Toast.makeText(getApplicationContext(),
                                        "Please update Google Play Services", Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.show();
            } else {
                //API will not work on this device
                Toast.makeText(this, "No Google Play Services on this device", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    //Return the best most recent location currently available
    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        //create a new instance of FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //get location coordinates
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //get latitude and longitude coordinates
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                //my-location point zoomed
                map.setMyLocationEnabled(true);
                if (markers.isEmpty()) {
                    map.moveCamera(CameraUpdateFactory.zoomBy(DEFAULT_ZOOM_LEVEL));
                }
            }
        });

        //notify when device location has changed
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location location : locationResult.getLocations()) {
                    //get latitude and longitude coordinates
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    // add a marker in device location and move the camera
                    LatLng myLocation = new LatLng(latitude, longitude);
                    if (markerMyLocation != null) {
                        //clear my location marker before update
                        markerMyLocation.remove();
                    }
                    markerMyLocation = map.addMarker(new MarkerOptions().position(myLocation).title("My Location"));
                    if (markers.isEmpty()) {
                        map.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                    }
                }
            }
        };

        //update location coordinates
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    //User presses the search "address or place" button
    @SuppressLint("MissingPermission")
    public void onClick(View view) {
        //stop updating location (temporary)
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);

        //clear previous markers from map and list
        if (!markers.isEmpty()) {
            for (Marker marker : markers) {
                marker.remove();
            }
            markers.clear();
        }

        //find button pressed
        switch (view.getId()) {
            //User searches for a point of interest
            case R.id.imageButtonSearchSpinner:
                //find the POI Place API type based on spinner selection
                String type = spinnerTypes[spinnerSearch.getSelectedItemPosition()];

                //create url
                final String strURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
                        + "location=" + latitude + "," + longitude
                        + "&radius=" + RADIUS + "&type=" + type + "&sensor=true"
                        + "&key=" + getResources().getString(R.string.google_maps_key);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(strURL);
                            httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.connect();
                            InputStream inputStream = httpURLConnection.getInputStream();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            String readLine;
                            stringBuilder = new StringBuilder();
                            while ((readLine = bufferedReader.readLine()) != null) {
                                stringBuilder.append(readLine);
                            }

                            //free buffer and stream resources
                            bufferedReader.close();
                            inputStream.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            //free connection resources
                            httpURLConnection.disconnect();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //get results from url connection
                                try {
                                    JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                                    //loop jsonArray for all points of interest
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        //get name
                                        String name = jsonArray.getJSONObject(i).getString("name");

                                        //get location coordinates
                                        JSONObject location = new JSONObject(String.valueOf(
                                                new JSONObject(String.valueOf(jsonArray.getJSONObject(i).get("geometry")))
                                                        .get("location")));
                                        Double lat = (Double) location.get("lat");
                                        Double lng = (Double) location.get("lng");

                                        //add point of interest to map
                                        addMapMarker(lat, lng, name);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }).start();
                break;

            //User searches for a named location
            case R.id.imageButtonSearchText:
                //check if edit text not null
                if (!editTextSearch.getText().toString().isEmpty()) {
                    //force hide virtual keyboard
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(editTextSearch.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    //get nearby addresses
                    if (Geocoder.isPresent()) {
                        Geocoder geocoder = new Geocoder(this);
                        try {
                            //get addresses that describe the named location
                            List<Address> addresses = geocoder.getFromLocationName(editTextSearch.getText().toString(), 5);
                            if (addresses.isEmpty()) {
                                return;
                            }
                            //display addresses to map
                            for (Address address : addresses) {
                                addMapMarker(address);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    //geocoding NOT present
                    else {
                        Toast.makeText(this, "LOCATION SERVICE ERROR", Toast.LENGTH_SHORT).show();
                    }
                    //edit text NULL
                } else {
                    Toast.makeText(this, "Please type named location", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        //restart location updates
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    @Override
    protected void onStop() {
        super.onStop();

        //stop updating location
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    /**
     * Adds a marker to a specific position on the map
     *
     * @param address an Address describing a location
     */
    private void addMapMarker(Address address) {
        //get location coordinates
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

        //create marker options
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        markerOptions.position(latLng);
        markerOptions.title(address.getFeatureName());

        //add marker to map and move camera
        markers.add(map.addMarker(markerOptions));
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.moveCamera(CameraUpdateFactory.zoomTo(CITY_ZOOM_LEVEL));
    }

    /**
     * Adds a marker to a specific position on the map
     *
     * @param latitude  marker latitude on map
     * @param longitude marker longitude
     * @param name      name of marker on map
     */
    private void addMapMarker(Double latitude, Double longitude, String name) {
        //get location coordinates
        LatLng latLng = new LatLng(latitude, longitude);

        //create marker options
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        markerOptions.position(latLng);
        markerOptions.title(name);

        //add marker to map and move camera
        markers.add(map.addMarker(markerOptions));
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.moveCamera(CameraUpdateFactory.zoomTo(CITY_ZOOM_LEVEL));
    }
}