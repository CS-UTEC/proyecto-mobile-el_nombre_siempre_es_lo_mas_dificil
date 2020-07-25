package com.example.ichef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    public void showMessage(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClickLogIn(View view) {
        Intent intent = new Intent(HomeActivity.this, LogInActivity.class);
        startActivity(intent);
    }

    public void onClickSignUp(View view) {
        Intent intent = new Intent(HomeActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onClickEnterWithoutLogIn(View view) {
        showMessage("Soon available");
    }
}