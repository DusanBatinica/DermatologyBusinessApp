package so;

import domen.AbstractObject;
import domen.StavkaTerapije;
import domen.Terapija;
import exception.ServerskiException;
import java.util.ArrayList;
import java.util.List;

public class SOvratiSveTerapije extends AbstractSO {

    List<AbstractObject> listaSvihTerapija;

    public SOvratiSveTerapije() {
        listaSvihTerapija = new ArrayList<>();
    }

    public List<AbstractObject> getListaSvihTerapija() {
        return listaSvihTerapija;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        listaSvihTerapija = db.ucitajTerapije();
        ucitajStavke();
         for (AbstractObject ao : listaSvihTerapija) {
            Terapija t = (Terapija) ao;
            System.out.println(t.getTerapijaID()+",Stavke:"+t.getListaStavki());
        }
    }

    private void ucitajStavke() throws ServerskiException {
        List<AbstractObject> listaStavki = db.vratiStavke();
        for (AbstractObject ao : listaSvihTerapija) {
            Terapija t = (Terapija) ao;
            t.initList();
            for (AbstractObject abstractObject : listaStavki) {
                StavkaTerapije s = (StavkaTerapije) abstractObject;
                if (s.getTerapija().getTerapijaID().trim().equals(t.getTerapijaID().trim())) {
                    s.setTerapija(t);
                    t.addStavka(s);
                }
            }
            System.out.println(t.getTerapijaID()+",Stavke:"+t.getListaStavki());
           
        }
    }
}
