package com.realactionsoft.android.example.apis;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * @author Oscar Salguero Guandique
 *
 */
public class CustomOverlay extends ItemizedOverlay {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
	
	/**
	 * Default Marker es la imagen que usaremos por defecto
	 * @param defaultMarker
	 */
	public CustomOverlay(Drawable defaultMarker) {
		super(boundCenter(defaultMarker));
	}
	
	//Agregando una capa
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
	
	//Creando una capa
	@Override
	protected OverlayItem createItem(int i) {
	  return mOverlays.get(i);
	}

	//Esto nos dice cuantas capas hay en total
	@Override
	public int size() {
		return mOverlays.size();
	}
	
	public CustomOverlay(Drawable defaultMarker, Context context) {
		  super(boundCenter(defaultMarker));
		  mContext = context;
	}
	
	//Manejando el evento Tap
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = mOverlays.get(index);
	  //Mostrando un Toast (dialogo sencillo) cada vez que se ha hecho tap en algun pin
	  Toast.makeText(mContext, item.getSnippet() , Toast.LENGTH_SHORT).show();
	  return true;
	}
	
}
