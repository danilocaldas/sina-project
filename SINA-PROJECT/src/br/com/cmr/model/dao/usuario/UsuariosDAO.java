/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.model.dao.usuario;

import br.com.cmr.model.dao.conexao.CriaConexao;
import br.com.cmr.model.entidade.usuario.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class UsuariosDAO implements IUsuariosDAO {

    private static final String sqlInsertUser = "insert into usuario (login, senha, role_user) values(?,?,?)";

    @Override
    public int save(Usuarios usuarios) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            int index = 0;
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlInsertUser);
            pstm.setString(++index, usuarios.getLogin());
            pstm.setString(++index, usuarios.getSenha());
            pstm.setString(++index, usuarios.getRole_user());
            result = pstm.executeUpdate();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            } finally {
                //DBConnection.close(conn, pstm, null);
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
        }
        return result;
    }

@Override
        public int update(Usuarios usuarios) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            int index = 0;
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlInsertUser);
            pstm.setString(++index, usuarios.getLogin());
            pstm.setString(++index, usuarios.getSenha());
            pstm.setString(++index, usuarios.getRole_user());
            pstm.setString(++index, usuarios.getLogin());
            result = pstm.executeUpdate();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            } finally {
                //DBConnection.close(conn, pstm, null);
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
        }
        return result; 
    }

    @Override
        public int delete(Long id) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlInsertUser);
            pstm.setLong(1, id);
            result = pstm.executeUpdate();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            } finally {
                //DBConnection.close(conn, pstm, null);
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
        }
        return result;
    }

    @Override
        public List<Usuarios> listar() {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Usuarios> usuarios = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlInsertUser);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Usuarios user = new  Usuarios();
                user.setId(rs.getLong("id"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                user.setRole_user(rs.getString("role_user"));
                usuarios.add(user);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            } finally {
                //DBConnection.close(conn, pstm, rs);
                CriaConexao.close(conn, pstm, rs);
            }
            ex.printStackTrace();
        }
        return usuarios;
    }
    
    
    
}
