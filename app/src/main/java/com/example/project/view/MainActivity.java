package com.example.project.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project.Singletons;
import com.example.project.controller.MainControlleur;
import com.example.project.model.Pokemon;
import com.example.project.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainControlleur controlleur=new MainControlleur(this,
        Singletons.getGson(),
        Singletons.getSharedPreferences(getApplicationContext()));
        controlleur.onStart();


    }



    public void showList(List<Pokemon> pokemonList){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //quand je supprime ces lignes ListAdapter marchent plus
        final List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }// define an adapter


        mAdapter = new ListAdapter(pokemonList);
        recyclerView.setAdapter(mAdapter);
    }


    public void showError(){
        Toast.makeText(this,"API Error",Toast.LENGTH_SHORT).show();
    }
}
