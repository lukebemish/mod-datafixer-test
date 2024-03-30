package modb;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.util.datafix.schemas.NamespacedSchema;

import java.util.Map;
import java.util.function.Supplier;

public class BV6 extends NamespacedSchema {
    public BV6(int i, Schema schema) {
        super(i, schema);
    }

    @Override
    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema schema) {
        var map = super.registerBlockEntities(schema);
        map.remove("mod_b:b_outer_old");
        schema.register(map, "mod_b:b_outer", () ->
                DSL.optionalFields("inventory", DSL.list(References.ITEM_STACK.in(schema)))
        );
        return map;
    }
}
