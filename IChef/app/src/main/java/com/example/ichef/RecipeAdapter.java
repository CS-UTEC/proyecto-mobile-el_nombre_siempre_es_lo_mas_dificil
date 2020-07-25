package com.example.ichef;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    public JSONArray recipes;
    private Context context;

    public RecipeAdapter(JSONArray recipes, Context context) {
        this.recipes = recipes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            final JSONObject recipe = recipes.getJSONObject(position);
            final String name = recipe.getString("title");

            holder.recipeName.setText(name);
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        goToViewRecipe(recipe.getString("markdown"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return recipes.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.tvRecipeName);
            container = itemView.findViewById(R.id.rlContainer);
        }
    }

    public void goToViewRecipe(String markdown) {
        Intent intent = new Intent(this.context, ViewRecipeActivity.class);
        intent.putExtra("markdown", markdown);
        this.context.startActivity(intent);
    }
}
