package com.example.shoppingcartnew;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {


    /* This is declaring the text data value,
    and this textview will show up the apps place.  */
    TextView txt1;
            TextView txt2;
    TextView txt3;
            TextView txt4;
    TextView txt5;
            TextView txt6;
            TextView txt7;

            TextView price1;
    TextView price2;
            TextView price3;
    TextView price4;
            TextView price5;
            TextView price6;
            TextView price7;

    TextView total;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*
        This parameter will calculate how much user bought each product,
         and calculate tax and shipping cost and show up the total cost fee in this parameter
        *
        * */
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

        txt4=findViewById(R.id.textView3);
        txt4.setText(val[3]+"");

        txt5=findViewById(R.id.textView22);
        txt5.setText(val[4]+"");

        price1 = findViewById(R.id.textView13);
        price1.setText("$"+val[0] *1.29 +"");

        price2 = findViewById(R.id.textView14);
        price2.setText("$"+val[1] *2.34 +"");

        price3 = findViewById(R.id.textView15);
        price3.setText("$"+val[2] *1.15 +"");

        price4 = findViewById(R.id.textView9);
        price4.setText("$"+val[3] *1.49 +"");

        price5 = findViewById(R.id.textView24);
        price5.setText("$"+val[4] *1.17 +"");


        total = findViewById(R.id.textView17);

        float tomato = Float.parseFloat(price1.getText().toString().substring(1));
        float peach = Float.parseFloat(price2.getText().toString().substring(1));
        float squash = Float.parseFloat(price3.getText().toString().substring(1));

        float orange = Float.parseFloat(price4.getText().toString().substring(1));
        float apple = Float.parseFloat(price5.getText().toString().substring(1));




        total.setText("$"+(tomato + peach+ squash+ orange+ apple)+"");

    }
}
