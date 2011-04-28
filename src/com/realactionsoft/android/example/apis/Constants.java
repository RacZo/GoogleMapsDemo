package com.realactionsoft.android.example.apis;

import com.google.android.maps.GeoPoint;

/**
 * @author Oscar Salguero Guandique
 *
 */
public class Constants {

	public static final GeoPoint CENTER = 
		new GeoPoint(
				new Double(13.691543953847518 * 1E6).intValue(), 
				new Double(-89.18870687484741 * 1E6).intValue()
				);//El Salvador
	
	public static final GeoPoint [] GEO_POINTS = new GeoPoint []{
		new GeoPoint(
				new Double(13.691543953848519 * 1E6).intValue(), 
				new Double(-89.18870687482742 * 1E6).intValue()
				),
		new GeoPoint(
				new Double(13.691543953847117 * 1E6).intValue(), 
				new Double(-89.18870687484140 * 1E6).intValue()
				)
		
	};
	
}
