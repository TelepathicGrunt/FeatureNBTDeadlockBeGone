package com.telepathicgrunt.featurenbtdeadlockbegone.mixin;

import net.minecraft.world.Clearable;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StructureTemplate.class)
public abstract class StructureTemplateMixin {

	/**
	 * Fixed worldgen deadlock where vanilla calls setChanged when a nbt piece replaces a block entity during worldgen.
	 * https://bugs.mojang.com/browse/MC-246262
	 * @author TelepathicGrunt
	 */
	@Redirect(
			method = "placeInWorld(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructurePlaceSettings;Ljava/util/Random;I)Z",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntity;setChanged()V"),
			require = 1
	)
	private void featurenbtdeadlockbegone_fixStructureTemplateDeadlock1(BlockEntity instance, ServerLevelAccessor level) {
		if(!(level instanceof WorldGenLevel)) {
			instance.setChanged();
		}
	}


	/**
	 * Fixed worldgen deadlock where vanilla calls setChanged when a nbt piece replaces a block entity during worldgen.
	 * https://bugs.mojang.com/browse/MC-246262
	 * @author TelepathicGrunt
	 */
	@Redirect(
			method = "placeInWorld(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructurePlaceSettings;Ljava/util/Random;I)Z",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/Clearable;tryClear(Ljava/lang/Object;)V"),
			require = 1
	)
	private void featurenbtdeadlockbegone_fixStructureTemplateDeadlock2(Object instance, ServerLevelAccessor level) {
		if(!(level instanceof WorldGenLevel)) {
			Clearable.tryClear(instance);
		}
	}
}
