package so;

import domen.AbstractObject;
import exception.ServerskiException;

public class SOObrisiPacijenta extends AbstractSO {
        AbstractObject param;
    AbstractObject obrisano;

    public SOObrisiPacijenta(AbstractObject param) {
        this.param = param;
    }

    public AbstractObject getObrisano() {
        return obrisano;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        obrisano = db.obrisiObjekat(param);
    }
}
