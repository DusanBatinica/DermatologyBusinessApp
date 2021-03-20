package domen;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
public class Korisnik extends AbstractObject{
    private String korisnikID;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String korisnickoIme;
    private String sifra;
    private boolean ulogovan;

    public Korisnik() {
    }

    public Korisnik(String korisnikID, String ime, String prezime, Date datumRodjenja, String korisnickoIme, String sifra, boolean ulogovan) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.ulogovan = ulogovan;
    }

    public Korisnik(String korisnikID) {
     this.korisnikID=korisnikID;   
    }
    public String getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(String korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }



    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public String vratiImeTabele() {
        return "korisnik";
    }

    @Override
    public String vratiParametre() {
        java.sql.Date datumm =  new java.sql.Date(datumRodjenja.getTime());
         return String.format("'%s', '%s', '%s', '%s','%s'",ime, prezime, korisnickoIme, sifra,datumm);
    }
    @Override
    public String vratiKolone() {
      return "ime,prezime,datumRodjenja,korisnickoIme,sifra";
    }
    @Override
    public String vratiPK() {
        return "korisnikID";
    }

    @Override
    public String vratiVrednostPK() {
        return korisnikID;
    }

    @Override
    public String vratiSlozenPK() {
       return null;
    }

    @Override
    public List<AbstractObject> RSuTabelu(ResultSet rs) {
                List<AbstractObject> lica = new ArrayList<>();

        try {
            while (rs.next()) {
                int korisnikID = rs.getInt("korisnikID");
                String Ime = rs.getString("ime");
                String Prezime = rs.getString("prezime");
                String Username = rs.getString("korisnickoIme");
                String Password = rs.getString("sifra");
                Date datum = rs.getDate("datum");
                lica.add(new Korisnik(korisnikID+"", Ime, Prezime, datum, Username, Password, false));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Greska kod RSuTabelu za Korisnika");
        }
        return lica;
    }

    @Override
    public String vratiUpdate() {
        java.sql.Date datumm =  new java.sql.Date(datumRodjenja.getTime());
         return String.format("'%s', '%s', '%s', '%s', '%s','%s'", korisnikID, ime, prezime, korisnickoIme, sifra,datumm);
    }

    @Override
    public void postaviVrednostPK(String pk) {
         this.korisnikID = pk;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public boolean isUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(boolean ulogovan) {
        this.ulogovan = ulogovan;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.korisnikID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.korisnikID, other.korisnikID)) {
            return false;
        }
        return true;
    }
}
