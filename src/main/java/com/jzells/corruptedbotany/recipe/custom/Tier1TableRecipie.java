package com.jzells.corruptedbotany.recipe.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jzells.corruptedbotany.CorruptedBotany;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class Tier1TableRecipie implements Recipe<SimpleContainer> {

    private final NonNullList<Ingredient> ingredients;
    private final ItemStack output;
    private final ResourceLocation id;

    public Tier1TableRecipie(NonNullList<Ingredient> ingredients, ItemStack output, ResourceLocation id) {
        this.ingredients = ingredients;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        int total = 25;
        int correct = 1;
        if(level.isClientSide){
            return false;
        }
        for(int i = 0; i < 25; i++){
            if(ingredients.get(i).test(simpleContainer.getItem(i))){
                correct++;
            }

        }
        return total == correct;
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<Tier1TableRecipie>{
        public static final Type INSTANCE = new Type();
        public static final String ID = "tier_1_table";
    }

    public static class Serializer implements RecipeSerializer<Tier1TableRecipie>{
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation((CorruptedBotany.MODID), "tier_1_table");

        @Override
        public Tier1TableRecipie fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject,"result"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(jsonObject, "ingredients");

            NonNullList<Ingredient> inputs = NonNullList.withSize(25, Ingredient.EMPTY);

            for(int i = 0; i < 25; i++){
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new Tier1TableRecipie(inputs, output, resourceLocation);
        }

        @Override
        public @Nullable Tier1TableRecipie fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(friendlyByteBuf.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++){
                inputs.set(i, Ingredient.fromNetwork(friendlyByteBuf));



            }

            ItemStack output = friendlyByteBuf.readItem();
            return new Tier1TableRecipie(inputs, output, resourceLocation);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, Tier1TableRecipie tier1TableRecipie) {
            friendlyByteBuf.writeInt(tier1TableRecipie.ingredients.size());

            for (Ingredient ingredient : tier1TableRecipie.getIngredients()) {
                ingredient.toNetwork(friendlyByteBuf);
            }

            friendlyByteBuf.writeItemStack(tier1TableRecipie.getResultItem(null),false);
        }
    }
}
