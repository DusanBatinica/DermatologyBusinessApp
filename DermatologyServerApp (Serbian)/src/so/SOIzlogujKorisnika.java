package so;

import domen.AbstractObject;
import domen.Korisnik;
import exception.ServerskiException;
import kont.Kontroler;

public class SOIzlogujKorisnika extends AbstractSO {

    private AbstractObject korisnik;

    public SOIzlogujKorisnika(AbstractObject korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
         int indeks = Kontroler.vratiInstancu().getListaKorisnika().indexOf(korisnik);
        if (indeks != -1) {
            ((Korisnik) Kontroler.vratiInstancu().getListaKorisnika().get(indeks)).setUlogovan(false);
        }
    }

    public void setKorisnik(AbstractObject korisnik) {
        this.korisnik = korisnik;
    }

    public AbstractObject getKorisnik() {
        return korisnik;
    }

}
