package ke.co.afixus.nspsi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class frag_AboutNSPSI extends Fragment
    {
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        FirebaseAuth mFirebaseAuth;


        TextView StoryView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            // Inflate the layout for this fragment
           View view  = inflater.inflate(R.layout.fragment_frag__about_nspsi, container, false);
           StoryView = view.findViewById(R.id.nspsi_about_storyid);
            mFirebaseAuth= FirebaseAuth.getInstance();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Story");
            databaseReference.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    String MsgStory = dataSnapshot.child("Description").getValue().toString();
                    StoryView.setText(MsgStory);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError)
                {
                    Toast.makeText(getActivity(), "No such user!", Toast.LENGTH_SHORT).show();
                }
            });
            //end of user retrieval

   return  view;
        }


    }
