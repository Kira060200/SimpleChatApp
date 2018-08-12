package com.example.android.chatnow;
import java.io.*;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.net.*;
public class MainActivity extends AppCompatActivity {
    BufferedReader reader;
    PrintWriter writer;
    String messageText;
    boolean mes=false;
    String username;
    public static final String EXTRA_MESSAGE = "message";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        username = intent.getStringExtra(EXTRA_MESSAGE);
        TextView myTextView = (TextView)findViewById(R.id.displaymessage);
        myTextView.setMovementMethod(new ScrollingMovementMethod());
        new ConnectServer().execute();
    }
    public void onSendMessage(View view) {      ///Send button
        mes=true;
        EditText messageView1 = (EditText) findViewById(R.id.message);
        messageText = messageView1.getText().toString();
        writer.println(username+": " + messageText);
        writer.flush();
        messageView1.setText("");
    }
    private class ConnectServer extends AsyncTask<Void, Void, Void> {       ///Background task that connects to the server
        private Socket sock;
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                sock=new Socket("xxxxxxxxx", 5000);
                InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamReader);
                writer = new PrintWriter(sock.getOutputStream());
                Log.d("InternetTag", "Net established");
                Thread readerThread = new Thread(new IncomingReader());
                readerThread.start();
            }catch (IOException ex){
                ex.printStackTrace();
                Log.d("InternetTag", "Net failed");
            }
            return null;
        }
    }
    public class IncomingReader implements Runnable{            ///Thread that receives incoming messages from the server
        public void run() {
            String message;
            try {
                while ( (message=reader.readLine())!=null) {
                    Log.d("TAG", message);
                    final String finalMessage = message;
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {                        ///Displays the message received

                            TextView messageView = (TextView)findViewById(R.id.displaymessage);
                            messageView.append(finalMessage +'\n');

                        }
                    });
                }
                Log.d("TAG","!while");
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
