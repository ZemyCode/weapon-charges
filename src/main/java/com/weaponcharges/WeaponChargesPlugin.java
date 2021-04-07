/*
 * Copyright (c) 2017, Seth <Sethtroll3@gmail.com>
 * Copyright (c) 2018, Hydrox6 <ikada@protonmail.ch>
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

import com.google.inject.Provides;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.EquipmentInventorySlot;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemID;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.ScriptCallbackEvent;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.util.Text;

@PluginDescriptor(
	name = "Weapon Charges",
	description = "Show number of weapon charges remaining",
	tags = {"inventory", "notifications", "overlay"}
)
@Slf4j
public class WeaponChargesPlugin extends Plugin
{
	private static final Pattern ABYSSAL_TENTACLE_CHECK_PATTERN = Pattern.compile(
			"Your abyssal tentacle can perform (?:(\\d+)|((\\d+),(\\d+))) more attacks?\\."
	);
	private static final Pattern ARCLIGHT_CHECK_PATTERN = Pattern.compile(
			"Your arclight has (\\d+) charges? left\\."
	);
	private static final Pattern CRAWS_BOW_CHECK_PATTERN = Pattern.compile(
			"Your bow has (?:(\\d+)|((\\d+),(\\d+))) charges? left powering it\\."
	);
	private static final Pattern CRYSTAL_BOW_CHECK_PATTERN = Pattern.compile(
			"Your crystal bow has (?:(\\d+)|((\\d+),(\\d+))) charges? remaining\\."
	);
	private static final Pattern CRYSTAL_HALBERD_CHECK_PATTERN = Pattern.compile(
			"Your crystal halberd has (?:(\\d+)|((\\d+),(\\d+))) charges? remaining\\."
	);
	private static final Pattern IBANS_STAFF_CHECK_PATTERN = Pattern.compile(
			"You have (\\d+) charges? left on the staff\\."
	);
	private static final Pattern SANGUINESTI_STAFF_CHECK_PATTERN = Pattern.compile(
			"Your sanguinesti staff has (?:(\\d+)|((\\d+),(\\d+))) charges? remaining\\."
	);
	private static final Pattern SARADOMINS_BLESSED_SWORD_CHECK_PATTERN = Pattern.compile(
			"The sword has (?:(\\d+)|((\\d+),(\\d+))) hits? left\\."
	);
	private static final Pattern SCYTHE_OF_VITUR_CHECK_PATTERN = Pattern.compile(
			"Your scythe of vitur has (?:(\\d+)|((\\d+),(\\d+))) charges? remaining\\."
	);
/*	private static final Pattern SLAYERS_STAFF_E_CHECK_PATTERN = Pattern.compile(
			"Your scythe of vitur has (?:(\\d+)|((\\d+),(\\d+))) charges? remaining\\."
	); */
	private static final Pattern THAMMARONS_SCEPTRE_CHECK_PATTERN = Pattern.compile(
			"Your sceptre has (?:(\\d+)|((\\d+),(\\d+))) charges? left powering it\\."
	);
	private static final Pattern TRIDENT_CHECK_PATTERN = Pattern.compile(
			"Your weapon has (?:(\\d+)|((\\d+),(\\d+))) charges?\\."
	);
	private static final Pattern VIGGORAS_CHAINMACE_CHECK_PATTERN = Pattern.compile(
			"Your chainmace has (?:(\\d+)|((\\d+),(\\d+))) charges? left powering it\\."
	);

<<<<<<< HEAD
<<<<<<< HEAD
	int[] chargeableWeaponIDs = { ItemID.ABYSSAL_TENTACLE,
=======
	private final int[] chargeableWeaponIDs = {
			ItemID.ABYSSAL_TENTACLE,
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
	int[] chargeableWeaponIDs = { ItemID.ABYSSAL_TENTACLE,
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			ItemID.ARCLIGHT,
			ItemID.CRAWS_BOW, ItemID.CRAWS_BOW_U,
			ItemID.CRYSTAL_BOW, ItemID.CRYSTAL_HALBERD,
			ItemID.IBANS_STAFF, ItemID.IBANS_STAFF_U,
			ItemID.SANGUINESTI_STAFF, ItemID.SANGUINESTI_STAFF_UNCHARGED,
			ItemID.SARADOMINS_BLESSED_SWORD,
			ItemID.SCYTHE_OF_VITUR, ItemID.SCYTHE_OF_VITUR_UNCHARGED,
			ItemID.SLAYERS_STAFF_E,
			ItemID.THAMMARONS_SCEPTRE, ItemID.THAMMARONS_SCEPTRE_U,
			ItemID.TRIDENT_OF_THE_SEAS, ItemID.UNCHARGED_TRIDENT, ItemID.UNCHARGED_TRIDENT_E, ItemID.TRIDENT_OF_THE_SEAS_E, ItemID.TRIDENT_OF_THE_SEAS_FULL,
			ItemID.TRIDENT_OF_THE_SWAMP, ItemID.TRIDENT_OF_THE_SWAMP_E, ItemID.UNCHARGED_TOXIC_TRIDENT, ItemID.UNCHARGED_TOXIC_TRIDENT_E,
<<<<<<< HEAD
<<<<<<< HEAD
			ItemID.VIGGORAS_CHAINMACE, ItemID.VIGGORAS_CHAINMACE_U };
=======
			ItemID.VIGGORAS_CHAINMACE, ItemID.VIGGORAS_CHAINMACE_U
	};
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
			ItemID.VIGGORAS_CHAINMACE, ItemID.VIGGORAS_CHAINMACE_U };
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97

	private int abyssalTentacleCharges, arclightCharges, crawsBowCharges, crystalBowCharges, crystalHalberdCharges,
			ibansStaffCharges, sanguinestiStaffCharges, saradominsBlessedSwordCharges, scytheOfViturCharges,
			//slayersStaffECharges,
			thammaronsSceptreCharges, tridentOfTheSeasCharges, tridentOfTheSwampCharges, viggorasChainmaceCharges;

	private int currentWeaponID; //keeps track of the weapon player is currently holding

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private WeaponChargesOverlay overlay;

	@Inject
	private ItemManager itemManager;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private Notifier notifier;

	@Inject
	private WeaponChargesConfig config;

<<<<<<< HEAD
<<<<<<< HEAD
=======
	// Limits destroy callback to once per tick
	private int lastCheckTick;
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97

	@Provides
    WeaponChargesConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(WeaponChargesConfig.class);
	}

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		infoBoxManager.removeIf(WeaponChargesInfobox.class::isInstance);
<<<<<<< HEAD
<<<<<<< HEAD
		// Limits destroy callback to once per tick
		int lastCheckTick = -1;
=======
		lastCheckTick = -1;
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
		// Limits destroy callback to once per tick
		int lastCheckTick = -1;
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
<<<<<<< HEAD
<<<<<<< HEAD
		boolean validWeapon = false;
		if (!event.getGroup().equals("weaponcharges"))
=======
		if (!event.getGroup().equals("itemCharge"))
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
		boolean validWeapon = false;
		if (!event.getGroup().equals("weaponcharges"))
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
		{
			return;
		}

		if (!config.showInfoboxes())
		{
			infoBoxManager.removeIf(WeaponChargesInfobox.class::isInstance);
			return;
		}
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
		currentWeaponID = client.getItemContainer(InventoryID.EQUIPMENT).getItem(EquipmentInventorySlot.WEAPON.getSlotIdx()).getId();
		for (int i : chargeableWeaponIDs) {
			if (currentWeaponID == i)
				validWeapon = true;
		}

		if (config.showInfoboxes() && validWeapon)
		{
			addInfobox(currentWeaponID);
		}
<<<<<<< HEAD

		if (!config.showAbyssalTentacleCharges() || (config.abyssalTentacle() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.ABYSSAL_TENTACLE);
		}
		if (!config.showArclightCharges() || (config.arclight() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.ARCLIGHT);
		}
		if (!config.showCrawsBowCharges() || (config.crawsBow() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.CRAWS_BOW);
		}
		if (!config.showCrystalBowCharges() || (config.crystalBow() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.CRYSTAL_BOW);
		}
		if (!config.showCrystalHalberdCharges() || (config.crystalHalberd() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.CRYSTAL_HALBERD);
		}
		if (!config.showIbansStaffCharges() || (config.ibansStaff() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.IBANS_STAFF);
		}
		if (!config.showSanguinestiStaffCharges() || (config.sanguinestiStaff() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.SANGUINESTI_STAFF);
		}
		if (!config.showSaradominsBlessedSwordCharges() || (config.saradominsBlessedSword() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.SARADOMINS_BLESSED_SWORD);
		}
		if (!config.showScytheOfViturCharges() || (config.scytheOfVitur() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.SCYTHE_OF_VITUR);
		}
		/*if (!config.showSlayersStaffECharges() || (config.slayersStaffE() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.SLAYERS_STAFF_E);
		}*/
		if (!config.showThammaronsSceptreCharges() || (config.thammaronsSceptre() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.THAMMARONS_SCEPTRE);
		}
		if (!config.showTridentOfTheSeasCharges() || (config.tridentOfTheSeas() > config.infoboxThreshold()))
=======
=======
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97

		if (!config.showAbyssalTentacleCharges() || (config.abyssalTentacle() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.ABYSSAL_TENTACLE);
		}
		if (!config.showArclightCharges() || (config.arclight() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.ARCLIGHT);
		}
		if (!config.showCrawsBowCharges() || (config.crawsBow() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.CRAWS_BOW);
		}
		if (!config.showCrystalBowCharges() || (config.crystalBow() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.CRYSTAL_BOW);
		}
		if (!config.showCrystalHalberdCharges() || (config.crystalHalberd() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.CRYSTAL_HALBERD);
		}
		if (!config.showIbansStaffCharges() || (config.ibansStaff() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.IBANS_STAFF);
		}
		if (!config.showSanguinestiStaffCharges() || (config.sanguinestiStaff() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.SANGUINESTI_STAFF);
		}
		if (!config.showSaradominsBlessedSwordCharges() || (config.saradominsBlessedSword() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.SARADOMINS_BLESSED_SWORD);
		}
		if (!config.showScytheOfViturCharges() || (config.scytheOfVitur() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.SCYTHE_OF_VITUR);
		}
		/*if (!config.showSlayersStaffECharges() || (config.slayersStaffE() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.SLAYERS_STAFF_E);
		}*/
		if (!config.showThammaronsSceptreCharges() || (config.thammaronsSceptre() > config.infoboxThreshold()))
		{
			removeInfobox(WeaponWithSlot.THAMMARONS_SCEPTRE);
		}
<<<<<<< HEAD
		if (!config.showTridentOfTheSeasCharges())
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
		if (!config.showTridentOfTheSeasCharges() || (config.tridentOfTheSeas() > config.infoboxThreshold()))
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
		{
			removeInfobox(WeaponWithSlot.TRIDENT_OF_THE_SEAS);
			removeInfobox(WeaponWithSlot.TRIDENT_OF_THE_SEAS_E);
			removeInfobox(WeaponWithSlot.TRIDENT_OF_THE_SEAS_FULL);
		}
<<<<<<< HEAD
<<<<<<< HEAD
		if (!config.showTridentOfTheSwampCharges() || (config.tridentOfTheSwamp() > config.infoboxThreshold()))
=======
		if (!config.showTridentOfTheSwampCharges())
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
		if (!config.showTridentOfTheSwampCharges() || (config.tridentOfTheSwamp() > config.infoboxThreshold()))
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
		{
			removeInfobox(WeaponWithSlot.TRIDENT_OF_THE_SWAMP);
			removeInfobox(WeaponWithSlot.TRIDENT_OF_THE_SWAMP_E);
		}
<<<<<<< HEAD
<<<<<<< HEAD
		if (!config.showViggorasChainmaceCharges() || (config.viggorasChainmace() > config.infoboxThreshold()))
=======
		if (!config.showViggorasChainmaceCharges())
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
		if (!config.showViggorasChainmaceCharges() || (config.viggorasChainmace() > config.infoboxThreshold()))
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
		{
			removeInfobox(WeaponWithSlot.VIGGORAS_CHAINMACE);
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		if (event.getType() == ChatMessageType.GAMEMESSAGE || event.getType() == ChatMessageType.SPAM)
		{
			String message = Text.removeTags(event.getMessage());
			Matcher abyssalTentacleCheckMatcher = ABYSSAL_TENTACLE_CHECK_PATTERN.matcher(message);
			Matcher arclightCheckMatcher = ARCLIGHT_CHECK_PATTERN.matcher(message);
			Matcher crawsBowCheckMatcher = CRAWS_BOW_CHECK_PATTERN.matcher(message);
			Matcher crystalBowCheckMatcher = CRYSTAL_BOW_CHECK_PATTERN.matcher(message);
			Matcher crystalHalberdCheckMatcher = CRYSTAL_HALBERD_CHECK_PATTERN.matcher(message);
			Matcher ibansStaffCheckMatcher = IBANS_STAFF_CHECK_PATTERN.matcher(message);
			Matcher sanguinestiStaffCheckMatcher = SANGUINESTI_STAFF_CHECK_PATTERN.matcher(message);
			Matcher saradominsBlessedSwordMatcher = SARADOMINS_BLESSED_SWORD_CHECK_PATTERN.matcher(message);
			Matcher scytheOfViturMatcher = SCYTHE_OF_VITUR_CHECK_PATTERN.matcher(message);
			//Matcher slayersStaffEMatcher = SLAYERS_STAFF_E_CHECK_PATTERN.matcher(message);
			Matcher thammaronsSceptreMatcher = THAMMARONS_SCEPTRE_CHECK_PATTERN.matcher(message);
			Matcher tridentCheckMatcher = TRIDENT_CHECK_PATTERN.matcher(message);
			Matcher viggorasChainmaceCheckMatcher = VIGGORAS_CHAINMACE_CHECK_PATTERN.matcher(message);

			currentWeaponID = client.getItemContainer(InventoryID.EQUIPMENT).getItem(EquipmentInventorySlot.WEAPON.getSlotIdx()).getId();

			if (abyssalTentacleCheckMatcher.find()) {
				abyssalTentacleCharges = findCharges(abyssalTentacleCheckMatcher.group(1), abyssalTentacleCheckMatcher.group(2));
				config.abyssalTentacle(abyssalTentacleCharges);
				if (config.showAbyssalTentacleCharges() && currentWeaponID == ItemID.ABYSSAL_TENTACLE) {
					addInfobox(WeaponWithSlot.ABYSSAL_TENTACLE, ItemID.ABYSSAL_TENTACLE, "Abyssal Tentacle");
				}
			}
			else if (arclightCheckMatcher.find()) {
				arclightCharges = Integer.parseInt(arclightCheckMatcher.group(1)); //findCharges method is unnecessary because no comma in values above 999
				config.arclight(arclightCharges);
				if (config.showArclightCharges() && currentWeaponID == ItemID.ARCLIGHT) {
					addInfobox(WeaponWithSlot.ARCLIGHT, ItemID.ARCLIGHT, "Arclight");
				}
			}
			else if (crawsBowCheckMatcher.find()) {
				crawsBowCharges = findCharges(crawsBowCheckMatcher.group(1), crawsBowCheckMatcher.group(2));
				config.crawsBow(crawsBowCharges);
				if (config.showCrawsBowCharges() && currentWeaponID == ItemID.CRAWS_BOW) {
					addInfobox(WeaponWithSlot.CRAWS_BOW, ItemID.CRAWS_BOW, "Craw's Bow");
				}
			}
			else if (crystalBowCheckMatcher.find()) {
				crystalBowCharges = findCharges(crystalBowCheckMatcher.group(1), crystalBowCheckMatcher.group(2));
				config.crystalBow(crystalBowCharges);
				if (config.showCrystalBowCharges() && currentWeaponID == ItemID.CRYSTAL_BOW) {
					addInfobox(WeaponWithSlot.CRYSTAL_BOW, ItemID.CRYSTAL_BOW, "Crystal Bow");
				}
			}
			else if (crystalHalberdCheckMatcher.find()) {
				crystalHalberdCharges = findCharges(crystalHalberdCheckMatcher.group(1), crystalHalberdCheckMatcher.group(2));
				config.crystalHalberd(crystalHalberdCharges);
				if (config.showCrystalHalberdCharges() && currentWeaponID == ItemID.CRYSTAL_HALBERD) {
					addInfobox(WeaponWithSlot.CRYSTAL_HALBERD, ItemID.CRYSTAL_HALBERD, "Crystal Halberd");
				}
			}
			else if (ibansStaffCheckMatcher.find()) {
				ibansStaffCharges = Integer.parseInt(ibansStaffCheckMatcher.group(1)); //findCharges method is unnecessary because no comma in values above 999
				config.ibansStaff(ibansStaffCharges);
				if (config.showIbansStaffCharges() && (currentWeaponID == ItemID.IBANS_STAFF || currentWeaponID == ItemID.IBANS_STAFF_U)) {
					addInfobox(WeaponWithSlot.IBANS_STAFF, ItemID.IBANS_STAFF, "Iban's Staff");
				}
			}
			else if (sanguinestiStaffCheckMatcher.find()) {
				sanguinestiStaffCharges = findCharges(sanguinestiStaffCheckMatcher.group(1), sanguinestiStaffCheckMatcher.group(2));
				config.sanguinestiStaff(sanguinestiStaffCharges);
				if (config.showSanguinestiStaffCharges() && currentWeaponID == ItemID.SANGUINESTI_STAFF) {
					addInfobox(WeaponWithSlot.SANGUINESTI_STAFF, ItemID.SANGUINESTI_STAFF, "Sanguinesti Staff");
				}
			}
			else if (saradominsBlessedSwordMatcher.find()) {
				saradominsBlessedSwordCharges = findCharges(saradominsBlessedSwordMatcher.group(1), saradominsBlessedSwordMatcher.group(2));
				config.saradominsBlessedSword(saradominsBlessedSwordCharges);
				if (config.showSaradominsBlessedSwordCharges() && currentWeaponID == ItemID.SARADOMINS_BLESSED_SWORD) {
					addInfobox(WeaponWithSlot.SARADOMINS_BLESSED_SWORD, ItemID.SARADOMINS_BLESSED_SWORD, "Saradomin's Blessed Sword");
				}
			}
			else if (scytheOfViturMatcher.find()) {
				scytheOfViturCharges = findCharges(scytheOfViturMatcher.group(1), scytheOfViturMatcher.group(2));
				config.scytheOfVitur(scytheOfViturCharges);
				if (config.showScytheOfViturCharges() && currentWeaponID == ItemID.SCYTHE_OF_VITUR) {
					addInfobox(WeaponWithSlot.SCYTHE_OF_VITUR, ItemID.SCYTHE_OF_VITUR, "Scythe of Vitur");
				}
			}
		/*	else if (slayersStaffEMatcher.find()) {
				slayersStaffECharges = findCharges(slayersStaffEMatcher.group(1), slayersStaffEMatcher.group(2));
				config.slayersStaffE(slayersStaffECharges);
				if (config.showSlayersStaffECharges() && currentWeaponID == ItemID.SLAYERS_STAFF_E) {
					addInfobox(WeaponWithSlot.SLAYERS_STAFF_E, ItemID.SLAYERS_STAFF_E, "Slayer's Staff (e)");
				}
			} */
			else if (thammaronsSceptreMatcher.find()) {
				thammaronsSceptreCharges = findCharges(thammaronsSceptreMatcher.group(1), thammaronsSceptreMatcher.group(2));
				config.thammaronsSceptre(thammaronsSceptreCharges);
				if (config.showThammaronsSceptreCharges() && currentWeaponID == ItemID.THAMMARONS_SCEPTRE) {
					addInfobox(WeaponWithSlot.THAMMARONS_SCEPTRE, ItemID.THAMMARONS_SCEPTRE, "Thammaron's Sceptre");
				}
			}
			else if (tridentCheckMatcher.find()) {
				//case that seas is in inventory or equipped
				if (client.getItemContainer(InventoryID.INVENTORY).contains(ItemID.TRIDENT_OF_THE_SEAS_FULL)
					||  currentWeaponID == ItemID.TRIDENT_OF_THE_SEAS_FULL
					||	client.getItemContainer(InventoryID.INVENTORY).contains(ItemID.TRIDENT_OF_THE_SEAS)
					||  currentWeaponID == ItemID.TRIDENT_OF_THE_SEAS
					||  client.getItemContainer(InventoryID.INVENTORY).contains(ItemID.TRIDENT_OF_THE_SEAS_E)
					||  currentWeaponID == ItemID.TRIDENT_OF_THE_SEAS_E) {
					tridentOfTheSeasCharges = findCharges(tridentCheckMatcher.group(1), tridentCheckMatcher.group(2));
					config.tridentOfTheSeas(tridentOfTheSeasCharges);
					if (config.showTridentOfTheSeasCharges() && (currentWeaponID == ItemID.TRIDENT_OF_THE_SEAS
						|| currentWeaponID == ItemID.TRIDENT_OF_THE_SEAS_E || currentWeaponID == ItemID.TRIDENT_OF_THE_SEAS_FULL)) {
						addInfobox(WeaponWithSlot.TRIDENT_OF_THE_SEAS, ItemID.TRIDENT_OF_THE_SEAS, "Trident of the Seas");
					}
				}
				//case that swamp is in inventory or equipped
				else if (client.getItemContainer(InventoryID.INVENTORY).contains(ItemID.TRIDENT_OF_THE_SWAMP)
					|| currentWeaponID == ItemID.TRIDENT_OF_THE_SWAMP
				    || client.getItemContainer(InventoryID.INVENTORY).contains(ItemID.TRIDENT_OF_THE_SWAMP_E)
					|| currentWeaponID == ItemID.TRIDENT_OF_THE_SWAMP_E) {
					tridentOfTheSwampCharges = findCharges(tridentCheckMatcher.group(1), tridentCheckMatcher.group(2));
					config.tridentOfTheSwamp(tridentOfTheSwampCharges);
					if (config.showTridentOfTheSwampCharges() && (currentWeaponID == ItemID.TRIDENT_OF_THE_SWAMP
							|| currentWeaponID == ItemID.TRIDENT_OF_THE_SWAMP_E)) {
						addInfobox(WeaponWithSlot.TRIDENT_OF_THE_SWAMP, ItemID.TRIDENT_OF_THE_SWAMP, "Trident of the Swamp");
					}
				}
			}
			else if (viggorasChainmaceCheckMatcher.find()) {
				viggorasChainmaceCharges = findCharges(viggorasChainmaceCheckMatcher.group(1), viggorasChainmaceCheckMatcher.group(2));
				config.viggorasChainmace(viggorasChainmaceCharges);
				if (config.showViggorasChainmaceCharges() && currentWeaponID == ItemID.VIGGORAS_CHAINMACE) {
					addInfobox(WeaponWithSlot.VIGGORAS_CHAINMACE, ItemID.VIGGORAS_CHAINMACE, "Viggora's Chainmace");
				}
			}
		}
	}

	private int findCharges(String group1, String group2) {
		if (group1 == null) {
			return Integer.parseInt(group2.replaceAll(",", ""));
		}
		else {
			return Integer.parseInt(group1);
		}
	}

	@Subscribe
	public void onAnimationChanged(AnimationChanged event) {
		if (event.getActor() != client.getLocalPlayer()) {
			return;
		}
		int animID = event.getActor().getAnimation();
		currentWeaponID = client.getItemContainer(InventoryID.EQUIPMENT)
									.getItem(EquipmentInventorySlot.WEAPON.getSlotIdx()).getId();

		final int WHIP_ATTACK = 1658;
		final int ONEHAND_SLASH_SWORD = 390, ONEHAND_STAB_SWORD = 386, ARCLIGHT_SPEC = 2890;
		final int BOW_ATTACK = 426;
		final int HALBERD_STAB = 428, HALBERD_SLASH = 440, HALBERD_SPEC = 1203;
		final int IBANS_STAFF_ANIMATION = 708;
		final int HIGH_LEVEL_MAGIC_ATTACK = 1167;
		final int ATTACK_SLASH = 7045, ATTACK_SLASH2 = 7055, ATTACK_CRUSH = 7054, SARADOMIN_SWORD_SPEC = 1133;
		final int SCYTHE_OF_VITUR_ANIMATION = 8056;
		//final int SLAYERS_STAFF_ANIMATION = 1576;
		final int STRIKE_BOLT_BLAST = 1162, CRUMBLE_UNDEAD = 1166, SURGE_SPELLS = 7855,
				RUSH_BLITZ = 1978, BURST_BARRAGE = 1979;
		final int VIGGORAS_ATTACK = 245;

		if (animID == WHIP_ATTACK && currentWeaponID == ItemID.ABYSSAL_TENTACLE) {
			config.abyssalTentacle(--abyssalTentacleCharges);
			if (config.showAbyssalTentacleCharges()) {
				addInfobox(WeaponWithSlot.ABYSSAL_TENTACLE, ItemID.ABYSSAL_TENTACLE, "Abyssal Tentacle");
			}
		}
		else if ((animID == ONEHAND_SLASH_SWORD || animID == ONEHAND_STAB_SWORD || animID == ARCLIGHT_SPEC) &&
				currentWeaponID == ItemID.ARCLIGHT) {
			config.arclight(--arclightCharges);
			if (config.showArclightCharges()) {
				addInfobox(WeaponWithSlot.ARCLIGHT, ItemID.ARCLIGHT, "Arclight");
			}
		}
		else if (animID == BOW_ATTACK && currentWeaponID == ItemID.CRAWS_BOW) {
			config.crawsBow(--crawsBowCharges);
			if (config.showCrawsBowCharges()) {
				addInfobox(WeaponWithSlot.CRAWS_BOW, ItemID.CRAWS_BOW, "Craw's Bow");
			}
		}
		else if (animID == BOW_ATTACK && currentWeaponID == ItemID.CRYSTAL_BOW) {
			config.crystalBow(--crystalBowCharges);
			if (config.showCrystalBowCharges()) {
				addInfobox(WeaponWithSlot.CRYSTAL_BOW, ItemID.CRYSTAL_BOW, "Crystal Bow");
			}
		}
		else if ((animID == HALBERD_STAB || animID == HALBERD_SLASH || animID == HALBERD_SPEC) &&
				currentWeaponID == ItemID.CRYSTAL_HALBERD) {
			config.crystalHalberd(--crystalHalberdCharges);
			if (config.showCrystalHalberdCharges()) {
				addInfobox(WeaponWithSlot.CRYSTAL_HALBERD, ItemID.CRYSTAL_HALBERD, "Crystal Halberd");
			}
		}
		else if (animID == IBANS_STAFF_ANIMATION && (currentWeaponID == ItemID.IBANS_STAFF ||
				currentWeaponID == ItemID.IBANS_STAFF_U)) {
			config.ibansStaff(--ibansStaffCharges);
			if (config.showIbansStaffCharges()) {
				addInfobox(WeaponWithSlot.IBANS_STAFF, ItemID.IBANS_STAFF, "Iban's Staff");
			}
		}
		else if (animID == HIGH_LEVEL_MAGIC_ATTACK && currentWeaponID == ItemID.SANGUINESTI_STAFF) {
			config.sanguinestiStaff(--sanguinestiStaffCharges);
			if (config.showSanguinestiStaffCharges()) {
				addInfobox(WeaponWithSlot.SANGUINESTI_STAFF, ItemID.SANGUINESTI_STAFF, "Sanguinesti Staff");
			}
		}
		else if ((animID == ATTACK_SLASH || animID == ATTACK_SLASH2 || animID == ATTACK_CRUSH ||
				animID == SARADOMIN_SWORD_SPEC) && currentWeaponID == ItemID.SARADOMINS_BLESSED_SWORD) {
			config.saradominsBlessedSword(--saradominsBlessedSwordCharges);
			if (config.showSaradominsBlessedSwordCharges()) {
				addInfobox(WeaponWithSlot.SARADOMINS_BLESSED_SWORD, ItemID.SARADOMINS_BLESSED_SWORD, "Saradomin's Blessed Sword");
			}
		}
		else if (animID == SCYTHE_OF_VITUR_ANIMATION && currentWeaponID == ItemID.SCYTHE_OF_VITUR) {
			config.scytheOfVitur(--scytheOfViturCharges);
			if (config.showScytheOfViturCharges()) {
				addInfobox(WeaponWithSlot.SCYTHE_OF_VITUR, ItemID.SCYTHE_OF_VITUR, "Scythe of Vitur");
			}
		}
		//else if (animID == SLAYERS_STAFF_ANIMATION && currentWeaponID == ItemID.SLAYERS_STAFF_E) {
		//	config.slayersStaffE(--slayersStaffECharges);
		//	if (config.showSlayersStaffECharges()) {
		//		addInfobox(WeaponWithSlot.SLAYERS_STAFF_E, ItemID.SLAYERS_STAFF_E, "Slayer's Staff (e)");
		//	}
		//}
		else if ((animID == STRIKE_BOLT_BLAST || animID == CRUMBLE_UNDEAD || animID == HIGH_LEVEL_MAGIC_ATTACK ||
				animID == SURGE_SPELLS || animID == RUSH_BLITZ || animID == BURST_BARRAGE)
				&& currentWeaponID == ItemID.THAMMARONS_SCEPTRE) {
			config.thammaronsSceptre(--thammaronsSceptreCharges);
			if (config.showThammaronsSceptreCharges()) {
				addInfobox(WeaponWithSlot.THAMMARONS_SCEPTRE, ItemID.THAMMARONS_SCEPTRE, "Thammaron's Sceptre");
			}
		}
		else if (animID == HIGH_LEVEL_MAGIC_ATTACK && (currentWeaponID == ItemID.TRIDENT_OF_THE_SEAS ||
				currentWeaponID == ItemID.TRIDENT_OF_THE_SEAS_E || currentWeaponID == ItemID.TRIDENT_OF_THE_SEAS_FULL)) {
			config.tridentOfTheSeas(--tridentOfTheSeasCharges);
			if (config.showTridentOfTheSeasCharges()) {
				addInfobox(WeaponWithSlot.TRIDENT_OF_THE_SEAS, ItemID.TRIDENT_OF_THE_SEAS, "Trident of the Seas");
			}
		}
		else if (animID == HIGH_LEVEL_MAGIC_ATTACK && (currentWeaponID == ItemID.TRIDENT_OF_THE_SWAMP ||
				currentWeaponID == ItemID.TRIDENT_OF_THE_SWAMP_E)) {
			config.tridentOfTheSwamp(--tridentOfTheSwampCharges);
			if (config.showTridentOfTheSwampCharges()) {
				addInfobox(WeaponWithSlot.TRIDENT_OF_THE_SWAMP, ItemID.TRIDENT_OF_THE_SWAMP, "Trident of the Swamp");
			}
		}
		else if (animID == VIGGORAS_ATTACK && currentWeaponID == ItemID.VIGGORAS_CHAINMACE) {
			config.viggorasChainmace(--viggorasChainmaceCharges);
			if (config.showViggorasChainmaceCharges()) {
				addInfobox(WeaponWithSlot.VIGGORAS_CHAINMACE, ItemID.VIGGORAS_CHAINMACE, "Viggora's Chainmace");
			}
		}
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		int newWeaponID = client.getItemContainer(InventoryID.EQUIPMENT)
				.getItem(EquipmentInventorySlot.WEAPON.getSlotIdx()).getId();

		if (newWeaponID != currentWeaponID) {
			removeAllInfoboxes();
		}
		currentWeaponID = newWeaponID; //update our current weapon since we equipped a new weapon
		boolean validWeapon = false;
		for (int i : chargeableWeaponIDs) {
			if (i == newWeaponID) {
				validWeapon = true;
			}
		}
		if (validWeapon) {
			if (newWeaponID == ItemID.ABYSSAL_TENTACLE && config.showAbyssalTentacleCharges()) {
<<<<<<< HEAD
<<<<<<< HEAD
				addInfobox(ItemID.ABYSSAL_TENTACLE);
			}
			if (newWeaponID == ItemID.ARCLIGHT && config.showArclightCharges()) {
				addInfobox(ItemID.ARCLIGHT);
			}
			if (newWeaponID == ItemID.CRAWS_BOW && config.showCrawsBowCharges()) {
				addInfobox(ItemID.CRAWS_BOW);
			}
			if (newWeaponID == ItemID.CRYSTAL_BOW && config.showCrystalBowCharges()) {
				addInfobox(ItemID.CRYSTAL_BOW);
			}
			if (newWeaponID == ItemID.CRYSTAL_HALBERD && config.showCrystalHalberdCharges()) {
				addInfobox(ItemID.CRYSTAL_HALBERD);
			}
			if ((newWeaponID == ItemID.IBANS_STAFF || newWeaponID == ItemID.IBANS_STAFF_U) && config.showIbansStaffCharges()) {
				addInfobox(ItemID.IBANS_STAFF);
			}
			if (newWeaponID == ItemID.SANGUINESTI_STAFF && config.showSanguinestiStaffCharges()) {
				addInfobox(ItemID.SANGUINESTI_STAFF);
			}
			if (newWeaponID == ItemID.SARADOMINS_BLESSED_SWORD && config.showSaradominsBlessedSwordCharges()) {
				addInfobox(ItemID.SARADOMINS_BLESSED_SWORD);
			}
			if (newWeaponID == ItemID.SCYTHE_OF_VITUR && config.showScytheOfViturCharges()) {
				addInfobox(ItemID.SCYTHE_OF_VITUR);
			}
		/*	if (newWeaponID == ItemID.SLAYERS_STAFF_E && config.showSlayersStaffECharges()) {
				addInfobox(ItemID.SLAYERS_STAFF_E);
			}*/
			if (newWeaponID == ItemID.THAMMARONS_SCEPTRE && config.showThammaronsSceptreCharges()) {
				addInfobox(ItemID.THAMMARONS_SCEPTRE);
			}
			if ((newWeaponID == ItemID.TRIDENT_OF_THE_SEAS || newWeaponID == ItemID.TRIDENT_OF_THE_SEAS_E ||
					newWeaponID == ItemID.TRIDENT_OF_THE_SEAS_FULL) && config.showTridentOfTheSeasCharges()) {
				addInfobox(ItemID.TRIDENT_OF_THE_SEAS);
			}
			if ((newWeaponID == ItemID.TRIDENT_OF_THE_SWAMP || newWeaponID == ItemID.TRIDENT_OF_THE_SWAMP_E) &&
					config.showTridentOfTheSwampCharges()) {
				addInfobox(ItemID.TRIDENT_OF_THE_SWAMP_E);
			}
			if (newWeaponID == ItemID.VIGGORAS_CHAINMACE && config.showViggorasChainmaceCharges()) {
				addInfobox(ItemID.VIGGORAS_CHAINMACE);
=======
				addInfobox(WeaponWithSlot.ABYSSAL_TENTACLE, ItemID.ABYSSAL_TENTACLE, "Abyssal Tentacle");
=======
				addInfobox(ItemID.ABYSSAL_TENTACLE);
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			}
			if (newWeaponID == ItemID.ARCLIGHT && config.showArclightCharges()) {
				addInfobox(ItemID.ARCLIGHT);
			}
			if (newWeaponID == ItemID.CRAWS_BOW && config.showCrawsBowCharges()) {
				addInfobox(ItemID.CRAWS_BOW);
			}
			if (newWeaponID == ItemID.CRYSTAL_BOW && config.showCrystalBowCharges()) {
				addInfobox(ItemID.CRYSTAL_BOW);
			}
			if (newWeaponID == ItemID.CRYSTAL_HALBERD && config.showCrystalHalberdCharges()) {
				addInfobox(ItemID.CRYSTAL_HALBERD);
			}
			if ((newWeaponID == ItemID.IBANS_STAFF || newWeaponID == ItemID.IBANS_STAFF_U) && config.showIbansStaffCharges()) {
				addInfobox(ItemID.IBANS_STAFF);
			}
			if (newWeaponID == ItemID.SANGUINESTI_STAFF && config.showSanguinestiStaffCharges()) {
				addInfobox(ItemID.SANGUINESTI_STAFF);
			}
			if (newWeaponID == ItemID.SARADOMINS_BLESSED_SWORD && config.showSaradominsBlessedSwordCharges()) {
				addInfobox(ItemID.SARADOMINS_BLESSED_SWORD);
			}
			if (newWeaponID == ItemID.SCYTHE_OF_VITUR && config.showScytheOfViturCharges()) {
				addInfobox(ItemID.SCYTHE_OF_VITUR);
			}
		/*	if (newWeaponID == ItemID.SLAYERS_STAFF_E && config.showSlayersStaffECharges()) {
				addInfobox(ItemID.SLAYERS_STAFF_E);
			}*/
			if (newWeaponID == ItemID.THAMMARONS_SCEPTRE && config.showThammaronsSceptreCharges()) {
				addInfobox(ItemID.THAMMARONS_SCEPTRE);
			}
			if ((newWeaponID == ItemID.TRIDENT_OF_THE_SEAS || newWeaponID == ItemID.TRIDENT_OF_THE_SEAS_E ||
					newWeaponID == ItemID.TRIDENT_OF_THE_SEAS_FULL) && config.showTridentOfTheSeasCharges()) {
				addInfobox(ItemID.TRIDENT_OF_THE_SEAS);
			}
			if ((newWeaponID == ItemID.TRIDENT_OF_THE_SWAMP || newWeaponID == ItemID.TRIDENT_OF_THE_SWAMP_E) &&
					config.showTridentOfTheSwampCharges()) {
				addInfobox(ItemID.TRIDENT_OF_THE_SWAMP_E);
			}
			if (newWeaponID == ItemID.VIGGORAS_CHAINMACE && config.showViggorasChainmaceCharges()) {
<<<<<<< HEAD
				addInfobox(WeaponWithSlot.VIGGORAS_CHAINMACE, ItemID.VIGGORAS_CHAINMACE, "Viggora's Chainmace");
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
				addInfobox(ItemID.VIGGORAS_CHAINMACE);
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
			}
		}
	}

	@Subscribe
	private void onScriptCallbackEvent(ScriptCallbackEvent event)
	{
		if (!"destroyOnOpKey".equals(event.getEventName()))
		{
			return;
		}

		final int yesOption = client.getIntStack()[client.getIntStackSize() - 1];
		if (yesOption == 1)
		{
			//checkDestroyWidget();
		}
	}

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
	private void addInfobox(int id) {
		if (id == ItemID.ABYSSAL_TENTACLE) {
			addInfobox(WeaponWithSlot.ABYSSAL_TENTACLE, id, "Abyssal Tentacle");
		}
		else if (id == ItemID.ARCLIGHT) {
			addInfobox(WeaponWithSlot.ARCLIGHT, id, "Arclight");
		}
		else if (id == ItemID.CRAWS_BOW) {
			addInfobox(WeaponWithSlot.CRAWS_BOW, id, "Craw's Bow");
		}
		else if (id == ItemID.CRYSTAL_BOW) {
			addInfobox(WeaponWithSlot.CRYSTAL_BOW, id, "Crystal Bow");
		}
		else if (id == ItemID.CRYSTAL_HALBERD) {
			addInfobox(WeaponWithSlot.CRYSTAL_HALBERD, id, "Crystal Halberd");
		}
		else if (id == ItemID.IBANS_STAFF || id == ItemID.IBANS_STAFF_U) {
			addInfobox(WeaponWithSlot.IBANS_STAFF, id, "Iban's Staff");
		}
		else if (id == ItemID.SANGUINESTI_STAFF) {
			addInfobox(WeaponWithSlot.SANGUINESTI_STAFF, id, "Sanguinesti Staff");
		}
		else if (id == ItemID.SARADOMINS_BLESSED_SWORD) {
			addInfobox(WeaponWithSlot.SARADOMINS_BLESSED_SWORD, id, "Saradomin's Blessed Sword");
		}
		else if (id == ItemID.SCYTHE_OF_VITUR) {
			addInfobox(WeaponWithSlot.SCYTHE_OF_VITUR, id, "Scythe of Vitur");
		}
		//else if (id == ItemID.SLAYERS_STAFF_E) {
			//addInfobox(WeaponWithSlot.SLAYERS_STAFF_E, id, "Slayer's Staff (e)");
		//}
		else if (id == ItemID.THAMMARONS_SCEPTRE) {
			addInfobox(WeaponWithSlot.THAMMARONS_SCEPTRE, id, "Thammaron's Sceptre");
		}
		else if (id == ItemID.TRIDENT_OF_THE_SEAS || id == ItemID.TRIDENT_OF_THE_SEAS_FULL || id == ItemID.TRIDENT_OF_THE_SEAS_E) {
			addInfobox(WeaponWithSlot.TRIDENT_OF_THE_SEAS, id, "Trident of the Seas");
		}
		else if (id == ItemID.TRIDENT_OF_THE_SWAMP || id == ItemID.TRIDENT_OF_THE_SWAMP_E) {
			addInfobox(WeaponWithSlot.TRIDENT_OF_THE_SWAMP, id, "Trident of the Swamp");
		}
		else if (id == ItemID.VIGGORAS_CHAINMACE) {
			addInfobox(WeaponWithSlot.VIGGORAS_CHAINMACE, id, "Viggora's Chainmace");
		}
	}

<<<<<<< HEAD
=======
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
	private void addInfobox(WeaponWithSlot type, int id, String name) {
		if (!config.showInfoboxes()) return; //leave instantly since infoboxes are off
		removeInfobox(type);
		int charges;

		if (type == WeaponWithSlot.ABYSSAL_TENTACLE) {
			charges = config.abyssalTentacle();
		}
		else if (type == WeaponWithSlot.ARCLIGHT) {
			charges = config.arclight();
		}
		else if (type == WeaponWithSlot.CRAWS_BOW) {
			charges = config.crawsBow();
		}
		else if (type == WeaponWithSlot.CRYSTAL_BOW) {
			charges = config.crystalBow();
		}
		else if (type == WeaponWithSlot.CRYSTAL_HALBERD) {
			charges = config.crystalHalberd();
		}
		else if (type == WeaponWithSlot.IBANS_STAFF) {
			charges = config.ibansStaff();
		}
		else if (type == WeaponWithSlot.SANGUINESTI_STAFF) {
			charges = config.sanguinestiStaff();
		}
		else if (type == WeaponWithSlot.SARADOMINS_BLESSED_SWORD) {
			charges = config.saradominsBlessedSword();
		}
		else if (type == WeaponWithSlot.SCYTHE_OF_VITUR) {
			charges = config.scytheOfVitur();
		}
	/*	else if (type == WeaponWithSlot.SLAYERS_STAFF_E) {
			charges = config.slayersStaffE();
		}*/
		else if (type == WeaponWithSlot.THAMMARONS_SCEPTRE) {
			charges = config.thammaronsSceptre();
		}
		else if (type == WeaponWithSlot.TRIDENT_OF_THE_SEAS || type == WeaponWithSlot.TRIDENT_OF_THE_SEAS_E ||
				type == WeaponWithSlot.TRIDENT_OF_THE_SEAS_FULL) {
			charges = config.tridentOfTheSeas();
		}
		else if (type == WeaponWithSlot.TRIDENT_OF_THE_SWAMP || type == WeaponWithSlot.TRIDENT_OF_THE_SWAMP_E) {
			charges = config.tridentOfTheSwamp();
		}
		else if (type == WeaponWithSlot.VIGGORAS_CHAINMACE) {
			charges = config.viggorasChainmace();
		}
		else {
			charges = -1;
		}

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97
		if (charges <= 0 || charges > config.infoboxThreshold())
		{
			return;
		}
<<<<<<< HEAD
=======
		if (charges <= 0) { return; }
>>>>>>> dc13b68... Base Commit, Plugin functional but not perfect
=======
>>>>>>> 9e9a4d34477107e44cc06bf79a1777c26fbe7e97


		final BufferedImage image = itemManager.getImage(id);
		final WeaponChargesInfobox infobox = new WeaponChargesInfobox(this, image, name, charges, type);
		infoBoxManager.addInfoBox(infobox);
	}

	private void removeInfobox(final WeaponWithSlot weapon)
	{
		infoBoxManager.removeIf(t -> t instanceof WeaponChargesInfobox && ((WeaponChargesInfobox) t).getWeapon() == weapon);
	}

	private void removeAllInfoboxes() {
		removeInfobox(WeaponWithSlot.ABYSSAL_TENTACLE);
		removeInfobox(WeaponWithSlot.ARCLIGHT);
		removeInfobox(WeaponWithSlot.CRAWS_BOW);
		removeInfobox(WeaponWithSlot.CRYSTAL_BOW);
		removeInfobox(WeaponWithSlot.CRYSTAL_HALBERD);
		removeInfobox(WeaponWithSlot.IBANS_STAFF);
		removeInfobox(WeaponWithSlot.SANGUINESTI_STAFF);
		removeInfobox(WeaponWithSlot.SARADOMINS_BLESSED_SWORD);
		removeInfobox(WeaponWithSlot.SCYTHE_OF_VITUR);
		//removeInfobox(WeaponWithSlot.SLAYERS_STAFF_E);
		removeInfobox(WeaponWithSlot.THAMMARONS_SCEPTRE);
		removeInfobox(WeaponWithSlot.TRIDENT_OF_THE_SEAS);
		removeInfobox(WeaponWithSlot.TRIDENT_OF_THE_SEAS_E);
		removeInfobox(WeaponWithSlot.TRIDENT_OF_THE_SEAS_FULL);
		removeInfobox(WeaponWithSlot.TRIDENT_OF_THE_SWAMP);
		removeInfobox(WeaponWithSlot.TRIDENT_OF_THE_SWAMP_E);
		removeInfobox(WeaponWithSlot.VIGGORAS_CHAINMACE);
	}

	Color getColor(int charges)
	{
		Color color = Color.WHITE;
		if (charges <= config.veryLowWarning())
		{
			color = config.veryLowWarningColor();
		}
		else if (charges <= config.lowWarning())
		{
			color = config.lowWarningolor();
		}
		return color;
	}
}
