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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListRide extends ListFragment
{
	public ArrayList<String>rideArrayList;
	private ArrayAdapter<String> rideArrayAdapter;
	private ArrayList<String> saida;
	private ArrayList<String> destino;
	private String rotas;
	public static final String ROTAS = "rotas";
	private ArrayList<String> listRotas;
	private NetworkOperations netOp = NetworkOperations.getNetworkOperations(getActivity());
	public static ListRide newInstance(ArrayList<String> carona)
	{
		 ListRide listRide = new ListRide();
		 Bundle argumentsBundle = new Bundle();
		 argumentsBundle.putStringArrayList(ROTAS,carona);
		 listRide.setArguments(argumentsBundle);
		 return listRide;
		
	}
	@Override
	public void onCreate(Bundle argumentsBundle)
	{
		super.onCreate(argumentsBundle);
		listRotas = getArguments().getStringArrayList(ROTAS);
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
		rideArrayList = new ArrayList<String>();
		setListAdapter(new RideArrayAdapter<String>(getActivity(),R.layout.ride_list_item,
				rideArrayList));
		ListView thisListView = getListView();
		rideArrayAdapter = (ArrayAdapter<String>)getListAdapter();
		thisListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		thisListView.setBackgroundColor(Color.WHITE);
		if(listRotas!= null)
		{
			addDefaultRide(listRotas);
		}
	}
	
	private void addDefaultRide(ArrayList <String>rotas)
	{
	
		for(int i = 0; i < rotas.size(); i++)
		{
			addRide(rotas.get(i));
			
		}
	}
	public void addRide(String saida)
	{
		rideArrayAdapter.add(saida);
				
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
	/*public String [] splitRoute(String rotas)
	{
		String []listRide;
		return listRide = rotas.split("#");
		
	}
	public ArrayList<String>getSaida(String [] rides)
	{
		ArrayList<String> saidas = new ArrayList<String>();
		
		for(int i = 0; i < rides.length; i++)
		{
			if(i % 2 == 0)
			{
				saidas.add(rides[i]);
			}
		}
		return saidas;
	}
	public ArrayList<String>getDestino(String [] rides)
	{
		ArrayList<String> destinos = new ArrayList<String>();
		for(int i = 0; i < rides.length; i++)
		{
			if(i % 2 != 0)
			{
				destinos.add(rides[i]);
			}
		}
		return destinos;
		
	}
		
	public class DownloadRide extends AsyncTask<Void,Void,Boolean>{
    	
    	final ProgressDialog dialog = new ProgressDialog(getActivity());
    	
    	Context mcontext;
    	
    	DownloadRide(Context context){
    		mcontext = context;
    	}
    	
    	@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.setMessage("Loading...");
		    dialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			
			rotas = netOp.downloadRides();
			//addDefaultRide(splitRoute(rotas));
			//addRide();
			if(rotas == null)
				return false;
			
			return true;
						
		}
		
		 protected void onPostExecute(Boolean respuesta) {
			 if (respuesta == true)
			 {
				 //getActivity().finish();
				 
				 dialog.dismiss();
				 
				 //Toast.makeText(getActivity(), "User successfully registered",  Toast.LENGTH_SHORT).show();
		        						 
				 //mcontext.startActivity(j);
				 
			 }
			 else{
				 
				// Toast.makeText(MainActivity.this, "Registration failure",  Toast.LENGTH_SHORT).show();
				 
				 dialog.dismiss();
			 }
					 
		}
    
    }*/
	

}
