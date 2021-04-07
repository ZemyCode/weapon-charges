/*
 * Copyright (c) 2019, Aleios <https://github.com/aleios>
 * Copyright (c) 2020, Unmoon <https://github.com/unmoon>
 * Copyright (c) 2021, ZemyCode <https://github.com/ZemyCode>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.weaponcharges;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

<<<<<<< HEAD
@ConfigGroup("weaponcharges")
=======
@ConfigGroup("itemCharge")
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
public interface WeaponChargesConfig extends Config
{
	@ConfigSection(
			name = "Warning Settings",
			description = "Configuration for Warnings",
			position = 0
	)
	String warningSection = "warnings";

	@ConfigSection(
			name = "Infobox Settings",
			description = "Configuration for Infobox",
			position = 98
	)
	String infoboxSection = "infobox";

	@ConfigSection(
		name = "Charge Settings",
		description = "Configuration for which charges should be displayed",
		position = 99
	)
	String chargesSection = "charges";

	@ConfigItem(
		keyName = "veryLowWarningColor",
		name = "Very Low Warning",
		description = "The color of the overlay when charges are very low",
		position = 1,
		section = warningSection
	)
	default Color veryLowWarningColor()
	{
		return Color.RED;
	}

	@ConfigItem(
		keyName = "lowWarningColor",
		name = "Low Warning",
		description = "The color of the overlay when charges are low",
		position = 2,
		section = warningSection
	)
	default Color lowWarningolor()
	{
		return Color.YELLOW;
	}

	@ConfigItem(
		keyName = "veryLowWarning",
		name = "Very Low Warning",
		description = "The charge count for the very low warning color",
		position = 3,
		section = warningSection
	)
	default int veryLowWarning()
	{
		return 100;
	}

	@ConfigItem(
		keyName = "lowWarning",
		name = "Low Warning",
		description = "The charge count for the low warning color",
		position = 4,
		section = warningSection
	)
	default int lowWarning()
	{
		return 250;
	}
<<<<<<< HEAD
<<<<<<< HEAD
//---------------------------------------------------------------------------------------------------------------------
=======

>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
//---------------------------------------------------------------------------------------------------------------------
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
	@ConfigItem(
			keyName = "showInfobox",
			name = "Infobox for Equipped Weapon",
			description = "Show an infobox with remaining charges for equipped items " +
					"(Player will need to re-equip weapon to trigger the infobox)",
			position = 5,
			section = infoboxSection
	)
	default boolean showInfoboxes()
	{
		return false;
	}

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
	@ConfigItem(
			keyName = "infoboxThreshold",
			name = "Infobox Threshold",
			description = "Shows infobox for equipped item if charges are on or under this threshold",
			position = 6,
			section = infoboxSection
	)
	default int infoboxThreshold()
	{
		return 20000;
	}

<<<<<<< HEAD
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "chargesThreshold",
			name = "Charges Threshold",
			description = "Displays weapon charges if on or below this threshold",
			position = 7,
			section = chargesSection
	)
	default int chargesThreshold()
{
	return 20000;
}

	@ConfigItem(
			keyName = "showAbyssalTentacleCharges",
			name = "Abyssal Tentacle",
			description = "Show Abyssal Tentacle Charges",
			position = 67,
=======
=======
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "chargesThreshold",
			name = "Charges Threshold",
			description = "Displays weapon charges if on or below this threshold",
			position = 7,
			section = chargesSection
	)
	default int chargesThreshold()
{
	return 20000;
}

	@ConfigItem(
			keyName = "showAbyssalTentacleCharges",
			name = "Abyssal Tentacle",
			description = "Show Abyssal Tentacle Charges",
<<<<<<< HEAD
			position = 6,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 67,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showAbyssalTentacleCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "abyssalTentacle",
			name = "",
			description = "",
			hidden = true
	)
	default int abyssalTentacle()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "abyssalTentacle",
			name = "",
			description = ""
	)
	void abyssalTentacle(int abyssalTentacle);
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "showArclightCharges",
			name = "Arclight",
			description = "Show Arclight Charges",
<<<<<<< HEAD
<<<<<<< HEAD
			position = 68,
=======
			position = 7,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 68,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showArclightCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "arcLight",
			name = "",
			description = "",
			hidden = true
	)
	default int arclight()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "arcLight",
			name = "",
			description = ""
	)
	void arclight(int arclight);
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "showCrawsBowCharges",
			name = "Craw's Bow",
			description = "Show Craw's Bow Charges",
<<<<<<< HEAD
<<<<<<< HEAD
			position = 69,
=======
			position = 8,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 69,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showCrawsBowCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "crawsBow",
			name = "",
			description = "",
			hidden = true
	)
	default int crawsBow()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "crawsBow",
			name = "",
			description = ""
	)
	void crawsBow(int crawsBow);
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
		keyName = "showCrystalBowCharges",
		name = "Crystal Bow",
		description = "Show Crystal Bow Charges",
<<<<<<< HEAD
<<<<<<< HEAD
		position = 70,
=======
		position = 9,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
		position = 70,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
		section = chargesSection
	)
	default boolean showCrystalBowCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "crystalBow",
			name = "",
			description = "",
			hidden = true
	)
	default int crystalBow()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "crystalBow",
			name = "",
			description = ""
	)
	void crystalBow(int crystalBow);
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "showCrystalHalberdCharges",
			name = "Crystal Halberd",
			description = "Show Crystal Halberd Charges",
<<<<<<< HEAD
<<<<<<< HEAD
			position = 71,
=======
			position = 10,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 71,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showCrystalHalberdCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "crystalHalberd",
			name = "",
			description = "",
			hidden = true
	)
	default int crystalHalberd()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "crystalHalberd",
			name = "",
			description = ""
	)
	void crystalHalberd(int crystalHalberd);
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "showIbansStaffCharges",
			name = "Iban's Staff",
			description = "Show Iban's Staff Charges",
<<<<<<< HEAD
<<<<<<< HEAD
			position = 72,
=======
			position = 11,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 72,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showIbansStaffCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "ibansStaff",
			name = "",
			description = "",
			hidden = true
	)
	default int ibansStaff()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "ibansStaff",
			name = "",
			description = ""
	)
	void ibansStaff(int ibansStaff);
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "showSanguinestiStaffCharges",
			name = "Sanguinesti Staff",
			description = "Show Sanguinesti Staff Charges",
<<<<<<< HEAD
<<<<<<< HEAD
			position = 73,
=======
			position = 12,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 73,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showSanguinestiStaffCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "sanguinestiStaff",
			name = "",
			description = "",
			hidden = true
	)
	default int sanguinestiStaff()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "sanguinestiStaff",
			name = "",
			description = ""
	)
	void sanguinestiStaff(int ibansStaff);
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "showSaradominsBlessedSwordCharges",
			name = "Saradomin's Blessed Sword",
			description = "Show Saradomin's Blessed Sword Charges",
<<<<<<< HEAD
<<<<<<< HEAD
			position = 74,
=======
			position = 13,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 74,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showSaradominsBlessedSwordCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "saradominsBlessedSword",
			name = "",
			description = "",
			hidden = true
	)
	default int saradominsBlessedSword()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "saradominsBlessedSword",
			name = "",
			description = ""
	)
	void saradominsBlessedSword(int saradominsBlessedSword);
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "showScytheOfViturCharges",
			name = "Scythe of Vitur",
			description = "Show Scythe of Vitur Charges",
<<<<<<< HEAD
<<<<<<< HEAD
			position = 75,
=======
			position = 14,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 75,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showScytheOfViturCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "scytheOfVitur",
			name = "",
			description = "",
			hidden = true
	)
	default int scytheOfVitur()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "scytheOfVitur",
			name = "",
			description = ""
	)
	void scytheOfVitur(int scytheOfVitur);
//---------------------------------------------------------------------------------------------------------------------
/*	@ConfigItem(
			keyName = "showSlayersStaffECharges",
			name = "Slayer's Staff (e)",
			description = "Show Slayer's Staff (e) charges",
<<<<<<< HEAD
<<<<<<< HEAD
			position = 76,
=======
			position = 15,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 76,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showSlayersStaffECharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "slayersStaffE",
			name = "",
			description = "",
			hidden = true
	)
	default int slayersStaffE()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "slayersStaffE",
			name = "",
			description = ""
	)
	void slayersStaffE(int slayersStaffE);*/		//future
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "showThammaronsSceptreCharges",
			name = "Thammaron's Sceptre",
			description = "Show Thammaron's Sceptre charges",
<<<<<<< HEAD
<<<<<<< HEAD
			position = 77,
=======
			position = 16,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 77,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showThammaronsSceptreCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "thammaronsSceptre",
			name = "",
			description = "",
			hidden = true
	)
	default int thammaronsSceptre()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "thammaronsSceptre",
			name = "",
			description = ""
	)
	void thammaronsSceptre(int thammaronsSceptre);
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "showTridentOfTheSeasCharges",
			name = "Trident of the Seas",
			description = "Show Trident of the Seas Charges (including Trident of the Seas (e))",
<<<<<<< HEAD
<<<<<<< HEAD
			position = 78,
=======
			position = 17,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 78,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showTridentOfTheSeasCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "tridentOfTheSeas",
			name = "",
			description = "",
			hidden = true
	)
	default int tridentOfTheSeas()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "tridentOfTheSeas",
			name = "",
			description = ""
	)
	void tridentOfTheSeas(int tridentOfTheSeas);
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "showTridentOfTheSwampCharges",
			name = "Trident of the Swamp",
			description = "Show Trident of the Swamp Charges (including Trident of the Swamp (e))",
<<<<<<< HEAD
<<<<<<< HEAD
			position = 79,
=======
			position = 18,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 79,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showTridentOfTheSwampCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "tridentOfTheSwamp",
			name = "",
			description = "",
			hidden = true
	)
	default int tridentOfTheSwamp()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "tridentOfTheSwamp",
			name = "",
			description = ""
	)
	void tridentOfTheSwamp(int tridentOfTheSwamp);
//---------------------------------------------------------------------------------------------------------------------
	@ConfigItem(
			keyName = "showViggorasChainmaceCharges",
			name = "Viggora's Chainmace",
			description = "Show Viggora's Chainmace Charges",
<<<<<<< HEAD
<<<<<<< HEAD
			position = 80,
=======
			position = 19,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			position = 80,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			section = chargesSection
	)
	default boolean showViggorasChainmaceCharges()
	{
		return true;
	}

	@ConfigItem(
			keyName = "viggorasChainmace",
			name = "",
			description = "",
			hidden = true
	)
	default int viggorasChainmace()
	{
		return -1;
	}

	@ConfigItem(
			keyName = "viggorasChainmace",
			name = "",
			description = ""
	)
	void viggorasChainmace(int viggorasChainmace);
//---------------------------------------------------------------------------------------------------------------------
}
