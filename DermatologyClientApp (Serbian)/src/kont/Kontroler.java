package kont;

import domen.AbstractObject;
import domen.Korisnik;
import domen.Lek;
import domen.Lekar;
import domen.Pacijent;
import domen.Terapija;
import java.io.IOException;
import java.util.List;
import komunikacija.KomunikacijaSaServerom;
import konstante.Operacija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class Kontroler {

    private static Kontroler instanca;

    private Kontroler() {
    }

    public static Kontroler vratiInstancu() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    private Object posaljiZahtev(int operacija, Object parametar) throws Exception {
        KlijentskiZahtev kt = new KlijentskiZahtev();
        kt.setOperacija(operacija);
        kt.setParametar(parametar);
        KomunikacijaSaServerom.vratiInstancu().posaljiZahtev(kt);
        ServerskiOdgovor so = KomunikacijaSaServerom.vratiInstancu().procitajOdgovor();
        if (so.getUspesnost() == 1) {
            return so.getOdgovor();
        } else {
            Exception ex = so.getException();
            throw ex;
        }
    }

    private Object posaljiZahtev(int operacija) throws Exception {
        return posaljiZahtev(operacija, null);
    }

    public AbstractObject ulogujKorisnika(String username, String password) throws Exception {
        Korisnik k = new Korisnik(null, null, null, null, username, password, false);
        return (AbstractObject) posaljiZahtev(Operacija.ULOGUJ_KORISNIKA, k);
    }

    public Lek sacuvajLek(Lek lek) throws Exception, IOException, ClassNotFoundException {
        return (Lek) (AbstractObject) posaljiZahtev(Operacija.SACUVAJ_LEK, lek);  
    }

    public List<AbstractObject> vratiLekove() throws Exception {
        return (List<AbstractObject>) posaljiZahtev(Operacija.UCITAJ_LEKOVE);
    }

    public Lek obrisiLek(Lek lek) throws Exception {
        return (Lek) (AbstractObject) posaljiZahtev(Operacija.OBRISI_LEK, lek);
    }

    public Lekar sacuvajLekara(Lekar lekar) throws Exception {
        return (Lekar) (AbstractObject) posaljiZahtev(Operacija.SACUVAJ_LEKARA, lekar);
    }

    public List<AbstractObject> vratiLekare() throws Exception {
        return (List<AbstractObject>) posaljiZahtev(Operacija.UCITAJ_LEKARE);
    }

    public Lekar obrisiLekara(Lekar lekar) throws Exception {
        return (Lekar) (AbstractObject) posaljiZahtev(Operacija.OBRISI_LEKARA, lekar);
    }

    public Pacijent sacuvajPacijenta(Pacijent pacijent) throws Exception {
        return (Pacijent) (AbstractObject) posaljiZahtev(Operacija.SACUVAJ_PACIJENTA, pacijent);
    }

    public List<AbstractObject> pretraziLekove(String pretraga) throws Exception {
        return (List<AbstractObject>) posaljiZahtev(Operacija.PRETRAZI_LEKOVE, pretraga);
    }

    public List<AbstractObject> pretraziLekare(String pretraga) throws Exception {
        return (List<AbstractObject>) posaljiZahtev(Operacija.PRETRAZI_LEKARE, pretraga);
    }

    public List<AbstractObject> vratiPacijente() throws Exception {
        return (List<AbstractObject>) posaljiZahtev(Operacija.VRATI_PACIJENTE);
    }

    public Pacijent obrisiPacijenta(Pacijent pacijent) throws Exception {
        return (Pacijent) (AbstractObject) posaljiZahtev(Operacija.OBRISI_PACIJENTA, pacijent);
    }

    public List<AbstractObject> pretraziPacijente(String pretraga) throws Exception {
        return (List<AbstractObject>) posaljiZahtev(Operacija.PRETRAZI_PACIJENTE, pretraga);
    }
    public Pacijent azurirajPacijenta(Pacijent p) throws Exception {
       return (Pacijent) (AbstractObject) posaljiZahtev(Operacija.AZURIRAJ_PACIJENTA, p);
    }
    public Terapija sacuvajTerapiju(Terapija t) throws Exception {
        return (Terapija) (AbstractObject) posaljiZahtev(Operacija.SACUVAJ_TERAPIJU, t);
    }
    //POMOCNE
    public int dajMaxIDLek() throws Exception {
        return (int) posaljiZahtev(Operacija.DAJ_MAX_ID_LEK);
    }

    public AbstractObject izlogujKorisnika(Korisnik korisnik)throws Exception {
    return (AbstractObject) posaljiZahtev(Operacija.IZLOGUJ_KORISNIKA, korisnik);
    }

    public List<AbstractObject> vratiTerapije() throws Exception {
    return (List<AbstractObject>) posaljiZahtev(Operacija.VRATI_TERAPIJE);
    }

    public List<AbstractObject> pretraziTerapije(String pretraga) throws Exception {
        return (List<AbstractObject>) posaljiZahtev(Operacija.PRETRAZI_TERAPIJE, pretraga);
    }
    public Terapija azurirajTerapija(Terapija t) throws Exception {
       return (Terapija) (AbstractObject) posaljiZahtev(Operacija.AZURIRAJ_TERAPIJU, t);
    }
}
