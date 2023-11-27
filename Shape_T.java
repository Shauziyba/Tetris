/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

/**
 *
 * @author nicholas
 */
public class Shape_T extends Tetromino{
    public Shape_T() {
        cells[0] = new Cell(0, 4, Tetromino.T);
        cells[1] = new Cell(0, 3, Tetromino.T);
        cells[2] = new Cell(0, 5, Tetromino.T);
        cells[3] = new Cell(1, 4, Tetromino.T);
        
        places= new Place[] { new Place(new int[][] { { 0, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } }),
	new Place(new int[][] { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, -1 } }),
	new Place(new int[][] { { 0, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } }),
	new Place(new int[][] { { 0, 0 }, { 1, 0 }, { -1, 0 }, { 0, 1 } }) };
    }    
}
