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
	    
	    thread = new AppThread();
	    
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
	
	Container c = getContentPane();
	MyPanel panel = new MyPanel();
	JDialog dialog = new JDialog();
	AppThread thread;

	public static void main(String[] args) {
		new App();
	}
}
