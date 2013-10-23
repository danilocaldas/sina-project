/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.model.entidade.producao;

/**
 *
 * @author Danilo
 */
public class Producao {

    private Long id;
    private String funcionario_nome;
    private String prestador_nome;
    private String procedimento_nome;

    
    public Producao(Long id, String funcionario_nome, String prestador_nome, String procedimento_nome) {
        this.id = id;
        this.funcionario_nome = funcionario_nome;
        this.prestador_nome = prestador_nome;
        this.procedimento_nome = procedimento_nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncionario_nome() {
        return funcionario_nome;
    }

    public void setFuncionario_nome(String funcionario_nome) {
        this.funcionario_nome = funcionario_nome;
    }

    public String getPrestador_nome() {
        return prestador_nome;
    }

    public void setPrestador_nome(String prestador_nome) {
        this.prestador_nome = prestador_nome;
    }

    public String getProcedimento_nome() {
        return procedimento_nome;
    }

    public void setProcedimento_nome(String procedimento_nome) {
        this.procedimento_nome = procedimento_nome;
    }

}
