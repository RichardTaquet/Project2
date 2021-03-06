package com.example.project.data;

import android.content.SharedPreferences;

import com.example.project.Constants;
import com.example.project.model.Pokemon;
import com.example.project.model.RestPokemonResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeRepository {
    private Gson gson;
    private PokeApi pokeApi;
    private SharedPreferences sharedPreferences;


    public PokeRepository(PokeApi pokeApi, SharedPreferences sharedPreferences, Gson gson) {
        this.pokeApi = pokeApi;
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void getPokemonResponse( final PokeCallback callback){
        List<Pokemon> list = getDataFromCache();
        if (list != null) {
            callback.onSuccess(list);


        }else {
            pokeApi.getPokemonResponse().enqueue(new Callback<RestPokemonResponse>() {
                @Override
                public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                    if (response.isSuccessful() && response.body() != null){
                        callback.onSuccess(response.body().getResults());
                    }else{
                        callback.onFailure();
                    }
                }

                @Override
                public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                    callback.onFailure();
                }
            });

        }
    }

    private List<Pokemon> getDataFromCache() {
        String jsonPokemon = sharedPreferences.getString(Constants.KEY_POKEMON_LIST, null);

        if(jsonPokemon == null){
            return null;
        }else{
            Type listType = new TypeToken<List<Pokemon>>(){}.getType();
            return gson.fromJson(jsonPokemon, listType);
        }
    }
    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_POKEMON_LIST, jsonString)
                .apply();
    }



}
