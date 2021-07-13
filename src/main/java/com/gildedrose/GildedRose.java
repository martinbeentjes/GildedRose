package com.gildedrose;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GildedRose {
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String CONJURED_ITEM = "Conjured item";
    List<Item> items;

    public GildedRose(Item[] items) {
        this.items = Arrays.stream(items).collect(Collectors.toList());
    }

    private static boolean isConjuredItem(Item item) {
        return item.name.equals(CONJURED_ITEM);
    }

    private static boolean isAgedBrieItem(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    private static boolean isSulfurasItem(Item item) {
        return item.name.equals(SULFURAS_HAND_OF_RAGNAROS);
    }

    private static boolean isBackstagePassItem(Item item) {
        return item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT);
    }

    public void updateQuality() {
        items.stream().filter(item -> !GildedRose.isSulfurasItem(item)).forEach(this::checkMaxQuality);

        List<Item> updatedConjuredItems = items
                .stream()
                .filter(GildedRose::isConjuredItem)
                .map(this::handleConjuredItem)
                .collect(Collectors.toList());

        List<Item> updatedAgedBrieItems = items
                .stream()
                .filter(GildedRose::isAgedBrieItem)
                .map(this::handleAgedBrieItems)
                .collect(Collectors.toList());

        List<Item> updatedBackstagePasses = items
                .stream()
                .filter(GildedRose::isBackstagePassItem)
                .map(this::handleBackstagePasses)
                .collect(Collectors.toList());

        List<Item> updatedSulfurasItems = items
                .stream()
                .filter(GildedRose::isSulfurasItem)
                .map(this::handleSulfurasItem)
                .collect(Collectors.toList());

        List<Item> updatesRestItems = items
                .stream()
                .filter(this::isNotSpecialItem)
                .map(this::handleNormalItem)
                .collect(Collectors.toList());

        items.clear();
        items.addAll(updatedConjuredItems);
        items.addAll(updatedAgedBrieItems);
        items.addAll(updatedBackstagePasses);
        items.addAll(updatedSulfurasItems);
        items.addAll(updatesRestItems);

    }

    private void checkMaxQuality(Item item) {
        if (item.quality > 50) item.quality = 50;
    }

    private Item handleAgedBrieItems(Item item) {
        return cleanupForAll(increaseQuality(item));
    }

    private Item handleNormalItem(Item item) {
        if (item.sellIn < 0) {
            decreaseQuality(item, 2);
        } else {
            decreaseQuality(item, 1);
        }
        return cleanupForAll(item);
    }

    private Item handleSulfurasItem(Item item) {
        item.quality = 80;
        return cleanupForAll(item);
    }

    private boolean isNotSpecialItem(Item item) {
        return !(isConjuredItem(item) ||
                isAgedBrieItem(item) ||
                isBackstagePassItem(item) ||
                isSulfurasItem(item));
    }

    private Item cleanupForAll(Item item) {
        if (item.quality < 0) {
            item.quality = 0;
        }
        decreaseSellIn(item);
        if (item.sellIn < 0) {
            return changeQualityWhenSellInIsLowerThanZero(item);
        }
        return item;
    }

    private Item handleConjuredItem(Item item) {
        return cleanupForAll(decreaseQuality(item, 2));
    }

    private Item handleBackstagePasses(Item item) {
        if (0 == item.sellIn) {
            item.quality = 0;
        } else if (0 < item.sellIn && item.sellIn <= 5) {
            increaseQuality(item, 3);
        } else if (5 < item.sellIn && item.sellIn <= 10) {
            increaseQuality(item, 2);
        } else {
            increaseQuality(item, 1);
        }

        return cleanupForAll(item);
    }

    private Item increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
        return item;
    }

    private void increaseQuality(Item item, int amount) {
        if ((item.quality + amount) <= 50) {
            item.quality += amount;
        }
    }

    private Item decreaseQuality(Item item, int amount) {
        if ((item.quality - amount) >= 0) {
            item.quality -= amount;
        }
        return item;
    }


    private Item decreaseQuality(Item item) {
        if (item.quality > 0 && !isSulfurasItem(item)) {
            item.quality--;
        }
        return item;
    }

    private void decreaseSellIn(Item item) {
        if (!isSulfurasItem(item)) {
            item.sellIn--;
        }
    }

    private Item changeQualityWhenSellInIsLowerThanZero(Item item) {
        Item newItem;
        if (isAgedBrieItem(item)) {
            newItem = increaseQuality(item);
        } else if (isBackstagePassItem(item)) {
            item.quality = 0;
            newItem = item;
        } else if (isSulfurasItem(item)) {
            newItem = item;
        } else {
            newItem = decreaseQuality(item);
        }

        return newItem;
    }
}