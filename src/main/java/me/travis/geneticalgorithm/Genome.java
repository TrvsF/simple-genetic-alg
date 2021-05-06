package me.travis.geneticalgorithm;

public class Genome {

    private final int[] genome;

    public Genome(int size) {
        this.genome = generateGenome(size);
    }

    public Genome(Genome genome) {
        this.genome = genome.getGenome().clone();
    }

    public int[] getGenome() {
        return this.genome;
    }

    private int[] generateGenome(int size) {
        int[] value = new int[size];
        for (int i = 0; i < value.length; i++) {
            value[i] = Util.oneOrZero();
        }
        return value;
    }

    public void printValue() {
        StringBuilder v = new StringBuilder();
        for (int g : this.genome) {
            v.append(g);
        }
        System.out.print("value=" + v.toString() + " : fitness=" + this.getFitness());
        System.out.println();
    }

    public String getGenomeAsString() {
        StringBuilder v = new StringBuilder();
        for (int g : this.genome) {
            v.append(g);
        }
        return v.toString();
    }

    public int getFitness() {
        return (this.getWeight() > GeneticAlgorithm.weightLimit ? 0 : this.getValue());
    }

    private int getValue() {
        int total = 0;
        for (int i = 0; i < this.genome.length; i++) {
            if (this.genome[i] == 1) {
                total += Util.getValue(i);
            }
        }
        return total;
    }

    private int getWeight() {
        int total = 0;
        for (int i = 0; i < this.genome.length; i++) {
            if (this.genome[i] == 1) {
                total += Util.getWeight(i);
            }
        }
        return total;
    }

}
