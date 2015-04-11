package org.mig.joinpreference;

public class calculations {
	private static int trueMax;
	private final joinPreference main;
	
	public calculations(joinPreference p){
		this.main = p;
	}
	
	public void setTrueMaxPlayers(){
		trueMax = main.getConfig().getInt("MaxPlayers");
	}
	
	public boolean isFull(){
		setTrueMaxPlayers();
		if(trueMax <= playerHandler.players.size()){
			return true;
		}
		else{
			return false;
		}
	}
	
	
}
