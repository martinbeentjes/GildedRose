package com.gildedrose.quality;

import com.gildedrose.Item;

public class ConjuredQualityControl implements QualityControl{
    @Override
    public void updateQuality(Item item) {
        item.quality = Math.min(item.quality, MAX_DAYS);

        int degradationFactor = 1;
        if (item.sellIn <= 0) {
            degradationFactor = 2;
        }

        if (item.quality >= degradationFactor) {
            item.quality -= (2*degradationFactor);
        }
    }
}
