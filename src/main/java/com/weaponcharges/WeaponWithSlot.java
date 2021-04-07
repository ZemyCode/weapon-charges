/*
 * Copyright (c) 2019, Tomas Slusny <slusnucky@gmail.com>
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

import com.google.common.collect.Sets;
import java.util.Set;
import lombok.Getter;
import net.runelite.api.EquipmentInventorySlot;

@Getter
enum WeaponWithSlot
{
	ABYSSAL_TENTACLE(WeaponChargesType.ABYSSAL_TENTACLE, EquipmentInventorySlot.WEAPON),
	ARCLIGHT(WeaponChargesType.ARCLIGHT, EquipmentInventorySlot.WEAPON),
	CRAWS_BOW(WeaponChargesType.CRAWS_BOW, EquipmentInventorySlot.WEAPON),
	CRYSTAL_BOW(WeaponChargesType.CRYSTAL_BOW, EquipmentInventorySlot.WEAPON),
	CRYSTAL_HALBERD(WeaponChargesType.CRYSTAL_HALBERD, EquipmentInventorySlot.WEAPON),
	IBANS_STAFF(WeaponChargesType.IBANS_STAFF, EquipmentInventorySlot.WEAPON),
	SANGUINESTI_STAFF(WeaponChargesType.SANGUINESTI_STAFF, EquipmentInventorySlot.WEAPON),
	SARADOMINS_BLESSED_SWORD(WeaponChargesType.SARADOMINS_BLESSED_SWORD, EquipmentInventorySlot.WEAPON),
	SCYTHE_OF_VITUR(WeaponChargesType.SCYTHE_OF_VITUR, EquipmentInventorySlot.WEAPON),
	//SLAYERS_STAFF_E(WeaponChargesType.SLAYERS_STAFF_E, EquipmentInventorySlot.WEAPON),
	THAMMARONS_SCEPTRE(WeaponChargesType.THAMMARONS_SCEPTRE, EquipmentInventorySlot.WEAPON),
	TRIDENT_OF_THE_SEAS(WeaponChargesType.TRIDENT_OF_THE_SEAS, EquipmentInventorySlot.WEAPON),
	TRIDENT_OF_THE_SEAS_E(WeaponChargesType.TRIDENT_OF_THE_SEAS_E, EquipmentInventorySlot.WEAPON),
	TRIDENT_OF_THE_SEAS_FULL(WeaponChargesType.TRIDENT_OF_THE_SEAS_FULL, EquipmentInventorySlot.WEAPON),
	TRIDENT_OF_THE_SWAMP(WeaponChargesType.TRIDENT_OF_THE_SWAMP, EquipmentInventorySlot.WEAPON),
	TRIDENT_OF_THE_SWAMP_E(WeaponChargesType.TRIDENT_OF_THE_SWAMP_E, EquipmentInventorySlot.WEAPON),
	VIGGORAS_CHAINMACE(WeaponChargesType.VIGGORAS_CHAINMACE, EquipmentInventorySlot.WEAPON);

	private final WeaponChargesType type;
	private final Set<EquipmentInventorySlot> slots;

	WeaponWithSlot(final WeaponChargesType type, final EquipmentInventorySlot... slots)
	{
		this.type = type;
		this.slots = Sets.newHashSet(slots);
	}
}
