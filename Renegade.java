import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Renegade extends JPanel implements ActionListener, ChangeListener, ItemListener{
	int WIDTH = 1000;
	int HEIGHT = 1000;
	JPanel middle = new JPanel();
	JPanel bottom = new JPanel();
	JPanel fencerMenu = new JPanel();
	JPanel sliderCount = new JPanel();
	Label TITLE = new Label("THE RENEGADE CHALLENGE", Label.CENTER);
	Label NUM_BOUTS_LABEL = new Label("Number of Bouts:", Label.CENTER);
	Label fencerDetails = new Label("", Label.CENTER);
	Label warningLabel = new Label("", Label.CENTER);
	Label scoreLabel = new Label ("");
	JTextField countText = new JTextField();
	JSlider boutSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
	Button CALCULATE = new Button("Calculate score");

	Fencer renegadeFencer;
	int boutsRecorded;
	int score = 0;

//	Fencer[] currentOpponent = new Fencer[100];
	JPanel[] fencerBouts = new JPanel[100];
	JPanel[] weaponsPanel = new JPanel[100];
	JPanel[] victoryPanel = new JPanel[100];
	JPanel[] opponentWarningPanel = new JPanel[100];
	JComboBox[] opponents = new JComboBox[100];
	ButtonGroup[] weaponSelect = new ButtonGroup[100];
	ButtonGroup[] victorySelect = new ButtonGroup[100];
	JRadioButton[][] weaponButtons = new JRadioButton[100][3];
	JRadioButton[][] victoryButtons = new JRadioButton[100][3];
	Label[] opponentWarning = new Label[100];

	// FENCER (NAME, AGE, RATING, BEST_WEAPON, WORST_WEAPON, TEAM_NVFA)		//DO NOT FORGET TO ADD FENCERS TO >>listOfFencers<< AND >>listOfNames<< BELOW!
	Fencer ISABEL = new Fencer("Isabel", 25, 'n', "foil", "epee", false);
	Fencer MILES = new Fencer("Miles", 25, 'U', "saber", "epee", true);
	Fencer ASA = new Fencer("Asa", 15, 'U', "saber", "epee", false);
	Fencer LUIS = new Fencer("Luis", 14, 'U', "saber", "epee", true);
	Fencer TAYLOR = new Fencer("Taylor", 28, 'E', "saber", "epee", true);
	Fencer PETER = new Fencer("Peter", 13, 'U', "foil", "epee", false);
	Fencer LUCAS = new Fencer("Lucas", 12, 'n', "saber", "epee", false);
	Fencer ORIEN = new Fencer("Orien", 10, 'n', "foil", "saber", false);
	Fencer MAYALANE = new Fencer("Mayalane", 14, 'n', "foil", "saber", false);
	Fencer KIT = new Fencer("Kit", 10, 'n', "foil", "epee", false);
	Fencer CARLO = new Fencer("Carlo", 10, 'n', "saber", "epee", false);
	Fencer ERIC = new Fencer("Eric", 14, 'n', "saber", "epee", false);
	Fencer TYE = new Fencer("Tye", 14, 'n', "foil", "epee", false);
	Fencer BRYCE = new Fencer("Bryce", 28, 'U', "saber", "epee", false);
	Fencer ANNA = new Fencer("Anna", 15, 'n', "foil", "saber", false);
	Fencer MAX = new Fencer("Max", 17, 'n', "foil", "saber", false);
	Fencer FINN = new Fencer("Finn", 10, 'n', "saber", "epee", false);
	Fencer ARTHUR = new Fencer("Arthur", 14, 'n', "epee", "saber", false);
	Fencer DWYER = new Fencer("Dwyer", 10, 'n', "foil", "epee", false);
	Fencer JUSTIN = new Fencer("Justin", 12, 'n', "saber", "epee", false);
	Fencer BEN = new Fencer("Ben", 16, 'n', "epee", "foil", false);
	Fencer HERICLEIA = new Fencer("Hericleia", 11, 'n', "epee", "saber", false);
	Fencer JANA = new Fencer("Jana", 40, 'C', "foil", "epee", true);
	Fencer NOEL = new Fencer("Noel", 11, 'n', "foil", "saber", false);
	Fencer LYDIA = new Fencer("Lydia", 12, 'n', "foil", "saber", false);
	Fencer ALBERT = new Fencer("Albert", 60, 'n', "foil", "saber", false);
	Fencer ADAM = new Fencer("Adam", 16, 'n', "saber", "epee", false);

	Fencer[] listOfFencers = {ISABEL, MILES, ASA, LUIS, TAYLOR, PETER, LUCAS, ORIEN, MAYALANE, KIT, CARLO, ERIC, TYE, BRYCE, ANNA, MAX, FINN, ARTHUR, DWYER, JUSTIN, BEN, HERICLEIA, JANA, NOEL, LYDIA, ALBERT, ADAM};
	String[] listOfNames = {ISABEL.name, MILES.name, ASA.name, LUIS.name, TAYLOR.name, PETER.name, LUCAS.name, ORIEN.name, MAYALANE.name, KIT.name, CARLO.name, ERIC.name, TYE.name, BRYCE.name, ANNA.name, MAX.name, FINN.name, ARTHUR.name, DWYER.name, JUSTIN.name, BEN.name, HERICLEIA.name, JANA.name, NOEL.name, LYDIA.name, ALBERT.name, ADAM.name};

	JComboBox currentFencer = new JComboBox(listOfNames);

// CONSTRUCTOR
	public Renegade(){
		// LAYOUTS
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.middle.setLayout(new BoxLayout(this.middle, BoxLayout.Y_AXIS));

		// BORDERS
		this.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));

		// INITIALIZE COMPONENTS
		this.renegadeFencer = this.findFencer((String)currentFencer.getSelectedItem());
		boutsRecorded = this.boutSlider.getValue();
		this.boutSlider.setMajorTickSpacing(10);
		this.boutSlider.setMinorTickSpacing(5);
		this.boutSlider.setPaintTicks(true);
		this.boutSlider.setPaintLabels(true);
		this.countText.setPreferredSize(new Dimension(40, 25));
		this.countText.setText(((Integer)this.boutsRecorded).toString());
		this.findFencerDetails((String)this.currentFencer.getSelectedItem());
		this.warningLabel.setForeground(Color.RED);

		
		// ADD COMPONENTS
		this.add(this.TITLE);
		this.add(this.fencerMenu);
			this.fencerMenu.add(this.currentFencer);
		this.add(this.fencerDetails);
		this.add(this.sliderCount);
			this.sliderCount.add(this.NUM_BOUTS_LABEL);
			this.sliderCount.add(this.countText);
		this.add(this.boutSlider);
		this.add(warningLabel);
		for(int i = 0; i < fencerBouts.length; i++){
			int j = i + 1;
			this.fencerBouts[i] = new JPanel();
			this.weaponsPanel[i] = new JPanel();
				this.weaponsPanel[i].setLayout(new BoxLayout(this.weaponsPanel[i], BoxLayout.Y_AXIS));
				this.weaponsPanel[i].setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 10));
			this.victoryPanel[i] = new JPanel();
				this.victoryPanel[i].setLayout(new BoxLayout(this.victoryPanel[i], BoxLayout.Y_AXIS));
				this.victoryPanel[i].setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 10));
			this.opponentWarningPanel[i] = new JPanel();
			this.opponentWarning[i] = new Label("");
				this.opponentWarning[i].setForeground(Color.RED);
			this.middle.add(fencerBouts[i]);
				this.opponents[i] = new JComboBox(listOfNames);
				this.weaponSelect[i] = new ButtonGroup();
				this.weaponButtons[i][0] = new JRadioButton("foil");
				this.weaponButtons[i][1] = new JRadioButton("saber");
				this.weaponButtons[i][2] = new JRadioButton("epee");
					this.weaponSelect[i].add(this.weaponButtons[i][0]);
					this.weaponSelect[i].add(this.weaponButtons[i][1]);
					this.weaponSelect[i].add(this.weaponButtons[i][2]);
				this.victorySelect[i] = new ButtonGroup();
				this.victoryButtons[i][0] = new JRadioButton("victory");
				this.victoryButtons[i][1] = new JRadioButton("defeat");
				this.victoryButtons[i][2] = new JRadioButton("flawless");
					this.victorySelect[i].add(this.victoryButtons[i][0]);
					this.victorySelect[i].add(this.victoryButtons[i][1]);
					this.victorySelect[i].add(this.victoryButtons[i][2]);
				this.fencerBouts[i].add(new Label(j + ": "));	
				this.fencerBouts[i].add(this.opponents[i]);
				this.fencerBouts[i].add(this.weaponsPanel[i]);
					this.weaponsPanel[i].add(this.weaponButtons[i][0]);
					this.weaponsPanel[i].add(this.weaponButtons[i][1]);
					this.weaponsPanel[i].add(this.weaponButtons[i][2]);
				this.fencerBouts[i].add(this.victoryPanel[i]);
					this.victoryPanel[i].add(this.victoryButtons[i][0]);
					this.victoryPanel[i].add(this.victoryButtons[i][1]);
					this.victoryPanel[i].add(this.victoryButtons[i][2]);
				this.fencerBouts[i].add(this.opponentWarningPanel[i]);
					this.opponentWarningPanel[i].add(this.opponentWarning[i]);
		}
			this.setFencerBoutCountView();
		this.add(this.middle);
		this.add(this.bottom);
			this.bottom.add(this.CALCULATE);
			this.bottom.add(this.scoreLabel);

		// LISTENERS
		this.CALCULATE.addActionListener(this);
		this.boutSlider.addChangeListener(this);
		this.currentFencer.addItemListener(this);
		for(int i = 0; i < listOfNames.length; i++){
			this.opponents[i].addItemListener(this);
		}
	}

// METHODS
/*
	public Dimension getPreferredSize(){
		return new Dimension(WIDTH, HEIGHT);
	}
*/
	public void findFencerDetails(String s){
		for(int i = 0; i < this.listOfFencers.length; i++){
			if(this.listOfFencers[i].name == s){
				this.fencerDetails.setText(this.listOfFencers[i].listStats());
			}
		}
	}

	public Fencer findFencer(String s){
		Fencer f = new Fencer();
		for(int i = 0; i < this.listOfFencers.length; i++){
			if(this.listOfFencers[i].name == s){
				f = this.listOfFencers[i];
			}
		}
			return f;
	}

	public void setFencerBoutCountView(){
		for(int i = 0; i < fencerBouts.length; i++){
			if(i < this.boutsRecorded){
				fencerBouts[i].setVisible(true);
			}
			else{
				fencerBouts[i].setVisible(false);
			}
		}
	}

	public void showWarnings(){
		this.warningLabel.setVisible(true);
		for(int i = 0; i < this.boutsRecorded; i++){
			this.opponentWarning[i].setVisible(true);
		}
	}

	public void hideWarning(){
		this.warningLabel.setVisible(false);
		for(int i = 0; i < this.boutsRecorded; i++){
			this.opponentWarning[i].setVisible(false);
		}
	}

	public void clearWarnings(){
		this.warningLabel.setText("");
		for(int i = 0; i < this.boutsRecorded; i++){
			this.opponentWarning[i].setText("");
		}
		this.scoreLabel.setText("");
	}

	public void resetScore(){
		this.score = 0;
		System.out.println("Score has been reset. Score = " + this.score);
	}

// EVENTS
	public void actionPerformed(ActionEvent e){
		this.resetScore();
		boolean warningReceived = false;
		int warningCount = 0;
		this.clearWarnings();
		if(e.getSource().equals(this.CALCULATE)){

			System.out.println("Button has been pressed. Bouts recorded : " + this.boutsRecorded);

			for(int i = 0; i < this.boutsRecorded; i++){
				int j = i + 1;
				Fencer opponent;
				boolean victory = false;
				boolean flawless = false;
				String weaponUsed = "foil";
				boolean opponentWarning = false;

				// SELECT OPPONENT
				opponent = this.findFencer(String.valueOf(this.opponents[i].getSelectedItem()));
				if(opponent.name == this.renegadeFencer.name){
					this.opponentWarning[i].setText(this.opponentWarning[i].getText() + "The opponent selected is the current fencer. Please Select a valid opponent.\n");
					warningReceived = true;
					opponentWarning = true;
					System.out.println("Warning message added: The opponent selected is the current fencer. Please Select a valid opponent.");
				}
				else{
					System.out.println("Opponent selected: " + this.opponents[i].getSelectedItem());
					System.out.println("Current Opponent is: " + opponent.name);
				}

				// SELECT VICTORY/DEFEAT/FLAWLESS
				if(this.victoryButtons[i][2].isSelected()){			// "flawless"
					victory = true;
					flawless = true;
					System.out.println("FLAWLESS.");
				}
				else if(this.victoryButtons[i][0].isSelected()){		// "victory"
					victory = true;
					flawless = false;
					System.out.println("VICTORY.");
				}
				else if(this.victoryButtons[i][1].isSelected()){		// "defeat"
					victory = false;
					flawless = false;
					System.out.println("DEFEAT.");
				}
				else{
					this.opponentWarning[i].setText(this.opponentWarning[i].getText() + "Please Select \"victory\", \"defeat\", or \"flawless\".\n");
					warningReceived = true;
					opponentWarning = true;
					System.out.println("Warning message added: Please Select \"victory\", \"defeat\", or \"flawless\".\n");
				}

				// SELECT WEAPON
				if(weaponButtons[i][0].isSelected()){				// "foil"
					weaponUsed = "foil";
					System.out.println("FOIL.");
				}
				else if(weaponButtons[i][1].isSelected()){			// "saber"
					weaponUsed = "saber";
					System.out.println("SABER.");
				}
				else if (weaponButtons[i][2].isSelected()){			// "epee"
					weaponUsed = "epee";
					System.out.println("EPEE.");
				}
				else{
					this.opponentWarning[i].setText(this.opponentWarning[i].getText() + "Please Select a weapon.\n");
					warningReceived = true;
					opponentWarning = true;
					System.out.println("Warning message added: Please Select a weapon.\n");
				}

				if(opponentWarning){
					this.warningLabel.setText(this.warningLabel.getText() + "Please modify Bout " + j + ".\n");
					warningCount += 1;
					System.out.println("Warnings were received. Warning count: " + warningCount + ".");
				}
				else{
					System.out.println("No current warnings.");
					System.out.println("Opponent: " + opponent.name);
					System.out.println("Weapon: " + weaponUsed);
					System.out.println("Victory? " + victory);
					System.out.println("Flawless? " + flawless);
					this.score += this.renegadeFencer.bout(opponent, weaponUsed, victory, flawless);
					System.out.println("Points received: " + this.renegadeFencer.bout(opponent, weaponUsed, victory, flawless));
				}
				System.out.println("Done. Current score: " + this.score);
			}

			if(warningCount > 0){
				System.out.println("Score could not be calculated. Please resolve errors and recalculate score.");
				this.scoreLabel.setText("Score could not be calculated. Please resolve errors and recalculate score.");
			}
			else{
				System.out.println("Score = " + this.score);
				this.scoreLabel.setText("RENEGADE SCORE: " + this.score + ".");
			}
		}
		this.validate();
	}
	
	public void stateChanged(ChangeEvent e){
		this.resetScore();
		if(!this.boutSlider.getValueIsAdjusting()){
			System.out.println("Value: " + this.boutSlider.getValue() + ".");
			this.boutsRecorded = this.boutSlider.getValue();
			this.countText.setText(((Integer)this.boutsRecorded).toString());
			System.out.println("Bouts recorded: " + this.boutSlider.getValue() + ".");
			this.setFencerBoutCountView();
			this.clearWarnings();
		}
	}

	public void itemStateChanged(ItemEvent e){
		this.resetScore();
		if(e.getSource().equals(this.currentFencer) && e.getStateChange() == ItemEvent.SELECTED){
			System.out.println("Fencer selected: " + e.getItem());
			this.findFencerDetails((String)e.getItem());
			this.renegadeFencer = this.findFencer((String)e.getItem());
			System.out.println("current Fencer: " + this.renegadeFencer.name);
			this.clearWarnings();
		}
	}


// MAIN
	public static void main(String[] args){
		JFrame f = new JFrame("Renegade");
		Renegade r = new Renegade();
		JScrollPane scroll = new JScrollPane(r);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.getVerticalScrollBar().setUnitIncrement(25);
		scroll.setPreferredSize(new Dimension(1500, 1000));


		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(scroll);
		f.pack();
		f.setVisible(true);
	}
}