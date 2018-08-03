package com.example.android.chatnow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        // get the password EditText
        final EditText mEtPwd = (EditText) findViewById(R.id.password);
        // get the show/hide password Checkbox
        CheckBox mCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);

        // add onCheckedListener on checkbox
        // when user clicks on this checkbox, this is the handler.
        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    mEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    mEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
    public void onLogin(View view) {                                ///Logiini button
        EditText nameView = (EditText)findViewById(R.id.username);
        String name = nameView.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_MESSAGE, name);
        startActivity(intent);
    }
}
