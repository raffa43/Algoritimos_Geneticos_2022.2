package com.AG;

import java.util.ArrayList;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int size = 1000;
        ArrayList<Chromosome> vectorList = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            vectorList.add(new Chromosome());
        }
        /*
        for (int i = 0; i < vectorList.size(); i++) {
            double aux = (vectorList.get(i)).getFitness();
            for (int j = i; j < vectorList.size(); j++) {
                if()
            }
        }*/

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
                    if (tournament.get(i).getFitness() > highestFitness) {
                        highestFitness = tournament.get(i).getFitness();
                        highestFitnessPos = j;
                    }
                }
                newVectorList.add(tournament.get(highestFitnessPos));
            }

            for (int j = 0; j < 500; j++) {
                Chromosome parent1 = newVectorList.get(rand.nextInt(newVectorList.size()));
                Chromosome parent2 = newVectorList.get(rand.nextInt(newVectorList.size()));

                newVectorList.add(new Chromosome(parent1, parent2));
                newVectorList.add(new Chromosome(parent2, parent1));
            }

        }
    }
}
