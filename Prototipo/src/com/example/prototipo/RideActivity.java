package com.example.prototipo;

import java.util.ArrayList;

import com.example.prototipo.AddRideDialogFragment.DialogFinishedListener;
import com.example.prototipo.MainActivity.Conectar;
import com.example.prototipo.network.NetworkOperations;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SlidingDrawer;
import android.widget.Toast;

public class RideActivity extends Activity implements DialogFinishedListener {
	private int currentTab;
	private ListRide listRideFragments;
	private static final int CURRENT_CONDITIONS_TAB = 1;
	private static final String CURRENT_TAB_KEY = "current_tab";
	private String rotas;
	private ArrayList<String> splitRotas;
	private NetworkOperations netOp = NetworkOperations.getNetworkOperations(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ride);
		listRideFragments = (ListRide)getFragmentManager().findFragmentById(R.id.replace);
		setupTabs();
		
	}
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.ride, menu);
		
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(item.getItemId() == R.id.add_ride_item){
			showAddRideDialog();
			return true;
		}
		return false;
	} 
	
	@Override
	public void onResume()
	{
		super.onResume();
		download();
		listRideFragments = ListRide.newInstance(splitRotas);
		selectFragment(listRideFragments);
	}
	private void selectFragment(Fragment fragment)
	{
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(R.id.replace, fragment);
		ft.commit();
	}
	@Override
	public void onSaveInstanceState(Bundle savedInstanceStateBundle){
		savedInstanceStateBundle.putInt("CURRENT_TAB_KEY", currentTab);
		super.onSaveInstanceState(savedInstanceStateBundle);
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceStadeBundle){
		super.onRestoreInstanceState(savedInstanceStadeBundle);
		currentTab = savedInstanceStadeBundle.getInt(CURRENT_TAB_KEY);
	}
	
	private void showAddRideDialog()
	{
		AddRideDialogFragment newAddRideDialogFragment = new AddRideDialogFragment();
		FragmentManager thisFragmentManager = getFragmentManager();
		FragmentTransaction addRideFragmentTransaction = thisFragmentManager.beginTransaction();
		newAddRideDialogFragment.show(addRideFragmentTransaction, "");
	}
	
	private void setupTabs()
	{
		ActionBar rideBar = getActionBar();
		
		rideBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		Tab profile = rideBar.newTab();
		
		profile.setText(getResources().getString(R.string.profile));
		profile.setTabListener(rideTabListener);
		rideBar.addTab(profile);
		
		Tab allRide = rideBar.newTab();
		
		allRide.setText(getResources().getString(R.string.allRide));
		allRide.setTabListener(rideTabListener);
		rideBar.addTab(allRide,true);
					
		currentTab = CURRENT_CONDITIONS_TAB;
				
	}
	private void download(){
		DownloadRide download = new DownloadRide(this);
		download.execute();
	}
	private void selectTab(int position)
	{
		currentTab =  position;
		download();
		if(currentTab == CURRENT_CONDITIONS_TAB )
		{
			listRideFragments = ListRide.newInstance(splitRotas);
			selectFragment(listRideFragments);
							
		}else{

			Profile profile = Profile.newInstance();
			selectFragment(profile);
		}
	}
	
	TabListener rideTabListener = new TabListener()
	{

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			selectTab(tab.getPosition());
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
		}
		
		
	};
	@Override
	public void onDialogFinished(String destino) {
	
		
	}
	
	public String [] splitRoute(String rotas)
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
    	
    	final ProgressDialog dialog = new ProgressDialog(RideActivity.this);
    	
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
			splitRotas=getSaida(splitRoute(rotas));
			//addRide();
			System.out.println(rotas);
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
    
    }
	
}
