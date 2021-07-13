package com.gildedrose.sellin;

import com.gildedrose.Item;

public class DefaultSellInControl implements SellInControl {
    @Override
    public void updateSellIn(Item item) {
        if (item.sellIn >= 0) {
            item.sellIn--;
        }
    }
}
