package br.ce.juanfaria.servicos;

import br.ce.juanfaria.entidades.Filme;
import br.ce.juanfaria.entidades.Locacao;
import br.ce.juanfaria.entidades.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class CalcularValorLocacao {
    //O que fica variando
    @Parameterized.Parameter
    public List<Filme> filmes;
    @Parameterized.Parameter(value=1)
    public Double valorLocacao;

    @Parameterized.Parameter(value=2)
    public String cenario;

    private LocacaoService locacaoService;

    @Before
    public void setup(){
        locacaoService = new LocacaoService();
    }

    private static Filme filme = new Filme("Filme 1", 2, 4.0);
    private static Filme filme2 = new Filme("Filme 2", 2, 4.0);
    private static Filme filme3 = new Filme("Filme 3", 2, 4.0);
    private static Filme filme4 = new Filme("Filme 4", 2, 4.0);
    private static Filme filme5 = new Filme("Filme 5", 2, 4.0);
    private static Filme filme6 = new Filme("Filme 6", 2, 4.0);

    @Parameterized.Parameters(name = "{2}")
    public static Collection<Object[]> getParametros(){
        //Cada linha da matriz é um cenário
        return Arrays.asList(new Object[][]{
                {Arrays.asList(filme, filme2, filme3), 11.0, "3 Filmes 25%"},
                {Arrays.asList(filme, filme2, filme3, filme4), 14.0, "4 Filmes 50%"},
                {Arrays.asList(filme, filme2, filme3, filme4, filme5), 17.0, "5 Filmes 75%"},
                {Arrays.asList(filme, filme2, filme3, filme4, filme5, filme6), 20.0, "6 Filmes 100%"}
        });
    }

    @Test
    public void deveCalcularValorLocacaoConsiderandoDescontos() throws Exception {
        //Cenário
        Usuario usuario = new Usuario("Usuario 1");

        //Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        //Verificação
        assertThat(locacao.getValor(), is(valorLocacao));
    }
}
