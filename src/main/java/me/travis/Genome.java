package me.travis;

public class Genome {

    private final int[] genome;

    public Genome() {
        this.genome = generateGenome();
    }

    public Genome(Genome genome) {
        this.genome = genome.getGenome().clone();
    }

    public int[] getGenome() {
        return this.genome;
    }

    private int[] generateGenome() {
        int[] value = new int[8];
        for (int i = 0; i < value.length; i++) {
            value[i] = Util.oneOrZero();
        }
        return value;
    }

    public void printValue() {
        String v = String.format("%d%d%d%d%d%d%d%d",this.genome[0],this.genome[1],this.genome[2],this.genome[3],this.genome[4],this.genome[5],this.genome[6],this.genome[7]);
        System.out.print("value=" + v + " : fitness=" + this.getFitness());
        System.out.println();
    }

    public String getGenomeAsString() {
        return String.format("%d%d%d%d%d%d%d%d",this.genome[0],this.genome[1],this.genome[2],this.genome[3],this.genome[4],this.genome[5],this.genome[6],this.genome[7]);
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
