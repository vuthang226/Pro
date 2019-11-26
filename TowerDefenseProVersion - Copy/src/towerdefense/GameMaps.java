package towerdefense;

import java.io.BufferedReader;
import java.io.ObjectInputFilter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import towerdefense.gameEntity.gameTile.MachineGunTower;
import towerdefense.gameEntity.gameTile.NormalTower;
import towerdefense.gameEntity.gameTile.SniperTower;
import towerdefense.gameEntity.gameTile.Spawner;
import towerdefense.gameEntity.gameTile.Target;
import towerdefense.gameEntity.gameTile.Tower;

public class GameMaps {
	private boolean first=true;
	public boolean change=false;
	public Position changePos=new Position();
	GameField gameField;
	int towerSize=0;
	public int[][] map;
	public int[][] towerMap;
	public int[][] draw;
	public int[][] water;
	public int[][] decorate;
	public Tower[] tower;
	public Position spawnerMatrixPosition;
	public Position targetMatrixPosition;
	public Target target;
	public Spawner spawner;
	public GameMaps(GameField gameField) {
		this.gameField=gameField;
		target= new Target();
		spawner= new Spawner();
		map = new int[14][24];
		towerMap = new int[14][24];
		tower= new Tower[14*24];
		draw= new int [14][24];
		water= new int [14][24];
		decorate= new int [14][24];
		loadData("res\\Config\\map.txt", map);
		loadData("res\\Config\\tower.txt", towerMap);
		loadData("res\\Config\\draw.txt", draw);
		loadData("res\\Config\\maps.txt" ,water);
		loadData("res\\Config\\decorate.txt",decorate);
		 buildTowerMap();
		findSpecialPosition();
	}
	
	public int getTowerSize() {
		return towerSize;
	}

	public int[][] getMap() {
		return map;
	}

	public int[][] getTowerMap() {
		return towerMap;
	}

	public Tower[] getTower() {
		return tower;
	}

	public Target getTarget() {
		return target;
	}

	public Spawner getSpawner() {
		return spawner;
	}

	public Position getSpawnerMatrixPosition() {
		return spawnerMatrixPosition;
	}

	public Position getTargetMatrixPosition() {
		return targetMatrixPosition;
	}

	private boolean loadData(String filePath, int[][] arr) {
		Path path = Paths.get(filePath);
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String line = null;
			int count = 0;
			while ((line = reader.readLine()) != null) {
				String k[] = line.split(" ");
				if (k.length == 1) {
					arr = new int[Integer.parseInt(k[0])][Integer.parseInt(k[0])];
				} else {
					for (int i = 0; i < k.length; i++) {
						arr[count][i] = Integer.parseInt(k[i]);
					}
					count++;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private void findSpecialPosition() {
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 24; j++) {
				if (this.map[i][j] == 3) {
					spawnerMatrixPosition = new Position(i, j);
					spawner.setPos(j * 64 , i * 64);
				} else if (this.map[i][j] == 2) {
					targetMatrixPosition = new Position(i, j);
					target.setPos(j * 64, i * 64);
				}
			}
		}
	}
	
	public void buildTower(int i, int j) {
		switch (towerMap[i][j]) {
		case -1 : for(int k=0; k<towerSize; k++) {
			if(tower[k]!=null&&tower[k].pos.equals(new Position(j*64, i*64))) {
				tower[k]=null; System.out.println("change sucess"); break;}
			}
		break;
		case 1:
			tower[towerSize]=new NormalTower(new Position(j*64, i*64), gameField); towerSize++;
			break;
		case 2:
			tower[towerSize]=new MachineGunTower(new Position(j*64, i*64), gameField); towerSize++;
			break;
		case 3:
			tower[towerSize]=new SniperTower(new Position(j*64, i*64), gameField); towerSize++;
			break;
		}
	}
	
	public void buildTowerMap() {
		if(first) {
		first=false;
		towerSize=0;
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 24; j++) {
				buildTower(i, j);
			}
		}
		} else {
			if(change) {
				buildTower((int)changePos.x, (int)changePos.y);
				change=false;
			}
		}
	}
	
}
