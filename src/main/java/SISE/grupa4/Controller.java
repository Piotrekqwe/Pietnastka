package SISE.grupa4;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField filePathField;
    public TextField resultFilePathField;
    public TextField metadataFilePathField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void bfs() {
        Node target = Utils.getData(filePathField.getText());

        System.out.println(PuzzleService.check(target));

        Metadata result = MetadataNodeManager.BFS(target, 25);
        result.node.reversePath();
        System.out.println(result);
        System.out.println(result.node);

        Utils.saveResult(result, resultFilePathField.getText(), metadataFilePathField.getText());
    }

    public void dfs() {
        Node target = Utils.getData(filePathField.getText());

        System.out.println(PuzzleService.check(target));

        Metadata result = MetadataNodeManager.DFS(target, 25);
        result.node.reversePath();
        System.out.println(result);
        System.out.println(result.node);

        Utils.saveResult(result, resultFilePathField.getText(), metadataFilePathField.getText());
    }

    public void hamming() {
        Node startingPoint = Utils.getData(filePathField.getText());

        System.out.println(PuzzleService.check(startingPoint));

        Metadata result = MetadataNodeManager.AStar(startingPoint, 25, Method.HAMMING);
        System.out.println(result);
        System.out.println(result.node);

        Utils.saveResult(result, resultFilePathField.getText(), metadataFilePathField.getText());
    }

    public void manhattan() {
        Node startingPoint = Utils.getData(filePathField.getText());

        System.out.println(PuzzleService.check(startingPoint));

        Metadata result = MetadataNodeManager.AStar(startingPoint, 25, Method.MANHATTAN);
        System.out.println(result);
        System.out.println(result.node);

        Utils.saveResult(result, resultFilePathField.getText(), metadataFilePathField.getText());
    }
}
