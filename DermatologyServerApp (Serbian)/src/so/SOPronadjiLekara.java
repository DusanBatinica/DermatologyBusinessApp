package so;

import domen.AbstractObject;
import domen.Lek;
import domen.Lekar;
import exception.ServerskiException;
import java.util.ArrayList;
import java.util.List;

public class SOPronadjiLekara extends AbstractSO {

    private String pretraga;
    private List<AbstractObject> listaNadjenihLekara;

    public SOPronadjiLekara(String pretraga) {
        this.pretraga = pretraga;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        List<AbstractObject> sviLekari = db.ucitajLekare();
        listaNadjenihLekara = new ArrayList<>();
        pretraga = pretraga.toLowerCase();
        for (AbstractObject abstractObject : sviLekari) {
            Lekar lekar = (Lekar) abstractObject;
            if (lekar.getIme().toLowerCase().contains(pretraga)) {
                listaNadjenihLekara.add(lekar);
            }
        }
    }

    public List<AbstractObject> getListaLekara() {
        return listaNadjenihLekara;
    }
}
