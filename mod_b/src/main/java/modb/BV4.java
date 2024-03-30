package modb;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.util.datafix.schemas.NamespacedSchema;

import java.util.Map;
import java.util.function.Supplier;

public class BV4 extends NamespacedSchema {
    public BV4(int i, Schema schema) {
        super(i, schema);
    }

    @Override
    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema schema) {
        var map = super.registerBlockEntities(schema);
        schema.register(map, "mod_b:b_outer_old", () ->
                DSL.optionalFields("inventory", DSL.list(References.ITEM_STACK.in(schema)))
        );
        return map;
    }
}
