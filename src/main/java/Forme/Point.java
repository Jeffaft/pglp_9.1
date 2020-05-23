package Forme;

public class Point {
	private int x;
	private int y;
	
	
	public Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," +y+")";
	}
	public static Point fromString(String str) {
		String[] newStr = str.split("[(,)]+");
		int a = 0;
		int b = 0;
		try {
			if(newStr.length!= 3)
				return null;
			a = Integer.parseInt(newStr[1]);
			b = Integer.parseInt(newStr[2]);
		}
		catch (NumberFormatException e) {
			return null;
		}
		return new Point(a,b);
	}
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
