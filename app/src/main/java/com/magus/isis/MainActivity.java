package com.magus.isis;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.magus.isis.net.res.FavoritesRes;
import com.magus.isis.net.res.MenuRes;
import com.magus.isis.ui.DashboardLayout;

import org.slf4j.LoggerFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuRes menu = (MenuRes)getIntent().getSerializableExtra("menu");
        FavoritesRes favorites = (FavoritesRes)getIntent().getSerializableExtra("favorites");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        System.out.println("获得dashboardLayout...");
        DashboardLayout dashboardLayout = (DashboardLayout) findViewById((R.id.main_dashboard));
        Logger.warn("获得dashboardLayout",dashboardLayout);



        for (int i =0;i<15;i++){
            final Button button = new Button(this,null,R.style.DashboardButton);
            button.setText("abc我是谁");
            button.setTop(R.drawable.diagram);
            final Drawable drawableTop = getResources().getDrawable(R.drawable.diagram);
            button.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop , null, null);
            dashboardLayout.addView(button);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Logger.warn("onOptionsItemSelected");
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Logger.warn("退出菜单");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_diagram) {

        } else if (id == R.id.nav_trend) {

        } else if (id == R.id.nav_settings) {
            Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_report) {

        } else if (id == R.id.nav_video) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
