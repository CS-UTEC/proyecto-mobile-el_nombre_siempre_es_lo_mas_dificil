package com.example.ichef;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class RecipeActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewMyRecipes;
    private RecyclerView.Adapter mAdapterMyRecipes;
    private RecyclerView mRecyclerViewRecipes;
    private RecyclerView.Adapter mAdapterRecipes;
    public String username;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        this.id = getIntent().getExtras().getInt("id");
        String username = getIntent().getExtras().getString("username");
        setTitle("Welcome chef " + username);
        mRecyclerViewMyRecipes = findViewById(R.id.rvMyRecipes);
        mRecyclerViewRecipes = findViewById(R.id.rvRecipes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerViewMyRecipes.setLayoutManager(new LinearLayoutManager(this));
        getMyRecipes();
        mRecyclerViewRecipes.setLayoutManager(new LinearLayoutManager(this));
        getRecipes();
    }

    public void getRecipes() {
        String url = "http://ec2-35-170-115-7.compute-1.amazonaws.com/recipes2";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                new JSONArray(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        mAdapterRecipes = new RecipeAdapter(response, RecipeActivity.this);
                        mRecyclerViewRecipes.setAdapter(mAdapterRecipes);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public void getMyRecipes() {
        String url = "http://ec2-35-170-115-7.compute-1.amazonaws.com/recipes2?user=" + Integer.toString(id);
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                new JSONArray(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        mAdapterMyRecipes = new RecipeAdapter(response, RecipeActivity.this);
                        mRecyclerViewMyRecipes.setAdapter(mAdapterMyRecipes);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}