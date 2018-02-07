package psyyx3.cw2.MapViewer.Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import psyyx3.cw2.MapViewer.TileMap.Content;

/**
 * this class is used to store some helper function for running mapViewer model
 * @author yehan
 *
 */
public class ModelHelper {

	
	
	
	public void showReminder(Label reminder, String message) {
		reminder.setText(message);
	}
	
	/**
	 * Check the position available.
	 * Items should be put on grass
	 * @param grid
	 * @param colIndex
	 * @param rowIndex
	 * @param reminder
	 * @param message
	 */
	public void checkPos(GridPane grid, int colIndex, int rowIndex, Label reminder, String message){
		Pane pane = new Pane();
		grid.add(pane, colIndex, rowIndex);

		pane.setOnMouseClicked(e -> {
			showReminder(reminder, message);
		});
	}
	
	/**
	 * load a image of axe to the chosen tile
	 * @param grid
	 * @param colIndex
	 * @param rowIndex
	 */
	public void generateAxeOnMap(GridPane grid, int colIndex, int rowIndex) {
		HBox imageField = new HBox();
		imageField.setAlignment(Pos.CENTER);
		grid.add(imageField, colIndex, rowIndex);

		BufferedImage axeBuf = Content.ITEMS[1][1];
		Image axeImage = SwingFXUtils.toFXImage(axeBuf, null);
		imageField.getChildren().add(new ImageView(axeImage));
	}

	public void generateBoatOnMap(GridPane grid, int colIndex, int rowIndex) {
		HBox imageField = new HBox();
		imageField.setAlignment(Pos.CENTER);
		grid.add(imageField, colIndex, rowIndex);

		BufferedImage boatBuf = Content.ITEMS[1][0];
		Image boatImage = SwingFXUtils.toFXImage(boatBuf, null);
		imageField.getChildren().add(new ImageView(boatImage));
	}

	/**
	 * wirte the setting file to Diamond Hunter/bin
	 * @param filePath
	 * @param rowIndex
	 * @param colIndex
	 */
	public void writePositionToFile(String filePath, int rowIndex, int colIndex) {
		try {
			File file = new File(filePath);

			// if file does not exists, then create it
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			PrintStream ps = new PrintStream(filePath);
			ps.println(rowIndex);
			ps.println(colIndex);

			ps.close();

		} catch (IOException x) {
			System.out.println("Error: " + x);
			x.printStackTrace();
		}
	}
}
