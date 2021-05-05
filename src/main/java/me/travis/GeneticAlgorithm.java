package me.travis;

import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm {

    public static int weightLimit = 100;

    // averages and stats
    private static Genome bestGenome        = null;
    private static int bestGeneration       = 0;
    private static int timesSeenBest        = 0;

    // counter
    private static int generation           = 0;

    // rates
    private final static int crossoverRate  = 10; // fraction of pop to crossover
    private final static int mutationRate   = 30; // fraction of pop to mutate

    private static final int loops = 10000;
    private static final int initPopulation = 100;

    private static final List<Genome> population = new ArrayList<>();

    public static void main(String[] args) {

        populate();

        for (int i = 0; i < loops; i++) {
            generation++;
            printPopulation();
            checkStats();
            removeDeadGenomes();
            tourneySelection();
            mutate();
            refill();
        }

        printStats();

    }

    private static void tourneySelection() {

        for (int i = 0; i < population.size() / crossoverRate; i++) {
            Genome parent1 = null;
            Genome parent2 = null;

            for (int j = 0; j < 2; j++) { // do this twice to get 2 parents
                int x = Util.getRandomIntFromPop(population.size());
                int y = Util.getRandomIntFromPop(population.size());
                if (x == y) continue;

                Genome winner = (population.get(x).getFitness() > population.get(y).getFitness() ?
                        population.get(x) : population.get(y));

                if (j == 0) {
                    parent1 = winner;
                } else {
                    parent2 = winner;
                }
            }
            if (parent1 == parent2 || parent1 == null || parent2 == null) continue;
            crossover(parent1, parent2);
        }
    }

    private static void crossover(Genome a, Genome b) {
        int cutPoint = Util.getCrossoverPoint();
        for (int i = 0; i < cutPoint; i++) {
            int temp = a.getGenome()[i];
            a.getGenome()[i] = b.getGenome()[i];
            b.getGenome()[i] = temp;
        }
    }

    private static void mutate() {
        for (int i = 0; i < population.size() / mutationRate; i++) {
            Genome g = Util.getRandomGenomeFromPop(population);
            int bitToFlip = Util.getCrossoverPoint();
            g.getGenome()[bitToFlip] = g.getGenome()[bitToFlip] ^ 1;
        }
    }

    private static void refill() {
        for (int i = 0; i < initPopulation - population.size(); i++) {
            population.add(new Genome());
        }
    }

    private static void populate() {
        for (int i = 0; i < initPopulation; i++) {
            population.add(new Genome());
        }
    }

    private static void printPopulation() {
        int total = 0;
        for (Genome g : population) {
            g.printValue();
            total += g.getFitness();
        }
        System.out.println("Total Fitness : " + total);
        System.out.println("Total pop : " + population.size());
        System.out.println("==========================");
    }

    private static void removeDeadGenomes() {
        List<Genome> deadGenomes = new ArrayList<>();
        for (Genome g : population) {
            if (g.getFitness() == 0) {
                deadGenomes.add(g);
            }
        }
        population.removeAll(deadGenomes);
    }

    private static void checkStats() {
        Genome best = new Genome(Util.getBestGenome(population));
        if (best.getFitness() > (bestGenome == null ? 0 : bestGenome.getFitness())) {
            bestGenome = best;
            bestGeneration = generation;
            timesSeenBest = 0;
        }
        if (best.getFitness() == (bestGenome == null ? 0 : bestGenome.getFitness())) {
            timesSeenBest++;
        }
    }

    private static void printStats() {
        System.out.println("BEST GENOME : " + bestGenome.getGenomeAsString());
        System.out.println("TIMES SEEN BEST GENOME " + timesSeenBest);
        System.out.println("BEST FITNESS : " + bestGenome.getFitness());
        System.out.println("BEST GENERATION : " + bestGeneration);
    }

}
