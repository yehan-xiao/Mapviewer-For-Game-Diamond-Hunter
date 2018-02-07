package psyyx3.cw2.MapViewer.Model;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import psyyx3.cw2.MapViewer.TileMap.TileMap;

/**
 * This class is a model of Map Viewer App
 * @author yehan
 *
 */
public class MapViewerModel implements IMapViewer {
	int NUM_COL = 40;
	int NUM_ROW = 40;

	// load map once
	boolean hasLoaded = false;

	// save map in this array
	int[][] map;

	// save the temporary position
	int[] temp_axe = new int[2];
	int[] temp_boat = new int[2];

	ModelHelper modelHelper = new ModelHelper();
	TileMap tileMap = new TileMap(16);

	/*
	 * @see psyyx3.cw2.MapViewer.Model.IMapViewer#loadMap(javafx.scene.layout.
	 * GridPane, javafx.scene.control.Label)
	 */
	@Override
	public void loadMap(GridPane grid, Label reminder) {
		if (hasLoaded == false) {
			hasLoaded = true;

			tileMap.loadTiles("/Tilesets/testtileset.gif");
			tileMap.loadMap("/Maps/testmap.map");
			map = tileMap.getMap();

			for (int row = 0; row < NUM_ROW; row++) {
				for (int col = 0; col < NUM_COL; col++) {

					tileMap.generateOneTileByMap(grid, row, col);
				}

			}
		}
		// tell user that load map successful
		modelHelper.showReminder(reminder, "Load Map Successfully!\n\nPlease Set Axe/Boat\nJust Click the button!");
	}

	/*
	 * @see psyyx3.cw2.MapViewer.Model.IMapViewer#setAxe(javafx.scene.layout.
	 * GridPane, javafx.scene.control.Label)
	 */
	@Override
	public void setAxe(GridPane grid, Label reminder) {
		//path for jar file: åString filePath = "../../DiamondHunter/bin/SettingFile/axe.txt";
		String filePath = "../DiamondHunter/bin/SettingFile/axe.txt";

		modelHelper.showReminder(reminder, "Please click a position to \nput axe!");
		for (int i = 0; i < NUM_COL; i++) {
			for (int j = 0; j < NUM_ROW; j++) {

				if (map[j][i] == 1 || map[j][i] == 2 || map[j][i] == 3) {

					capturePutAxe(i, j, filePath, grid, reminder);

				} else {
					modelHelper.checkPos(grid, i, j, reminder, "Please set item on grass");
				}
			}
		}
	}

	/*
	 * @see psyyx3.cw2.MapViewer.Model.IMapViewer#setBoat(javafx.scene.layout.
	 * GridPane, javafx.scene.control.Label)
	 */
	@Override
	public void setBoat(GridPane grid, Label reminder) {
		//path for jar file: String filePath = "../../DiamondHunter/bin/SettingFile/boat.txt";
		String filePath = "../DiamondHunter/bin/SettingFile/boat.txt";

		modelHelper.showReminder(reminder, "Please click a position to \nput boat!");
		for (int i = 0; i < NUM_COL; i++) {
			for (int j = 0; j < NUM_ROW; j++) {

				if (map[j][i] == 1 || map[j][i] == 2 || map[j][i] == 3) {

					capturePutBoat(i, j, filePath, grid, reminder);

				} else {
					modelHelper.checkPos(grid, i, j, reminder, "Please set item on grass");
				}
			}
		}
	}

	/*
	 * @see psyyx3.cw2.MapViewer.Model.IMapViewer#closeApp()
	 */
	@Override
	public void closeApp() {
		System.exit(0);
	}

	// =====================================================================================================
	
	
	/**
	 * Capture mouse click to identify the position of axe
	 * @param colIndex
	 * @param rowIndex
	 * @param filePath
	 * @param grid
	 * @param reminder
	 */
	public void capturePutAxe(int colIndex, int rowIndex, String filePath, GridPane grid, Label reminder) {

		Pane pane = new Pane();
		grid.add(pane, colIndex, rowIndex);

		pane.setOnMouseClicked(e -> {
			
			
			// keep the same position expression way with game
			modelHelper.writePositionToFile(filePath, rowIndex, colIndex);

			if (temp_axe[0] != 0 && temp_axe[1] != 0) {
				clearLastAxe(filePath, grid, reminder);
			}

			modelHelper.generateAxeOnMap(grid, colIndex, rowIndex);

			temp_axe[0] = rowIndex;
			temp_axe[1] = colIndex;
			modelHelper.showReminder(reminder, "Set Axe Successfully!");

		});

	}

	public void capturePutBoat(int colIndex, int rowIndex, String filePath, GridPane grid, Label reminder) {

		Pane pane = new Pane();
		grid.add(pane, colIndex, rowIndex);

		pane.setOnMouseClicked(e -> {

			modelHelper.writePositionToFile(filePath, rowIndex, colIndex);

			if (temp_boat[0] != 0 && temp_boat[1] != 0) {
				clearLastBoat(filePath, grid, reminder);
			}
			modelHelper.generateBoatOnMap(grid, colIndex, rowIndex);
			temp_boat[0] = rowIndex;
			temp_boat[1] = colIndex;

			modelHelper.showReminder(reminder, "Set Boat Successfully!");

		});

	}

	/**
	 * Clear the last position of axe. 
	 * Show only one newest axe on map
	 * @param filePath
	 * @param grid
	 * @param reminder
	 */
	public void clearLastAxe(String filePath, GridPane grid, Label reminder) {

		tileMap.generateOneTileByMap(grid, temp_axe[0], temp_axe[1]);

		// when boat and axe in the same position
		// we need keep boat there only to clear axe
		if (temp_boat[1] == temp_axe[1] && temp_boat[0] == temp_axe[0]) {
			modelHelper.generateBoatOnMap(grid, temp_boat[1], temp_boat[0]);
		}

		capturePutAxe(temp_axe[1], temp_axe[0], filePath, grid, reminder);

	}

	public void clearLastBoat(String filePath, GridPane grid, Label reminder) {

		tileMap.generateOneTileByMap(grid, temp_boat[0], temp_boat[1]);

		if (temp_boat[1] == temp_axe[1] && temp_boat[0] == temp_axe[0]) {

			modelHelper.generateAxeOnMap(grid, temp_axe[1], temp_axe[0]);
		}
		capturePutBoat(temp_boat[1], temp_boat[0], filePath, grid, reminder);

	}
}
