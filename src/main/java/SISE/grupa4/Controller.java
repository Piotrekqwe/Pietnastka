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
    void fun(){
        int[] x = { 4, 1, 5, 3,
                    8, 2, 7, 11,
                    12, 0, 9, 15,
                    13, 6, 14, 10};
        Node target = new Node(x, 4, 1, "", 0);
        Node result = NodeManager.BFS(target, 60);
        System.out.println(result);
    }
}
