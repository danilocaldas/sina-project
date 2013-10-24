/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.control.controller.usuario;

import br.com.cmr.control.facade.usuario.UsuarioFacade;
import br.com.cmr.model.entidade.usuario.Usuarios;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class UsuariosController {

    private UsuarioFacade facade;

    public UsuariosController() {
        this.facade = new UsuarioFacade();
    }

    public int save(Usuarios usuarios) {
        return facade.save(usuarios);
    }

    public int update(Usuarios usuarios) {
        return facade.update(usuarios);
    }

    public int delete(Long id) {
        return facade.delete(id);
    }
    
    public List<Usuarios> listar(){
        return facade.listar();
    }
}
