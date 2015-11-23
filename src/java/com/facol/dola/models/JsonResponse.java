/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facol.dola.models;

/**
 *
 * @author dolalima
 */
public class JsonResponse {
    
    private boolean erro = true;
    private String mensagem = "";   

    public JsonResponse() {
    }

    public JsonResponse(boolean erro, String mensagem) {
        this.erro = erro;
        this.mensagem = mensagem;
    }
    
    

    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
    
}
