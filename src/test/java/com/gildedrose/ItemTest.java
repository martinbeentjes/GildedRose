package com.gildedrose;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemTest {

    public static final String EXPECTED_OUTCOME_TEST_ITEM = "Test item, 1, 1";
    public static final String TEST_ITEM_NAME = "Test item";

    @Test
    void testItem() {
        Item item = new Item(TEST_ITEM_NAME, 1, 1);
        assertEquals(EXPECTED_OUTCOME_TEST_ITEM, item.toString());
    }

}