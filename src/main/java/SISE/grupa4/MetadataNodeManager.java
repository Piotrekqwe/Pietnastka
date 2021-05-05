package SISE.grupa4;

import java.util.*;

public class MetadataNodeManager {

    public static Metadata BFS(Node target, int maxDepth, Direction[] directions) {
        Metadata metadata = new Metadata();
        metadata.time = System.nanoTime();
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
            int tempListSize = tempList.size();
            for (Node node : tempList) {
                if (!hashSet.contains(node)) {
                    if (node.equals(target)) {
                        metadata.time = (System.nanoTime() - metadata.time) / 1000000;
                        metadata.node = node;
                        int temp = 0;
                        for (Node n : tempList) {
                            if (!hashSet.contains(node)) temp++;
                        }
                        for (Node n : newTempList) {
                            if (!hashSet.contains(node)) temp++;
                        }
                        metadata.metStates = temp + hashSet.size();
                        metadata.processedStates = hashSet.size() + tempListSize - tempList.size();
                        metadata.maxDepth = node.getDepth();
                        return metadata;
                    }
                    hashSet.add(node);
                    if (directions[0].canMove(node)) newTempList.add(directions[0].move(node));
                    if (directions[1].canMove(node)) newTempList.add(directions[1].move(node));
                    if (directions[2].canMove(node)) newTempList.add(directions[2].move(node));
                    if (directions[3].canMove(node)) newTempList.add(directions[3].move(node));
                }
            }
            tempList = new ArrayList<>(newTempList);
            newTempList.clear();
        }

        metadata.time = (System.nanoTime() - metadata.time) / 1000000;
        metadata.node = target;
        metadata.node.setDepth(-1);
        return metadata;
    }

    public static Metadata DFS(Node starterNode, int maxDepth, Direction[] directions) {
        Metadata metadata = new Metadata();
        metadata.time = System.nanoTime();
        HashMap<Node, Node> hashMap = new HashMap<>();
        int[] x = new int[starterNode.getConfiguration().length];
        for (int i = 0; i < x.length - 1; i++) {
            x[i] = i + 1;
        }
        x[x.length - 1] = 0;
        Node target = new Node(x, starterNode.getWidth(), x.length - 1, "", 0);
        Node temp = DFSRecursive(target, starterNode, maxDepth, hashMap, directions);
        metadata.time = (System.nanoTime() - metadata.time) / 1000000;

        if (temp != null) metadata.node = temp;
        else {
            metadata.node = target;
            metadata.node.setDepth(-1);
        }

        metadata.processedStates = hashMap.size() - 1;
        metadata.metStates = hashMap.size();
        for (Node node : hashMap.values()) {
            if(node.getDepth() > metadata.maxDepth) metadata.maxDepth = node.getDepth();
        }

        return metadata;
    }

    private static Node DFSRecursive(Node target, Node node, int depthLeft, HashMap<Node, Node> hashMap, Direction[] directions) {
        if (hashMap.containsKey(node)) {
            if (hashMap.get(node).getDepth() > node.getDepth()) {
                hashMap.remove(node);
            } else {
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
            if (directions[0].canMove(node)) temp = DFSRecursive(target, directions[0].move(node), depthLeft, hashMap, directions);
            if (temp != null) return temp;
            if (directions[1].canMove(node)) temp = DFSRecursive(target, directions[1].move(node), depthLeft, hashMap, directions);
            if (temp != null) return temp;
            if (directions[2].canMove(node)) temp = DFSRecursive(target, directions[2].move(node), depthLeft, hashMap, directions);
            if (temp != null) return temp;
            if (directions[3].canMove(node)) temp = DFSRecursive(target, directions[3].move(node), depthLeft, hashMap, directions);
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

    private static void check(HashMap<Node, Node> hashMap, TreeSet<ScoredNode> movesList, Node node, Method method) {
        if (hashMap.containsKey(node)) {
            if (hashMap.get(node).getDepth() > node.getDepth()) {
                hashMap.remove(node);
                movesList.add(new ScoredNode(node, method.score(node)));
                hashMap.put(node, node);
            }
        } else {
            movesList.add(new ScoredNode(node, method.score(node)));
            hashMap.put(node, node);
        }
    }

    public static Metadata AStar(Node startingPoint, int maxDepth, Method method, Direction[] directions) {
        Metadata metadata = new Metadata();
        metadata.time = System.nanoTime();
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

        while (!movesList.isEmpty()) {
            node = movesList.pollFirst().getNode();
            metadata.processedStates++;
            if (node.equals(target)) {

                metadata.time = (System.nanoTime() - metadata.time) / 1000000;
                metadata.node = node;
                metadata.metStates = hashMap.size();

                for (Node n : hashMap.values()) {
                    if(n.getDepth() > metadata.maxDepth) metadata.maxDepth = n.getDepth();
                }
                return metadata;
            }

            if (node.getDepth() < maxDepth) {
                if (directions[0].canMove(node)) check(hashMap, movesList, directions[0].move(node), method);
                if (directions[1].canMove(node)) check(hashMap, movesList, directions[1].move(node), method);
                if (directions[2].canMove(node)) check(hashMap, movesList, directions[2].move(node), method);
                if (directions[3].canMove(node)) check(hashMap, movesList, directions[3].move(node), method);
            }
        }

        metadata.time = (System.nanoTime() - metadata.time) / 1000000;
        metadata.node = startingPoint;
        metadata.node.setDepth(-1);
        return metadata;
    }

}
