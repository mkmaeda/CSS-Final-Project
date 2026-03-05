package com.mycompany.realestatemanagement.myLib;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Block {
    private int blockNumber;
    private List<Lot> lots;
    
    public Block(int blockNumber) {
        this.blockNumber = blockNumber;
        this.lots = generateLots();
    }
    
    //Generates 20 lots with sizes and price
    private List<Lot> generateLots() {
        List<Lot> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            double size = 100 + (i % 5) * 25;
            double price = size * (15000 + blockNumber * 500);
            list.add(new Lot(i, blockNumber, size, price));
        }
        return list;
    }
    //Find specific lot
    public Lot getLot(int lotNumber) {
        return lots.stream()
                .filter(l -> l.getLotNumber() == lotNumber)
                .findFirst()
                .orElse(null);
    }
    
    public List<Lot> getLots() { return lots; }
    
    public List<Lot> getAvailableLots() {
        return lots.stream()
                .filter(l -> l.getStatus() == LotStatus.AVAILABLE)
                .collect(Collectors.toList());
    }
    
    public int getBlockNumber() { return blockNumber; }
}
