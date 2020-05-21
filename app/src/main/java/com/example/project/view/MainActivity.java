package com.example.project.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.Singletons;
import com.example.project.controller.MainControlleur;
import com.example.project.data.PokeRepository;
import com.example.project.model.Pokemon;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private PokeRepository pokeRepository;
    private MainControlleur controlleur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controlleur=new MainControlleur(this,
        pokeRepository);
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

        mAdapter = new ListAdapter(pokemonList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon item) {
                 controlleur.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }


    public void showError(){
        Toast.makeText(this,"API Error",Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Pokemon pokemon) {
        Intent myIntent = new Intent(MainActivity.this,DetailActivity.class);
        myIntent.putExtra("pokemonKey",Singletons.getGson().toJson(pokemon));
        MainActivity.this.startActivity(myIntent);
    }
}
