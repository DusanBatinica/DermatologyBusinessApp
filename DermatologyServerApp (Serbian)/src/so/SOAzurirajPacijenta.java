package so;

import domen.AbstractObject;
import exception.ServerskiException;

public class SOAzurirajPacijenta extends AbstractSO{
    
    private AbstractObject param;
    private AbstractObject pacijent;

    public SOAzurirajPacijenta(AbstractObject param) {
        this.param = param;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
             pacijent = db.azurirajObjekat(param);
    }
    
     public AbstractObject getParam() {
        return param;
    }

    public void setParam(AbstractObject param) {
        this.param = param;
    }

    public AbstractObject getPacijent() {
        return pacijent;
    }
    
}
