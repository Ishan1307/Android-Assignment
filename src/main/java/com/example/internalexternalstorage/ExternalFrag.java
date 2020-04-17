package com.example.internalexternalstorage;


import android.app.usage.ExternalStorageStats;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExternalFrag extends Fragment {


    public ExternalFrag() {
        // Required empty public constructor
    }
    Button b1,b2;
    EditText ed1,ed2;
    TextView tv;
    String s1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1=inflater.inflate(R.layout.fragment_external, container, false);
        b1=v1.findViewById(R.id.b1);
        b2=v1.findViewById(R.id.b2);
        ed1=v1.findViewById(R.id.ed1);
        ed2=v1.findViewById(R.id.ed2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File textFile=new File(Environment.getExternalStorageDirectory(),ed1.getText().toString());
                s1=textFile.getPath();
                OutputStreamWriter outputStreamWriter=null;
                try
                {

                    outputStreamWriter=new OutputStreamWriter((getContext().openFileOutput(textFile.getName(), Context.MODE_PRIVATE)));
                    outputStreamWriter.write(ed2.getText().toString());
                    ed1.getText().clear();
                    ed2.setText("");
                    Toast.makeText(getContext(), "saved to:"+s1, Toast.LENGTH_SHORT).show();
                    outputStreamWriter.close();
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(), "exception", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ret="";
                try{
                    InputStream inputStream=getContext().openFileInput(ed1.getText().toString());
                    if(inputStream!=null)
                    {
                        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                        String recieve="";
                        StringBuilder stringBuilder=new StringBuilder();
                        while ((recieve=bufferedReader.readLine())!=null)
                        {
                            stringBuilder.append("\n").append(recieve);
                        }
                        inputStream.close();
                        ret=stringBuilder.toString();
                        ed2.setText(ret);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(), "exception", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v1;
    }

}
