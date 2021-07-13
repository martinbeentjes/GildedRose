package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";

    // Just for clarification, an item can never have its Quality increase above 50, however "Sulfuras" is a
    //legendary item and as such its Quality is 80 and it never alters.
    @Test
    void testMaxQuality() {
        Item[] items = new Item[] { new Item("foo", 20, 55) };
        GildedRose app = new GildedRose(items);
        app.endTheDay();
        assertEquals(49, app.items[0].quality);
    }

    @Test
    void testLegendaryQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 75) };
        GildedRose app = new GildedRose(items);
        app.endTheDay();
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void testBackstagePassQuality() {
        Item[] items = new Item[] {
                new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 9, 10),
                new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 3, 10),
                new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, -1, 10),
                new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 15, 10),

        };
        GildedRose app = new GildedRose(items);
        app.endTheDay();
        assertEquals(12, app.items[0].quality);
        assertEquals(13, app.items[1].quality);
        assertEquals(0, app.items[2].quality);
        assertEquals(11, app.items[3].quality);

    }

    @Test
    void testAgedBrie() {
        Item[] items = new Item[] { new Item("Aged Brie", 5, 25) };
        GildedRose app = new GildedRose(items);
        app.endTheDay();
        assertEquals(26, app.items[0].quality);
    }

    @Test
    void testConjuredItem() {
        Item[] items = new Item[] { new Item("Conjured item", 5, 25) };
        GildedRose app = new GildedRose(items);
        app.endTheDay();
        assertEquals(23, app.items[0].quality);

        app.items[0].sellIn = -1;
        app.endTheDay();
        assertEquals(19, app.items[0].quality);
    }

    @Test
    void testNormalItem() {
        Item[] items = new Item[] { new Item("foo", 20, 55) };
        GildedRose app = new GildedRose(items);
        app.endTheDay();
        app.endTheDay();
        app.endTheDay();
        assertEquals(47, app.items[0].quality);
        assertEquals(17, app.items[0].sellIn);
    }

}
