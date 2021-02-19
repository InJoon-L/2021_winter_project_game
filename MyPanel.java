package Game_tetris;

import java.awt.*;

import javax.swing.*;

public class MyPanel extends JPanel{
	
	public MyPanel() {
		requestFocus(true);
		lb1.setLocation(360,145);
		lb2.setLocation(353,120);
		lb1.setFont(new Font("�������",Font.PLAIN,15));
	    lb2.setText("��  ��");
	    lb2.setFont(new Font("�������",Font.PLAIN,15));
	    lb1.setText(Integer.toString(score*100));
	    add(lb1);
		add(lb2);
	}
	
	static int blockSize = 20;
	private Color[] colorSet = {Color.BLUE, Color.CYAN, Color.GREEN,
			Color.PINK, Color.DARK_GRAY, Color.MAGENTA, Color.RED};
	int score = 0;
	int end = 0;
	int rotation = 0;
	int width = 100, height = 0;
	int x[] = new int[4], y[] = new int[4];
	int random = 0, random2 = (int)(Math.random()*7);
	boolean limit = false;
	JLabel  lb1 = new JLabel();
	JLabel  lb2 = new JLabel();
	
	int blocks[][][][] = { //��� ������ �����͸� ����
			{
				// ��
				// ����
				{ { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 1, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } } 
			},
			{

				//   ��
				// ����
				{ { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } } 
			},
			{
				// ���
				// ���
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } } 
			},
			{
				// �����
				{ { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } } 
			},
			{
				//  ��
				// ����
				{ { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } 
			},			
			{
				// ���
				//  ���
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } 
			},			
			{
				//  ���
				// ���
				{ { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } } 
			} 
	};
	
	int[][] gameBoard = {
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
	
	@Override
	public void paintComponent(Graphics g) {
		int checkNum = 0, checkNum2 = 0;
		super.paintComponent(g);
		
		g.setColor(colorSet[random2]); // ������
		
		// ���� ���� ���� ���
        nextBlockLook(g);
        
        // ���� õ�忡 ������ ���� ����
        gameOverCheck();
        
        // �� ���� ��� ������� ä���� ��� ��ϵ� ����(ä���������� ��� ��� ����������)
        removeBlock(checkNum, checkNum2, g);
        
        // ����� ���� �����ϸ� ���->������ ��ȯ(�������� ��� �ʱ�ȭ)
        changeWall();
        
        // ������ ����
        makeWall(g);
        
        // �׵θ� ����
        makeBorder(g);
        
		if(end == 1) {
			random2 = (int)(Math.random()*7);
			end = 0;
		}
	}
	
	public void nextBlockLook(Graphics g) {
		for(int a = 0; a<4 ;a++){
            for(int b = 0; b<4;b++){
               if(blocks[random2][0][a][b] == 1){
                  g.fill3DRect(b*blockSize+120, a*blockSize, blockSize, blockSize, true);
               }
            }
        }
	}
	
	public boolean gameOverCheck(){
        for(int x=1;x<12;x++)
            if(gameBoard[2][x]==1){
               limit = true;
               lb1.setText("����");
               lb2.setText("��");
            }
        return limit;
    }

	public void blockDown(int checkNum, Graphics g){
        for(int j = 0; j<4 ;j++){
             for(int k = 0; k<4;k++){
            	 
                if(blocks[random][rotation][j][k] == 1){
                   x[checkNum] = ((k*blockSize)+width)/blockSize; y[checkNum] = ((j*blockSize)+height)/blockSize;//x,y[0][1][2][3]�� ��ǥ 4�� ����
                   g.fill3DRect(x[checkNum]*blockSize+20, y[checkNum]*blockSize+60, blockSize, blockSize, true); 
                   checkNum++;
                }
             }
          }
     }	
	
	public void removeBlock(int checkNum, int checkNum2, Graphics g){
        for(int y =0;y<19;y++){
            for(int x =1; x<12 ; x++){
               if(gameBoard[y][x] == 1){
                  checkNum2++;
               }
            }
            if(checkNum2 == 11){
               for(int i=y;i>1;i--)
                  for(int j=1;j<13;j++){
                     gameBoard[i][j] = 0;
                     gameBoard[i][j] = gameBoard[i-1][j];
                  }
              score++;
            }else{
               blockDown(checkNum,g); // �� ���� ��� ������� ä������ ���� ���� ����� ���������� ��
            }
            checkNum2 = 0;
         }
    }
	
	public void makeWall(Graphics g){
        g.setColor(Color.GRAY); 
        for(int y=0; y<19;y++){
            for(int x=1; x<12; x++){
                if(gameBoard[y][x]== 1){
                    g.fill3DRect(x*blockSize+20, y*blockSize+60, blockSize, blockSize, true);
                }
            }
        }
    }
	
	// �������� ����� ���� �Ǵ��� �˻�
    // ���� �Ǹ� width=100, height=0 ���� ��� �ʱ�ȭ, rotation�� �ʱ�ȭ 
    public void changeWall(){
       try{
       for(int z = 0; z<4 ; z++)
            if(gameBoard[y[z]+1][x[z]] == 1)
                for (int j= 0; j<4;j++){ 
                    gameBoard[y[j]][x[j]] = 1;
                    width =100; 
                    height =0;
                    end = 1;
                    rotation = 0;
                    random = random2;
                }
        }catch(ArrayIndexOutOfBoundsException e) { 
    	   return;
        }
        for(int i=0; i<4 ; i++)
             System.out.print("("+y[i]+","+x[i]+")");
        System.out.println();
    }
    
    // ���� ���� �浹�ϸ� �������̵���
    public int collision_LEFT(){
        for(int i=0; i<4; i++){
            if(gameBoard[y[i]][x[i]-1] == 1)  // �浹�� 1 ��ȯ
            return 1;
        }
        return 0; // �浹���� ������ 0 ��ȯ
    }
    
    // ������ ���� �浹�ϸ� �� �����̵���
    public int collision_RIGHT(){
        for(int i=0; i<4; i++){
            if(gameBoard[y[i]][x[i]+1] == 1)   // �浹�� 1��ȯ
                return 1;
        }
        return 0; // �浹���� ������ 0��ȯ
    }
    
    // x,y�� ���� ȸ�� ������ ������ǥ�� ��� ����صΰ�, ���� �������̳� ���� X��ǥ1,2ĭ �ȿ� ���� ������ �׸�ŭ ������ Ȥ�� �������� �о ��ġ
    public void rotationCheck(){
        // x,y�� ���� ȸ�� ������ ������ǥ�� ��� ����صΰ�, �ؿ� �������� �� ������ǥ�� ���� ���� ����� �Ǵ�
        int checkNum2=0;
        for(int j = 0; j<4 ;j++){
            for(int k = 0; k<4;k++){
                int rotation2 = (rotation%4)+1 ;
                if(rotation2 == 4)
                    rotation2 = 0;
                if(blocks[random][rotation2][j][k] == 1){
                    x[checkNum2] = ((k*blockSize)+width)/blockSize; y[checkNum2] = ((j*blockSize)+height)/blockSize;
                    checkNum2++;
                }    
            }
        }
        // x,y�� ����� ��ǥ�� �̿�
        int check = 0;
        int blank =0;
        //���� ��
        if(gameBoard[y[0]][x[0]] == 1 || (random == 6 && gameBoard[y[2]][x[2]] == 1) || (random == 1 && gameBoard[y[1]][x[1]] ==1 )){
            check = 1; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� check=1�� ǥ����           

            System.out.println("chk1");
            if(random == 3){ // ���ڸ����� ��� ȸ���� ������ �ִ� ������ ������ ȸ������
                for(int i=1;i<5;i++)
                    if(gameBoard[y[0]][x[0]+i] == 0)
                        blank++;
                if(blank < 4)
                    check = 4;
                System.out.println(blank);
            }else{ // �� ���� ��� ȸ���� ������ ���� ������ ������ ȸ�� ����
                for(int i=1; i<4;i++)
                    if(gameBoard[y[0]][x[0]+i] == 0)
                        blank++;
                if(blank <3)
                    check = 4;
                System.out.println("blank2");
                System.out.println(blank);
            }
        //������ ��
        }else if(gameBoard[y[2]][x[2]] == 1){
            check = 2; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� check=2�� ǥ����  
            System.out.println("chk2");
           
            for(int i=1; i<5;i++)
                if(gameBoard[y[2]][x[2]-i] == 0)
                    blank ++;
            if(blank < 4)
                check = 4;
            System.out.println("blank2");
            System.out.println(blank);
        }else if(gameBoard[y[3]][x[3]] == 1){
            check = 3; // ���� ���� ȸ���� ������ ��ġ�� ���� ��ģ�ٸ� check=3�� ǥ����    
            System.out.println("chk3");
            for(int i=0; i<5;i++)
                if(gameBoard[y[3]][x[3]-i] == 0)
                    blank ++;
            if(blank < 4)
                check = 4;
            System.out.println(blank);
        }
        if(check == 1){ // check = 1(���� ȸ���� ������ ��ġ�� ���� �ߺ��Ǹ�)�� width(����)�� 30�̵�
            width += blockSize;
            rotation++;
            rotation = rotation%4;
        }else if (check ==2){
            width -= blockSize*2;
            rotation++;
            rotation = rotation%4;
        }else if (check ==3){
            width -= blockSize;
            rotation++;
            rotation = rotation%4;
        }else if(check == 4){
            System.out.println("ban");
        }else{
            rotation++;
            rotation = rotation%4;
        }
    }
    
    public void makeBorder(Graphics g){
        g.setColor(Color.BLACK);
        
        g.draw3DRect(28, 70, 5, 375,true); // ���
        g.draw3DRect(265, 70, 5, 375, true); // ���
        g.draw3DRect(15, 445, 270, 5,true); // �ٴ�
        g.draw3DRect(15, 65, 270, 5, true); // õ��
    }
    
    void down(){
        height +=blockSize;
        repaint();
    }
    
    void moveUp(){
        rotationCheck();
            if(limit == false)
                repaint();
    }
    
    void moveDown(){
        if(limit == false){
            height += blockSize;
            repaint();
        }
    }
    
    void moveLeft(){
        int sel = collision_LEFT();// sel�� 1�̸� �浹, 0�̸� �浹X
        if(sel == 0 && limit == false){
            width -= blockSize;
            repaint();
        }
    }
    
    void moveRight(){
        int sel = collision_RIGHT();// sel�� 1�̸� �浹, 0�̸� �浹X
        if(sel == 0 && limit == false){ 
            width += blockSize;
            repaint();
        }
     }
}
