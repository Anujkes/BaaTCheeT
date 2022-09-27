package com.example.baatcheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.baatcheet.Adapter.fragmentAdapter;
import com.google.android.gms.common.internal.Objects;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth=FirebaseAuth.getInstance();

   ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager=findViewById(R.id.viewPager);
        tabLayout=findViewById(R.id.tabLayout);
       viewPager.setAdapter(new fragmentAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater=getMenuInflater() ;
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       switch (item.getItemId())
       {
           case R.id.setting:
               Toast.makeText(this, "Setting clicked", Toast.LENGTH_SHORT).show();
               break;

           case R.id.logout:
               auth.signOut();
               startActivity(new Intent(MainActivity.this,SignInActivity.class));
               break;


       }
        return super.onOptionsItemSelected(item);
    }













}