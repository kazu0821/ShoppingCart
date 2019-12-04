package com.example.shoppingcartnew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class checkoutActivity extends AppCompatActivity {


    TextView sub;
    TextView shipping;
    TextView tax;
    TextView sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent it=getIntent();

        float subtotal;
        subtotal=it.getExtras().getFloat("total");

        Log.d("Activity","DataStream: "+subtotal);

        sub=findViewById(R.id.textView28);

        shipping=findViewById(R.id.textView34);
        tax=findViewById(R.id.textView35);

        sum=findViewById(R.id.textView37);

        sub.setText("$"+subtotal+"");
        shipping.setText("$20");
        tax.setText("7.5%");
        float total= (float)(subtotal+(subtotal*0.075)+20.0);

        sum.setText("$"+total+"");
    }
}
