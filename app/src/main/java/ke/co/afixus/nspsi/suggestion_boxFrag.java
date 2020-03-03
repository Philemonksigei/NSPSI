package ke.co.afixus.nspsi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;

public class suggestion_boxFrag extends Fragment {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseAuth mFirebaseAuth;

    TextView suggestionTxt;
    Button submitSuggestion;
    //ValueEventListener listener;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_suggestion_box, container, false);
        submitSuggestion = view.findViewById(R.id.buttonSubmitSg);
        suggestionTxt = view.findViewById(R.id.suggestionMs);
        submitSuggestion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String suggestionMessage;
                String dateOfPost;
                suggestionMessage = suggestionTxt.getText().toString().trim();
                dateOfPost = DateFormat.getDateTimeInstance().format(new Date());
                mFirebaseAuth = FirebaseAuth.getInstance();
                if (suggestionMessage.startsWith(" ") || suggestionMessage.isEmpty()) {
                    Toast.makeText(getActivity(), "Please key in your Suggestion", Toast.LENGTH_SHORT).show();
                    suggestionTxt.setText("");
                       }
                        else if(suggestionMessage.length()> 100)
                                    {
                                     Toast.makeText(getActivity(), "Message too long!", Toast.LENGTH_SHORT).show();

                                    }
                else {

/*
                    private void confirmDialog() {
                        Context context = null;
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);

                        builder
                                .setMessage("Are you sure?")
                                .setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        // Yes-code
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                })
                                .show();
                    }

*/
                        databaseReference = firebaseDatabase.getReference("Suggestions").child(mFirebaseAuth.getUid());
                        suggestionsData suggestions = new suggestionsData(suggestionMessage, dateOfPost);
                        databaseReference.push().setValue(suggestions);

                        //TODO Check if process is successful first
                        //if it is successful, show toast of success
                        Toast.makeText(getActivity(), "Recieved\nThanks for your message!", Toast.LENGTH_SHORT).show();

                        suggestionTxt.setText("");
                      }
            }
        });
        return view;
    }

}
