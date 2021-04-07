/*
 * Copyright (c) 2017, Seth <Sethtroll3@gmail.com>
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

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import javax.inject.Inject;
import net.runelite.api.ItemID;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.client.ui.overlay.components.TextComponent;

class WeaponChargesOverlay extends WidgetItemOverlay
{
	private final WeaponChargesPlugin weaponChargePlugin;
	private final WeaponChargesConfig config;

	@Inject
    WeaponChargesOverlay(WeaponChargesPlugin weaponChargePlugin, WeaponChargesConfig config)
	{
		this.weaponChargePlugin = weaponChargePlugin;
		this.config = config;
		showOnInventory();
		showOnEquipment();
	}

	@Override
	public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem)
	{
		if (!displayOverlay())
		{
			return;
		}

		graphics.setFont(FontManager.getRunescapeSmallFont());

		int charges;
		if (itemId == ItemID.ABYSSAL_TENTACLE)
		{
			if (!config.showAbyssalTentacleCharges())
			{
				return;
			}

			charges = config.abyssalTentacle();
		}
		else if (itemId == ItemID.ARCLIGHT)
		{
			if (!config.showArclightCharges())
			{
				return;
			}

			charges = config.arclight();
		}
		else if (itemId == ItemID.CRAWS_BOW)
		{
			if (!config.showCrawsBowCharges())
			{
				return;
			}

			charges = config.crawsBow();
		}
		else if (itemId == ItemID.CRYSTAL_BOW)
		{
			if (!config.showCrystalBowCharges())
			{
				return;
			}

			charges = config.crystalBow();
		}
		else if (itemId == ItemID.CRYSTAL_HALBERD)
		{
			if (!config.showCrystalHalberdCharges())
			{
				return;
			}

			charges = config.crystalHalberd();
		}
		else if (itemId == ItemID.IBANS_STAFF || itemId == ItemID.IBANS_STAFF_U)
		{
			if (!config.showIbansStaffCharges())
			{
				return;
			}

			charges = config.ibansStaff();
		}
		else if (itemId == ItemID.SANGUINESTI_STAFF)
		{
			if (!config.showSanguinestiStaffCharges())
			{
				return;
			}

			charges = config.sanguinestiStaff();
		}
		else if (itemId == ItemID.SARADOMINS_BLESSED_SWORD)
		{
			if (!config.showSaradominsBlessedSwordCharges())
			{
				return;
			}

			charges = config.saradominsBlessedSword();
		}
		else if (itemId == ItemID.SCYTHE_OF_VITUR)
		{
			if (!config.showScytheOfViturCharges())
			{
				return;
			}

			charges = config.scytheOfVitur();
		}
	/*	else if (itemId == ItemID.SLAYERS_STAFF_E)
		{
			if (!config.showSlayersStaffECharges())
			{
				return;
			}

			charges = config.slayersStaffE();
		}*/
		else if (itemId == ItemID.THAMMARONS_SCEPTRE)
		{
			if (!config.showThammaronsSceptreCharges())
			{
				return;
			}

			charges = config.thammaronsSceptre();
		}
		else if (itemId == ItemID.TRIDENT_OF_THE_SEAS || itemId == ItemID.TRIDENT_OF_THE_SEAS_E || itemId == ItemID.TRIDENT_OF_THE_SEAS_FULL)
		{
			if (!config.showTridentOfTheSeasCharges())
			{
				return;
			}

			charges = config.tridentOfTheSeas();
		}
		else if (itemId == ItemID.TRIDENT_OF_THE_SWAMP || itemId == ItemID.TRIDENT_OF_THE_SWAMP_E)
		{
			if (!config.showTridentOfTheSwampCharges())
			{
				return;
			}

			charges = config.tridentOfTheSwamp();
		}
		else if (itemId == ItemID.VIGGORAS_CHAINMACE)
		{
			if (!config.showViggorasChainmaceCharges())
			{
				return;
			}

			charges = config.viggorasChainmace();
		}
		else
		{
			return;
		}

		final Rectangle bounds = widgetItem.getCanvasBounds();
		final TextComponent textComponent = new TextComponent();
		textComponent.setPosition(new Point(bounds.x - 1, bounds.y + 15));
		textComponent.setText(charges < 0 ? "?" : String.valueOf(charges));
		textComponent.setColor(weaponChargePlugin.getColor(charges));
		if (charges <= config.chargesThreshold()) {
			textComponent.render(graphics);
		}
	}

	private boolean displayOverlay()
	{
		return config.showAbyssalTentacleCharges() || config.showArclightCharges() || config.showCrawsBowCharges() ||
				config.showCrystalBowCharges() || config.showCrystalHalberdCharges() || config.showIbansStaffCharges() ||
				config.showSanguinestiStaffCharges() || config.showSaradominsBlessedSwordCharges() ||
				config. showScytheOfViturCharges()
				//|| config.showSlayersStaffECharges()
				|| config.showThammaronsSceptreCharges() ||
				config.showTridentOfTheSeasCharges() || config.showTridentOfTheSwampCharges() || config.showViggorasChainmaceCharges();
	}
}
