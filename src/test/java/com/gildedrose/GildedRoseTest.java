package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.endTheDay();
        assertEquals("fixme", app.items[0].name);
    }

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
                new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, -1, 10)
        };
        GildedRose app = new GildedRose(items);
        app.endTheDay();
        assertEquals(12, app.items[0].quality);
        assertEquals(13, app.items[1].quality);
        assertEquals(0, app.items[2].quality);

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
