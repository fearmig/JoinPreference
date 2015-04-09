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
	
	@SuppressWarnings("deprecation")
	public boolean isFull(){
		setTrueMaxPlayers();
		if(trueMax <= main.getServer().getOnlinePlayers().length){
			return true;
		}
		else{
			return false;
		}
	}
	
	
}
