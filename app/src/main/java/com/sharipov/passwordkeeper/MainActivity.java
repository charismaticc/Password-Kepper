package com.sharipov.passwordkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sharipov.passwordkeeper.Presentation.Repository.Repository;
import com.sharipov.passwordkeeper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        Repository.init(getApplication());
    }
}