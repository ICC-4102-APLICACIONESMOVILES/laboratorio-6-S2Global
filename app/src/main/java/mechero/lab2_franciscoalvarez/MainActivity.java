package mechero.lab2_franciscoalvarez;

import android.arch.persistence.room.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private static final String DATABASE_NAME = "poll_db";
    private FormDatabase formDatabase;
    public static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String email = prefs.getString("Email",null);
        String password = prefs.getString("Password",null);
        Button btn = (Button) findViewById(R.id.button);
        TextView mail = (TextView) findViewById(R.id.fin1);
        if (email == null && password == null) {
            btn.setText("Log Out");
            loginGo();
        }
        else {
            btn.setText("clear");
            mail.setText(email);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        formDatabase = Room.databaseBuilder(getApplicationContext(), FormDatabase.class, DATABASE_NAME).build();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        switch (menuItem.getItemId()) {

                            case R.id.nav_camera:
                                FragmentForm fragmentForm = new FragmentForm();
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentForm).commit();
                                mDrawerLayout.closeDrawers();
                                return true;

                            case R.id.nav_gallery:
                                FormList form2 = new FormList();
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, form2).commit();
                                mDrawerLayout.closeDrawers();
                                return true;

                            case R.id.nav_slideshow:
                                FragmentForm fragmentForm3 = new FragmentForm();
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentForm3).commit();
                                mDrawerLayout.closeDrawers();
                                return true;
                        }

                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }

                }
        );

    }

    public void savePress(View view){
        EditText name = (EditText) view.getRootView().findViewById(R.id.new_name);
        EditText date = (EditText) view.getRootView().findViewById(R.id.new_date);
        Spinner category = (Spinner) view.getRootView().findViewById(R.id.new_cat);
        EditText comment = (EditText) view.getRootView().findViewById(R.id.new_com);

        final String nameFin = name.getText().toString();
        final String dateFin = date.getText().toString();
        final String catFin = category.getSelectedItem().toString();
        final String comFin = comment.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Form form =new Form();
                form.setName(nameFin);
                form.setDate(dateFin);
                form.setCategory(catFin);
                form.setComment(comFin);
                formDatabase.daoAccess().insertOnlySinglePoll (form);
            }
        }) .start();

    }

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    public void LogButton(View v){
        String email = prefs.getString("Email",null);
        String password = prefs.getString("Password",null);
        Button btn = (Button) findViewById(R.id.button);
        if (email != null && password != null) {
            clear(v);
            btn.setText("Log In");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    formDatabase.daoAccess().nukeForms();
                }
            }) .start();
        }
        else{
            loginGo();
            btn.setText("Log Out");
        }
    }

    public void clear(View v){
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.remove("Email");
        editor.remove("Password");
        editor.remove("Token");
        TextView mail = (TextView) findViewById(R.id.fin1);
        mail.setText("Not Logged In");
        editor.apply();

    }

    public void loginGo(){
        TextView mail = (TextView) findViewById(R.id.fin1);
        Intent data = new Intent(this, Login.class);
        data.putExtra("Email", "example@mail.com");
        data.putExtra("Password", "0000");
        data.putExtra("Token", "abc123");
        startActivityForResult(data,20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 20) {
            TextView mail = (TextView) findViewById(R.id.fin1);
            mail.setText(data.getExtras().getString("Email"));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}