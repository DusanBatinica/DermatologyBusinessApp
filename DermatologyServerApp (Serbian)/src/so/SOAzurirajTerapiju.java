package so;

import domen.AbstractObject;
import domen.StavkaTerapije;
import domen.Terapija;
import exception.ServerskiException;

public class SOAzurirajTerapiju extends AbstractSO {

    AbstractObject parametar;
    AbstractObject terapija;

    public SOAzurirajTerapiju(Terapija parametar) {
        this.parametar = parametar;
    }

    public AbstractObject getTerapija() {
        return terapija;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        terapija = db.azurirajObjekat(parametar);
        Terapija t = (Terapija) terapija;
        for (AbstractObject ao : t.getListaStavki()) {
            StavkaTerapije st = (StavkaTerapije) ao;
            st.setTerapija(t);
            if (st.getStanje() != null && st.getStanje().equals("obrisana")) {
                db.obrisiStavku(st);
            } else {
                db.sacuvajIliAzurirajObjekat(st);
            }
        }
    }

}
