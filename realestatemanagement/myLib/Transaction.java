package com.mycompany.realestatemanagement.myLib;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private Lot lot;
    private Buyer buyer;
    private String action; //if reserved or sold
    private String timestamp;
    
    public Transaction(Lot lot, Buyer buyer, String action) {
        this.lot = lot;
        this.buyer = buyer;
        this.action = action;
        this.timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    public Lot getLot() { return lot;}
    public Buyer getBuyer() { return buyer;}
    public String getAction() { return action;}
    public String getTimeStamp() { return timestamp;}
    
    @Override
    public String toString() {
        return String.format("[%s] %s: Block %d, Lot %d -> %s",
                timestamp, action, lot.getBlockNumber(), lot.getLotNumber(), buyer.getName());
    }
    
}
