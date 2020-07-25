package com.example.ichef;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import io.noties.markwon.Markwon;

public class ViewRecipeActivity extends AppCompatActivity {
    private String markdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        this.markdown = getIntent().getExtras().getString("markdown");

        final Markwon markwon = Markwon.create(ViewRecipeActivity.this);

        markwon.setMarkdown((TextView) findViewById(R.id.tvMarkdown), markdown);
    }
}