package ke.co.afixus.nspsi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;


public class messages_updatesFrag extends Fragment
    {
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;
        FirebaseAuth mFirebaseAuth;
        RecyclerView mRecyclerView;

        DatabaseReference mDatabaseReference;
        ArrayList<messagesData> list;
        messagesAdapter adapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            // Inflate the layout for this fragment
           View view  = inflater.inflate(R.layout.messages_updates_frag, container, false);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.messagesRecyclerview);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            list = new ArrayList<messagesData>();


            mFirebaseAuth = FirebaseAuth.getInstance();
            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Stories_Messages");
            mDatabaseReference.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        messagesData messagesSpec = dataSnapshot1.getValue(messagesData.class);
                        list.add(messagesSpec);
                    }
                    adapter =new messagesAdapter(getContext(),list);
                    mRecyclerView.setAdapter(adapter);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), "Oopssss...... Something wnt wrong!", Toast.LENGTH_SHORT).show();
                }
            });
            //end of user retrieval
   return  view;
        }

    }
