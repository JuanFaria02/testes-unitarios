package br.ce.juanfaria.servicos;

import br.ce.juanfaria.builder.FilmeBuilder;
import br.ce.juanfaria.builder.UsuarioBuilder;
import br.ce.juanfaria.entidades.Filme;
import br.ce.juanfaria.entidades.Locacao;
import br.ce.juanfaria.entidades.Usuario;
import br.ce.juanfaria.utils.DataUtils;
import buildermaster.BuilderMaster;
import org.junit.Assert;
import org.junit.Assume;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;

import java.util.*;

import static br.ce.juanfaria.matchers.MatchersProprios.*;
import static br.ce.juanfaria.utils.DataUtils.isMesmaData;
import static br.ce.juanfaria.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class LocacaoServiceTest {
    private List<Filme> filmes;
    private LocacaoService locacaoService;
    // Permite mudar o comportamento dos testes e criar regras
    @Rule
    public ErrorCollector error = new ErrorCollector();


    //Executa antes de cada teste
    @BeforeEach
    public void setup(){
        locacaoService = new LocacaoService();
        filmes = new ArrayList<>();
    }

    @Test
    public void testAluguelFilme() throws Exception {
        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
        //cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        Filme filme = FilmeBuilder.umFilme().agora();
        Filme filme2 = FilmeBuilder.umFilme().agora();


        filmes.addAll(Arrays.asList(filme, filme2));

        //ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);
        //avaliação usando assert that (verifique que)
        assertThat(locacao.getValor(), is(equalTo(10.0)));
        assertThat(locacao.getValor(), not(equalTo(6.0)));

        assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
        error.checkThat(locacao.getDataRetorno(), eHoje());

        error.checkThat(locacao.getDataRetorno(), eHojeComDiferencaDias(1));
    }
    @Test
    public void testExceptionSemEstoque() throws Exception{
        //cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        Filme filme = FilmeBuilder.umFilmeSemEstoque().agora();
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
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        //acao
        Throwable exception = Assertions.assertThrows(Exception.class, ()->{
            locacaoService.alugarFilme(usuario, filmes);
        });

        Assert.assertEquals("Lista vazia", exception.getMessage());

    }
    @Test
    public void testUsuarioVazio(){
        //cenario
        Filme filme = FilmeBuilder.umFilme().agora();
        filmes.add(filme);
        //acao
        Throwable exception = Assertions.assertThrows(Exception.class, ()->{
            locacaoService.alugarFilme(null, filmes);
        });

        Assert.assertEquals("Usuario vazio", exception.getMessage());
    }

    @Test
    public void testPagar25pctNoTerceiroFilme() throws Exception {
        //Cenário
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
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
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
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
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
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
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
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

    @Test
    //@Disabled Teste não será executado
    public void naoDeveDevolverFilmeDomingo() throws Exception {
        //Só executa o teste se a data for sabado
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
        //Cenario
        Usuario usuario = UsuarioBuilder.umUsuario().agora();
        Filme filme = FilmeBuilder.umFilme().agora();
        filmes.addAll(Arrays.asList(filme));

        //Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        //Verificação
        boolean segunda = DataUtils.verificarDiaSemana(locacao.getDataRetorno(), Calendar.MONDAY);
        Assert.assertTrue(segunda);
        //assertThat(locacao.getDataRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
        assertThat(locacao.getDataRetorno(), caiEm(Calendar.MONDAY));
        assertThat(locacao.getDataRetorno(), caiNumaSegunda());
    }
/*
    public static void main(String[] args) {
        new BuilderMaster().gerarCodigoClasse(Locacao.class);
    }

 */
}
