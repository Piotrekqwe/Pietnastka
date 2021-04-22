package SISE.grupa4;

import java.util.*;

public class NodeManager {


    public static Node BFS(Node target, int maxDepth) {
        HashSet<Node> hashSet = new HashSet<>();
        ArrayList<Node> tempList = new ArrayList<>();
        ArrayList<Node> newTempList = new ArrayList<>();
        int[] x = new int[target.getConfiguration().length];
        for (int i = 0; i < x.length - 1; i++) {
            x[i] = i + 1;
        }
        x[x.length - 1] = 0;
        tempList.add(new Node(x, target.getWidth(), x.length - 1, "", 0));

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
        HashMap<Node, Node> hashMap = new HashMap<>();
        int[] x = new int[target.getConfiguration().length];
        for (int i = 0; i < x.length - 1; i++) {
            x[i] = i + 1;
        }
        x[x.length - 1] = 0;
        Node starterNode = new Node(x, target.getWidth(), x.length - 1, "", 0);
        Node temp = DFSRecursive(target, starterNode, maxDepth, hashMap);
        if (temp != null) return temp;
        return target;
    }

    private static Node DFSRecursive(Node target, Node node, int depthLeft, HashMap<Node, Node> hashMap) {
        if (hashMap.containsKey(node)) {
            if(hashMap.get(node).getDepth() > node.getDepth()){
                hashMap.remove(node);
            }
            else{
                return null;
            }
        }
        if (node.equals(target)) {
            return node;
        }
        hashMap.put(node, node);
        depthLeft--;
        if (depthLeft > 0) {
            Node temp = null;
            if (node.canMoveLeft()) temp = DFSRecursive(target, node.moveLeft(), depthLeft, hashMap);
            if (temp != null) return temp;
            if (node.canMoveRight()) temp = DFSRecursive(target, node.moveRight(), depthLeft, hashMap);
            if (temp != null) return temp;
            if (node.canMoveUp()) temp = DFSRecursive(target, node.moveUp(), depthLeft, hashMap);
            if (temp != null) return temp;
            if (node.canMoveDown()) temp = DFSRecursive(target, node.moveDown(), depthLeft, hashMap);
            return temp;
        }

        return null;
    }

    private static class ScoredNode {
        private final Node node;
        private final Integer score;

        public ScoredNode(Node node, int score) {
            this.node = node;
            this.score = score;
        }

        public Node getNode() {
            return node;
        }

        public Integer getScore() {
            return score;
        }
    }

    private static void check(HashMap<Node, Node> hashMap, TreeSet<ScoredNode> movesList, Node node, Method method){
        if (hashMap.containsKey(node)) {
            if(hashMap.get(node).getDepth() > node.getDepth()){
                hashMap.remove(node);
                movesList.add(new ScoredNode(node, method.score(node)));
                hashMap.put(node, node);
            }
        }
        else{
            movesList.add(new ScoredNode(node, method.score(node)));
            hashMap.put(node, node);
        }
    }

    public static Node AStar(Node startingPoint, int maxDepth, Method method){
        HashMap<Node, Node> hashMap = new HashMap<>();
        TreeSet<ScoredNode> movesList = new TreeSet<>((o1, o2) -> {
            if (o1.getScore() < o2.getScore()) {
                return -1;
            }
            return 1;
        });

        int[] x = new int[startingPoint.getConfiguration().length];
        for (int i = 0; i < x.length - 1; i++) {
            x[i] = i + 1;
        }
        x[x.length - 1] = 0;
        Node target = new Node(x, startingPoint.getWidth(), x.length - 1, "", 0);
        movesList.add(new ScoredNode(startingPoint, method.score(startingPoint)));
        hashMap.put(startingPoint, startingPoint);
        Node node;

        while(!movesList.isEmpty()){
            node = movesList.pollFirst().getNode();
            if (node.equals(target)) {
                return node;
            }

            if(node.getDepth() < maxDepth){
                if (node.canMoveLeft()) check(hashMap, movesList, node.moveLeft(), method);
                if (node.canMoveRight()) check(hashMap, movesList, node.moveRight(), method);
                if (node.canMoveUp()) check(hashMap, movesList, node.moveUp(), method);
                if (node.canMoveDown()) check(hashMap, movesList, node.moveDown(), method);
            }
        }
        return startingPoint;
    }

}
