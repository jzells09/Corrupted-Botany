package com.jzells.corruptedbotany.entities.client.model;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class Tier3ZombieSpiderModel<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart tier_3_zombie_spider_hybrid;

	public Tier3ZombieSpiderModel(ModelPart root) {
		this.tier_3_zombie_spider_hybrid = root.getChild("tier_3_zombie_spider_hybrid");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition tier_3_zombie_spider_hybrid = partdefinition.addOrReplaceChild("tier_3_zombie_spider_hybrid", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Zombie = tier_3_zombie_spider_hybrid.addOrReplaceChild("Zombie", CubeListBuilder.create().texOffs(0, 53).addBox(-4.0F, -29.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(16, 69).addBox(-4.0F, -21.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 69).mirror().addBox(4.0F, -21.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(40, 69).addBox(-8.0F, -21.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Spider = tier_3_zombie_spider_hybrid.addOrReplaceChild("Spider", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legs1 = Spider.addOrReplaceChild("legs1", CubeListBuilder.create().texOffs(32, 0).addBox(3.0F, -7.0F, 9.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(3.0F, -7.0F, 6.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(3.0F, -7.0F, 3.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(3.0F, -7.0F, 0.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg2 = Spider.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(32, 0).addBox(-19.0F, -7.0F, 9.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-19.0F, -7.0F, 6.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-19.0F, -7.0F, 3.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-19.0F, -7.0F, 0.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = Spider.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 36).addBox(-3.0F, -9.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.0F, -10.0F, 3.0F, 10.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		tier_3_zombie_spider_hybrid.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return tier_3_zombie_spider_hybrid;
	}


}