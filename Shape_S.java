/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

/**
 *
 * @author nicholas
 */
public class Shape_S extends Tetromino {
    public Shape_S() {
        cells[0] = new Cell(0, 4, Tetromino.S);
        cells[1] = new Cell(0, 5, Tetromino.S);
        cells[2] = new Cell(1, 3, Tetromino.S);
        cells[3] = new Cell(1, 4, Tetromino.S);
        
        places = new Place[] { new Place(new int[][] { { 0, 0 }, { 0, 1 }, { 1, -1 }, { 1, 0 } }),
	new Place(new int[][] { { 0, 0 }, { -1, 0 }, { 1, 1 }, { 0, 1 } }) };
    }
}
