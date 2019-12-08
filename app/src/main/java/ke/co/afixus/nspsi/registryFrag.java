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

import java.util.ArrayList;

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
     Spinner refcoursesSpinner;
     Spinner refintake;
     Spinner reflevels;
     Button submitbuttton;
     long EntryNO =0;
     //String refereename;
     //DatePicker datePicker;


     FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
     DatabaseReference databaseReference;
     FirebaseAuth mFirebaseAuth;
     //TextViews for retrieval purposes only
     TextView refnameview;
     TextView refphoneview;
     //dealing with courses retrieval
     ArrayAdapter<String> adapter_courses;
     ArrayList<String> spinner_courses;
     ValueEventListener listener;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            // Inflate the layout for this fragment
           View view = inflater.inflate(R.layout.fragment_registry, container, false);

//RETRIEVE USER
            refnameview = view.findViewById(R.id.curentusertxt);
            refphoneview= view.findViewById(R.id.userphoneid);
            //long registryno;

            if (haveNetwork())
            {//check network just incase the user logged well but data was disconnected
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

            }
            else
            {
                Toast.makeText(getActivity(), "Oopssss... Network Error!", Toast.LENGTH_SHORT).show();

            }

///COURSES showing on the spinner

 refcoursesSpinner =  view.findViewById(R.id.spinnercourse);
 databaseReference = FirebaseDatabase.getInstance().getReference().child("Courses");

 spinner_courses = new ArrayList<>();
 adapter_courses = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, spinner_courses);
 refcoursesSpinner.setAdapter(adapter_courses);
 retrieveCourses();
            refstdname = (EditText) view.findViewById(R.id.stdname);
            refgenderselect = view.findViewById(R.id.genderspinner);
            refphoneno1 =  view.findViewById(R.id.phone1);
            refphoneno2 = view.findViewById(R.id.phone2);
            refemail =  view.findViewById(R.id.emailid);
            refintake =  view.findViewById(R.id.spinnerintake);
            refgrade = view.findViewById(R.id.grademark);
            reflevels =  view.findViewById(R.id.spinnerlevel);
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

            //kcse grade
            final String [] kcsegrade;
            kcsegrade = getResources().getStringArray(R.array.grades);
            final ArrayAdapter<String> adapterkcsegrade = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, kcsegrade);
            adapterkcsegrade.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            refgrade.setAdapter(adapterkcsegrade);

            String [] levelofcourses;
            levelofcourses = getResources().getStringArray(R.array.course_level);
            ArrayAdapter<String> adaptercourselevel = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, levelofcourses);
            adaptercourselevel.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            reflevels.setAdapter(adaptercourselevel);



            submitbuttton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    if(haveNetwork()){
                        referalreg();
                    }

                    else{
                        Toast.makeText(getContext(), "No Internet!", Toast.LENGTH_SHORT).show();

                    }

                }
            });


           return  view;
        }

 //retrieve courses here


     public void retrieveCourses ()
     {
         if(haveNetwork())
         {
             listener = databaseReference.addValueEventListener(new ValueEventListener()
             {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                 {
                     for (DataSnapshot item: dataSnapshot.getChildren())
                     {

                         spinner_courses.add(item.getValue().toString());
                     }
                     adapter_courses.notifyDataSetChanged();
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError)
                 {
                     Toast.makeText(getContext(), "Error in Showing Current Courses", Toast.LENGTH_SHORT).show();

                 }
             });


         }

         else{
                 //Courses
                 String [] mycozplaceholder = {"-Course-"};
                 ArrayAdapter<String> adaptercozholder = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, mycozplaceholder);
                 adaptercozholder.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                 refcoursesSpinner.setAdapter(adaptercozholder);
            }
     }


//send referrrals to database
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
         String mrefcourse;
         String mrefintake;
         String mreflevels;

         mrefereename = refnameview.getText().toString().trim();
         mrefereephone = refphoneview.getText().toString().trim();
         mrefstdname = refstdname.getText().toString().trim();
         mrefgenderselect =  refgenderselect.getSelectedItem().toString().trim();
         mrefphoneno1 = refphoneno1.getText().toString();
          mrefphoneno2= refphoneno2.getText().toString();
         mrefemail = refemail.getText().toString().trim();
         mrefgrade =   refgrade.getSelectedItem().toString().trim();
         mrefcourse =  refcoursesSpinner.getSelectedItem().toString();
         mrefintake =   refintake.getSelectedItem().toString().trim();
         mreflevels =   reflevels.getSelectedItem().toString().trim();

         if ( mrefereename.isEmpty()|| mrefereename.isEmpty()){
             refstdname.setError("Network Error!");
             refstdname.requestFocus();
         }

         if (mrefstdname.isEmpty()){
             refstdname.setError("Name cannot be Empty");
             refstdname.requestFocus();
         }

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
         else  if(refcoursesSpinner.getSelectedItem().equals("-Course-")){
             Toast.makeText(getActivity(), "Please choose a Course", Toast.LENGTH_SHORT).show();
         }

         else  if(refintake.equals("-Intake-")){
             Toast.makeText(getActivity(), "Please Intake!", Toast.LENGTH_SHORT).show();
         }
         else {
                {
                     if (haveNetwork())
                     {
                             //now connect to the database
                           mFirebaseAuth= FirebaseAuth.getInstance();
                           databaseReference = firebaseDatabase.getReference("Refferrals");
                           //.child(mFirebaseAuth.getUid());
                             //send to database
                            Refferrals myreferals = new Refferrals(mrefereename,mrefereephone,mrefstdname,
                              mrefgenderselect, mrefphoneno2, mrefemail, mrefgrade,mrefcourse, mrefintake, mreflevels);
                             databaseReference.push().setValue(myreferals);

                             refstdname.setText("");
                             refgenderselect.setSelection(0);
                             refphoneno1.setText("");
                             refphoneno2.setText("");
                             refemail.setText("");
                             refgrade.setSelection(0);
                              refcoursesSpinner.setSelection(0);
                             refintake.setSelection(0);
                             reflevels.setSelection(0);
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
