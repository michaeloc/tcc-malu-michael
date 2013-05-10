package com.example.prototipo;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListRide extends ListFragment
{
	public ArrayList<String>rideArrayList;
	private ArrayAdapter<String> rideArrayAdapter;
	public static ListRide newInstance()
	{
		ListRide listRide = new ListRide();
		//listRide.addDefaultRide();
		return listRide;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceStateBundle)
	{
		super.onActivityCreated(savedInstanceStateBundle);
		rideArrayList = new ArrayList<String>();
		setListAdapter(new RideArrayAdapter<String>(getActivity(),R.layout.ride_list_item,
				rideArrayList));
		ListView thisListView = getListView();
		rideArrayAdapter = (ArrayAdapter<String>)getListAdapter();
		thisListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		thisListView.setBackgroundColor(Color.WHITE);
		addDefaultRide();
	}
	private void addDefaultRide()
	{
		String [] defaultRideSaida = getResources().getStringArray(R.array.default_ride_saidas);
		String [] defaultRideDestino = getResources().getStringArray(R.array.default_ride_destinos);
		
		for(int i = 0; i < defaultRideSaida.length; i++)
		{
			addRide(defaultRideSaida[i],defaultRideDestino[i]);
			
		}
	}
	public void addRide(String saida,String destino)
	{
		rideArrayAdapter.add(destino);
				
	}
	private class RideArrayAdapter<T> extends ArrayAdapter<String>
	{
		
		private Context context;
		
		public RideArrayAdapter(Context context, int textViewResourceId, List<String> objects)
		{
			super(context, textViewResourceId,objects);
			this.context = context;
		}
	}
	public View getView(int position, View convertView,ViewGroup parent){
		TextView listItemTextView = (TextView)
				super.getView();
		return listItemTextView;
	}
	

}
