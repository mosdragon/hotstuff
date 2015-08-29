package es.sakhi.osama.hotstuff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;


public class ConfigurationActivity extends AppCompatActivity {

    private Switch frontWindow;
    private Switch backWindow;
    private  Switch autoDefrost;
    private  SeekBar temp;
    private  Switch autoTemp;
    private Switch heatSeats;
    private Switch coolSeats;
    private RadioGroup gasOptions;
    private RadioButton cheapGas;
    private RadioButton closeGas;
    private RadioButton noGas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        frontWindow = (Switch) findViewById(R.id.Front_Window);
        frontWindow.setChecked(prefs.getBoolean(AppConstants.DEFROST_FRONT_WINDOW, false));

        backWindow = (Switch) findViewById(R.id.Rear_Window);
        backWindow.setChecked(prefs.getBoolean(AppConstants.DEFROST_BACK_WINDOW, false));

        autoDefrost = (Switch) findViewById(R.id.Auto_Defrost);
        autoDefrost.setChecked(prefs.getBoolean(AppConstants.AUTO_DEFROST, true));

        temp = (SeekBar) findViewById(R.id.Set_Temp);
        temp.setProgress(prefs.getInt(AppConstants.SET_TEMP_TO, 70));

        autoTemp = (Switch) findViewById(R.id.Auto_Temp);
        autoTemp.setChecked(prefs.getBoolean(AppConstants.AUTO_TEMP, true));

        gasOptions = (RadioGroup) findViewById(R.id.Fuel_Group);

        closeGas = (RadioButton) findViewById(R.id.close);
        cheapGas = (RadioButton) findViewById(R.id.cheap);
        noGas = (RadioButton) findViewById(R.id.Do_Not_Add);
        closeGas.setChecked(prefs.getBoolean(AppConstants.FIND_CLOSE_GAS, false));
        cheapGas.setChecked(prefs.getBoolean(AppConstants.FIND_CHEAP_GAS, false));
        noGas.setChecked(prefs.getBoolean(AppConstants.FIND_NO_GAS, true));

        heatSeats = (Switch) findViewById(R.id.Heated_Seats);
        heatSeats.setChecked(prefs.getBoolean(AppConstants.ACTIVATE_HEATED_SEATS, false));

        coolSeats = (Switch) findViewById(R.id.Cooled_Seats);
        coolSeats.setChecked(prefs.getBoolean(AppConstants.ACTIVATE_COOLED_SEATS, false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configuration, menu);
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

    public void saveConfigs(View v) {
        //Defrost
        //Front Window
        boolean shouldFrontWindow = frontWindow.isChecked();
        //Back Window
        boolean shouldBackWindow = backWindow.isChecked();
        //Auto
        boolean shouldAutoDefrost = autoDefrost.isChecked();

        // Car Temperature
        // Set Car Temperature
        int tempValue = temp.getProgress();
        //Auto Temp
        boolean shouldAutoTemp = autoTemp.isChecked();

        //Fuel
        boolean findCloseGas = false;
        boolean findCheapGas = false;
        boolean findNoGas = false;

        int optionChecked = gasOptions.getCheckedRadioButtonId();
        RadioButton buttonChecked = (RadioButton) findViewById(optionChecked);
        String buttonName = (String) buttonChecked.getText();

        if (buttonName.equals("Cheapest Within a 10 Mile Range")) {
            findCheapGas = true;
        } else if (buttonName.equals("Closest To The Route")) {
            findCloseGas = true;
        } else if (buttonName.equals("Do Not Add")) {
            findNoGas = true;
        }

        //Additional Features
        //Heated Seats
        boolean shouldHeatSeats = heatSeats.isChecked();
        //Cooled Seats
        boolean shouldCoolSeats = coolSeats.isChecked();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(AppConstants.DEFROST_FRONT_WINDOW, shouldFrontWindow);
        editor.putBoolean(AppConstants.DEFROST_BACK_WINDOW, shouldBackWindow);
        editor.putBoolean(AppConstants.AUTO_DEFROST, shouldAutoDefrost);
        editor.putInt(AppConstants.SET_TEMP_TO, tempValue);
        editor.putBoolean(AppConstants.AUTO_TEMP, shouldAutoTemp);
        editor.putBoolean(AppConstants.FIND_CHEAP_GAS, findCheapGas);
        editor.putBoolean(AppConstants.FIND_CLOSE_GAS, findCloseGas);
        editor.putBoolean(AppConstants.FIND_NO_GAS, findNoGas);
        editor.putBoolean(AppConstants.ACTIVATE_HEATED_SEATS, shouldHeatSeats);
        editor.putBoolean(AppConstants.ACTIVATE_COOLED_SEATS, shouldCoolSeats);
        editor.commit();

//        beginServices();
        finish();

    }

    private void beginServices() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        CarDefrostService defrostService = new CarDefrostService();
        CarDefrostService.startDefrostService(this);


//        if (!(prefs.getBoolean(AppConstants.DEFROST_FRONT_WINDOW, false)) && !(prefs.getBoolean(AppConstants.AUTO_DEFROST, false)) &&
//                !(prefs.getBoolean(AppConstants.DEFROST_BACK_WINDOW, false))) {
//            CarDefrostService.startDefrostService(this);
//        }
    }
}
