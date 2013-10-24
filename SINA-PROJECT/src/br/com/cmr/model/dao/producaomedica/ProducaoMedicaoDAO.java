/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.model.dao.producaomedica;

import br.com.cmr.model.dao.conexao.CriaConexao;
import br.com.cmr.model.dao.conexao.DBConnection;
import br.com.cmr.model.entidade.producao.ProducaoMedica;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ritacosta
 */
public class ProducaoMedicaoDAO implements IProducaoMedicaDAO {

    public static final String sqlInsert = "insert into producao_medica "
            + "(data_entrada_cmr, prestador, procedimento, quantidade_laudos, "
            + "data_analise, funcionario, data_encaminhamento, nucleos) values (?,?,?,?,?,?,?,?)";
    public static final String sqlUpdate = "update producao_medica set data_entrada_cmr = ?, "
            + "prestador = ?, procedimento = ?, quantidade_laudos = ?, data_analise = ?"
            + ", funcionario = ?, data_encaminhamento = ?, nucleos = ?  where ID = ?";
    public static final String sqlDelete = "delete from producao_medica where ID = ? ";
    public static final String sqlList = "select * from producao_medica";
    public static final String sqlListNomePeriodo = "SELECT * FROM PRODUCAO_MEDICA WHERE FUNCIONARIO LIKE ? "
            + "AND DATA_ANALISE BETWEEN ? AND ?";

    @Override
    public int save(ProducaoMedica pMedica) {
        //Connection conn = DBConnection.getConnection();
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            int index = 0;
            pstm = conn.prepareStatement(sqlInsert);
            pstm.setDate(++index, pMedica.getEntradaCmr());
            pstm.setString(++index, pMedica.getPrestador_nome());
            pstm.setString(++index, pMedica.getProcedimento_nome());
            pstm.setInt(++index, pMedica.getQuantidade());
            pstm.setDate(++index, pMedica.getAnalise());
            pstm.setString(++index, pMedica.getFuncionario_nome());
            pstm.setDate(++index, pMedica.getEncaminhamento());
            pstm.setString(++index, pMedica.getNucleos());
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
    public int updade(ProducaoMedica pMedica) {
        //Connection conn = DBConnection.getConnection();
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            int index = 0;
            pstm = conn.prepareStatement(sqlUpdate);
            pstm.setDate(++index, pMedica.getEntradaCmr());
            pstm.setString(++index, pMedica.getPrestador_nome());
            pstm.setString(++index, pMedica.getProcedimento_nome());
            pstm.setInt(++index, pMedica.getQuantidade());
            pstm.setDate(++index, pMedica.getAnalise());
            pstm.setString(++index, pMedica.getFuncionario_nome());
            pstm.setDate(++index, pMedica.getEncaminhamento());
            pstm.setString(++index, pMedica.getNucleos());
            pstm.setLong(++index, pMedica.getId());
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
            pstm = conn.prepareStatement(sqlDelete);
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
                DBConnection.close(conn, pstm, null);
            }
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<ProducaoMedica> listar() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ProducaoMedica> pMedicas = new ArrayList();
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlList);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ProducaoMedica pMedica = new ProducaoMedica(
                        rs.getString("nucleos"),
                        rs.getInt("quantidade_laudos"),
                        rs.getDate("data_entrada_cmr"),
                        rs.getDate("data_analise"),
                        rs.getDate("data_encaminhamento"),
                        rs.getLong("id"),
                        rs.getString("funcionario"),
                        rs.getString("prestador"),
                        rs.getString("procedimento"));
                pMedicas.add(pMedica);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            } finally {
                // DBConnection.close(conn, pstm, rs);
                CriaConexao.close(conn, pstm, rs);
            }
            ex.printStackTrace();
        }
        return pMedicas;
    }

    @Override
    public List<ProducaoMedica> listarProMedica(String nome, Date dataDe, Date dataAte) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ProducaoMedica> pMedicas = new ArrayList();
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlListNomePeriodo);
            pstm.setString(1, nome);
            pstm.setDate(2, dataDe);
            pstm.setDate(3, dataAte);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ProducaoMedica pMedica = new ProducaoMedica(
                        rs.getString("nucleos"),
                        rs.getInt("quantidade_laudos"),
                        rs.getDate("data_entrada_cmr"),
                        rs.getDate("data_analise"),
                        rs.getDate("data_encaminhamento"),
                        rs.getLong("id"),
                        rs.getString("funcionario"),
                        rs.getString("prestador"),
                        rs.getString("procedimento"));
                pMedicas.add(pMedica);
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
        return pMedicas;
    }
}
