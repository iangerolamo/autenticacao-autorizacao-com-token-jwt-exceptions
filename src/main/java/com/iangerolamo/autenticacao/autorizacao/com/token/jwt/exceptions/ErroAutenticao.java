package com.iangerolamo.autenticacao.autorizacao.com.token.jwt.exceptions;

public class ErroAutenticao extends RuntimeException {

    public ErroAutenticao(String msg) {
        super(msg);
    }
}
