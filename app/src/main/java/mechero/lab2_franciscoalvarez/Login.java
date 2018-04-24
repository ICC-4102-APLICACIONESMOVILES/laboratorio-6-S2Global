package mechero.lab2_franciscoalvarez;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import static mechero.lab2_franciscoalvarez.MainActivity.MY_PREFS_NAME;

public class Login extends AppCompatActivity {

    boolean ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        networkManager = NetworkManager.getInstance(this);
    }

    private NetworkManager networkManager;

    public void onLoginClick(View view){

        TextView a = (TextView) findViewById(R.id.Email);
        TextView b = (TextView) findViewById(R.id.Pass);

        try {
            final Activity currAct = this;
            networkManager.login(a.getText().toString(), b.getText().toString(), new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    getForms();
                    JSONObject headers = response.optJSONObject("headers");
                    String token = headers.optString("Authorization", "");
                    Intent data = new Intent();
                    TextView a = (TextView) findViewById(R.id.Email);
                    TextView b = (TextView) findViewById(R.id.Pass);
                    data.putExtra("Email", a.getText().toString());
                    data.putExtra("Password", b.getText().toString());
                    data.putExtra("Token", token);
                    setResult(RESULT_OK, data);
                    Credencials creds = new Credencials(currAct);
                    User usuario = new User();
                    usuario.setEmail(a.getText().toString());
                    usuario.setPassword(b.getText().toString());
                    creds.setCreds(20,RESULT_OK, data, MY_PREFS_NAME);
                    currAct.finish();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error
                    System.out.println(error);
                    Toast.makeText(currAct, "Error in Email or Password", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getForms(){
        networkManager.getForms(new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                System.out.println(error);
            }
        });
    }

}
