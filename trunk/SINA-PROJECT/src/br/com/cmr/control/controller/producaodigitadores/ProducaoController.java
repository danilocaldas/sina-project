/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.control.controller.producaodigitadores;

import br.com.cmr.control.facade.producaodigitadores.ProducaoDigitadoresFacade;
import br.com.cmr.model.entidade.producao.ProducaoDigitadores;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class ProducaoController {

    private ProducaoDigitadoresFacade facade;

    public ProducaoController() {
        this.facade = new ProducaoDigitadoresFacade();
    }

    public int salvarProducao(ProducaoDigitadores producao) {
        return facade.save(producao);
    }

    public int atualizarProducao(ProducaoDigitadores producao) {
        return facade.update(producao);
    }

    public int deletarProducao(Long id) {
        return facade.remove(id);
    }

    public List<ProducaoDigitadores> listarProducao() {
        return facade.findAll();
    }

    public List<ProducaoDigitadores> listarProPeriodo(Date dataInicial, Date dataFinal) {
        return facade.findProPeriodo(dataInicial, dataFinal);
    }

    public List<ProducaoDigitadores> listarProFuncionario(String funcionario) {
        return facade.findProFuncionario(funcionario);
    }
    
     public List<ProducaoDigitadores> listarProFunPeriodo(String funcionario, Date dataInicial, Date dataFinal) {
        return facade.findProFuncioPeriodo(funcionario, dataInicial, dataFinal);
    }
    
    public List<ProducaoDigitadores> listarProPrestador(String prestador) {
        return facade.findProPrestador(prestador);
    }
}
