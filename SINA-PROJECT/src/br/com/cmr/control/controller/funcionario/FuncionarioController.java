/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.control.controller.funcionario;

import br.com.cmr.control.facade.funcionario.FuncionarioFacade;
import br.com.cmr.model.entidade.funcionario.Funcionario;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class FuncionarioController {
    
    private FuncionarioFacade facade;

    public FuncionarioController() {
        this.facade = new FuncionarioFacade();
    }
    
    public int addFuncionario(Funcionario funcionario){
        return facade.save(funcionario);
    }
    
    public int updateFuncionario(Funcionario funcionario){
        return facade.update(funcionario);
    }
    
    public int excluirFuncionario(Long id){
        return facade.remove(id);
    }
    
    public List<Funcionario> finfFuncionario(){
        return facade.findAll();
    }
    
    public List<Funcionario> findNome(String nome){
        return facade.findNome(nome);
    }
    
    public List<Funcionario> findNomeMedico(){
        return facade.findNomeMedico();
    }
    
    public List<Funcionario> findNomeDigitador(){
        return facade.findNomeDigitador();
    }
}
