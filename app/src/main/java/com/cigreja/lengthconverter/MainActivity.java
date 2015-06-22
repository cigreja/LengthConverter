package com.cigreja.lengthconverter;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Spinner convertFromUnitTypeSpinner;
    Spinner convertToUnitTypeSpinner;
    // this array is for the spinner items (the unit types for selection)
    // it is also arranged in alphabetical order
    String[] unitTypes = {"centimeters","feet","inches","kilometers","meters","miles","millimeters","yards"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set spinner variables to the corresponding spinner components
        convertFromUnitTypeSpinner = (Spinner)findViewById(R.id.convertFromUnitTypeSpinner);
        convertToUnitTypeSpinner = (Spinner)findViewById(R.id.convertToUnitTypeSpinner);

        // set up an adapter for the layout of the spinner,
        // and connect the string array of unit type items
        // simple_spinner_item were too small; I changed to list item 1
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, unitTypes);

        // now set the adapter to the spinners
        convertFromUnitTypeSpinner.setAdapter(spinnerAdapter);
        convertToUnitTypeSpinner.setAdapter(spinnerAdapter);

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
            if (id == R.id.action_exit) {
                finish();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    public void onClickConvertButtonEvent(View view){

        // declare variables
        double beginning_qty;
        double ending_qty;
        String beginning_unit_type;
        String ending_unit_type;

        // declare components
        EditText qtyEditText = (EditText)findViewById(R.id.qtyEditText);
        TextView resultOutputTextView = (TextView)findViewById(R.id.resultOutputTextView);

        // create object for length conversion
        Length_Conversion lengthConverter = new Length_Conversion();

        // check that the quantity field has been entered
        if(!qtyEditText.getText().toString().equals("")) {

            // get input user data from fields
            beginning_qty = Double.parseDouble(qtyEditText.getText().toString());
            beginning_unit_type = convertFromUnitTypeSpinner.getSelectedItem().toString();
            ending_unit_type = convertToUnitTypeSpinner.getSelectedItem().toString();

            // input data into object
            lengthConverter.setBeginning_qty(beginning_qty);
            lengthConverter.setBeginning_unit_type(beginning_unit_type);
            lengthConverter.setEnding_unit_type(ending_unit_type);

            // calculate the conversion result
            ending_qty = lengthConverter.calculateEnding_qty();
            lengthConverter.setEnding_qty(ending_qty);

            // output the conversion result to console
            resultOutputTextView.setText(lengthConverter.toString());

        } else {

            // create a toast message to enter quantity
            Context context = getApplicationContext();
            CharSequence text = "Enter Quantity";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
