package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class KomunikacijaSaServerom {
    private static KomunikacijaSaServerom instanca;
    
    private static Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    
    private KomunikacijaSaServerom(){   
      
    }
    
    public static KomunikacijaSaServerom vratiInstancu() {
        if (instanca == null) {
            instanca = new KomunikacijaSaServerom();
        }
        return instanca;
    }
    
    public Socket getSocket() {
        return socket;
    }
    
    public void setSocket(Socket socket) throws IOException {
        KomunikacijaSaServerom.socket = socket;
        out = new ObjectOutputStream(KomunikacijaSaServerom.socket.getOutputStream());
        in = new ObjectInputStream(KomunikacijaSaServerom.socket.getInputStream());
        System.out.println("Podešeni in and out streamovi.");
    }

    public void posaljiZahtev(KlijentskiZahtev kz) throws IOException {
        out.reset();
        out.writeUnshared(kz);
    }

    public ServerskiOdgovor procitajOdgovor() throws IOException, ClassNotFoundException {
        return (ServerskiOdgovor) in.readUnshared();
    }
}
