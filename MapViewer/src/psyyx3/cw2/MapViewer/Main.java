package psyyx3.cw2.MapViewer;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import psyyx3.cw2.MapViewer.View.MapViewerController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	private Stage primaryStage;
	private BorderPane root;

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("MapViewer");

			showMapViewer();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show the user interface of Map Viewer Application.
	 */
	public void showMapViewer() {
		try {
		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("View/MapViewer.fxml"));
			root = (BorderPane) loader.load();
			Scene scene = new Scene(root);

			setUserAgentStylesheet(STYLESHEET_MODENA);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			MapViewerController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
