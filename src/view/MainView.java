package view;

import java.io.File;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class MainView extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
			primaryStage.setTitle("Project nhóm 2");
			primaryStage.getIcons().add(new Image( new File("src/resource/history.png").toURI().toString()));
			primaryStage.setScene(new Scene(root, 1122, 768));
			primaryStage.show();	
		} catch(Exception e) {
            e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
