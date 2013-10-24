/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.view.producaomedica;

import br.com.cmr.control.controller.funcionario.FuncionarioController;
import br.com.cmr.control.controller.prestador.PrestadorController;
import br.com.cmr.control.controller.procedimento.ProcedimentoController;
import br.com.cmr.control.controller.producaomedica.ProducaoMedicaController;
import br.com.cmr.model.entidade.funcionario.Funcionario;
import br.com.cmr.model.entidade.prestador.Prestador;
import br.com.cmr.model.entidade.procedimento.Procedimento;
import br.com.cmr.model.entidade.producao.ProducaoMedica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ritacosta
 */
public class ProducaoMedicaActionControl implements ActionListener {

    public FormProducaoMedica form;
    public List<Procedimento> listProdimento;
    public List<Prestador> listPrestador;
    public List<Funcionario> listFuncionario;
    public List<ProducaoMedica> listProducaoMedica;
    private Long idProducaoMedica;

    public ProducaoMedicaActionControl(FormProducaoMedica form) {
        this.form = form;
        addControleBtn();
        refreshComboPesquisaMedica();
        enableFilds(false);

    }

    private void addControleBtn() {
        form.getBtNovo().addActionListener(this);
        form.getBtAtualizar().addActionListener(this);
        form.getBtSalvar().addActionListener(this);
        form.getBtExcluir().addActionListener(this);
        form.getBtCancelar().addActionListener(this);
        form.getBtPesquisar().addActionListener(this);

    }

    private void refreshCombo() {
        listFuncionario = new FuncionarioController().findNomeMedico();
        listPrestador = new PrestadorController().findNome("%%");
        listProdimento = new ProcedimentoController().finfNome("%%");
        form.getComboFuncionario().removeAllItems();
        form.getComboPrestador().removeAllItems();
        form.getComboProcedimento().removeAllItems();

        if (listFuncionario != null) {
            for (int i = 0; i < listFuncionario.size(); i++) {
                form.getComboFuncionario().addItem(listFuncionario.get(i).getNome());
            }
        }
        if (listPrestador != null) {
            for (int i = 0; i < listPrestador.size(); i++) {
                form.getComboPrestador().addItem(listPrestador.get(i).getNome());
            }
        }
        if (listProdimento != null) {
            for (int i = 0; i < listProdimento.size(); i++) {
                form.getComboProcedimento().addItem(listProdimento.get(i).getNome());
            }
        }
    }

    private void refreshComboPesquisaMedica() {
        listFuncionario = new FuncionarioController().findNomeMedico();
        form.getComboPesquisaMedico().removeAllItems();

        if (listFuncionario != null) {
            for (int i = 0; i < listFuncionario.size(); i++) {

                form.getComboPesquisaMedico().addItem(listFuncionario.get(i).getNome());
            }
        }
    }

    private void mostraDadosTable() {
        if (listProducaoMedica != null) {
            form.getTbProducaoMedica().setModel(new ProducaoMedicaTableModel(listProducaoMedica));
            form.getTbProducaoMedica().setDefaultRenderer(Object.class, new ProducaoMedicaCellRenderer());
        }
    }

//    private void refreshTable() {
//        listProducaoMedica = new ProducaoMedicaController().listarProducao();
//        mostraDadosTable();
//        somar();
//    }
    private void refreshTableNomePeriodo() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataDe = (java.util.Date) form.getTxtDataDe().getDate();
        java.util.Date dataAte = (java.util.Date) form.getTxtDataAte().getDate();
        listProducaoMedica = new ProducaoMedicaController().listarProducaoFunMedico(
                "%" + form.getComboPesquisaMedico().getSelectedItem().toString() + "%", Date.valueOf(formato.format(dataDe)), Date.valueOf(formato.format(dataAte)));
        mostraDadosTable();
        somarQtdAnalisada();

    }

    private void enableFilds(boolean enabled) {

        form.getTxtDataEntrada().setEnabled(enabled);
        form.getComboPrestador().setEnabled(enabled);
        form.getComboProcedimento().setEnabled(enabled);
        form.getTxtQtdLaudos().setEnabled(enabled);
        form.getTxtDataAnalise().setEnabled(enabled);
        form.getComboFuncionario().setEnabled(enabled);
        form.getTxtEncaminhamento().setEnabled(enabled);
        form.getComboNucleos().setEnabled(enabled);
    }

    private void onCancelar() {
        limparCombo();
        form.getTxtDataAnalise().setDate(null);
        form.getTxtEncaminhamento().setDate(null);
        form.getTxtDataEntrada().setDate(null);
        form.getTxtQtdLaudos().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Novo":
                enableFilds(true);
                refreshCombo();
                break;
            case "Cancelar":
                onCancelar();
                enableFilds(false);
                break;
            case "Salvar":
                onSaveProducao();
                break;
            case "Atualizar":
                onAlterarProducao();
                break;
            case "Excluir":
                removerProducao();
                break;
            case "PESQUISAR":
                if (form.getTxtDataDe().getDate() != null && form.getTxtDataAte().getDate() != null) {
                    refreshTableNomePeriodo();
                } else {
                    JOptionPane.showMessageDialog(form, "As datas são necessárias para pesquisa.",
                            "Validação", JOptionPane.ERROR_MESSAGE);
                }
        }
    }

    private boolean verificarPreencherDatas() {
        if (form.getTxtDataEntrada().getDate() != null && form.getTxtDataAnalise().getDate() != null
                && form.getTxtEncaminhamento().getDate() != null) {
            return true;
        } else {
            return false;
        }
    }

    private void onSaveProducao() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataEntrada = (java.util.Date) form.getTxtDataEntrada().getDate();
        java.util.Date dataAnalise = (java.util.Date) form.getTxtDataAnalise().getDate();
        java.util.Date dataEncaminhamento = (java.util.Date) form.getTxtEncaminhamento().getDate();
        ProducaoMedica pMedica = new ProducaoMedica(
                form.getComboNucleos().getSelectedItem().toString(),
                Integer.parseInt(form.getTxtQtdLaudos().getText()),
                Date.valueOf(formato.format(dataEntrada)),
                Date.valueOf(formato.format(dataAnalise)),
                Date.valueOf(formato.format(dataEncaminhamento)),
                Long.MAX_VALUE,
                form.getComboFuncionario().getSelectedItem().toString(),
                form.getComboPrestador().getSelectedItem().toString(),
                form.getComboProcedimento().getSelectedItem().toString());

        int result = 0;
        if (idProducaoMedica == null) {
            result = new ProducaoMedicaController().salvarProducaoMedica(pMedica);
        } else {
            pMedica.setId(idProducaoMedica);
            result = new ProducaoMedicaController().atualizarProducaoMedica(pMedica);
            idProducaoMedica = null;
        }
        if (result == 1) {
            JOptionPane.showMessageDialog(form, "Produção inserido com sucesso!");
            //enableFilds(false);
            //onCancelar();
        } else {
            JOptionPane.showMessageDialog(form, "Tente novamente!");
        }
    }

    private void onAlterarProducao() {
        int indexRow = form.getTbProducaoMedica().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(form, "Selecione a produção a ser alterada!");
            return;
        }
        ProducaoMedica pMedica = new ProducaoMedicaTableModel(listProducaoMedica).get(indexRow);

        idProducaoMedica = pMedica.getId();
        form.getLabelID().setText(String.valueOf(pMedica.getId()));
        form.getTxtDataEntrada().setDate(pMedica.getEntradaCmr());
        form.getComboPrestador().setSelectedItem(pMedica.getPrestador_nome());
        form.getComboProcedimento().setSelectedItem(pMedica.getProcedimento_nome());
        form.getTxtQtdLaudos().setText(String.valueOf(pMedica.getQuantidade()));
        form.getTxtDataAnalise().setDate(pMedica.getAnalise());
        form.getComboFuncionario().setSelectedItem(pMedica.getFuncionario_nome());
        form.getTxtEncaminhamento().setDate(pMedica.getEncaminhamento());
        form.getComboNucleos().setSelectedItem(pMedica.getNucleos());
        //enableFilds(true);
        //refreshCombo();
    }

    private void removerProducao() {
        int indexRow = form.getTbProducaoMedica().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(form, "Selecione a produção a ser removida!");
            return;
        }
        ProducaoMedica pMedica = new ProducaoMedicaTableModel(listProducaoMedica).get(indexRow);
        int confirm = JOptionPane.showConfirmDialog(form, "Confirmar a exclusão?", "Excluir Produção", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }
        int result = new ProducaoMedicaController().deletarProducao(pMedica.getId());
        if (result == 1) {
            JOptionPane.showMessageDialog(form, "Produção removida com sucesso!");
            refreshTableNomePeriodo();
        } else {
            JOptionPane.showMessageDialog(form, "Tente novamente!");
        }
    }

    private void somarQtdAnalisada() {
        int total = 0;
        String valor = "";
        int linhas = form.getTbProducaoMedica().getRowCount();
        for (int i = 0; i < linhas; i++) {
            valor = String.valueOf(form.getTbProducaoMedica().getValueAt(i, 4));
            total = total + Integer.parseInt(valor);
        }
        form.getLabelTotal().setText("" + total);
    }

    private void limparCombo() {
        form.getComboFuncionario().removeAllItems();
        form.getComboPrestador().removeAllItems();
        form.getComboProcedimento().removeAllItems();
    }

}
