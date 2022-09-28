package com.AG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int size = 1000;
        ArrayList<Chromosome> vectorList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            vectorList.add(new Chromosome());
        }

        double lastFitness = 0;

        for (int i = 0; i <= 2000; i++) {
            ArrayList<Chromosome> newVectorList = new ArrayList<>();

            ArrayList<Chromosome> tournament = new ArrayList<>();

            while (newVectorList.size() < 1000) {
                tournament.clear();
                for (int j = 0; j < 10; j++) {
                    tournament.add(vectorList.get(rand.nextInt(vectorList.size())));
                }

                double highestFitness = 0;
                int highestFitnessPos = 0;
                for (int j = 0; j < tournament.size(); j++) {
                    if (tournament.get(j).getFitness() > highestFitness) {
                        highestFitness = tournament.get(j).getFitness();
                        highestFitnessPos = j;
                    }
                }
                newVectorList.add(tournament.get(highestFitnessPos));
            }

            ArrayList<Chromosome> tempList1 = new ArrayList<>();

            int rec = (int)Math.round(newVectorList.size() * 0.95);

            for (int j = 0; j < rec; j++) {
                Chromosome parent1 = newVectorList.get(rand.nextInt(newVectorList.size()));
                Chromosome parent2 = newVectorList.get(rand.nextInt(newVectorList.size()));

                tempList1.add(new Chromosome(parent1, parent2));
                tempList1.add(new Chromosome(parent2, parent1));
            }

            ArrayList<Chromosome> tempList2 = new ArrayList<>();

            int mutations = (int)Math.round(newVectorList.size() * 0.05);

            for (int j = 0; j < 50; j++) {
                Chromosome mutation = newVectorList.get(rand.nextInt(newVectorList.size()));
                int[] genes = mutation.getVector();
                int genePos = rand.nextInt(genes.length);

                if(genes[genePos] == 1) genes[genePos] = 0;
                else if (genes[genePos] == 0) genes[genePos] = 1;

                mutation.setVector(genes);
                tempList2.add(mutation);
            }

            newVectorList.addAll(tempList1);
            newVectorList.addAll(tempList2);

            Collections.sort(newVectorList);
            System.out.println(newVectorList.get(newVectorList.size()-1));

            if(newVectorList.get(newVectorList.size()-1).getFitness() == lastFitness) break;
            else lastFitness = newVectorList.get(newVectorList.size()-1).getFitness();
            vectorList = newVectorList;
        }
        System.out.println("-------------------Valor final-------------------");

        
        Collections.sort(vectorList);
        System.out.println(vectorList.get(vectorList.size()-1));
    }
}
