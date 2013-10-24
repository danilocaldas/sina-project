/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.view.inicio;


import br.com.cmr.view.acesso.Acesso;
import br.com.cmr.view.funcionario.FormFuncionario;
import br.com.cmr.view.prestador.FormPrestador;
import br.com.cmr.view.procedimento.FormProcedimemto;
import br.com.cmr.view.producaodigitadores.FormProducao;
import br.com.cmr.view.producaomedica.FormProducaoMedica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author ritacosta
 */
public class SistemaActionControl implements ActionListener {

    public FormSistema form;

    public SistemaActionControl(FormSistema form) {
        this.form = form;
        addControlBtMenusForm();
        form.setExtendedState(form.getExtendedState()| JFrame.MAXIMIZED_BOTH);
    }
    
    private void addControlBtMenusForm() {
        form.getMenuProducaoMedica().addActionListener(this);
        form.getMenuPrestadores().addActionListener(this);
        form.getMenuProcedimentos().addActionListener(this);
        form.getMenuFuncionarios().addActionListener(this);
        form.getMenuProdDigitadores().addActionListener(this);
        form.getMenuTrocarUsuario().addActionListener(this);
        form.getMenuSair().addActionListener(this);
    }

    private void abrirProducaoMedica() {
        FormProducaoMedica formSis = new FormProducaoMedica();
        form.getjDesktopPaneSistema().add(formSis);
        formSis.show();
    }

    private void abrirPrestadores() {
        FormPrestador formSisPres = new FormPrestador();
        form.getjDesktopPaneSistema().add(formSisPres);
        formSisPres.show();
    }

    private void abrirProcedimentos() {
        FormProcedimemto formSisProc = new FormProcedimemto();
        form.getjDesktopPaneSistema().add(formSisProc);
        formSisProc.show();
    }

    private void abrirFuncionarios() {
        FormFuncionario formSisFunc = new FormFuncionario();
        form.getjDesktopPaneSistema().add(formSisFunc);
        formSisFunc.show();
    }

    private void abrirProdDigitadores() {
        FormProducao formSisFormProdu = new FormProducao();
        form.getjDesktopPaneSistema().add(formSisFormProdu);
        formSisFormProdu.show();
    }

   @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Produção Médica")) {
            abrirProducaoMedica();
        } else if (e.getActionCommand().equals("Prestadores")) {
            abrirPrestadores();
        } else if (e.getActionCommand().equals("Procedimentos")) {
            abrirProcedimentos();
        } else if (e.getActionCommand().equals("Funcionários")) {
            abrirFuncionarios();
        } else if (e.getActionCommand().equals("Produção digitadores")) {
            abrirProdDigitadores();
        } else if (e.getActionCommand().equals("Trocar usuário")) {
            form.dispose();
            Acesso a = new Acesso();
            a.setVisible(true);
        } else if (e.getActionCommand().equals("Sair")) {
            System.exit(0);
        }
    }
}
