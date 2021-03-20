package so;
import db.DBBroker;
import exception.ServerskiException;
public abstract class AbstractSO {

    protected DBBroker db;

    public AbstractSO() {
        this.db = new DBBroker();
    }

    synchronized public void izvrsiOperaciju() throws ServerskiException {
        otvoriKonekciju();
        try {
            izvrsiKonkretnuOperaciju();
            commit();
        } catch (ServerskiException e) {
            System.out.println("ROLLBACK");
            rollback();
            throw e;
        } finally {
            zatvoriKonekciju();
        }
    }

    private void commit() throws ServerskiException {
        db.commit();
    }

    private void rollback() {
        db.rollback();
    }

    private void zatvoriKonekciju() throws ServerskiException {
        db.zatvoriKonekciju();
    }

    private void otvoriKonekciju() throws ServerskiException {
        db.otvoriKonekciju();
    }

    protected abstract void izvrsiKonkretnuOperaciju() throws ServerskiException;

}