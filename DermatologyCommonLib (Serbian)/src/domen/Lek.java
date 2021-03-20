package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lek extends AbstractObject {

    private String lekID;
    private String naziv;
    private String rokTrajanja;

    public Lek() {
    }

    public Lek(String lekID, String naziv, String rokTrajanja) {
        this.lekID = lekID;
        this.naziv = naziv;
        this.rokTrajanja = rokTrajanja;
    }

    public Lek(String lekID) {
        this.lekID = lekID;
    }

    public String getRokTrajanja() {
        return rokTrajanja;
    }

    public void setRokTrajanja(String rokTrajanja) {
        this.rokTrajanja = rokTrajanja;
    }

    public String getLekID() {
        return lekID;
    }

    public void setLekID(String lekID) {
        this.lekID = lekID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiImeTabele() {
        return "lek";
    }

    @Override
    public String vratiParametre() {
        return String.format("'%s','%s'",naziv, rokTrajanja);
    }
    public String vratiKolone(){
        return "naziv,rokTrajanja";
    }

    @Override
    public String vratiPK() {
        return "lekID";
    }

    @Override
    public String vratiVrednostPK() {
        return lekID;
    }

    @Override
    public String vratiSlozenPK() {
        return null;
    }

    @Override
    public List<AbstractObject> RSuTabelu(ResultSet rs) {
        List<AbstractObject> lekovi = new ArrayList<>();

        try {
            while (rs.next()) {
                int lekID = rs.getInt("lekID");
                String nazivLeka = rs.getString("naziv");
                String rokTrajanja = rs.getString("rokTrajanja");
                Lek l = new Lek(lekID + "", nazivLeka, rokTrajanja);

                lekovi.add(l);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Greska kod RSuTabelu za Lek");
        }
        System.out.println(lekovi.size());
        return lekovi;
    }

    @Override
    public String vratiUpdate() {
        return String.format("'%s', '%s','%s'", lekID, naziv, rokTrajanja);
    }

    @Override
    public void postaviVrednostPK(String pk) {
        this.lekID=pk;
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
        final Lek other = (Lek) obj;
        if (!Objects.equals(this.lekID, other.lekID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.lekID);
        return hash;
    }
}
