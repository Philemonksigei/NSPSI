package ke.co.afixus.nspsi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adduserActivity extends AppCompatActivity {

    Spinner usertype;
    EditText stdadmno_staffno;
    EditText stdname;
    EditText stdphoneno1;
    EditText stdphoneno2;
    EditText stdemail;

    //new user items
    EditText stdpassword1;
    EditText stdpassword2;
    FirebaseAuth mFirebaseAuth;
    Button stdsignuphere;
    ProgressBar pbsignup;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Hide the Title bar of this activity screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);

        pbsignup= findViewById(R.id.progressBarsignup);
        pbsignup.setVisibility(View.GONE);
        usertype = findViewById(R.id.spinnerusers);
        stdadmno_staffno = findViewById(R.id.admnoid);
        stdname = findViewById(R.id.nameid);

        stdphoneno1 = findViewById(R.id.phoneid);
        stdphoneno2 = findViewById(R.id.phoneid2);
        stdemail = findViewById(R.id.emailid);

        mFirebaseAuth = FirebaseAuth.getInstance();
        stdpassword1 = findViewById(R.id.pwd);
        stdpassword2 = findViewById(R.id.pwd2);
        stdsignuphere = findViewById(R.id.stdsignup);

        String [] users = {"-Usertype-","Student","Staff","Guest",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        usertype.setAdapter(adapter);

        stdsignuphere.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    signingUp();

                }
            });
    }

    public void signingUp()
    {
        final String muser = usertype.getSelectedItem().toString();
        final String mstdadmno = stdadmno_staffno.getText().toString();
        final String mstdname = stdname.getText().toString();
        final String mstdphoneno1 =stdphoneno1.getText().toString();
        final String mstdphoneno2 =stdphoneno2.getText().toString();
        final String mstemail = stdemail.getText().toString();
        String mstdpwd1 = stdpassword1.getText().toString().trim();
        String mstdpwd2 = stdpassword2.getText().toString().trim();

        if(muser.equals("-Usertype-"))
        {
            Toast.makeText(this, "Sorry!! \n Please select correct USERTYPE!", Toast.LENGTH_SHORT).show();
        }
        else if(mstdname.startsWith(" ")||mstdadmno.startsWith(" ")||mstdname.isEmpty()|| mstdadmno.isEmpty() || mstdphoneno1.isEmpty()|| mstdphoneno2.isEmpty()|| mstemail.isEmpty())
        {
            Toast.makeText(this, " Check Empty Fields!!\n Fill them first.", Toast.LENGTH_SHORT).show();
        }
        else if(mstdphoneno1.length() !=10 ||mstdphoneno1.startsWith(" ")|| mstdphoneno2.length() != 10 || mstdphoneno2.startsWith(" "))
        {
            stdphoneno1.setError("Invalid Phone Number!");
            stdphoneno1.requestFocus();
            stdphoneno2.setError("Invalid Phone Number!");
            stdphoneno2.requestFocus();
        }
        else   if(!(mstdphoneno1.equals(mstdphoneno2))){
            Toast.makeText(getApplicationContext(), "Phone numbers do not match!", Toast.LENGTH_SHORT).show();
        }
        else if (mstemail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mstemail).matches())
        {
            stdemail.setError("Invalid Email Address!");
            stdemail.requestFocus();
        }
        else if(mstdpwd1.isEmpty()||mstdpwd1.startsWith(" "))
        {
            stdpassword1 .setError("Please fill in your password!");
            stdpassword1 .requestFocus();
        }
        else if(mstdpwd1.length()<9)
        {
            stdpassword1 .setError("Password too Short! \n" +
                    "Use at least 9 Characters");
            stdpassword1 .requestFocus();
        }
        else if(mstdpwd1.length()>11)
        {
            stdpassword1 .setError("Password too long! \n" +
                    "Use at most 11 Characters");
            stdpassword1 .requestFocus();
        }

        else if(mstdpwd2.isEmpty()||mstdpwd2.startsWith(" "))
        {
            stdpassword2 .setError("Please fill in your password!");
            stdpassword2 .requestFocus();
            stdpassword1.setText("");
        }
        else if (!mstdpwd1.equals(mstdpwd2)){
            stdpassword1 .setError("Passwords do not match!");
            stdpassword1 .requestFocus();
            stdpassword2 .setError("Passwords do not match!");
            stdpassword2 .requestFocus();
        }
        else  {
                if (haveNetwork())
                {
                    pbsignup.setVisibility(View.VISIBLE);
                    stdsignuphere.setVisibility(View.GONE);

                    mFirebaseAuth.createUserWithEmailAndPassword(mstemail, mstdpwd1)
                    .addOnCompleteListener(adduserActivity.this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                if( muser.equals("Student"))
                                {
                                    mFirebaseAuth.getUid();
                                    databaseReference = firebaseDatabase.getReference("Users").child(mFirebaseAuth.getUid());

                                }
                                if(muser.equals("Staff"))
                                {
                                    mFirebaseAuth.getUid();
                                    databaseReference = firebaseDatabase.getReference("Users").child(mFirebaseAuth.getUid());

                                }
                                if(muser.equals("Guest"))
                                { mFirebaseAuth.getUid();
                                    databaseReference = firebaseDatabase.getReference("Users").child(mFirebaseAuth.getUid());

                                }
                                registryData.Student_staff student_staff= new registryData.Student_staff(muser,mstdadmno, mstdname, mstdphoneno1, mstemail);
                                //send to database
                                databaseReference.setValue(student_staff);
                                databaseReference.setValue(student_staff);
                                databaseReference.setValue(student_staff);
                                databaseReference.setValue(student_staff);
                                databaseReference.setValue(student_staff);

                                Toast.makeText(adduserActivity.this, "Registration Successful!\nPlease Login.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), loginActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else {
                                Toast.makeText(adduserActivity.this, "Sorry! Could not Create user.\n Please Try again!!", Toast.LENGTH_SHORT).show();
                                }
                        }
                    });
                }
                    else if (!haveNetwork())
                    {
                        Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                    }
            }
    }
    private boolean haveNetwork()
    {
        boolean have_WIFI = false;
        boolean have_Mobiledata =false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

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
