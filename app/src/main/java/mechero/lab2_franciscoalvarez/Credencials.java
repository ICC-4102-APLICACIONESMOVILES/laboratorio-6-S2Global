package mechero.lab2_franciscoalvarez;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Francisco Alvarez on 20-Mar-18.
 */

public class Credencials {

    private Context context;

    public Credencials(Context context){
        this.context = context;
    }

    public void setCreds(int requestCode, int resultCode, Intent data, String MY_PREFS_NAME){
        if (resultCode == RESULT_OK && requestCode == 20) {
            SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("Email", data.getExtras().getString("Email"));
            editor.putString("Password", data.getExtras().getString("Password"));
            editor.putString("Token", data.getExtras().getString("Token"));
            editor.apply();

            String email = MainActivity.prefs.getString("Email",null);
            String password = MainActivity.prefs.getString("Password",null);
            String token = MainActivity.prefs.getString("Token",null);
            Toast.makeText(this.context,"Log In Successful.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this.context,"Log In Failure.", Toast.LENGTH_SHORT).show();
        }
    }

}
