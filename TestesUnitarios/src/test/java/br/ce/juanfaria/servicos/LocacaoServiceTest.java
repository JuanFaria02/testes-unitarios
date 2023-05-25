package br.ce.juanfaria.servicos;

import br.ce.juanfaria.entidades.Filme;
import br.ce.juanfaria.entidades.Locacao;
import br.ce.juanfaria.entidades.Usuario;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static br.ce.juanfaria.utils.DataUtils.isMesmaData;
import static br.ce.juanfaria.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class LocacaoServiceTest {
    private List<Filme> filmes;
    private LocacaoService locacaoService;
    // Permite mudar o comportamento dos testes e criar regras
    @Rule
    private ErrorCollector error = new ErrorCollector();


    //Executa antes de cada teste
    @BeforeEach
    public void setup(){
        locacaoService = new LocacaoService();
        filmes = new ArrayList<>();
    }

    @Test
    public void testAluguelFilme() throws Exception {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);
        Filme filme2 = new Filme("Filme 1", 2, 5.0);


        filmes.addAll(Arrays.asList(filme, filme2));

        //ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);


        //avaliação usando assert that (verifique que)
        assertThat(locacao.getValor(), is(equalTo(10.0)));
        assertThat(locacao.getValor(), not(equalTo(6.0)));

        assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }
    @Test
    public void testExceptionSemEstoque() throws Exception{
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 0, 5.0);
        filmes.add(filme);
        //acao
        Throwable exception = Assertions.assertThrows(Exception.class, ()->{
            locacaoService.alugarFilme(usuario, filmes);
        });

        Assert.assertEquals("Filme fora de estoque", exception.getMessage());
    }

    @Test
    public void testListaFilmesVazia(){
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        //acao
        Throwable exception = Assertions.assertThrows(Exception.class, ()->{
            locacaoService.alugarFilme(usuario, filmes);
        });

        Assert.assertEquals("Lista vazia", exception.getMessage());

    }
    @Test
    public void testUsuarioVazio(){
        //cenario
        Filme filme = new Filme("Filme 1", 2, 5.0);
        filmes.add(filme);
        //acao
        Throwable exception = Assertions.assertThrows(Exception.class, ()->{
            locacaoService.alugarFilme(null, filmes);
        });

        Assert.assertEquals("Usuario vazio", exception.getMessage());
    }

    @Test
    public void testPagar75pctNoTerceiroFilme() throws Exception {
        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);
        Filme filme2 = new Filme("Filme 1", 2, 5.0);
        Filme filme3 = new Filme("Filme 1", 2, 5.0);

        filmes.addAll(Arrays.asList(filme, filme2, filme3));
        //Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);
        //Verificação
        assertThat(locacao.getValor(), is(equalTo(13.75)));
    }
    @Test
    public void testPagar50pctNoQuartoFilme() throws Exception {
        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);
        Filme filme2 = new Filme("Filme 1", 2, 5.0);
        Filme filme3 = new Filme("Filme 1", 2, 5.0);
        Filme filme4 = new Filme("Filme 1", 2, 20.0);
        filmes.addAll(Arrays.asList(filme, filme2, filme3, filme4));
        //Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);
        //Verificação
        assertThat(locacao.getValor(), is(equalTo(25.0)));
    }

    @Test
    public void testPagar75pctNoQuintoFilme() throws Exception {
        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);
        Filme filme2 = new Filme("Filme 1", 2, 5.0);
        Filme filme3 = new Filme("Filme 1", 2, 5.0);
        Filme filme4 = new Filme("Filme 1", 2, 20.0);
        Filme filme5 = new Filme("Filme 1", 2, 20.0);

        filmes.addAll(Arrays.asList(filme, filme2, filme3, filme4, filme5));
        //Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);
        //Verificação
        assertThat(locacao.getValor(), is(equalTo(40.0)));
    }

    @Test
    public void testPagar100pctNoQuintoFilme() throws Exception {
        //Cenário
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);
        Filme filme2 = new Filme("Filme 1", 2, 5.0);
        Filme filme3 = new Filme("Filme 1", 2, 5.0);
        Filme filme4 = new Filme("Filme 1", 2, 20.0);
        Filme filme5 = new Filme("Filme 1", 2, 20.0);
        Filme filme6 = new Filme("Filme 1", 2, 20.0);
        filmes.addAll(Arrays.asList(filme, filme2, filme3, filme4, filme5, filme6));
        //Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);
        //Verificação
        assertThat(locacao.getValor(), is(equalTo(55.0)));
    }
}
