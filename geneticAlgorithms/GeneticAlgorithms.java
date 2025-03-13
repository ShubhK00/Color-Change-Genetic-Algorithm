package geneticAlgorithms;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GeneticAlgorithms extends JPanel{

    JFrame frame = new JFrame("Genetic Algorithms");
    ArrayList<Cell> population = new ArrayList<>();

    final int scale = 8;
    final int rows = 100;
    final int cols = 100;
    private int bg;
    Color bgColor = new Color(bg, bg, bg);
    
    public GeneticAlgorithms(int bg){
        this.bg = bg;
        for(int i = 0; i < rows * cols; i++){
            population.add(new Cell((int)(Math.random() * 255)));
        }
    }

    public void jFrameSetup(){
        frame.setSize(1000, 840);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void displayCells(Graphics g){
        int count = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                int index = i * cols + j;
                if(index < population.size()){
                    g.setColor(population.get(count).getColor());
                    g.fillRect(j * scale, i * scale, scale, scale);
                    count++;
                }
            }
        }
    }

    public void reproduce(){
        int halfSize = population.size();
        ArrayList<Cell> newGeneration = new ArrayList<>();

        for(int i = 0; i < halfSize; i++){
            Cell original = population.get(i);
            int newColor = original.getColorValue();

            if((Math.random() * 100) + 1 < 1.1){
                newColor = (int)(Math.random() * 256);
            }

            newGeneration.add(new Cell(newColor));
        }

        Collections.shuffle(newGeneration);
        population.addAll(newGeneration);
    }

    public void half(){
        population.subList(population.size() / 2, population.size()).clear();
    }

    public void sort(){
        for(int i = 1; i < population.size(); i++){
            Cell temp = population.get(i);
            int j = i - 1;
            while(j >= 0 && population.get(j).getFitnessScore(bg) < temp.getFitnessScore(bg)){
                population.set(j + 1, population.get(j));
                j--;
            }
            population.set(j + 1, temp);
        }
    }

    public void waitFor(int ms){
        try{
            Thread.sleep(ms);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void runSimulation(int x){
        for(int i = 0; i < x; i++){
            System.out.println("Generation: " + (i + 1));
            repaint();
            waitFor(2000);
            sort();
            repaint();
            waitFor(2000);
            half();
            repaint();
            waitFor(2000);
            reproduce();
        }
        waitFor(2000);
        sort();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(bgColor);
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, frame.getWidth(), frame.getHeight());
        displayCells(g);
    }

    public Color getBgColor() {
        return bgColor;
    }
}