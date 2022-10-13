import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rd = new Random();
        
        int size = 1000;
        ArrayList<Chromosome> chromosomes = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            chromosomes.add(new Chromosome());
        }

        double lastFitness = Double.MAX_VALUE;
        int generations = 2000;

        for (int i = 0; i < generations; i++) {
            ArrayList<Chromosome> newChromosomes = new ArrayList<>();

            ArrayList<Chromosome> tournament = new ArrayList<>();

            while(newChromosomes.size() < 1000){
                tournament.clear();

                for (int j = 0; j < 10; j++) { tournament.add(chromosomes.get(rd.nextInt(chromosomes.size()))); }

                double bestFitness = Double.MAX_VALUE;
                int bestFitnessPos = 0;
                for (int j = 0; j < tournament.size(); j++) {
                    if (tournament.get(j).getFitness() < bestFitness) {
                        bestFitness = tournament.get(j).getFitness();
                        bestFitnessPos = j;
                    }
                }
                newChromosomes.add(tournament.get(bestFitnessPos));
            }

            ArrayList<Chromosome> recombined = new ArrayList<>();
            int rec = (int)Math.round(newChromosomes.size() * 0.90);

            for (int j = 0; j < rec; j++) {
                Chromosome parent1 = newChromosomes.get(rd.nextInt(newChromosomes.size()));
                Chromosome parent2 = newChromosomes.get(rd.nextInt(newChromosomes.size()));

                recombined.add(new Chromosome(parent1, parent2));
                recombined.add(new Chromosome(parent2, parent1));
            }

            ArrayList<Chromosome> mutated = new ArrayList<>();

            int mutations = (int)Math.round(newChromosomes.size() * 0.1);

            for (int j = 0; j < mutations; j++) {
                Chromosome mutation = newChromosomes.get(rd.nextInt(newChromosomes.size()));
                int[] genesA = mutation.getA();
                int geneAPos = rd.nextInt(genesA.length);
                int[] genesB = mutation.getB();
                int geneBPos = rd.nextInt(genesB.length);

                if(genesA[geneAPos] == 1) genesA[geneAPos] = 0;
                else genesA[geneAPos] = 1;

                if(genesB[geneBPos] == 1) genesB[geneBPos] = 0;
                else genesB[geneBPos] = 1;

                mutation.setA(genesA);
                mutation.setB(genesB);

                mutated.add(mutation);
            }

            newChromosomes.addAll(mutated);
            newChromosomes.addAll(recombined);

            Collections.sort(newChromosomes);
            System.out.println(newChromosomes.get(0).toString() + "\n");

            if(newChromosomes.get(newChromosomes.size()-1).getFitness() == lastFitness) break;
            else lastFitness = newChromosomes.get(newChromosomes.size()-1).getFitness();
            chromosomes = newChromosomes;
        }

        System.out.println("-------------------Valor final-------------------");


        Collections.sort(chromosomes);
        System.out.println(chromosomes.get(0));
    }
}