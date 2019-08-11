package HW9_simplePaint;

import java.awt.Color;

public class Shape {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;
	private String username;

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		if (color != null)
			this.color = color;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username != null)
			this.username = username;
	}

	public Shape(int x1, int y1, int x2, int y2, Color color, String username) {
		setX1(x1);
		setY1(y1);
		setX2(x2);
		setY2(y2);
		setColor(color);
		setUsername(username);
	}

	public Shape(int x1, int y1, Color color, String username) {
		setX1(x1);
		setY1(y1);
		setColor(color);
		setUsername(username);
	}
}
