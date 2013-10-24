/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.control.facade.producaodigitadores;

import br.com.cmr.model.dao.producaodigitadores.IProducaoDigitadoresDAO;
import br.com.cmr.model.dao.producaodigitadores.ProducaoDigitadoresDAO;
import br.com.cmr.model.entidade.producao.ProducaoDigitadores;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class ProducaoDigitadoresFacade {
    
    private IProducaoDigitadoresDAO dao;

    public ProducaoDigitadoresFacade() {
        this.dao = new ProducaoDigitadoresDAO();
    }
    
    public int save(ProducaoDigitadores producaoDigitadores){
        return dao.save(producaoDigitadores);
    }
    
    public int update(ProducaoDigitadores producaoDigitadores){
        return dao.update(producaoDigitadores);
    }
    
    public int remove(Long id){
        return dao.excluir(id);
    }
    public List<ProducaoDigitadores> findAll(){
        return dao.findAll();
    }
    
    public List<ProducaoDigitadores> findProPeriodo(Date dataInicial, Date dataFinal){
        return dao.findProPeriodo(dataInicial, dataFinal);
    }
    
    public List<ProducaoDigitadores> findProFuncionario(String funcionario){
        return  dao.findProFuncionario(funcionario);
    }
    
    public List<ProducaoDigitadores> findProFuncioPeriodo(String funcionario, Date dataInicial, Date dataFinal){
        return dao.findProFuncioPeriodo(funcionario, dataInicial, dataFinal);
    }
    
    public List<ProducaoDigitadores> findProPrestador(String prestador){
        return dao.findProPrestador(prestador);
    }
}
