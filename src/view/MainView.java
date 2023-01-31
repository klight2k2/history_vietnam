package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
			primaryStage.setTitle("Hello");
			primaryStage.setScene(new Scene(root, 1024, 768));
			primaryStage.show();	
		} catch(Exception e) {
            e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
