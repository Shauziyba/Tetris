/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

import java.awt.image.BufferedImage;

/**
 *
 * @author nicholas
 */
public class Cell {
    private int row;
    private int col;
    BufferedImage image;

    public Cell(){    }

    public Cell(int row, int col, BufferedImage img) {
        this.row = row;
        this.col = col;
        this.image = img;
    }
    public BufferedImage getImg() {
        return image;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
    this.col = col;
    }

    public void down() {
        row++;
    }

    public void right() {
        col++;
    }

    public void left() {
        col--;
    }

}
