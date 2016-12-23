package com.dam.vu2k16;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dam.vu2k16.fragments.ContactFragment;
import com.dam.vu2k16.fragments.DasavathaaramFragment;
import com.dam.vu2k16.fragments.HomeFragment;
import com.dam.vu2k16.fragments.ThiruvulsavamFragment;
import com.dam.vu2k16.fragments.UlsavamThemeSongFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    boolean backButtonStatus = false;
    FragmentTransaction ft = null;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(Gravity.LEFT);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, new HomeFragment()).commit();

    }

    // CLOSE DRAWER//
    @Override
    protected void onResume() {
        super.onResume();

        drawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                drawer.closeDrawer(Gravity.LEFT);
            }
        }, 2000);

    }


    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (backButtonStatus) {
                finish();
                System.exit(0);
                return;
            }
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, new HomeFragment()).commit();
            this.backButtonStatus = true;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    backButtonStatus = false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_quit) {
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        ft = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            ft.replace(R.id.container, new HomeFragment()).commit();
        } else if (id == R.id.nav_avathaaram) {
            ft.replace(R.id.container, new DasavathaaramFragment()).commit();
        } else if (id == R.id.nav_ulsavam) {
            ft.replace(R.id.container, new ThiruvulsavamFragment()).commit();

        } else if (id == R.id.nav_theme_song) {
            ft.replace(R.id.container, new UlsavamThemeSongFragment()).commit();
        } else if (id == R.id.nav_contact) {
            ft.replace(R.id.container, new ContactFragment()).commit();
        } else if (id == R.id.nav_exit) {
            exitFromApp();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void exitFromApp() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setIcon(R.mipmap.ic_launcher);
        alertBuilder.setTitle("Warning!!!");
        alertBuilder.setMessage("Do you want to exit from VIMMALA ULSAVAM?");
        alertBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //            int pId = Process.myPid();
//            Process.killProcess(pId);
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        alertBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        alertBuilder.create().show();
    }
}
