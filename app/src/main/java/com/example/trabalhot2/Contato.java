package com.example.trabalhot2;

import android.widget.ImageView;

import java.io.Serializable;

public class Contato implements Serializable {
    private int id;
    private String nome;
    private int telefone;
    private String observacao;

    public Contato(){}

    public Contato(String nome, int telefone, String observacao) {
        super();
        this.nome = nome;
        this.telefone = telefone;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
