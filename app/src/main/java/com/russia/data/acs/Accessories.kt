package com.russia.data.acs

import com.russia.game.EntitySnaps
import com.russia.game.SnapShot
import com.russia.game.core.Samp
import com.russia.game.gui.donate.Donate
import com.russia.game.gui.donate.DonateItem
import com.russia.game.gui.magicStore.MagicStoreItem
import com.russia.game.gui.magicStore.MagicStoreItems
import com.russia.game.gui.treasure.TreasureItem
import com.russia.data.Rare
import com.russia.data.skins.Skins
import com.russia.data.vehicles.Vehicles

object Accessories {

    fun getPriceLc(modelId: Int) : Int {
        return list[modelId]?.priceLc ?: 0
    }

    fun getSnap(modelId: Int): SnapShot {
        val acs = list[modelId]
        if(acs == null)
            return SnapShot(EntitySnaps.OBJECT, 18631, 0.0f, 0.0f,0.0f, 0.0f, 0.0f, 0.0f)

        return SnapShot(
            EntitySnaps.OBJECT,
            modelId,
            acs.rotX, acs.rotY, acs.rotZ,
            acs.offsetX, acs.offsetY, acs.offsetZ
        )
    }

    fun createDonateItem(modelId: Int): DonateItem {
        return DonateItem(getName(modelId), Donate.CATEGORY_ACS, getPriceLc(modelId), modelId, getSnap(modelId))
    }

    fun createTreasureItem(acsId: Int): TreasureItem {
        val acs = list[acsId]

        if(acs == null)
            return TreasureItem(0, "pizda", 0)

        return TreasureItem(
            SnapShot(
                EntitySnaps.OBJECT,
                acsId,
                acs.rotX, acs.rotY, acs.rotZ,
                acs.offsetX, acs.offsetY, acs.offsetZ
            ),
            acs.name,
            acs.rare
        )
    }

    fun getPriceRub(modelId: Int) : Int {
        return list[modelId]?.priceRub ?: 0
    }

    fun getRare(modelId: Int) : Int {
        return if((list[modelId]?.rare ?: 0) == Rare.NONE)
            Rare.getRareFromPrice(getPriceLc(modelId))
        else {
            list[modelId]?.rare ?: 0
        }
    }

    fun getName(modelId: Int) : String {
        return list[modelId]?.name ?: "Invalid Id"
    }

    val list = hashMapOf(
        5767 to Accesory("Raven", 500000, 500, Rare.NONE, 0.0f, 180.0f, 70.0f, 0.0f, 0.2f, 0.0f),
        5770 to Accesory("Pumpkin", 500000, 500, Rare.NONE, 0.0f, 180.0f, 70.0f, 0.0f, 0.2f, 0.0f),
        5778 to Accesory("Hyena Mask", 500000, 50),
        5768 to Accesory("Rodent Mask", 500000, 50),
        5769 to Accesory("Smiley Mask", 500000, 50, Rare.NONE, 0.0f, 180.0f, 90.0f, 0.0f, 0.0f, 0.0f),
        5809 to Accesory("Deer Mask", 500000, 50),
        5811 to Accesory("Bear Mask", 500000, 500, Rare.NONE, 0.0f, 180.0f, 70.0f, 0.0f, 0.7f, 0.07f),
        5869 to Accesory("Boar Mask", 500000, 50),
        5873 to Accesory("Bag Mask", 500000, 500, Rare.NONE, 0.0f, 180.0f, 70.0f, 0.0f, 0.0f, 0.0f),
        5874 to Accesory("Humanoid", 700000, 700, Rare.NONE, 0.0f, 180.0f, 70.0f, 0.0f, 0.2f, 0.0f),
        5916 to Accesory("Doggo", 500000, 500, Rare.NONE, 0.0f, 180.0f, 70.0f, 0.0f, 0.8f, 0.1f),
        5918 to Accesory("Skateboard", 1000000, 100, Rare.NONE, 0.0f, 180.0f, 70.0f, 0.0f, 0.8f, 0.1f),
        6605 to Accesory("Yellow Nike Backpack", 750000, 75),
        17976 to Accesory("Purple PUMA Backpack", 650000, 65),
        10971 to Accesory("Wide Gray Mask", 40000, 4),
        10973 to Accesory("Cloth Mask", 40000, 4, Rare.NONE, 7.0f, 180.0f, 90.0f, 0.0f, 0.0f, -1.0f),
        10974 to Accesory("Mask", 40000, 4, Rare.NONE, 0.0f, 180.0f, 90.0f, 0.0f, 0.0f, -0.91f),
        10976 to Accesory("Kabuki Mask", 120000, 4, Rare.NONE, 0.0f, 180.0f, 90.0f, -0.05f, 0.0f, -1.0f),
        10977 to Accesory("Paintball Mask", 70000, 7, Rare.NONE, 0.07f, 180.0f, 90.0f, 0.0f, 0.0f, -0.1f),
        10978 to Accesory("Green Mask 2", 90000, 9),
        10979 to Accesory("Mortal Kombat Mask", 150000, 15, Rare.NONE, 0.00f, 180.0f, 90.0f, 0.02f, 0.0f, -1.0f),
        10980 to Accesory("Respirator", 70000, 7, Rare.NONE, 0.0f, 180.0f, 90.0f, 0.0f, 0.0f, -1.0f),
        10981 to Accesory("White Mask", 40000, 4, 0),
        10982 to Accesory("Payday Mask", 200000, 20, Rare.NONE, 0.0f, 180.0f, 90.0f, 0.10f, 0.3f, -1.0f),
        10998 to Accesory("Azure Cap", 60000, 6, Rare.NONE, -175.0f, 0.0f, -55.0f, 0.0f, 0.0f, 0.0f),
        10999 to Accesory("Brown Cap", 60000, 6),
        11000 to Accesory("Green Cap", 60000, 6),
        11001 to Accesory("Camo Cap", 60000, 6),
        11002 to Accesory("White Adidas Backpack", 250000, 25),
        11003 to Accesory("Red Adidas Backpack", 280000, 28),
        11004 to Accesory("Red Louis Vuitton Backpack", 280000, 28),
        11005 to Accesory("Green Louis Vuitton Backpack", 280000, 28),
        11006 to Accesory("Azure Backpack with Roses", 300000, 30),
        11007 to Accesory("Black Backpack with Roses", 320000, 32),
        17995 to Accesory("Black Backpack", 420000, 42),
        17571 to Accesory("Red Backpack", 350000, 35),
        6603 to Accesory("Blue Backpack", 350000, 35),
        6602 to Accesory("Pink Backpack", 350000, 35),
        14011 to Accesory("Rich Bag", 600000, 60),
        14037 to Accesory("Louis Bag", 600000, 60),
        14283 to Accesory("Skateboard", 1000000, 1000, Rare.NONE, 50.0f, 180.0f, 70.0f, 0.0f, 0.1f, -0.05f),
        17598 to Accesory("Tony Hawk Skateboard", 1000000, 100),
        6159 to Accesory("Regular Skateboard", 600000, 60),
        17570 to Accesory("Hoopla Skateboard", 1000000, 100),
        17451 to Accesory("Rise & Shine Skateboard", 1000000, 100),
        14289 to Accesory("Thor's Hammer", 2500000, 2500, Rare.NONE, 90.0f, 180.0f, 0.0f, 0.0f, 0.0f, 0.0f),
        14393 to Accesory("Staff", 1500000, 1500, Rare.NONE, 230.0f, 180.0f, 250.0f, 0.0f, 0.1f, 0.0f),
        14394 to Accesory("Pickaxe", 1500000, 1500),
        16757 to Accesory("Guitar", 500000, 50, Rare.NONE, -180.0f, -310.0f, 84.0f, 0.0f, 0.0f, 0.0f),
        17445 to Accesory("Black Guitar", 500000, 50, Rare.NONE, -180.0f, -310.0f, 84.0f, 0.0f, 0.0f, 0.0f),
        17444 to Accesory("Brown Guitar", 500000, 50, Rare.NONE, -180.0f, -310.0f, 84.0f, 0.0f, 0.0f, 0.0f),
        16794 to Accesory("Azure-Pink Bag", 600000, 60, Rare.NONE, 9.0f, -178.0f, -48.0f, 0.0f, 0.0f, -0.03f),
        16803 to Accesory("Pink Leopard Bag", 600000, 60, Rare.NONE, 9.0f, -178.0f, -48.0f, 0.0f, 0.0f, -0.03f),
        16804 to Accesory("Blue-Pink Bag", 600000, 60, Rare.NONE, 9.0f, -178.0f, -48.0f, 0.0f, 0.0f, -0.03f),
        16805 to Accesory("Red-Blue Bag", 600000, 60, Rare.NONE, 9.0f, -178.0f, -48.0f, 0.0f, 0.0f, -0.03f),
        16807 to Accesory("Red-Black Bag", 600000, 60, Rare.NONE, 9.0f, -178.0f, -48.0f, 0.0f, 0.0f, -0.03f),
        16808 to Accesory("Blue-Black Bag", 600000, 60, Rare.NONE, 9.0f, -178.0f, -48.0f, 0.0f, 0.0f, -0.03f),
        16810 to Accesory("Green-Blue Bag", 600000, 60, Rare.NONE, 9.0f, -178.0f, -48.0f, 0.0f, 0.0f, -0.03f),
        16819 to Accesory("Yellow-Red Bag", 600000, 60, Rare.NONE, 9.0f, -178.0f, -48.0f, 0.0f, 0.0f, -0.03f),
        16831 to Accesory("Black Bag", 600000, 60, Rare.NONE, 9.0f, -178.0f, -48.0f, 0.0f, 0.0f, -0.03f),
        17991 to Accesory("Green Handbag", 600000, 60, Rare.NONE, 0.0f, 150.0f, 58.0f, 0.0f, 0.0f, 0.0f),
        17443 to Accesory("Red Handbag", 600000, 60, Rare.NONE, 0.0f, 150.0f, 58.0f, 0.0f, 0.0f, 0.0f),
        17442 to Accesory("Orange Handbag", 600000, 60, Rare.NONE, 0.0f, 150.0f, 58.0f, 0.0f, 0.0f, 0.0f),
        6009 to Accesory("Pink Handbag", 600000, 60, Rare.NONE, 0.0f, 150.0f, 58.0f, 0.0f, 0.0f, 0.0f),
        6608 to Accesory("Black Handbag", 600000, 60, Rare.NONE, 0.0f, 150.0f, 58.0f, 0.0f, 0.0f, 0.0f),
        6607 to Accesory("Blue Handbag", 600000, 60, Rare.NONE, 0.0f, 150.0f, 58.0f, 0.0f, 0.0f, 0.0f),
        6606 to Accesory("White Handbag", 600000, 60, Rare.NONE, 0.0f, 150.0f, 58.0f, 0.0f, 0.0f, 0.0f),
        6604 to Accesory("Sunglasses 3", 90000, 9, Rare.NONE, 4.0f, 84.0f, 0.0f, 0.0f, 0.0f, -0.09f),
        17979 to Accesory("Wings", 5000000, 5000, Rare.NONE, -35.0f, 63.0f, 15.0f, 0.0f, 0.0f, -0.26f),
        17992 to Accesory("Black Glasses", 90000, 9, Rare.NONE, 4.0f, 84.0f, 0.0f, 0.0f, 0.0f, -0.09f),
        6012 to Accesory("Metal Detector", 200000, 20),
        17581 to Accesory("JBL Speaker", 2000000, 200, Rare.NONE, 0.0f, 160.0f, 0.0f, 0.0f, 0.0f, 0.0f),
        6013 to Accesory("Sunglasses", 90000, 9, Rare.NONE, 4.0f, 84.0f, 0.0f, 0.0f, 0.0f, -0.09f),
        6010 to Accesory("Sunglasses 2", 90000, 9, Rare.NONE, 4.0f, 84.0f, 0.0f, 0.0f, 0.0f, -0.09f),
        6075  to Accesory("Divine Halo", 5_000_000, 5_000, Rare.LEGENDARY),
        5872  to Accesory("Pryan", 1_000_000, 1_000, Rare.LEGENDARY, 0.0f, 180.0f, 90.0f, 0.0f, 0.0f, 0.0f),

        //magicstore only?
        17342  to Accesory("Infernal", 999999999, 999999999, Rare.LEGENDARY, 180.0f, 0.0f, -115.0f, 0.0f, 3.7f, 0.0f),
        17346  to Accesory("Sylph", 999999999, 999999999, Rare.LEGENDARY, 180.0f, 0.0f, -90.0f, 0.052f, 3.5f, 0.0f),
        17350  to Accesory("Sulimo's Trident", 999999999, 999999999, Rare.LEGENDARY),
        17351  to Accesory("Balanar's Scythe", 999999999, 999999999, Rare.LEGENDARY),
        16849  to Accesory("Captain America's Shield", 999999999, 999999999, Rare.LEGENDARY, -90.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f),
        16583  to Accesory("Etrigan's Axe", 999999999, 999999999, Rare.LEGENDARY),
        17359  to Accesory("Sword of Sauron", 999999999, 999999999, Rare.LEGENDARY)
    )
}