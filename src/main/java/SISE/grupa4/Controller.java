package SISE.grupa4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField filePathField;
    public TextField resultFilePathField;
    public TextField metadataFilePathField;
    public Text canBeSolvedDisplay;

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

    public void check() {
        Node node = Utils.getData(filePathField.getText());
        if(PuzzleService.check(node)) canBeSolvedDisplay.setText(filePathField.getText() + " can be solved");
        else canBeSolvedDisplay.setText(filePathField.getText() + " cannot be solved");
    }
}
