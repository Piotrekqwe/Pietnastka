package SISE.grupa4;

import java.util.Arrays;

public class Node {
    private int[] configuration;  //aligned from 0 to max [->][v]
    private int width;                        //0 means blank
    private int position;
    private String path;
    private int depth;

    public Node(int[] configuration, int width, int position, String path, int depth) {
        this.configuration = configuration;
        this.width = width;
        this.position = position;
        this.path = path;
        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        for (int i = 0; i < configuration.length; i++) {
            if(configuration[i] != ((Node)o).configuration[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(configuration);
    }

    public boolean canMoveLeft(){
        return position % width > 0;
    }
    public Node moveLeft(){
        int[] nextConfiguration = Arrays.copyOf(configuration, configuration.length);
        nextConfiguration[position] = configuration[position - 1];
        nextConfiguration[position - 1] = 0;
        return new Node(nextConfiguration, width, position - 1, path + "L", depth + 1);
    }

    public boolean canMoveRight(){
        return position % width < width - 1;
    }
    public Node moveRight(){
        int[] nextConfiguration = Arrays.copyOf(configuration, configuration.length);
        nextConfiguration[position] = configuration[position + 1];
        nextConfiguration[position + 1] = 0;
        return new Node(nextConfiguration, width, position + 1, path + "R", depth + 1);
    }

    public boolean canMoveUp(){
        return position >= width;
    }
    public Node moveUp(){
        int[] nextConfiguration = Arrays.copyOf(configuration, configuration.length);
        nextConfiguration[position] = configuration[position - width];
        nextConfiguration[position - width] = 0;
        return new Node(nextConfiguration, width, position - width, path + "U", depth + 1);
    }

    public boolean canMoveDown(){
        return position < configuration.length - width;
    }
    public Node moveDown(){
        int[] nextConfiguration = Arrays.copyOf(configuration, configuration.length);
        nextConfiguration[position] = configuration[position + width];
        nextConfiguration[position + width] = 0;
        return new Node(nextConfiguration, width, position + width, path + "D", depth + 1);
    }

    public int[] getConfiguration() {
        return configuration;
    }

    public int getWidth() {
        return width;
    }

    public int getPosition() {
        return position;
    }

    public String getPath() {
        return path;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        return "Node{" +
                "configuration=" + Arrays.toString(configuration) +
                ", width=" + width +
                ", position=" + position +
                ", path='" + path + '\'' +
                ", depth=" + depth +
                '}';
    }
}
