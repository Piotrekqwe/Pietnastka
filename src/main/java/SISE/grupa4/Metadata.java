package SISE.grupa4;

public class Metadata {
    public Node node;
    public int maxDepth = 0;
    public int metStates = 0;
    public int processedStates = 0;
    public double time = 0;

    @Override
    public String toString() {
        return "Metadata{" +
                "depth=" + node.getDepth() +
                ", maxDepth=" + maxDepth +
                ", metStates=" + metStates +
                ", processedStates=" + processedStates +
                ", time=" + time +
                '}';
    }
}
