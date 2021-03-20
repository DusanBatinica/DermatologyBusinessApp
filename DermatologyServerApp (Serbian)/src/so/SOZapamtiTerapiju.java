package so;

import domen.AbstractObject;
import domen.StavkaTerapije;
import domen.Terapija;
import exception.ServerskiException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SOZapamtiTerapiju extends AbstractSO {

    AbstractObject parametar;
    AbstractObject terapija;

    public SOZapamtiTerapiju(AbstractObject parametar) {
        this.parametar = parametar;

    }

    public AbstractObject getTerapija() {
        return terapija;
    }

    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        try {
            terapija = db.sacuvajObjekat(parametar);
            sacuvajStavke();
        } catch (SQLException ex) {
            System.out.println("Greška prilikom čuvanja terapije u bazi!");
        }
    }

    private void sacuvajStavke() throws SQLException, ServerskiException {
        Terapija t = (Terapija) parametar;
        ArrayList<AbstractObject> stavke = t.getListaStavki();
        for (AbstractObject abstractObjekat : stavke) {
            StavkaTerapije s = (StavkaTerapije) abstractObjekat;
             s.setTerapija(t);
             s.getTerapija().setTerapijaID(t.getTerapijaID());
            db.sacuvajObjekat(s);
        }
    }
}
