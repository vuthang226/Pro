package towerdefense.gameEntity.gameTile;

import java.awt.Image;

import javax.swing.ImageIcon;

import towerdefense.GameField;
import towerdefense.Position;

public class SniperTower extends Tower{
	static Image image= new ImageIcon(getPath()).getImage();
	public SniperTower(Position pos, GameField gameField) {
		super(30, 70, 500, 300,130, "res\\GameEntity\\GameTile\\Tower\\tank3.png", "res\\GameEntity\\GameTile\\Tower\\SniperBullet.png", gameField);
		this.pos=pos;
		this.getBullet().setPos(pos.x, pos.y);
		this.getBullet().setStockPosition(new Position(pos.x,pos.y));
	}
	public static String getPath() {
		return "res\\GameEntity\\GameTile\\Tower\\tank3.png";
		
	}
	
	public static int getSpeed() {
		return 50;
	}
	
	public static int getShootSpeed() {
		return 1000;
	}
	
	public static int getDamage() {
		return 30;
	}
	
	public static int getCost() {
		return 130;
	}
	
	public static int getRange() {
		return 300;
	}
	
	public static Image getImage() {
		return image;
	}
}
