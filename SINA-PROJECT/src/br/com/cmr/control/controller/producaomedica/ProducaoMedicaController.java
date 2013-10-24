/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.control.controller.producaomedica;

import br.com.cmr.control.facade.producaomedica.ProducaoMedicaFacade;
import br.com.cmr.model.entidade.producao.ProducaoMedica;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ritacosta
 */
public class ProducaoMedicaController {
    
    private ProducaoMedicaFacade facade;
    
    public ProducaoMedicaController() {
        this.facade = new ProducaoMedicaFacade();
    }
    
    public int salvarProducaoMedica(ProducaoMedica pMedica) {
        return facade.save(pMedica);
    }

    public int atualizarProducaoMedica(ProducaoMedica pMedica) {
        return facade.update(pMedica);
    }

    public int deletarProducao(Long id) {
        return facade.remove(id);
    }

    public List<ProducaoMedica> listarProducao() {
        return facade.findAll();
    }
    
    public List<ProducaoMedica> listarProducaoFunMedico(String nome, Date dataDe, Date dataAte) {
        return facade.findAllPeriodo(nome, dataDe, dataAte);
    }
}
