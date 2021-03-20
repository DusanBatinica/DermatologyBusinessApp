package so;

import domen.AbstractObject;
import domen.Korisnik;
import exception.ServerskiException;
import java.util.List;

public class SOUcitajListuKorisnika extends AbstractSO {
    private List<AbstractObject> lista;
    
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        lista = db.vratiSveObjekte(new Korisnik());
    }

    public List<AbstractObject> getLista() {
        return lista;
    }
}
