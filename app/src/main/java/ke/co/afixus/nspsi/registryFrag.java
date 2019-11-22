package ke.co.afixus.nspsi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static androidx.constraintlayout.widget.Constraints.TAG;
import static androidx.core.content.ContextCompat.getSystemService;


public class registryFrag extends Fragment
 {
     //Spinner intake;
     EditText refstdname;
     Spinner refgenderselect;
     EditText refphoneno1;
     EditText refphoneno2;
     EditText refemail;
     Spinner refgrade;
     //Spinner refcourses;
     Spinner refintake;
     Spinner reflevels;
     Spinner refyrofjoining;
     Spinner refcounty;
     Button submitbuttton;
     //DatePicker datePicker;


    FirebaseDatabase firebaseDatabase;
    //= FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseAuth mFirebaseAuth;

    private   TextView currentusersname;
    private   TextView currentusersemail;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            // Inflate the layout for this fragment
           View view = inflater.inflate(R.layout.fragment_registry, container, false);

            refstdname = (EditText) view.findViewById(R.id.stdname);
            refgenderselect = view.findViewById(R.id.genderspinner);
            refphoneno1 =  view.findViewById(R.id.phone1);
            refphoneno2 = view.findViewById(R.id.phone2);
            refemail =  view.findViewById(R.id.emailid);
            refintake =  view.findViewById(R.id.spinnerintake);
            refcounty =  view.findViewById(R.id.spinnercounty);
            refgrade = view.findViewById(R.id.grademark);
            //refcourses = view.findViewById(R.id.spinnercourse);
            reflevels =  view.findViewById(R.id.spinnerlevel);
            refyrofjoining =  view.findViewById(R.id.spinneryrjoin);

            submitbuttton = (Button) view.findViewById(R.id.submitdetails);
            //gender
            String [] mygender = {"-Gender-","Male","Female",};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, mygender);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            refgenderselect.setAdapter(adapter);
            //intakes
            String [] intakes = {"-Intake-","May","September","January",};
            ArrayAdapter<String> adapterintake = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, intakes);
            adapterintake.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            refintake.setAdapter(adapterintake);

            //counties, using string resources
            String [] ourcounties;
            ourcounties= getResources().getStringArray(R.array.counties);
            ArrayAdapter<String> adaptercounties = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, ourcounties);
            adaptercounties.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            refcounty.setAdapter(adaptercounties);

            //kcse grade
            String [] kcsegrade;
            kcsegrade = getResources().getStringArray(R.array.grades);
            ArrayAdapter<String> adapterkcsegrade = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, kcsegrade);
            adapterkcsegrade.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            refgrade.setAdapter(adapterkcsegrade);

            //course levels
            String [] levelofcourses;
            levelofcourses = getResources().getStringArray(R.array.course_level);
            ArrayAdapter<String> adaptercourselevel = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, levelofcourses);
            adaptercourselevel.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            reflevels.setAdapter(adaptercourselevel);

            //course levels
            String [] yrofjoin;
            yrofjoin = getResources().getStringArray(R.array.yearof_join);
            ArrayAdapter<String> adapteryrjoin = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, yrofjoin);
            adapteryrjoin.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            refyrofjoining.setAdapter(adapteryrjoin);

    //time to retrive

   currentusersname = view.findViewById(R.id.curentusertxt);

     mFirebaseAuth = FirebaseAuth.getInstance();
     firebaseDatabase = FirebaseDatabase.getInstance();
     DatabaseReference databaseReference = firebaseDatabase.getReference(mFirebaseAuth.getUid()).child("stdname");

     databaseReference.addValueEventListener(new ValueEventListener()
                 {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                     {
                         Data.Student_staff  datausers = dataSnapshot.getValue(Data.Student_staff.class);


                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError)
                     {

                     }
                 });


     ///trying to retrieve the user info here
       /*     DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(mFirebaseAuth.getCurrentUser().getUid());

            databaseReference.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    final  String usertypetxt = dataSnapshot.child("Users").getValue(String.class);

                    showcurrent_user.setText(usertypetxt);

                    if(usertypetxt.matches("StudentUsers"))
                    {
                        final String usersemail = dataSnapshot.child("Users").child("StudentUsers").getValue(String.class);
                        final String usersname = dataSnapshot.child("Users").child("StudentUsers").getValue(String.class);
                    }
                    else if(usertypetxt.matches("GuestUsers"))
                    {
                        final String usersemail = dataSnapshot.child("Users").child("StudentUsers").getValue(String.class);
                        final  String usersname = dataSnapshot.child("Users").child("StudentUsers").getValue(String.class);
                    }
                    else{
                        final String usersemail = dataSnapshot.child("Users").child("StudentUsers").getValue(String.class);
                        final  String usersname = dataSnapshot.child("Users").child("StudentUsers").getValue(String.class);
                    }

                }
                @Override
                public void onCancelled(DatabaseError error)
                {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
            */
///end of try to retieve
            submitbuttton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    referalreg();
                }
            });



           return  view;
        }


     public void referalreg()
     {
         String mrefstdname;
         String mrefgenderselect;
         String mrefphoneno1;
         String  mrefphoneno2;
         String mrefemail;
         String mrefgrade;
         //String mrefcourse;
         String mrefintake;
         String mrefyrofjoining;
         String mreflevels;
         String  mrefcounty;

         mrefstdname = refstdname.getText().toString().trim();
         mrefgenderselect =  refgenderselect.getSelectedItem().toString().trim();
         mrefphoneno1 = refphoneno1.getText().toString();
         mrefphoneno2= refphoneno2.getText().toString();
         mrefemail = refemail.getText().toString().trim();
         mrefgrade =   refgrade.getSelectedItem().toString().trim();
         //mrefcourse =  refcourses.getSelectedItem().toString();
         mrefintake =   refintake.getSelectedItem().toString().trim();
         mrefyrofjoining =  refyrofjoining.getSelectedItem().toString();
         mreflevels =   reflevels.getSelectedItem().toString().trim();
         mrefcounty =   refcounty.getSelectedItem().toString().trim();

         if (mrefstdname.isEmpty()){
             refstdname.setError("Name cannot be Empty");
             refstdname.requestFocus();
         }
         else  if (mrefgenderselect.equals("-Gender-")){

             Toast.makeText(getActivity(), "Please Select Gender!", Toast.LENGTH_SHORT).show();
         }
         else   if (mrefphoneno1.isEmpty()||mrefphoneno2.isEmpty()){
             refphoneno1.setError("Phone number field cannot be Empty!");
             refphoneno1.requestFocus();
             refphoneno2.setError("Phone number field cannot be Empty!");
             refphoneno2.requestFocus();
         }
         //check here
         else   if(!(mrefphoneno1.equals(mrefphoneno2))){
             Toast.makeText(getActivity(), "Phone numbers do not match!", Toast.LENGTH_SHORT).show();
         }
         else   if(mrefemail.isEmpty()){ refemail.setError("Email nummber cannot be Empty!");
             refemail.requestFocus();
         }
         else   if (!Patterns.EMAIL_ADDRESS.matcher(mrefemail).matches()){
             refemail.setError("Invalid Email Address!");
             refemail.requestFocus();
         }
         else    if(refgrade.equals("-Grade-")){
             Toast.makeText(getActivity(), "Please choose KCSE Grade!", Toast.LENGTH_SHORT).show();
         }
         else  if(refintake.equals("-Intake-")){
             Toast.makeText(getActivity(), "Please Intake!", Toast.LENGTH_SHORT).show();
         }
         else if(refyrofjoining.equals("-Year-")){
             Toast.makeText(getActivity(), "Please choose year to Join!", Toast.LENGTH_SHORT).show();
         }
         else if(reflevels.equals("-Level-")){
             Toast.makeText(getActivity(), "Please choose Level!", Toast.LENGTH_SHORT).show();
         }
         else  if(refcounty.equals("-County-")){
             Toast.makeText(getActivity(), "Please choose COUNTY!", Toast.LENGTH_SHORT).show();
         }
    else {
                {
                     if (haveNetwork())
                     {
                         //now connect to the database
                         databaseReference = firebaseDatabase.getReference( "Referrals").child("Kelvin Rono");
                         String id = databaseReference.push().getKey();
                         //send to database
                         Data.referals myreferals = new Data.referals(mrefstdname, mrefgenderselect, mrefphoneno2, mrefemail, mrefgrade, mrefintake, mrefyrofjoining, mreflevels, mrefcounty);
                         databaseReference.child(id).setValue(myreferals);
                         databaseReference.child(id).setValue(myreferals);
                         databaseReference.child(id).setValue(myreferals);
                         databaseReference.child(id).setValue(myreferals);
                         databaseReference.child(id).setValue(myreferals);
                         databaseReference.child(id).setValue(myreferals);
                         databaseReference.child(id).setValue(myreferals);
                         databaseReference.child(id).setValue(myreferals);
                         //clear fields
                         refstdname.setText("");
                         refgenderselect.setSelected(true);
                         refphoneno1.setText("");
                         refphoneno2.setText("");
                         refemail.setText("");
                         refgrade.setSelected(true);
                         //Spinner refcourses;
                         refintake.setSelected(true);
                         reflevels.setSelected(true);
                         refyrofjoining.setSelected(true);
                         refcounty.setSelected(true);
                         Toast.makeText(getActivity(), "Congratulations! \n You have added a student Successfully!", Toast.LENGTH_SHORT).show();


                     } else if (!haveNetwork())
                     {
                         Toast.makeText(getActivity(), "Sorry it appears you are disconnected!!\n Check your internet connection!", Toast.LENGTH_SHORT).show();
                     }
                }

         }

     }
     private boolean haveNetwork()
     {
         boolean have_WIFI = false;
         boolean have_Mobiledata =false;

         ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(CONNECTIVITY_SERVICE);
         NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
         for(NetworkInfo info:networkInfos)
         {

             if(info.getTypeName().equalsIgnoreCase("WIFI"))
             {
                 if(info.isConnected())
                     have_WIFI = true;
             }
             else if (info.getTypeName().equalsIgnoreCase("MOBILE"))
             {
                 if(info.isConnected())
                     have_Mobiledata = true;
             }

         }
         return  have_Mobiledata || have_WIFI;
     }
 }
