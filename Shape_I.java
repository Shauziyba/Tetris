/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

/**
 *
 * @author nicholas
 */
public class Shape_I extends Tetromino {
    public Shape_I() {
        cells[0] = new Cell(0, 4, Tetromino.I);
        cells[1] = new Cell(0, 3, Tetromino.I);
        cells[2] = new Cell(0, 5, Tetromino.I);
        cells[3] = new Cell(0, 6, Tetromino.I); 
        
        places = new Place[] { new Place(new int[][] { { 0, 0 }, { 0, -1 }, { 0, 1 }, { 0, 2 } }),
	new Place(new int[][] { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 2, 0 } }) };
    }
}
