package com.example.prototipo;

import com.example.prototipo.network.NetworkOperations;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText loginEditText,passwordEditText;
	
	Button loginButton;
	
	CheckBox lembrar;
	
	String username, password;
	
	private NetworkOperations netOp = NetworkOperations.getNetworkOperations(this);
	
	public static Intent j;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		loginEditText = (EditText)findViewById(R.id.loginEditText);
		passwordEditText = (EditText)findViewById(R.id.passwordEditText);
		lembrar = (CheckBox)findViewById(R.id.sotoreCheckBox);
		loginButton = (Button)findViewById(R.id.loginButton);
		//SharedPreferece guarda dados que podem ser compartilhados ele pode ser moficado com get SharedPreferences.Editor 
		SharedPreferences sharedPreferences = this.getSharedPreferences("Main",MODE_PRIVATE);
		
        loginEditText.setText(sharedPreferences.getString("username", ""));

        if(sharedPreferences.getBoolean("remember", false))
            passwordEditText.setText(sharedPreferences.getString("password", ""));
        else
        	passwordEditText.setText("");

        lembrar.setChecked(sharedPreferences.getBoolean("remember", false));
	}
	
	public void enter(View view)
	{
		username = loginEditText.getText().toString();
		
        password = passwordEditText.getText().toString();
        
        Editor editor = (Editor) this.getSharedPreferences("Genx",Context.MODE_PRIVATE).edit();
        //Modificando SharedPreferences
    	editor.putString("username", username);
    	
    	if(lembrar.isChecked()){
        	editor.putString("password",password);
        }
        else
        	editor.putString("password","");
        
        editor.putBoolean("remember", lembrar.isChecked());
        
    	editor.commit();
    	
    	createNewActivity();
		
	}
	public void createNewActivity()
	{
		Conectar loggin = new Conectar(this);
		
    	j = new Intent(this, RideActivity.class);
    	
    	loggin.execute(username);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	 public class Conectar extends AsyncTask<String, Void, Boolean>{
	    	
	    	final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
	    	
	    	Context mcontext;
	    	
	    	Conectar(Context context){
	    		mcontext = context;
	    	}
	    	
	    	@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog.setMessage("Loading...");
			    dialog.show();
			}

			@Override
			protected Boolean doInBackground(String... params) {
				
				if(netOp.doLogin(username, password)){
		        	return true;
		        }
		        else{      	
		        	
		        	return false;
		        }
				
			}
			
			 protected void onPostExecute(Boolean respuesta) {
				 if (respuesta == true)
				 {
					 finish();
					 
					 dialog.dismiss();
					 
					 Toast.makeText(MainActivity.this, "User successfully registered",  Toast.LENGTH_SHORT).show();
			        						 
					 mcontext.startActivity(j);
					 
				 }
				 else{
					 
					 Toast.makeText(MainActivity.this, "Registration failure",  Toast.LENGTH_SHORT).show();
					 
					 dialog.dismiss();
				 }
						 
			}
	    
	    }
	 

}
