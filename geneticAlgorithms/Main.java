package geneticAlgorithms;

class Main{
    public static void main(String[] args){
        GeneticAlgorithms ga = new GeneticAlgorithms(0);
        ga.jFrameSetup();
        ga.runSimulation(10);
    }
}
