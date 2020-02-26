package ke.co.afixus.nspsi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class suggestion_boxFrag extends Fragment {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference, databaseReference2;
    FirebaseAuth mFirebaseAuth;

    TextView suggestionTxt;
    

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String suggestionMessage;
        suggestionMessage= suggestionTxt.getText().toString().trim();

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_suggestion_box, container, false);
        suggestionTxt = view.findViewById(R.id.suggestionMs);
        mFirebaseAuth= FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference("Suggestions").child(mFirebaseAuth.getUid());
       suggestionsData suggestions = new suggestionsData(suggestionMessage);
        databaseReference.push().setValue(suggestions);

        suggestionTxt.setText("");





        return view;
    }

}
