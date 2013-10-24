/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.model.dao.prestador;

import br.com.cmr.model.entidade.prestador.Prestador;
import java.util.List;

/**
 *
 * @author Danilo
 */
public interface IPrestadorDAO {
    int save(Prestador prestador);
    int update(Prestador prestador);
    int remove(Long id);
    List<Prestador> findAll();
    List<Prestador> findNome(String nome);
}
