package br.ce.juanfaria.servicos;

import br.ce.juanfaria.servicos.exceptions.NaoPodeDividirPorZeroException;

public class Calculadora {
    public static int somar(int a, int b){
        return a+b;
    }

    public static int subtrair(int a, int b){
        return a-b;
    }
    public static int dividir(int a, int b){
        if (b == 0){
            throw new NaoPodeDividirPorZeroException("Não é possível dividir por zero");
        }
        return a/b;
    }
}
