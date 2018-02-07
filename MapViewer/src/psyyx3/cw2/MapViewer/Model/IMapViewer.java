package psyyx3.cw2.MapViewer.Model;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public interface IMapViewer {

	/**
	 * This method is used to load map file and show the map on the screen
	 * 
	 * @param GridPane
	 *            grid the background grind pane
	 * @param Label
	 *            reminder reminder message
	 */
	void loadMap(GridPane grid, Label reminder);

	/**
	 * This method is used to set the position of axe
	 */
	void setAxe(GridPane grid, Label reminder);

	/**
	 * This method is used to set the position of axe
	 */
	void setBoat(GridPane grid, Label reminder);

	/**
	 * This method is used to close this app
	 * 
	 */
	void closeApp();

}