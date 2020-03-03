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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    Button  login_button;
    TextView signup_button;;
    ProgressBar pb;
    EditText mEmail;
    EditText mPassword;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //showing full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the Title bar of this activity screen
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        login_button = findViewById(R.id.button_login);
        mEmail = findViewById(R.id.email_login);
        pb = findViewById(R.id.progress_pb);
        mPassword = findViewById(R.id.login_password);
        pb.setVisibility(View.INVISIBLE);
        login_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                String mstemail = mEmail.getText().toString().trim();
                String mstdpwd1 = mPassword.getText().toString().trim();

                if(mstemail.isEmpty()|| mstdpwd1.isEmpty() ){

                    Toast.makeText(loginActivity.this, "Sorry!\n Empty Fields!", Toast.LENGTH_SHORT).show();
                }

                if(mstemail.isEmpty()){
                    mEmail.setError("Please fill in your email!");
                    mEmail.requestFocus();
                }

                else if (!Patterns.EMAIL_ADDRESS.matcher(mstemail).matches())
                {
                    mEmail.setError("Invalid Email Address!");
                    mEmail.requestFocus();
                }
                else if(mstdpwd1.isEmpty())
                {
                    mPassword .setError("Please fill in your password!");
                    mPassword .requestFocus();
                }

                else if (!(mstemail.isEmpty()&& mstdpwd1.isEmpty()) )
                {
                    if(haveNetwork())
                    {
                        login_button.setVisibility(View.INVISIBLE);
                        pb.setVisibility(View.VISIBLE);

                        mFirebaseAuth = FirebaseAuth.getInstance();
                        mFirebaseAuth.signInWithEmailAndPassword(mstemail, mstdpwd1)
                                .addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task)
                                    {
                                        if (task.isSuccessful())
                                        {

                                            Toast.makeText(loginActivity.this, "  Login Successful!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                                            startActivity(intent);
                                            pb.setVisibility(View.GONE);
                                            mEmail.setText("");
                                            mPassword.setText("");
                                           finish();
                                        }
                                        else
                                        {
                                            login_button.setVisibility(View.VISIBLE);
                                           pb.setVisibility(View.GONE);
                                            Toast.makeText(loginActivity.this, "Error!! \n Check  Email and Password", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                    }

                    else if(!haveNetwork())
                    {
                        Toast.makeText(loginActivity.this, "No Internet Connection!\n Turn on Mobile registryData or Join WIFI.", Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                    }
                }
                else
                {

                    Toast.makeText(loginActivity.this, "Error!!\n Check Email and Password!", Toast.LENGTH_SHORT).show();
                    //pb.setVisibility(View.GONE);
                }
            }
        });

       signup_button = findViewById(R.id.signupbtn);
        signup_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),adduserActivity.class);
                startActivity(i);
            }
        });


        mAuthStateListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null)
                {
                    Toast.makeText(loginActivity.this, "You are Loggeg in!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                    startActivity(intent);
                    //now open the next activity
                }
            }
        };

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
