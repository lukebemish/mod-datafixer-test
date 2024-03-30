package modb;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModB implements ModInitializer {
    public static final Item B_INNER = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("mod_b", "b_inner"), new Item(new Item.Properties()));
    public static final Block BLOCK_OUTER = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation("mod_b", "b_outer"),
            new BlockB(BlockBehaviour.Properties.of())
    );
    public static final BlockEntityType<BlockEntityB> BE_OUTER = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            new ResourceLocation("mod_b", "b_outer"),
            FabricBlockEntityTypeBuilder.create(BlockEntityB::new, BLOCK_OUTER).build(new ResourceLocation("mod_b", "b_outer"))
    );

    public static final int DATA_VERSION = 6;

    @Override
    public void onInitialize() {

    }
}
