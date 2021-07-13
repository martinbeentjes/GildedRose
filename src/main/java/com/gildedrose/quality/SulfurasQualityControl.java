package com.gildedrose.quality;

import com.gildedrose.Item;

public class SulfurasQualityControl implements QualityControl{

    public static final int DEFAULT_QUALITY_SULFURAS = 80;

    @Override
    public void updateQuality(Item item) {
        item.quality = DEFAULT_QUALITY_SULFURAS;
    }
}
