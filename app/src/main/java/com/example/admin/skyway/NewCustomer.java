package com.example.admin.skyway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class NewCustomer extends AppCompatActivity {

    private EditText nm1;
    private EditText ad1;
    private EditText ph1;
    private EditText alt1;
    private EditText tent1;
    private CheckBox m;
    private CheckBox y;
    private CheckBox h;
    private Spinner spinner;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);
        initUi();
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.documents_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(getApplicationContext(), About.class));
                return true;
//            case R.id.help:
//                showHelp();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void initUi() {

        nm1 = (EditText) findViewById(R.id.nm);
        ad1 = (EditText) findViewById(R.id.address);
        ph1 = (EditText) findViewById(R.id.phone_no);
        alt1 = (EditText) findViewById(R.id.altphone_no);
        tent1 = (EditText) findViewById(R.id.tent);
        m = (CheckBox) findViewById(R.id.monthly);
        y = (CheckBox) findViewById(R.id.full);
        h = (CheckBox) findViewById(R.id.half);
        spinner = (Spinner) findViewById(R.id.documents_spinner);
    }

    public String getdata() {

        final StringBuilder paymentStringBuilder = new StringBuilder();
        if (m.isChecked()) paymentStringBuilder.append(m.getText().toString());
        if (h.isChecked()) paymentStringBuilder.append(h.getText().toString());
        if (y.isChecked()) paymentStringBuilder.append(y.getText().toString());
        return "\nName: " + nm1.getText().toString() +
                "\nAddress: " + ad1.getText().toString() +
                "\nPhone No.: " + ph1.getText().toString() +
                "\nAlternate No.: " + alt1.getText().toString() +
                "\nTenant of: " + tent1.getText().toString() +
                "\nDocuments: " + spinner.getSelectedItem().toString() +
                "\nPayment Mode: " + paymentStringBuilder.toString();
    }

    public void submit(final View v) {
        final Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"amarn19@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "NewCustomerDetails");
        email.putExtra(Intent.EXTRA_TEXT, getdata());
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
}
