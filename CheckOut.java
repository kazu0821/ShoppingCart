package com.zyxe.shopping;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckOut extends AppCompatActivity {
    private static final String TAG = "SAM";

    TextView sub;
    TextView shipping;
    TextView tax;
    TextView sum;
    TextView messageLog_TextView;
    Button sendtoserver_Button;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_out);

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

        sendtoserver_Button = findViewById(R.id.send_to_server_Button );
        sendtoserver_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity();
            }
        });

        sub.setText("$"+subtotal+"");
        shipping.setText("$20");
        tax.setText("$"+Tax+"");
        float total= (float)(subtotal+(Tax)+20.0);

        sum.setText("$"+total+"");
    }



    public void startNewActivity()
    {
        Log.i(TAG, "startNewActivity");
        Intent intent = new Intent( this, SendServer.class);
        intent.putExtra("Total", sum.getText());
        startActivity( intent );
    }

}
