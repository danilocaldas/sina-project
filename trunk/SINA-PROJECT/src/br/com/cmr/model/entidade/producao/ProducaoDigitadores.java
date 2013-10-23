/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.cmr.model.entidade.producao;

import java.sql.Date;

/**
 *
 * @author Danilo
 */
public class ProducaoDigitadores extends Producao{

    private Date entrada;
    private Date digitacao;
    private Integer quantidade;

    public ProducaoDigitadores(Date entrada, Date digitacao, Integer quantidade, Long id, String funcionario_nome, String prestador_nome, String procedimento_nome) {
        super(id, funcionario_nome, prestador_nome, procedimento_nome);
        this.entrada = entrada;
        this.digitacao = digitacao;
        this.quantidade = quantidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getDigitacao() {
        return digitacao;
    }

    public void setDigitacao(Date digitacao) {
        this.digitacao = digitacao;
    }
    
   
    
    
    
}
