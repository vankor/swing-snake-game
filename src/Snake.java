import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;


public class Snake {

	private Direction vector;
	private int len;
	private int speed = 160;
	private Point last;
	private ArrayList <Point> body = new ArrayList <Point>();
	
	public Point getLast() {
		return last;
	}
	
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Direction getVector() {
		return vector;
	}

	public void setVector(Direction vector) {
		this.vector = vector;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	Snake(int nx, int ny, int l){
		this.vector = Direction.BOTTOM;
		len = l;
		int x = nx;
		int y = ny;
		for(int i = 0; i < l; i++){
		this.body.add(new Point(x,y));
		x = x+10;
		}
		last=body.get(body.size()-1);
	}
	
	public ArrayList <Point> getBody(){
		return body;
	}
	
	public void paint(Graphics2D g){
		
		for(Point k : body){
			g.setColor(Color.yellow);
			g.fillArc(k.x, k.y, 10, 10, 0, 360);
			g.setColor(Color.magenta);
            g.drawArc(k.x, k.y, 10, 10, 0, 360);
		}
	}
	
	
	public void moving(){
		if(vector == Direction.LEFT){
			body.remove(0);
			Point temp = new Point(last.x -10, last.y);
			last=temp;
			body.add(temp);
		}
		
		if(vector == Direction.RIGHT){
			body.remove(0);
			Point temp = new Point(last.x +10, last.y);
			last=temp;
			body.add(temp);
		}
		
		if(vector == Direction.TOP){
			body.remove(0);
			Point temp = new Point(last.x, last.y-10);
			last=temp;
			body.add(temp);
		}
		
		if(vector == Direction.BOTTOM){
			body.remove(0);
			Point temp = new Point(last.x, last.y+10);
			last=temp;
			body.add(temp);
		}
	}
	
	
	public void increase(){
		
		body.add(0, new Point(body.get(0).x,body.get(0).y));
	}
	
	public boolean destroy(){
		for(int i =0; i<body.size(); i++){
		if(last.equals(body.get(i))){
			return true;
		}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Snake s = new Snake(2,2,4);
		s.paint(null);
	}
	
	

}
