package com.meetpatel.loginregister2;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    Button bLogin;
    Button SignUPButton;
    EditText etFirstName,etLastName, etPassword, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etFirstName = (EditText) findViewById(R.id.etFirst_Name);
        etLastName = (EditText) findViewById(R.id.etLast_Name);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);

        SignUPButton = (Button) findViewById(R.id.bSignUp);
        SignUPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AndroidBottomNavigation.class);
                startActivity(intent);
            }
        });
    }
}