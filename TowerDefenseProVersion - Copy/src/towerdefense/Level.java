package towerdefense;

public class Level {                                      //PHÂN TỪNG LEVEL VS CẤU TRÚC CỦA TỪNG LV
	 int level;
	 int spawnSpeed; //Tốc đọ sinh quái
	 int enemiesPerWaveUp; //Số quái mỗi lượt
	 int numberWaveUp; //Số lượt
	 int newWaveSpeed; //Tốc độ mỗi lượt
	 int startEnemies; //Số quái chưa tính boss


	public Level(int l) {
		switch (l) {
		case 1:
			startEnemies = 5;
			enemiesPerWaveUp = 3;
			spawnSpeed = 600;
			numberWaveUp = 1;
			newWaveSpeed =3000;
			break;
		case 2:
			startEnemies = 7;
			enemiesPerWaveUp = 4;
			spawnSpeed = 500;
			numberWaveUp = 2;
			newWaveSpeed = 7500;
			break;
		case 3:
			startEnemies = 9;
			enemiesPerWaveUp = 5;
			spawnSpeed = 400;
			numberWaveUp = 2;
			newWaveSpeed = 7500;
			break;
		}
	}

}
