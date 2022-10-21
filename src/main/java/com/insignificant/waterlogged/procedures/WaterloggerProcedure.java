package com.insignificant.waterlogged.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import java.util.Map;
import java.util.HashMap;

import com.insignificant.waterlogged.WaterloggedMod;

public class WaterloggerProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WaterloggedMod.LOGGER.warn("Failed to load dependency world for procedure Waterlogger!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WaterloggedMod.LOGGER.warn("Failed to load dependency x for procedure Waterlogger!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WaterloggedMod.LOGGER.warn("Failed to load dependency y for procedure Waterlogger!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WaterloggedMod.LOGGER.warn("Failed to load dependency z for procedure Waterlogger!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		BlockState test = Blocks.AIR.getDefaultState();
		sx = (-8);
		found = (false);
		for (int index0 = 0; index0 < (int) (16); index0++) {
			sy = (-8);
			for (int index1 = 0; index1 < (int) (16); index1++) {
				sz = (-8);
				for (int index2 = 0; index2 < (int) (16); index2++) {
					if ((world.getBlockState(new BlockPos(x + sx, y + sy, z + sz))).getBlock() == Blocks.IRON_BLOCK) {
						found = (true);
						if ((world.getBlockState(new BlockPos(x + sx, y + sy + 1, z + sz))).getBlock() == Blocks.WATER) {
							world.setBlockState(new BlockPos(x + sx, y + sy, z + sz), Blocks.GOLD_BLOCK.getDefaultState(), 3);
						}
						if ((world.getBlockState(new BlockPos((x + sx) - 1, y + sy, z + sz))).getBlock() == Blocks.WATER) {
							world.setBlockState(new BlockPos(x + sx, y + sy, z + sz), Blocks.GOLD_BLOCK.getDefaultState(), 3);
						}
						if ((world.getBlockState(new BlockPos(x + sx + 1, y + sy, z + sz))).getBlock() == Blocks.WATER) {
							world.setBlockState(new BlockPos(x + sx, y + sy, z + sz), Blocks.GOLD_BLOCK.getDefaultState(), 3);
						}
						if ((world.getBlockState(new BlockPos(x + sx, y + sy, (z + sz) - 1))).getBlock() == Blocks.WATER) {
							world.setBlockState(new BlockPos(x + sx, y + sy, z + sz), Blocks.GOLD_BLOCK.getDefaultState(), 3);
						}
						if ((world.getBlockState(new BlockPos(x + sx, y + sy, z + sz + 1))).getBlock() == Blocks.WATER) {
							world.setBlockState(new BlockPos(x + sx, y + sy, z + sz), Blocks.GOLD_BLOCK.getDefaultState(), 3);
						}
					}
					sz = (sz + 1);
				}
				sy = (sy + 1);
			}
			sx = (sx + 1);
		}
	}
}
