import java.util.Arrays;
import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
    private Random rd = new Random();
    private int[] a = new int[4];
    private int[] b = new int[4];
    private double fitness;

    public Chromosome(){
        for(int i = 0; i < a.length; i++) {
            a[i] = rd.nextInt(2);
            b[i] = rd.nextInt(2);
        }
        fitness = fitnessCalc();
    }

    public Chromosome(Chromosome parent1, Chromosome parent2){
        for (int i = 0; i < 2; i++) {
            this.a[i] = parent1.getA()[i];
            this.b[i] = parent1.getB()[i];
        }
        for (int i = 2; i < a.length; i++){
            this.a[i] = parent2.getA()[i];
            this.b[i] = parent2.getB()[i];
        }

        fitness = fitnessCalc();
    }

    private int toDecimal(int[] vector){
        int decimal = 0;

        for (int i = 0; i < vector.length; i++) {
            decimal += vector[i] * (int)Math.pow(2,i);
        }
        return decimal;
    }

    private int normalize(int x){
        return (((10-1)*x)/15)+1;
    }

    private int fitnessCalc(){
        int value = 2*((int)Math.pow(normalize(toDecimal(a)), 2)) + normalize(toDecimal(b)) - 57;
        if(value < 0) value *= -1;
        return value;
    }

    public void setA(int[] a) {
        this.a = a;
        this.fitness = fitnessCalc();
    }

    public void setB(int[] b) {
        this.b = b;
        this.fitness = fitnessCalc();
    }

    public int[] getA() {
        return a;
    }

    public int[] getB() {
        return b;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(Chromosome x){
        return Double.compare(this.fitness, x.getFitness());
    }

    public String toString(){
        return ("Value a: " + normalize(toDecimal(a)) + "\nVector b: " + normalize(toDecimal(b)) + "\nFitness Value: " + getFitness());
    }
}
