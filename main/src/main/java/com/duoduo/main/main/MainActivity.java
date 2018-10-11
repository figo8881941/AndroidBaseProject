package com.duoduo.main.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.duoduo.main.R;

/**
 * 主activity
 */
public class MainActivity extends AppCompatActivity {

    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainController = new MainController(getApplicationContext());
        mainController.requestTabData();
    }
}
