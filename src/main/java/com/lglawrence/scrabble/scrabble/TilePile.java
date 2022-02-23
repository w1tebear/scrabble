/**
 * 
 */
package com.lglawrence.scrabble.scrabble;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lgl
 *
 */
public class TilePile {
	private List<Tile> tiles = new ArrayList<>();
	
	public TilePile() {
		tiles.add(new Tile('A', (byte)1));
		tiles.add(new Tile('A', (byte)1));
		tiles.add(new Tile('A', (byte)1));
		tiles.add(new Tile('A', (byte)1));
		tiles.add(new Tile('A', (byte)1));
		tiles.add(new Tile('A', (byte)1));
		tiles.add(new Tile('A', (byte)1));
		tiles.add(new Tile('A', (byte)1));
		tiles.add(new Tile('A', (byte)1));
		tiles.add(new Tile('B', (byte)1));
		tiles.add(new Tile('B', (byte)1));
		tiles.add(new Tile('C', (byte)1));
		tiles.add(new Tile('C', (byte)1));
		tiles.add(new Tile('D', (byte)2));
		tiles.add(new Tile('D', (byte)2));
		tiles.add(new Tile('D', (byte)2));
		tiles.add(new Tile('D', (byte)2));
		tiles.add(new Tile('E', (byte)1));
		tiles.add(new Tile('E', (byte)1));
		tiles.add(new Tile('E', (byte)1));
		tiles.add(new Tile('E', (byte)1));
		tiles.add(new Tile('E', (byte)1));
		tiles.add(new Tile('E', (byte)1));
		tiles.add(new Tile('E', (byte)1));
		tiles.add(new Tile('E', (byte)1));
		tiles.add(new Tile('E', (byte)1));
		tiles.add(new Tile('E', (byte)1));
		tiles.add(new Tile('E', (byte)1));
		tiles.add(new Tile('E', (byte)1));
		tiles.add(new Tile('F', (byte)1));
		tiles.add(new Tile('F', (byte)1));
		tiles.add(new Tile('G', (byte)2));
		tiles.add(new Tile('G', (byte)2));
		tiles.add(new Tile('G', (byte)2));
		tiles.add(new Tile('H', (byte)4));
		tiles.add(new Tile('H', (byte)4));
		tiles.add(new Tile('I', (byte)1));
		tiles.add(new Tile('I', (byte)1));
		tiles.add(new Tile('I', (byte)1));
		tiles.add(new Tile('I', (byte)1));
		tiles.add(new Tile('I', (byte)1));
		tiles.add(new Tile('I', (byte)1));
		tiles.add(new Tile('I', (byte)1));
		tiles.add(new Tile('I', (byte)1));
		tiles.add(new Tile('I', (byte)1));
		tiles.add(new Tile('J', (byte)1));
		tiles.add(new Tile('K', (byte)5));
		tiles.add(new Tile('L', (byte)1));
		tiles.add(new Tile('L', (byte)1));
		tiles.add(new Tile('L', (byte)1));
		tiles.add(new Tile('L', (byte)1));
		tiles.add(new Tile('M', (byte)3));
		tiles.add(new Tile('M', (byte)3));
		tiles.add(new Tile('N', (byte)1));
		tiles.add(new Tile('N', (byte)1));
		tiles.add(new Tile('N', (byte)1));
		tiles.add(new Tile('N', (byte)1));
		tiles.add(new Tile('N', (byte)1));
		tiles.add(new Tile('N', (byte)1));
		tiles.add(new Tile('O', (byte)1));
		tiles.add(new Tile('O', (byte)1));
		tiles.add(new Tile('O', (byte)1));
		tiles.add(new Tile('O', (byte)1));
		tiles.add(new Tile('O', (byte)1));
		tiles.add(new Tile('O', (byte)1));
		tiles.add(new Tile('O', (byte)1));
		tiles.add(new Tile('O', (byte)1));
		tiles.add(new Tile('P', (byte)3));
		tiles.add(new Tile('P', (byte)3));
		tiles.add(new Tile('Q', (byte)1));
		tiles.add(new Tile('R', (byte)1));
		tiles.add(new Tile('R', (byte)1));
		tiles.add(new Tile('R', (byte)1));
		tiles.add(new Tile('R', (byte)1));
		tiles.add(new Tile('R', (byte)1));
		tiles.add(new Tile('R', (byte)1));
		tiles.add(new Tile('S', (byte)1));
		tiles.add(new Tile('S', (byte)1));
		tiles.add(new Tile('S', (byte)1));
		tiles.add(new Tile('S', (byte)1));
		tiles.add(new Tile('T', (byte)1));
		tiles.add(new Tile('T', (byte)1));
		tiles.add(new Tile('T', (byte)1));
		tiles.add(new Tile('T', (byte)1));
		tiles.add(new Tile('T', (byte)1));
		tiles.add(new Tile('T', (byte)1));
		tiles.add(new Tile('U', (byte)1));
		tiles.add(new Tile('U', (byte)1));
		tiles.add(new Tile('U', (byte)1));
		tiles.add(new Tile('U', (byte)1));
		tiles.add(new Tile('V', (byte)4));
		tiles.add(new Tile('V', (byte)4));
		tiles.add(new Tile('W', (byte)4));
		tiles.add(new Tile('W', (byte)4));
		tiles.add(new Tile('X', (byte)1));
		tiles.add(new Tile('Y', (byte)4));
		tiles.add(new Tile('Y', (byte)4));
		tiles.add(new Tile('Z', (byte)1));
		
		shuffle();
	}
	

	public Tile pickATile() {
		return tiles.remove(0);
	}

	protected void shuffle() {
	}

	public void returnToPile(Collection<Tile> tiles) {
		this.tiles.addAll(tiles);
		shuffle();
	}
}
