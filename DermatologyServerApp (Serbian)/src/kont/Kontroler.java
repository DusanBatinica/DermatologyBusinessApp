package kont;

import domen.AbstractObject;
import domen.Korisnik;
import domen.Lek;
import domen.Lekar;
import domen.Pacijent;
import domen.Terapija;
import exception.ServerskiException;
import java.util.List;
import so.SOAzurirajPacijenta;
import so.SOAzurirajTerapiju;
import so.SOIzlogujKorisnika;
import so.SOObrisiLek;
import so.SOObrisiLekara;
import so.SOObrisiPacijenta;
import so.SOPronadjiLek;
import so.SOPronadjiLekara;
import so.SOPronadjiPacijenta;
import so.SOPronadjiTerapiju;
import so.SOUcitajListuKorisnika;
import so.SOUlogujKorisnika;
import so.SOZapamtiLek;
import so.SOZapamtiLekara;
import so.SOZapamtiPacijenta;
import so.SOZapamtiTerapiju;
import so.SOvratiSveLekare;
import so.SOvratiSveLekare;
import so.SOvratiSveLekove;
import so.SOvratiSvePacijente;
import so.SOvratiSveTerapije;

public class Kontroler {

    private static Kontroler instanca;
    private List<AbstractObject> listaKorisnika;

    private Kontroler() {
    }

    public static Kontroler vratiInstancu() {
        if (instanca == null) {
            instanca = new Kontroler();
        }

        return instanca;
    }

    public AbstractObject ulogujKorisnika(Korisnik k) throws ServerskiException {
        SOUlogujKorisnika sok = new SOUlogujKorisnika();
        sok.setUnetiParametri(k);
        sok.izvrsiOperaciju();
        return sok.getUlogovanKorisnik();
    }

    public List<AbstractObject> getListaKorisnika() throws ServerskiException {
        if (listaKorisnika == null) {
            listaKorisnika = vratiListuKorisnika();
        }
        return listaKorisnika;
    }

    private List<AbstractObject> vratiListuKorisnika() throws ServerskiException {
        SOUcitajListuKorisnika soul = new SOUcitajListuKorisnika();
        soul.izvrsiOperaciju();
        return soul.getLista();
    }

    public AbstractObject kreirajLek(Lek l) throws ServerskiException {
        SOZapamtiLek sokt = new SOZapamtiLek(l);
        sokt.izvrsiOperaciju();
        return sokt.getLek();
    }

    public List<AbstractObject> vratiListuLekova() throws ServerskiException {
        SOvratiSveLekove sovsl = new SOvratiSveLekove();
        sovsl.izvrsiOperaciju();
        return sovsl.getListaLekova();
    }

    public AbstractObject obrisiLek(Lek L) throws ServerskiException {

        SOObrisiLek sool = new SOObrisiLek(L);
        sool.izvrsiOperaciju();
        return sool.getObrisano();
    }

    public AbstractObject kreirajLekara(Lekar le) throws ServerskiException {
        SOZapamtiLekara sokt = new SOZapamtiLekara(le);
        sokt.izvrsiOperaciju();
        return sokt.getLekar();
    }

    public List<AbstractObject> vratiListuLekara() throws ServerskiException {
        SOvratiSveLekare sovsl = new SOvratiSveLekare();
        sovsl.izvrsiOperaciju();
        return sovsl.getListaLekara();
    }

    public AbstractObject obrisiLekara(Lekar L) throws ServerskiException {
        SOObrisiLekara sool = new SOObrisiLekara(L);
        sool.izvrsiOperaciju();
        return sool.getObrisano();
    }

    public AbstractObject kreirajLekara(Pacijent p) throws ServerskiException {
        SOZapamtiPacijenta sopt = new SOZapamtiPacijenta(p);
        sopt.izvrsiOperaciju();
        return sopt.getPacijent();
    }

    public List<AbstractObject> pretraziLekove(String pretraga) throws ServerskiException {
        SOPronadjiLek sopl = new SOPronadjiLek(pretraga);
        sopl.izvrsiOperaciju();
        return sopl.getListaLekova();
    }

    public List<AbstractObject> pretraziLekare(String pretragaa) throws ServerskiException {
        SOPronadjiLekara sople = new SOPronadjiLekara(pretragaa);
        sople.izvrsiOperaciju();
        return sople.getListaLekara();
    }

    public List<AbstractObject> vratiListuPacijenata() throws ServerskiException {
        SOvratiSvePacijente sovsp = new SOvratiSvePacijente();
        sovsp.izvrsiOperaciju();
        return sovsp.getListaSvihPacijenata();
    }

    public AbstractObject obrisiPacijenta(Pacijent P) throws ServerskiException {
        SOObrisiPacijenta soop = new SOObrisiPacijenta(P);
        soop.izvrsiOperaciju();
        return soop.getObrisano();
    }

    public List<AbstractObject> pretraziPacijente(String pretraga1) throws ServerskiException {
        SOPronadjiPacijenta sople = new SOPronadjiPacijenta(pretraga1);
        sople.izvrsiOperaciju();
        return sople.getListaLekara();
    }

    public AbstractObject azurirajPacijenta(Pacijent p2) throws ServerskiException {
        SOAzurirajPacijenta soap = new SOAzurirajPacijenta(p2);
        soap.izvrsiOperaciju();
        return soap.getPacijent();
    }

    public AbstractObject zapamtiTerapiju(Terapija terapija) throws ServerskiException {
        SOZapamtiTerapiju sozt = new SOZapamtiTerapiju(terapija);
        sozt.izvrsiOperaciju();
        return sozt.getTerapija();
    }

    public AbstractObject izlogujKorisnika(Korisnik kor) throws ServerskiException {
        SOIzlogujKorisnika soik = new SOIzlogujKorisnika(kor);
        soik.izvrsiOperaciju();
        return soik.getKorisnik();
    }

    public List<AbstractObject> vratiListuTerapija() throws ServerskiException {
        SOvratiSveTerapije sovst = new SOvratiSveTerapije();
        sovst.izvrsiOperaciju();
        return sovst.getListaSvihTerapija();
    }

    public List<AbstractObject> pretraziTerapije(String pretraga2) throws ServerskiException {
        SOPronadjiTerapiju sopt = new SOPronadjiTerapiju(pretraga2);
        sopt.izvrsiOperaciju();
        return sopt.getListaNadjenihTerapija();
    }

    public AbstractObject azurirajTerapiju(Terapija terapijica) throws ServerskiException {
        SOAzurirajTerapiju soat = new SOAzurirajTerapiju(terapijica);
        soat.izvrsiOperaciju();
        return soat.getTerapija();
    }
}
