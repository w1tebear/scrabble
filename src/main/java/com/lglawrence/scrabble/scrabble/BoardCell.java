/**
 * 
 */
package com.lglawrence.scrabble.scrabble;

/**
 * Object representing a single cell on the board.
 */
public class BoardCell {
	Tile tile;
	int cell_multiplier;
	int word_multiplier;

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public int getCellValue() {
		return tile.value * cell_multiplier;
	}

	public int getWordMultiplier() {
		return word_multiplier;
	}

}
