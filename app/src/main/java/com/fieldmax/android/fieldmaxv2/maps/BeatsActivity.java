package com.fieldmax.android.fieldmaxv2.maps;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fieldmax.android.fieldmaxv2.R;
import com.fieldmax.android.fieldmaxv2.activity.BaseActivity;
import com.fieldmax.android.fieldmaxv2.activity.CustomerListActivity;
import com.fieldmax.android.fieldmaxv2.activity.InboxActivity;
import com.fieldmax.android.fieldmaxv2.activity.ReportActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by suraj on 11/2/2015.
 */
public class BeatsActivity extends BaseActivity implements LocationSource.OnLocationChangedListener {
    private GoogleMap map;
    ArrayList<LatLng> mMarkerPoints;
    double mLatitude = 0;
    double mLongitude = 0;
    private Spinner mSpinnerBeat;
    String getDays;
    TextView description;
    private ArrayList<LatLng> latLngMon;
    private ArrayList<LatLng> latLngTue;
    private ArrayList<LatLng> latLngWed;
    private ArrayList<LatLng> latLngThu;
    private ArrayList<LatLng> latLngFri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beat);
        getSupportActionBar().hide();

        final Button getRoute = (Button) findViewById(R.id.getRoute);
        //If Network is not available
        if (!isNetworkStatusAvailable(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "internet not avialable", Toast.LENGTH_SHORT).show();
        }

        //If wifi is not available
        EnableWIFIIfPossible();
        //If Gps is not activated
        EnableGPSIfPossible();
        Button footerCustomerButton = (Button) findViewById(R.id.FooterButtonBeat);
        footerCustomerButton.setSelected(true);

        Button footerButton = (Button) findViewById(R.id.FooterButtonCustomer);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(BeatsActivity.this, CustomerListActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });


        footerButton = (Button) findViewById(R.id.FooterButtonInbox);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(BeatsActivity.this, InboxActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });


        footerButton = (Button) findViewById(R.id.FooterButtonHome);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(BeatsActivity.this, ReportActivity.class);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(launchIntent);
            }
        });


        //Maps Implementation

        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        if (status != ConnectionResult.SUCCESS) { // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        } else { // Google Play Services are available..


            // Initializing
            mMarkerPoints = new ArrayList<LatLng>();

            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

            // Enable MyLocation Button in the Map
            map.setMyLocationEnabled(true);

            // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            // Getting Current Location From GPS
            Location location = locationManager.getLastKnownLocation(provider);


            mSpinnerBeat = (Spinner) findViewById(R.id.spinnerBeatDays);


            mSpinnerBeat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent,
                                           View view, int pos, long id) {


                    getDays = parent.getItemAtPosition(pos).toString();

                    if(getDays.equals("BEAT DAYS")){
                        Toast.makeText(getApplicationContext(),"PLEASE SELECT THE BEAT DAY",Toast.LENGTH_LONG).show();
                    }

                    if (getDays.equals("MONDAY")) {
                        map.clear();
                        latLngMon = new ArrayList<LatLng>();
                        latLngMon.add(new LatLng(12.994720, 77.711288));
                        latLngMon.add(new LatLng(12.992127, 77.720816));
                        latLngMon.add(new LatLng(12.991082, 77.709529));
                        latLngMon.add(new LatLng(12.991291, 77.713778));


                        for (LatLng location : latLngMon) {
                            drawMultipleMarkers(location);


                        }


                    }
                    if (getDays.equals("TUESDAY")) {
                        map.clear();
                        latLngTue = new ArrayList<LatLng>();
                        latLngTue.add(new LatLng(12.99129, 77.713788));
                        latLngTue.add(new LatLng(12.9949904976, 77.7001522108));
                        latLngTue.add(new LatLng(12.99703001256, 77.6915004104));
                        latLngTue.add(new LatLng(12.9983775924, 77.6888745278));


                        for (LatLng location : latLngTue) {
                            drawMultipleMarkers(location);

                        }

                    }
                    if (getDays.equals("WEDNESDAY")) {
                        map.clear();
                        latLngWed = new ArrayList<LatLng>();
                        latLngWed.add(new LatLng(12.9995066, 77.715478353));
                        latLngWed.add(new LatLng(12.992127, 77.720816));
                        latLngWed.add(new LatLng(13.00502195, 77.7122670));
                        latLngWed.add(new LatLng(13.00693594, 77.7006635));


                        for (LatLng location : latLngWed) {
                            drawMultipleMarkers(location);

                        }
                    }
                    if (getDays.equals("THURSDAY")) {
                        map.clear();
                        latLngThu = new ArrayList<LatLng>();
                        latLngThu.add(new LatLng(12.994720, 77.711288));
                        latLngThu.add(new LatLng(12.992127, 77.720816));
                        latLngThu.add(new LatLng(12.991082, 77.709529));
                        latLngThu.add(new LatLng(12.991291, 77.713778));


                        for (LatLng location : latLngThu) {
                            drawMultipleMarkers(location);

                        }
                    }

                    if (getDays.equals("FRIDAY")) {
                        map.clear();
                        latLngFri = new ArrayList<LatLng>();
                        latLngFri.add(new LatLng(12.994720, 77.711288));
                        latLngFri.add(new LatLng(12.992127, 77.720816));
                        latLngFri.add(new LatLng(12.991082, 77.709529));
                        latLngFri.add(new LatLng(12.991291, 77.713778));


                        for (LatLng location : latLngFri) {
                            drawMultipleMarkers(location);

                        }
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });

           /* map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View view = getLayoutInflater().inflate(R.layout.layout_map_info_window, null);
                    TextView place = (TextView) view.findViewById(R.id.place);
                    TextView description = (TextView) view.findViewById(R.id.description);
                    LatLng latLng = marker.getPosition();
                    place.setText("Latitude:" + latLng.latitude + "Longitude:" + latLng.longitude);


                    // Returning the view containing InfoWindow contents
                    return view;

                }
            });
*/

            if (location != null) {
                onLocationChanged(location);

            }


            // Setting onclick event listener for the map
            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                @Override
                public void onMapClick(final LatLng point) {


                    // Already map contain destination location
                    if (mMarkerPoints.size() > 1) {
                        mMarkerPoints.clear();
                        FragmentManager fm = getSupportFragmentManager();


                        LatLng startPoint = new LatLng(mLatitude, mLongitude);
                        // draw the marker at the current position
                        drawMarker(startPoint);
                    }

                    // draws the marker at the currently touched location
                   // drawMarker(point);
                    getRoute.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            for (int j = 0; j < 4; j++) {


                                if (mMarkerPoints.size() >= 2) {


                                    LatLng origin = mMarkerPoints.get(0);
                                    if (getDays.equals("MONDAY")) {

                                        LatLng dest1 = latLngMon.get(j);
                                        // Getting URL to the Google Directions API
                                        String url = getDirectionsUrl(origin, dest1);
                                        DownloadTask downloadTask = new DownloadTask();
                                        // Start downloading json data from Google Directions API
                                        downloadTask.execute(url);

                                    }
                                    if (getDays.equals("TUESDAY")) {

                                        LatLng dest1 = latLngTue.get(j);
                                        // Getting URL to the Google Directions API
                                        String url = getDirectionsUrl(origin, dest1);
                                        DownloadTask downloadTask = new DownloadTask();
                                        // Start downloading json data from Google Directions API
                                        downloadTask.execute(url);

                                    }
                                    if (getDays.equals("WEDNESDAY")) {
                                        LatLng dest1 = latLngWed.get(j);
                                        // Getting URL to the Google Directions API
                                        String url = getDirectionsUrl(origin, dest1);
                                        DownloadTask downloadTask = new DownloadTask();
                                        // Start downloading json data from Google Directions API
                                        downloadTask.execute(url);
                                    }

                                    if (getDays.equals("THURSDAY")) {
                                        LatLng dest1 = latLngThu.get(j);
                                        // Getting URL to the Google Directions API
                                        String url = getDirectionsUrl(origin, dest1);
                                        DownloadTask downloadTask = new DownloadTask();
                                        // Start downloading json data from Google Directions API
                                        downloadTask.execute(url);
                                    }

                                    if (getDays.equals("FRIDAY")) {
                                        LatLng dest1 = latLngFri.get(j);
                                        // Getting URL to the Google Directions API
                                        String url = getDirectionsUrl(origin, dest1);
                                        DownloadTask downloadTask = new DownloadTask();
                                        // Start downloading json data from Google Directions API
                                        downloadTask.execute(url);

                                    }
                                }
                            }

                        }
                    });


                }
            });


        }
    }


    public static boolean isNetworkStatusAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if (netInfos != null)
                if (netInfos.isConnected())
                    return true;
        }
        return false;
    }

    private void EnableGPSIfPossible() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            buildAlertMessageNoWifi();
        }
    }

    private void EnableWIFIIfPossible() {

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!mWifi.isConnected()) {
            buildAlertMessageNoWifi();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(BeatsActivity.this);
        builder.setMessage("Yout GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void buildAlertMessageNoWifi() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(BeatsActivity.this);
        builder.setMessage("Yout wifi seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void drawMultipleMarkers(LatLng point) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        map.addMarker(markerOptions);
    }


    private void drawMarker(LatLng point) {
        mMarkerPoints.add(point);
        // Creating MarkerOptions
        MarkerOptions options = new MarkerOptions();
        // Setting the position of the marker
        options.position(point);

        /**
         * For the start location, the color of marker is GREEN and
         * for the end location, the color of marker is RED.
         */
        if (mMarkerPoints.size() == 1) {
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        } else if (mMarkerPoints.size()>= 2) {
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        }
        // Add new marker to the Google Map Android API V2
        map.addMarker(options);

    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {


        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception downloading", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    /**
     * A class to download data from Google Directions URL
     */
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service


                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }


    /**
     * A class to parse the Google Directions in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            // Traversing through all the routes
            String duration = "";
            String distance = "";
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();


                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);
                    if (j == 0) {    // Get distance from the list
                        distance = (String) point.get("distance");
                        continue;
                    } else if (j == 1) { // Get duration from the list
                        duration = (String) point.get("duration");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);
                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(30);
                lineOptions.color(Color.BLUE);
            }


            // Drawing polyline in the Google Map for the i-th route
            map.addPolyline(lineOptions);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // Draw the marker, if destination location is not set
        if (mMarkerPoints.size() < 2) {

            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();
            LatLng point = new LatLng(mLatitude, mLongitude);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(point)      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(0)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            map.getUiSettings().setZoomGesturesEnabled(true);
            map.getUiSettings().setAllGesturesEnabled(true);
            map.isBuildingsEnabled();
            drawMarker(point);
        }
    }


}

