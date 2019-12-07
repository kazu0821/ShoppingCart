package com.zyxe.shopping;

//  Reference:
//      https://www.tutorialspoint.com/sending-and-receiving-data-with-sockets-in-android
/**
 This class

 */

//      11/20/2019  Thread 2 does not loop.
//      11/20/2019  Thread 3 creates a Thread 2.
//      11/26/2019  Toggle Connect / Disconnect Button
//      12/06/2019  Take a Bundle

//      Developed from C Drive.
//      If Error Code 3: Can't find ...Client then delete app from Android phone.
//      Don't just simple delete icon.
//      Go into applications & Uninstall.
//      11/25/2019  Idea to add Button to clear terminal.
//      11/26/2019  Does not crash.
//package com.zyxe.client;


/**
 * To receive the socket connection from the SocketServer class.
 * Continue the socket connection.
 * Receive commands from the client and relay those commands to an instance of
 * the FileSystem class.
 * Forward the response of the FileSystem class to the client via the socket
 * connection.
 @Author Kazuki Kanke
 */
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * The Client class is run from a local or remote client that will
 * connect via a socket to the SocketServer class.
 */
public class SendServer extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "SAM";
    Thread Thread1 = null;
    TextView messageLog_TextView;
    EditText message_to_server_EditText;
    Button connect_Button;
    Button send_Button;
    Spinner server_Spinner;
    String SERVER_IP;
    int SERVER_PORT;


    /**
     * This method creates the user interface.
     * @param savedInstanceState If one exists, reloads a saved instance.
     *                           due from rotating phone.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_server);
        server_Spinner = findViewById(R.id.server_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.servers, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        server_Spinner.setAdapter(adapter);
        server_Spinner.setOnItemSelectedListener( this );

        messageLog_TextView = findViewById(R.id.messageLog_TextView);
        message_to_server_EditText = findViewById(R.id.message_to_server_EditText);
        send_Button = findViewById(R.id.send_Button);
        messageLog_TextView.setMovementMethod(new ScrollingMovementMethod());
        connect_Button = findViewById(R.id.connect_Button);
        connect_Button.setText("Connect");
        connect_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (connect_Button.getText().equals("Connect"))
                {
                    messageLog_TextView.setText("");
                    connect_Button.setText("Disconnect");
                    Thread1 = new Thread(new Thread1());
                    Thread1.start();
                }
                else
                    new Thread(new Thread3("exit")).start();
            }
        });

        send_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String message = message_to_server_EditText.getText().toString().trim();
                new Thread(new Thread3(message)).start();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String message_string = extras.getString("Total");
            message_to_server_EditText.setText(message_string);
        }
    }

    Socket socket;
    PrintWriter output;
    InputStreamReader in;
    BufferedReader br;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedServer = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), selectedServer, Toast.LENGTH_LONG).show();
        Toast.makeText(parent.getContext(), Integer.toString( position ), Toast.LENGTH_LONG).show();
        //setServer(int position);
        switch(position)
        {
            case 0:
                SERVER_IP = "134.154.20.86";
                SERVER_PORT = 7000;
                break;

            case 1:
                SERVER_IP = "10.0.0.124";
                SERVER_PORT = 8070;
                break;

            case 2:
                SERVER_IP = "73.223.16.32";
                SERVER_PORT = 7000;
                break;

            case 3:
                SERVER_IP = "73.223.16.32";
                SERVER_PORT = 8070;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Creates socket connection with server.
     */
    class Thread1 implements Runnable {
        public void run() {
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                output = new PrintWriter(socket.getOutputStream());
                in = new InputStreamReader(socket.getInputStream());
                br = new BufferedReader(in);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        messageLog_TextView.setText("Connected.\n");
                    }
                });
                new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Receive Messages from Server
     */
    class Thread2 implements Runnable
    {
        @Override
        public void run()
        {
            try
            {
                final String message = br.readLine();
                final String newMessage = message.replaceAll("▼", "\n");

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        messageLog_TextView.append( newMessage + "\n");
                    }
                });
            } catch (IOException e) {
                //e.printStackTrace();
                Log.i(TAG, "IO Exception");
            }
        }
    }


    /**
     * Send messages to Server & create a new thread if not exit.
     */
    class Thread3 implements Runnable {
        private String message;

        Thread3(String message) {
            this.message = message;
        }

        @Override
        public void run()
        {
            output.println(message);
            output.flush();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageLog_TextView.append( message + "\n");
                    if (message.equals("exit"))
                    {
                        messageLog_TextView.append("Socket Connection Closed: " + socket + "\n");
                        connect_Button.setText("Connect");
                    }
                    message_to_server_EditText.setText("");
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            });

            if (!message.equals("exit"))
                new Thread(new Thread2()).start();
        }
    }

}