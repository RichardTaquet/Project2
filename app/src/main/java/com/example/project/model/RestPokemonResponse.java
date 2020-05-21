package com.example.project.model;

import com.example.project.model.Pokemon;

import java.util.List;

public class RestPokemonResponse {
    private Integer count;
    private String next;
    private List<Pokemon> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List getResults() {
        return results;
    }
}
