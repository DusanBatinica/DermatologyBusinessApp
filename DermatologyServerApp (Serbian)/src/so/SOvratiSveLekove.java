package so;

import domen.AbstractObject;
import exception.ServerskiException;
import java.util.ArrayList;

public class SOvratiSveLekove extends AbstractSO {
    ArrayList<AbstractObject> listaLekova;
    public ArrayList<AbstractObject> getListaLekova() {
        return listaLekova;
    }
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        listaLekova = (ArrayList<AbstractObject>) db.vratiSveLekove();
    }
    
}
