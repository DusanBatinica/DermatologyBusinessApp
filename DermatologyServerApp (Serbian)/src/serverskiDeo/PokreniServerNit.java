package serverskiDeo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PokreniServerNit extends Thread {

    private ServerSocket serverSocket;
    public static int brojPorta = 9000;
    static List<ObradaKlijentskogZahteva> klijenti = new ArrayList<>();
    private static boolean radi = false;
    
    public PokreniServerNit() {
        try {
            serverSocket = new ServerSocket(brojPorta);
            System.out.println("Kreiran server socket na portu " + brojPorta+".");
        } catch (Exception ex) {
            System.out.println("Greška prilikom pokretanja servera!");
        }
    }
    public void run() {
        try {
            System.out.println("Čeka се klijent..");
            while (!isInterrupted()) {
                Socket socket = serverSocket.accept();
                ObradaKlijentskogZahteva okz = new ObradaKlijentskogZahteva(socket, klijenti);
                okz.start();
                klijenti.add(okz);
                System.out.println("Novi klijent se povezao!");
            }
        } catch (SocketException ex) {
            System.out.println("Server se gasi.");
        } catch (IOException e) {
            System.out.println("Greška prilikom povezivanja sa klijentom!");
        }
    }
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static boolean isRadi() {
        return radi;
    }

    public static void setRadi(boolean radi) {
        PokreniServerNit.radi = radi;
    }

    public void zaustaviNiti() {
        try {
            serverSocket.close();
            for (ObradaKlijentskogZahteva obradaNit : klijenti) {
                obradaNit.getSocket().close();
            }
        } catch (IOException ioe) {
            Logger.getLogger(PokreniServerNit.class.getName()).log(Level.SEVERE, null, ioe);
        }
    }

}
