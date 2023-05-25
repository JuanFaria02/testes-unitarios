package br.ce.juanfaria.servicos.exceptions;

public class NaoPodeDividirPorZeroException extends RuntimeException{
    public NaoPodeDividirPorZeroException(String msg){
        super(msg);
    }
}
