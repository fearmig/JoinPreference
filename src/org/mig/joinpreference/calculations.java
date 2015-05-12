package org.mig.joinpreference;

//This class was created to do any calculations made.
public class Calculations {
	
	private static int trueMax;
	private final JoinPreference main;
	
	//constructor
	public Calculations(JoinPreference p){
		this.main = p;
	}
	
	//set the Max amount of players allowed into the server
	public void setTrueMaxPlayers(){
		trueMax = main.getConfig().getInt("MaxPlayers");
	}
	
	//return if the server is full of players.
	public boolean isFull(){
		setTrueMaxPlayers();
		if(trueMax <= PlayerHandler.players.size()){
			return true;
		}
		else{
			return false;
		}
	}
	
	
}
