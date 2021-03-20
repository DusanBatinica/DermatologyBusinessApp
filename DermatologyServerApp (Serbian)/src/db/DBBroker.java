package db;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import domen.AbstractObject;
import domen.Korisnik;
import domen.Lek;
import domen.Lekar;
import domen.Pacijent;
import domen.StavkaTerapije;
import domen.Terapija;
import exception.ServerskiException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DBBroker {

    private Connection konekcija;

    public DBBroker() {
    }

    public void otvoriKonekciju() throws ServerskiException {
        try {

            Class.forName(Util.getInstance().getDriver());
            String url = Util.getInstance().getURL();
            String user = Util.getInstance().getUser();
            String password = Util.getInstance().getPassword();
            konekcija = DriverManager.getConnection(url, user, password);

            konekcija.setAutoCommit(false);

            System.out.println("Konekcija sa bazom je uspešna!!");

        } catch (IOException ex) {
            throw new ServerskiException("Greška kod učitavanja parametara iz properties fajla!");
        } catch (ClassNotFoundException ex) {
            throw new ServerskiException("Drajver nije pronadjen!");
        } catch (SQLException ex) {
            throw new ServerskiException("Neuspešna konekcija na bazu!");
        }
    }

    public void zatvoriKonekciju() throws ServerskiException {
        try {
            konekcija.close();
            System.out.println("Konekcija je uspešno raskinuta!");
        } catch (SQLException ex) {
            throw new ServerskiException("Neuspešno raskidanje konekcije!");
        }
    }

    public void commit() throws ServerskiException {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            throw new ServerskiException("Transakcije neuspešna!!");
        }
    }

    public void rollback() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            System.out.println("Neuspešno poništavanje transakcije!");
        }
    }

    public List<AbstractObject> vratiSveObjekte(AbstractObject o) throws ServerskiException {
        try {
            String upit = "SELECT * FROM " + o.vratiImeTabele();
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            List<AbstractObject> listaObjekata = o.RSuTabelu(rs);
            s.close();
            System.out.println("Uspešno izvršen SELECT za:"+o.vratiImeTabele());
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException("Server ne može da prikaže podatke o " + o.getClass().getName() + ".");
        }
    }

    public AbstractObject sacuvajObjekat(AbstractObject o) throws SQLException, ServerskiException {
        try {
            String upit = String.format("INSERT INTO %s (%s)  VALUES (%s)", o.vratiImeTabele(), o.vratiKolone(), o.vratiParametre());
            System.out.println("Upit:\n" + upit);
            System.out.println(o.vratiImeTabele());
            Statement s = konekcija.createStatement();
            s.executeUpdate(upit, s.RETURN_GENERATED_KEYS);
            ResultSet rs = s.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                o.postaviVrednostPK("" + id);
            }
            s.close();
            return o;
        } catch (MySQLIntegrityConstraintViolationException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerskiException("Vec postoji element sa unetim ID-jem!");
        }
    }

    public List<AbstractObject> vratiSveLekove() throws ServerskiException {
        try {
            Lek l = new Lek();

            String upit = "SELECT * FROM lek";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            List<AbstractObject> listaObjekata = l.RSuTabelu(rs);
            for (AbstractObject ao : listaObjekata) {
                Lek lek = (Lek) ao;
            }
            s.close();
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException(ex.getMessage());
        }
    }

    public AbstractObject vratiObjekatPoKljucu(AbstractObject o, String ID) throws ServerskiException {
        String upit;
        if (o.vratiPK() != null) {
            upit = "SELECT * FROM " + o.vratiImeTabele() + " WHERE " + o.vratiPK() + "=" + ID;
        } else {
            upit = "SELECT * FROM " + o.vratiImeTabele() + " WHERE " + o.vratiSlozenPK();
        }
        try (Statement s = konekcija.createStatement();) {
            ResultSet rs = s.executeQuery(upit);
            List<AbstractObject> listaObjekata = o.RSuTabelu(rs);
            s.close();
            return listaObjekata.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerskiException(ex.getMessage());
        }
    }

    public AbstractObject obrisiObjekat(AbstractObject o) throws ServerskiException {
        try {
            String upit = String.format("DELETE FROM %s WHERE %s = %s", o.vratiImeTabele(), o.vratiPK(), o.vratiVrednostPK());
            Statement s = konekcija.createStatement();
            System.out.println(upit);
            s.executeUpdate(upit);
            commit();
            s.close();
        } catch (SQLException ex) {
            throw new ServerskiException(ex.getMessage());
        }
        return o;
    }

    public List<AbstractObject> vratiSveLekare() throws ServerskiException {
        try {
            Lekar l = new Lekar();

            String upit = "SELECT * FROM lekar";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            List<AbstractObject> listaObjekata = l.RSuTabelu(rs);
            for (AbstractObject ao : listaObjekata) {
                Lekar lekar = (Lekar) ao;
            }
            s.close();
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException(ex.getMessage());
        }
    }

    public List<AbstractObject> vratiSvePacijente() throws ServerskiException {
        try {
            Pacijent p = new Pacijent();

            String upit = "SELECT * FROM pacijent";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            List<AbstractObject> listaObjekata = p.RSuTabelu(rs);
            for (AbstractObject ao : listaObjekata) {
                Pacijent pa = (Pacijent) ao;
            }
            s.close();
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException(ex.getMessage());
        }
    }

    public List<AbstractObject> ucitajLekove() throws ServerskiException {
        try {
            Lek l = new Lek();

            String upit = "SELECT * FROM lek";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            List<AbstractObject> listaObjekata = l.RSuTabelu(rs);
            for (AbstractObject ao : listaObjekata) {
                Lek lek = (Lek) ao;
            }
            s.close();
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException(ex.getMessage());
        }
    }

    public List<AbstractObject> ucitajLekare() throws ServerskiException {
        try {
            Lekar l = new Lekar();

            String upit = "SELECT * FROM lekar";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            List<AbstractObject> listaObjekata = l.RSuTabelu(rs);
            for (AbstractObject ao : listaObjekata) {
                Lekar lekar = (Lekar) ao;
            }
            s.close();
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException(ex.getMessage());
        }
    }

    public List<AbstractObject> ucitajPacijente() throws ServerskiException {
        try {
            Pacijent p = new Pacijent();

            String upit = "SELECT * FROM pacijent";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            List<AbstractObject> listaObjekata = p.RSuTabelu(rs);
            for (AbstractObject ao : listaObjekata) {
                Pacijent pacijent = (Pacijent) ao;
            }
            s.close();
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException(ex.getMessage());
        }
    }

    public AbstractObject azurirajObjekat(AbstractObject o) {
        try {
            String upit;
            if (o.vratiPK() != null) {
                upit = String.format("UPDATE %s SET %s WHERE %s = %s", o.vratiImeTabele(), o.vratiUpdate(), o.vratiPK(), o.vratiVrednostPK());
            } else {
                upit = String.format("UPDATE %s SET %s WHERE %s", o.vratiImeTabele(), o.vratiUpdate(), o.vratiSlozenPK());
            }
            System.out.println("Upit:\n" + upit);
            Statement s = konekcija.createStatement();
            s.executeUpdate(upit);
            s.close();
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<AbstractObject> ucitajTerapije() throws ServerskiException {
        try {
            Terapija t = new Terapija();

            String upit = "SELECT * FROM terapija";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            List<AbstractObject> listaObjekata = t.RSuTabelu(rs);
            for (AbstractObject ao : listaObjekata) {
                Terapija terapija = (Terapija) ao;
                Lekar l = (Lekar) vratiObjekatPoKljucu(new Lekar(), terapija.getLekar().getLekarID());
                terapija.setLekar(l);
                Pacijent p = (Pacijent) vratiObjekatPoKljucu(new Pacijent(), terapija.getPacijent().getPacijentID());
                terapija.setPacijent(p);
                Korisnik k = (Korisnik) vratiObjekatPoKljucu(new Korisnik(), terapija.getKorisnik().getKorisnikID());
                terapija.setKorisnik(k);

            }
            s.close();
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException(ex.getMessage());
        }
    }

    public List<AbstractObject> vratiStavke() throws ServerskiException {
        try {
            StavkaTerapije st = new StavkaTerapije();

            String upit = "SELECT * FROM stavkaterapije";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            List<AbstractObject> listaObjekata = st.RSuTabelu(rs);
            for (AbstractObject ao : listaObjekata) {
                StavkaTerapije stavka = (StavkaTerapije) ao;
                Lek l = (Lek) vratiObjekatPoKljucu(new Lek(), stavka.getLek().getLekID());
                stavka.setLek(l);
            }
            s.close();
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException(ex.getMessage());
        }
    }

    public List<AbstractObject> vratiStavkePoTerapijaID(String terapijaID) throws ServerskiException {
        try {
            StavkaTerapije st = new StavkaTerapije();
            String upit = "SELECT * FROM stavkaterapije where terapijaID = '" + terapijaID + "'";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            List<AbstractObject> listaObjekata = st.RSuTabelu(rs);
            for (AbstractObject ao : listaObjekata) {
                StavkaTerapije s1 = (StavkaTerapije) ao;
                Lek l = (Lek) vratiObjekatPoKljucu(new Lek(), s1.getLek().getLekID());
                s1.setLek(l);
            }
            s.close();
            return listaObjekata;
        } catch (SQLException ex) {
            throw new ServerskiException(ex.getMessage());
        }
    }

    public AbstractObject obrisiStavku(StavkaTerapije st) throws ServerskiException {
        try {
            String upit = "DELETE FROM stavkaterapije WHERE " + st.vratiSlozenPK();
            Statement s = konekcija.createStatement();
            System.out.println(upit);
            s.executeUpdate(upit);
            commit();
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerskiException(ex.getMessage());
        }
        return st;
    }

    public AbstractObject sacuvajIliAzurirajObjekat(StavkaTerapije o) throws ServerskiException {
        try {
            List<AbstractObject> lista = vratiSveObjekte(o);
            System.out.println(lista);
            String upit;
            String tipUpita;
            boolean postoji = false;

            for (AbstractObject ao : lista) {
                StavkaTerapije s = (StavkaTerapije) o;
                StavkaTerapije s1 = (StavkaTerapije) ao;
                if ((s.getTerapija().getTerapijaID().equals(s1.getTerapija().getTerapijaID()) && s.getRedniBroj()==s1.getRedniBroj()) || (s.getTerapija().getTerapijaID().equals(s1.getTerapija().getTerapijaID()) && s.getNacinPripreme().equals(s1.getNacinPripreme()))) {
                    postoji = true;
                }

            }

            if (postoji) {
                tipUpita = "UPDATE";
                if (o.vratiPK() != null) {
                    upit = String.format("UPDATE %s SET %s WHERE %s = %s", o.vratiImeTabele(), o.vratiUpdate(), o.vratiPK(), o.vratiVrednostPK());
                } else {
                    upit = String.format("UPDATE %s SET %s WHERE %s", o.vratiImeTabele(), o.vratiUpdate(), o.vratiSlozenPK());
                }
            } else {
                tipUpita = "INSERT";
                upit = String.format("INSERT INTO %s VALUES (%s)", o.vratiImeTabele(), o.vratiParametre());
            }
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            s.executeUpdate(upit, s.RETURN_GENERATED_KEYS);
            if (tipUpita.equals("INSERT")) {
                ResultSet rs = s.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    o.postaviVrednostPK("" + id);
                    break;
                }
            }
            s.close();
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerskiException(ex.getMessage());
        }
    }
}
