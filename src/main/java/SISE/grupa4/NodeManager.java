package SISE.grupa4;

import java.util.ArrayList;
import java.util.HashSet;

public class NodeManager {


    public static Node BFS(Node target, int maxDepth){
        HashSet<Node> hashSet = new HashSet<>();
        ArrayList<Node> tempList = new ArrayList<>();
        ArrayList<Node> newTempList = new ArrayList<>();
        int[] x = new int[target.getConfiguration().length];
        for(int i = 0; i < x.length; i++){
            x[i] = i;
        }
        tempList.add(new Node(x, target.getWidth(), 0, "", 0));

        for(int i = 0; i < maxDepth; i++) {
            for (Node node : tempList) {
                if (!hashSet.contains(node)) {
                    if (node.equals(target)) {
                        return node;
                    }
                    hashSet.add(node);
                    if (node.canMoveLeft()) newTempList.add(node.moveLeft());
                    if (node.canMoveRight()) newTempList.add(node.moveRight());
                    if (node.canMoveUp()) newTempList.add(node.moveUp());
                    if (node.canMoveDown()) newTempList.add(node.moveDown());
                }
            }
            tempList = new ArrayList<>(newTempList);
            newTempList.clear();
        }


        return target;
    }
}
