package br.ce.juanfaria.builder;

import br.ce.juanfaria.entidades.Usuario;
import java.lang.Double;
import java.util.Date;
import br.ce.juanfaria.entidades.Locacao;
import br.ce.juanfaria.utils.DataUtils;


public class LocacaoBuilder {
    private Locacao elemento;
    private LocacaoBuilder(){}

    public static LocacaoBuilder umLocacao() {
        LocacaoBuilder builder = new LocacaoBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(LocacaoBuilder builder) {
        builder.elemento = new Locacao();
        Locacao elemento = builder.elemento;

        elemento.setUsuario(UsuarioBuilder.umUsuario().agora());
        elemento.setDataLocacao(new Date());
        elemento.setDataRetorno(DataUtils.obterDataComDiferencaDias(1));
        elemento.setValor(5d);
    }

    public LocacaoBuilder comUsuario(Usuario param) {
        elemento.setUsuario(param);
        return this;
    }

    public LocacaoBuilder comDataLocacao(Date param) {
        elemento.setDataLocacao(param);
        return this;
    }

    public LocacaoBuilder comDataRetorno(Date param) {
        elemento.setDataRetorno(param);
        return this;
    }

    public LocacaoBuilder comValor(Double param) {
        elemento.setValor(param);
        return this;
    }

    public Locacao agora() {
        return elemento;
    }
}