package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import view.ClassicBoardController;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Model model = new Model();
        ClassicBoardController controller = new ClassicBoardController(model);
        model.addObserver(controller);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml_sheets/ClassicBoard.fxml"));

        loader.setController(controller);

        Parent root = loader.load();


        primaryStage.setTitle("Classic Chess");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
