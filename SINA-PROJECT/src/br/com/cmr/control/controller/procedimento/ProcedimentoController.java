/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.control.controller.procedimento;

import br.com.cmr.control.facade.procedimento.ProcedimentoFacade;
import br.com.cmr.model.entidade.procedimento.Procedimento;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class ProcedimentoController {
    
    private ProcedimentoFacade facade;

    public ProcedimentoController() {
        this.facade = new ProcedimentoFacade();
    }
    
    public int addProcedimento(Procedimento procedimento){
        return facade.save(procedimento);
    }
    
    public int updateProcedimento(Procedimento procedimento){
        return facade.update(procedimento);
    }
    
    public int excluirProcedimento(Long id){
        return facade.remove(id);
    }
    
    public List<Procedimento> finfPProcedimento(){
        return facade.findAll();
    }
    
    public List<Procedimento> finfNome(String nome){
        return facade.findNome(nome);
    }
}
