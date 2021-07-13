package com.gildedrose.quality;

import com.gildedrose.Item;

public class DefaultQualityControl implements QualityControl {

    @Override
    public void updateQuality(Item item) {
        item.quality = Math.min(item.quality, MAX_DAYS);
        if (item.quality > 0) {
            item.quality--;
        }
    }
}
