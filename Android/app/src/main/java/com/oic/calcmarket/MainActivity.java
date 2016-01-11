package com.oic.calcmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.oic.calcmarket.common.views.residemenu.ResideMenu;
import com.oic.calcmarket.common.views.residemenu.ResideMenuItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String titles[] = {"Shopping", "History", "Settings"};
    public static final int icon[] = {R.drawable.ic_menu_home, R.drawable.ic_menu_profile, R.drawable.ic_menu_setting};

    ResideMenu resideMenu;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        resideMenu = new ResideMenu(this);

        // Set up your ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.toggle();
            }
        });

        setupResideMenu();
    }

    private void setupResideMenu() {
        // attach to current activity;
        resideMenu.setBackground(R.drawable.ic_menu_bg);
        resideMenu.attachToActivity(this);

        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        // create menu items;
        for (int i = 0; i < titles.length; i++) {
            ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i]);
            item.setTag(titles[i]);
            item.setOnClickListener(this);
            resideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT);
        }
    }

    @Override
    public void onClick(View v) {
        Log.e("TAG", v.getTag().toString());
        if (v.getTag().toString().equalsIgnoreCase(titles[0])) {  // home
        } else if (v.getTag().toString().equalsIgnoreCase(titles[1])) { //profile

        } else if (v.getTag().toString().equalsIgnoreCase(titles[2])) { //setting

        }
        resideMenu.toggle();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuSubmit) {
            Log.e("TAG", "submit clicked");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
