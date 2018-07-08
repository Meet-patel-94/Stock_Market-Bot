package com.meetpatel.loginregister2.Dashboard;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.meetpatel.loginregister2.Global;
import com.meetpatel.loginregister2.PriceFragment;
import com.meetpatel.loginregister2.R;
import com.meetpatel.loginregister2.Util;
import com.snatik.storage.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.regex.Pattern;


public class SelectMail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RadioGroup radioGroup;
    private RadioButton radioButton_quandl;
    private RadioButton radioButton_yahoo;
    private RadioButton radioButton_google;

    private Button buttonNext;

    private Global global;

    private PriceFragment priceFragment;

    private String strSelectedItem = "Quandl";

    private StorageReference mStorageRef;

    public SelectMail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        global = Global.getInstance();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_mail, container, false);

        priceFragment = new PriceFragment();

        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton_quandl = view.findViewById(R.id.radioQuandl);
        radioButton_yahoo = view.findViewById(R.id.radioYahoo);
        radioButton_google = view.findViewById(R.id.radioGoogle);

        buttonNext = view.findViewById(R.id.btn_nextpage);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global.setSelectedMail(strSelectedItem);

                fileMake_upload();

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.bottomNavLayout, priceFragment);
                transaction.detach(SelectMail.this).attach(priceFragment);
                transaction.commit();

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radioQuandl){
                    strSelectedItem = radioButton_quandl.getText().toString();
                } else if (checkedId == R.id.radioYahoo){
                    strSelectedItem = radioButton_yahoo.getText().toString();

                } else if (checkedId == R.id.radioGoogle){
                    strSelectedItem = radioButton_google.getText().toString();
                }
                global.setSelectedMail(strSelectedItem);
            }
        });
        return view;
    }

    public void fileMake_upload(){
        Storage storage = new Storage(getActivity().getApplicationContext());
        String path = storage.getExternalStorageDirectory() + File.separator + "Temp";

        String strYearTemp = String.valueOf(global.getPresentYear());

        Random r = new Random();
        int n = r.nextInt(2018 - 2004) + 2004;
        String fileName = global.getPresentDay() + global.getMonthCharacter(global.getPresentMonth() + 1) +  strYearTemp.substring(2) + "-" + String.valueOf(n) + "query.txt";
        String strContent = global.getMailFormat();

        storage.createDirectory(path);

        storage.createFile(path + fileName, global.getMailFormat().getBytes());
        File file = new File(path, fileName);

        try {
            FileOutputStream stream = new FileOutputStream(file);
            try {
                stream.write(global.getMailFormat().getBytes());
            } finally {
                stream.close();
            }
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        //Upload query file
        mStorageRef = FirebaseStorage.getInstance().getReference();
        Uri file_upload = Uri.fromFile(file);
        StorageReference riversRef = mStorageRef.child("query/" + fileName);

        riversRef.putFile(file_upload).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("adsf", "adsf");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("adsf", "adsf");
            }
        });
    }

}
