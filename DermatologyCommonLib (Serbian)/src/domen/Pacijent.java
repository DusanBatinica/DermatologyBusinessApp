package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pacijent extends AbstractObject {

    private String pacijentID;
    private String ime;
    private String prezime;
    private String kontakt;

    public Pacijent() {
    }

    public Pacijent(String pacijentID, String ime, String prezime, String kontakt) {
        this.pacijentID = pacijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.kontakt = kontakt;
    }

    public Pacijent(String pacijentID) {
    this.pacijentID = pacijentID;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public String getPacijentID() {
        return pacijentID;
    }

    public void setPacijentID(String pacijentID) {
        this.pacijentID = pacijentID;
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

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiImeTabele() {
        return "pacijent";
    }

    @Override
    public String vratiParametre() {
        return String.format("'%s','%s','%s'",ime, prezime, kontakt);
    }

    @Override
    public String vratiPK() {
        return "pacijentID";
    }

    @Override
    public String vratiVrednostPK() {
        return pacijentID;
    }

    @Override
    public String vratiSlozenPK() {
        return null;
    }

    @Override
    public List<AbstractObject> RSuTabelu(ResultSet rs) {
        List<AbstractObject> pacijenti = new ArrayList<>();

        try {
            while (rs.next()) {
                int pacijentID = rs.getInt("pacijentID");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String kontakt = rs.getString("kontakt");
                Pacijent p = new Pacijent(pacijentID + "", ime, prezime, kontakt);

                pacijenti.add(p);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Greska kod RSuTabelu za Tim");
        }
        return pacijenti;
    }

    @Override
    public String vratiUpdate() {
        return String.format("pacijentID='%s', ime='%s', prezime='%s',kontakt='%s'", pacijentID, ime, prezime, kontakt);
    }
    
    @Override
    public String vratiKolone() {
        return "ime,prezime,kontakt";
    }
    
    @Override
    public void postaviVrednostPK(String pk) {
        this.pacijentID = pk;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.pacijentID);
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
        final Pacijent other = (Pacijent) obj;
        if (!Objects.equals(this.pacijentID, other.pacijentID)) {
            return false;
        }
        return true;
    }
}
