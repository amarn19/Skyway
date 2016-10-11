package com.example.admin.skyway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class BookComplaint extends AppCompatActivity {
String complaint1="";
    String details1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_complaint);
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
    public void getdata(View v)
    {
        EditText nm2 = (EditText)findViewById(R.id.nm);
        EditText ad2 = (EditText)findViewById(R.id.address);
        EditText ph2 = (EditText)findViewById(R.id.phone_no);
        EditText alt2 = (EditText)findViewById(R.id.altphone_no);

        EditText tent2 = (EditText)findViewById(R.id.tent);
        CheckBox f=(CheckBox)findViewById(R.id.five) ;
        CheckBox s=(CheckBox)findViewById(R.id.seven) ;
        CheckBox o=(CheckBox)findViewById(R.id.others) ;

        String name = nm2.getText().toString();
        String addr = ad2.getText().toString();
        String phno = ph2.getText().toString();
        String altno = alt2.getText().toString();
        String tentof = tent2.getText().toString();
        if(f.isChecked())
        {
            complaint1+=f.getText().toString();
        }
        if(s.isChecked())
        {
            complaint1+=s.getText().toString();
        }
        if(o.isChecked())
        {
            complaint1+=o.getText().toString();
        }

        details1+="\nName:"+name+"\nAddress :"+addr+"\nPhone no:"+phno+"\nAlternate no:"+altno+"\nTenent of:"+tentof+"\nComplaint Type:"+complaint1;
    }
    public void submit(View v)
    {
        getdata(v);
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"amarn19@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Complaint Details");
        email.putExtra(Intent.EXTRA_TEXT,details1);
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
details1="";
        complaint1="";
    }
}
