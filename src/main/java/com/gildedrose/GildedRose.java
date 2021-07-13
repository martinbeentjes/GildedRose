package com.gildedrose;

import com.gildedrose.quality.AgedBrieQualityControl;
import com.gildedrose.quality.BackstagePassesQualityControl;
import com.gildedrose.quality.ConjuredQualityControl;
import com.gildedrose.quality.DefaultQualityControl;
import com.gildedrose.quality.QualityControl;
import com.gildedrose.quality.SulfurasQualityControl;
import com.gildedrose.sellin.DefaultSellInControl;
import com.gildedrose.sellin.SellInControl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class GildedRose {
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String CONJURED_ITEM = "Conjured item";
    private static final String DEFAULT_ITEM = "Default item";
    Item[] items;
    private Map<String, QualityControl> qualityControllers;
    private SellInControl sellInControl;

    public GildedRose(Item[] items) {
        this.items = items;
        initQualityControllers();
        initSellInControl();
    }

    private void initSellInControl() {
        this.sellInControl = new DefaultSellInControl();
    }

    public void endTheDay() {
        updateQuality();
        updateSellIn();
    }

    private void updateSellIn() {
        Arrays.stream(items).forEach(sellInControl::updateSellIn);
    }

    private void updateQuality() {
        Arrays.stream(items).forEach(item -> {
            QualityControl qualityControl = qualityControllers.get(item.name);
            if (qualityControl == null) {
                qualityControl = qualityControllers.get(DEFAULT_ITEM);
            }
            qualityControl.updateQuality(item);
        });
    }

    private void initQualityControllers() {
        this.qualityControllers = new HashMap<>();
        qualityControllers.put(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, new BackstagePassesQualityControl());
        qualityControllers.put(SULFURAS_HAND_OF_RAGNAROS, new SulfurasQualityControl());
        qualityControllers.put(AGED_BRIE, new AgedBrieQualityControl());
        qualityControllers.put(CONJURED_ITEM, new ConjuredQualityControl());
        qualityControllers.put(DEFAULT_ITEM, new DefaultQualityControl());
    }
}