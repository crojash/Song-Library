package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("/ui/Window.fxml"));
			//root.getStylesheets().add("/application/test.css");
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			//primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("Song Library");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
