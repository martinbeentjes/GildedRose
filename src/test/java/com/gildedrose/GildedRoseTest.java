package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items.get(0).name);
    }

    // Just for clarification, an item can never have its Quality increase above 50, however "Sulfuras" is a
    //legendary item and as such its Quality is 80 and it never alters.
    @Test
    void testMaxQuality() {
        Item[] items = new Item[] { new Item("foo", 20, 55) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(49, app.items.get(0).quality);
    }

    @Test
    void testLegendaryQuality() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 75) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items.get(0).quality);
    }

    @Test
    void testNormalItem() {
        Item[] items = new Item[] { new Item("foo", 20, 55) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(47, app.items.get(0).quality);
        assertEquals(17, app.items.get(0).sellIn);
    }

}
