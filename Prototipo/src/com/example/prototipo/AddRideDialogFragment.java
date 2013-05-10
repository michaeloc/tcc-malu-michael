package com.example.prototipo;



import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddRideDialogFragment extends DialogFragment 
implements OnClickListener{
	EditText addSaidaRideEditText;
	EditText addDestinoRideEditText;
	
	public interface DialogFinishedListener{
		void onDialogFinished(String destino);
	}
	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		this.setCancelable(true);
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle argumentBundle)	{
		
		View rootView = inflater.inflate(R.layout.add_ride_dialog,container,
				false);
		addSaidaRideEditText = (EditText)rootView.findViewById(R.id.add_ride_saida_edit_text);
		addDestinoRideEditText = (EditText)rootView.findViewById(R.id.add_ride_destino_edit_text);
		getDialog().setTitle(R.string.add);
		Button button = (Button)rootView.findViewById(R.id.add_ride_button);
		button.setOnClickListener(this);
		return rootView;
	}
	@Override
	public void onClick(View clickView) {
		// TODO Auto-generated method stub
		if(clickView.getId() == R.id.add_ride_button){
			DialogFinishedListener listener = (DialogFinishedListener)getActivity();
			
			listener.onDialogFinished(addDestinoRideEditText.getText().toString());
			dismiss();
		}
	}
	@Override
	public void onSaveInstanceState(Bundle argumentsBundle){
		argumentsBundle.putCharSequence("destino", addDestinoRideEditText.getText().toString());
		super.onSaveInstanceState(argumentsBundle);
	}
	
	

}
