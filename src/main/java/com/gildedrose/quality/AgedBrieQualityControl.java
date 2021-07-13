package com.gildedrose.quality;

import com.gildedrose.Item;

public class AgedBrieQualityControl implements QualityControl{
    @Override
    public void updateQuality(Item item) {
        if (item.quality < MAX_DAYS) item.quality++;
    }
}
