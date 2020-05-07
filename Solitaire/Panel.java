
package Solitaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Panel extends JPanel implements ActionListener, MouseMotionListener {

	private JButton newGame, drawPile, threeCards;
	private JButton[] aces;
	private ArrayList<JButton>[] sevenPiles;
	private JPanel controlPanel, drawAndAces, sevenPilesPanel;
	private Controller game;
	private String fileprefix;
	private int listNum1, listNum2;

	public Panel(Controller controller) {

		game = controller;
		game.startNewGame();

		fileprefix = "images/";
		Point origin = new Point(0, 0);
		int offset = 20;

		JLayeredPane pile1 = new JLayeredPane();
		JLayeredPane pile2 = new JLayeredPane();
		JLayeredPane pile3 = new JLayeredPane();
		JLayeredPane pile4 = new JLayeredPane();
		JLayeredPane pile5 = new JLayeredPane();
		JLayeredPane pile6 = new JLayeredPane();
		JLayeredPane pile7 = new JLayeredPane();

		pile1.setPreferredSize(new Dimension(72, 230));
		pile2.setPreferredSize(new Dimension(72, 230));
		pile3.setPreferredSize(new Dimension(72, 230));
		pile4.setPreferredSize(new Dimension(72, 230));
		pile5.setPreferredSize(new Dimension(72, 230));
		pile6.setPreferredSize(new Dimension(72, 230));
		pile7.setPreferredSize(new Dimension(72, 230));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		drawAndAces = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 1));
		sevenPilesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

		ButtonListener listener = new ButtonListener();
		sevenPiles = (ArrayList<JButton>[]) new ArrayList[7];

		for (int i = 0; i < 7; i++) {
			sevenPiles[i] = new ArrayList<JButton>();
		}

		for (int i = 0; i < 7; i++) {
			switch (i) {
			case 0:
				newGame = new JButton("NEW GAME");
				newGame.setBackground(Color.LIGHT_GRAY);
				newGame.setForeground(Color.RED);
				newGame.addActionListener(listener);
				drawAndAces.add(newGame);
				break;
			case 1:
				JLabel space = new JLabel();
				space.setPreferredSize(new Dimension(120, 96));
				drawAndAces.add(space);
				break;
			case 2:
				drawPile = new JButton();
				drawPile.setIcon(new ImageIcon("cats.png"));
				drawPile.setPreferredSize(new Dimension(72, 96));
				drawPile.addActionListener(listener);
				drawAndAces.add(drawPile);
				break;
			case 3:
				JLabel space1 = new JLabel();
				space1.setPreferredSize(new Dimension(15, 96));
				drawAndAces.add(space1);
				break;
			case 4:
				threeCards = new JButton();
				threeCards.setBorder(BorderFactory.createSoftBevelBorder(0, Color.blue, Color.cyan.darker()));
				threeCards.setBackground(Color.white);
				threeCards.setPreferredSize(new Dimension(72, 96));
				threeCards.addActionListener(listener);
				drawAndAces.add(threeCards);
				break;
			case 5:
				JLabel space2 = new JLabel();
				space2.setPreferredSize(new Dimension(120, 96));
				drawAndAces.add(space2);
				break;
			case 6:
				aces = new JButton[4];
				for (int listNum = 0; listNum < 4; listNum++) {
					JButton cardImage = new JButton();
					cardImage.setBorder(BorderFactory.createSoftBevelBorder(0, Color.black, Color.gray));
					cardImage.setBackground(Color.white);
					cardImage.setPreferredSize(new Dimension(72, 96));
					aces[listNum] = cardImage;
					drawAndAces.add(cardImage);
				}
			}
			add(drawAndAces);
		}
		for (int j = 0; j < 7; j++) {
			switch (j) {
			case 0:
				PlayingCard c = game.getCardFromPiles(0, 0);
				if (c != null) {
					JButton label = new JButton(new ImageIcon(fileprefix + c.getImageFileName()));
					label.setPreferredSize(new Dimension(72, 96));
					label.setBorder(BorderFactory.createLineBorder(Color.black));
					label.setBounds(origin.x, origin.y, 72, 96);
					label.addActionListener(listener);
					pile1.add(label, 1, 0);
					sevenPiles[0].add(label);
				}
				sevenPilesPanel.add(pile1);
			case 1:
				origin = new Point(0, 0);
				for (int i = 0; i < 2; i++) {
					c = game.getCardFromPiles(1, i);
					if (c != null) {
						JButton label = new JButton(new ImageIcon("cats.png"));
						label.setPreferredSize(new Dimension(72, 96));
						label.setBorder(BorderFactory.createLineBorder(Color.black));
						label.setBounds(origin.x, origin.y, 72, 96);
						label.addActionListener(listener);
						pile2.add(label, 1, 0);
						origin.y += offset;
						sevenPiles[1].add(label);
						if (i == 1) {
							label.setIcon(new ImageIcon(fileprefix + c.getImageFileName()));
						}
					}
				}
				sevenPilesPanel.add(pile2);
			case 2:
				origin = new Point(0, 0);
				for (int i = 0; i < 3; i++) {
					c = game.getCardFromPiles(2, i);
					if (c != null) {
						JButton label = new JButton(new ImageIcon("cats.png"));
						label.setPreferredSize(new Dimension(72, 96));
						label.setBorder(BorderFactory.createLineBorder(Color.black));
						label.setBounds(origin.x, origin.y, 72, 96);
						label.addActionListener(listener);
						pile3.add(label, 1, 0);
						origin.y += offset;
						if (i == 2) {
							label.setIcon(new ImageIcon(fileprefix + c.getImageFileName()));
						}

						sevenPiles[2].add(label);
					}
				}
				sevenPilesPanel.add(pile3);
			case 3:
				origin = new Point(0, 0);
				for (int i = 0; i < 4; i++) {
					c = game.getCardFromPiles(3, i);
					if (c != null) {
						JButton label = new JButton(new ImageIcon("cats.png"));
						label.setPreferredSize(new Dimension(72, 96));
						label.setBorder(BorderFactory.createLineBorder(Color.black));
						label.setBounds(origin.x, origin.y, 72, 96);
						label.addActionListener(listener);
						pile4.add(label, 1, 0);
						origin.y += offset;
						if (i == 3) {
							label.setIcon(new ImageIcon(fileprefix + c.getImageFileName()));
						}
						sevenPiles[3].add(label);
					}
				}
				sevenPilesPanel.add(pile4);
			case 4:
				origin = new Point(0, 0);
				for (int i = 0; i < 5; i++) {
					c = game.getCardFromPiles(4, i);
					if (c != null) {
						JButton label = new JButton(new ImageIcon("cats.png"));
						label.setPreferredSize(new Dimension(72, 96));
						label.setBorder(BorderFactory.createLineBorder(Color.black));
						label.setBounds(origin.x, origin.y, 72, 96);
						pile5.add(label, 1, 0);
						label.addActionListener(listener);
						origin.y += offset;
						if (i == 4) {
							label.setIcon(new ImageIcon(fileprefix + c.getImageFileName()));
						}
						sevenPiles[4].add(label);
					}
				}
				sevenPilesPanel.add(pile5);
			case 5:
				origin = new Point(0, 0);
				for (int i = 0; i < 6; i++) {
					c = game.getCardFromPiles(5, i);
					if (c != null) {
						JButton label = new JButton(new ImageIcon("cats.png"));
						label.setPreferredSize(new Dimension(72, 96));
						label.setBorder(BorderFactory.createLineBorder(Color.black));
						label.setBounds(origin.x, origin.y, 72, 96);
						label.addActionListener(listener);
						pile6.add(label, 1, 0);
						origin.y += offset;
						if (i == 5) {
							label.setIcon(new ImageIcon(fileprefix + c.getImageFileName()));
						}
						sevenPiles[5].add(label);
					}
				}
				sevenPilesPanel.add(pile6);
			case 6:
				origin = new Point(0, 0);
				for (int i = 0; i < 7; i++) {
					c = game.getCardFromPiles(6, i);
					if (c != null) {
						JButton label = new JButton(new ImageIcon("cats.png"));
						label.setPreferredSize(new Dimension(72, 96));
						label.setBorder(BorderFactory.createLineBorder(Color.black));
						label.setBounds(origin.x, origin.y, 72, 96);
						label.addActionListener(listener);
						pile7.add(label, 1, 0);
						origin.y += offset;
						if (i == 6) {
							label.setIcon(new ImageIcon(fileprefix + c.getImageFileName()));
						}
						sevenPiles[6].add(label);
					}
				}
				sevenPilesPanel.add(pile7);
			}
		}
		add(sevenPilesPanel);
	}

	private void updatePanel() {

		// Each card is a JLabel with an icon (picture) displayed
		// The iconArray keeps track of the references to all of
		// the icons used for the game display.

		for (int listNum = 0; listNum < 7; listNum++) {

			PlayingCard c = game.getCardFromPiles(listNum, game.getLast(listNum));

			if (c == null) {
				//sevenPiles[listNum].remove(sevenPiles[listNum].size() - 1);
				sevenPiles[listNum].get(sevenPiles[listNum].size() - 1).setIcon(null);
				sevenPiles[listNum].get(sevenPiles[listNum].size() - 1).setEnabled(false);
			} else {
				sevenPiles[listNum].add(sevenPiles[listNum].get(sevenPiles[listNum].size() - 1));

				sevenPiles[listNum].get(sevenPiles[listNum].size() - 1)
						.setIcon(new ImageIcon(fileprefix + c.getImageFileName()));
				sevenPiles[listNum].get(sevenPiles[listNum].size() - 1).setEnabled(true);
			}

		}

		for (int i = 0; i < 4; i++) {
			PlayingCard c = game.getCardFromAces(i);
			if (aces.length > 0) {
				if (c == null)
					aces[i].setIcon(null);
				else
					aces[i].setIcon(new ImageIcon(fileprefix + c.getImageFileName()));
			}
		}

		PlayingCard threeCard = game.getCardFromThreeCards();
		if (threeCard == null) {
			threeCards.setIcon(null);
			threeCards.setEnabled(false);
		} else {
			threeCards.setIcon(new ImageIcon(fileprefix + threeCard.getImageFileName()));
			threeCards.setEnabled(true);
		}
		PlayingCard d = game.getCardFromDrawPile();
		if (d == null && threeCard == null) {
			drawPile.setIcon(null);
			drawPile.setEnabled(false);
		} else if (d == null && threeCard != null) {
			drawPile.setIcon(null);
			drawPile.setEnabled(true);
		} else {
			drawPile.setIcon(new ImageIcon("cats.png"));
			drawPile.setPreferredSize(new Dimension(72, 96));
			drawPile.setEnabled(true);
		}
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == drawPile && drawPile.getIcon() != null)
				game.deal();
			else if (event.getSource() == drawPile && drawPile.getIcon() == null) {
				game.reDeck();
				drawPile.setIcon(new ImageIcon("cats.png"));
				drawPile.setPreferredSize(new Dimension(72, 96));
				drawPile.setEnabled(true);
				threeCards.setIcon(null);
				threeCards.setEnabled(false);
			} else if (event.getSource() == threeCards) {
				if (game.canMoveFromThreesToAces() == true) {
					game.moveFromThreesToAces();
				} else {
					for (int i = 0; i < 7; i++) {
						game.moveFromThreesToSevens(i);
						Point origin = new Point(0,0);
						PlayingCard c = game.getCardFromPiles(i, sevenPiles[i].size()-1);
						JButton label = new JButton(new ImageIcon("cats.png"));
						//label.setIcon(new ImageIcon(fileprefix + c.getImageFileName()));
						label.setPreferredSize(new Dimension(72, 96));
						label.setBorder(BorderFactory.createLineBorder(Color.black));
						label.setBounds(origin.x, origin.y, 72, 96);
						ButtonListener listener = new ButtonListener();
						label.addActionListener(listener);
					}
				}
			} else if (event.getSource() == newGame)
				game.startNewGame();
			else {
				for (int listNum = 0; listNum < 7; listNum++) {
					if (event.getSource() == sevenPiles[listNum].get(sevenPiles[listNum].size() - 1)) {
						if (game.canMoveToAces(listNum) == true) {
							game.moveToAces(listNum);
						} else {
							game.moveWithinLists(listNum);
						}
					}
				}
			}
			updatePanel();
		}

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == drawPile && drawPile.getIcon() != null)
			game.deal();
		else if (arg0.getSource() == drawPile && drawPile.getIcon() == null) {
			game.reDeck();
			drawPile.setIcon(new ImageIcon("cats.png"));
			drawPile.setPreferredSize(new Dimension(72, 96));
			drawPile.setEnabled(true);
			threeCards.setIcon(null);
			threeCards.setEnabled(false);
		} else if (arg0.getSource() == threeCards) {
			if (game.canMoveFromThreesToAces() == true) {
				game.moveFromThreesToAces();
			} else {
				for (int i = 0; i < 7; i++) {
					game.moveFromThreesToSevens(i);
				}
			}
		} else if (arg0.getSource() == newGame)
			game.startNewGame();
		else {
			for (int listNum = 0; listNum < 7; listNum++) {
				if (arg0.getSource() == sevenPiles[listNum].get(sevenPiles[listNum].size() - 1)) {
					if (game.canMoveToAces(listNum) == true) {
						game.moveToAces(listNum);
					} else {
						game.moveWithinLists(listNum);
					}
				}
			}
		}
		updatePanel();
	}

}