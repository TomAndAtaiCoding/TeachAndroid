package com.example.teachapp.components;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.ImageReader;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teachapp.R;

import java.io.IOException;
import java.security.Permission;
import java.util.List;
import java.util.Locale;

public class ComponentActivity extends AppCompatActivity {

    private Button m_buttonGPS;
    private Button m_buttonCamera;
    private Button m_buttonMedia;
    private TextView m_textAddress;
    private ImageView m_imageCamera;
    private ImageView m_imageMedia;

    // Whenever we request a permission, we use an Access Code to later identify
    // the specific request.
    private static final int LOCATION_ACCESS_CODE = 0;
    private static final int CAMERA_ACCESS_CODE = 1;
    private static final int STORAGE_ACCESS_CODE = 2;

    // As we'll see later, to access the camera and gallery we'll use an "Activity with Results"
    // to distinguish between the different activity when getting results, we'll use this code
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int GALLERY_REQUEST_CODE = 200;

    // Location objects are useful to store pure latitude-longitude locations, and they are the
    // objects you'll initially get when using the built-in location features.
    private Location m_locGPS;
    private Location m_locNetwork;
    private Location m_locFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_components);


        m_buttonGPS = findViewById(R.id.button_comp_gps);
        m_buttonCamera = findViewById(R.id.button_comp_camera_test);
        m_buttonMedia = findViewById(R.id.button_comp_media);

        m_textAddress = findViewById(R.id.text_comp_gps);
        m_imageCamera = findViewById(R.id.image_comp_camera);
        m_imageMedia = findViewById(R.id.image_comp_media);

        m_buttonGPS.setOnClickListener(v -> {

            // Check for location permissions.
            // Other than here, you should also write your required permissions in the app's manifest.
            if (
                // Most precise location, using network and GPS
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                // Less precise, allow only using networks (e.g WiFi towers)
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

                // If we don't have them, request them.
                ActivityCompat.requestPermissions(
                        this,
                        new String[] {
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_NETWORK_STATE
                        },
                        LOCATION_ACCESS_CODE
                );
            } else {
                // If we do set the address (see method below)
                setAddress();
            }



        });


        m_buttonCamera.setOnClickListener((v) -> {
            // Again, first we check for permissions and request them if we don't already have them.
            // Always remember to also put your required permissions in the Android Manifest!
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[] {Manifest.permission.CAMERA},
                        CAMERA_ACCESS_CODE
                );
            } else {
                // If we do, we can use a special Intent with a String "Action" parameter.
                // Many actions are available throughout android, but what we want to use
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                // The method startActivityWithResult allows us to get the result of an action
                // defined in an intent. These are all given in the onActivityResult method,
                // which is why we give a request code (the second parameter) to differentiate
                // between them.
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            }

        });

        m_buttonMedia.setOnClickListener((v) -> {
            // Same thing - request for permissions if we don't have them.
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        STORAGE_ACCESS_CODE);
            } else {
                // Like the camera, a special intent lets the user pick from storage
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                // You can use the intent's setType method to filter for specific file types.
                // The parameter takes a string of a "Mime Type", which is a special standardized
                // way to communicate file types. Mime types are separated into 5 classes - audio,
                // video, image, text and application. To specify beyond that, there is a slash /
                // followed by an identifier to filter for specific types (e.g "text/js" for
                // javascript files). While the identifier after the text often matches the file
                // extension, it varies and you should just google what you need. Finally, you can
                // use an asterisk * as a "wildcard", matching all types in a specific class, like
                // what we do here (followed by an asterisk)
                photoPicker.setType("image/*");

                // Now, like in the camera, we use startActivityWithResult (you may look at the
                // camera button code for more details on that).
                startActivityForResult(photoPicker, GALLERY_REQUEST_CODE);
            }
        });


    }


    private void setAddress() {

        // We can use  a LocationManager object to use the phone's location services. It
        // essentially serves as an abstract layer, allowing us to get location directly rather
        // than dealing with GPS or Cell Tower signals.
        //
        // To get the phone's location manager, we can use the built-in method getSystemService,
        // which can also provide many other low-software and hardware services (e.g audio,
        // battery, alarm, et cetera), as listed in the Context class.
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // If no permissions are given for any kind of locatiom, exit the method (failure)
        if (
                // Most precise location, using network and GPS
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                // Less precise, allow only using networks (e.g WiFi towers)
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        // When accessing phone hardware we usually need to surround the code with try-catch blocks,
        // so we can handle errors from lack of permissions or access problems.
        try {

            // To get the closest approximation of the user's location, we can simply use the
            // getLastKnownLocation method in the location managed, and specify the source
            // ("provider") we would like to get the location from (GPS or network).
            m_locGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            m_locNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        } catch (Exception e) {
            // More complex apps might handle different exceptions individually, and provide
            // more user-friendly and informative solutions. Since this is a simple app, we simply
            // print the error to console (so developers can see it), and go on with our day.
            // Notice if one of location caused an error, it will remain null, as we couldn't manage
            // to put the value in. This will be useful for checks in a moment.
            e.printStackTrace();
        }

        // If location was available, use it to set our final location parameters.
        double m_latitude;
        double m_longitude;

        if (m_locGPS != null) {
            m_locFinal = m_locGPS;
            m_latitude = m_locFinal.getLatitude();
            m_longitude = m_locFinal.getLongitude();
        }

        // If it wasn't and only network was available, and the network was, use that
        else if (m_locNetwork != null) {
            m_locFinal = m_locNetwork;
            m_latitude = m_locFinal.getLatitude();
            m_longitude = m_locFinal.getLongitude();
        }

        // Finally, if no provider was available, just use default values.
        else {
            m_latitude = 0.0;
            m_longitude = 0.0;
        }



        // Here, we use the location found to determine the user's current address.
        // This also requires a try-catch block, since it requires an Internet connection the user
        // might not have at the moment.
        try {
            // The Geocoder class allows the conversion between human readable locations (addresses,
            // cities, countries), into the more machine readable latitude and longitude.
            // other than the usual context (what activity are we doing this from), the constructor
            // also requires a locale (a region) to use the information of. We can get the locale
            // set by the user with Local.getDefault().
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            // We can give the longitude and altitude to the geocoder's getFromLocation to recieve
            // the matching list of human readable descriptions (e.g addresses, shop names). We can
            // limit the results with the final parameter, to recieve just the most relevant ones.
            List<Address> addresses = geocoder.getFromLocation(m_latitude, m_longitude, 1);
            // Finally, if all worked well, we can use the address object to write the user's
            // address and country
            if (addresses != null && addresses.size() > 0) {
                String userCountry = addresses.get(0).getCountryName();
                String userAddress = addresses.get(0).getAddressLine(0);
                m_textAddress.setText(userCountry + ", " + userAddress);
            } else {
                m_textAddress.setText(R.string.text_pc_location_error_text);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Whenever an activity from an startActivityWithResult is finished, this method is
    // ran, with the request code that was given and a result code indicating success
    // or failure, along with an Intent to preserve relevant data.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // The super method here MUST be ran, to handle lower requests.
        super.onActivityResult(requestCode, resultCode, data);
        
        // We can compare the request code to each of the codes we used, to find out which request
        // it is.
        // For each request, will check the result code against the built in constant RESULT_OK (-1),
        // to make sure it was successful. The other options are RESULT_CANCELED (0), and any special
        // result defined in more complex activities.
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    // We can use the class BitMap to store photos - and the Intent action
                    // IMAGE_CAPTURE indeed keeps the taken photo as a BitMap object, under the
                    // key "data".
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    // Finally, we can set the content of ImageView with the method setImageBitMap.
                    m_imageCamera.setImageBitmap(photo);
                }
                break;
            case GALLERY_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    // Note that since this action dealt with a filesystem which could raise many
                    // kinds of errors (in reading or finding the relevant file), we also need to
                    // surround this code with a try-catch block.
                    try {
                        // Unlike the IMAGE_CAPTURE intent, the ACTION_PICK intent does not store
                        // the file directly as an extra field, as it can be used to pick many types
                        // of files which would need to be under different classes of objects.
                        // Instead, it simply maintain a URI (Uniform Resource Identifier) under the
                        // getData(). This identifier can then be used to get the picked file.
                        // Here, we use the method getBitMap under MediaStore.Images.Media to convert
                        // the URI object into a Bitmap image.
                        // Note also the first parameter of this method, specifying a "ContentResolver"
                        // to do the conversion. You can get the relevant object by invoking the
                        // built-in method getContentResolver.
                        Bitmap photo =
                                MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                        m_imageMedia.setImageBitmap(photo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }

    // To resume activity immediately upon being given the relevant permission, we can use
    // the onRequestPermissionResult method to get the user response to each request.
    // To check which request this was, we can use the request code which we put as a parameter in
    // the initial
    // request. Then we can use grantResults to determine weather each of the request (remember we
    // can send multiple requests in batch) was successful (PERMISSION_GRANTED=0) or not (PERMISSION_DENIED=-1)
    // If we don't care about the specific request and instead only care about the permissions
    // requested, we can use the permissions parameter to check that.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_ACCESS_CODE:
                // If any of the permissions were granted, utilize them to set the address
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                       || grantResults[1] == PackageManager.PERMISSION_GRANTED
                       || grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                    setAddress();
                }
                break;
            case CAMERA_ACCESS_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                }
                break;
            case STORAGE_ACCESS_CODE:
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                startActivityForResult(photoPicker, GALLERY_REQUEST_CODE);
                break;
        }
    }
}
