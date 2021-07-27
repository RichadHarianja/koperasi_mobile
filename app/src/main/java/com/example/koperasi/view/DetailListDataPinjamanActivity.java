package com.example.koperasi.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.koperasi.R;
import com.example.koperasi.ui.home.HomeFragment;
import com.example.koperasi.ui.pinjaman.PinjamanBottomFragment;
import com.example.koperasi.ui.setting.SettingBottomFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailListDataPinjamanActivity extends AppCompatActivity {
    private TextView title;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private TextView textView11;
    private TextView textView12;
    private TextView textView13;

    private TextView textView14;
    private TextView textView15;


    private String accessToken;
    private String username;
    private Integer id;
    private Toolbar mToolbar;
    private RelativeLayout relativeLayout;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_list_view);

        mToolbar = findViewById(R.id.toolbarBack);
        mToolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        mToolbar.setTitle("Pinjaman");

        Intent intentValue;
        intentValue = getIntent();

        accessToken = intentValue.getStringExtra("access_token");
        username = intentValue.getStringExtra("username");
        id = intentValue.getIntExtra("id_anggota", 0);

        bottomNavigationClick();

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });


        title = findViewById(R.id.textViewDetailListViewTitle);
        textView1 = findViewById(R.id.textViewDetailListView6);
        textView2 = findViewById(R.id.textViewDetailListView7);
        textView3 = findViewById(R.id.textViewDetailListView8);
        textView4 = findViewById(R.id.textViewDetailListView9);
        textView5 = findViewById(R.id.textViewDetailListView10);
        textView6 = findViewById(R.id.textViewDot3);
        textView7 = findViewById(R.id.textViewDot4);
        textView8 = findViewById(R.id.textViewDetailListView4);
        textView9 = findViewById(R.id.textViewDetailListView5);
        textView11 = findViewById(R.id.textViewDetailListView3);
        textView12 = findViewById(R.id.textViewDetailListView1);
        textView13 = findViewById(R.id.textViewDetailListView2);
        textView14 = findViewById(R.id.textViewDot);
        textView15 = findViewById(R.id.textViewDot1);

        Intent intent = getIntent();
        title.setText("Detail Pinjaman");
        textView1.setText("Rp. "+intent.getStringExtra("denom"));
        textView2.setText(intent.getStringExtra("bunga")+"%");
        textView3.setText(intent.getStringExtra("tempo")+" Bulan");
        textView4.setVisibility(View.INVISIBLE);
        textView5.setVisibility(View.INVISIBLE);
        textView6.setVisibility(View.INVISIBLE);
        textView7.setVisibility(View.INVISIBLE);
        textView8.setVisibility(View.INVISIBLE);
        textView9.setVisibility(View.INVISIBLE);

        textView11.setText("Tempo Pinjaman");
        textView12.setText("Denom Pinjaman");
        textView13.setText("Bunga Pinjaman");

    }

    private void bottomNavigationClick() {
        BottomNavigationView bottomNavigationView ;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                Fragment fragment = null;
                Bundle bundle = new Bundle();
                bundle.putString("access_token", accessToken);
                bundle.putString("username", username);
                bundle.putInt("id_anggota", id);

                switch (item.getItemId()) {
                    case R.id.navigation_dashboard:
                        intent = new Intent(DetailListDataPinjamanActivity.this, NavigateDrawerActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("access_token", accessToken);
                        intent.putExtra("id_anggota", id);
                        startActivity(intent);
                        fragment = new HomeFragment();
                        fragment.setArguments(bundle);
                        break;
//                    case R.id.navigation_pinjaman:
//                        relativeLayout = findViewById(R.id.detailListView);
//                        relativeLayout.removeView(title);
//                        mToolbar.setTitle("Pinjaman");
//                        relativeLayout.removeView(textView1);
//                        relativeLayout.removeView(textView2);
//                        relativeLayout.removeView(textView12);
//                        relativeLayout.removeView(textView13);
//                        relativeLayout.removeView(textView14);
//                        relativeLayout.removeView(textView15);
//
//                        fragment = new PinjamanBottomFragment();
//                        fragment.setArguments(bundle);
//                        break;
                    case R.id.navigation_profile:

                        relativeLayout = findViewById(R.id.detailListView);
                        relativeLayout.removeView(title);
                        mToolbar.setTitle("Profile");
                        title.setText("Data Diri ");
                        relativeLayout.removeView(textView1);
                        relativeLayout.removeView(textView2);
                        relativeLayout.removeView(textView12);
                        relativeLayout.removeView(textView13);
                        relativeLayout.removeView(textView14);
                        relativeLayout.removeView(textView15);
                        fragment = new SettingBottomFragment();
                        fragment.setArguments(bundle);
                        break;

                }
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
