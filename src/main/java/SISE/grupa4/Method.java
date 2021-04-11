package SISE.grupa4;


import static java.lang.Math.sqrt;

public enum Method {
    HAMMING {
        @Override
        int score(Node node) {
            int result = 0;
            for (int i = 0; i < node.getConfiguration().length; i++){
                if(node.getConfiguration()[i] != i) result++;
            }
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
                if(node.getConfiguration()[i] != i) {
                    temp = node.getConfiguration()[i];
                    distance = temp % node.getWidth() - i % node.getWidth();
                    temp -= distance * node.getWidth();
                    distance = Math.abs(distance);
                    distance += Math.abs(temp - i);
                    result += distance;
                }
            }
            return result;
        }
    };

    abstract int score(Node node);
}
