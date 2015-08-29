package es.sakhi.osama.hotstuff;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;


public class ConfigurationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
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
        Switch frontWindow = (Switch) findViewById(R.id.Front_Window);
        boolean shouldFrontWindow = frontWindow.isChecked();
        //Back Window
        Switch backWindow = (Switch) findViewById(R.id.Rear_Window);
        boolean shouldBackWindow = backWindow.isChecked();
        //Auto
        Switch autoDefrost = (Switch) findViewById(R.id.Auto_Defrost);
        boolean shouldAutoDefrost = autoDefrost.isChecked();

        // Car Temperature
        // Set Car Temperature
        SeekBar temp = (SeekBar) findViewById(R.id.Set_Temp);
        int tempValue = temp.getVerticalScrollbarPosition();
        //Auto Temp
        Switch autoTemp = (Switch) findViewById(R.id.Auto_Temp);
        boolean shouldAutoTemp = autoTemp.isChecked();

        //Fuel
        boolean findCloseGas = false;
        boolean findCheapGas = false;

        RadioGroup gasOptions = (RadioGroup) findViewById(R.id.Fuel_Group);
        if (gasOptions.isActivated()) {
            int optionChecked = gasOptions.getCheckedRadioButtonId();
            RadioButton buttonChecked = (RadioButton) findViewById(optionChecked);
            String buttonName = (String) buttonChecked.getText();

            if (buttonName.equals("Cheapest Within a 10 Mile Range")) {
                findCheapGas = true;
            } else if (buttonName.equals("Closest To The Route")) {
                findCloseGas = true;
            }
        }
        //Additional Features
        //Heated Seats
        Switch heatSeats = (Switch) findViewById(R.id.Heated_Seats);
        boolean shouldHeatSeats = heatSeats.isActivated();
        //Cooled Seats
        Switch coolSeats = (Switch) findViewById(R.id.Cooled_Seats);
        boolean shouldCoolSeats = coolSeats.isActivated();

        finish();

    }
}
