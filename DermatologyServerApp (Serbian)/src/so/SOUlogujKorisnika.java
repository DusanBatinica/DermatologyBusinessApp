package so;

import domen.AbstractObject;
import domen.Korisnik;
import exception.ServerskiException;
import java.util.List;
import javax.swing.JOptionPane;
import kont.Kontroler;

public class SOUlogujKorisnika extends AbstractSO {

    private AbstractObject unetiParametri;
    private AbstractObject ulogovanKorisnik;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        List<AbstractObject> listaKorisnika = db.vratiSveObjekte(new Korisnik());
        Korisnik unetiKorisnik = (Korisnik) unetiParametri;
        for (AbstractObject abstractObject : listaKorisnika) {
            Korisnik izBaze = (Korisnik) abstractObject;
            String user1 = izBaze.getKorisnickoIme();
            String pass1 = izBaze.getSifra();
            String user2 = unetiKorisnik.getKorisnickoIme();
            String pass2 = unetiKorisnik.getSifra();
            if (user1.equals(user2) && pass1.equals(pass2)) {
                ulogovanKorisnik = izBaze;
                int indeks = Kontroler.vratiInstancu().getListaKorisnika().indexOf(izBaze);
                Korisnik izListe = (Korisnik) Kontroler.vratiInstancu().getListaKorisnika().get(indeks);
                if (izListe.isUlogovan()) {
                    JOptionPane.showMessageDialog(null,"Korisnik je već ulogovan!", "GREŠKA!", JOptionPane.ERROR_MESSAGE);
                    throw new ServerskiException("Korisnik je već ulogovan!");
                } else {
                    izListe.setUlogovan(true);
                }
                System.out.println("Sistem je uspešno ulogovao novog korisnika.");
                return;
            } else {
                if (izBaze.getKorisnickoIme().equals(unetiKorisnik.getKorisnickoIme())) {
                    throw new ServerskiException("Netačno uneta šifra!");
                }
            }
        }
        JOptionPane.showMessageDialog(null,"Sistem ne može da pronađe korisnika!", "GREŠKA!", JOptionPane.ERROR_MESSAGE);
        throw new ServerskiException("Sistem ne može da pronađe korisnika!");
    }

    public AbstractObject getUnetiParametri() {
        return unetiParametri;
    }

    public void setUnetiParametri(AbstractObject unetiParametri) {
        this.unetiParametri = unetiParametri;
    }

    public AbstractObject getUlogovanKorisnik() {
        return ulogovanKorisnik;
    }

    public void setUlogovanKorisnik(AbstractObject ulogovanKorisnik) {
        this.ulogovanKorisnik = ulogovanKorisnik;
    }

}
