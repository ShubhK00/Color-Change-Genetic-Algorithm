package geneticAlgorithms;

import java.awt.Color;

class Cell{
    private int color;
    private int fitnessScore;

    public Cell(int color){
        this.color = Math.max(0, Math.min(255, color));
    }

    public int getColorValue(){
        return color;
    }

    public int calculateFitness(int bg){
        this.fitnessScore = 255 - Math.abs(bg - color);
        return 255 - Math.abs(bg - color);
    }

    public int getFitnessScore(int bg){
        return calculateFitness(bg);
    }

    public Color getColor(){
        return new Color(color, color, color);
    }
}