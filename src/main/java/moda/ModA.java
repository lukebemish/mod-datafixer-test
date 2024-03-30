package moda;

import com.mojang.datafixers.schemas.Schema;
import modb.BV4;
import modb.BV6;
import modb.ModB;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datafixer.v1.FabricDataFixerBuilder;
import net.fabricmc.fabric.api.datafixer.v1.FabricDataFixes;
import net.fabricmc.fabric.api.datafixer.v1.SimpleFixes;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.util.datafix.fixes.AddNewChoices;
import net.minecraft.util.datafix.fixes.BlockEntityRenameFix;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.util.datafix.schemas.NamespacedSchema;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModA implements ModInitializer {
    public static final Item A_INNER = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("mod_a", "a_inner"), new Item(new Item.Properties()));
    public static final Block BLOCK_OUTER = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation("mod_a", "a_outer"),
            new BlockA(BlockBehaviour.Properties.of())
    );
    public static final BlockEntityType<BlockEntityA> BE_OUTER = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            new ResourceLocation("mod_a", "a_outer"),
            FabricBlockEntityTypeBuilder.create(BlockEntityA::new, BLOCK_OUTER).build(new ResourceLocation("mod_a", "a_outer"))
    );

    public static final int DATA_VERSION = 3;

    @Override
    public void onInitialize() {
        aFixers();
        bFixers();
    }

    private void aFixers() {
        var container = FabricLoader.getInstance().getModContainer("mod_a").orElseThrow();
        var builder = new FabricDataFixerBuilder(DATA_VERSION);
        builder.addSchema(0, FabricDataFixes.getBaseSchema());
        Schema schema1 = builder.addSchema(1, AV1::new);
        builder.addFixer(new AddNewChoices(schema1, "Add A BE", References.BLOCK_ENTITY));
        Schema schema2 = builder.addSchema(2, NamespacedSchema::new);
        SimpleFixes.addItemRenameFix(builder, "Rename A inner", new ResourceLocation("mod_a", "a_inner_old"), new ResourceLocation("mod_a", "a_inner"), schema2);
        Schema schema3 = builder.addSchema(3, AV3::new);
        builder.addFixer(BlockEntityRenameFix.create(schema3, "Rename A outer", DataFixers.createRenamer(new ResourceLocation("mod_a", "a_outer_old").toString(), new ResourceLocation("mod_a", "a_outer").toString())));
        SimpleFixes.addBlockRenameFix(builder, "Rename A outer", new ResourceLocation("mod_a", "a_outer_old"), new ResourceLocation("mod_a", "a_outer"), schema3);

        FabricDataFixes.buildAndRegisterFixer(container, builder);
    }

    private void bFixers() {
        var container = FabricLoader.getInstance().getModContainer("mod_b").orElseThrow();
        var builder = new FabricDataFixerBuilder(ModB.DATA_VERSION);
        builder.addSchema(0, FabricDataFixes.getBaseSchema());
        Schema schema1 = builder.addSchema(1, NamespacedSchema::new);
        Schema schema2 = builder.addSchema(2, NamespacedSchema::new);
        Schema schema3 = builder.addSchema(3, NamespacedSchema::new);
        Schema schema4 = builder.addSchema(4, BV4::new);
        builder.addFixer(new AddNewChoices(schema4, "Add B BE", References.BLOCK_ENTITY));
        Schema schema5 = builder.addSchema(5, NamespacedSchema::new);
        SimpleFixes.addItemRenameFix(builder, "Rename B inner", new ResourceLocation("mod_b", "b_inner_old"), new ResourceLocation("mod_b", "b_inner"), schema5);
        Schema schema6 = builder.addSchema(6, BV6::new);
        builder.addFixer(BlockEntityRenameFix.create(schema6, "Rename B outer", DataFixers.createRenamer(new ResourceLocation("mod_b", "b_outer_old").toString(), new ResourceLocation("mod_b", "b_outer").toString())));
        SimpleFixes.addBlockRenameFix(builder, "Rename B outer", new ResourceLocation("mod_b", "b_outer_old"), new ResourceLocation("mod_b", "b_outer"), schema6);

        FabricDataFixes.buildAndRegisterFixer(container, builder);
    }
}
