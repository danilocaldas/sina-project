/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.control.facade.producaomedica;

import br.com.cmr.model.dao.producaomedica.IProducaoMedicaDAO;
import br.com.cmr.model.dao.producaomedica.ProducaoMedicaoDAO;
import br.com.cmr.model.entidade.producao.ProducaoMedica;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ritacosta
 */
public class ProducaoMedicaFacade {
    
    private IProducaoMedicaDAO dao;

    public ProducaoMedicaFacade() {
        this.dao = new ProducaoMedicaoDAO();
    }
    
     public int save(ProducaoMedica pMedica){
        return dao.save(pMedica);
    }
    
    public int update(ProducaoMedica pMedica){
        return dao.updade(pMedica);
    }
    
    public int remove(Long id){
        return dao.delete(id);
    }
    public List<ProducaoMedica> findAll(){
        return dao.listar();
    }
    public List<ProducaoMedica> findAllPeriodo(String nome, Date dataDe, Date dataAte){
        return dao.listarProMedica(nome, dataDe, dataAte);
    }
}
