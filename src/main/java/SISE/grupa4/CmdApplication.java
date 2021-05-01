package SISE.grupa4;

import java.io.File;

import static SISE.grupa4.CmdApplication.Strategy.*;

public class CmdApplication {

    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("za mało argumentów");
            return;
        }

        Strategy strategy;
        switch (args[0]) {
            case "bfs":
                strategy = BFS;
                break;
            case "dfs":
                strategy = DFS;
                break;
            case "astr":
                strategy = A_STAR;
                break;
            default:
                System.out.println("Błędna strategia");
                return;
        }

        Direction[] directions = new Direction[0];
        Method method = null;
        if (strategy != A_STAR && args[1].length() == 4 && args[1].contains("L")
                && args[1].contains("R") && args[1].contains("U") && args[1].contains("D")) {
            directions = PuzzleService.getDirections(args[1]);
        } else if (strategy == A_STAR) {
            if (args[1].equals("manh")) {
                method = Method.MANHATTAN;
            } else if (args[1].equals("hamm")) {
                method = Method.HAMMING;
            } else {
                System.out.println("Błędna metoda");
                return;
            }
        }
        Node target = null;

        if(new File(args[2]).exists()) {
            target = Utils.getData(args[2]);
        }
        else{
            System.out.println("Plik z układem początkowym nie istnieje");
            return;
        }

        if (!PuzzleService.check(target)) {
            System.out.println("Plik z układem początkowym jest źle sformatowany, lub nie ma rozwiązania");
            return;
        }

        Metadata result;

        switch (strategy) {
            case BFS:
                result = MetadataNodeManager.BFS(target, PuzzleService.MAX_DEPTH, directions);
                result.node.reversePath();
                break;
            case DFS:
                result = MetadataNodeManager.DFS(target, PuzzleService.MAX_DEPTH, directions);
                result.node.reversePath();
                break;
            case A_STAR:
                result = MetadataNodeManager.AStar(target, PuzzleService.MAX_DEPTH, method, new Direction[]{Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN});
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + strategy);
        }

        Utils.saveResult(result, args[3], args[4]);
    }

    enum Strategy {
        BFS,
        DFS,
        A_STAR
    }
}
