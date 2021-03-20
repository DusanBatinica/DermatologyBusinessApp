package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Terapija extends AbstractObject {

    private String terapijaID;
    private String naziv;
    private Date datum;
    private Pacijent pacijent;
    private Korisnik korisnik;
    private Lekar lekar;
    private ArrayList<AbstractObject> listaStavki;

    public Terapija() {
        listaStavki=new ArrayList<>();
    }

    public Terapija(String terapijaID, String naziv, Date datum, Pacijent pacijent, Korisnik korisnik, Lekar lekar, ArrayList<AbstractObject> listaStavki) {
        this.terapijaID = terapijaID;
        this.naziv = naziv;
        this.datum = datum;
        this.pacijent = pacijent;
        this.korisnik = korisnik;
        this.lekar = lekar;
        this.listaStavki = listaStavki;
    }

    public Terapija(String terapijaID) {
        this.terapijaID = terapijaID;
    }

    public ArrayList<AbstractObject> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(ArrayList<AbstractObject> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public String getTerapijaID() {
        return terapijaID;
    }

    public void setTerapijaID(String terapijaID) {
        this.terapijaID = terapijaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }

    @Override
    public String vratiImeTabele() {
        return "terapija";
    }

    @Override
    public String vratiParametre() {
        System.out.println(naziv);
        System.out.println(datum);
        System.out.println(pacijent);
        System.out.println(korisnik);
        System.out.println(lekar);
        java.sql.Date datumm = new java.sql.Date(datum.getTime());
        return String.format("'%s', '%s', '%s', '%s', '%s'", naziv, datumm, pacijent.getPacijentID(), korisnik.getKorisnikID(), lekar.getLekarID());
    }

    @Override
    public String vratiKolone() {
        return "naziv,datum,pacijentID,korisnikID,lekarID";
    }

    @Override
    public String vratiPK() {
        return "terapijaID";
    }

    @Override
    public String vratiVrednostPK() {
        return terapijaID;
    }

    @Override
    public String vratiSlozenPK() {
        return null;
    }

    @Override
    public List<AbstractObject> RSuTabelu(ResultSet rs) {
        List<AbstractObject> terapije = new ArrayList<>();
        try {
            while (rs.next()) {

                int terapijaID = rs.getInt("terapijaID");
                String nazivTerapije = rs.getString("naziv");
                Date datumT = rs.getDate("datum");
                int pacijentID = rs.getInt("pacijentID");
                Pacijent p = new Pacijent(pacijentID + "");
                int korisnikID = rs.getInt("korisnikID");
                Korisnik k = new Korisnik(korisnikID + "");
                int lekarID = rs.getInt("lekarID");
                Lekar l = new Lekar(lekarID + "");
                Terapija terapija = new Terapija(terapijaID + " ", nazivTerapije, datumT, p, k, l, listaStavki);
                terapije.add(terapija);
            }
        } catch (SQLException ex) {
            System.out.println("Greska kod RSuTabelu za Terapiju");
        }
        return terapije;
    }

    @Override
    public String vratiUpdate() {
        java.sql.Date datumm = new java.sql.Date(datum.getTime());
        return String.format("terapijaID='%s', naziv='%s', datum='%s',pacijentID='%s',korisnikID='%s',lekarID='%s'", terapijaID, naziv, datumm,pacijent.getPacijentID(),korisnik.getKorisnikID(),lekar.getLekarID());
    }

    @Override
    public void postaviVrednostPK(String pk) {
        this.terapijaID = pk;
    }

    @Override
    public String toString() {
        return "Terapija{" + "terapijaID=" + terapijaID + ", naziv=" + naziv + ", datum=" + datum + ", pacijent=" + pacijent + ", korisnik=" + korisnik + ", lekar=" + lekar + '}';
    }

    public void addStavka(StavkaTerapije s){
        listaStavki.add(s);
    }
    public void initList(){
        listaStavki = new ArrayList<>();
    }
}
