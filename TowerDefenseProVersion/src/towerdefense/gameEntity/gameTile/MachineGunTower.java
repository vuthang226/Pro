package towerdefense.gameEntity.gameTile;

import java.awt.Image;

import javax.swing.ImageIcon;

import towerdefense.GameField;
import towerdefense.Position;

public class MachineGunTower extends Tower{
	static Image image= new ImageIcon(getPath()).getImage();
	public MachineGunTower(Position pos, GameField gameField) {
		super(20,70, 500, 300, 100, "res\\GameEntity\\GameTile\\Tower\\tank2.png","res\\GameEntity\\GameTile\\Tower\\MachineBullet.png", gameField);
		this.pos=pos;
		this.getBullet().setPos(pos.x, pos.y);
		this.getBullet().setStockPosition(new Position(pos.x,pos.y));
	}
	public static String getPath() {
		return "res\\GameEntity\\GameTile\\Tower\\tank2.png";
	}
	
	public static int getSpeed() {
		return 50;
	}
	
	public static int getShootSpeed() {
		return 200;
	}
	
	public static int getDamage() {
		return 20;
	}
	
	public static int getCost() {
		return 100;
	}
	
	public static int getRange() {
		return 300;
	}
	
	public static Image getImage() {
		return image;
	}

}
