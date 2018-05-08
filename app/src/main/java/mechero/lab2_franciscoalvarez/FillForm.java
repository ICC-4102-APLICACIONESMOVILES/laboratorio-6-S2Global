package mechero.lab2_franciscoalvarez;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class FillForm extends AppCompatActivity {

    private FusedLocationProviderClient mFuse;
    public double lat;
    public double lon;
    public int id;
    public Form form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relleno_form);
        mFuse = LocationServices.getFusedLocationProviderClient(this);
        Bundle extras = getIntent().getExtras();
        id = getIntent().getIntExtra("id", -1);
        final TextView idF = (TextView) findViewById(R.id.formID);
        final TextView nameF = (TextView) findViewById(R.id.formNAME);
        final TextView dateF = (TextView) findViewById(R.id.formDATE);
        final TextView catF = (TextView) findViewById(R.id.formCAT);
        final TextView comF = (TextView) findViewById(R.id.formCOM);

        new Thread(new Runnable() {
            @Override
            public void run() {
                form = MainActivity.formDatabase.daoAccess().fetchOnePollbyPollId(id);
                Handler mainHandler = new Handler(getApplication().getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        nameF.setText(form.getName());
                        dateF.setText(form.getDate());
                        catF.setText(form.getCategory());
                        comF.setText(form.getComment());
                    }
                });

            }
        }).start();
    }

    public void enviar(View view) {
        final Activity currAct = this;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFuse.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    lat = location.getLatitude();
                    lon = location.getLongitude();
                }
            }
        });

        currAct.finish();
    }
}
