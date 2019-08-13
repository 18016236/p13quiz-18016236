package sg.edu.rp.soi.c346p13quiz;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rgChoice;
    RadioButton rbOk;
    RadioButton rbNoOk;
    private BroadcastReceiver iR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgChoice = findViewById(R.id.radiogroup);
        rbOk = findViewById(R.id.msg1);
        rbNoOk = findViewById(R.id.msg2);
        iR = new imageReceiver();

        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction("android.intent.action.WALLPAPER_CHANGED");
        this.registerReceiver(iR,filter);

    }
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        int id = rgChoice.getCheckedRadioButtonId();
        prefEdit.putInt("",id);
        Toast.makeText(getApplicationContext(),"You have changed the wallpaper",Toast.LENGTH_SHORT).show();

        prefEdit.commit();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(iR);
    }
}
