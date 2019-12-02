package com.example.shoppingcartnew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView txt1 , txt2, txt3, price1, price2, price3, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent it=getIntent();

        int val[]=it.getIntArrayExtra("values");

        for(int i=0;i<val.length;i++)
        {
            Log.w("Activity","Values: "+val[i]);
        }

        txt1=findViewById(R.id.textView10);
        txt1.setText(val[0]+"");

        txt2=findViewById(R.id.textView11);
        txt2.setText(val[1]+"");

        txt3=findViewById(R.id.textView12);
        txt3.setText(val[2]+"");


        price1 = findViewById(R.id.textView13);
        price1.setText("$"+val[0] *1.29 +"");

        price2 = findViewById(R.id.textView14);
        price2.setText("$"+val[1] *2.34 +"");

        price3 = findViewById(R.id.textView15);
        price3.setText("$"+val[2] *1.15 +"");



        total = findViewById(R.id.textView17);

        //total.setText("$" + Float.parseFloat(price1.getText().toString()) + Float.parseFloat(price2.getText().toString())+ Float.parseFloat(price3.getText().toString())+"");

    }
}
