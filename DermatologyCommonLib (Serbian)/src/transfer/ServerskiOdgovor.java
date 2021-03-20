
package transfer;

import java.io.Serializable;

public class ServerskiOdgovor implements Serializable{
    private Object odgovor;
    private int uspesnost;
    private Exception exception;

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public int getUspesnost() {
        return uspesnost;
    }

    public void setUspesnost(int uspesnost) {
        this.uspesnost = uspesnost;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
    

    
}
