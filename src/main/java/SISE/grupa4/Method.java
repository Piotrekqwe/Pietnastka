package SISE.grupa4;

public enum Method {
    HAMMING {
        @Override
        int score(Node node) {
            int result = 0;
            for (int i = 0; i < node.getConfiguration().length - 1; i++){
                if(node.getConfiguration()[i] != i + 1) result++;
            }
            return result;
        }
    },
    MANHATTAN {
        @Override
        int score(Node node) {
            int result = 0;
            int distance;
            for (int i = 0; i < node.getConfiguration().length; i++){
                int val = node.getConfiguration()[i];
                if(val != i + 1 && val != 0) {
                    distance = (val - 1) % node.getWidth() - (i) % node.getWidth();
                    val -= distance;
                    distance = Math.abs(distance);
                    distance += Math.abs((val - (i + 1)) % node.getWidth());
                    result += distance;
                }
            }
            return result;
        }
    };

    abstract int score(Node node);
}
