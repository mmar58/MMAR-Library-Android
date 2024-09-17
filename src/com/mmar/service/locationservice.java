package com.mmar.service;
import android.location.Location;
import android.location.LocationManager;
import java.io.File;
/*permissions
 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
 <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
 <uses-permission android:name="android.permission.INTERNET" />
 */

public class locationservice extends myService
{
	boolean isGPSEnabled = false;

	// flag for network status
	boolean isNetworkEnabled = false;

	// flag for GPS status
	boolean canGetLocation = false;

	Location location; // location
	double latitude; // latitude
	double longitude; // longitude

	// Declaring a Location Manager
	protected LocationManager locationManager;
	@Override
	public void onCreate()
	{
		// TODO: Implement this method
		super.onCreate();
	}
	public Location getLocation() {
		try {
			locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE);

			// getting GPS status
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			isNetworkEnabled = locationManager
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			location = locationManager
				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {
				this.canGetLocation = true;
				// First get location from Network Provider
				if (isNetworkEnabled) {
					/*locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER,0,0,this);
					locationManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER,
						MIN_TIME_BW_UPDATES,
						MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

					*/
					if (locationManager != null) {
						location = locationManager
							.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

						if (location != null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}

				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						/*locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
*/
		
						if (locationManager != null) {
							location = locationManager
								.getLastKnownLocation(LocationManager.GPS_PROVIDER);

							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}

	/**
	 * Stop using GPS listener
	 * Calling this function will stop using GPS in your app
	 * */

	public void stopUsingGPS(){

	}

	/**
	 * Function to get latitude
	 * */

	public double getLatitude(){
		if(location != null){
			latitude = location.getLatitude();
		}

		// return latitude
		return latitude;
	}

	/**
	 * Function to get longitude
	 * */

	public double getLongitude(){
		if(location != null){
			longitude = location.getLongitude();
		}

		// return longitude
		return longitude;
	}

	/**
	 * Function to check GPS/wifi enabled
	 * @return boolean
	 * */

	public boolean canGetLocation() {
		return this.canGetLocation;
	}
	@Override
	public void onUpdate()
	{
		// TODO: Implement this method
		super.onUpdate();
		//refreh location
		getLocation();
        ongetlocation(getLatitude(),getLongitude());
	}
    public void ongetlocation(double lattitude,double longtitude){
		
	}
}
