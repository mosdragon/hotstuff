package es.sakhi.osama.hotstuff;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity {

    private boolean started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void pushToStart(View v) {
        Button button = (Button) v;
        if (!started) {
            button.setText("Starting Your Car");
            //CarStartService.startCar(this);
            //button.setText("Push to Stop");
        } else {
            button.setText("Push to Start");
//            Stopping car
            CarStartService.stopCar(this);
           // button.
//            deselect radio button

        }

        started = !started;
    }

    public void goToConfiguationScreen(View v) {
        Intent intent = new Intent(this, ConfigurationActivity.class);
        startActivity(intent);
    }
}
