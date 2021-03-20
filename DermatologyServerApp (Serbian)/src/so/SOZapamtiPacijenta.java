package so;

import domen.AbstractObject;
import exception.ServerskiException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SOZapamtiPacijenta extends AbstractSO {

    AbstractObject parametar;
    AbstractObject pacijent;

    public SOZapamtiPacijenta(AbstractObject parametar) {
        this.parametar = parametar;
    }

    public AbstractObject getPacijent() {
        return pacijent;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        try {
            pacijent = db.sacuvajObjekat(parametar);
        } catch (SQLException ex) {
            Logger.getLogger(SOZapamtiLek.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
