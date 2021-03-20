package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public abstract class AbstractObject implements Serializable {

    public abstract String vratiImeTabele();
    public abstract String vratiParametre();
    public abstract String vratiPK();
    public abstract String vratiVrednostPK();
    public abstract String vratiSlozenPK();
    public abstract List<AbstractObject> RSuTabelu(ResultSet rs);
    public abstract String vratiUpdate();
    public abstract void postaviVrednostPK(String pk);
    public abstract String vratiKolone();
    private int status;
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

}
