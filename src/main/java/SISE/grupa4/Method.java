package SISE.grupa4;


import static java.lang.Math.sqrt;

public enum Method {
    HAMMING {
        @Override
        int score(Node node) {
            int result = 0;
            for (int i = 0; i < node.getConfiguration().length - 1; i++){
                if(node.getConfiguration()[i] != i + 1) result++;
            }
            if(node.getConfiguration()[node.getConfiguration().length - 1] != 0) result++;
            return result;
        }
    },
    MANHATTAN {
        @Override
        int score(Node node) {
            int result = 0;
            int temp;
            int distance;
            for (int i = 0; i < node.getConfiguration().length; i++){
                int val = node.getConfiguration()[i];
                if(val == 0) val = node.getConfiguration().length;
                if(val != i + 1) {
                    distance = val % node.getWidth() - (i + 1) % node.getWidth();
                    val -= distance * node.getWidth();
                    distance = Math.abs(distance);
                    distance += Math.abs(val - (i + 1));
                    result += distance;
                }
            }
            return result;
        }
    };

    abstract int score(Node node);
}
