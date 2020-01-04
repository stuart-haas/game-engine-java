package com.spaceshooter.pathfinding;

public interface IHeapItem extends Comparable<IHeapItem> {
    int hCost = 0;
    int fCost();
    void setHeapIndex(int value);
    int getHeapIndex();
}
