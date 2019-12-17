package ke.co.afixus.nspsi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myprofileFrag extends Fragment
{
    TextView usernameView;
    TextView usertypeView;
    TextView userphoneView;
    TextView useremailView;
    TextView userIDView;
    TextView userstotalRefsView;

    Button edit_login_details;
    TextView currentpwd;
    TextView newpwd;
    Button update_login_details;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseAuth mFirebaseAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myprofile, container, false);
        usernameView = view.findViewById(R.id.usersnametextView);
        usertypeView= view.findViewById(R.id.userstypetextView);
        userphoneView = view.findViewById(R.id.usersphonetextView);
        useremailView = view.findViewById(R.id.usersemailtextView);
        userIDView = view.findViewById(R.id.usersIDtextView);
        userstotalRefsView = view.findViewById(R.id.ref_totals);

        edit_login_details = view.findViewById(R.id.button_update_details);
        currentpwd = view.findViewById(R.id.current_pwd);
        newpwd = view.findViewById(R.id.new_pwd);
        update_login_details = view.findViewById(R.id.update_login_button);
        //hide oncreate
        update_login_details.setVisibility(View.GONE);
        currentpwd.setVisibility(View.GONE);
        newpwd .setVisibility(View.GONE);


        //get the user id of the current user
        mFirebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(mFirebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                String usersname = dataSnapshot.child("stdname").getValue().toString();
                String userstype = dataSnapshot.child("usertype").getValue().toString();
                String usersphoneno1 = dataSnapshot.child("phoneno1").getValue().toString();
                String usersemail = dataSnapshot.child("email").getValue().toString();
                String usersIds = dataSnapshot.child("admno_staffno").getValue().toString();

               // String userRefTotals = dataSnapshot.child("phoneno1").getValue().toString();

                usernameView .setText("Name:"+ " " +usersname);
                usertypeView.setText("User-type:"+ " " +userstype);
                userphoneView .setText("Mobile NO:"+ " " +usersphoneno1 );
                useremailView .setText("Email :"+" " + usersemail);
                userIDView .setText( "User Reg-ID:"+ " " +usersIds);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(getActivity(), "No such user!", Toast.LENGTH_SHORT).show();
            }
        });
        //end of user retrieval

        //show the edit fields
        edit_login_details.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                update_login_details.setVisibility(View.VISIBLE);
                currentpwd.setVisibility(View.VISIBLE);
                newpwd .setVisibility(View.VISIBLE);
            }
        });
        //pichk values from the fields then update



        return  view;
    }
}
