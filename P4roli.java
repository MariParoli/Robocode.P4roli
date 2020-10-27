package Solutis_robot;

import java.awt.Color;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.HitWallEvent;
import robocode.WinEvent;

/**
 * P4roli - a robot by Mariana Paroli
 * 
 */

public class P4roli extends AdvancedRobot
{
	private final int DISTANCE_GREATER = 200;
	private final int VELOCITY = 10;
	private final int LOW_FIRE = 1;
	private final int MID_FIRE = 2;
	private final int HIGH_FIRE = 3; 
	private int anglePerpendicular = 90;
	private int moveDirection = 1;
	
	/*
	* Método para inicialização do robô
	*/		
	public void run() {
		// Definição das cores (corpo, arma, radar, scan e bala)
		setBodyColor(Color.black);
		setGunColor(Color.white); 
		setRadarColor(Color.black); 
		setScanColor(Color.red); 
		setBulletColor(Color.black); 
		
		//Movimentação independente de cada parte do robô
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForRobotTurn(true);
		turnRadarRightRadians(Double.POSITIVE_INFINITY); //definição da movimentação do radar para direita
	}

	/**
	 * Evento para indicar como e o que fazer ao detectar o inimigo
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Cálculo para saber a localização do robô escaneado, através do movimento paralelo entre os robôs
		double ennemyAbsoluteBearing = e.getBearingRadians() + getHeadingRadians();//valor absoluto da posição do inimigo
		double ennemyLateralVelocity = e.getVelocity() * Math.sin(e.getHeadingRadians() - ennemyAbsoluteBearing); //valor da velocidade lateral do inimigo
		double gunTurnAmt;
		setTurnRadarLeftRadians(getRadarTurnRemainingRadians()); //travamento do radar
		
		if (e.getDistance() > DISTANCE_GREATER) { //se a distância do robô escaneado for maior que 200, ou seja, se estiver bem distante, atire com poder de fogo 2
			gunTurnAmt = robocode.util.Utils.normalRelativeAngle((ennemyAbsoluteBearing - getGunHeadingRadians()) + ennemyLateralVelocity/VELOCITY); //valor necessário para virar a arma
			setTurnGunRightRadians(gunTurnAmt);
			setTurnRight(anglePerpendicular + e.getBearing()); //vire o robô perpendicularmente para o inimigo
			setAhead(e.getDistance() * moveDirection);
			setFire(MID_FIRE);
		} 
		else { //se não, se estiver perto suficiente, atire com poder máximo de fogo 3
			gunTurnAmt = robocode.util.Utils.normalRelativeAngle((ennemyAbsoluteBearing - getGunHeadingRadians()) + ennemyLateralVelocity/VELOCITY); //valor necessário para virar a arma
			setTurnGunRightRadians(gunTurnAmt);
			setTurnLeft(- anglePerpendicular - e.getBearing()); //vire o robô perpendicularmente para o inimigo
			setAhead(e.getDistance() * moveDirection);
			setFire(HIGH_FIRE);
		}
	}

	public void onHitWall(HitWallEvent e) {
		// Ao bater na parede, o robô reverte sua direção
		moveDirection = - moveDirection;
	}	
	
	public void victory() {
		//Celebrando a vitória
		setFire(LOW_FIRE);
		setStop();
		execute();
	}
	
	public void onWin (WinEvent e){
		victory();
	}
}
