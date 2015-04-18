

public class Dot {
	
	public int x;
	public int y;


	public Dot(double x, double y) {
		this.x = (int)x;
		this.y = (int)y;
	//	System.out.println("x : " + this.x + ", y : " + this.y);
		translate();
	//	System.out.println("x : " + this.x + ", y : " + this.y);
	}
	
	public Dot(double x, double y, Boolean bol) {
		
		this.x = (int)x; 
		this.y = (int)y;
		
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void translate(){
		
		
	//	System.out.println(IA.xOr + " " + IA.yOr);
		this.x = this.x + IA.xOr;
		this.y = this.y + IA.yOr;
		
	}
	
}


