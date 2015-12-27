import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.*;


class GameFlow extends JPanel {

	private Snake current;
	private Shape fon = new Polygon();
	private Shape fon1 = new Polygon();
	private Shape h1 = new Polygon();
	private Shape h2 = new Polygon();
	private Shape h3 = new Polygon();
	private Shape bor1 = new Polygon();
	private Shape bor2 = new Polygon();
	private Shape bor3 = new Polygon();
	private Shape bor4 = new Polygon();
	private Point fr = new Point();
	private int scores =0;
	Random n = new Random();
	JLabel scoresTab = new JLabel();
	
	
	
	public Snake getCurrent() {
		return current;
	}
	
	
	
	public int getScores() {
		return scores;
	}

	public void setCurrent(Snake current) {
		this.current = current;
	}
	
	public void setScores(int sc) {
		this.scores = sc;
	}
	
	public GameFlow() {
		super(true);
		setLayout(null);
		Dimension d = new Dimension (500,500);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        setSize(d);
        setMap();
        setFruit();
       
       	setVisible(true);
       	
       	
       	
       	current = new Snake(20,20,5);
       	
       	
       	
		 Thread g = new Thread( new Runnable(){

				@Override
				public void run() {
					while(flow() ==true){
					try {
						Thread.sleep(current.getSpeed());
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					
				}
	        	
	        });
	        g.start();
		
	}
	
	
	
	private void setMap(){
		int arr1[] = {20, 100, 100, 250, 250, 20};
		int[] arr2 = {180, 180, 200, 200, 220, 220};
		int arr3[] = {300, 340, 340, 300, 300, 320,320,300,300};
		int[] arr4 = {80, 80, 180, 180, 160, 160,140,140,110};
		
		int arr5[] = {300, 300, 320, 200, 200, 220,220,200,200};
		int[] arr6 = {360, 320, 320, 180, 160, 160,140,140,110};
		
		
		Rectangle c = new Rectangle(0,0,500,500);
		Rectangle c1 = new Rectangle(500,0,190,500);
		Polygon a1 = new Polygon(arr1,arr2,6);
		Polygon a2 = new Polygon(arr3,arr4,9);
		Polygon a3 = new Polygon(arr5,arr6,8);
		
		Rectangle b1 = new Rectangle(0,0,10,this.getHeight());
		Rectangle b2 = new Rectangle(0, this.getHeight()-10,this.getWidth(),11);
		Rectangle b3 = new Rectangle(this.getWidth()-2,0,11,this.getHeight()+1);
		Rectangle b4 = new Rectangle(0,0,getWidth()+1,11);
		fon = c;
		fon1 = c1;
		h1 = a1;
		h2 = a2;
		h3 = a3;
		
		bor1 = b1;
		bor2 = b2;
		bor3 = b3;
		bor4 = b4;
		
	}
	
	public void paint(Graphics h){
		Graphics2D e = (Graphics2D)h.create();
		e.setColor(Color.white);
		e.draw(fon);
		e.fill(fon);
		e.setColor(Color.CYAN);
		e.draw(fon1);
		e.fill(fon1);
		e.setColor(Color.gray);
		e.draw(h1);
		e.fill(h1);
		e.draw(h2);
		e.fill(h2);
		e.draw(h3);
		e.fill(h3);
		e.draw(bor1);
		e.fill(bor1);
		e.draw(bor2);
		e.fill(bor2);
		e.draw(bor3);
		e.fill(bor3);
		e.draw(bor4);
		e.fill(bor4);
		Font f1 = new Font(e.getFont().getFamily(), Font.BOLD, 17);
		e.setColor(Color.BLACK);
		e.setFont(f1);
		e.drawString("Набрано очков: " + scores, 520, 30);
		e.setColor(Color.red);
		e.fillArc(fr.x, fr.y, 10, 10, 0, 360);
		
		current.paint(e);
				
	}

	public GameFlow(Snake current) {
		this.current = current;
	}
	
	private void setFruit(){
		int x = n.nextInt(500);
		int y = n.nextInt(500);
		System.out.println(x+" " +y);
		Point g = new Point(x,y);
				
		if(h1.contains(g)|| h2.contains(g) || h3.contains(g) || bor1.contains(g) || bor2.contains(g) || bor3.contains(g) ||bor4.contains(g)){setFruit();}
		
		else{
			fr=g;
		}
	}

	public void parseKey(KeyEvent evt){
		switch(evt.getKeyCode()){
		case KeyEvent.VK_LEFT:  current.setVector(Direction.LEFT); break;
		case KeyEvent.VK_RIGHT:  current.setVector(Direction.RIGHT); break;
		case KeyEvent.VK_UP:  current.setVector(Direction.TOP); break;
		case KeyEvent.VK_DOWN:  current.setVector(Direction.BOTTOM); break;
		}
	}
	
	public boolean flow(){
		
		if(h1.contains(current.getLast())||h2.contains(current.getLast())||h3.contains(current.getLast()) || bor1.contains(current.getLast()) || bor2.contains(current.getLast()) || bor3.contains(current.getLast()) ||bor4.contains(current.getLast())){
			current.setVector(Direction.DEAD);
			error();
			current.getBody().remove(current.getBody().size()-1);
			return false;
		}
		
		
		
		if(current.getLast().distance(fr)<10 ){current.increase(); setFruit(); scores++; if(current.getSpeed()>50){current.setSpeed(current.getSpeed()-10);}}
		current.moving();
		this.repaint();
		return true;
		
	}
	
	public void error(){
		new JDialog(){
			{
				this.setVisible(true);
				this.setSize(new Dimension(400,200));
				this.setBackground(Color.GRAY);
				
				
			}
			public void paint(Graphics h){
				Font f = h.getFont();
				Font f1 = new Font(f.getFamily(), Font.BOLD, 27);
				h.setFont(f1);
				h.setColor(Color.BLACK);
				h.fillRect(0, 0, 400, 200);
				h.setColor(Color.WHITE);
				h.drawString("Лузер!", 100, 100);
			}
		};
	}
	
	public static void main(String[] args) {
		GameFlow g = new GameFlow();

	}

}
