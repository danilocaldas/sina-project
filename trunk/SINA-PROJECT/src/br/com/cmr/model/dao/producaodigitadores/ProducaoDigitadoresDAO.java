/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.model.dao.producaodigitadores;

import br.com.cmr.model.dao.conexao.DBConnection;
import br.com.cmr.model.entidade.producao.ProducaoDigitadores;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class ProducaoDigitadoresDAO implements IProducaoDigitadoresDAO {

    private static final String sqlInsert = "insert into PRODUCAO (FUNCIONARIO, PRESTADOR, PROCEDIMENTO,"
            + "DATA_ENTRADA, DATA_DIGITACAO, QUANTIDADE)"
            + "VALUES (?,?,?,?,?,?)";
    private static final String sqlUpdate = "update PRODUCAO set FUNCIONARIO = ?, "
            + "PRESTADOR = ?, PROCEDIMENTO = ?, DATA_ENTRADA = ?"
            + ", DATA_DIGITACAO = ?, QUANTIDADE = ?  where ID = ?";
    private static final String sqlDelete = "delete from PRODUCAO where ID = ? ";
    //CONSULTAS
    private static final String sqlFindAll = "select * from PRODUCAO";
    private static final String sqlFindProPeriodo = "select * from PRODUCAO where DATA_DIGITACAO between ? and ?";
    private static final String sqlFindProFuncionario = "select * from PRODUCAO where FUNCIONARIO like ?";
    private static final String sqlFindProFuncioPeriodo = "select * from PRODUCAO where FUNCIONARIO like ?"
            + " and DATA_DIGITACAO between ? and ?";
    private static final String sqlFindProPrestador = "select * from PRODUCAO where PRESTADOR like ?";

    @Override
    public int save(ProducaoDigitadores producaoDigitadores) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
        try {
            pstm = conn.prepareStatement(sqlInsert);
            pstm.setString(1, producaoDigitadores.getFuncionario_nome());
            pstm.setString(2, producaoDigitadores.getPrestador_nome());
            pstm.setString(3, producaoDigitadores.getProcedimento_nome());
            pstm.setDate(4, producaoDigitadores.getEntrada());
            pstm.setDate(5, producaoDigitadores.getDigitacao());
            pstm.setInt(6, producaoDigitadores.getQuantidade());
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
    public int update(ProducaoDigitadores producaoDigitadores) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
        try {
            pstm = conn.prepareStatement(sqlUpdate);
            pstm.setString(1, producaoDigitadores.getFuncionario_nome());
            pstm.setString(2, producaoDigitadores.getPrestador_nome());
            pstm.setString(3, producaoDigitadores.getProcedimento_nome());
            pstm.setDate(4, producaoDigitadores.getEntrada());
            pstm.setDate(5, producaoDigitadores.getDigitacao());
            pstm.setInt(6, producaoDigitadores.getQuantidade());
            pstm.setLong(7, producaoDigitadores.getId());
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
    public int excluir(Long id) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
        try {
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
    public List<ProducaoDigitadores> findAll() {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        List<ProducaoDigitadores> producoes = new ArrayList<ProducaoDigitadores>();
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sqlFindAll);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ProducaoDigitadores producao = new ProducaoDigitadores(rs.getDate("data_entrada"),
                        rs.getDate("data_digitacao"),
                        rs.getInt("quantidade"),
                        rs.getLong("id"),
                        rs.getString("funcionario"),
                        rs.getString("prestador"),
                        rs.getString("procedimento"));
                producoes.add(producao);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            } finally {
                DBConnection.close(conn, pstm, rs);
            }
            ex.printStackTrace();
        }
        return producoes;
    }

    @Override
    public List<ProducaoDigitadores> findProPeriodo(Date dataInicial, Date dataFinal) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        List<ProducaoDigitadores> producoes = new ArrayList<ProducaoDigitadores>();
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sqlFindProPeriodo);
            pstm.setDate(1, dataInicial);
            pstm.setDate(2, dataFinal);
            rs = pstm.executeQuery();
            while (rs.next()) {
                 ProducaoDigitadores producao = new ProducaoDigitadores(rs.getDate("data_entrada"),
                        rs.getDate("data_digitacao"),
                        rs.getInt("quantidade"),
                        rs.getLong("id"),
                        rs.getString("funcionario"),
                        rs.getString("prestador"),
                        rs.getString("procedimento"));
                producoes.add(producao);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            } finally {
                DBConnection.close(conn, pstm, rs);
            }
            ex.printStackTrace();
        }
        return producoes;
    }

    @Override
    public List<ProducaoDigitadores> findProFuncionario(String funcionario) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        List<ProducaoDigitadores> producoes = new ArrayList<ProducaoDigitadores>();
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sqlFindProFuncionario);
            pstm.setString(1, funcionario);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ProducaoDigitadores producao = new ProducaoDigitadores(rs.getDate("data_entrada"),
                        rs.getDate("data_digitacao"),
                        rs.getInt("quantidade"),
                        rs.getLong("id"),
                        rs.getString("funcionario"),
                        rs.getString("prestador"),
                        rs.getString("procedimento"));
                producoes.add(producao);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            } finally {
                DBConnection.close(conn, pstm, rs);
            }
            ex.printStackTrace();
        }
        return producoes;
    }

    @Override
    public List<ProducaoDigitadores> findProFuncioPeriodo(String funcionario, Date dataInicial, Date dataFinal) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        List<ProducaoDigitadores> producoes = new ArrayList<ProducaoDigitadores>();
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sqlFindProFuncioPeriodo);
            pstm.setString(1, funcionario);
            pstm.setDate(2, dataInicial);
            pstm.setDate(3, dataFinal);
            rs = pstm.executeQuery();
            while (rs.next()) {
                 ProducaoDigitadores producao = new ProducaoDigitadores(rs.getDate("data_entrada"),
                        rs.getDate("data_digitacao"),
                        rs.getInt("quantidade"),
                        rs.getLong("id"),
                        rs.getString("funcionario"),
                        rs.getString("prestador"),
                        rs.getString("procedimento"));
                producoes.add(producao);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            } finally {
                DBConnection.close(conn, pstm, rs);
            }
            ex.printStackTrace();
        }
        return producoes;
    }

    @Override
    public List<ProducaoDigitadores> findProPrestador(String prestador) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        List<ProducaoDigitadores> producoes = new ArrayList<ProducaoDigitadores>();
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(sqlFindProPrestador);
            pstm.setString(1, prestador);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ProducaoDigitadores producao = new ProducaoDigitadores(rs.getDate("data_entrada"),
                        rs.getDate("data_digitacao"),
                        rs.getInt("quantidade"),
                        rs.getLong("id"),
                        rs.getString("funcionario"),
                        rs.getString("prestador"),
                        rs.getString("procedimento"));
                producoes.add(producao);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            } finally {
                DBConnection.close(conn, pstm, rs);
            }
            ex.printStackTrace();
        }
        return producoes;
    }
}
