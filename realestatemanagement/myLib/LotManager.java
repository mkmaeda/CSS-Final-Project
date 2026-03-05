package com.mycompany.realestatemanagement.myLib;

import java.util.List;
import java.util.stream.Collectors;

public class LotManager {
    private Subdivision subdivision;
    
    public LotManager() {
        //Singleton - always gets the same subdivision instance
        this.subdivision = Subdivision.getInstance();
    }
    //Search by block
    public List<Lot> searchByBlock(int blockNumber) {
        Block block = subdivision.getBlock(blockNumber);
        if (block == null) return List.of();
        return block.getLots();
    }
    //Search by size range
    public List<Lot> searchBySize(double minSize, double maxSize) {
        return subdivision.getAllLots().stream()
                .filter(l -> l.getSize() >= minSize && l.getSize() <= maxSize)
                .collect(Collectors.toList());
    }
    //Search by price range
    public List<Lot> searchByPrice(double minPrice, double maxPrice) {
        return subdivision.getAllLots().stream()
                .filter(l -> l.getPrice() >= minPrice && l.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
    //Search by status
    public List<Lot> searchByStatus(LotStatus status) {
        return subdivision.getAllLots().stream()
                .filter(l -> l.getStatus() == status)
                .collect(Collectors.toList());
    }
    
    //Reserve a lot
    public String reserveLot(int blockNumber, int lotNumber, String buyerName, String contact) {
        Block block = subdivision.getBlock(blockNumber);
        if (block == null) return "Block " + blockNumber + " not found.";
        
        Lot lot = block.getLot(lotNumber);
        if (lot == null) return "Lot " + lotNumber + "not found in Block" + blockNumber + ".";
        
        Buyer buyer = new Buyer(buyerName, contact);
        String result = lot.reserve(buyer);
        
        if (result.startsWith("SUCCESS")) {
            subdivision.logTransaction(lot, buyer, "RESERVED");
        }
        return result;
    }
    
    //Sell a lot
    public String sellLot(int blockNumber, int lotNumber, String buyerName, String contact) {
        Block block = subdivision.getBlock(blockNumber);
        if (block == null) return "Block " + blockNumber + "not found.";
        
        Lot lot = block.getLot(lotNumber);
        if (lot == null) return "Lot " + lotNumber + " not found in Block " + blockNumber + ".";
        
        Buyer buyer = new Buyer(buyerName, contact);
        String result = lot.sell(buyer);
        
        if (result.startsWith("SUCCESS")) {
            subdivision.logTransaction(lot, buyer, "SOLD");
        }
        return result;
    }
    
    public List<Lot> generateReport() {
        return subdivision.getAllLots();
    }
    
    public List<Transaction> getTransactions() {
        return subdivision.getTransactionss();
    }
    
    public Subdivision getSubdivision() {
        return subdivision;
    }
}
