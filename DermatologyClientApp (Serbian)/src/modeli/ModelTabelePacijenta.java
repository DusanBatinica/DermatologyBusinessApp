package modeli;

import domen.AbstractObject;
import domen.Lek;
import domen.Pacijent;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabelePacijenta extends AbstractTableModel {

    private List<AbstractObject> listaPacijenata;
    String[] kolone = {"Pacijent ID", "Ime", "Prezime", "Kontakt"};

    public ModelTabelePacijenta(List<AbstractObject> listaPacijenata) {
        this.listaPacijenata = listaPacijenata;
    }

    @Override
    public int getRowCount() {
        return listaPacijenata.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pacijent p = (Pacijent) listaPacijenata.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getPacijentID();
            case 1:
                return p.getIme();
            case 2:
                return p.getPrezime();
            case 3:
                return p.getKontakt();
            default:
                return "";
        }

    }

    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<AbstractObject> getListaPacijenata() {
        return listaPacijenata;
    }

    public void setListaPacijenata(List<AbstractObject> listaLekova) {
        this.listaPacijenata = listaLekova;
        fireTableDataChanged();
    }

}
