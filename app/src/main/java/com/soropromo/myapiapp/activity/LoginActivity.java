package com.soropromo.myapiapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.soropromo.myapiapp.R;

public class LoginActivity extends AppCompatActivity {

    private Button logIn;
    private EditText inputUserName;

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        logIn = (Button) findViewById(R.id.btn_login);
        inputUserName = (EditText) findViewById(R.id.input_username);

    }

    //called when user clicks logIn button
    public void getUser(View view) {
        i = new Intent(LoginActivity.this, MainActivity.class);
        i.putExtra("STRING_I_NEED", inputUserName.getText().toString());
        startActivity(i);
    }
}
