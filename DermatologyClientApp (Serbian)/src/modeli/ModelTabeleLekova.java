package modeli;

import domen.AbstractObject;
import domen.Lek;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleLekova extends AbstractTableModel {

    private List<AbstractObject> listaLekova;
    String[]kolone = {"Lek ID","Naziv leka","Rok trajanja"};
    public ModelTabeleLekova(List<AbstractObject> listaLekova) {
        this.listaLekova = listaLekova;
    }

    @Override
    public int getRowCount() {
        return listaLekova.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lek l = (Lek) listaLekova.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return l.getLekID();
            case 1:
                return l.getNaziv();
            case 2:
                return l.getRokTrajanja();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<AbstractObject> getListaLekova() {
        return listaLekova;
    }

    public void setListaLekova(List<AbstractObject> listaLekova) {
        this.listaLekova = listaLekova;
        fireTableDataChanged();
    }
    

}
