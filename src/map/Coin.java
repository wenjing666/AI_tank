package map;

import net.sf.json.JSONObject;

public class Coin {
	private int x;
	private int y;
	private int point;
	
	public Coin(JSONObject object) {
		this.x = object.getInt("x");
		this.y = object.getInt("y");
		this.point = object.getInt("point");
		System.out.printf("star x %d, y %d\n point %d\n", this.x, this.y, this.point);
	}
}
