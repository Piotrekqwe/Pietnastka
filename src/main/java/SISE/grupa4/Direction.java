package SISE.grupa4;

import java.util.Arrays;

public enum Direction {
    LEFT {
        @Override
        public boolean canMove(Node node){
            return node.getPosition() % node.getWidth() > 0;
        }
        @Override
        public Node move(Node node){
            int[] nextConfiguration = Arrays.copyOf(node.getConfiguration(), node.getConfiguration().length);
            nextConfiguration[node.getPosition()] = node.getConfiguration()[node.getPosition() - 1];
            nextConfiguration[node.getPosition() - 1] = 0;
            return new Node(nextConfiguration, node.getWidth(), node.getPosition() - 1, node.getPath() + "L", node.getDepth() + 1);
        }
    },
    RIGHT {
        @Override
        public boolean canMove(Node node){
            return node.getPosition() % node.getWidth() < node.getWidth() - 1;
        }
        @Override
        public Node move(Node node){
            int[] nextConfiguration = Arrays.copyOf(node.getConfiguration(), node.getConfiguration().length);
            nextConfiguration[node.getPosition()] = node.getConfiguration()[node.getPosition() + 1];
            nextConfiguration[node.getPosition() + 1] = 0;
            return new Node(nextConfiguration, node.getWidth(), node.getPosition() + 1, node.getPath() + "R", node.getDepth() + 1);
        }
    },
    UP{
        @Override
        public boolean canMove(Node node){
            return node.getPosition() >= node.getWidth();
        }
        @Override
        public Node move(Node node){
            int[] nextConfiguration = Arrays.copyOf(node.getConfiguration(), node.getConfiguration().length);
            nextConfiguration[node.getPosition()] = node.getConfiguration()[node.getPosition() - node.getWidth()];
            nextConfiguration[node.getPosition() - node.getWidth()] = 0;
            return new Node(nextConfiguration, node.getWidth(), node.getPosition() - node.getWidth(), node.getPath() + "U", node.getDepth() + 1);
        }
    },
    DOWN{
        @Override
        public boolean canMove(Node node){
            return node.getPosition() < node.getConfiguration().length - node.getWidth();
        }
        @Override
        public Node move(Node node){
            int[] nextConfiguration = Arrays.copyOf(node.getConfiguration(), node.getConfiguration().length);
            nextConfiguration[node.getPosition()] = node.getConfiguration()[node.getPosition() + node.getWidth()];
            nextConfiguration[node.getPosition() + node.getWidth()] = 0;
            return new Node(nextConfiguration, node.getWidth(), node.getPosition() + node.getWidth(), node.getPath() + "D", node.getDepth() + 1);
        }
    };

    public abstract boolean canMove(Node node);
    abstract Node move(Node node);

}
