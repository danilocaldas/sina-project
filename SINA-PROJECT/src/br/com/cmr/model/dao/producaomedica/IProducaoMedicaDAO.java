/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.model.dao.producaomedica;

import br.com.cmr.model.entidade.producao.ProducaoMedica;
import java.sql.Date;
import java.util.List;
/**
 *
 * @author ritacosta
 */
public interface IProducaoMedicaDAO {

    int save(ProducaoMedica pMedica);

    int updade(ProducaoMedica pMedica);

    int delete(Long id);

    List<ProducaoMedica> listar();
    
    List<ProducaoMedica> listarProMedica(String nome, Date dataDe, Date dataAte);
}
