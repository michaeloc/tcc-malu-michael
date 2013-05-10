package com.example.prototipo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends Fragment
{
	private TextView userTextView;
	private ImageView userImage;
	private Context context;
	
	public static Profile newInstance()
	{
		Profile newProfileFragment = new Profile();
		return newProfileFragment;
	}
	@Override
	public void onCreate(Bundle argumentsBundle)
	{
		super.onCreate(argumentsBundle);
		
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.profile,null);
		userTextView = (TextView)rootView.findViewById(R.id.userTextView);
		userImage = (ImageView)rootView.findViewById(R.id.userImageView);
		context = rootView.getContext();
		return rootView;
	}
	
	
	
}
