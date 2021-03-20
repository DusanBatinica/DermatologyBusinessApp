package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lekar extends AbstractObject {

    private String lekarID;
    private String ime;
    private String prezime;
    private String kontakt;

    public Lekar() {
    }

    public Lekar(String lekarID, String ime, String prezime, String kontakt) {
        this.lekarID = lekarID;
        this.ime = ime;
        this.prezime = prezime;
        this.kontakt = kontakt;
    }

    public Lekar(String lekarID) {
        this.lekarID = lekarID;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public String getLekarID() {
        return lekarID;
    }

    public void setLekarID(String lekarID) {
        this.lekarID = lekarID;
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
    public String vratiImeTabele() {
        return "lekar";
    }

    @Override
    public String vratiParametre() {
        return String.format("'%s','%s','%s'", ime, prezime, kontakt);
    }

    @Override
    public String vratiKolone() {
        return "ime,prezime,kontakt";
    }

    @Override
    public String vratiPK() {
        return "lekarID";
    }

    @Override
    public String vratiVrednostPK() {
        return lekarID;
    }

    @Override
    public String vratiSlozenPK() {
        return null;
    }

    @Override
    public List<AbstractObject> RSuTabelu(ResultSet rs) {
        List<AbstractObject> lekari = new ArrayList<>();

        try {
            while (rs.next()) {
                int lekarID = rs.getInt("lekarID");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String kontakt = rs.getString("kontakt");
                Lekar l = new Lekar(lekarID + "", ime, prezime, kontakt);

                lekari.add(l);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Greska kod RSuTabelu za Tim");
        }
        return lekari;
    }

    @Override
    public String vratiUpdate() {
        return String.format("'%s', '%s','%s','%s'", lekarID, ime, prezime, kontakt);
    }

    @Override
    public void postaviVrednostPK(String pk) {
        this.lekarID = pk;
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
        final Lekar other = (Lekar) obj;
        if (!Objects.equals(this.lekarID, other.lekarID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.lekarID);
        return hash;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }
}
