package Suchi;

import java.awt.AWTException;

public abstract class Recette {
	
	protected static Ia ia;
	
	protected Recette() throws AWTException{
		
		ia = new Ia();
		
	}

}
