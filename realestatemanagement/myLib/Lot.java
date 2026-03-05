package com.mycompany.realestatemanagement.myLib;

public class Lot {
    private int lotNumber;
    private int blockNumber;
    private double size;
    private double price;
    private LotStatus status;
    private Buyer buyer;
    
    public Lot(int lotNumber, int blockNumber, double size, double price) {
        this.lotNumber = lotNumber;
        this.blockNumber = blockNumber;
        this.size = size;
        this.price = price;
        this.status = LotStatus.AVAILABLE;
        this.buyer = null;
    }
    
    //If AVAILABLE
    public String reserve(Buyer buyer) {
        if (status != LotStatus.AVAILABLE) {
            return "Lot " + lotNumber + " is already " + status + ". ";
        }
        this.status = LotStatus.RESERVED;
        this.buyer = buyer;
        return "SUCCSS: Lot " + lotNumber + " reserved for " + buyer.getName() + ".";
    }
    
    //If AVAILABLE or RESERVED, not SOLD
    public String sell (Buyer buyer) {
        if (status == LotStatus.SOLD) {
            return "Lot " + lotNumber + "is already sold.";
        }
        this.status = LotStatus.SOLD;
        this.buyer = buyer;
        return "SUCCESS: Lot " + lotNumber + " sold to " + buyer.getName() + ".";
    }
    
    //Getters
    public int getLotNumber() {return lotNumber;}
    public int getBlockNumber() {return blockNumber;}
    public double getSize() {return size;}
    public double getPrice() {return price;}
    public LotStatus getStatus() {return status;}
    public Buyer getBuyer() {return buyer;}
    
    public String getBuyerName() {
        return buyer !=null ? buyer.toString(): "None";
    }
    
    @Override
    public String toString() {
        return String.format("Block %d | Lot %d | %.0f sqm | ₱%.2f | %s | Buyer: %s",
            blockNumber, lotNumber, size, price, status, getBuyerName());
    }
}
