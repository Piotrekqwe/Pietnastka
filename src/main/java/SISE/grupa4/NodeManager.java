package SISE.grupa4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class NodeManager {


    public static Node BFS(Node target, int maxDepth) {
        HashSet<Node> hashSet = new HashSet<>();
        ArrayList<Node> tempList = new ArrayList<>();
        ArrayList<Node> newTempList = new ArrayList<>();
        int[] x = new int[target.getConfiguration().length];
        for (int i = 0; i < x.length; i++) {
            x[i] = i;
        }
        tempList.add(new Node(x, target.getWidth(), 0, "", 0));

        for (int i = 0; i < maxDepth; i++) {
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


    public static Node DFS(Node target, int maxDepth) {
        HashMap<Node, Node> hashSet = new HashMap<>();
        int[] x = new int[target.getConfiguration().length];
        for (int i = 0; i < x.length; i++) {
            x[i] = i;
        }
        Node starterNode = new Node(x, target.getWidth(), 0, "", 0);
        Node temp = DFSRecursive(target, starterNode, maxDepth, hashSet);
        if (temp != null) return temp;
        return target;
    }

    private static Node DFSRecursive(Node target, Node node, int depthLeft, HashMap<Node, Node> hashSet) {
        if (hashSet.containsKey(node)) {
            if(hashSet.get(node).getDepth() > node.getDepth()){
                hashSet.remove(node);
            }
            else{
                return null;
            }
        }
        if (node.equals(target)) {
            return node;
        }
        hashSet.put(node, node);
        depthLeft--;
        if (depthLeft > 0) {
            Node temp = null;
            if (node.canMoveLeft()) temp = DFSRecursive(target, node.moveLeft(), depthLeft, hashSet);
            if (temp != null) return temp;
            if (node.canMoveRight()) temp = DFSRecursive(target, node.moveRight(), depthLeft, hashSet);
            if (temp != null) return temp;
            if (node.canMoveUp()) temp = DFSRecursive(target, node.moveUp(), depthLeft, hashSet);
            if (temp != null) return temp;
            if (node.canMoveDown()) temp = DFSRecursive(target, node.moveDown(), depthLeft, hashSet);
            return temp;
        }

        return null;
    }
}
