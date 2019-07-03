package reader.adapter.android.obd.prof.alc40;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import reader.adapter.android.obd.prof.alc40.util.PreferencesKeys;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isFirestLogin();
    }


    private void isFirestLogin(){
        Log.d(TAG, "isFirstLogin: checking if this is first login.");

        final SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstLogin=preferences.getBoolean(PreferencesKeys.FIRST_TIME_LOGIN, true);

        if (isFirstLogin){
            Log.d(TAG, "isFirstLogin: launching Alert Dialogue.");

            AlertDialog.Builder alertDialogueBuilder=new AlertDialog.Builder(this);
            alertDialogueBuilder.setMessage(getString(R.string.first_time_user_message));
            alertDialogueBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d(TAG, "onClick: closing Dialogue");

                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putBoolean(PreferencesKeys.FIRST_TIME_LOGIN, false);
                    editor.commit();
                    dialog.dismiss();
                }
            });

            alertDialogueBuilder.setIcon(R.drawable.icon_notification);
            alertDialogueBuilder.setTitle(" ");
            AlertDialog alertDialog=alertDialogueBuilder.create();
            alertDialog.show();

        }
    }
}
