package com.meetpatel.loginregister2;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LoadActivity extends AppCompatActivity {
    Button btn_write, btn_read, btn_nextpage;
    EditText edt_name;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        edt_name = (EditText) findViewById(R.id.edt_name);
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),LoadActivity.class);
                startActivity(intent);
                buttonOption(view);
            }
        });
        btn_write = (Button) findViewById(R.id.btn_write);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonOption(view);
            }
        });
        btn_nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),PriceFragment.class);
                startActivity(intent);
                buttonOption(view);
            }
        });
    }

    public void buttonOption(View view){
        switch (view.getId()) {
            case R.id.btn_read:
                read:
                readdata();
                break;
            case R.id.btn_write:
                savedata();
                break;
        }
    }

    private void savedata() {
        try {
            OutputStreamWriter write = new OutputStreamWriter(getApplicationContext().openFileOutput("myfile.txt", MODE_APPEND));
            write.write(edt_name.getText().toString());
            write.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void readdata() {
        StringBuilder data = null;
        try {
            String recive;
            InputStream inputStream = getApplication().openFileInput("myfile.txt");
            if (inputStream != null) {
                data = new StringBuilder();
                InputStreamReader read = new InputStreamReader(inputStream);
                BufferedReader buff = new BufferedReader(read);
                while ((recive = buff.readLine())!= null)
                {
                    data.append(recive);
                }}
            Toast.makeText(getApplicationContext(), data.toString(),Toast.LENGTH_LONG).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
