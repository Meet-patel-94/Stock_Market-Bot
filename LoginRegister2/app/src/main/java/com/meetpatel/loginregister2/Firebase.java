package com.meetpatel.loginregister2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meetpatel.loginregister2.Model.FirebaseDataModel;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Firebase extends AppCompatActivity {

    private SimpleDateFormat sdf;

    private ArrayList<String> fileNames = new ArrayList<>();
    private ArrayList<String> fileExtensions = new ArrayList<>();
    private ArrayList<String> fileURLs = new ArrayList<>();

    private ProgressDialog progressDialog;

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

       // SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        //String date = sdf.format(new Date());
        //Log.i("tifalab",date);

        loadDataFromFirebase();

        imageView = findViewById(R.id.ShowImageView);


        String dob = "01/08/1990";
        int month = 0, dd = 0, yer = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date d = sdf.parse(dob);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            month = cal.get(Calendar.MONTH);
            dd = cal.get(Calendar.DATE);
            yer = cal.get(Calendar.YEAR);
            System.out.println("Month - " + String.format("%02d", (month+1)));
            System.out.println("Day - " + String.format("%02d", dd));
            System.out.println("Year - " + yer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromFirebase(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("masterSheet");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object object = dataSnapshot.getValue();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    for (int j = 0; j < 3; j++){

                        switch (j){
                            case 0:
                                fileNames.add(String.valueOf(postSnapshot.child(String.valueOf(j)).getValue()));
                                break;
                            case 1:
                                fileExtensions.add(String.valueOf(postSnapshot.child(String.valueOf(j)).getValue()));
                                break;
                            case 2:
                                fileURLs.add(String.valueOf(postSnapshot.child(String.valueOf(j)).getValue()));
                                break;
                        }
                    }
                }

                Log.d("adsf", "asdf");

                progressDialog.dismiss();
                displayImage();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("adsf", "asdf");
            }
        });

    }

    public void displayImage(){
        String str = fileURLs.get(2);
        Picasso.with(this).load("https://drive.google.com/file/d/10Yri6MTFXJpzwiEgiFcnqDdlE8nDHYDT/view").into((ImageView)findViewById(R.id.ShowImageView));
    }

}