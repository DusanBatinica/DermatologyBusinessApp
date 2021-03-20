package modeli;

import domen.AbstractObject;
import domen.StavkaTerapije;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleTerapija extends AbstractTableModel {

    private List<AbstractObject> listaStavki;
    private List<AbstractObject> listaStavkiIzBaze = new ArrayList<>();
    int rB = 0;

    public ModelTabeleTerapija() {
        listaStavki = new ArrayList<>();
    }

    String[] kolone = {"RB", "Nacin pripreme", "Lek",};

    @Override
    public int getRowCount() {
        return listaStavki.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaTerapije st = (StavkaTerapije) listaStavki.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return st.getNoviRedniBroj();
            case 1:
                return st.getNacinPripreme();
            case 2:
                return st.getLek();
            default:
                return "";
        }
    }

    public int dajRB() {
        return ++rB;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodajStavku(StavkaTerapije s) {
        listaStavki.add(s);
        fireTableDataChanged();
    }

    public void obrisiStavku(int index,ArrayList<AbstractObject>helpingList) {
        helpingList.add(listaStavki.get(index));
        listaStavki.remove(index);
        rB = 0;
        for (AbstractObject stavkaTerapije : listaStavki) {
            StavkaTerapije st = (StavkaTerapije) stavkaTerapije;
            st.setRedniBroj(st.getRedniBroj());
            st.setNoviRedniBroj(++rB);
        }
        fireTableDataChanged();
    }

    public List<AbstractObject> getListaStavki() {
        return listaStavki;
    }

    public void resetujModel() {
        rB = 0;
        listaStavki = new ArrayList<>();
        fireTableDataChanged();
    }

    public void setListaStavki(List<AbstractObject> listaStavki) {
        this.listaStavki = listaStavki;
        fireTableDataChanged();
    }

    public void popuniListuStavkiIzBaze(ArrayList<AbstractObject> listaStavki) {
        for (AbstractObject ao : listaStavki) {
            StavkaTerapije s = (StavkaTerapije) ao;
            listaStavkiIzBaze.add(ao);

        }
    }

    public void postaviRB() {
        int rb = 1;
        for (AbstractObject abstractObject : listaStavki) {
            StavkaTerapije s = (StavkaTerapije) abstractObject;
            s.setRedniBroj(rb);
            s.setNoviRedniBroj(rb);
            rb++;
        }
        fireTableDataChanged();
    }

    public List<AbstractObject> getListaStavkiIzBaze() {
        return listaStavkiIzBaze;
    }

    public void setListaStavkiIzBaze(List<AbstractObject> listaStavkiIzBaze) {
        this.listaStavkiIzBaze = listaStavkiIzBaze;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StavkaTerapije s = (StavkaTerapije) listaStavki.get(rowIndex);
        String nacin = (String) aValue;
        s.setNacinPripreme(nacin);
        fireTableDataChanged();
        return;
    }

}
