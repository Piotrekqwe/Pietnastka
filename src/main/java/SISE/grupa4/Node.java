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

    public void setDepth(int depth) {
        this.depth = depth;
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

    public void reversePath() {
        StringBuilder sb = new StringBuilder(path);
        sb.reverse();

        String result = sb.toString().replace('U', 'X');
        result = result.replace('D', 'U');
        result = result.replace('X', 'D');
        result = result.replace('L', 'X');
        result = result.replace('R', 'L');
        path = result.replace('X', 'R');
    }
}

