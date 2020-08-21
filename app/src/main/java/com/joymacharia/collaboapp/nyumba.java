//package com.joymacharia.collaboapp;
//
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.joymacharia.collaboapp.Fragments.AccountFragment;
//import com.joymacharia.collaboapp.Fragments.HomeFragment;
//
//
//public class nyumba extends AppCompatActivity {
//    private FragmentManager fragmentManager;
//    private FloatingActionButton fab;
//    private BottomNavigationView navigationView;
//    private static final int GALLERY_ADD_POST = 2;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//
//        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.frameHomeContainer, new HomeFragment(), HomeFragment.class.getSimpleName()).commit();
//        init();
//    }
//
//    private void init() {
//        navigationView = findViewById(R.id.bottom_nav);
//        fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(Intent.ACTION_PICK);
////                i.setType("image/*");
////                HomeActivity.this.startActivityForResult(i, GALLERY_ADD_POST);
//                Intent push = new Intent(HomeActivity.this, ViewTasksFragment.class);
//                startActivity(push);
//            }
//        });
//
//        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch (item.getItemId()) {
//                    case R.id.item_home: {
//                        Fragment account = fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName());
//                        if (account != null) {
//                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName())).commit();
//                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
//                        }
//                        break;
//                    }
//
//                    case R.id.item_account: {
//                        Fragment account = fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName());
//                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
//                        if (account != null) {
//                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(AccountFragment.class.getSimpleName())).commit();
//                        } else {
//                            fragmentManager.beginTransaction().add(R.id.frameHomeContainer, new AccountFragment(), AccountFragment.class.getSimpleName()).commit();
//                        }
//                        break;
//                    }
//                }
//
//                return true;
//            }
//        });
//
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == GALLERY_ADD_POST && resultCode == RESULT_OK) {
//            Uri imgUri = data.getData();
//            Intent i = new Intent(HomeActivity.this, EditUserInfoActivity.class);
//            i.setData(imgUri);
//            startActivity(i);
//        }
//    }
//
//
//}
