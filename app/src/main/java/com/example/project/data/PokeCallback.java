package com.example.project.data;

import com.example.project.model.Pokemon;

import java.util.List;

public interface PokeCallback {
        public void onSuccess(List<Pokemon>  response);
        public void onFailure();

}
