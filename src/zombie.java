import java.util.*;
class zombie{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		int x = nousers();//call for amount of users setting x as number of players
		int y = 2; // number of dice starts of with 3
		
		String[] TempUserhold = new String [x];
		
		String[][] userNames = new String[x][4];//array to store users names
		String[] NewDice = new String[y]; //Array to hold the dice colour generated
		
		int[][]Scoreholder = new int [x][4];
		int[][]ScoreStorage = new int [x][4];
		
		int[][]MasterBrainHolder = new int [x][4];
		
		System.out.println("amount of users = "+x);//used for testing purposes
		System.out.println("");
		
		TempUserhold = nameinput(x);//calling for input of player names
		
		for(int z=0; z<x; z++){
			userNames[z][0] = TempUserhold[z];
			
		}
		
		System.out.println (""); // Spacing added in
		displaynames(userNames,x);//call in to display names
		
		System.out.print("Your chooice: ");
		System.out.println(userNames[0][0]);
		int menu = menusystem();//play again or next player options
		structure(menu, y, NewDice, userNames, x, Scoreholder, ScoreStorage, MasterBrainHolder);//loop for how the game should be structured
	}
	public static int [] structure(int menu, int y, String[] NewDice, String[][] userNames, int x, int[][]Scoreholder, int[][]ScoreStorage, int[][]MasterBrainHolder){//put all things that involve calling methods of menu system
		int i=1;
		int turn = 0;
		int [] DiceCheck_Holder = new int [y];
		//below need to be linked to score keeping so they update 
		int scorekeeper = (userNames.length);


		while(menu!=9){//needs be change to stop after 13 brains are reached
			
			if(ScoreStorage[i-1][1] == 13 || MasterBrainHolder[i-1][1] == 13 || MasterBrainHolder[i-1][1] + ScoreStorage[i-1][1] == 13){
				gamefinished();
			}
			if(turn == 4){ //to make it do there is 13 dice
				menu = 2;
				System.out.println ("You ran out of dice, next players turn");
			}
			if(menu == 1){
				String[] score = new String[userNames.length];
				NewDice = dice_random(y);
				
				System.out.println ("******************************************************************************************************");
				System.out.println ("");
				DisplayDice(NewDice,y);
				System.out.println ("");
				int [] DiceChecked = DiceColour_Check(NewDice, y);
				System.out.println ("");
				DiceCheck_Holder = DiceChecked;
				Scoreholder = DiceColour_Match (DiceCheck_Holder,y,userNames,i,x);
				
				ScoreStorage[i-1][2] = ScoreStorage[i-1][2] + Scoreholder[i-1][2]; //Storing how many time your shot for this turn
				
				if(Scoreholder[i-1][2] < 3){
					ScoreStorage[i-1][1] = ScoreStorage[i-1][1] + Scoreholder[i-1][1];
				}
				
				if (ScoreStorage[i-1][2] < 3){

				System.out.println ("");
				System.out.println (userNames[i-1][0] + " have collected " + ScoreStorage[i-1][1] + " brains in total for this turn");
				System.out.println ("");
				System.out.println (userNames[i-1][0] + " have been shot " + ScoreStorage[i-1][2] + " times");
				System.out.println ("");
				System.out.println ("******************************************************************************************************");
				System.out.println ("");
				System.out.println ("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println ("######################################################################################################");
				System.out.println ("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				}
		
				if (ScoreStorage[i-1][2] >= 3){
				System.out.println ("");
				System.out.println("You got shot three times by a human scum....Your dead again, you lost all the brains for this turn");
				System.out.println ("");
				System.out.println ("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					
					ScoreStorage[i-1][1] = 0;
					ScoreStorage[i-1][2] = 0;
					menu = 4;
					//set so that its next players turn
				}
				turn++;
			}
				if(menu == 2){ //sent here if player chooise to end turn
					if(i==userNames.length){//this is so that the counter resets if the all players go through one turn
						MasterBrainHolder[i-1][1] = MasterBrainHolder[i-1][1] + ScoreStorage[i-1][1];
						ScoreStorage[i-1][2] = 0;
						ScoreStorage[i-1][1] = 0;
						i=1;
						}
					else{
						ScoreStorage[i-1][2] = 0;
						MasterBrainHolder[i-1][1] = MasterBrainHolder[i-1][1] + ScoreStorage[i-1][1];
						ScoreStorage[i-1][1] = 0;	
						i++;
						turn = 0;
						}
						//set so that its next players turn
					}
				
				if(menu == 4){ //sent here if player is shot 3 times
					if(i==userNames.length){//this is so that the counter resets if the all players go through one turn
						i=1;
						turn = 0;
						}
					else{
						ScoreStorage[i-1][2] = 0;
						ScoreStorage[i-1][1] = 0;	
						i++;
						turn = 0;
						}
						//set so that its next players turn
					}
				if(menu == 3){
					for(int ct = 1; ct-1<userNames.length; ct++){
					System.out.print(userNames[ct-1][0] + "Haves: ");
					System.out.println(MasterBrainHolder[ct-1][1] + " amount for brain saved");
					System.out.println ("");
					}
				}
		
		System.out.println ("");
		System.out.print ("Your chooise: ");  //Added in to tell user who is choosing
		System.out.println(userNames[i-1][0]);  //Calling in display current user's turn name to be displayed 
		menu = menusystem();//display options again
		}
		return (DiceCheck_Holder);
	}
	public static int menusystem(){//menu options to display to user
		Scanner menusys = new Scanner(System.in);
		System.out.println("| 1 |     	Roll	    |");
		System.out.println("| 2 |    finish turn    |");
		System.out.println("| 3 |   Display brains  |");
		System.out.println ("");
		int input = menusys.nextInt();
		return(input);
	}
	public static String[] green_random(){//generate green dice
		String[] green = {"brain","foot","shotgun","brain","brain","foot"};
		Random diceSelector = new Random();
		int equals = diceSelector.nextInt(green.length);
		String[] GreenOutput = new String [1];
		GreenOutput [0] = green[equals];
		return (GreenOutput);
	}
	public static String[] yellow_random(){//generate yellow dice
		String[] yellow = {"brain","foot","shotgun","shotgun","brain","foot"};
		Random diceSelector = new Random();
		int equals = diceSelector.nextInt(yellow.length);
		String[] YellowOutput = new String [1];
		YellowOutput [0] = yellow[equals];
		return (YellowOutput);
	}
	public static String[] red_random(){//generate red dice 
		String[] red = {"brain","foot","shotgun","foot","shotgun","shotgun"};
		Random diceSelector = new Random();
		int equals = diceSelector.nextInt(red.length);
		String[] RedOutput = new String [1];
		RedOutput [0] = red[equals];
		return (RedOutput);
	}
	public static String[] nameinput(int x){//method to input user names
		String [] b = new String [x];
		for(int i=0; i < x; i++){
			Scanner in = new Scanner(System.in);
			System.out.print("input the name of player ");
			System.out.print(i+1+": ");
			b[i]=in.nextLine();
		}
		return(b);//returned to String[] username
	}
	public static void displaynames(String[][] b,int z){//method to display users names
		for(int i=0; i < z; i++){
			System.out.print("Player " + (i+1) + " is : " + b[i][0] );
			System.out.println("");
			System.out.println("");
		}
		//displays all player names 
	}
	public static int nousers(){//method for amount of users
		Scanner in = new Scanner(System.in);
		System.out.println("");
		System.out.print("input number user of users: ");
		int users = in.nextInt();
		return(users);//sets x as the number of players
	}
	public static String[] dice_random(int count){ //Method for the generating three random coloured dice
		String[] dice = {"Red","Red","Red","Yellow","Yellow","Yellow","Yellow","Green","Green","Green","Green","Green","Green"}; //amount of each colour dice
		int p = 0;
		String[] RandomDice = new String [dice.length];
		
		while(p <dice.length){
			Random DiceRoll = new Random();
			int RollNo = DiceRoll.nextInt(dice.length);
			RandomDice[p] = dice[RollNo];
			count--;
			p++;
		}
		return(RandomDice);
	}
	public static void DisplayDice(String [] NewDice, int y){  //Method for displaying the dice already generated
		System.out.print("Dice colour are: ");	
		while(y>=0){
			System.out.print (NewDice[y]);
			System.out.print(", ");
			y--;
		}
		System.out.println("");
	}
	public static int[] DiceColour_Check(String [] NewDice, int y){ //Method for checking what Colour dice have been generated
		int [] CheckDice = new int [NewDice.length];
		String Green = "Green";  		// Green = 1
		String Yellow = "Yellow";		// Yellow = 2
		String Red = "Red";				// Red = 3
		
		while(y>= 0){
			if(NewDice[y] == Green){
			CheckDice[y] = 1;
			}
			if(NewDice[y] == Yellow){
			CheckDice[y] = 2;
			}
			if(NewDice[y] == Red){
			CheckDice[y] = 3;
			}
			y--;
		}
		return (CheckDice);
	}
	public static int[][] DiceColour_Match(int [] DiceCheck_Holder, int y ,String[][] userNames,int i, int x){ // Method to match colour dice to the different colour dice generators and display
		// Green = 1
		// Yellow = 2
		// Red = 3
		String[] MatchedDice = new String [3];
		
		String[] GreenOutput = new String [1];
		String[] YellowOutput = new String [1];
		String[] RedOutput = new String [1];
		
		String brain = "brain";		// Stored in ScoreKeep[i-1][1]
		String shotgun = "shotgun";	//Stored in ScoreKeep[i-1][2]
		String foot = "foot";	//Stored in ScoreKeep[i-1][3]
		
		
		int[][] ScoreKeep = new int[x][4];
		
		while(y>= 0){
			if(DiceCheck_Holder[y] == 1){
				GreenOutput = green_random();
				MatchedDice[y] = GreenOutput[0];
				System.out.println (userNames[i-1][0] +" "+ MatchedDice[y]);
			}
			if(DiceCheck_Holder[y] == 2){
				YellowOutput = yellow_random();
				MatchedDice[y] = YellowOutput[0];
				System.out.println (userNames[i-1][0] +" "+ MatchedDice[y]);
			}
			if(DiceCheck_Holder[y] == 3){
				RedOutput = red_random();
				MatchedDice[y] = RedOutput[0];
				System.out.println (userNames[i-1][0] +" "+ MatchedDice[y]);
			}
			if(MatchedDice[y] == brain){
				ScoreKeep[i-1][1] = ScoreKeep[i-1][1] + 1;
			}
			if(MatchedDice[y] == shotgun){
				ScoreKeep[i-1][2] = ScoreKeep[i-1][2] + 1;
			}
			if(MatchedDice[y] == foot){
				ScoreKeep[i-1][3] = ScoreKeep[i-1][3] + 1;
			}
			
			y--;
		}

		return (ScoreKeep);
	}
	public static void gamefinished(){//Method if someone wins and option to play again
		System.out.println("you have won");
		System.exit(0);
	}
}
