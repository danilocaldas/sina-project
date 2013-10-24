/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.model.dao.producaodigitadores;

import br.com.cmr.model.entidade.producao.ProducaoDigitadores;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Danilo
 */
public interface IProducaoDigitadoresDAO {
    
    int save(ProducaoDigitadores producaoDigitadores);
    int update(ProducaoDigitadores producaoDigitadores);
    int excluir(Long id);
    List<ProducaoDigitadores> findAll();
    List<ProducaoDigitadores> findProPeriodo(Date dataInicial, Date dataFinal);
    List<ProducaoDigitadores> findProFuncionario(String funcionario);
    List<ProducaoDigitadores> findProFuncioPeriodo(String funcionario, Date dataInicial, Date dataFinal);
    List<ProducaoDigitadores> findProPrestador(String prestador);
    
    
}
