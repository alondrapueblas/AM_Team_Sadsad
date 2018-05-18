package com.example.alondrapueblas.iexpense;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class NavBar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GridLayout mainGrid;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainGrid = (GridLayout)findViewById(R.id.maingrid);
        db = new Database(this);
        //SET EVENT
            setSingleEvent(mainGrid);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // for Click Event of Grid layout

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all children of grid
        for (int i=0;i<mainGrid.getChildCount();i++){
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (finalI){
                        case 0:
                            Intent i0 = new Intent(NavBar.this, Income_Act.class);
                            startActivity(i0);
                            break;
                        case 1:
                            Intent i1 = new Intent(NavBar.this, ExpensesActivity.class);
                            startActivity(i1);
                            break;
                        case 2:
                            Intent i2 = new Intent(NavBar.this, BillsActivity.class);
                            startActivity(i2);
                            break;
                        case 3:
                            Intent i3 = new Intent(NavBar.this, BudgetActivity.class);
                            startActivity(i3);
                            break;
                    }
                }
            });

        }
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "About" , Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_income) {
            Intent i = new Intent(NavBar.this, Income_Act.class);
            startActivity(i);
        } else if (id == R.id.nav_expenses) {
            Intent i = new Intent(NavBar.this, ExpensesActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_bills) {
            Intent i = new Intent(NavBar.this, BillsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_budget) {
            Intent i = new Intent(NavBar.this, BudgetActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
