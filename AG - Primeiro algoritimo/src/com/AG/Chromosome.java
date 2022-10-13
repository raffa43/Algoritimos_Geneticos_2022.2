package com.AG;

import java.util.*;

public class Chromosome implements Comparable<Chromosome> {
    private Random rand = new Random();
    private int[] vector = new int[22];
    private double fitness;

    public Chromosome() {
        for(int i = 0; i < vector.length; i++) {
            vector[i] = rand.nextInt(2);
        }

        fitness = fitnessCalc(normalize(toDecimal(vector)));
    }

    public Chromosome(Chromosome parent1, Chromosome parent2){
        for (int i = 0; i < 10; i++) {
            this.vector[i] = parent1.getVector()[i];
        }
        for (int i = 11; i < vector.length; i++){
            this.vector[i] = parent2.getVector()[i];
        }

        fitness = fitnessCalc(normalize(toDecimal(vector)));
    }


    private int toDecimal(int[] vector){
        int decimal = 0;

        for (int i = 0; i < vector.length; i++) {
            decimal += vector[i] * (int)Math.pow(2,i);
        }
        return decimal;

    }

    private double normalize(int num){
        return -1.0 + num * 3.0/(Math.pow(2.0,22.0)-1.0);
    }

    private double fitnessCalc(double x){
        return x*Math.sin(10*Math.PI*x)+1;
    }

    public int[] getVector() {
        return vector;
    }

    public double getFitness() {
        return fitness;
    }

    public void setVector(int[] vector) {
        this.vector = vector;
        this.fitness = fitnessCalc(normalize(toDecimal(vector)));
    }

    public String toString(){
        return ("Vector: " + Arrays.toString(getVector()) + "\n" + "Fitness Value: " + getFitness() + "\n");
    }

    @Override
    public int compareTo(Chromosome x){
        return Double.compare(this.fitness, x.getFitness());
    }


}
