package com.spaceshooter.pathfinding;

import java.util.Arrays;

public class Heap {

    IHeapItem[] items;
    int currentItemCount;

    public Heap(int maxHeapSize) {
        items = new IHeapItem[maxHeapSize];
    }

    public void add(IHeapItem item) {
        item.setHeapIndex(currentItemCount);
        items[currentItemCount] = item;
        sortUp(item);
        currentItemCount++;
    }

    public IHeapItem removeFirst() {
        IHeapItem firstItem = items[0];
        currentItemCount--;
        items[0] = items[currentItemCount];
        items[0].setHeapIndex(0);
        sortDown(items[0]);
        return firstItem;
    }

    public int getCurrentItemCount() {
        return currentItemCount;
    }

    public boolean contains(IHeapItem item) {
        return Arrays.asList(items).contains(item);
    }

    void sortDown(IHeapItem item) {
        while (true) {
            int childIndexLeft = item.getHeapIndex() * 2 + 1;
            int childIndexRight = item.getHeapIndex() * 2 + 2;
            int swapIndex = 0;

            if (childIndexLeft < currentItemCount) {
                swapIndex = childIndexLeft;

                if (childIndexRight < currentItemCount) {
                    if (items[childIndexLeft].compareTo(items[childIndexRight]) < 0) {
                        swapIndex = childIndexRight;
                    }
                }

                if (item.compareTo(items[swapIndex]) < 0) {
                    swap(item,items[swapIndex]);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    void sortUp(IHeapItem item) {
        int parentIndex = (item.getHeapIndex()-1)/2;

        while(true) {
            IHeapItem parentItem = items[parentIndex];
            if(item.compareTo(parentItem) > 0) {
                swap(item, parentItem);
            } else {
                break;
            }

            parentIndex = (item.getHeapIndex()-1)/2;
        }
    }

    void swap(IHeapItem itemA, IHeapItem itemB) {
        items[itemA.getHeapIndex()] = itemB;
        items[itemB.getHeapIndex()] = itemA;
        int itemAIndex = itemA.getHeapIndex();
        itemA.setHeapIndex(itemB.getHeapIndex());
        itemB.setHeapIndex(itemAIndex);
    }
}

