import java.util.ArrayList;

/** The class for the thread actions of a single tile
 * @author Paweł Leśniak
 * @version 1.0
 */
class TileThread extends Thread {
	/** Object for tile synchronisation */
	static private final Object synch = new Object();
	/** Probability variable for deciding which action the tile takes */
	static private double p;
	/** Array that holds all the tile buttons */
	static private ArrayList<TileButton> tileList;
	/** The width of the board */
	static private int n;
	/** The height of the board */
	static private int m;
	/** The tile's button */
	private TileButton tile;
	/** The tile x position */
	private int x;
	/** The tile y position */
	private int y;
	/** Main constructor
	 * @param tile the button for the current tile
	 * @param x the x position of the tile
	 * @param y the y position of the tile
	 */
	public TileThread(TileButton tile, int x, int y) {
		this.tile = tile;
		this.x = x;
		this.y = y;
	}
	/** Method for initialisation of the static members
	 * @param p probability for deciding how to change the state
	 * @param tileList the ArrayList of all tiles
	 * @param n the width of the board
	 * @param m the height of the board
	 */
	public static void initValues(double p, ArrayList<TileButton> tileList, int n, int m) {
		TileThread.p = p;
		TileThread.tileList = tileList;
		TileThread.n = n;
		TileThread.m = m;
	}
	/** Method that changes the current state of the tile */
	private void change() {
		synchronized (synch) {
			if (tile.getState()) {
				if (RNG.randomProbability() <= p) {
					final double newHue = RNG.randomHue();
					tile.changeBackground(newHue);
					tile.setHue(newHue);
				} else {
					double newHue = 0;
					int active_neighbours = 0;
					int up = x*m + Math.floorMod(y - 1, m);
					int down = x*m + Math.floorMod(y + 1, m);
					int right = y + m*Math.floorMod(x + 1, n);
					int left = y + m*Math.floorMod(x - 1, n);
					if (tileList.get(up).getState()) {
						newHue += tileList.get(up).getHue();
						++active_neighbours;
					}
					if (tileList.get(down).getState()) {
						newHue += tileList.get(down).getHue();
						++active_neighbours;
					}
					if (tileList.get(right).getState()) {
						newHue += tileList.get(right).getHue();
						++active_neighbours;
					}
					if (tileList.get(left).getState()) {
						newHue += tileList.get(left).getHue();
						++active_neighbours;
					}
					if (active_neighbours != 0) {
						newHue /= active_neighbours;
						tile.changeBackground(newHue);
						tile.setHue(newHue);
					};
				}
			}
		}
	}
	/** Maid thread method */
	@Override
	public void run() {
		while (true) {
			try {
				sleep(RNG.randomDelay());
				change();
			} catch (InterruptedException ie){}
		}
	}
}
