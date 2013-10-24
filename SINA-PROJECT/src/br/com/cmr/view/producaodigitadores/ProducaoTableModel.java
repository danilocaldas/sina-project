/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cmr.view.producaodigitadores;

import br.com.cmr.model.entidade.producao.Producao;
import br.com.cmr.model.entidade.producao.ProducaoDigitadores;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class ProducaoTableModel extends AbstractTableModel {

    private static final int COL_ID = 0;
    private static final int COL_FUNCIONARIO = 1;
    private static final int COL_PRESTADOR = 2;
    private static final int COL_PROCEDIMENTO = 3;
    private static final int COL_DATA_ENTRADA = 4;
    private static final int COL_DATA_DIGITACAO = 5;
    private static final int COL_QUANTIDADE = 6;
    private List<ProducaoDigitadores> valores;

    public ProducaoTableModel(List<ProducaoDigitadores> valores) {
        this.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProducaoDigitadores producaoDigitadores = valores.get(rowIndex);
        SimpleDateFormat formatdata = new SimpleDateFormat("dd/MM/yyyy");
        if (columnIndex == COL_ID) {
            return producaoDigitadores.getId();
        } else if (columnIndex == COL_FUNCIONARIO) {
            return producaoDigitadores.getFuncionario_nome();
        } else if (columnIndex == COL_PRESTADOR) {
            return producaoDigitadores.getPrestador_nome();
        }else if (columnIndex == COL_PROCEDIMENTO) {
            return producaoDigitadores.getProcedimento_nome();
        }else if (columnIndex == COL_DATA_ENTRADA) {
            return formatdata.format(producaoDigitadores.getEntrada());
        }else if (columnIndex == COL_DATA_DIGITACAO) {
            return formatdata.format(producaoDigitadores.getDigitacao());
        }else if (columnIndex == COL_QUANTIDADE) {
            return producaoDigitadores.getQuantidade();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {
        String colunas = "";
        switch (column) {
            case COL_ID:
                colunas = "Id";
                break;
            case COL_FUNCIONARIO:
                colunas = "Funcionário";
                break;
            case COL_PRESTADOR:
                colunas = "Prestador";
                break;
            case COL_PROCEDIMENTO:
                colunas = "Procedimento";
                break;
            case COL_DATA_ENTRADA:
                colunas = "Entrada";
                break;
            case COL_DATA_DIGITACAO:
                colunas = "Digitado";
                break;
            case COL_QUANTIDADE:
                colunas = "Qtd";
                break;
            default:
                throw new IllegalArgumentException("Coluna Inválida!");
        }
        return colunas;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == COL_ID){
            return Long.class;
        }else if (columnIndex == COL_FUNCIONARIO) {
            return String.class;
        }else if (columnIndex == COL_PRESTADOR) {
            return String.class;
        }else if (columnIndex == COL_PROCEDIMENTO) {
            return String.class;
        }else if (columnIndex == COL_DATA_ENTRADA) {
            return String.class;
        }else if (columnIndex == COL_DATA_DIGITACAO) {
            return String.class;
        }else if (columnIndex == COL_QUANTIDADE) {
           return String.class;
        }
        return null; 
    }
    
     public ProducaoDigitadores get(int row){
        return valores.get(row);
    }
    
}
