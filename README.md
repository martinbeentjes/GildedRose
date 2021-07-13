# Gilded Rose Kata Java by Martin Beentjes

 - All items have a SellIn value which denotes the number of days we have to sell the item
 - All items have a Quality value which denotes how valuable the item is 
 - At the end of each day our system lowers both values for every item
 - The Quality of an item is never negative
 - Once the sell by date has passed, Quality degrades twice as fast
 - The Quality of an item is never more than 50



## Item types

### Normal Items
 - Quality decreases by one.
 - Quality decreases by two when sell by date has passed
 - SellIn value can not be lower than -1.
 - 

### Conjured Items

 - "Conjured" items degrade in Quality twice as fast as normal items
 - Quality decreases by two.
 - Quality decreases by four when sell by date has passed.
 - 

### Sulfuras

 - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality

### Backstage passes

 - "Backstage passes" increases in Quality as its SellIn value approaches;
  Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
  Quality drops to 0 after the concert
   

### Aged brie items
 - "Aged Brie" actually increases in Quality the older it gets


	

We have recently signed a supplier of conjured items. This requires an update to our system:

