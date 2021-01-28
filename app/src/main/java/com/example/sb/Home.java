package com.example.sb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    Button buttonBack,buttonHamburger,buttonKill;

    private FirebaseAuth mAuth;

    NavigationView navigationView;
    DrawerLayout drawer;
    FrameLayout fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonHamburger = (Button)findViewById(R.id.buttonHamburger);
        buttonKill = (Button)findViewById(R.id.buttonKill);
        drawer = (DrawerLayout)findViewById(R.id.drawer);
        fragment_container = (FrameLayout)findViewById(R.id.fragment_container);

        //AppBar Button OnPressed
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        buttonHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(navigationView);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.logout:
                        mAuth.signOut();
                        finishAffinity();
                        startActivity(new Intent(Home.this,Login.class));
                        finish();
                        break;
                    case R.id.change_pin:
                        startActivity(new Intent(Home.this,PinOne.class));
                        drawer.closeDrawer(navigationView,false);
                        break;
                    case R.id.forgot_password:
                        break;
                    case R.id.profile:
                        startActivity(new Intent(Home.this, UserProfile.class));
                        drawer.closeDrawer(navigationView,false);
                        break;
                }

                return true;
            }
        });

        //loading the default fragment
        loadFragment(new FragmentGeneral());
        //BottomNavigationMenu
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.navigation_general:
                        fragment = new FragmentGeneral();
                        break;
                    case R.id.navigation_rooms:
                        fragment = new FragmentRooms();
                        break;
                }
                return loadFragment(fragment);
            }
        });
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }
}