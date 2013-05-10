package com.example.prototipo;

import com.example.prototipo.AddRideDialogFragment.DialogFinishedListener;
import com.example.prototipo.MainActivity.Conectar;

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
import android.widget.Toast;

public class RideActivity extends Activity implements DialogFinishedListener {
	private int currentTab;
	private ListRide listRideFragments;
	private static final int CURRENT_CONDITIONS_TAB = 1;
	private static final String CURRENT_TAB_KEY = "current_tab";
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
	//Ainda não defini a inserção de rotas
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
		rideBar.addTab(allRide);
		
		rideBar.selectTab(allRide);
		
		currentTab = CURRENT_CONDITIONS_TAB;
		
	}
	private void selectTab(int position)
	{
		currentTab =  position;
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		if(listRideFragments==null)
		{
			listRideFragments = ListRide.newInstance();
			ft.replace(R.id.replace, listRideFragments);
			
		}else{
			
			if(currentTab == CURRENT_CONDITIONS_TAB )
			{
				listRideFragments = ListRide.newInstance();
				ft.replace(R.id.replace, listRideFragments);
							
			}else{
				
				Profile p = Profile.newInstance();
				ft.replace(R.id.replace, p);
				
			}
		}
		ft.commit();
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
	
	

}
