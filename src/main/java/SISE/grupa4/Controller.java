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
        int[] x = { 4, 1, 5, 3,
                    8, 2, 7, 11,
                    12, 6, 9, 15,
                    13, 14, 10, 0};
        Node target = new Node(x, 4, 0, "", 0);
        Node result = NodeManager.BFS(target, 25);
        System.out.println(result);
    }

    public void dfs( ) {
        int[] x = { 4, 1, 5, 3,
                    8, 2, 7, 11,
                    12, 6, 9, 15,
                    13, 14, 10, 0};
        Node target = new Node(x, 4, 0, "", 0);
        Node result = NodeManager.DFS(target, 25);
        System.out.println(result);
    }

    public void hamming() {
        int[] x = { 4, 1, 5, 3,
                    8, 2, 7, 11,
                    12, 6, 9, 15,
                    13, 14, 10, 0};
        Node startingPoint = new Node(x, 4, 15, "", 0);
        Node result = NodeManager.AStar(startingPoint, 25, Method.HAMMING);
        System.out.println(result);
    }

    public void manhattan() {
        int[] x = { 4, 1, 5, 3,
                8, 2, 7, 11,
                12, 6, 9, 15,
                13, 14, 10, 0};
        Node startingPoint = new Node(x, 4, 15, "", 0);
        Node result = NodeManager.AStar(startingPoint, 25, Method.MANHATTAN);
        System.out.println(result);
    }
}
