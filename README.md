# Weapon Charges Plugin
This is a plugin which tracks a player's weapon charges. This applies to most weapons which consume charges such as a Trident or Scythe of Vitur.

## Visual Example
![image](https://user-images.githubusercontent.com/69816894/112778895-8bd98980-9013-11eb-8484-75a09ca6c360.png)


## Usage
The first step is to download the Weapon Charges plugin from the Runelite Plugin Hub.

The weapon charges plugin icon should appear in the Runelite sidebar. After clicking on the icon there are different settings which are mostly self explanatory. The "Infobox for Equipped Weapon" setting will show an infobox displaying the charges for the weapon the player currently has equipped.

**The player will have to right-click "check" the charges on their weapon in order to properly configure each weapon, this lets the plugin update the current amount of charges on the weapon**

If there is any concern on if the amount of charges is displaying incorrectly, the first step to fix this is to make sure you right-click "check" on the weapon, if it is still incorrect then it may be due to having both a Trident of the Seas and Trident of the Swamp in your inventory, these two weapons can cause issues do to having the same in-game messages when checking charges so it is not advised to have both of these in your inventory at the same time, lastly if you are still finding issues with the charge count, please 
[create an issue](https://github.com/ZemyCode/weapon-charges/issues/new) and report what the problem is.

## Notes
Currently, some weapons such as the toxic blowpipe or slayer's staff (e) are hard to track due to their ammunition usage being based on RNG. These weapons are currently not implemeneted as a part of this plugin, but may be done in the future.

## Discussion and Credit
If you encounter bugs with the plugin or just have an addition to recommend please [create an issue](https://github.com/ZemyCode/weapon-charges/issues/new) and include the details.

Credit is due to the developers of the official Runelite plugin "Item Charges" as this plugin is built off of the modification of these files.
