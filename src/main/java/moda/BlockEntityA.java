package moda;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityA extends BlockEntity {
    public CompoundTag item;

    public BlockEntityA(BlockPos blockPos, BlockState blockState) {
        super(ModA.BE_OUTER, blockPos, blockState);
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        ListTag items = new ListTag();
        if (item != null) {
            items.add(this.item);
        } else {
            var itemTag = new CompoundTag();
            ItemStack.EMPTY.save(provider, itemTag);
            items.add(itemTag);
        }
        tag.put("inventory", items);

        super.saveAdditional(tag, provider);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        ListTag items = tag.getList("inventory", Tag.TAG_COMPOUND);
        this.item = (CompoundTag) items.get(0);

        super.loadAdditional(tag, provider);
    }
}
