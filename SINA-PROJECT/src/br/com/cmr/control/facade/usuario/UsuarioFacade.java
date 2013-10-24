/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.control.facade.usuario;

import br.com.cmr.model.dao.usuario.IUsuariosDAO;
import br.com.cmr.model.dao.usuario.UsuariosDAO;
import br.com.cmr.model.entidade.usuario.Usuarios;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class UsuarioFacade {
    
    private IUsuariosDAO dao;

    public UsuarioFacade() {
        this.dao = new UsuariosDAO();
    }
    
    public int save(Usuarios usuarios){
        return dao.save(usuarios);
    }
    
    public int update(Usuarios usuarios){
        return dao.update(usuarios);
    }
    
    public int delete(Long id){
        return dao.delete(id);
    }
    
    public List<Usuarios> listar(){
        return dao.listar();
    }
}
