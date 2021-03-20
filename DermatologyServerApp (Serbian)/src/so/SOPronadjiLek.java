package so;

import domen.AbstractObject;
import domen.Lek;
import exception.ServerskiException;
import java.util.ArrayList;
import java.util.List;

public class SOPronadjiLek extends AbstractSO {

    private String pretraga;
    private List<AbstractObject> listaNadjenihLekova;

    public SOPronadjiLek(String pretraga) {
        this.pretraga = pretraga;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        List<AbstractObject> sviLekovi = db.ucitajLekove();
        listaNadjenihLekova = new ArrayList<>();
        pretraga = pretraga.toLowerCase();
        for (AbstractObject abstractObject : sviLekovi) {
            Lek lek = (Lek) abstractObject;
            if (lek.getNaziv().toLowerCase().contains(pretraga)) {
                listaNadjenihLekova.add(lek);
            }
        }
    }

    public List<AbstractObject> getListaLekova() {
        return listaNadjenihLekova;
    }

}
