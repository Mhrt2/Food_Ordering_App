package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodorderingapp.Adapters.MainAdapter;
import com.example.foodorderingapp.Models.MainModel;
import com.example.foodorderingapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    MainAdapter adapter;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Binding is used instead of findVIewById
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        // Custom Toolbar
        Toolbar customToolbar = binding.mainCustomToolbar.customToolbar;
        // OR
        // Toolbar customToolbar = findViewById(R.id.mainCustomToolbar);
        setSupportActionBar(customToolbar);
        // OR
        // setSupportActionBar(binding.mainCustomToolbar.customToolbar);


        // Setting title of custom toolbar
        getSupportActionBar().setTitle("Food Ordering App");



        /*     Navigation Drawer Menu     */



        // Adding toggle button to the left side of toolbar which will open and close custom navigation drawer
        toggle = new ActionBarDrawerToggle(MainActivity.this, binding.drawerLayout, customToolbar, R.string.open_nav_drawer, R.string.open_nav_drawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        // By default Home will remain selected in navigation menu
        binding.navDrawer.setCheckedItem(R.id.menuHome);


        // This function is used to show what will happen after selecting any item from navigation drawer
        binding.navDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {

                    case R.id.menuHome:
                        break;

                    case R.id.menuMyOrders:
                        startActivity(new Intent(MainActivity.this, OrderActivity.class));
                        break;
                    case R.id.menuLogout:
                        Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(in);
                        finish();
                        break;

                    default:

                }
                // This function is used to close navigation drawer whenever any one item is selected from the navigation drawer
                binding.drawerLayout.closeDrawers();
                return true;

            }
        });



        // Following code shows how items are added in recycler view of main activity
        // Here items are added from the ArrayList as shown below
        ArrayList<MainModel> list = new ArrayList<>();


        // Here we are adding items in the array list
        // Here each time when items are added in the list the MainModel constructor is called where items are initialised
        list.add(new MainModel(R.drawable.beef, "Beef", "5", "Beef with a little soup"));
        list.add(new MainModel(R.drawable.tehari, "Tehari", "12", "Delicious tehari"));
        list.add(new MainModel(R.drawable.sweet, "Sweet", "4", "Sweet increase stamina"));
        list.add(new MainModel(R.drawable.hilsa, "Hilsa fry", "5", "There are hilsa Fry with biriany and chilli on it"));
        list.add(new MainModel(R.drawable.dalvat, "Dalvat", "7", "Launch time meal"));
        list.add(new MainModel(R.drawable.burger, "Burger", "9", "Burger with extra cheese"));
        list.add(new MainModel(R.drawable.friedrice, "Fried rice", "3", "Pork fried rice with eggs"));
        list.add(new MainModel(R.drawable.pantavat, "Pantavat", "4", "Pahela Baishakh festival events"));
        list.add(new MainModel(R.drawable.chicken, "Chicken", "10", "Chicken with vegetables and potato"));
        list.add(new MainModel(R.drawable.samosa, "Samosa", "8", "Samosa with red and green chutney"));


        // Setting adapter on the recycler view
        adapter = new MainAdapter(list,this);
        binding.mainRecyclerView.setAdapter(adapter);


        // Vertical scrolling using linear layout manager
        // Setting linear layout manager on the recycler view
        // Here linear layout manager is used to vertically scroll all the items of the recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.mainRecyclerView.setLayoutManager(layoutManager);

    }



    // Menu shown at right side of the toolbar
    // This function is used to show or inflate menu in main activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_orders_and_search_bar_button, menu);
        return true;
    }

    // This function is used to show what will happen after selecting any item from menu that is at right side of toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.myOrders:
                // Move to order activity from main activity
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                break;

            case R.id.searchBar:
                // Search Bar Code
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setQueryHint("Type here to Search");
                searchView.setIconifiedByDefault(false);

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        adapter.getFilter().filter(newText);
                        return true;
                    }
                });
                break;

            default:

        }
        return true;

    }



    // This method is called when we press back button in our mobile from main activity page
    @Override
    public void onBackPressed() {

        // Here we are closing the drawer if drawer is open when we press back button in our mobile
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawers();
        }

        // Alert Dialog Box
        // It will be shown when we press back button in our mobile
        else{
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setIcon(R.drawable.warning)
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }).show();
        }

    }

}
