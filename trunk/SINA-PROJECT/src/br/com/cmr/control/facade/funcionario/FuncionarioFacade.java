/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.control.facade.funcionario;

import br.com.cmr.model.dao.funcionario.FuncionarioDAO;
import br.com.cmr.model.dao.funcionario.IFuncionarioDAO;
import br.com.cmr.model.entidade.funcionario.Funcionario;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class FuncionarioFacade {
    
    private IFuncionarioDAO dao;

    public FuncionarioFacade() {
        this.dao = new FuncionarioDAO();
    }
    
    public int save (Funcionario funcionario){
        return dao.save(funcionario);
    }
    
    public int update(Funcionario funcionario){
        return dao.update(funcionario);
    }

    public int remove(Long id){
        return dao.remove(id);
    }
    
    public List<Funcionario> findAll(){
        return dao.findAll();
    }
    
    public List<Funcionario> findNome(String nome){
        return dao.findNome(nome);
    }
    
    public List<Funcionario> findNomeMedico(){
        return dao.findNomeMedico();
    }
    
    public List<Funcionario> findNomeDigitador(){
        return dao.findNomeDigitador();
    }
}
