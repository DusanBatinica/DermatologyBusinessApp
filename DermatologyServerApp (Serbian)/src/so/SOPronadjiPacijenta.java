package so;

import domen.AbstractObject;
import domen.Lekar;
import domen.Pacijent;
import exception.ServerskiException;
import java.util.ArrayList;
import java.util.List;

public class SOPronadjiPacijenta extends AbstractSO {

    private String pretraga;
    private List<AbstractObject> listaNadjenihPacijenata;

    public SOPronadjiPacijenta(String pretraga) {
        this.pretraga = pretraga;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        List<AbstractObject> sviPacijenti = db.ucitajPacijente();
        listaNadjenihPacijenata = new ArrayList<>();
        pretraga = pretraga.toLowerCase();
        for (AbstractObject abstractObject : sviPacijenti) {
            Pacijent pacijent = (Pacijent) abstractObject;
            if (pacijent.getIme().toLowerCase().contains(pretraga)) {
                listaNadjenihPacijenata.add(pacijent);
            }
        }
    }

    public List<AbstractObject> getListaLekara() {
        return listaNadjenihPacijenata;
    }
}
