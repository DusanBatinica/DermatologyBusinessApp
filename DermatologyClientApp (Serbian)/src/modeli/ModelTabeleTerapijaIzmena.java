package modeli;

import domen.AbstractObject;
import domen.Terapija;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleTerapijaIzmena extends AbstractTableModel {
    private ArrayList<AbstractObject>listaTerapija;
    String[]kolone = {"Terapija ID","Naziv","Datum","Pacijent","Lekar"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    public ModelTabeleTerapijaIzmena() {
    }

  

    public ModelTabeleTerapijaIzmena(ArrayList<AbstractObject> arrayList) {
        this.listaTerapija = arrayList;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return listaTerapija.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Terapija terapija = (Terapija) listaTerapija.get(rowIndex);
        String datumm=sdf.format(terapija.getDatum());
        switch(columnIndex){
            case 0:return terapija.getTerapijaID();
            case 1:return terapija.getNaziv();
            case 2:return datumm;
            case 3:return terapija.getPacijent();
            case 4:return terapija.getLekar();
            default:return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public ArrayList<AbstractObject> getListaTerapija() {
        return listaTerapija;
    }

    public void setListaTerapija(List<AbstractObject> listaTerapija) {
        this.listaTerapija = (ArrayList<AbstractObject>) listaTerapija;
        fireTableDataChanged();
    }
    
}
