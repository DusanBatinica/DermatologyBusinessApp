package modeli;

import domen.AbstractObject;
import domen.Lek;
import domen.Lekar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleLekara extends AbstractTableModel{
    private List<AbstractObject> listaLekara;
    String[]kolone = {"Lekar ID","Ime","Prezime","Kontakt"};
     public ModelTabeleLekara(List<AbstractObject> listaLekara) {
        this.listaLekara = listaLekara;
    }
    @Override
    public int getRowCount() {
        return listaLekara.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
                Lekar l = (Lekar) listaLekara.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return l.getLekarID();
            case 1:
                return l.getIme();
            case 2:
                return l.getPrezime();
            case 3:
                return l.getKontakt();
            default:
                return "";
        }
    }
     public String getColumnName(int column) {
        return kolone[column];
    }

    public List<AbstractObject> getListaLekara() {
        return listaLekara;
    }

    public void setListaLekova(List<AbstractObject> listaLekara) {
        this.listaLekara = listaLekara;
        fireTableDataChanged();
    }
    
}
