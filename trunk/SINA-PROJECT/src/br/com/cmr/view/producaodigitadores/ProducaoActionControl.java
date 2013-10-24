/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.view.producaodigitadores;

import br.com.cmr.control.controller.funcionario.FuncionarioController;
import br.com.cmr.control.controller.prestador.PrestadorController;
import br.com.cmr.control.controller.procedimento.ProcedimentoController;
import br.com.cmr.control.controller.producaodigitadores.ProducaoController;
import br.com.cmr.model.entidade.funcionario.Funcionario;
import br.com.cmr.model.entidade.prestador.Prestador;
import br.com.cmr.model.entidade.procedimento.Procedimento;
import br.com.cmr.model.entidade.producao.Producao;
import br.com.cmr.model.entidade.producao.ProducaoDigitadores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class ProducaoActionControl implements ActionListener {

    public FormProducao frm;
    public List<ProducaoDigitadores> listProducao;
    public List<Procedimento> listProdimento;
    public List<Prestador> listPrestador;
    public List<Funcionario> listFuncionario;
    private Long idProducao;

    public ProducaoActionControl(FormProducao frm) {
        this.frm = frm;
        addButoesToForm();
        //refreshTable();
        refreshCombo();
        enableFilds(false);
        frm.getTxtId().setEnabled(false);
    }

    private void refreshCombo() {
        listFuncionario = new FuncionarioController().findNomeDigitador();
        listPrestador = new PrestadorController().findNome("%%");
        listProdimento = new ProcedimentoController().finfNome("%%");
        frm.getComboFuncionario().removeAllItems();
        frm.getComboPrestador().removeAllItems();
        frm.getComboProcedimento().removeAllItems();

        frm.getComboPesquisaFuncionario().removeAllItems();
        frm.getComboPesquisaPrestador().removeAllItems();

        if (listFuncionario != null) {
            for (int i = 0; i < listFuncionario.size(); i++) {
                frm.getComboFuncionario().addItem(listFuncionario.get(i).getNome());
                frm.getComboPesquisaFuncionario().addItem(listFuncionario.get(i).getNome());
            }
        }
        if (listPrestador != null) {
            for (int i = 0; i < listPrestador.size(); i++) {
                frm.getComboPrestador().addItem(listPrestador.get(i).getNome());
                frm.getComboPesquisaPrestador().addItem(listPrestador.get(i).getNome());
            }
        }
        if (listProdimento != null) {
            for (int i = 0; i < listProdimento.size(); i++) {
                frm.getComboProcedimento().addItem(listProdimento.get(i).getNome());
            }
        }
    }

    private void mostraDadosTable() {
        if (listProducao != null) {
            frm.getTbProducao().setModel(new ProducaoTableModel(listProducao));
            frm.getTbProducao().setDefaultRenderer(Object.class, new ProducaoCellRenderer());
        }
    }

    private void refreshTable() {
        listProducao = new ProducaoController().listarProducao();
        mostraDadosTable();
    }

    private void refreshTableFuncionario() {
        listProducao = new ProducaoController().listarProFuncionario("%" + frm.getComboPesquisaFuncionario().
                getSelectedItem().toString() + "%");
        mostraDadosTable();
    }

    private void refreshTablePrestador() {
        listProducao = new ProducaoController().listarProPrestador("%" + frm.getComboPesquisaPrestador().
                getSelectedItem().toString() + "%");
        mostraDadosTable();
    }

    private void refreshTablePeriodo() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataInicial = (java.util.Date) frm.getTxtDataDe().getDate();
        java.util.Date dataFinal = (java.util.Date) frm.getTxtDataAte().getDate();
        listProducao = new ProducaoController().listarProPeriodo(Date.valueOf(formato.format(dataInicial)),
                Date.valueOf(formato.format(dataFinal)));
        mostraDadosTable();
    }

    private void refreshTableFuncionarioPeriodo() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataInicial = (java.util.Date) frm.getTxtDataDe().getDate();
        java.util.Date dataFinal = (java.util.Date) frm.getTxtDataAte().getDate();
        listProducao = new ProducaoController().listarProFunPeriodo(
                "%" + frm.getComboPesquisaFuncionario().getSelectedItem().toString() + "%",
                Date.valueOf(formato.format(dataInicial)),
                Date.valueOf(formato.format(dataFinal)));
        mostraDadosTable();
    }

    private void enableFilds(boolean enabled) {
        frm.getComboFuncionario().setEnabled(enabled);
        frm.getComboPrestador().setEnabled(enabled);
        frm.getComboProcedimento().setEnabled(enabled);
        frm.getTxtDataEntrada().setEnabled(enabled);
        frm.getTxtDataDigitacao().setEnabled(enabled);
        frm.getTxtQuantidade().setEnabled(enabled);

    }

    private void onCancelar() {
        frm.getTxtDataDigitacao().setDate(null);
        frm.getTxtDataEntrada().setDate(null);
        frm.getTxtQuantidade().setText("");
        frm.getTxtId().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Salvar")) {
            onSaveProducao();
        } else if (e.getActionCommand().equals("Novo")) {
            enableFilds(true);
        } else if (e.getActionCommand().equals("Cancelar")) {
            onCancelar();
        } else if (e.getActionCommand().equals("Excluir")) {
            removerProducao();
        } else if (e.getActionCommand().equals("Atualizar")) {
            onAlterarProducao();
        } else if (e.getActionCommand().equals("Pesquisar")) {
            pesquisarProducaoGeral();
            pesquisarProdPorProfissional();
            pesquisarProdPorPrestador();
            pesquisarProdPorPeriodo();
            pesquisarProdPorPeriodoProfissional();
        }
    }

    private boolean verificarPreencherDatas() {
        if (frm.getTxtDataDigitacao().getDate() != null && frm.getTxtDataEntrada().getDate() != null && frm.getTxtQuantidade().getText().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void onSaveProducao() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataEntrada = (java.util.Date) frm.getTxtDataEntrada().getDate();
        java.util.Date dataDigitacao = (java.util.Date) frm.getTxtDataDigitacao().getDate();
        ProducaoDigitadores producaoDigitadores = new ProducaoDigitadores(
                Date.valueOf(formato.format(dataEntrada)),
                Date.valueOf(formato.format(dataDigitacao)),
                Integer.valueOf(frm.getTxtQuantidade().getText()),
                Long.MAX_VALUE,
                frm.getComboFuncionario().getSelectedItem().toString(),
                frm.getComboPrestador().getSelectedItem().toString(),
                frm.getComboProcedimento().getSelectedItem().toString());

        int result = 0;
        if (idProducao == null) {
            result = new ProducaoController().salvarProducao(producaoDigitadores);
        } else {
            producaoDigitadores.setId(idProducao);
            result = new ProducaoController().atualizarProducao(producaoDigitadores);
            idProducao = null;
        }

        if (result == 1) {
            JOptionPane.showMessageDialog(frm, "Produção inserido com sucesso!");
            enableFilds(false);
            onCancelar();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(frm, "Tente novamente!");
        }
    }

    private void onAlterarProducao() {
        int indexRow = frm.getTbProducao().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(frm, "Selecione a produção a ser alterada!");
            return;
        }
        ProducaoDigitadores producaoDigitadores = new ProducaoTableModel(listProducao).get(indexRow);

        idProducao = producaoDigitadores.getId();
        frm.getTxtId().setText(String.valueOf(producaoDigitadores.getId()));
        frm.getComboFuncionario().setSelectedItem(producaoDigitadores.getFuncionario_nome());
        frm.getComboPrestador().setSelectedItem(producaoDigitadores.getPrestador_nome());
        frm.getComboProcedimento().setSelectedItem(producaoDigitadores.getProcedimento_nome());
        frm.getTxtDataEntrada().setDate(producaoDigitadores.getEntrada());
        frm.getTxtDataDigitacao().setDate(producaoDigitadores.getDigitacao());
        frm.getTxtQuantidade().setText(String.valueOf(producaoDigitadores.getQuantidade()));
        enableFilds(true);

    }

    private void removerProducao() {
        int indexRow = frm.getTbProducao().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(frm, "Selecione a produção a ser removida!");
            return;
        }
        Producao producao = new ProducaoTableModel(listProducao).get(indexRow);
        int confirm = JOptionPane.showConfirmDialog(frm, "Confirmar a exclusão?", "Excluir Produção", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }
        int result = new ProducaoController().deletarProducao(producao.getId());
        if (result == 1) {
            JOptionPane.showMessageDialog(frm, "Produção removida com sucesso!");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(frm, "Tente novamente!");
        }
    }

    private void addButoesToForm() {
        frm.getBtNovo().addActionListener(this);
        frm.getBtAtualizar().addActionListener(this);
        frm.getBtSalvar().addActionListener(this);
        frm.getBtExcluir().addActionListener(this);
        frm.getBtCancelar().addActionListener(this);
        frm.getBtPesquisar().addActionListener(this);
        frm.getRadioGeral().addActionListener(this);
        frm.getRadioFuncionario().addActionListener(this);
        frm.getRadioFuncionarioPeriodo().addActionListener(this);
        frm.getRadioPeriodo().addActionListener(this);
        frm.getRadioPrestador().addActionListener(this);
    }

    private boolean validarCamposDataPesquisa() {
        if (frm.getTxtDataDe().getDate() != null && frm.getTxtDataAte().getDate() != null) {
            return true;
        }
        JOptionPane.showMessageDialog(frm, "É necessário o preechimento do período desejado!", "Pesquisa", JOptionPane.INFORMATION_MESSAGE);
        return false;
    }

    public void pesquisarProducaoGeral() {
        if (frm.getRadioGeral().isSelected()) {
            //limparTabela(producoes);
            refreshTable();
        }
    }

    public void pesquisarProdPorPeriodo() {
        if (frm.getRadioPeriodo().isSelected() && validarCamposDataPesquisa()) {
            //limparTabela(producoes);
            refreshTablePeriodo();
        }
    }

    public void pesquisarProdPorProfissional() {
        if (frm.getRadioFuncionario().isSelected()) {
            //limparTabela(producoes);
            refreshTableFuncionario();
        }
    }

    public void pesquisarProdPorPeriodoProfissional() {
        if (frm.getRadioPeriodo().isSelected() && validarCamposDataPesquisa()) {
            //limparTabela(producoes);
            refreshTableFuncionarioPeriodo();
        }
    }

    public void pesquisarProdPorPrestador() {
        if (frm.getRadioPrestador().isSelected()) {
            //limparTabela(producoes);
            refreshTablePrestador();
        }
    }
}
