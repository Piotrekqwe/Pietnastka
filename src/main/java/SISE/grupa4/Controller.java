package SISE.grupa4;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void bfs(){
        int[] x = { 2, 0, 3, 4,
                    1, 5, 6, 15,
                    9, 11, 7, 8,
                    13, 10, 14, 12};
        Node target = new Node(x, 4, 1, "", 0);

        System.out.println(PuzzleService.check(target));

        Node result = NodeManager.BFS(target, 25);
        result.reversePath();
        System.out.println(result);
    }

    public void dfs( ) {
        int[] x = { 2, 0, 3, 4,
                    1, 5, 6, 15,
                    9, 11, 7, 8,
                    13, 10, 14, 12};
        Node target = new Node(x, 4, 1, "", 0);
        Node result = NodeManager.DFS(target, 25);
        result.reversePath();
        System.out.println(result);
    }

    public void hamming() {
        int[] x = { 2, 0, 3, 4,
                    1, 5, 6, 15,
                    9, 11, 7, 8,
                    13, 10, 14, 12};
        Node startingPoint = new Node(x, 4, 1, "", 0);
        Node result = NodeManager.AStar(startingPoint, 25, Method.HAMMING);
        System.out.println(result);
    }

    public void manhattan() {
        int[] x = { 2, 0, 3, 4,
                    1, 5, 6, 15,
                    9, 11, 7, 8,
                    13, 10, 14, 12};
        Node startingPoint = new Node(x, 4, 1, "", 0);
        Node result = NodeManager.AStar(startingPoint, 25, Method.MANHATTAN);
        System.out.println(result);
    }
}
