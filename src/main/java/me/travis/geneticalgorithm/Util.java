package me.travis.geneticalgorithm;

import java.util.List;
import java.util.Random;

public class Util {

    private static int[] genomeWeights;
    private static int[] genomeValues;

    private static final Random random = new Random();

    public static void initGenomeWeights(int size) {
        genomeWeights = new int[size];
        for (int i = 0; i < genomeWeights.length; i++) {
            genomeWeights[i] = randomInt(5, 50);
        }
    }

    public static void initGenomeValues(int size) {
        genomeValues = new int[size];
        for (int i = 0; i < genomeValues.length; i++) {
            genomeValues[i] = randomInt(10, 50);
        }
    }

    public static int randomInt(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

    public static int oneOrZero() {
        return random.nextInt(2);
    }

    public static int getRandomIntFromPop(int size) {
        return random.nextInt(size);
    }

    public static int getCrossoverPoint() {
        return random.nextInt(7) + 1;
    }

    public static Genome getRandomGenomeFromPop(List<Genome> pop) {
        return pop.get(random.nextInt(pop.size()));
    }

    public static int getWeight(int i) {
        return genomeWeights[i];
    }

    public static int getValue(int i) {
        return genomeValues[i];
    }

    private static int getTotalFitness(List<Genome> pop) {
        int total = 0;
        for (Genome g : pop) {
            total += g.getFitness();
        }
        return total;
    }

    public static double getAverageFitness(List<Genome> pop) {
        return (double) getTotalFitness(pop) / pop.size();
    }

    public static Genome getBestGenome(List<Genome> pop) {
        Genome best = null;
        int bestFitness = 0;
        for (Genome g : pop) {
            if (g.getFitness() > bestFitness) {
                best = g;
                bestFitness = g.getFitness();
            }
        }
        return best;
    }

}
