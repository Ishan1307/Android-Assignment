package com.example.internalexternalstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button b1,b2;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv1);
        tv.setSelected(true);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        img=findViewById(R.id.img);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setVisibility(View.INVISIBLE);
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                img.setVisibility(View.INVISIBLE);
                Fragment fragment=new InternalFrag();
                loadfragment(fragment);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setVisibility(View.INVISIBLE);
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                img.setVisibility(View.INVISIBLE);
                Fragment fragment=new ExternalFrag();
                loadfragment(fragment);
            }
        });
    }
    private void loadfragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.f1, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        img.setVisibility(View.VISIBLE);
        tv.setVisibility(View.VISIBLE);
        return super.onKeyDown(keyCode, event);
    }
}
