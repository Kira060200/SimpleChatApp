package com.example.android.chatnow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public int EmailCheck() {
        EditText emailView = (EditText)findViewById(R.id.email);
        String email = emailView.getText().toString();
        if(!email.contains("@"))
            return 0;
        return 1;
    }
    public int PasswordCheck() {
        EditText pass1View = (EditText)findViewById(R.id.password);
        String pass1 = pass1View.getText().toString();
        EditText pass2View = (EditText)findViewById(R.id.password2);
        String pass2 = pass2View.getText().toString();
        if(!pass1.equals(pass2)){
            return 0;
        }
        return 1;
    }
    public void onCreateAccount(View view) {
        boolean ok = true;
        if(EmailCheck()==0) {
            TextView errorView = (TextView) findViewById(R.id.textView);
            errorView.setText("Invalid email address");
            ok = false;
        }
        if(PasswordCheck()==0) {
            TextView errorView = (TextView) findViewById(R.id.textView);
            errorView.setText("Password doesn't match!");
            ok = false;
        }
        if(ok) {
            EditText nameView = (EditText) findViewById(R.id.username);
            String name = nameView.getText().toString();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.EXTRA_MESSAGE, name);
            startActivity(intent);
        }
    }
}
