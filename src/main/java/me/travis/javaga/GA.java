package me.travis.javaga;

import java.util.ArrayList;
import java.util.List;

public class GA {

    public static int weightLimit = 100;

    private static Genome bestGenome    = null;
    private static double bestAvgFit    = 0;
    private static int bestAvgGen       = 0;
    private static int bestFitnessGen   = 0;
    private static int bestFitness      = 0;
    private static int generation       = 0;

    private static final int loops = 10;
    private static final int initPopulation = 10;

    private static final List<Genome> population = new ArrayList<>();

    public static void main(String[] args) {

        for (int i = 0; i < loops; i++) {
            generation++;
        }

        populate();
        printPopulation();
        checkLandmarks();
        // mutate

    }

    private static void populate() {
        for (int i = 0; i < initPopulation; i++) {
            Genome g = new Genome();
            population.add(g);
        }
    }

    private static void printPopulation() {
        for (Genome g : population) {
            g.printValue();
        }
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

    private static void checkLandmarks() {

        Genome best = Util.getBestGenome(population);
        double bestAvg = Util.getAverageFitness(population);

        if (best.getFitness() > bestFitness) {
            bestFitness = best.getFitness();
            bestGenome = best;
            bestAvgFit = generation;
        }
        if (bestAvg > bestAvgFit) {
            bestAvgFit = bestAvg;
            bestAvgGen = generation;
        }

    }

}