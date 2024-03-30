package moda.gametest;

import moda.BlockEntityA;
import modb.BlockEntityB;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ModAGameTest implements FabricGameTest {
    @SuppressWarnings("DataFlowIssue")
    @GameTest(template = "mod_a_gametest:a_in_b")
    public void aInB(GameTestHelper helper) {
        BlockEntity be = helper.getBlockEntity(new BlockPos(0, 1, 0));
        helper.assertTrue(be != null, "Block entity not present");
        helper.assertTrue(be instanceof BlockEntityB, "Block entity is not B");
        BlockEntityB beB = (BlockEntityB) be;
        helper.assertValueEqual(beB.item.getString("id"), "mod_a:a_inner", "Item ID");
        helper.succeed();
    }

    @SuppressWarnings("DataFlowIssue")
    @GameTest(template = "mod_a_gametest:b_in_a")
    public void bInA(GameTestHelper helper) {
        BlockEntity be = helper.getBlockEntity(new BlockPos(0, 1, 0));
        helper.assertTrue(be != null, "Block entity not present");
        helper.assertTrue(be instanceof BlockEntityA, "Block entity is not A");
        BlockEntityA beA = (BlockEntityA) be;
        helper.assertValueEqual(beA.item.getString("id"), "mod_b:b_inner", "Item ID");
        helper.succeed();
    }

    @SuppressWarnings("DataFlowIssue")
    @GameTest(template = "mod_a_gametest:a_in_a")
    public void aInA(GameTestHelper helper) {
        BlockEntity be = helper.getBlockEntity(new BlockPos(0, 1, 0));
        helper.assertTrue(be != null, "Block entity not present");
        helper.assertTrue(be instanceof BlockEntityA, "Block entity is not A");
        BlockEntityA beA = (BlockEntityA) be;
        helper.assertValueEqual(beA.item.getString("id"), "mod_a:a_inner", "Item ID");
        helper.succeed();
    }

    @SuppressWarnings("DataFlowIssue")
    @GameTest(template = "mod_a_gametest:b_in_b")
    public void bInB(GameTestHelper helper) {
        BlockEntity be = helper.getBlockEntity(new BlockPos(0, 1, 0));
        helper.assertTrue(be != null, "Block entity not present");
        helper.assertTrue(be instanceof BlockEntityB, "Block entity is not B");
        BlockEntityB beB = (BlockEntityB) be;
        helper.assertValueEqual(beB.item.getString("id"), "mod_b:b_inner", "Item ID");
        helper.succeed();
    }
}
