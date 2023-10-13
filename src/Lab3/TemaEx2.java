package Lab3;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

class Produs{
    private final String nume;
    private final float pret;
    private  int cantitate;
    private final LocalDate data_expirarii;
    private float incasari;

    public Produs(String nume, float pret, int cantitate, LocalDate data_expirarii){
        super();
        this.nume=nume;
        this.pret=pret;
        this.cantitate=cantitate;
        this.data_expirarii=data_expirarii;
        this.incasari=0;
    }
    @Override
    public String toString(){
        return "Nume"+nume+", Pret:"+pret+", Cantitate:"+cantitate+", Data expirarii:"+data_expirarii;
    }
    public LocalDate getDataExpirare () {
        return data_expirarii;
    }

    public String getNume () {
        return nume;
    }
    public int getCantitate () {
        return cantitate;
    }

    public float getPret()
    {
        return pret;
    }
    public int Vanzare(int produse_vandute) {
        if(this.cantitate>0)
        {
            this.cantitate = this.cantitate-produse_vandute;
            this.incasari=this.incasari+ produse_vandute*this.pret;
            return 1;
        }
        else

            System.out.println("Nu mai avem acest produs in stoc");
        return 0;


    }


}



public class TemaEx2 {
    public static void main(String[] args)throws java.io.FileNotFoundException   {
        List <Produs> lista_produse= new ArrayList<>();
        Scanner sc = new Scanner(new File("C:\\Users\\Florentina\\Desktop\\produs.cvs"));
        sc.useDelimiter(",");
        while (sc.hasNext())
        {   String linie = sc.nextLine();
            String[] splited = linie.split(",");
            lista_produse.add(new Produs(splited[0], Float.parseFloat(splited[1]), Integer.parseInt(splited[2]), LocalDate.parse(splited[3])));

        }
        sc.close();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Alegeti optiunea dorita:");
        System.out.println("a: afișarea tuturor produselor din colecția List");
        System.out.println("b: afișarea produselor expirate");
        System.out.println("c: vânzarea unui produs, care se poate realiza doar dacă există suficientă cantitate pe stoc.");
                //Daca produsul ajunge la cantitate zero, se elimina din listă.
                // În clasa Produs se va declara o variabilă statica încasări care se va actualiza la fiecare vânzare a unui produs,
                 // luând în considerare prețul produsului vândut şi cantitatea vândută .
        System.out.println("d: afișarea produselor cu prețul minim (pot fi mai multe cu același preț)");
        System.out.println("e: salvarea produselor care au o cantitate mai mică decât o valoare citită de la tastatură\n" +
                "într-un fișier de ieșire. ");

        String optiune = scanner.nextLine();
        switch (optiune) {
            case "a":
                for(Produs p:lista_produse){
                    System.out.println(p);
                }
                break;
            case "b":
                LocalDate azi = LocalDate.now();
                System.out.println("Produsele exprirate sunt:");
                for(Produs p:lista_produse){
                   if (azi.isAfter(p.getDataExpirare()))
                       System.out.println(p);
                }
            break;
            case "c":
                System.out.println("Introduceti produsul dorit");
                String nume_vanzare = scanner.nextLine();
                System.out.println("Introduceti cantitatea dorita");
                int cantitate_vanzare = Integer.parseInt(scanner.nextLine());
                for(Produs p:lista_produse){
                    if (p.getNume().equals(nume_vanzare))
                        if (p.Vanzare(cantitate_vanzare)==1)
                            {System.out.println("Vanzare efectuata cu succes");
                            if(p.getCantitate()==0)
                                    lista_produse.remove(p);
                            }
                }
                break;
            case "d":
                float pret_minim=1000;
                for(Produs p:lista_produse){
                    if(p.getPret()<pret_minim)
                        pret_minim=p.getPret();

                }

                for(Produs p:lista_produse){
                    if(p.getPret()==pret_minim)
                        System.out.println(p.getNume());

                }
               break;
            case "e":
                PrintStream flux_out=new PrintStream("produse_pret_minim_out.txt");
                System.out.println("Introduceti valoarea pretului dorit:");
                int valoare_maxima = Integer.parseInt(scanner.nextLine());
                for(Produs p:lista_produse){
                    if(p.getPret()<valoare_maxima)
                        flux_out.println(p);
                }

            break;
        }

    }

}
