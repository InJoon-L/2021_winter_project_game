package Game_tetris;
//panel의 객체가 App의 panel과 달라 내려가질 않음
//결국 내부클래스로 옮겨서 진행
public class AppThread extends Thread {
	MyPanel panel = new MyPanel();
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
