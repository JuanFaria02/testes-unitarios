package br.ce.juanfaria.suites;

import br.ce.juanfaria.servicos.CalculadoraTest;
import br.ce.juanfaria.servicos.CalcularValorLocacao;
import br.ce.juanfaria.servicos.LocacaoServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CalculadoraTest.class,
        CalcularValorLocacao.class}) //LocaçãoServiceTest não vai rodar por usar alguns elementos do Junit 5 e não Junit 4
public class SuiteExecucao {
}
