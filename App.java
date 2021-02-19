package Game_tetris;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class App extends JFrame{
	App() {
	    setTitle("테트리스");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(null);
	    panel.setSize(720,600);
	    
	    add(panel);
	    
	    thread = new MyThread();
	    
	    dialog.setTitle("점수");
	    dialog.setSize(250, 190);
	    dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 30));;
	    
	    panel.addKeyListener(new KeyAdapter() {
	    	public void keyPressed(KeyEvent e){
	            int keyCode = e.getKeyCode();
	                        
	            if(keyCode == KeyEvent.VK_UP)
	                panel.moveUp();
	            if(keyCode == KeyEvent.VK_DOWN)
	                panel.moveDown();
	            if(keyCode == KeyEvent.VK_LEFT)
	                panel.moveLeft();
	            if(keyCode == KeyEvent.VK_RIGHT)
	                panel.moveRight();
	        }
	    });
	    
	    button.addActionListener(new ActionListener(){
	         public void actionPerformed(ActionEvent e){
	            panel.limit = false;
	            for(int y=0; y<19;y++)
	               for(int x=1; x<12; x++)
	                  panel.gameBoard[y][x] =0 ;
	            panel.score = 0;
	            panel.width = 100; 
	            panel.height = 0;
	        }
	    });
	    
	    panel.setBackground(Color.WHITE);
	    setSize(530,520);
	    setVisible(true);
	    
	    // 화면 중앙 정렬
	    Dimension frameSize = this.getSize();
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
	    dialog.setLocation((screenSize.width - frameSize.width)/2 + 220, (screenSize.height - frameSize.height)/2 +220);

	    panel.requestFocus(true);
	    thread.start();
	}
	
	class MyThread extends Thread{
		public void run() {
			while(true) {
				try {
					sleep(500);
					if(panel.limit == false)
						panel.down();
				}catch(InterruptedException e) {
					return;
				}
			}
		}
	}
	
	Container c = getContentPane();
	MyPanel panel = new MyPanel();
	JDialog dialog = new JDialog();
	MyThread thread;
	JButton button = new JButton("재도전");

	public static void main(String[] args) {
		new App();
	}
}
