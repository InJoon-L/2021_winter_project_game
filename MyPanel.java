package Game_tetris;

import java.awt.*;

import javax.swing.*;

public class MyPanel extends JPanel{
	
	public MyPanel() {
		requestFocus(true);
		lb1.setLocation(360,145);
		lb2.setLocation(353,120);
		lb1.setFont(new Font("나눔고딕",Font.PLAIN,15));
	    lb2.setText("점  수");
	    lb2.setFont(new Font("나눔고딕",Font.PLAIN,15));
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
	
	int blocks[][][][] = { //모든 도형의 데이터를 저장
			{
				// ■
				// ■■■
				{ { 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 1, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } } 
			},
			{

				//   ■
				// ■■■
				{ { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } } 
			},
			{
				// ■■
				// ■■
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } } 
			},
			{
				// ■■■■
				{ { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } } 
			},
			{
				//  ■
				// ■■■
				{ { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 1, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } 
			},			
			{
				// ■■
				//  ■■
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } },
				{ { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } 
			},			
			{
				//  ■■
				// ■■
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
		
		g.setColor(colorSet[random2]); // 도형색
		
		// 다음 나올 도형 출력
        nextBlockLook(g);
        
        // 벽이 천장에 닿으면 게임 오버
        gameOverCheck();
        
        // 한 행이 모두 블록으로 채워진 경우 블록들 제거(채워지지않은 경우 블록 떨어지도록)
        removeBlock(checkNum, checkNum2, g);
        
        // 블록이 벽에 착지하면 블록->벽으로 변환(떨어지는 블록 초기화)
        changeWall();
        
        // 벽들을 생성
        makeWall(g);
        
        // 테두리 생성
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
               lb1.setText("게임");
               lb2.setText("끝");
            }
        return limit;
    }

	public void blockDown(int checkNum, Graphics g){
        for(int j = 0; j<4 ;j++){
             for(int k = 0; k<4;k++){
            	 
                if(blocks[random][rotation][j][k] == 1){
                   x[checkNum] = ((k*blockSize)+width)/blockSize; y[checkNum] = ((j*blockSize)+height)/blockSize;//x,y[0][1][2][3]에 좌표 4개 저장
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
               blockDown(checkNum,g); // 한 행이 모두 블록으로 채워지지 않을 때만 블록이 내려가도록 함
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
	
	// 떨어지던 블록이 벽이 되는지 검사
    // 벽이 되면 width=100, height=0 으로 블록 초기화, rotation도 초기화 
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
    
    // 왼쪽 벽에 충돌하면 못움직이도록
    public int collision_LEFT(){
        for(int i=0; i<4; i++){
            if(gameBoard[y[i]][x[i]-1] == 1)  // 충돌시 1 반환
            return 1;
        }
        return 0; // 충돌하지 않으면 0 반환
    }
    
    // 오른쪽 벽에 충돌하면 못 움직이도록
    public int collision_RIGHT(){
        for(int i=0; i<4; i++){
            if(gameBoard[y[i]][x[i]+1] == 1)   // 충돌시 1반환
                return 1;
        }
        return 0; // 충돌하지 않으면 0반환
    }
    
    // x,y에 다음 회전 도형의 절대좌표를 모두 기록해두고, 만약 오른쪽이나 왼쪽 X좌표1,2칸 안에 벽이 있으면 그만큼 오른쪽 혹은 왼쪽으로 밀어서 배치
    public void rotationCheck(){
        // x,y에 다음 회전 도형의 절대좌표를 모두 기록해두고, 밑에 구문에서 그 절대좌표의 값이 벽에 닿는지 판단
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
        // x,y에 저장된 좌표를 이용
        int check = 0;
        int blank =0;
        //왼쪽 벽
        if(gameBoard[y[0]][x[0]] == 1 || (random == 6 && gameBoard[y[2]][x[2]] == 1) || (random == 1 && gameBoard[y[1]][x[1]] ==1 )){
            check = 1; // 만약 다음 회전한 도형의 위치가 벽과 겹친다면 check=1로 표시함           

            System.out.println("chk1");
            if(random == 3){ // 일자막대의 경우 회전할 여유가 있는 공백이 없으면 회전막음
                for(int i=1;i<5;i++)
                    if(gameBoard[y[0]][x[0]+i] == 0)
                        blank++;
                if(blank < 4)
                    check = 4;
                System.out.println(blank);
            }else{ // 그 외의 경우 회전할 여유가 없는 공백이 없으면 회전 막음
                for(int i=1; i<4;i++)
                    if(gameBoard[y[0]][x[0]+i] == 0)
                        blank++;
                if(blank <3)
                    check = 4;
                System.out.println("blank2");
                System.out.println(blank);
            }
        //오른쪽 벽
        }else if(gameBoard[y[2]][x[2]] == 1){
            check = 2; // 만약 다음 회전한 도형의 위치가 벽과 겹친다면 check=2로 표시함  
            System.out.println("chk2");
           
            for(int i=1; i<5;i++)
                if(gameBoard[y[2]][x[2]-i] == 0)
                    blank ++;
            if(blank < 4)
                check = 4;
            System.out.println("blank2");
            System.out.println(blank);
        }else if(gameBoard[y[3]][x[3]] == 1){
            check = 3; // 만약 다음 회전한 도형의 위치가 벽과 겹친다면 check=3로 표시함    
            System.out.println("chk3");
            for(int i=0; i<5;i++)
                if(gameBoard[y[3]][x[3]-i] == 0)
                    blank ++;
            if(blank < 4)
                check = 4;
            System.out.println(blank);
        }
        if(check == 1){ // check = 1(다음 회전한 도형의 위치가 벽과 중복되면)면 width(가로)로 30이동
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
        
        g.draw3DRect(28, 70, 5, 375,true); // 기둥
        g.draw3DRect(265, 70, 5, 375, true); // 기둥
        g.draw3DRect(15, 445, 270, 5,true); // 바닥
        g.draw3DRect(15, 65, 270, 5, true); // 천장
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
        int sel = collision_LEFT();// sel이 1이면 충돌, 0이면 충돌X
        if(sel == 0 && limit == false){
            width -= blockSize;
            repaint();
        }
    }
    
    void moveRight(){
        int sel = collision_RIGHT();// sel이 1이면 충돌, 0이면 충돌X
        if(sel == 0 && limit == false){ 
            width += blockSize;
            repaint();
        }
     }
}
