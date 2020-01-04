package com.spaceshooter.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.spaceshooter.map.Id;
import com.spaceshooter.map.Layer;
import com.spaceshooter.pathfinding.IHeapItem;

public class Node extends Entity implements IHeapItem {

    public int gridX;
    public int gridY;
    public int gCost;
    public int hCost;
    public Node parent;

	BufferedImage image;
	int heapIndex;
	
	public Node(BufferedImage image, int gridX, int gridY, int x, int y, int width, int height, Id id, Layer layer) {
		super(x, y, width, height, id, layer);
		this.gridX = gridX;
		this.gridY = gridY;
		this.image = image;
	}

	public Node(int x, int y, int width, int height, Id id, Layer layer) {
		super(x, y, width, height, id, layer);
	}
	
	public void render(Graphics2D g) {
		g.drawImage(image, (int) position.getX(), (int) position.getY(), null);
		
		if(debug) {
			super.debug(g);
		}
	}

	public int fCost() {
        return gCost + hCost;
    }

	@Override
	public void setHeapIndex(int value) {
		heapIndex = value;
	}

	@Override
	public int getHeapIndex() {
		return heapIndex;
	}

    public int compareTo(IHeapItem o) {
        int compare = Integer.compare(fCost(), o.fCost());
        if (compare == 0) {
            compare = Integer.compare(hCost, o.hCost);
        }
        return -compare;
	}
}
