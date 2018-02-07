package psyyx3.cw2.MapViewer.View;


import javafx.fxml.FXML;

import javafx.scene.control.Label;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.RowConstraints;
import psyyx3.cw2.MapViewer.Main;
import psyyx3.cw2.MapViewer.Model.IMapViewer;
import psyyx3.cw2.MapViewer.Model.MapViewerModel;
import psyyx3.cw2.MapViewer.Model.ModelHelper;


public class MapViewerController {
	
	IMapViewer model = new MapViewerModel();
	ModelHelper modelHelper = new ModelHelper();
	
	int NUM_COL = 40;
	int NUM_ROW = 40;
	//make true map loaded once
	boolean hasLoaded = false;


	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public MapViewerController() {

	}

	@FXML
	GridPane grid;
	@FXML 
	Label reminder;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	public void initialize() {
		modelHelper.showReminder(reminder, "Hello!!");
		createGridPane();
		model.loadMap(grid, reminder);
	}

	/**
	 * create a 40*40 GridPane
	 */
	public void createGridPane() {

		for (int i = 0; i < NUM_COL; i++) {
			ColumnConstraints colConstraints = new ColumnConstraints();
			colConstraints.setPercentWidth(2.5);
			grid.getColumnConstraints().add(colConstraints);
		}

		for (int i = 0; i < NUM_ROW; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPercentHeight(2.5);
			grid.getRowConstraints().add(rowConstraints);
		}

	}

	/**
	 * when click button load, load map to screen
	*/
	@FXML
	public void onLoadMap() {
		
		model.loadMap(grid, reminder);
		
	}
	
	/**
	 * When users click button set axe, prepare the grid
	 */
	@FXML
	public void onSetAxe() {
		model.setAxe(grid, reminder);
	}
	
	/**
	 * Use for set boat
	 */
	@FXML
	public void onSetBoat() {
		model.setBoat(grid, reminder);
	}
	
	/**
     * Closes the application.
     */
    @FXML
    private void onClose() {
    	model.closeApp();
    }

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */

	public void setMainApp(Main mainApp) {

		// Add observable list data to the table

	}
}