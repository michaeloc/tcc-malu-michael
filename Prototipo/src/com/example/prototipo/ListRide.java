/*
 * Criado por Michael e Malu
 * Exibe a lista de caronas
 * 
 */

package com.example.prototipo;

import java.util.ArrayList;
import java.util.List;

import com.example.prototipo.network.NetworkOperations;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListRide extends ListFragment
{
	private LayoutInflater inflater;
	public ArrayList<String>rideArrayList;
	private ArrayAdapter<String> rideArrayAdapter;
	public static final String SAIDA = "saida";
	public static final String DESTINO = "destino";
	static ArrayList<String> listRotasSaida;
	private ArrayList<String> listRotasDestino;
	//TextView saida,destino;	
	public static ListRide newInstance(ArrayList<String> rotaSaida,ArrayList<String>rotaDestino)
	{
		 ListRide listRide = new ListRide();
		 Bundle argumentsBundle = new Bundle();
		 argumentsBundle.putStringArrayList(SAIDA,rotaSaida);
		 argumentsBundle.putStringArrayList(DESTINO,rotaDestino);
		 listRide.setArguments(argumentsBundle);
		 return listRide;
		
	}
	@Override
	public void onCreate(Bundle argumentsBundle)
	{
		super.onCreate(argumentsBundle);
		listRotasSaida = getArguments().getStringArrayList(SAIDA);
		listRotasDestino = getArguments().getStringArrayList(DESTINO);
	}
	@Override 
	public void onSaveInstanceState(Bundle savedInstanceStateBundle)
	{
		super.onSaveInstanceState(savedInstanceStateBundle);
		
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceStateBundle)
	{
		super.onActivityCreated(savedInstanceStateBundle);
		
		if(listRotasSaida!= null)
		{
			setListAdapter(new RideArrayAdapter<String>(getActivity(),R.layout.list,
					listRotasSaida));
			
		}else{
			rideArrayList = new ArrayList<String>();
			setListAdapter(new RideArrayAdapter<String>(getActivity(),R.layout.list,
					rideArrayList));
		}
		ListView thisListView = getListView();
		rideArrayAdapter = (ArrayAdapter<String>)getListAdapter();
		thisListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		thisListView.setBackgroundColor(Color.WHITE);
			
	}
	//Deprecated...
	/*private void addDefaultRide(ArrayList <String>rotasSaida,ArrayList <String>rotasDestino)
	{
	
		for(int i = 0; i < rotasSaida.size(); i++)
		{
			addRide(rotasSaida.get(i),rotasDestino.get(i));
			
		}
	}
	public void addRide(String saida, String destino)
	{
		rideArrayAdapter.add(saida+"n/"+destino);
				
	}*/
	private class RideArrayAdapter<T> extends ArrayAdapter<String>
	{
		
		private Context context;
		
		public RideArrayAdapter(Context context, int textViewResourceId, ArrayList<String> objects)
		{
			super(context, textViewResourceId, objects);
			this.context = context;
			
		}
		
		@Override
		public View getView(int position, View convertView,ViewGroup parent){
			inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.list, parent,false);
			if(listRotasDestino != null)
			{
				 TextView saida = (TextView)view.findViewById(R.id.saidaTextView);
				 saida.setText(listRotasSaida.get(position));
				 TextView destino = (TextView)view.findViewById(R.id.destinoTextView);
				 destino.setText(listRotasDestino.get(position));
			}
			return view;
			
		}
	
	}
}
