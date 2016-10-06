package com.example.admin.skyway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button bn = (Button) findViewById(R.id.cus);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(getApplicationContext(), NewCustomer.class));
            }
        });

        Button bn1=(Button)findViewById(R.id.bkcp);
        bn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),BookComplaint.class);
                startActivity(i);//starting new activity
            }
        });
        Button bn2=(Button)findViewById(R.id.pr);
        bn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Products.class);
                startActivity(i);//starting new activity
            }
        });
    }

}

