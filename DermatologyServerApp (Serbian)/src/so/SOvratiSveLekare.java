package so;

import domen.AbstractObject;
import exception.ServerskiException;
import java.util.ArrayList;

public class SOvratiSveLekare extends AbstractSO {

    ArrayList<AbstractObject> listaLekara;

    public ArrayList<AbstractObject> getListaLekara() {
        return listaLekara;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        listaLekara = (ArrayList<AbstractObject>) db.vratiSveLekare();
    }

}
