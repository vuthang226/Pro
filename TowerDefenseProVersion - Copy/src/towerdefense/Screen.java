//show update game
//GameField
package towerdefense;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


import towerdefense.gameEntity.enemy.Enemy;
import towerdefense.gameEntity.gameTile.*;

public class Screen extends JPanel implements Runnable {


	private static final long serialVersionUID = 1L;
	boolean saveGame=false;
	private boolean firstTime=true;
	private boolean started=false;
	boolean select =false;
	private int fps = 0;
	public boolean isRunning = false;
	public int status=0;
	Frame frame;
	private User user;
	private Level level;
	private GameField gameField;
	private Thread thread = new Thread(this);
	private int onHand = 0;
	private int mousePosX = 0;
	private int mousePosY = 0;
	private int transparentColor =0xffffffff;


	Color warning = new Color(255, 16, 0, 90);
	Color exepting = new Color(64, 64, 64, 64);
	Image winStage= new ImageIcon("res\\Background\\WinStage.png").getImage();
	Image lose= new ImageIcon("res\\Background\\Lose.png").getImage();
	Image levelSelect= new ImageIcon("res\\Background\\Level.png").getImage();
	Image pauseMenu= new ImageIcon("res\\Background\\PauseMenu.png").getImage();
	Image SpeedUp= new ImageIcon("res\\Background\\SpeedUp.png").getImage();
	Image normalSpeed= new ImageIcon("res\\Background\\normalSpeed.png").getImage();
	Image pauseIcon= new ImageIcon("res\\Background\\stop.png").getImage();
	Image background= new ImageIcon("res\\Background\\BackGround.jpg").getImage();
	Image mainMenu= new ImageIcon("res\\Background\\Menu.png").getImage();
	Image panel= new ImageIcon("res\\Background\\button3.png").getImage();
	Image Play= new ImageIcon("res\\Background\\Play.png").getImage();

	



	public Screen(Frame frame) {
		this.frame = frame;
		MouseHandler mouseHandler = new MouseHandler(this);
		KeyHandler keyHandler=new KeyHandler(this);
		frame.addKeyListener(keyHandler);
		addKeyListener(keyHandler);
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
		setVisible(true);
		thread.start();
	}




	private void draw(Graphics g, int x, int y, int range, String texturePath, Color color) {
		g.drawImage(new ImageIcon(texturePath).getImage(), x - 20, y - 20, null);
		g.setColor(color);
		g.fillOval(x - range, y - range, range*2, range*2);
	}





	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setColor(Color.red);
		
		switch (status) {
		case 0:
			Start(g);
			break;
		case 1:
			inGame(g);
			break;
		case 2:
			WinStage(g);
			break;
		case 3:
			Lose(g);
			break;
		case 4:
			Pause(g);
			break;
		case 5:
			Setting(g);
			break;
		default:
			break;
		}
	}

	private void Start(Graphics g) {
		if(level==null) status =5;
		else {
			if(firstTime) {
				loadGame();
				firstTime = false;
			}
			g.setColor(Color.yellow);
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
			g.drawImage(mainMenu, 0, 0, null);
			g.setColor(Color.red);
		}
	}


	private void Pause(Graphics g) {
		g.drawImage(pauseMenu, 0, 0, null);
	}




	private void Setting(Graphics g) {
		g.drawImage(levelSelect, 0, 0, null);
	}







	private void WinStage(Graphics g) {
		g.drawImage(winStage, 0, 0, null);
	}





	private void Lose(Graphics g) {
		g.drawImage(lose, 0, 0, null);
	}



		
	private void inGame(Graphics g) {
		if (status == 1) {
			g.drawImage(background, 0, 0, null);
			g.setColor(Color.BLUE);
			g.drawRect(0, 0, 40, 40);
			g.setColor(Color.BLUE);


			for (int i = 0; i < 24; i++) {
				for (int j = 0; j < 14; j++) {
					g.drawImage(Grass.getImage(), i * 64, j * 64, null);
					switch (gameField.gameMaps.water[j][i]) {
						case 10:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_10.png").getImage(), i * 64, j * 64, null);
							break;
						case 11:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_11.png").getImage(), i * 64, j * 64, null);
							break;
						case 12:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_12.png").getImage(), i * 64, j * 64, null);
							break;
						case 26:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_26.png").getImage(), i * 64, j * 64, null);
							break;
						case 27:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_27.png").getImage(), i * 64, j * 64, null);
							break;
						case 28:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_28.png").getImage(), i * 64, j * 64, null);
							break;
						case 42:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_42.png").getImage(), i * 64, j * 64, null);
							break;
						case 43:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_43.png").getImage(), i * 64, j * 64, null);
							break;
						case 44:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_44.png").getImage(), i * 64, j * 64, null);
							break;
						case 59:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_59.png").getImage(), i * 64, j * 64, null);
							break;
						case 58:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_58.png").getImage(), i * 64, j * 64, null);
							break;
						case 74:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_74.png").getImage(), i * 64, j * 64, null);
							break;
						case 75:
							g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_75.png").getImage(), i * 64, j * 64, null);
							break;
					}
						switch (gameField.gameMaps.draw[j][i]) {
							case 3:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_03.png").getImage(), i * 64, j * 64, null);
								break;
							case 6:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_06.png").getImage(), i * 64, j * 64, null);
								break;
							case 7:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_07.png").getImage(), i * 64, j * 64, null);
								break;
							case 8:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_08.png").getImage(), i * 64, j * 64, null);
								break;
							case 9:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_09.png").getImage(), i * 64, j * 64, null);
								break;
							case 22:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_22.png").getImage(), i * 64, j * 64, null);
								break;
							case 23:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_23.png").getImage(), i * 64, j * 64, null);
								break;
							case 24:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_24.png").getImage(), i * 64, j * 64, null);
								break;
							case 25:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_25.png").getImage(), i * 64, j * 64, null);
								break;
							case 34:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_34.png").getImage(), i * 64, j * 64, null);
								break;
							case 35:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_35.png").getImage(), i * 64, j * 64, null);
								break;
							case 36:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_36.png").getImage(), i * 64, j * 64, null);
								break;
							case 37:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_37.png").getImage(), i * 64, j * 64, null);
								break;
							case 38:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_38.png").getImage(), i * 64, j * 64, null);
								break;
							case 39:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_39.png").getImage(), i * 64, j * 64, null);
								break;
							case 40:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_40.png").getImage(), i * 64, j * 64, null);
								break;
							case 41:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_41.png").getImage(), i * 64, j * 64, null);
								break;
							case 52:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_52.png").getImage(), i * 64, j * 64, null);
								break;
							case 53:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_53.png").getImage(), i * 64, j * 64, null);
								break;
							case 54:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_54.png").getImage(), i * 64, j * 64, null);
								break;
							case 55:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_55.png").getImage(), i * 64, j * 64, null);
								break;
							case 56:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_56.png").getImage(), i * 64, j * 64, null);
								break;
							case 57:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_57.png").getImage(), i * 64, j * 64, null);
								break;
							case 69:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_69.png").getImage(), i * 64, j * 64, null);
								break;
							case 84:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_84.png").getImage(), i * 64, j * 64, null);
								break;
							case 86:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\tile_86.png").getImage(), i * 64, j * 64, null);
								break;


						}
						switch (gameField.gameMaps.decorate[j][i]){
							case 30:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_30.png").getImage(), i * 64, j * 64, null);
								break;
							case 46:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_46.png").getImage(), i * 64, j * 64, null);
								break;
							case 47:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_47.png").getImage(), i * 64, j * 64, null);
								break;
							case 48:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_48.png").getImage(), i * 64, j * 64, null);
								break;
							case 49:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_49.png").getImage(), i * 64, j * 64, null);
								break;
							case 50:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_50.png").getImage(), i * 64, j * 64, null);
								break;
							case 51:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_51.png").getImage(), i * 64, j * 64, null);
								break;
							case 62:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_62.png").getImage(), i * 64, j * 64, null);
								break;
							case 65:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_65.png").getImage(), i * 64, j * 64, null);
								break;
							case 70:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_70.png").getImage(), i * 64, j * 64, null);
								break;
							case 71:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_71.png").getImage(), i * 64, j * 64, null);
								break;
							case 72:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_72.png").getImage(), i * 64, j * 64, null);
								break;
							case 76:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_76.png").getImage(), i * 64, j * 64, null);
								break;
							case 87:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_87.png").getImage(), i * 64, j * 64, null);
								break;
							case 88:
								g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\tile_88.png").getImage(), i * 64, j * 64, null);
								break;
						}

					}
				g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\button.png").getImage(), 130, 738, null);
				g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\Dec.png").getImage(), 24, 700, null);
				g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\button1.png").getImage(), 160, 728, null);
				g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\button1.png").getImage(), 260, 728, null);
				g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\button1.png").getImage(), 360, 728, null);
				g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\button2.png").getImage(), 480, 718, null);
				g.drawImage(new ImageIcon("res\\GameEntity\\GameTile\\Decorate\\button2.png").getImage(), 600, 718, null);
				g.drawImage(NormalTower.getImage(), 168, 736, null);
				g.drawImage(MachineGunTower.getImage(), 268, 736, null);
				g.drawImage(SniperTower.getImage(),368,736,null);
				}


			gameField.gameMaps.getSpawner().draw(g);
			for (Enemy enemy:gameField.getEnemiesList()) {
				if(enemy.active&&enemy.alive) {
					enemy.draw(g);
				}
			}


			gameField.gameMaps.getTarget().draw(g);
			for(int i=0; i<gameField.gameMaps.getTowerSize(); i++) {
				if(gameField.gameMaps.tower[i]!=null)
				gameField.gameMaps.tower[i].draw(g);
			}


			g.setColor(Color.BLUE);
				if(Clock.speedUp!=4) {
				g.drawImage(SpeedUp, 480, 718, null);
			} else g.drawImage(normalSpeed, 480, 718, null);



			g.setColor(Color.BLACK);
			g.setFont(new Font("a", Font.ITALIC,15));
			g.drawImage(panel,50, 620,null);
			g.drawImage(panel,50, 658,null);
			g.drawString("Health: " + user.player.health, 70, 641);
			g.drawString("Coin: " + user.player.coin, 70, 676);




			if (onHand != 0) {
				switch (onHand) {
				case 1:
					if (isAble(mousePosX, mousePosY))
						draw(g, mousePosX, mousePosY, NormalTower.getRange(), NormalTower.getPath(), exepting);
					else
						draw(g, mousePosX, mousePosY, NormalTower.getRange(), NormalTower.getPath(), warning);
					break;
				case 2:
					if (isAble(mousePosX, mousePosY))
						draw(g, mousePosX, mousePosY, MachineGunTower.getRange(), MachineGunTower.getPath(), exepting);
					else
						draw(g, mousePosX, mousePosY, MachineGunTower.getRange(), MachineGunTower.getPath(), warning);
					break;
				case 3:
					if (isAble(mousePosX, mousePosY))
						draw(g, mousePosX, mousePosY, SniperTower.getRange(), SniperTower.getPath(), exepting);
					else
						draw(g, mousePosX, mousePosY, SniperTower.getRange(), SniperTower.getPath(), warning);
					break;
				}
			}
			g.drawImage(pauseIcon, 600, 718, null);

		}
	}



	

	public void loadGame() {
		isRunning = true;
		user = new User(this);
		user.creatPlayer();
		gameField = new GameField(level, user.player);
	}











	public void startGame() {
			if(!started) {
				status=1;
				started=true;
			}
	}
	










	public void newGame() {
		started=false;
		loadGame();
	}
	
	






	public void loadSaveGame() {
		if(saveGame) {
			status=1;
		} else System.out.println("no save game found");
	}










	public void update() {
		if(status==1) {
			if(Clock.getTotalTime()-gameField.getTimeLastWave()>= level.newWaveSpeed*Clock.deltaDelay()) {
				if(Clock.getTotalTime()-gameField.getTimeLastSpawn()>=level.spawnSpeed*Clock.deltaDelay()) gameField.setSpawning(true);
				else gameField.setSpawning(false);
			} else gameField.setSpawning(false);
			gameField.spawn();
			for(Enemy e:gameField.getEnemiesList()) {
				e.run();
			}
			for(int i=0; i<gameField.gameMaps.getTowerSize(); i++) {
				if(gameField.gameMaps.tower[i]!=null)gameField.gameMaps.tower[i].checkEnemy();
			}
			if(gameField.isWinStage()) status=2;
			if(user.player.health<=0) status=3;
		}
	}










	public void run() {
		System.out.print("RUNNING!\n");
		long beginTimes = System.currentTimeMillis();
		int frames = 0;
		while (isRunning || level==null) {
			if(select==true) Clock.update();
			update();
			repaint();
			frames++;
			if (System.currentTimeMillis() - beginTimes >= 1000) {
				fps = frames;
				frames = 0;
				beginTimes = System.currentTimeMillis();
			}
			  try { Thread.sleep(2); } catch (InterruptedException e) {
			  e.printStackTrace(); }
		}
		System.out.println("STOP");
		System.exit(0);
	}







	public void destroyTower() {
		if(status ==1) {
			if(gameField.gameMaps.towerMap[(mousePosY)/64][(mousePosX)/64]>0){
				gameField.gameMaps.towerMap[(mousePosY)/64][(mousePosX)/64]=-1;
				gameField.gameMaps.map[(mousePosY)/64][(mousePosX)/64]=-1;
				gameField.gameMaps.changePos.setPosition((mousePosY ) / 64,(mousePosX ) / 64);
				gameField.gameMaps.change=true;
				select=true;
			}
		}
	}








	public boolean isAble(int x, int y) {
		int indexX = (x ) / 64;
		int indexY = (y ) / 64;
		if (x > 0 && x < 1000 && y > 0 && y < 600) {
			if (gameField.gameMaps.towerMap[indexY][indexX] > 0) {
				return false;
			}
			if (gameField.gameMaps.map[indexY][indexX] != 0) {
				if(gameField.gameMaps.map[indexY][indexX] == -1) return true;
				return false;
			}
		} else
			return false;
		return true;
	}







	public void placeTower(int x, int y, int onHand) {
		if (isAble(x, y)&&status==1) {
			gameField.gameMaps.towerMap[(int) (y ) / 64][(int) (x ) / 64] = onHand;
			switch (onHand) {
			case 1:
				user.player.coin -= NormalTower.getCost();
				System.out.println("YOU BOUGHT NORMAL TOWER, YOUR COIN: " + user.player.coin);
				break;
			case 2:
				user.player.coin -= MachineGunTower.getCost();
				System.out.println("YOU BOUGHT MACHINE GUN TOWER, YOUR COIN: " + user.player.coin);
				break;
			case 3:
				user.player.coin -= SniperTower.getCost();
				System.out.println("YOU BOUGHT SNIPER TOWER, YOUR COIN: " + user.player.coin);
				break;
			}
			
			gameField.gameMaps.change=true;
			gameField.gameMaps.changePos.setPosition((y ) / 64,(x ) / 64);
		}
	}





	public class MouseEffect {
		boolean mouseDown = false;


		public void moved(MouseEvent e) {
			mousePosX = e.getX();
			mousePosY = e.getY();
		}

		public void Effect(MouseEvent e) {
			if (status == 1)
			{
				if (mouseDown && onHand == 0) {

					if (e.getY() >= 728 & e.getY() <= 808) {
						if (e.getX() >= 160 && e.getX() <= 240) {
							if (user.player.coin >= NormalTower.getCost()) {
								onHand = 1;
							} else
								System.out.println("NOT ENOUGH MONEY!");
						}
						if (e.getX() >= 260 && e.getX() <= 340) {
							if (user.player.coin >= MachineGunTower.getCost()) {
								onHand = 2;
							} else
								System.out.println("NOT ENOUGH MONEY!");
						}
						if (e.getX() >= 360 && e.getX() <= 440) {
							if (user.player.coin >= SniperTower.getCost()) {
								onHand = 3;
							} else
								System.out.println("NOT ENOUGH MONEY!");
						}

					}
				}
			}
		}


		public void down(MouseEvent e) {
			mouseDown = true;
			if (status == 1 && onHand != 0) {
				placeTower(e.getX(), e.getY(), onHand);
				onHand = 0;
				select=true;
			}
			if(status==1&&mousePosY>=718&&mousePosY<=818) {
				if(mousePosX>=480&& mousePosX<=580) {
					Clock.speedUp();
					select=true;
				}
				if(mousePosX>=600&&mousePosX<=700) {
					status=4;
					select=true;
				}
			}
			Effect(e);
		}

		public void choose() {
			switch (status) {
				case 0:
					if(mousePosX>=553&&mousePosX<=958&&mousePosY>=360&&mousePosY<=428){
					select=true;
					Clock.speedUp=1;
					if(!saveGame) {
						started=false;
						startGame();}
					else {
						newGame();
						startGame();
					}
				}else if(mousePosX>=538&&mousePosX<=954&&mousePosY>=455&&mousePosY<=515){
					select=true;
					loadSaveGame();
					break;
				}else if(mousePosX>=636&&mousePosX<=883&&mousePosY>=548&&mousePosY<=615){

				}else if(mousePosX>=656&&mousePosX<=831&&mousePosY>=638&&mousePosY<=710){
					isRunning=false;
				}
					break;
				case 1: break;
				case 2:
					if(mousePosX>=596&&mousePosX<=941&&mousePosY>=461&&mousePosY<=521){
						select=true;
						gameField.nextStage();
						Clock.speedUp=1;
						status=1;
					}else if(mousePosX>=564&&mousePosX<=968&&mousePosY>=563&&mousePosY<=633){
						select=true;
						status=0;
						saveGame = true;
					}
					break;
				case 3:
					if(mousePosX>=587&&mousePosX<=923&&mousePosY>=503&&mousePosY<=549){
						select=true;
						newGame();
						status=0;
					}else if(mousePosX>=559&&mousePosX<=941&&mousePosY>=602&&mousePosY<=655){
						select=true;
						status=0;
						firstTime=true;
					}break;
				case 4:
					if(mousePosX>=591&&mousePosX<=913&&mousePosY>=472&&mousePosY<=527) {
						select = true;
						status = 1;
					}else if (mousePosX>=581&&mousePosX<=947&&mousePosY>=577&&mousePosY<=642){
						select=true;
						status=0;
						saveGame=true;
					}break;
				case 5:
					if(mousePosX>=677&&mousePosX<=897&&mousePosY>=476&&mousePosY<=536){
						select=true;
						level= new Level(1);
						isRunning=true;
						status=0;
					}else if(mousePosX>=653&&mousePosX<=915&&mousePosY>=570&&mousePosY<=639){
						select=true;
						level= new Level(2);
						isRunning=true;
						status=0;
					} else if (mousePosX>=689&&mousePosX<=882&&mousePosY>=664&&mousePosY<=724) {
						select=true;
						level= new Level(3);
						isRunning=true;
						status=0;
					}

			}
		}


	}

}
