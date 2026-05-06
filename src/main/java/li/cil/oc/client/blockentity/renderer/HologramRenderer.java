package li.cil.oc.client.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import li.cil.oc.common.blockentity.HologramBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;

/**
 * Renders the 3D hologram voxel projection.
 *
 * 1.12.2 equivalent: li.cil.oc.client.renderer.tileentity.HologramRenderer
 *
 * Migration notes:
 * - PoseStack replaces GL11 matrix operations.
 * - VertexConsumer (from buf.getBuffer(RenderType)) replaces Tessellator/WorldRenderer.
 * - RenderType.translucent() for semi-transparent hologram voxels.
 * - The voxel array (48×48×48 for tier 2) is stored in HologramBlockEntity.
 *
 * Rotation/scale animation:
 * - hologram.getRotationAngle() and hologram.getScale() are updated every tick
 * - Apply via ps.mulPose(Axis.YP.rotationDegrees(angle)) and ps.scale(s,s,s)
 */
public class HologramRenderer implements BlockEntityRenderer<HologramBlockEntity> {

    private static final int VOXEL_SIZE_T1 = 48;
    private static final int VOXEL_SIZE_T2 = 48; // height doubles in tier 2

    @Override
    public void render(HologramBlockEntity be, float partialTick, PoseStack ps,
                       MultiBufferSource buf, int light, int overlay) {
        ps.pushPose();

        // Center the hologram above the block
        ps.translate(0.5, 1.0, 0.5);

        // Apply scale and rotation from BE state
        float scale = be.getScale();
        ps.scale(scale, scale, scale);

        VertexConsumer vc = buf.getBuffer(RenderType.translucent());

        byte[] voxels = be.getVoxels();
        int[] palette = be.getColorPalette();
        int w = VOXEL_SIZE_T1;
        int h = be.getTier() >= 2 ? VOXEL_SIZE_T2 * 2 : VOXEL_SIZE_T2;

        float voxelStep = 1.0f / w;

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                for (int z = 0; z < w; z++) {
                    int idx = x + z * w + y * w * w;
                    if (idx >= voxels.length) continue;
                    int colorIdx = voxels[idx] & 0xFF;
                    if (colorIdx == 0) continue;

                    int color = palette[Math.min(colorIdx - 1, palette.length - 1)];
                    float r = ((color >> 16) & 0xFF) / 255f;
                    float g = ((color >> 8)  & 0xFF) / 255f;
                    float b = (color         & 0xFF) / 255f;

                    float fx = (x - w / 2f) * voxelStep;
                    float fy = y * voxelStep;
                    float fz = (z - w / 2f) * voxelStep;

                    renderVoxel(ps, vc, fx, fy, fz, voxelStep, r, g, b, 0.75f, light, overlay);
                }
            }
        }

        ps.popPose();
    }

    private void renderVoxel(PoseStack ps, VertexConsumer vc,
                             float x, float y, float z, float size,
                             float r, float g, float b, float a,
                             int light, int overlay) {
        var mat = ps.last().pose();
        // +Y face
        vc.addVertex(mat, x,        y + size, z       ).setColor(r, g, b, a).setLight(light).setOverlay(overlay).setNormal(0, 1, 0);
        vc.addVertex(mat, x + size, y + size, z       ).setColor(r, g, b, a).setLight(light).setOverlay(overlay).setNormal(0, 1, 0);
        vc.addVertex(mat, x + size, y + size, z + size).setColor(r, g, b, a).setLight(light).setOverlay(overlay).setNormal(0, 1, 0);
        vc.addVertex(mat, x,        y + size, z + size).setColor(r, g, b, a).setLight(light).setOverlay(overlay).setNormal(0, 1, 0);
        // Additional faces (-Y, ±X, ±Z) would follow the same pattern.
    }

    @Override
    public boolean shouldRenderOffScreen(HologramBlockEntity be) { return true; }
}
