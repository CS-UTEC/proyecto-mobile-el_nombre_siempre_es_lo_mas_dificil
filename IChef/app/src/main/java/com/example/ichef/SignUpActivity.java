package com.example.ichef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void showMessage(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    public void onClickSignUpAction(View view) {
        EditText txtUsername = (EditText) findViewById(R.id.etUsername);
        EditText txtPassword = (EditText) findViewById(R.id.etPassword);
        final String username = txtUsername.getText().toString();
        final String password = txtPassword.getText().toString();

        Map<String, String> message = new HashMap<String, String>();
        message.put("username", username);
        message.put("password", password);

        JSONObject jsonObject = new JSONObject(message);

        showMessage(username + ' ' + password);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "http://ec2-35-170-115-7.compute-1.amazonaws.com/users",
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        showMessage(response.toString());
                        showMessage("Success");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showMessage(error.toString());
                        Log.d("TAG", "onClickSignUpAction: " + error.toString());
                        showMessage("Fail");
                    }
                }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }
}