package com.example.koperasi.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.koperasi.R;
import com.example.koperasi.ui.angsuran.AngsuranFragment;
import com.example.koperasi.ui.home.HomeFragment;
import com.example.koperasi.ui.pinjaman.PinjamanFragment;
import com.example.koperasi.ui.setting.SettingFragment;
import com.example.koperasi.ui.simpanan.SimpananFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NavigateDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String accessToken;
    private int id;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView bottomNavigationView;

        View headerView = navigationView.getHeaderView(0);

        TextView textViewUsername = headerView.findViewById(R.id.textView_username);
        TextView textViewRole = headerView.findViewById(R.id.textViewEmail);

        Intent intent = getIntent();

        accessToken = intent.getStringExtra("access_token");
        id = intent.getIntExtra("id_anggota", 0);
        username = intent.getStringExtra("username");

        intent.putExtra("id_anggota", id);
        intent.putExtra("access_token", accessToken);
        intent.putExtra("username", username);


        textViewUsername.setText("Welcome "+intent.getStringExtra("username").toUpperCase());
//        if (intent.getStringExtra("role").equals("N")){
            textViewRole.setText("Nasabah Koperasi");
//        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.item_nav_home);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Bundle bundle = new Bundle();
                bundle.putString("access_token", accessToken);
                bundle.putInt("id_anggota", id);
                bundle.putString("username", username);
                switch (item.getItemId()) {
                    case R.id.navigation_dashboard:
                        fragment = new HomeFragment();
                        fragment.setArguments(bundle);
                        break;
                    case R.id.navigation_pinjaman:
                        fragment = new PinjamanFragment();
                        fragment.setArguments(bundle);
                        break;
                    case R.id.navigation_profile:
                        fragment = new SettingFragment();
                        fragment.setArguments(bundle);
                        break;

                }

                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();

                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigate_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Logout sukses", Toast.LENGTH_SHORT).show();
    }

    private void displaySelectedScreen(int itemId) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        bundle.putString("access_token", accessToken);
        bundle.putInt("id_anggota", id);
        bundle.putString("username", username);
        switch (itemId) {
            case R.id.item_nav_home:
                fragment = new HomeFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.item_nav_angsuran:
                fragment = new AngsuranFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.item_nav_pinjaman:
                fragment = new PinjamanFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.item_nav_simpanan:
                fragment = new SimpananFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.item_nav_setting:
                fragment = new SettingFragment();
                fragment.setArguments(bundle);
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }
}