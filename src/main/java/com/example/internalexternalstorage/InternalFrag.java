package com.example.internalexternalstorage;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


/**
 * A simple {@link Fragment} subclass.
 */
public class InternalFrag extends Fragment {


    public InternalFrag() {
        // Required empty public constructor
    }


    private static final String File_Name="int.txt";
    Button b1,b2;
    EditText ed1;
    TextView tv1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v1=inflater.inflate(R.layout.fragment_internal, container, false);;
         b1=v1.findViewById(R.id.b1);
         b2=v1.findViewById(R.id.b2);
         ed1=v1.findViewById(R.id.ed1);
         tv1=v1.findViewById(R.id.tv1);
         tv1.setSelected(true);
         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //Toast.makeText(getContext(), "ishan", Toast.LENGTH_SHORT).show();
                 String s1=ed1.getText().toString();
                 OutputStreamWriter outputStreamWriter=null;
                 try
                 {

                     outputStreamWriter=new OutputStreamWriter((getContext().openFileOutput(File_Name, Context.MODE_PRIVATE)));
                     outputStreamWriter.write(s1);
                     ed1.getText().clear();
                     Toast.makeText(getContext(), "Saved to: "+File_Name, Toast.LENGTH_SHORT).show();
                     outputStreamWriter.close();
                 }
                 catch (IOException e)
                 {
                     Toast.makeText(getContext(), "Exception", Toast.LENGTH_SHORT).show();
                 }




             }
         });
         b2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String ret="";
                 try{
                     InputStream inputStream=getContext().openFileInput(File_Name);
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
                         ed1.setText(ret);
                     }
                 }
                 catch (Exception e)
                 {

                 }
             }
         });
        return v1;
    }


}
