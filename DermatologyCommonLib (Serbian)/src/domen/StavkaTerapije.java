package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StavkaTerapije extends AbstractObject {

    private int redniBroj;
    private int noviRedniBroj;
    private String nacinPripreme;
    private Lek lek;
    private Terapija terapija;
    private String stanje;
    private int alarm = 0;
    
    public StavkaTerapije() {
    }
    
    public StavkaTerapije(int redniBroj, String nacinPripreme, Lek lek, Terapija terapija) {
        this.redniBroj = redniBroj;
        this.nacinPripreme = nacinPripreme;
        this.lek = lek;
        this.terapija = terapija;
    }

    public Terapija getTerapija() {
        return terapija;
    }

    public void setTerapija(Terapija terapija) {
        this.terapija = terapija;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public String getNacinPripreme() {
        return nacinPripreme;
    }

    public void setNacinPripreme(String nacinPripreme) {
        this.nacinPripreme = nacinPripreme;
    }

    @Override
    public String toString() {
        return "" + nacinPripreme;
    }

    @Override
    public String vratiImeTabele() {
        return "stavkaTerapije";
    }

    @Override
    public String vratiParametre() {
        return String.format("'%s', '%s', '%s','%s'", noviRedniBroj, nacinPripreme, lek.getLekID(), terapija.getTerapijaID());
    }
    
    @Override
    public String vratiKolone() {
        return "redniBroj,nacinPripreme,lekID,terapijaID";
    }

    @Override
    public String vratiPK() {
        return null;
    }

    @Override
    public String vratiVrednostPK() {
        return null;
    }

    @Override
    public String vratiSlozenPK() {
        return String.format("redniBroj='%s' AND terapijaID='%s'", redniBroj, terapija.getTerapijaID());
    }

    @Override
    public List<AbstractObject> RSuTabelu(ResultSet rs) {
        List<AbstractObject> stavke = new ArrayList<>();
        try {
            while (rs.next()) {
                int redniBroj = rs.getInt("redniBroj");
                String nacinPripreme = rs.getString("nacinPripreme");
                int terapijaID = rs.getInt("terapijaID");
                Terapija t = new Terapija(terapijaID + "");
                int lekID = rs.getInt("lekID");
                Lek l = new Lek(lekID + "");
                StavkaTerapije s = new StavkaTerapije(redniBroj, nacinPripreme, l, t);
                stavke.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("Greska kod RSuTabelu za Stavku Terapije!");
        }
        return stavke;
    }

    @Override
    public String vratiUpdate() {
        return String.format("redniBroj='%s', nacinPripreme='%s', lekID='%s',terapijaID='%s'",noviRedniBroj,nacinPripreme,lek.getLekID(),terapija.getTerapijaID());
    }

    @Override
    public void postaviVrednostPK(String pk) {
    }

    public Lek getLek() {
        return lek;
    }

    public void setLek(Lek lek) {
        this.lek = lek;
    }

    public String getStanje() {
        return stanje;
    }

    public void setStanje(String stanje) {
        this.stanje = stanje;
    }
    
    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public int getNoviRedniBroj() {
        return noviRedniBroj;
    }

    public void setNoviRedniBroj(int noviRedniBroj) {
        this.noviRedniBroj = noviRedniBroj;
    }
    
}
