import static java.lang.System.out;
import java.lang.Object;
import java.lang.Character;
import java.lang.String;
import java.lang.Integer;
import java.util.Scanner;

public class Fencer
{
	String name;
	Character rating;
	int age;
	boolean nvfa;
	String bestWeapon;
	String weakestWeapon;
	int score;

	public Fencer()	//TO DO: MAKE RANDOM PERSON
	{
		this.name = "";
		this.age = 0;
		this.rating = 'n';
		this.bestWeapon = "saber";
		this.weakestWeapon = "epee";
		this.nvfa = false;
		this.score = 0;
	}

	public Fencer(String name, int age, Character rating, String best, String weakest, boolean nvfa){
	this.name = name;
	this.age = age;
	this.rating = rating;
	this.bestWeapon = best;
	this.weakestWeapon = weakest;
	this.nvfa = nvfa;
	this.score = 0;
	}

	public int bout(Fencer other, String weapon, boolean victory, boolean flawless){
		this.score = 0;
		if(victory){
//			System.out.println("YOU WON!");
			this.score += 3;
//			System.out.println("+3 POINTS for victory.");
		
			if(flawless){
				if(this.ageCategory().equals(other.ageCategory()) || this.age <= other.age){
					this.score += 3;
//					System.out.println("+3 POINTS for flawless victory.");
				}
			}

			if(weapon.equals(other.bestWeapon)){
				this.score += 3;
//				System.out.println("+3 POINTS for winning with Opponent's best weapon.");
			}
			else if(weapon.equals(this.bestWeapon)){
				this.score += 2;
//				System.out.println("+2 POINTS for winning with your best weapon.");
			}
		
			if(weapon.equals(this.weakestWeapon)){
				this.score += 4;
//				System.out.println("+4 POINTS for winning with your weakest weapon.");
			}

			if(other.age <= 12){
				this.score += 2;
//				System.out.println("+2 POINTS for winning against an opponent in the age category of " + other.ageCategory() + ".");
			}
			else if(other.age <= 15 && other.age > 12){
				this.score += 3;
//				System.out.println("+3 POINTS for winning against an opponent in the age category of " + other.ageCategory() + ".");
			}
			else if(other.age <= 19 && other.age > 15){
				this.score +=4;
//				System.out.println("+4 POINTS for winning against an opponent in the age category of " + other.ageCategory() + ".");
			}
			else{
				this.score += 5;
//				System.out.println("+5 POINTS for winning against an opponent in the age category of " + other.ageCategory() + ".");
			}
		
			switch (other.rating){
				case 'U': 
					this.score += 2;
//					System.out.println("+2 POINTS for winning against a USFA rated fencer with a  rating of " + other.rating() + ".");
					break;
				case 'E':
					this.score += 3;
//					System.out.println("+3 POINTS for winning against a USFA rated fencer with a  rating of " + other.rating() + ".");
					break;
				case 'D':
					this.score += 5;
//					System.out.println("+5 POINTS for winning against a USFA rated fencer with a  rating of " + other.rating() + ".");
					break;
				case 'C':
					this.score += 7;
//					System.out.println("+7 POINTS for winning against a USFA rated fencer with a  rating of " + other.rating() + ".");
					break;
				case 'B':
					this.score += 10;
//					System.out.println("+10 POINTS for winning against a USFA rated fencer with a  rating of " + other.rating() + ".");
					break;
				case 'A':
					this.score += 15;
//					System.out.println("+15 POINTS for winning against a USFA rated fencer with a  rating of " + other.rating() + ".");
					break;
			}

			if(other.nvfa){
				this.score += 3;
//				System.out.println("+3 POINTS for winning against a competitive NVFA fencer.");
			}

		}
		else
		{
//			System.out.println("YOU LOST!");

			this.score += 1;
//			System.out.println("+1 POINT for defeat.");

			if(weapon.equals(other.bestWeapon)){
				this.score += 1;
//				System.out.println("+1 POINT for losing with Opponent's best weapon.");
			}
		
			if(weapon.equals(this.weakestWeapon)){
				this.score += 1;
//				System.out.println("+1 POINT for losing with your weakest weapon.");
			}
			else if(weapon.equals(this.bestWeapon)){
//				System.out.println("0 POINTS for losing with you best weapon.");
			}
		
			if(other.age <= 14){
				this.score += 1;
//				System.out.println("+1 POINT for losing against an opponent in the age category of " + other.ageCategory() + ".");
			}
			else{
				this.score += 2;
//				System.out.println("+1 POINT for losing against an opponent in the age category of " + other.ageCategory() + ".");
			}

			switch (other.rating) {
				case 'U': 
					this.score += 1;
//					System.out.println("+1 POINT for losing against a USFA rated fencer with a  rating of " + other.rating() + ".");
					break;
				case 'E':
					this.score += 1;
//					System.out.println("+1 POINT for losing against a USFA rated fencer with a  rating of " + other.rating() + ".");
					break;
				case 'D':
					this.score += 2;
//					System.out.println("+2 POINTS for losing against a USFA rated fencer with a  rating of " + other.rating() + ".");
					break;
				case 'C':
					this.score += 2;
//					System.out.println("+2 POINTS for losing against a USFA rated fencer with a  rating of " + other.rating() + ".");
					break;
				case 'B':
					this.score += 4;
//					System.out.println("+4 POINTS for losing against a USFA rated fencer with a  rating of " + other.rating() + ".");
					break;
				case 'A':
					this.score += 4;
//					System.out.println("+4 POINTS for losing against a USFA rated fencer with a  rating of " + other.rating() + ".");
					break;
			}

			if(other.nvfa){
				this.score += 2;
//				System.out.println("+2 POINTS for losing against a competitive NVFA fencer.");
			}
		}

//		System.out.println("FINAL SCORE: " + this.score);
		return this.score;
	}

	String rating(){
		if(this.rating.equals('n')){
			return "N/A";
		}
		else{
			return Character.toString(this.rating);
		}
	}

	String isNVFA(){
		if(this.nvfa){
			return "Yes";
		}
		else{
			return "No";
		}
	}

	String ageCategory(){
		if(this.age <= 10){
			return "Y10";
		}
		else if(this.age == 11 || this.age == 12){
			return "Y12";
		}
		else if(this.age == 13 || this.age == 14){
			return "Y14";
		}
		else if(this.age == 15){
			return "Cadet";
		}
		else if(this.age <= 19 && this.age >= 16){
			return "Junior";
		}
		else if(this.age > 40){
			return "Veteran";
		}
		else{
			return "Senior";
		}
	}

	String listStats(){
		System.out.println("\nName: " + this.name);	
		System.out.println("Age category: " + this.ageCategory());
		System.out.println("Best Weapon: " + this.bestWeapon);
		System.out.println("Weakest Weapon: " + this.weakestWeapon);
		System.out.println("USFA rating: " + this.rating());
		System.out.println("Competitive NVFA team member? " + this.nvfa);
	
		return "Name: " + this.name + "\nAge category: " + this.ageCategory() + "\nBest Weapon: " + this.bestWeapon + "\nWeakest Weapon: " + this.weakestWeapon + "\nUSFA rating: " + this.rating() + "\nCompetitive NVFA team member: " + this.isNVFA();
	}

	void setName(String name){
		this.name = name;
		System.out.println("Name has been set as: " + this.name);
	}

	void setAge(int age){
		this.age = age;
		System.out.println("Age has been set to: " + this.age);
	}

	void setRating(Character rating){
		this.rating = rating;
		System.out.println("USFA rating has been set to: " + this.rating);
	}

	void setBestWeapon(String weapon){
		this.bestWeapon = weapon;
		System.out.println("Best weapon has been set to: " + this.bestWeapon);
	}

	void setWeakestWeapon(String weapon){
		this.weakestWeapon = weapon;
		System.out.println("Weakest weapon has been set to: " + this.weakestWeapon);
	}

/*
public static void main(String [] args)
{
	Fencer ISABEL = new Fencer("Isabel", 25, 'n', "foil", "epee", false);
	Fencer MILES = new Fencer("Miles", 25, 'U', "saber", "epee", true);
	Fencer ASA = new Fencer("Asa", 15, 'U', "saber", "epee", false);
	Fencer LUIS = new Fencer("Luis", 14, 'U', "saber", "epee", true);
	Fencer TAYLOR = new Fencer("Taylor", 28, 'E', "saber", "epee", true);
	Fencer PETER = new Fencer("Peter", 13, 'U', "saber", "epee", false);
	Fencer LUCAS = new Fencer("Lucas", 12, 'n', "saber", "epee", false);
	Fencer ORIEN = new Fencer("Orien", 10, 'n', "foil", "epee", false);
	Fencer MAYALANE = new Fencer("Mayalane", 14, 'n', "foil", "saber", false);
	Fencer KIT = new Fencer("Kit", 10, 'n', "foil", "epee", false);
	Fencer CARLO = new Fencer("Carlo", 10, 'n', "saber", "epee", false);
	Fencer ERIC = new Fencer("Eric", 14, 'n', "saber", "epee", false);
	Fencer TYE = new Fencer("Tye", 14, 'n', "saber", "epee", false);
	Fencer BRYCE = new Fencer("Bryce", 28, 'U', "saber", "epee", false);
	Fencer ANNA = new Fencer("Anna", 15, 'n', "foil", "epee", false);
	Fencer MAX = new Fencer("Max", 17, 'n', "foil", "epee", false);
	Fencer FINN = new Fencer("Finn", 10, 'n', "saber", "epee", false);
	Fencer ARTHUR = new Fencer("Arthur", 14, 'n', "epee", "saber", false);
	Fencer DWYER = new Fencer("Dwyer", 10, 'n', "foil", "epee", false);
	Fencer JUSTIN = new Fencer("Justin", 12, 'n', "epee", "foil", false);
	Fencer BEN = new Fencer("Ben", 16, 'n', "saber", "epee", false);
	Fencer HERICLEIA = new Fencer("Hericleia", 11, 'n', "saber", "epee", false);
	Fencer JANA = new Fencer("Jana", 40, 'C', "foil", "epee", true);
	Fencer NOEL = new Fencer("Noel", 11, 'n', "saber", "epee", false);
	Fencer LYDIA = new Fencer("Lydia", 12, 'n', "foil", "epee", false);
	Fencer ALBERT = new Fencer("Albert", 60, 'n', "foil", "epee", false);

	Fencer[] listOfFencers = {ISABEL, MILES, ASA, LUIS, TAYLOR, PETER, LUCAS, ORIEN, MAYALANE, KIT, CARLO, ERIC, TYE, BRYCE, ANNA, MAX, FINN, ARTHUR, DWYER, JUSTIN, BEN, HERICLEIA, JANA, NOEL, LYDIA, ALBERT};

	Scanner user_input = new Scanner(System.in);
	String input;

	System.out.println("WELCOME TO THE RENEGADE CHALLENGE!");
	System.out.println("Please type your first name: ");
	input = user_input.next();
	
	Fencer current_fencer;
	int i;
	for(i = 0; i < listOfFencers.length; i++){
		if(input.equalsIgnoreCase(listOfFencers[i].name)){
			current_fencer = listOfFencers[i];
			break;
		}
	}

	// FENCER_NAME.bout(OPPONENT_NAME, WEAPON_NAME, VICTORY_BOOLEAN, FLAWLESS_VICTORY_BOOLEAN)
	// ISABEL.bout(MILES, "saber", false, false);
}
*/

}