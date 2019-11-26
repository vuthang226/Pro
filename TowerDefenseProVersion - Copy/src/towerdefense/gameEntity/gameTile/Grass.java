package towerdefense.gameEntity.gameTile;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Grass extends GameTile {
	public Grass() {
		this.texturePath = "res\\GameEntity\\GameTile\\tile_73.png";
	}

	public static String getPath() {
		return "res\\GameEntity\\GameTile\\tile_73.png";
	}
	static Image image = new ImageIcon(getPath()).getImage();
	public static Image getImage() {
		return image;
	}
}
