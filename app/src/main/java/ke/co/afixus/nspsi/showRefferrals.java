package ke.co.afixus.nspsi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class showRefferrals extends Fragment
    {
        View ReffView;
       RecyclerView mRecyclerView;
       FirebaseDatabase mFirebaseDatabase;
       DatabaseReference mDatabaseReference;
       ArrayList<Refferrals> list;
       myAdapter adapter;
       FirebaseAuth mFirebaseAuth;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            ReffView = inflater.inflate(R.layout.fragment_show_refferrals, container, false);
            mRecyclerView = (RecyclerView) ReffView.findViewById(R.id.refferals_recyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            list = new ArrayList<Refferrals>();

            mFirebaseAuth = FirebaseAuth.getInstance();
            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Refferrals").child(mFirebaseAuth.getUid());
            mDatabaseReference.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                         Refferrals refferralsSpecific = dataSnapshot1.getValue(Refferrals.class);
                         list.add(refferralsSpecific);
                    }
                    adapter =new myAdapter(getContext(),list);
                    mRecyclerView.setAdapter(adapter);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), "Oopssss...... Something wnt wrong!", Toast.LENGTH_SHORT).show();
                }
            });
            return   ReffView ;
        }
    }
