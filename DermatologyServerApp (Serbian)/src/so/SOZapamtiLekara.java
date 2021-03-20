package so;

import domen.AbstractObject;
import exception.ServerskiException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SOZapamtiLekara extends AbstractSO {

    AbstractObject parametar;
    AbstractObject lekar;

    public SOZapamtiLekara(AbstractObject parametar) {
        this.parametar = parametar;
    }

    public AbstractObject getLekar() {
        return lekar;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        try {
            lekar = db.sacuvajObjekat(parametar);
        } catch (SQLException ex) {
            Logger.getLogger(SOZapamtiLek.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
