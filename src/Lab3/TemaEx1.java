package Lab3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Parabola{
    private final int a;
    private final int b;
    private final int c;
    private int x;
    private double y;
    public Parabola(int a, int b,int c){
        super();
        this.a=a;
        this.b=b;
        this.c=c;
    }
    @Override
    public String toString(){
        return a+"x^2+"+b+"x+"+c;
    }

    public void MijloculSegmentului(){
         x= -(b/2*a);
         y=(-(Math.pow((double) b,(double)2))+4*a*c)/4*a;
        System.out.println("("+x+","+y+")");
    }

    public void SegmentDeDreapta(Parabola p){
        int new_x=(x+p.x)/2;
        double new_y=(y+p.y)/2;
        System.out.println("("+new_x+","+new_y+")");
    }
    public double LungimeSegment1Parabola(){
        return Math.hypot(x,y);
    }

    public double LungimeSegment2Parabole(Parabola p){
        return Math.sqrt(Math.pow((double)(x-p.x),2)+ Math.pow((y-p.y),2));
    }

}

public class TemaEx1 {
    public static void main(String[] args) throws FileNotFoundException {
        List <Parabola> parabole= new ArrayList<Parabola>();
        String fFileName= "in.txt";
        Scanner fileReader= new Scanner(new File(fFileName));

        while (fileReader.hasNext()) {
            String linie = fileReader.nextLine();
            String[] splited = linie.split(" ");
            parabole.add(new Parabola(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]),Integer.parseInt(splited[2])));

        }
        System.out.println("Ecuatii parabole:");
        for(Parabola p:parabole){
            System.out.println(p);
        }
        System.out.println("Mijlocul segmentului:");
        for(Parabola p:parabole){
            p.MijloculSegmentului();
        }
        System.out.println("Segement de dreapta primele doua:");
        parabole.get(0).SegmentDeDreapta(parabole.get(1));

        System.out.println("Varianta lungime segment cu o parabola:");
        for(Parabola p:parabole){
            System.out.println(p.LungimeSegment1Parabola());
        }
        System.out.println("Varianta lungime segment cu primele doua parabole:");
        System.out.println(parabole.get(0).LungimeSegment2Parabole(parabole.get(1)));
    }
}
