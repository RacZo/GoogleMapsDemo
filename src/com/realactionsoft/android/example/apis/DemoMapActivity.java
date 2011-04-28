package com.realactionsoft.android.example.apis;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

/**
 * @author Oscar Salguero Guandique
 *
 */
public class DemoMapActivity extends MapActivity {
    
	private MapView mapView;
	private MapController mapController;
	private List<Overlay> mapOverlays;
	private CustomOverlay itemizedOverlay;
	private CustomOverlay myItemizedOverlay;
	private LocationManager mlocManager;
	private LocationListener mlocListener;
	private int zoomLevel = 4;//3 o 4 son los mas adecuados en este caso
	private static final String LOG_TAG = "DemoMapActivity";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		//Default GeoPoint
        //Latitud y Longitud de El Salvador (Es nuestro GeoPoint por defecto)
		GeoPoint point = Constants.CENTER;
		
        //MapView
		//Este mapview esta en nuestro layout en XML
        mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);//Esto hace aparecer los controles de Zoom
		mapView.setSatellite(false);//Vista modo satelite
		mapView.setStreetView(false);//Habilita Street View (si est‡ dispoible)
		mapView.setTraffic(false);//Muestra informacion sobre el tr‡fico en esa area (si est‡ disponible)
		mapView.invalidate();
		
		//MapController
		mapController = mapView.getController();
		mapController.setZoom(zoomLevel);//Nivel de Zoom deseado
		mapController.setCenter(point);//Centra el mapa en el punto deseado
		mapController.animateTo(point);//Causa una animaci—n llevando el Mapa al centro
		
		/* Usando el LocationManager para obtener la ubicacion del GPS */
		mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		mlocListener = new CustomLocationListener();
		//mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
		
		//Esta es la ultima ubiaci—n conocida del Usuario
		Location userLocation = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if(userLocation==null){
			userLocation = mlocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		
		//Colecci—n de Map Layers
		mapOverlays = mapView.getOverlays();

		//Muestra un Android como la ubicaci—n actual del usuario en el Mapa
		if(userLocation!=null){
			point = new GeoPoint(
					new Double(userLocation.getLatitude() * 1E6).intValue()  ,
					new Double(userLocation.getLongitude() * 1E6).intValue()
					);
		}

		//Preparando las capas a agregar
		myItemizedOverlay = new CustomOverlay(this.getResources().getDrawable(R.drawable.blue_pin), this.getBaseContext());
		myItemizedOverlay.addOverlay(new OverlayItem(point, "", ""));
		mapOverlays.add(myItemizedOverlay);
		mapView.invalidate();
		
		//Add all the countries Flags on the Map with its name and location with more overlays
		for(int i = 0; i<Constants.GEO_POINTS.length-1;i++){
			
			itemizedOverlay = new CustomOverlay(this.getResources().getDrawable(R.drawable.red_pin), this.getBaseContext());
			
			itemizedOverlay.addOverlay(new OverlayItem(Constants.GEO_POINTS[i], "", ""));
			Log.i(LOG_TAG, "Agregando capa para: " + "Lat: " + Constants.GEO_POINTS[i].getLatitudeE6() + " " + "Long: " + Constants.GEO_POINTS[i].getLongitudeE6() );
			
			//Agregando la capa al Mapa
			mapOverlays.add(itemizedOverlay);
			mapView.invalidate();
		}
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public class CustomLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location loc){
			String text = "Ubicaci—n actual:" + " Lat = " + loc.getLatitude() + ", Long = " + loc.getLongitude();
			Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			Toast.makeText( getApplicationContext(), "GPS offline", Toast.LENGTH_SHORT ).show();
		}

		@Override
		public void onProviderEnabled(String provider){
			Toast.makeText( getApplicationContext(), "GPS online", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras){
			
		}		
		
	}
	
}