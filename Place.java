/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

/**
 *
 * @author Nicholas
 */
public class Place{
    //Class place digunakan untuk merotasi shape
    int[] rows = new int[4];
    int[] cols = new int[4];

	
    public Place() {
    }

    public Place(int[][] rowAndCol) {
	super();
	for (int i = 0; i < 4; i++) {
            this.rows[i] = rowAndCol[i][0];
            this.cols[i] = rowAndCol[i][1];
	}
    }        
}
