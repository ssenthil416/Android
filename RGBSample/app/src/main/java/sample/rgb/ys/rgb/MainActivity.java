package sample.rgb.ys.rgb;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnTouchListener {

    private Spinner redSpinner, greenSpinner, blueSpinner;
    private LinearLayout frameLayout;
    private View circle;
    ArrayList<String> color, red, blue, green;
    int[] numberOfValues;
    ArrayAdapter<String> redValues;
    ArrayAdapter<String> greenValues;
    ArrayAdapter<String> blueValues;
    ColorHelper colorHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redSpinner = (Spinner) findViewById(R.id.spinnerForRed);
        greenSpinner = (Spinner) findViewById(R.id.spinnerForGreen);
        blueSpinner = (Spinner) findViewById(R.id.spinnerForBlue);

        circle = (View) findViewById(R.id.circle);
        colorHelper = new ColorHelper();

        initColor();
        red = getColorValues();
        blue = getColorValues();
        green = getColorValues();
        // Create an ArrayAdapter using the string array and a default spinner layout
        redValues = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, red);
        greenValues = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, green);
        blueValues = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, blue);
        // Apply the adapter to the spinner
        redSpinner.setAdapter(redValues);
        greenSpinner.setAdapter(greenValues);
        blueSpinner.setAdapter(blueValues);
        redSpinner.setOnItemSelectedListener(this);
        greenSpinner.setOnItemSelectedListener(this);
        blueSpinner.setOnItemSelectedListener(this);
    }

    private void initColor() {
        red = new ArrayList<>();
        blue = new ArrayList<>();
        green = new ArrayList<>();
    }


    private ArrayList<String> getColorValues() {
        color = new ArrayList<>();

        numberOfValues = new int[255];
        for (int a = 0; a < numberOfValues.length; a++) {
            color.add(String.valueOf(a));
        }
        return color;
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


    boolean userSelect = false;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        userSelect = true;
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        if (userSelect) {
            userSelect = false;
        }
        switch (adapterView.getId()) {
            case R.id.spinnerForRed:
                colorHelper.setValueForRed(Integer.parseInt(red.get(position)));
                Log.e("COLOR-red", "RED" + colorHelper.getValueForRed());
                Log.e("COLOR-red", "GREEN" + colorHelper.getValueForGreen());
                Log.e("COLOR-red", "BLUE" + colorHelper.getValueForBlue());
                changeColor();

                break;

            case R.id.spinnerForGreen:
                colorHelper.setValueForGreen(Integer.parseInt(green.get(position)));
                Log.e("COLOR-green", "RED" + colorHelper.getValueForRed());
                Log.e("COLOR-green", "GREEN" + colorHelper.getValueForGreen());
                Log.e("COLOR-green", "BLUE" + colorHelper.getValueForBlue());
                changeColor();

                break;
            case R.id.spinnerForBlue:
                colorHelper.setValueForBlue(Integer.parseInt(blue.get(position)));
                Log.e("COLOR-b", "RED" + colorHelper.getValueForRed());
                Log.e("COLOR-b", "GREEN" + colorHelper.getValueForGreen());
                Log.e("-b", "BLUE" + colorHelper.getValueForBlue());
                changeColor();

                break;
            default:
//                getColor = android.graphics.Color.rgb(valueForRed, valueForGreen, valueForBlue);
//                circle.setBackgroundColor(getColor);
                break;

        }
    }

    private void changeColor() {
        int getColor = android.graphics.Color.rgb(colorHelper.getValueForRed(), colorHelper.getValueForGreen(), colorHelper.getValueForBlue());
        String hex = String.format("#%02x%02x%02x", colorHelper.getValueForRed(), colorHelper.getValueForGreen(), colorHelper.getValueForBlue());
        Log.d("COLOR",hex);
        ((GradientDrawable)circle.getBackground()).setColor(getColor);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}