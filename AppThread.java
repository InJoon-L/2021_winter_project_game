package Game_tetris;
//panel�� ��ü�� App�� panel�� �޶� �������� ����
//�ᱹ ����Ŭ������ �Űܼ� ����
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
