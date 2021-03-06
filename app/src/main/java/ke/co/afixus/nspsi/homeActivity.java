package ke.co.afixus.nspsi;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;

public class homeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.interchange_area, new registryFrag());
        tx.commit();
        setContentView(R.layout.activity_home);
         Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
           /*
               FloatingActionButton fab = findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
           */
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed()
        {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START))
            {
                drawer.closeDrawer(GravityCompat.START);
            } else
                {
                super.onBackPressed();
            }
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.logout)
        {
            Intent i = new Intent(getApplicationContext(),loginActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        Fragment fragment= null;
        int id = item.getItemId();
        if (id == R.id.nav_registry)
        {
            fragment = new registryFrag();
            // Handle the camera action
        }
        else if (id == R.id.nav_my_list)
        {
            fragment = new referralsFrag();
        }
        else if (id == R.id.nav_aboutnspsi)
        {
            fragment = new messages_updatesFrag();
        }
        else if (id == R.id.nav_myprofile)
        {
            fragment = new myprofileFrag();
        }
        else if (id == R.id.nav_aboutapp)
        {
            fragment = new frag_aboutapp();
        }
        else if (id == R.id.nav_sbox)
        {
            fragment = new suggestion_boxFrag();
        }
        else if (id == R.id.nav_share)
        {

        }

        if(fragment != null)
            {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.interchange_area, fragment);
                fragmentTransaction.commit();
            }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
