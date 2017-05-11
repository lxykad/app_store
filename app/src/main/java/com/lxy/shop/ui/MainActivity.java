package com.lxy.shop.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseApplication;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void testClick(View view){
        Toast.makeText(this,"test",Toast.LENGTH_SHORT).show();
    }
}
