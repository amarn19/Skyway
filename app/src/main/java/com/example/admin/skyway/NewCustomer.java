package com.example.admin.skyway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class NewCustomer extends AppCompatActivity {

    String payment="";
    String details="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);

        //////////////////////////////////////////////////////////////////////////////
        Spinner spinner = (Spinner) findViewById(R.id.documents_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.documents_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        ///////////////////////////////////////////////////////////////////////////////////////////////
        EditText nm1 = (EditText)findViewById(R.id.nm);
        EditText ad1 = (EditText)findViewById(R.id.address);
        EditText ph1 = (EditText)findViewById(R.id.phone_no);
        EditText alt1 = (EditText)findViewById(R.id.altphone_no);
        EditText tent1 = (EditText)findViewById(R.id.tent);
        CheckBox m=(CheckBox)findViewById(R.id.monthly) ;
        CheckBox y=(CheckBox)findViewById(R.id.full) ;
        CheckBox h=(CheckBox)findViewById(R.id.half) ;
        Spinner mySpinner = (Spinner)findViewById(R.id.documents_spinner);

        String doc = mySpinner.getSelectedItem().toString();
        String name = nm1.getText().toString();
        String addr = ad1.getText().toString();
        String phno = ph1.getText().toString();
        String altno = alt1.getText().toString();
        String tentof = tent1.getText().toString();
        if(m.isChecked())
        {
            payment+=m.getText().toString();
        }
        if(h.isChecked())
        {
            payment+=h.getText().toString();
        }
        if(y.isChecked())
        {
            payment+=y.getText().toString();
        }

        details+="\nName:"+name+"\nAddress"+addr+"\nPhone no:"+phno+"\nAlternate no:"+altno+"\nTenent of:"+tentof+"\ndocuments:"+doc+"\nPayment Mode:"+payment;
    }

    public void submit(View v)
    {
        {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"amarn19@gmail.com"});
            email.putExtra(Intent.EXTRA_SUBJECT, "orderSummary");
            email.putExtra(Intent.EXTRA_TEXT,details);
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Choose an Email client :"));
        }


    }

}
