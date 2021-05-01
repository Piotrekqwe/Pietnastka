package SISE.grupa4;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField filePathField;
    public TextField resultFilePathField;
    public TextField metadataFilePathField;
    public TextField searchOrderField;
    public Text canBeSolvedDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void bfs() {
        Node target = Utils.getData(filePathField.getText());

        System.out.println(PuzzleService.check(target));

        Direction[] directions;
        String order = searchOrderField.getText();
        if (order.length() == 4 && order.contains("L") && order.contains("R") && order.contains("U") && order.contains("D")) {
            directions = PuzzleService.getDirections(order);
        } else {
            directions = new Direction[]{Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN};
        }

        Metadata result = MetadataNodeManager.BFS(target, PuzzleService.MAX_DEPTH, directions);
        result.node.reversePath();
        System.out.println(result);
        System.out.println(result.node);

        Utils.saveResult(result, resultFilePathField.getText(), metadataFilePathField.getText());
    }

    public void dfs() {
        Node target = Utils.getData(filePathField.getText());

        System.out.println(PuzzleService.check(target));

        Direction[] directions;
        String order = searchOrderField.getText();
        if (order.length() == 4 && order.contains("L") && order.contains("R") && order.contains("U") && order.contains("D")) {
            directions = PuzzleService.getDirections(order);
        } else {
            directions = new Direction[]{Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN};
        }

        Metadata result = MetadataNodeManager.DFS(target, PuzzleService.MAX_DEPTH, directions);
        result.node.reversePath();
        System.out.println(result);
        System.out.println(result.node);

        Utils.saveResult(result, resultFilePathField.getText(), metadataFilePathField.getText());
    }

    public void hamming() {
        Node startingPoint = Utils.getData(filePathField.getText());

        System.out.println(PuzzleService.check(startingPoint));

        Direction[] directions;
        String order = searchOrderField.getText();
        if (order.length() == 4 && order.contains("L") && order.contains("R") && order.contains("U") && order.contains("D")) {
            directions = PuzzleService.getDirections(order);
        } else {
            directions = new Direction[]{Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN};
        }

        Metadata result = MetadataNodeManager.AStar(startingPoint, PuzzleService.MAX_DEPTH, Method.HAMMING, directions);
        System.out.println(result);
        System.out.println(result.node);

        Utils.saveResult(result, resultFilePathField.getText(), metadataFilePathField.getText());
    }

    public void manhattan() {
        Node startingPoint = Utils.getData(filePathField.getText());

        System.out.println(PuzzleService.check(startingPoint));

        Direction[] directions;
        String order = searchOrderField.getText();
        if (order.length() == 4 && order.contains("L") && order.contains("R") && order.contains("U") && order.contains("D")) {
            directions = PuzzleService.getDirections(order);
        } else {
            directions = new Direction[]{Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN};
        }

        Metadata result = MetadataNodeManager.AStar(startingPoint, PuzzleService.MAX_DEPTH, Method.MANHATTAN, directions);
        System.out.println(result);
        System.out.println(result.node);

        Utils.saveResult(result, resultFilePathField.getText(), metadataFilePathField.getText());
    }

    public void check() {
        Node node = Utils.getData(filePathField.getText());
        if (PuzzleService.check(node)) canBeSolvedDisplay.setText(filePathField.getText() + " can be solved");
        else canBeSolvedDisplay.setText(filePathField.getText() + " cannot be solved");
    }
}
