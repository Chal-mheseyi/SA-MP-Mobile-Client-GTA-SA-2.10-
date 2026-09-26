package com.russia.game.gui.magicStore

import com.russia.game.core.Samp
import com.russia.data.acs.Accessories
import com.russia.data.skins.Skin
import com.russia.data.skins.Skins

object MagicStoreItems {
    const val PRICE_BRONZE = 0
    const val PRICE_SILVER = 1
    const val PRICE_GOLD = 2

    const val CATEGORY_ITEMS = 0
    const val CATEGORY_ANIMALS = 1
    const val CATEGORY_POTIONS = 2
    const val CATEGORY_CONSUMABLES = 3
    const val CATEGORY_SKINS = 4

    val list = listOf(
        MagicStoreItem("Infernal", CATEGORY_ANIMALS, 7_500, PRICE_GOLD, Accessories.getSnap(17342)),
        MagicStoreItem("Sylph", CATEGORY_ANIMALS, 7_500, PRICE_GOLD, Accessories.getSnap(17346)),

        MagicStoreItem("Captain America's Shield", CATEGORY_ITEMS, 7_500, PRICE_GOLD, Accessories.getSnap(16849)),
        MagicStoreItem("Sword of Sauron", CATEGORY_ITEMS, 5_000, PRICE_GOLD, Accessories.getSnap(17359)),
        MagicStoreItem("Etrigan's Axe", CATEGORY_ITEMS, 5_000, PRICE_GOLD, Accessories.getSnap(16583)),
        MagicStoreItem("Sulimo's Trident", CATEGORY_ITEMS, 5_000, PRICE_GOLD, Accessories.getSnap(17350)),
        MagicStoreItem("Balanar's Scythe", CATEGORY_ITEMS, 5_000, PRICE_GOLD, Accessories.getSnap(17351)),

        MagicStoreItem("Key to the Dungeon", CATEGORY_CONSUMABLES, 100, PRICE_BRONZE, getRecourse("inv_key_dungeon")),
        MagicStoreItem("Chest Key", CATEGORY_CONSUMABLES, 100, PRICE_SILVER, getRecourse("inv_key_chest")),
        MagicStoreItem("Bell", CATEGORY_CONSUMABLES, 5, PRICE_GOLD, getRecourse("inv_bell")),

        MagicStoreItem("Healing Potion", CATEGORY_POTIONS, 1, PRICE_GOLD, getRecourse("inv_potion_health")),
        MagicStoreItem("Potion of Wisdom", CATEGORY_POTIONS, 150, PRICE_SILVER, getRecourse("inv_potion_wisdom")),
        MagicStoreItem("Satiety Potion", CATEGORY_POTIONS, 100, PRICE_BRONZE, getRecourse("inv_potion_satiety")),
        MagicStoreItem("Potion of the Deity", CATEGORY_POTIONS, 5, PRICE_GOLD, getRecourse("inv_potion_deities")),
        MagicStoreItem("Infernal Potion", CATEGORY_POTIONS, 2, PRICE_GOLD, getRecourse("inv_potion_infernal")),
        MagicStoreItem("Sylph Potion", CATEGORY_POTIONS, 2, PRICE_GOLD, getRecourse("inv_potion_sylphs")),
        MagicStoreItem("Reputation Potion", CATEGORY_POTIONS, 1, PRICE_GOLD, getRecourse("inv_potion_reputation")),

        MagicStoreItem(Skins.getName(153), CATEGORY_SKINS, 5000, PRICE_GOLD, Skins.getSnap(153)),
        MagicStoreItem(Skins.getName(38), CATEGORY_SKINS, 5000, PRICE_GOLD, Skins.getSnap(38)),
        MagicStoreItem(Skins.getName(241), CATEGORY_SKINS, 5000, PRICE_GOLD, Skins.getSnap(241)),
        MagicStoreItem(Skins.getName(242), CATEGORY_SKINS, 5000, PRICE_GOLD, Skins.getSnap(242))
    )


    private fun getRecourse(name: String): Int {
        val findRes = Samp.activity.resources.getIdentifier(name, "drawable", Samp.activity.packageName)
        if(findRes == 0)
            return Samp.activity.resources.getIdentifier("acs_10998", "drawable", Samp.activity.packageName)
        else
            return findRes
    }

}