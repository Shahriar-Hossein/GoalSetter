package com.example.goalsetter;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

// import ui's
import com.example.goalsetter.ui.goals.GoalsFragment;
import com.example.goalsetter.ui.daily.DailyFragment;
import com.example.goalsetter.ui.recurring.RecurringFragment;

import com.example.goalsetter.R;



public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private MaterialToolbar topAppBar;
    private BottomNavigationView bottomNav;
    private NavigationView navDrawer;
    private FloatingActionButton fabAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        fabAdd.setOnClickListener(this::onAddButtonClicked);
        initTopAppBar();
        initDrawerMenu();
        initBottomNavigation();

        // Load default fragment
        loadFragment(new GoalsFragment(), "Goals");
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        topAppBar = findViewById(R.id.topAppBar);
        bottomNav = findViewById(R.id.bottomNavigationView);
        navDrawer = findViewById(R.id.navigation_view);
        fabAdd = findViewById(R.id.fab_add);
    }

    private void initTopAppBar() {
        topAppBar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_profile_icon) {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    private void initBottomNavigation() {
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_goals) {
                loadFragment(new GoalsFragment(), "Goals");
                return true;
            } else if (id == R.id.nav_daily) {
                loadFragment(new DailyFragment(), "Daily Tasks");
                return true;
            } else if (id == R.id.nav_recurring) {
                loadFragment(new RecurringFragment(), "Recurring Tasks");
                return true;
            }
            return false;
        });

    }

    private void initDrawerMenu() {
        navDrawer.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.nav_profile){
                Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();
                return true;
            }else if(id == R.id.nav_settings){
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                return true;
            }else if(id == R.id.nav_night_mode){
                toggleNightMode();
                return true;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
//            switch (item.getItemId()) {
//                case R.id.nav_profile:
//                    Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.nav_settings:
//                    Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.nav_night_mode:
//                    toggleNightMode();
//                    break;
//            }

//            return true;
        });
    }

    private void loadFragment(Fragment fragment, String title) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        topAppBar.setTitle(title);
    }

    private void onAddButtonClicked(View view){
        Toast.makeText(this, "Add button clicked", Toast.LENGTH_SHORT).show();
    }


    private void toggleNightMode() {
        int mode = AppCompatDelegate.getDefaultNightMode();
        if (mode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
}