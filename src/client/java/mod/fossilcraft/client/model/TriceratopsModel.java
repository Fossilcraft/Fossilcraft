package mod.fossilcraft.client.model;

import com.google.common.collect.ImmutableList;
import mod.fossilcraft.entity.TriceratopsEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class TriceratopsModel extends EntityModel<TriceratopsEntity> {

    private final ModelPart upperBody;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart crest;
    private final ModelPart beak;
    private final ModelPart beakHorn;
    private final ModelPart leftHorn;
    private final ModelPart rightHorn;

    private final ModelPart lowerBody;
    private final ModelPart tailBase;
    private final ModelPart tailMid;
    private final ModelPart tailTip;

    private final ModelPart leftBackUpperLeg;
    private final ModelPart rightBackUpperLeg;
    private final ModelPart leftBackLowerLeg;
    private final ModelPart rightBackLowerLeg;

    private final ModelPart leftFrontUpperLeg;
    private final ModelPart rightFrontUpperLeg;
    private final ModelPart leftFrontLowerLeg;
    private final ModelPart rightFrontLowerLeg;


    private final ImmutableList<ModelPart> parts;


    public TriceratopsModel(ModelPart root) {

        this.upperBody = root.getChild("upper_body");
        this.neck = this.upperBody.getChild("neck");
        this.head = this.neck.getChild("head");
        this.crest = this.head.getChild("crest");
        this.beak = this.head.getChild("beak");
        this.beakHorn = this.beak.getChild("beak_horn");

        this.leftHorn = this.head.getChild("left_horn");
        this.rightHorn = this.head.getChild("right_horn");

        this.lowerBody = root.getChild("lower_body");
        this.tailBase = this.lowerBody.getChild("tail_base");
        this.tailMid = this.tailBase.getChild("tail_mid");
        this.tailTip = this.tailMid.getChild("tail_tip");

        this.leftBackUpperLeg = this.lowerBody.getChild("left_back_upper_leg");
        this.rightBackUpperLeg = this.lowerBody.getChild("right_back_upper_leg");
        this.leftBackLowerLeg = this.leftBackUpperLeg.getChild("left_back_lower_leg");
        this.rightBackLowerLeg = this.rightBackUpperLeg.getChild("right_back_lower_leg");

        this.leftFrontUpperLeg = this.upperBody.getChild("left_front_upper_leg");
        this.rightFrontUpperLeg = this.upperBody.getChild("right_front_upper_leg");
        this.leftFrontLowerLeg = this.leftFrontUpperLeg.getChild("left_front_lower_leg");
        this.rightFrontLowerLeg = this.rightFrontUpperLeg.getChild("right_front_lower_leg");

        this.parts = ImmutableList.of(this.upperBody, this.lowerBody);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        ModelPartData upperBody = root.addChild("upper_body",
                ModelPartBuilder.create()
                        .uv(77, 0)
                        .cuboid(-3.5f, -1.5f, -5f, 7, 6, 6)
                        .mirrored(),
                ModelTransform
                        .of(0f, 0f, 0f, 0f, 0f, 0f)
        );

        ModelPartData neck = upperBody.addChild("neck",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-1.5F, -4F, -3F, 3, 4, 3)
                        .mirrored(),
                ModelTransform.of(0F, 3.5F, -5F, 0F, 0F, 0F)
        );

        ModelPartData head = neck.addChild("head",
                ModelPartBuilder.create()
                        .uv(0, 34)
                        .cuboid(-2F, -2F, -3.5F, 4, 4, 4),
                ModelTransform.of(0F, -3.5F, -2F, 0F, 0F, 0F)
        );

        ModelPartData crest = head.addChild("crest",
                ModelPartBuilder.create()
                        .uv(20, 0)
                        .cuboid(-4F, -7F, 0F, 8, 7, 1)
                        .uv(20, 8)
                        .cuboid(-5F, -7.6F, -0.1F, 10, 8, 1)
                        .mirrored(),
                ModelTransform.of(0F, 0F, -1F, 0F, 0F, 0F)
        );

        ModelPartData beak = head.addChild("beak",
                ModelPartBuilder.create()
                        .uv(0, 43)
                        .cuboid(-1F, 0F, -2.5F, 2, 3, 4)
                        .mirrored(),
                ModelTransform.of(0F, -1F, -3.5F, 0F, 0F, 0F)
        );

        ModelPartData beakHorn = beak.addChild("beak_horn",
                ModelPartBuilder.create()
                        .uv(24, 37)
                        .cuboid(-0.5F, -0.5F, -2F, 1, 1, 2)
                        .mirrored(),
                ModelTransform.of(0F, 1F, -1F, 0F, 0F, 0F)
        );

        ModelPartData leftHorn = head.addChild("left_horn",
                ModelPartBuilder.create()
                        .uv(32, 35)
                        .cuboid(0F, 0F, -3.5F, 1, 1, 4)
                        .uv(33, 27)
                        .cuboid(0F, 0F, -7.5F, 1, 1, 4),
                ModelTransform.of(0.9F, -2F, -0.5F, 0F, 0F, 0F)
        );

        ModelPartData rightHorn = head.addChild("right_horn",
                ModelPartBuilder.create()
                        .uv(32, 35)
                        .cuboid(-1F, 0F, -3.5F, 1, 1, 4)
                        .uv(33, 27)
                        .cuboid(-1F, 0F, -7.5F, 1, 1, 4),
                ModelTransform.of(-0.9F, -2F, -0.5F, 0F, 0F, 0F)
        );

        ModelPartData lowerBody = root.addChild("lower_body",
                ModelPartBuilder.create()
                        .uv(104, 1)
                        .cuboid(-3F, -1F, 0F, 6, 6, 6)
                        .mirrored(),
                ModelTransform
                        .of(0f, -0.1f, -0.5f, 0f, 0f, 0f)
        );

        ModelPartData tailBase = lowerBody.addChild("tail_base",
                ModelPartBuilder.create()
                        .uv(48, 10)
                        .cuboid(-2.5f, -1.2f, 0f, 5, 4, 3)
                        .mirrored(),
                ModelTransform.of(0f, 0.5f, 5.5f, 0f, 0f, 0f)
        );

        ModelPartData tailMid = tailBase.addChild("tail_mid",
                ModelPartBuilder.create()
                        .uv(68, 17)
                        .cuboid(-1.5f, -1f, -0.5f, 3, 3, 3)
                        .mirrored(),
                ModelTransform.of(0f, 0.5f, 3f, 0f, 0f, 0f)
        );

        ModelPartData tailTip = tailMid.addChild("tail_tip",
                ModelPartBuilder.create()
                        .uv(54, 21)
                        .cuboid(-1F, -1F, 0F, 2, 2, 3)
                        .mirrored(),
                ModelTransform.of(0F, 0.5F, 2.5F, 0F, 0F, 0F)
        );

        ModelPartData leftBackUpperLeg = lowerBody.addChild("left_back_upper_leg",
                ModelPartBuilder.create()
                        .uv(12, 24)
                        .cuboid(0F, 0F, -1.5F, 2, 4, 3)
                        .mirrored(),
                ModelTransform.of(3F, 1.5F, 3F, 0F, 0F, 0F)
        );

        ModelPartData rightBackUpperLeg = lowerBody.addChild("right_back_upper_leg",
                ModelPartBuilder.create()
                        .uv(12, 24)
                        .cuboid(-2F, 0F, -1.5F, 2, 4, 3)
                        .mirrored(),
                ModelTransform.of(-3F, 1.5F, 3F, 0F, 0F, 0F)
        );

        ModelPartData leftBackLowerLeg = leftBackUpperLeg.addChild("left_back_lower_leg",
                ModelPartBuilder.create()
                        .uv(32, 19)
                        .cuboid(-0.5F, 0F, -4F, 1, 2, 4)
                        .mirrored(),
                ModelTransform.of(1F, 2F, 0.0F, 0F, 0F, 0F)
        );

        ModelPartData rightBackLowerLeg = rightBackUpperLeg.addChild("right_back_lower_leg",
                ModelPartBuilder.create()
                        .uv(32, 19)
                        .cuboid(-0.5F, 0F, -4F, 1, 2, 4)
                        .mirrored(),
                ModelTransform.of(-1F, 2F, 0.0F, 0F, 0F, 0F)
        );

        ModelPartData leftFrontUpperLeg = upperBody.addChild("left_front_upper_leg",
                ModelPartBuilder.create()
                        .uv(0, 16)
                        .cuboid(0F, 0F, -1F, 2, 3, 2)
                        .mirrored(),
                ModelTransform.of(3F, 3F, -3.5F, 0F, 0F, 0F)
        );

        ModelPartData rightFrontUpperLeg = upperBody.addChild("right_front_upper_leg",
                ModelPartBuilder.create()
                        .uv(0, 16)
                        .cuboid(-2F, 0F, -1F, 2, 3, 2)
                        .mirrored(),
                ModelTransform.of(-3F, 3F, -3.5F, 0F, 0F, 0F)
        );

        ModelPartData leftFrontLowerLeg = leftFrontUpperLeg.addChild("left_front_lower_leg",
                ModelPartBuilder.create()
                        .uv(16, 19)
                        .cuboid(-0.5F, -1F, -3F, 1, 2, 3)
                        .mirrored(),
                ModelTransform.of(1F, 2F, 0.5F, 0F, 0F, 0F)
        );

        ModelPartData rightFrontLowerLeg = rightFrontUpperLeg.addChild("right_front_lower_leg",
                ModelPartBuilder.create()
                        .uv(16, 19)
                        .cuboid(-0.5F, -1F, -3F, 1, 2, 3)
                        .mirrored(),
                ModelTransform.of(-1F, 2F, 0.5F, 0F, 0F, 0F)
        );


        return TexturedModelData.of(modelData, 128, 64);
    }

    @Override
    public void setAngles(TriceratopsEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = (float) Math.toRadians(19);
        this.beak.pitch = (float) Math.toRadians(14);
        this.beakHorn.pitch = (float) Math.toRadians(-80);
        this.crest.pitch = (float) Math.toRadians(-30);
        this.leftHorn.pitch = (float) Math.toRadians(-46);
        this.rightHorn.pitch = (float) Math.toRadians(-46);

        this.upperBody.pitch = (float) Math.toRadians(9);
        this.neck.pitch = (float) Math.toRadians(-25);
        this.tailBase.pitch = (float) Math.toRadians(-24);
        this.tailMid.pitch = (float) Math.toRadians(2);
        this.tailTip.pitch = (float) Math.toRadians(10);

        this.leftFrontLowerLeg.pitch = (float) Math.toRadians(90);
        this.rightFrontLowerLeg.pitch = (float) Math.toRadians(90);
        this.leftBackLowerLeg.pitch = (float) Math.toRadians(90);
        this.rightBackLowerLeg.pitch = (float) Math.toRadians(90);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch += headPitch * 0.017453292F;

        this.leftFrontUpperLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.rightFrontUpperLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
        this.leftBackUpperLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
        this.rightBackUpperLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        this.parts.forEach((modelRenderer) -> {
            modelRenderer.render(matrices, vertices, light, overlay);
        });
    }

}
