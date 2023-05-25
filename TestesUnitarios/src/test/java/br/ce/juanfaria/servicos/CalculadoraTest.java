package br.ce.juanfaria.servicos;

import br.ce.juanfaria.servicos.exceptions.NaoPodeDividirPorZeroException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    @Test
    public void deveSomarDoisValores(){
        //Cenario
        int a = 5;
        int b = 3;
        Calculadora calculadora = new Calculadora();
        //Ação
        int resutado = calculadora.somar(a, b);
        //Verificação
        Assert.assertEquals(8, resutado);
    }
    @Test
    public void deveSubtrairDoisValores(){
        //Cenario
        int a = 8;
        int b = 5;
        Calculadora calculadora = new Calculadora();
        //Ação
        int resultado = calculadora.subtrair(a,b);
        //Verificação
        Assert.assertEquals(3, resultado);
    }

    @Test
    public void deveDividirDoisValores(){
        //Cenario
        int a = 4;
        int b = 2;
        Calculadora calculadora = new Calculadora();
        //Ação
        int resultado = calculadora.dividir(a,b);
        //Verificação
        Assert.assertEquals(2, resultado);
    }

    @Test
    public void deveLancarExcecaoDividirPorZero(){
        //Cenario
        int a = 4;
        int b = 0;
        Calculadora calculadora = new Calculadora();
        //Ação
        Throwable error = Assertions.assertThrows(NaoPodeDividirPorZeroException.class, ()->calculadora.dividir(a,b));

        //Verificação
        Assert.assertEquals("Não é possível dividir por zero", error.getMessage());
    }

}
