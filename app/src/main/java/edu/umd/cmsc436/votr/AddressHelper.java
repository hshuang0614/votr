package edu.umd.cmsc436.votr;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AddressHelper {
    public static String addressFromLatLng(Context context, float latitude, float longitude) throws Exception {
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());

            // Here 1 signifies the max amount of addresses we want returned.
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses == null || addresses.isEmpty()) {
                throw new Exception(getLatLonError(latitude, longitude));
            }

            return addressToString(addresses.get(0));
        } catch (IOException e) {
            throw new Exception(getLatLonError(latitude, longitude));
        }
    }

    public static LatLng latLngFromAddress(Context context, String strAddress) {
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses;
        LatLng location;

        try {
            addresses = geocoder.getFromLocationName(strAddress, 1 /* max results */);
            if (addresses == null || addresses.isEmpty()) {
                return null;
            }

            Address address = addresses.get(0);

            location = new LatLng(address.getLatitude(), address.getLongitude());
        } catch (Exception ex) {
            return null;
        }

        return location;
    }

    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    public static String addressToString(Address address) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
            if (i > 0) {
                sb.append(", ");
            }

            String line = address.getAddressLine(i);
            if (line != null) {
                sb.append(line);
            }
        }

        // City
        sb.append(", " + address.getLocality());

        // State
        sb.append(", " + address.getAdminArea());

        // Zipcode
        sb.append(", " + address.getPostalCode());

        return sb.toString();
    }

    private static String getLatLonError(float latitude, float longitude) {
        return "Could not find an address at coordinates: (" + latitude + ", " + longitude + ")";
    }
}
