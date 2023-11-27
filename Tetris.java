/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tetris;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author nicholas
 */
public class Tetris extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private Tetromino bidakMain = Tetromino.randomOnes();
    private Tetromino bidakSelanjutnya = Tetromino.randomOnes();
    
    private final int row = 20;
    private final int col = 10;
    
    private int lines;
    
    int[] scores_pool = { 0, 1, 2, 5, 10 };
    private int totalScore = 0;
    private int totalLine = 0;
    
    private int gamestate;
    public static final int PLAYING = 0;
    public static final int PAUSE = 1;
    public static final int GAMEOVER = 2;
    
    private Cell[][] papanPermainan = new Cell[row][col];
    private static final int CELL_SIZE = 25;
    
    public void drawPapan(Graphics g) { // menggambar papan permainan
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                int x = CELL_SIZE * j;
                int y = CELL_SIZE * i;
                Cell cell = papanPermainan[i][j];
                
                if(cell == null) {
                    g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                } else {
                    g.drawImage(cell.getImg(), x, y, CELL_SIZE, CELL_SIZE, null);
                }
            }
        }
    }
    
    public void landToWall() {
	Cell[] cells = bidakMain.cells;
	for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            papanPermainan[row][col] = cell;
	}
    }
    
    public void drawBidakMain(Graphics g) { // menampilkan bidak yang sedang dimainkan
        Cell[] cells = bidakMain.getCells();
        for(Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE;
            int y = cell.getRow() * CELL_SIZE;
            g.drawImage(cell.getImg(), x, y, CELL_SIZE, CELL_SIZE, null);
        }
    }
    
    public void drawBidakSelanjutnya(Graphics g) { // menampilkan bidak yang akan dimainkan selanjutnya
        Cell[] cells = bidakSelanjutnya.getCells();
        for(Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE + 250;
            int y = cell.getRow() * CELL_SIZE + 25;
            g.drawImage(cell.getImg(), x, y, CELL_SIZE, CELL_SIZE, null);
        }
    }
     public void paintScore(Graphics g) {	
	g.drawString ("Bidak Selanjutnya" , 285 , 10);
	g.drawRect(280, 125, 160, 40);
	g.drawString ("Score:" + totalScore , 285 , 150);
        g.drawString("P= Pause/Resume", 280, 220);
	g.drawString ("R =Reset Game" , 280 , 260);
	g.drawString ("Arrow UP = Rotasi" , 280 , 300);
        g.drawString ("Arrow down = Turun", 280 , 340);
        g.drawString ("Spasi = Drop Instan", 280 , 380);
    }
     
    @Override
    public void paint(Graphics g) {//menampilkan gambar
        g.translate(0, 0);
        super.paint(g);
        drawPapan(g);
        drawBidakMain(g);
        drawBidakSelanjutnya(g);
        paintScore(g);
    }
    
    // batasan pergerakan bidak
    private boolean outOfBound() {
        Cell[] cells = bidakMain.getCells();
        for(Cell cell : cells) {
            int cellrow = cell.getRow();
            int cellcol = cell.getCol();
            if (cellrow <0 || cellrow >19 || cellcol<0 || cellcol>9){
                return true;
            }
        }
        return false;
    }
    
    private boolean coincide() { // jika terdapat bidak lain di papan permainan
        Cell[] cells = bidakMain.getCells();
        for(Cell cell : cells) {
            int cellrow = cell.getRow();
            int cellcol= cell.getCol();
            if (papanPermainan[cellrow][cellcol] != null){
                return true;
            }
        }
        return false;
    }
    
    private boolean isDrop() {
        Cell[] cells = bidakMain.getCells();
        for(Cell cell : cells) {
            int cellrow = cell.getRow();
            int cellcol= cell.getCol();
            if (cellrow == 19){
                return false;
            }
            
            if (papanPermainan[cellrow+1][cellcol] != null){
                return false;
            }
        }
        return true;
    }
    
    public boolean isFullLine(int row) {
	Cell[] lines = papanPermainan[row];
	for (Cell c : lines) {
            if (c == null) {
                return false;
            }
	}
	return true;
    }
    
    private void stopDrop() {
        Cell[] cells = bidakMain.getCells();
        for(Cell cell : cells) {
            papanPermainan[cell.getRow()][cell.getCol()] = cell;
        }
    }
    /*catatan:ada error pada baris penuh yaitu kadang kadang saat baris penuh
      baris tersebut tidak mau hilang,presentase error sekitar 40%-50%.
      sudah saya cari solusinya tapi malah tambah parah,alhasil saya tidak bisa memperbaiki error ini
      gajadi udh berhasil aoksoakwokwokwoawkaowaowkaowk*/
    public void barisPenuh() {
	lines = 0;
        Cell[] cells= bidakMain.cells;
        for (Cell cell : cells) {
            int row = cell.getRow ();
            while (row < 20) {
		if (isFullLine (row) == true) {
                    lines++;
                    papanPermainan[row] = new Cell[10];
                    for (int i = row; i > 0; i--) {
                        System.arraycopy(papanPermainan[i-1], 0 , papanPermainan[i] , 0 , 10);
                    }
                    papanPermainan[0] = new Cell[10];
		}
		row++;
            }
	}
	totalScore += scores_pool[lines];
	totalLine += lines;
    }

    // pergerakan bidak
    protected void softDropAction() {
        if(isDrop()) {
            bidakMain.moveDown();
        } else {
            stopDrop();
            bidakMain = bidakSelanjutnya;
            bidakSelanjutnya = Tetromino.randomOnes();
	    barisPenuh();
        }
    }
    
    private boolean tooLeft() {// batas kiri
        Cell[] cells = bidakMain.getCells();
        for(Cell cell : cells) {
            int cellcol= cell.getCol();
            if (cellcol <= 0){
                return true;
            }
        }
        return false;
    }
    
    private boolean tooRight() {//batas kanan
        Cell[] cells = bidakMain.getCells();
        for(Cell cell : cells) {
            int cellcol= cell.getCol();
            if (cellcol >= col-1){
                return true;
            }
        }
        return false;
    }
    
    protected void moveLeftAction() {
        if(!coincide() && !tooLeft() && !outOfBound()){
            bidakMain.moveLeft();
        }
    }
    
    protected void moveRightAction() {
        if(!coincide() && !tooRight()&& !outOfBound()){
            bidakMain.moveRight();
        }
    }
    
    public void rotateLeftAction() {
	if (!coincide() && !outOfBound() && !tooRight() && !tooLeft()) {
            bidakMain.rotasi();
	}
    }
    
    public void instantDropAction() {
	for (;;) {
            if (isDrop() == true) {
		bidakMain.moveDown();
            } else {
		break;
                    }
	}
	landToWall();
	barisPenuh();
	if (isGameOver () == true) {
            gamestate = GAMEOVER;
	} 
        else {
            bidakMain = bidakSelanjutnya;
            bidakSelanjutnya = Tetromino.randomOnes();
        }
    }
    
    public boolean isGameOver() {
	Cell[] cells = bidakSelanjutnya.cells;
	for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            if (papanPermainan[row][col] != null) {
		return true;
            }
	}
    return false;
    }
    
    // Thread permainan
    public void start() {
        KeyListener keylists =  new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent args) {
                int key = args.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_DOWN : {
                        softDropAction();
                        break;
                    } 
                    case KeyEvent.VK_LEFT : {
                        moveLeftAction();
                        break;
                    } 
                    case KeyEvent.VK_RIGHT : {
                        moveRightAction();
                        break;
                        
                    } 
                    case KeyEvent.VK_UP:{
			rotateLeftAction();
			break;
                    }
                    case KeyEvent.VK_P: {
			if (gamestate == PLAYING) {
                            gamestate = PAUSE;
			} else if (gamestate == PAUSE) {
                            gamestate = PLAYING;
                        }
			break;
                    } 
                    case KeyEvent.VK_R: {
			gamestate = PLAYING;
			papanPermainan = new Cell[20][10];
			bidakMain = Tetromino.randomOnes();
			bidakSelanjutnya = Tetromino.randomOnes();
			totalScore = 0;
			totalLine = 0;
			break;
                    } 
                    case KeyEvent.VK_ESCAPE: {
			System.exit(0);
                    } 
                    case KeyEvent.VK_SPACE: {
			instantDropAction();
                        break;
                    } 
                }
                repaint();
            }
        };
        
        this.addKeyListener(keylists);
        this.requestFocus();
        
        while(true) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException ex) {
		ex.printStackTrace();
            }

            if (gamestate == PLAYING) {
		if (isDrop() == true) {
                    bidakMain.moveDown();
                } else {
			landToWall();
			barisPenuh();
			if (isGameOver () == true) {
                            gamestate = GAMEOVER;
			} else {
				bidakMain = bidakSelanjutnya;
				bidakSelanjutnya = Tetromino.randomOnes();
                    }
                }
                repaint();
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Tetris");
        frame.setSize(525, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Tetris theGame = new Tetris();
        frame.add(theGame);
        frame.setVisible(true);
        theGame.start();
    }
    
}
