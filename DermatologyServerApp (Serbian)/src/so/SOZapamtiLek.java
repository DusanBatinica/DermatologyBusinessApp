package so;

import domen.AbstractObject;
import exception.ServerskiException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SOZapamtiLek extends AbstractSO {

    AbstractObject parametar;
    AbstractObject lek;

    public SOZapamtiLek(AbstractObject parametar) {
        this.parametar = parametar;
    }

    public AbstractObject getLek() {
        return lek;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        try {
            lek = db.sacuvajObjekat(parametar);
        } catch (SQLException ex) {
            Logger.getLogger(SOZapamtiLek.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
