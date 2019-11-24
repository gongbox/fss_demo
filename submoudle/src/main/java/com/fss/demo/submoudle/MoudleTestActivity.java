package com.fss.demo.submoudle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fss.router.annotation.Route;

@Route
public class MoudleTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moudle_test);
    }
}
