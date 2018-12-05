package com.thangnc.insurancemanagement;

import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.thangnc.insurancemanagement.database.DatabaseHelper;
import com.thangnc.insurancemanagement.fragment.CustomerFragment;
import com.thangnc.insurancemanagement.fragment.InvoiceFragment;
import com.thangnc.insurancemanagement.fragment.StatisticFragment;
import com.thangnc.insurancemanagement.sqlitedao.InvoiceDAO;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView btnNav;
    private CustomerFragment fragmentCustomer;
    private InvoiceFragment fragmentInvoice;
    private StatisticFragment fragmentStatistic;
    private InvoiceDAO invoiceDAO;
    private DatabaseHelper databaseHelper;


    final String DATABASE_NAME = "CustomerDB.sqlite";
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = Database.initDatabase(this, DATABASE_NAME);
        btnNav = findViewById(R.id.btnNav);
        fragmentCustomer = new CustomerFragment();
        fragmentStatistic = new StatisticFragment();
        fragmentInvoice = new InvoiceFragment();
        databaseHelper = new DatabaseHelper(this);
        invoiceDAO = new InvoiceDAO(databaseHelper);

        for (int i = 0; i < invoiceDAO.getAllInvoice().size(); i++) {
            long date = invoiceDAO.getAllInvoice().get(i).getDate();
            long dateEnd = date + 30000;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Notification.Builder mBuilder = new Notification.Builder(HomeActivity.this);
                    mBuilder.setContentTitle("Tin nhắn mới");
                    mBuilder.setContentText("Có điểm thực hành môn Android.");
                    mBuilder.setTicker("Thông báo!"); mBuilder.setSmallIcon(R.drawable.ic_exit_to_app_black_24dp);



                    Log.e("timer", "Thuc hien cong viec!");
                }
            }, dateEnd);
            break;
        }


        showFragment(fragmentStatistic);
        btnNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.customer:
                        showFragment(fragmentCustomer);
                        return true;
                    case R.id.invoice:
                        showFragment(fragmentInvoice);
                        return true;
                    case R.id.statistic:
                        showFragment(fragmentStatistic);
                        return true;
                }
                return false;
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
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
        getMenuInflater().inflate(R.menu.home, menu);
        MenuItem item = menu.findItem(R.id.action_settings);
        SearchView searchView = (SearchView) item.getActionView();

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        switch (item.getItemId()) {
            case R.id.nav_user:
                startActivity(new Intent(HomeActivity.this, AddCusActivity.class));
                break;
            case R.id.nav_invoice:
                startActivity(new Intent(HomeActivity.this, AddInvoiceActivity.class));
                break;
            case R.id.nav_manage:
                startActivity(new Intent(HomeActivity.this, ManageActivity.class));
                break;
            case R.id.nav_exit:
                Exit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void Exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Do you want to exit ?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }
}
