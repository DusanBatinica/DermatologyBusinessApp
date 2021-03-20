package so;

import domen.AbstractObject;
import exception.ServerskiException;
import java.util.ArrayList;
import java.util.List;

public class SOvratiSvePacijente extends AbstractSO{
    List<AbstractObject> listaSvihPacijenata;

    public SOvratiSvePacijente() {
        listaSvihPacijenata = new ArrayList<>();
    }

    public List<AbstractObject> getListaSvihPacijenata() {
        return listaSvihPacijenata;
    }
    
   
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        listaSvihPacijenata = db.ucitajPacijente();
    }
    
}
