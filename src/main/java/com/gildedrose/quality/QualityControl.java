package com.gildedrose.quality;

import com.gildedrose.Item;

public interface QualityControl {
    static final int MAX_DAYS = 50;

    void updateQuality(Item item);
}
