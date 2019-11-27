package ke.co.afixus.nspsi;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

import java.lang.reflect.Array;

import static android.content.Context.CONNECTIVITY_SERVICE;


public class registryFrag extends Fragment
 {
     //Spinner intake;
     EditText refstdname;
     Spinner refgenderselect;
     EditText refphoneno1;
     EditText refphoneno2;
     EditText refemail;
     Spinner refgrade;
     Spinner refcourses;
     Spinner refintake;
     Spinner reflevels;
     Spinner refyrofjoining;
     Spinner refcounty;
     Button submitbuttton;
     //String refereename;
     //DatePicker datePicker;


     FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
     DatabaseReference databaseReference;
     FirebaseAuth mFirebaseAuth;

     //TextViews for retrieval purposes only
     TextView refnameview;
     TextView refphoneview;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            // Inflate the layout for this fragment
           View view = inflater.inflate(R.layout.fragment_registry, container, false);

           //retrieve user here
            refnameview = view.findViewById(R.id.curentusertxt);
            refphoneview= view.findViewById(R.id.userphoneid);
            //long registryno;

            mFirebaseAuth= FirebaseAuth.getInstance();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(mFirebaseAuth.getUid());
            databaseReference.addValueEventListener(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                               String refereename = dataSnapshot.child("stdname").getValue().toString();
                                String  refereephone = dataSnapshot.child("phoneno1").getValue().toString();
                                refnameview.setText( "By:" + refereename+" ");
                                refphoneview.setText(":-"+refereephone);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError)
                            {
                                Toast.makeText(getActivity(), "No such user!", Toast.LENGTH_SHORT).show();
                            }
                        });
            //end of user retrieval


            refstdname = (EditText) view.findViewById(R.id.stdname);
            refgenderselect = view.findViewById(R.id.genderspinner);
            refphoneno1 =  view.findViewById(R.id.phone1);
            refphoneno2 = view.findViewById(R.id.phone2);
            refemail =  view.findViewById(R.id.emailid);
            refintake =  view.findViewById(R.id.spinnerintake);
            refcounty =  view.findViewById(R.id.spinnercounty);
            refgrade = view.findViewById(R.id.grademark);
            refcourses = view.findViewById(R.id.spinnercourse);
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
            final String [] kcsegrade;
            kcsegrade = getResources().getStringArray(R.array.grades);
            final ArrayAdapter<String> adapterkcsegrade = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, kcsegrade);
            adapterkcsegrade.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            refgrade.setAdapter(adapterkcsegrade);

                         /*///COURSES
                            FirebaseDatabase.getInstance();
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("Courses").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String [] nspsicourses;
                                    nspsicourses = getResources().getStringArray(R.array.);
                                    ArrayAdapter<String> adaptercourses = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, nspsicourses);
                                    adapterkcsegrade.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                                    refcourses.setAdapter(adaptercourses);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                         */

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
         String  mrefereename;
         String  mrefereephone;

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


         mrefereename = refnameview.getText().toString().trim();
         mrefereephone = refphoneview.getText().toString().trim();

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
                             databaseReference = firebaseDatabase.getReference("Refferrals").child(mrefereephone);
                             String id = databaseReference.push().getKey();
                             //send to database
                             Data.referals myreferals = new Data.referals(mrefereename,mrefereephone,mrefstdname, mrefgenderselect, mrefphoneno2, mrefemail, mrefgrade, mrefintake, mrefyrofjoining, mreflevels, mrefcounty);
                             databaseReference.child(id).setValue(myreferals);
                             databaseReference.child(id).setValue(myreferals);
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
                             refgenderselect.setSelection(0);
                             refphoneno1.setText("");
                             refphoneno2.setText("");
                             refemail.setText("");
                             refgrade.setSelection(0);
                             //Spinner refcourses;
                             refintake.setSelection(0);
                             reflevels.setSelection(0);
                             refyrofjoining.setSelection(0);
                             refcounty.setSelection(0);
                             Toast.makeText(getActivity(), "Congratulations! \n You have added a student Successfully!", Toast.LENGTH_SHORT).show();
                     }
                     else if (!haveNetwork())
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
