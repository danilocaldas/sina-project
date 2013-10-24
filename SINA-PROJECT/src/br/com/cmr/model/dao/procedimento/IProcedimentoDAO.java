/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.model.dao.procedimento;

import br.com.cmr.model.entidade.procedimento.Procedimento;
import java.util.List;

/**
 *
 * @author Danilo
 */
public interface IProcedimentoDAO {
    int save(Procedimento procedimento);
    int update(Procedimento procedimento);
    int remove(Long id);
    List<Procedimento> findAll();
    List<Procedimento> findNome(String nome);
}
