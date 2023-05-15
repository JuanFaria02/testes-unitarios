package br.ce.juanfaria.servicos;

import br.ce.juanfaria.entidades.Filme;
import br.ce.juanfaria.entidades.Locacao;
import br.ce.juanfaria.entidades.Usuario;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.rules.ErrorCollector;

import java.util.Date;

import static br.ce.juanfaria.utils.DataUtils.isMesmaData;
import static br.ce.juanfaria.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class LocacaoServiceTest {
    // Permite mudar o comportamento dos testes e criar regras
    @Rule
    private ErrorCollector error = new ErrorCollector();
    @Test
    public void test() {
        //cenario
        LocacaoService locacaoService = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);


        //ação
        Locacao locacao = null;
        try {
            locacao = locacaoService.alugarFilme(usuario, filme);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //avaliação usando assert that (verifique que)
        assertThat(locacao.getValor(), is(equalTo(5.0)));
        assertThat(locacao.getValor(), not(equalTo(6.0)));

        assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

    public void testeLocacao() throws Exception {
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        //acao

        Locacao locacao = service.alugarFilme(usuario, filme);

        //verificacao
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }
    @Test
    public void testException() throws Exception{
        //cenario
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);

        //acao
        Throwable exception = Assertions.assertThrows(Exception.class, ()->{
            service.alugarFilme(usuario, filme);
        });

        Assert.assertEquals("Filme fora de estoque", exception.getMessage());
    }
}
