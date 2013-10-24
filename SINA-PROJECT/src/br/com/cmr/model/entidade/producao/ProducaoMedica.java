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
public class ProducaoMedica extends Producao{

    public final Long id ;
    private String nucleos;
    private Integer quantidade;
    private Date entradaCmr;
    private Date analise;
    private Date encaminhamento;

    public ProducaoMedica(String nucleos, Integer quantidade, 
            Date entradaCmr, Date analise, 
            Date encaminhamento, Long id, 
            String funcionario_nome, String prestador_nome, 
            String procedimento_nome) {
        super(id, funcionario_nome, prestador_nome, procedimento_nome);
        this.id = id;
        this.nucleos = nucleos;
        this.quantidade = quantidade;
        this.entradaCmr = entradaCmr;
        this.analise = analise;
        this.encaminhamento = encaminhamento;
    }


    public String getNucleos() {
        return nucleos;
    }

    public void setNucleos(String nucleos) {
        this.nucleos = nucleos;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Date getEntradaCmr() {
        return entradaCmr;
    }

    public void setEntradaCmr(Date entradaCmr) {
        this.entradaCmr = entradaCmr;
    }

    public Date getAnalise() {
        return analise;
    }

    public void setAnalise(Date analise) {
        this.analise = analise;
    }

    public Date getEncaminhamento() {
        return encaminhamento;
    }

    public void setEncaminhamento(Date encaminhamento) {
        this.encaminhamento = encaminhamento;
    }

}
