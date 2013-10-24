/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.control.facade.procedimento;

import br.com.cmr.model.dao.procedimento.IProcedimentoDAO;
import br.com.cmr.model.dao.procedimento.ProcedimentoDAO;
import br.com.cmr.model.entidade.procedimento.Procedimento;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class ProcedimentoFacade {

    private IProcedimentoDAO dao;

    public ProcedimentoFacade() {
        this.dao = new ProcedimentoDAO();
    }

    public int save(Procedimento procedimento) {
        return dao.save(procedimento);
    }

    public int update(Procedimento procedimento) {
        return dao.update(procedimento);
    }

    public int remove(Long id) {
        return dao.remove(id);
    }

    public List<Procedimento> findAll() {
        return dao.findAll();
    }
    
    public List<Procedimento> findNome(String nome) {
        return dao.findNome(nome);
    }
}
