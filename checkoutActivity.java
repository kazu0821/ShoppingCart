package com.example.shoppingcartnew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class checkoutActivity extends AppCompatActivity {


    TextView sub;
    TextView shipping;
    TextView tax;
    TextView sum;
    TextView messageLog_TextView;

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

        float Tax = (float)(subtotal*0.075);

        sum=findViewById(R.id.textView37);


        messageLog_TextView = findViewById(R.id.messageLog_TextView);



        sub.setText("$"+subtotal+"");
        shipping.setText("$20");
        tax.setText("$"+Tax+"");
        float total= (float)(subtotal+(Tax)+20.0);

        sum.setText("$"+total+"");

        String SERVER_IP;
        int SERVER_PORT;

        Socket socket;
        PrintWriter output;
        InputStreamReader in;
        BufferedReader br;

        try {
            socket = new Socket("10.0.0.124", 7000);
            output = new PrintWriter(socket.getOutputStream(), true);
            in = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(in);

            final String message = br.readLine();
            final String newMessage = message.replaceAll("▼", "\n");

            messageLog_TextView.setText( newMessage  );
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
