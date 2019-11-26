package towerdefense.gameEntity.gameTile;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Land extends GameTile {
    public Land() {
        this.texturePath = "res\\GameEntity\\GameTile\\tile_02.png";
    }

    public static String getPath() {
        return "res\\GameEntity\\GameTile\\tile_02.png";
    }
    static Image image = new ImageIcon(getPath()).getImage();
    public static Image getImage() {
        return image;
    }
}
