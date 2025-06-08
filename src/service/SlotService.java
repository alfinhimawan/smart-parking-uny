package service;

public class SlotService {
    private int totalSlot;
    private int sisaSlot;

    public SlotService(int totalSlot) {
        this.totalSlot = totalSlot;
        this.sisaSlot = totalSlot;
    }

    public int getTotalSlot() {
        return totalSlot;
    }

    public int getSisaSlot() {
        return sisaSlot;
    }

    public boolean isFull() {
        return sisaSlot <= 0;
    }

    public void decrement() {
        if (sisaSlot > 0) sisaSlot--;
    }

    public void increment() {
        if (sisaSlot < totalSlot) sisaSlot++;
    }

    public void reset() {
        sisaSlot = totalSlot;
    }
}
