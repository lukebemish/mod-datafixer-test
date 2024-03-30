package moda;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.util.datafix.schemas.NamespacedSchema;

import java.util.Map;
import java.util.function.Supplier;

public class AV1 extends NamespacedSchema {
    public AV1(int i, Schema schema) {
        super(i, schema);
    }

    @Override
    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema schema) {
        var map = super.registerBlockEntities(schema);
        schema.register(map, "mod_a:a_outer_old", () ->
                DSL.optionalFields("inventory", DSL.list(References.ITEM_STACK.in(schema)))
        );
        return map;
    }
}
