package so;

import domen.AbstractObject;
import domen.Terapija;
import exception.ServerskiException;
import java.util.ArrayList;
import java.util.List;

public class SOPronadjiTerapiju extends AbstractSO {

    private String pretraga;
    private List<AbstractObject> listaNadjenihTerapija;

    public SOPronadjiTerapiju(String pretraga) {
        this.pretraga = pretraga;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        List<AbstractObject> sveTerapije = db.ucitajTerapije();
        listaNadjenihTerapija = new ArrayList<>();
        pretraga = pretraga.toLowerCase();
        for (AbstractObject abstractObject : sveTerapije) {
            Terapija terapija = (Terapija) abstractObject;
            if (terapija.getNaziv().toLowerCase().contains(pretraga)) {

                List<AbstractObject> listaStavki = db.vratiStavkePoTerapijaID(terapija.getTerapijaID());
                terapija.setListaStavki((ArrayList<AbstractObject>) listaStavki);

                listaNadjenihTerapija.add(terapija);
            }
        }
    }

    public List<AbstractObject> getListaNadjenihTerapija() {
        return listaNadjenihTerapija;
    }
}
