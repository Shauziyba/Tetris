/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author nicholas
 */
public class Tetromino {
    protected Cell[] cells;
    protected Place[] places;
    private int count = 10000;
    
    public Tetromino() {
        cells = new Cell[4];
    }
    
    public Cell[] getCells() {
        return cells;
    }
    
    public void moveDown() {
        for (Cell cell : cells) {
            cell.down();
        }
    }
    
    public void moveLeft() {
        for (Cell cell : cells) {
            cell.left();
        }
    }
    
    public void moveRight() {
        for (Cell cell : cells) {
            cell.right();
        }
    }
    //menyiapkan rotasi dengan class place
    public void rotasi() {
    count--;
    Place place = places[count%places.length];

    Cell cell = cells[0];
    int row = cell.getRow();
    int col = cell.getCol();
    for (int i = 1; i < 4; i++) {
            cells[i].setRow(row + place.rows[i]);
            cells[i].setCol(col + place.cols[i]);
    }
    }
    
    public static BufferedImage I;
    public static BufferedImage J;
    public static BufferedImage L;
    public static BufferedImage O;
    public static BufferedImage T;
    public static BufferedImage S;
    public static BufferedImage Z;
    
    static {
        try {
            I = ImageIO.read(new File("src/resource/I.png"));
            J = ImageIO.read(new File("src/resource/J.png"));
            L = ImageIO.read(new File("src/resource/L.png"));
            O = ImageIO.read(new File("src/resource/O.png"));
            T = ImageIO.read(new File("src/resource/T.png"));
            S = ImageIO.read(new File("src/resource/S.png"));
            Z = ImageIO.read(new File("src/resource/Z.png"));
        } catch (IOException ex) {
            Logger.getLogger(Tetromino.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
    
    public static Tetromino randomOnes() {
        Tetromino t = null;
        int ran = (int) (Math.random() * 7);
        switch(ran) {
            case 0 : t = new Shape_Z(); break;
            case 1 : t = new Shape_S(); break;
            case 2 : t = new Shape_T(); break;
            case 3 : t = new Shape_O(); break;
            case 4 : t = new Shape_L(); break;
            case 5 : t = new Shape_J(); break;
            case 6 : t = new Shape_I(); break;
        }
        return t;
    }
    
}
