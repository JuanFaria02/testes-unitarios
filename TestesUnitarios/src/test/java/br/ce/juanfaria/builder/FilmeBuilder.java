package br.ce.juanfaria.builder;

import br.ce.juanfaria.entidades.Filme;

public class FilmeBuilder {
    private Filme filme;

    private FilmeBuilder(){}

    public static FilmeBuilder umFilme(){
        FilmeBuilder filmeBuilder = new FilmeBuilder();
        filmeBuilder.filme = new Filme();
        filmeBuilder.filme.setEstoque(2);
        filmeBuilder.filme.setNome("Filme");
        filmeBuilder.filme.setPrecoLocacao(5d);
        return filmeBuilder;
    }

    public static FilmeBuilder umFilmeSemEstoque(){
        FilmeBuilder filmeBuilder = new FilmeBuilder();
        filmeBuilder.filme = new Filme();
        filmeBuilder.filme.setEstoque(0);
        filmeBuilder.filme.setNome("Filme");
        filmeBuilder.filme.setPrecoLocacao(5d);
        return filmeBuilder;
    }
/*
    public FilmeBuilder filmeSemEstoque(){
        filme.setEstoque(0);
        return this;
    }

 */
    public Filme agora(){
        return filme;
    }
}
