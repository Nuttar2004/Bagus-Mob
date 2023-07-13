package baguchan.bagusmob.client.model;// Made with Blockbench 4.6.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import bagu_chan.bagus_lib.client.layer.IArmor;
import baguchan.bagusmob.client.aniamtion.NinjarAnimations;
import baguchan.bagusmob.entity.Ninjar;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;

public class NinjarModel<T extends Ninjar> extends HierarchicalModel<T> implements ArmedModel, HeadedModel, IArmor {
	private final ModelPart root;

	private final ModelPart roots;
	private final ModelPart head;
	private final ModelPart waist;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public NinjarModel(ModelPart root) {
		this.root = root;
		this.roots = root.getChild("roots");
		this.head = this.roots.getChild("head");
		this.waist = this.roots.getChild("waist");
		this.right_arm = this.roots.getChild("RightArm");
		this.left_arm = this.roots.getChild("LeftArm");
		this.right_leg = this.roots.getChild("RightLeg");
		this.left_leg = this.roots.getChild("LeftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition roots = partdefinition.addOrReplaceChild("roots", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition waist = roots.addOrReplaceChild("waist", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition Body = waist.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, -24.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 38).addBox(-4.0F, -24.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition head = roots.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition LeftLeg = roots.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

		PartDefinition RightLeg = roots.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -12.0F, 0.0F));

		PartDefinition RightArm = roots.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

		PartDefinition LeftArm = roots.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, -22.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);

		this.animate(entity.idleAnimationState, NinjarAnimations.IDLE, ageInTicks);
		this.animateWalk(NinjarAnimations.DASH, limbSwing, limbSwingAmount * (entity.getRunningScale()), 1.0F, 5.0F);
		this.animateWalk(NinjarAnimations.WALK, limbSwing, limbSwingAmount * (1.0F - entity.getRunningScale()), 3.0F, 4.0F);
		this.animate(entity.slashRightAnimationState, NinjarAnimations.SLASH_RIGHT, ageInTicks);
		this.animate(entity.slashLeftAnimationState, NinjarAnimations.SLASH_LEFT, ageInTicks);
		this.animate(entity.disappearAnimationState, NinjarAnimations.DESPAWN, ageInTicks);
		this.animate(entity.appearAnimationState, NinjarAnimations.SPAWN, ageInTicks);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	private ModelPart getArm(HumanoidArm p_102923_) {
		return p_102923_ == HumanoidArm.LEFT ? this.left_arm : this.right_arm;
	}

	public void translateToHand(HumanoidArm p_102925_, PoseStack p_102926_) {
		this.roots.translateAndRotate(p_102926_);
		this.getArm(p_102925_).translateAndRotate(p_102926_);
	}

	@Override
	public void translateToHead(ModelPart modelPart, PoseStack poseStack) {
		this.roots.translateAndRotate(poseStack);
		modelPart.translateAndRotate(poseStack);
	}

	@Override
	public void translateToChest(ModelPart modelPart, PoseStack poseStack) {
		this.roots.translateAndRotate(poseStack);
		modelPart.translateAndRotate(poseStack);
	}

	@Override
	public void translateToLeg(ModelPart modelPart, PoseStack poseStack) {
		this.roots.translateAndRotate(poseStack);
		modelPart.translateAndRotate(poseStack);
	}

	@Override
	public void translateToChestPat(ModelPart modelPart, PoseStack poseStack) {
		this.roots.translateAndRotate(poseStack);
		modelPart.translateAndRotate(poseStack);
	}

	@Override
	public Iterable<ModelPart> rightHandArmors() {
		return ImmutableList.of(this.right_arm);
	}

	@Override
	public Iterable<ModelPart> leftHandArmors() {
		return ImmutableList.of(this.left_arm);
	}

	@Override
	public Iterable<ModelPart> rightLegPartArmors() {
		return ImmutableList.of(this.right_leg);
	}

	@Override
	public Iterable<ModelPart> leftLegPartArmors() {
		return ImmutableList.of(this.left_leg);
	}

	@Override
	public Iterable<ModelPart> bodyPartArmors() {
		return ImmutableList.of(this.waist);
	}

	@Override
	public Iterable<ModelPart> headPartArmors() {
		return ImmutableList.of(this.head);
	}

	@Override
	public ModelPart getHead() {
		return this.head;
	}
}