package serverskiDeo;

import domen.AbstractObject;
import domen.Korisnik;
import domen.Lek;
import domen.Lekar;
import domen.Pacijent;
import domen.Terapija;
import exception.ServerskiException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konstante.Operacija;
import kont.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class ObradaKlijentskogZahteva extends Thread {

    private Socket socket;
    private List<ObradaKlijentskogZahteva> klijenti;
    ObjectInputStream in;
    ObjectOutputStream out;
    AbstractObject korisnik;

    public ObradaKlijentskogZahteva(Socket socket, List<ObradaKlijentskogZahteva> klijenti) {
        this.socket = socket;
        this.klijenti = klijenti;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        System.out.println("Klijent nit pokrenuta.");
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println("Čeka se zahtev..");
                KlijentskiZahtev kz = (KlijentskiZahtev) in.readUnshared();
                ServerskiOdgovor so = new ServerskiOdgovor();
                try{
                int operacija = kz.getOperacija();
                switch (operacija) {
                    case Operacija.ULOGUJ_KORISNIKA:
                        Korisnik k = (Korisnik) kz.getParametar();
                        korisnik = Kontroler.vratiInstancu().ulogujKorisnika(k);
                        so.setOdgovor(korisnik);
                        break;
                    case Operacija.SACUVAJ_LEK:
                        Lek l = (Lek) kz.getParametar();
                        AbstractObject lek = Kontroler.vratiInstancu().kreirajLek(l);
                        so.setOdgovor(lek);
                        break;
                    case Operacija.UCITAJ_LEKOVE:
                        List<AbstractObject> listaLekova = Kontroler.vratiInstancu().vratiListuLekova();
                        so.setOdgovor(listaLekova);
                        break;
                    case Operacija.OBRISI_LEK:
                        Lek l1 = (Lek) kz.getParametar();
                        Lek l2 = (Lek) Kontroler.vratiInstancu().obrisiLek(l1);
                        so.setOdgovor(l2);
                        break;
                    case Operacija.SACUVAJ_LEKARA:
                        Lekar le = (Lekar) kz.getParametar();
                        AbstractObject lekar = Kontroler.vratiInstancu().kreirajLekara(le);
                        so.setOdgovor(lekar);
                        break;
                    case Operacija.UCITAJ_LEKARE:
                        List<AbstractObject> listaLekara = Kontroler.vratiInstancu().vratiListuLekara();
                        so.setOdgovor(listaLekara);
                        break;
                    case Operacija.OBRISI_LEKARA:
                        Lekar le1 = (Lekar) kz.getParametar();
                        Lekar le2 = (Lekar) Kontroler.vratiInstancu().obrisiLekara(le1);
                        so.setOdgovor(le2);
                        break;
                    case Operacija.SACUVAJ_PACIJENTA:
                        Pacijent p = (Pacijent) kz.getParametar();
                        AbstractObject pacijent = Kontroler.vratiInstancu().kreirajLekara(p);
                        so.setOdgovor(pacijent);
                        break;
                    case Operacija.PRETRAZI_LEKOVE:
                        String pretraga = (String) kz.getParametar();
                        List<AbstractObject> listaL = Kontroler.vratiInstancu().pretraziLekove(pretraga);
                        so.setOdgovor(listaL);
                        break;
                    case Operacija.PRETRAZI_LEKARE:
                        String pretragaa = (String) kz.getParametar();
                        List<AbstractObject> listaLE = Kontroler.vratiInstancu().pretraziLekare(pretragaa);
                        so.setOdgovor(listaLE);
                        break;
                    case Operacija.VRATI_PACIJENTE:
                        List<AbstractObject> listaPacijenata = Kontroler.vratiInstancu().vratiListuPacijenata();
                        so.setOdgovor(listaPacijenata);
                        break;
                    case Operacija.OBRISI_PACIJENTA:
                        Pacijent pa1 = (Pacijent) kz.getParametar();
                        Pacijent pa2 = (Pacijent) Kontroler.vratiInstancu().obrisiPacijenta(pa1);
                        so.setOdgovor(pa2);
                        break;
                    case Operacija.PRETRAZI_PACIJENTE:
                        String pretraga1 = (String) kz.getParametar();
                        List<AbstractObject> listaPA = Kontroler.vratiInstancu().pretraziPacijente(pretraga1);
                        so.setOdgovor(listaPA);
                        break;
                    case Operacija.AZURIRAJ_PACIJENTA:
                        Pacijent p2 = (Pacijent) kz.getParametar();
                        AbstractObject p3 = Kontroler.vratiInstancu().azurirajPacijenta(p2);
                        so.setOdgovor(p3);
                        break;
                    case Operacija.SACUVAJ_TERAPIJU:
                        Terapija terapija = (Terapija) kz.getParametar();
                        AbstractObject terapija1 = Kontroler.vratiInstancu().zapamtiTerapiju(terapija);
                        so.setOdgovor(terapija1);
                        break;
                    case Operacija.IZLOGUJ_KORISNIKA:
                        Korisnik kor = (Korisnik) kz.getParametar();
                        Kontroler.vratiInstancu().izlogujKorisnika(kor);
                        break;
                    case Operacija.VRATI_TERAPIJE:
                        List<AbstractObject> listaTerapija = Kontroler.vratiInstancu().vratiListuTerapija();
                        so.setOdgovor(listaTerapija);
                        break;
                    case Operacija.PRETRAZI_TERAPIJE:
                        String pretraga2 = (String) kz.getParametar();
                        List<AbstractObject> listaNadjenihTerapija = Kontroler.vratiInstancu().pretraziTerapije(pretraga2);
                        so.setOdgovor(listaNadjenihTerapija);
                        break;
                    case Operacija.AZURIRAJ_TERAPIJU:
                        Terapija terapijica = (Terapija) kz.getParametar();
                        AbstractObject terap = Kontroler.vratiInstancu().azurirajTerapiju(terapijica);
                        so.setOdgovor(terap);
                        break;

                }
                so.setUspesnost(1);
            }
            catch (ServerskiException ex) {
                    so.setUspesnost(-1);
                    so.setException(ex);
                }
                out.reset();
                out.writeUnshared(so);
            }
                
        } catch (SocketException ex) {
            try {
                System.out.println("Klijent se isključuje.");

                in.close();
                out.close();
                socket.close();
                klijenti.remove(this);
            } catch (IOException ex1) {
                Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskogZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
