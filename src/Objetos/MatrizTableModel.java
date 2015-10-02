package Objetos;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Allan.Amaral
 */
public class MatrizTableModel extends AbstractTableModel {

    private Integer[][] matriz;

    public MatrizTableModel(Integer[][] matriz) {
        if (matriz == null) {
            throw new IllegalArgumentException("Matriz inválida!");
        }

        this.matriz = matriz;
    }

    public int getColumnCount() {
        return matriz[0].length;
    }

    public int getRowCount() {
        return matriz.length;
    }

    public Object getValueAt(int row, int col) {
        return Integer.valueOf(matriz[row][col]);
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        assert value instanceof Integer;

        matriz[row][col] = ((Integer) value).intValue();
    }

    public Class<?> getColumnClass(int col) {
        return Integer.class;
    }

    public void setValue(int row, int col, int value) {
        matriz[row][col] = value;
        fireTableRowsUpdated(row, col);
    }

    @Override
    public String getColumnName(int col) {
        return Integer.toString(col);
    }
}
