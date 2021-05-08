package SISE.grupa4;

import static java.lang.Math.abs;

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
            for (int i = 0; i < node.getConfiguration().length; i++){
                int val = node.getConfiguration()[i] - 1;
                if(val != i && val >= 0) {
                    int vh = val / node.getWidth();
                    int vw = val % node.getWidth();
                    int h = i / node.getWidth();
                    int w = i % node.getWidth();
                    result += abs(h - vh) + abs(w - vw);
                }
            }
            return result;
        }
    };

    abstract int score(Node node);
}
