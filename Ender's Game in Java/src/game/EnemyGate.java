package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EnemyGate extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	boolean two_Player = true;
	// THE TWO PLAYER SWITCH. TRUE FOR TWO PLAYERS. FALSE FOR 1 PLAYER
	boolean doctor_rule = true;
	int doc_score = 2;

	int blueDoc = 1;
	int redDoc = 1;
	// Pause
	boolean pause = false;
	// Positive in favor of Red, Negative in favor of Blue. The most it can be
	// is 5 or -5
	int cheat = 0;

	Color fr = new Color(0, 0, 0); // (0, 0, 0)
	Color ufr = new Color(255, 255, 255); // (255, 255, 255)

	Color redFocused = new Color(255, 180, 0); // (255, 180, 0) or (100, 255,
												// 100)
	Color redNorm = new Color(255, 0, 0); // (255, 0, 0) or (0, 175, 25)
	Color frozenNumberRed = fr;
	Color unfrozenNumberRed = ufr;

	Color blueFocused = new Color(0, 255, 255); // (0, 255, 255)
	Color blueNorm = new Color(0, 0, 255); // (0, 0, 255)
	Color frozenNumberBlue = fr;
	Color unfrozenNumberBlue = ufr;

	Color bulletColor = new Color(0, 0, 0); // (0, 0, 0)
	Color blockColor = new Color(180, 180, 180); // (180, 180, 180)
	Color borderColor = new Color(0, 0, 0); // (0, 0, 0)
	Color backgroundColor = new Color(255, 255, 255); // (255, 255, 255)
	// Speeds, Maximums, ActionListeners
	double bs = 0.3;
	final int size = 20;
	int max = size;
	That movement = new That();
	Those shooting = new Those();
	Changed switching = new Changed();
	// This stuff is the x AND y coordinates for the random blocks.
	int blockNumb = 10;
	int[][] block = new int[blockNumb][2];
	// Which players are focused.
	int focusedB = 0;
	int focusedR = 0;
	int number = 5;
	boolean[] frozenB = new boolean[number];
	boolean[] frozenR = new boolean[number];
	// Position of the players.
	int[] xr = new int[number];
	int[] yr = new int[number];
	int[] xb = new int[number];
	int[] yb = new int[number];
	// Border size
	int finishSize = 3 * size;
	// Scores
	int scoB = 0;
	int scoR = 0;
	// Speed of the players.
	double[] velxr = new double[number];
	double[] velyr = new double[number];
	double[] velxb = new double[number];
	double[] velyb = new double[number];

	double[] vxr = new double[number];
	double[] vyr = new double[number];
	double[] vxb = new double[number];
	double[] vyb = new double[number];
	// Keys
	boolean up, down, left, right;
	boolean upB, downB, leftB, rightB;
	boolean rrPressed;
	boolean rlPressed;
	boolean brPressed;
	boolean blPressed;
	// Bullets
	int bullNumb = 200;
	int firedr = 0;
	int firedb = 0;
	int[] bullXr = new int[bullNumb];
	int[] bullYr = new int[bullNumb];
	boolean[] bulletr = new boolean[bullNumb];
	int[] bullXb = new int[bullNumb];
	int[] bullYb = new int[bullNumb];
	boolean[] bulletb = new boolean[bullNumb];
	double bulletSpeed = 10;
	int sizeX = (int) (.25 * size);
	int sizeY = (int) (.1 * size);
	boolean[] bulletB = new boolean[4];
	boolean bullFiredB;
	boolean[] bulletR = new boolean[4];
	boolean bullFiredR;
	// Clocks
	Timer clock = new Timer(30, movement);
	Timer clock2 = new Timer(300, shooting);

	// MAIN
	public static void main(String[] args) {
		EnemyGate d = new EnemyGate();
		d.go();
	}

	public void go() {
		reset();
		JFrame frame = new JFrame("\"Remember, The Enemy's Gate Is Down.\"");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		clock.start();
		clock2.start();

		frame.addKeyListener(this);
		frame.setSize(1280, 792);
		frame.setVisible(true);
		while (true) {
			if ((focusedB < 4) && (bulletB[focusedB] != false)) {
				bulletB[focusedB] = false;
				bullXb[firedb] = (xb[focusedB] + size / 2);
				bullYb[firedb] = (yb[focusedB] + size / 2 - sizeY / 2);
				firedb += 1;
			}
			if ((focusedR < 4) && (bulletR[focusedR] != false)) {
				bulletR[focusedR] = false;
				bullXr[firedr] = (xr[focusedR] + size / 2);
				bullYr[firedr] = (yr[focusedR] + size / 2 - sizeY / 2);
				firedr += 1;
			}

			if (firedr == bullNumb)
				firedr = 0;
			if (firedb == bullNumb) {
				firedb = 0;
			}
			for (int i = 0; i < number; i++) {
				int tmp302_301 = i;
				int[] tmp302_298 = xr;
				tmp302_298[tmp302_301] = ((int) (tmp302_298[tmp302_301] + velxr[i]));
				int tmp319_318 = i;
				int[] tmp319_315 = yr;
				tmp319_315[tmp319_318] = ((int) (tmp319_315[tmp319_318] + velyr[i]));
				int tmp336_335 = i;
				int[] tmp336_332 = xb;
				tmp336_332[tmp336_335] = ((int) (tmp336_332[tmp336_335] + velxb[i]));
				int tmp353_352 = i;
				int[] tmp353_349 = yb;
				tmp353_349[tmp353_352] = ((int) (tmp353_349[tmp353_352] + velyb[i]));
				if (velxr[i] > max)
					velxr[i] -= 1.0;
				else if (velxr[i] < -max)
					velxr[i] += 1.0;
				if (velxb[i] > max)
					velxb[i] -= 1.0;
				else if (velxb[i] < -max) {
					velxb[i] += 1.0;
				}
			}
			// BULLET MOVEMENTS
			for (int i = 0; i < bullNumb; i++) {
				// RED
				if ((bullXr[i] < 0) || (bullXr[i] > 1280)) {
					bullXr[i] = -100;
					bullYr[i] = -100;
				}
				if ((bullXr[i] > -100) && (i < bullNumb) && (i >= 0)) {
					int tmp554_553 = i;
					int[] tmp554_550 = bullXr;
					tmp554_550[tmp554_553] = ((int) (tmp554_550[tmp554_553] + bulletSpeed));
				}
				// BLUE
				if ((bullXb[i] < 0) || (bullXb[i] > 1280))
					bullXb[i] = -100;
				if ((bullXb[i] > -100) && (i < bullNumb) && (i >= 0)) {
					int tmp621_620 = i;
					int[] tmp621_617 = bullXb;
					tmp621_617[tmp621_620] = ((int) (tmp621_617[tmp621_620] - bulletSpeed));
				}
				// BULLET COLISION DETECTION
				for (int k = 0; k < blockNumb; k++) {
					// BLOCKS
					if ((bullXr[i] < block[k][0] + 80)
							&& (bullXr[i] + sizeX > block[k][0])
							&& (bullYr[i] + sizeY > block[k][1])
							&& (bullYr[i] < block[k][1] + 80))
						bullXr[i] = -100;
					if ((bullXb[i] < block[k][0] + 80)
							&& (bullXb[i] + sizeX > block[k][0])
							&& (bullYb[i] + sizeY > block[k][1])
							&& (bullYb[i] < block[k][1] + 80)) {
						bullXb[i] = -100;
					}
				}
				for (int k = 0; k < number; k++) {// X1, X2
													// PLAYERS
					if ((bullXr[i] + sizeX >= xb[k])
							&& (bullXr[i] < xb[k] + size)
							&& (bullYr[i] < yb[k] + size)
							&& (bullYr[i] + sizeY > yb[k])) {
						frozenB[k] = true;
						bullXr[i] = -100;
						bullYr[i] = -100;
					}
					if ((bullXb[i] - sizeX <= xr[k])
							&& (bullXb[i] + size > xr[k])
							&& (bullYb[i] < yr[k] + size)
							&& (bullYb[i] + sizeY > yr[k])) {
						frozenR[k] = true;
						bullXb[i] = -100;
						bullYb[i] = -100;
					}
				}
			}
			for (int i = 0; i < number; i++) {
				if (xr[i] < 0)
					velxr[i] = Math.abs(velxr[i]);
				if (xr[i] > getWidth() - size)
					velxr[i] = (-Math.abs(velxr[i]));
				if (yr[i] < 0)
					velyr[i] = Math.abs(velyr[i]);
				if (yr[i] > getHeight() - size - 2) {
					velyr[i] = (-Math.abs(velyr[i]));
				}
				if (xb[i] < 0)
					velxb[i] = Math.abs(velxb[i]);
				if (xb[i] > getWidth() - size)
					velxb[i] = (-Math.abs(velxb[i]));
				if (yb[i] < 0)
					velyb[i] = Math.abs(velyb[i]);
				if (yb[i] > getHeight() - size - 2) {
					velyb[i] = (-Math.abs(velyb[i]));
				}
				// THESE ARE THE BLOCK COLISTIONS
				for (int k = 0; k < blockNumb; k++) {
					if ((xr[i] + size > block[k][0])
							&& (xr[i] <= block[k][0] + 40)
							&& (yr[i] + size > block[k][1])
							&& (yr[i] < block[k][1] + 80))
						velxr[i] = (-Math.abs(velxr[i]));
					if ((xr[i] < block[k][0] + 80)
							&& (xr[i] - 40 >= block[k][0])
							&& (yr[i] > block[k][1])
							&& (yr[i] < block[k][1] + 80))
						velxr[i] = Math.abs(velxr[i]);
					if ((yr[i] + size > block[k][1])
							&& (yr[i] < block[k][1] + 40)
							&& (xr[i] + size > block[k][0])
							&& (xr[i] < block[k][0] + 80))
						velyr[i] = (-Math.abs(velyr[i]));
					if ((yr[i] < block[k][1] + 80)
							&& (yr[i] - size > block[k][1])
							&& (xr[i] + size > block[k][0])
							&& (xr[i] < block[k][0] + 80)) {
						velyr[i] = Math.abs(velyr[i]);
					}
					if ((xb[i] + size > block[k][0])
							&& (xb[i] <= block[k][0] + 40)
							&& (yb[i] + size > block[k][1])
							&& (yb[i] < block[k][1] + 80))
						velxb[i] = (-Math.abs(velxb[i]));
					if ((xb[i] < block[k][0] + 80)
							&& (xb[i] - 40 >= block[k][0])
							&& (yb[i] > block[k][1])
							&& (yb[i] < block[k][1] + 80))
						velxb[i] = Math.abs(velxb[i]);
					if ((yb[i] + size > block[k][1])
							&& (yb[i] < block[k][1] + 40)
							&& (xb[i] + size > block[k][0])
							&& (xb[i] < block[k][0] + 80))
						velyb[i] = (-Math.abs(velyb[i]));
					if ((yb[i] < block[k][1] + 80)
							&& (yb[i] - size > block[k][1])
							&& (xb[i] + size > block[k][0])
							&& (xb[i] < block[k][0] + 80)) {
						velyb[i] = Math.abs(velyb[i]);
					}
				}
				// SIDE/SIDE COLISION DETECTION
				for (int k = 0; k < number; k++) {
					if (xr[i] > 640) {
						if ((xr[i] <= xb[k] + size) && (xr[i] + size > xb[k])
								&& (yr[i] <= yb[k] + size)
								&& (yr[i] + size >= yb[k]))
							if (i != 4) {
								velxr[i] = 0;
								velyr[i] = 0;
								xr[i] = 100;
								yr[i] = (200 + 100 * i);
							} else {
								velxr[4] = 0;
								velyr[4] = 0;
								xr[4] = 50;
								yr[4] = 350;
							}
					} else if ((xb[k] < 640) && (xr[i] <= xb[k] + size)
							&& (xr[i] + size > xb[k])
							&& (yr[i] <= yb[k] + size)
							&& (yr[i] + size >= yb[k])) {
						if (k != 4) {
							velxb[k] = 0;
							velyb[k] = 0;
							xb[k] = 1140;
							yb[k] = (200 + 100 * k);
						} else {
							velxb[4] = 0;
							velyb[4] = 0;
							xb[4] = 1190;
							yb[4] = 350;
						}
					}
				}

			}
			// THE DOCTOR'S DUTY
			for (int i = 0; i < 4; i++) {
				if ((xr[i] <= xr[4] + size) && (xr[i] + size > xr[4])
						&& (yr[i] <= yr[4] + size) && (yr[i] + size >= yr[4]))
					frozenR[i] = false;
				if ((xb[i] <= xb[4] + size) && (xb[i] + size > xb[4])
						&& (yb[i] <= yb[4] + size) && (yb[i] + size >= yb[4]))
					frozenB[i] = false;
			}
			for (int i = 0; i < number; i++) {
				if ((xr[i] > getWidth() - size - 2) && (yr[i] < 416)
						&& (yr[i] > 316) /* && frozenR[i] == false */) {
					if (!doctor_rule)
						scoR += 1;
					else if (doctor_rule) {
						if (i == number - 1)
							scoR += doc_score;
						else
							scoR += 1;
					}
					System.out.println("RED WON - " + scoR + ":" + scoB);
					reset();
				}
				if ((xb[i] < 2) && (yb[i] < 416) && (yb[i] > 316)
				/* && frozenB[i] == false */) {
					if (!doctor_rule)
						scoB += 1;
					else if (doctor_rule) {
						if (i == number - 1)
							scoB += doc_score;
						else
							scoB += 1;
					}
					System.out.println("BLUE WON - " + scoB + ":" + scoR);
					reset();
				}
			}
			// THIS IS IF IT'S FROZEN AND YOU DON'T WANT FOCUS.
			if ((xr[4] > getWidth() - size - 2)
					&& (yr[4] < 376 + finishSize - size)
					&& (yr[4] > 376 - finishSize)) {
				System.out.println("RED WON");
				scoR += 2;
				reset();
			}
			if ((xb[4] < 2) && (yb[4] < 376 + finishSize - size)
					&& (yb[4] > 376 - finishSize)) {
				System.out.println("BLUE WON");
				scoB += 2;
				reset();
			}

			repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException localInterruptedException) {
			}
		}
	}

	public void reset() {
		for (int i = 0; i < 4; i++) {
			velxr[i] = 0;
			velyr[i] = 0;
			velxb[i] = 0;
			velyb[i] = 0;
			xr[i] = 100;
			yr[i] = (200 + 100 * i);
			xb[i] = 1140;
			yb[i] = (200 + 100 * i);
			frozenB[i] = false;
			frozenR[i] = false;
			// These are the random spots of the blocks.
		}
		velxr[4] = 0;
		velyr[4] = 0;
		velxb[4] = 0;
		velyb[4] = 0;
		xr[4] = 50;
		yr[4] = 350;
		xb[4] = 1190;
		yb[4] = 350;
		frozenB[4] = false;
		frozenR[4] = false;
		if (blueDoc > 1) {
			xb[4] = 100000;
			if (blueDoc > 2) {
				xb[3] = 100000;
				if (blueDoc > 3) {
					xb[2] = 100000;
					if (blueDoc > 4) {
						xb[1] = 100000;
						if (blueDoc > 5) {
							xb[0] = 100000;
						}
					}
				}
			}
		}
		if (redDoc > 1) {
			xr[4] = -100000;
			if (redDoc > 2) {
				xr[3] = -100000;
				if (redDoc > 3) {
					xr[2] = -100000;
					if (redDoc > 4) {
						xr[1] = -100000;
						if (redDoc > 5) {
							xr[0] = -100000;
						}
					}
				}
			}
		}
		for (int i = 0; i < blockNumb / 2 + cheat; i++) {
			block[i][0] = ((int) (Math.random() * 560 + 150));
			block[i][1] = ((int) (Math.random() * 692));
		}
		for (int i = blockNumb / 2 + cheat; i < blockNumb; i++) {
			block[i][0] = ((int) (Math.random() * 480 + 560));
			block[i][1] = ((int) (Math.random() * 692));
			if (block[i][0] > 1080) {
				block[i][0] = ((int) (Math.random() * 490 + 500));
			}
		}
		for (int i = 0; i < bullNumb; i++) {
			bullXr[i] = -100;
			bullYr[i] = -100;
			bullXb[i] = -100;
			bullXb[i] = -100;
		}
		focusedR = 0;
		focusedB = 0;
	}

	public void paintComponent(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		// THE BORDER
		g.setColor(borderColor);
		g.drawLine(640, 0, 640, getHeight());
		g.drawLine(641, 0, 641, getHeight());
		// HERE ARE THE PLAYERS
		for (int i = 0; i < 4; i++) {
			if (focusedR == i) {
				g.setColor(redFocused);
			} else if (focusedR != i) {
				g.setColor(redNorm);
			}
			g.fillRect(xr[i], yr[i], size, size);
			if (focusedB == i) {
				g.setColor(blueFocused);
			} else if (focusedB != i) {
				g.setColor(blueNorm);
			}
			g.fillRect(xb[i], yb[i], size, size);
			String si = Integer.toString(i + 1);
			g.setFont(new Font("serif", 1, (int) (.7 * size)));
			if (frozenR[i] == false)
				g.setColor(unfrozenNumberRed);
			else if (frozenR[i] != false) {
				g.setColor(frozenNumberRed);
			}
			g.drawString(si, xr[i] + (int) (.3 * size), yr[i]
					+ (int) (.7 * size));
			if (frozenB[i] == false)
				g.setColor(unfrozenNumberBlue);
			else if (frozenB[i] != false) {
				g.setColor(frozenNumberBlue);
			}
			g.drawString(si, xb[i] + (int) (.3 * size), yb[i]
					+ (int) (.7 * size));
		}// THE DOCTERS:
		if (focusedR == 4) {
			g.setColor(redFocused);
		} else if (focusedR != 4) {
			g.setColor(redNorm);
		}

		g.fillRect(xr[4], yr[4], size, size);
		if (focusedB == 4) {
			g.setColor(blueFocused);
		} else if (focusedB != 4) {
			g.setColor(blueNorm);
		}

		g.fillRect(xb[4], yb[4], size, size);
		if (frozenR[4] == false)
			g.setColor(unfrozenNumberRed);
		else if (frozenR[4] != false) {
			g.setColor(frozenNumberRed);
		}
		g.drawString("D", xr[4] + (int) (.3 * size), yr[4] + (int) (.7 * size));
		if (frozenB[4] == false)
			g.setColor(unfrozenNumberBlue);
		else if (frozenB[4] != false) {
			g.setColor(frozenNumberBlue);
		}
		g.drawString("D", xb[4] + (int) (.3 * size), yb[4] + (int) (.7 * size));
		// THESE ARE THE FINISHES
		g.setColor(redNorm);
		g.drawLine(0, 376 - finishSize, 0, 376 + finishSize);
		g.drawLine(1, 376 - finishSize, 1, 376 + finishSize);
		g.drawLine(2, 376 - finishSize, 2, 376 + finishSize);
		g.setColor(blueNorm);
		g.drawLine(getWidth() - 1, 376 - finishSize, getWidth() - 1,
				376 + finishSize);
		g.drawLine(getWidth() - 2, 376 - finishSize, getWidth() - 2,
				376 + finishSize);
		g.drawLine(getWidth() - 3, 376 - finishSize, getWidth() - 3,
				376 + finishSize);
		// THESE ARE THE BULLETS
		g.setColor(bulletColor);
		for (int i = 0; i < bullNumb; i++) {
			g.drawRect(bullXr[i], bullYr[i], sizeX, sizeY);
			g.drawRect(bullXb[i], bullYb[i], sizeX, sizeY);
		}
		// THESE ARE THE BLOCKS
		g.setColor(blockColor);
		for (int i = 0; i < blockNumb; i++) {
			g.fillRect(block[i][0], block[i][1], 80, 80);
		}
		// THESE ARE THE SCORES
		g.setColor(redNorm);
		g.setFont(new Font("serif", 1, 18));
		String sr = Integer.toString(scoR);
		String sb = Integer.toString(scoB);
		g.drawString(sr, 635 - sr.length() * 9, 20);
		g.setColor(blueNorm);
		g.drawString(sb, 647, 20);
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (two_Player) {
			if (code == 88)
				rrPressed = true;
			if (code == 90) {
				rlPressed = true;
			}
			if (code == 46)
				brPressed = true;
			if (code == 44) {
				blPressed = true;
			}
			if (code == 87)
				up = true;
			if (code == 83)
				down = true;
			if (code == 65)
				left = true;
			if (code == 68)
				right = true;
			if (code == 67) {
				bullFiredR = true;
			}

			if (code == 38)
				upB = true;
			if (code == 40)
				downB = true;
			if (code == 37)
				leftB = true;
			if (code == 39)
				rightB = true;
			if (code == 47) {
				bullFiredB = true;
			}
			if (code == 48) {
				scoB = 0;
				scoR = 0;
			}
			if (code == 32)
				reset();
		} else {
			if (code == 69)
				rrPressed = true;
			if (code == 81) {
				rlPressed = true;
			}
			if (code == 79)
				brPressed = true;
			if (code == 85) {
				blPressed = true;
			}
			if (code == 87)
				up = true;
			if (code == 83)
				down = true;
			if (code == 65)
				left = true;
			if (code == 68)
				right = true;
			if (code == 67) {
				bullFiredR = true;
			}

			if (code == 73)
				upB = true;
			if (code == 75)
				downB = true;
			if (code == 74)
				leftB = true;
			if (code == 76)
				rightB = true;
			if (code == 78) {
				bullFiredB = true;
			}
			if (code == 48) {
				scoB = 0;
				scoR = 0;
			}
			if (code == 32)
				reset();
		}
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (two_Player) {
			if (code == 88)
				rrPressed = false;
			if (code == 90)
				rlPressed = false;
			if (code == 46)
				brPressed = false;
			if (code == 44) {
				blPressed = false;
			}
			if (code == 87)
				up = false;
			if (code == 83)
				down = false;
			if (code == 65)
				left = false;
			if (code == 68)
				right = false;
			if (code == 67) {
				bullFiredR = false;
			}
			if (code == 38)
				upB = false;
			if (code == 40)
				downB = false;
			if (code == 37)
				leftB = false;
			if (code == 39)
				rightB = false;
			if (code == 47)
				bullFiredB = false;
		} else {
			if (code == 69)
				rrPressed = false;
			if (code == 81)
				rlPressed = false;
			if (code == 79)
				brPressed = false;
			if (code == 85) {
				blPressed = false;
			}
			if (code == 87)
				up = false;
			if (code == 83)
				down = false;
			if (code == 65)
				left = false;
			if (code == 68)
				right = false;
			if (code == 67) {
				bullFiredR = false;
			}
			if (code == 73)
				upB = false;
			if (code == 75)
				downB = false;
			if (code == 74)
				leftB = false;
			if (code == 76)
				rightB = false;
			if (code == 78)
				bullFiredB = false;
		}
	}

	public void keyTyped(KeyEvent arg0) {
		if (rrPressed) {
			if (focusedR == number - redDoc)
				focusedR = 0;
			else {
				focusedR += 1;
			}
		}
		if (rlPressed) {
			if (focusedR == 0)
				focusedR = (number - redDoc);
			else {
				focusedR -= 1;
			}
		}
		if (brPressed) {
			if (focusedB == number - blueDoc)
				focusedB = 0;
			else {
				focusedB += 1;
			}
		}
		if (blPressed)
			if (focusedB == 0)
				focusedB = (number - blueDoc);
			else
				focusedB -= 1;
	}

	class Changed implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (rrPressed) {
				if (focusedR == number - redDoc)
					focusedR = 0;
				else {
					focusedR += 1;
				}
			}
			if (rlPressed) {
				if (focusedR == 0)
					focusedR = (number - redDoc);
				else {
					focusedR -= 1;
				}
			}
			if (brPressed) {
				if (focusedB == number - blueDoc)
					focusedB = 0;
				else {
					focusedB += 1;
				}
			}
			if (blPressed)
				if (focusedB == 0)
					focusedB = (number - blueDoc);
				else
					focusedB -= 1;
		}
	}

	class That implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (frozenR[focusedR] == false) {
				if (up)
					velyr[focusedR] -= bs;
				if (down)
					velyr[focusedR] += bs;
				if (left)
					velxr[focusedR] -= bs;
				if (right)
					velxr[focusedR] += bs;
			}
			if (frozenB[focusedB] == false) {
				if (upB)
					velyb[focusedB] -= bs;
				if (downB)
					velyb[focusedB] += bs;
				if (leftB)
					velxb[focusedB] -= bs;
				if (rightB)
					velxb[focusedB] += bs;
			}
		}
	}

	class Those implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if ((frozenR[focusedR] == false) && (focusedR != 4) && (bullFiredR)) {
				bulletR[focusedR] = true;
			}

			if ((frozenB[focusedB] == false) && (focusedB != 4) && (bullFiredB))
				bulletB[focusedB] = true;
		}
	}
}// 809 Lines of code.