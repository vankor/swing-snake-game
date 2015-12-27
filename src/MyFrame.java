import java.awt.*;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;;

public class MyFrame extends JFrame{
	
	JButton start;
	JDialog game;
	GameFlow g;
	MyFrame(){
		super();
		setLayout(null);
		this.setSize(new Dimension(400,400));
		this.setVisible(true);
				
		start = new JButton("Start Game");
		start.setBounds(100,100,100,50);
		this.add(start);
		
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
			startSnake();					
			}
		});
 }
	
	public void startSnake(){
		game  = new JDialog((JFrame)null,"Игра");
		game.setSize(new Dimension(698,550));
		
		
		g = new GameFlow();
		game.add(g);
		
		Button b = new Button("Начать заново");
		//b.setBounds(530, 120, 100, 46);
		//g.add(b);
		
		b.addActionListener(new ActionListener(){
	       	  public void actionPerformed(ActionEvent ae)
	       	  {
	       	 
	       	   g.setCurrent(new Snake(20,20,5));
	       	   g.setScores(0);	       	  }
	       	 });
		
		game.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent evt){
				g.parseKey(evt);
			}
		});
		
		
		
		
		game.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		MyFrame n = new MyFrame();

	}

}
