/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.control.controller.prestador;

import br.com.cmr.control.facade.prestador.PrestadorFacade;
import br.com.cmr.model.entidade.prestador.Prestador;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class PrestadorController {
    private PrestadorFacade facade;

    public PrestadorController() {
        this.facade = new PrestadorFacade();
    }
    
    public int addPrestador(Prestador prestador){
        return facade.save(prestador);
    }
    
    public int updatePrestador(Prestador prestador){
        return facade.update(prestador);
    }
    
    public int excluirPrestador(Long id){
        return facade.remove(id);
    }
    
    public List<Prestador> finfPrestador(){
        return facade.findAll();
    }
    public List<Prestador> findNome(String nome){
        return facade.findNome(nome);
    }
}
