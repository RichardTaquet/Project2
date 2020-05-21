package com.example.project.controller;

import android.content.SharedPreferences;

import com.example.project.data.PokeCallback;
import com.example.project.data.PokeRepository;
import com.example.project.model.Pokemon;
import com.example.project.view.MainActivity;

import java.util.List;

public class MainControlleur {


    private final PokeRepository pokeRepository;
    private SharedPreferences sharedPreferences;
    private MainActivity view;


    public MainControlleur(MainActivity mainActivity, PokeRepository pokeRepository) {
        this.view = mainActivity;
        this.pokeRepository = pokeRepository;
    }


    public void onStart() {
        pokeRepository.getPokemonResponse(new PokeCallback() {
            @Override
            public void onSuccess(List<Pokemon> response) {
                view.showList(response);

            }

            @Override
            public void onFailure() {
                view.showError();
            }
        });


    }


    public void onItemClick(Pokemon pokemon) {
        view.navigateToDetails(pokemon);
    }
}
