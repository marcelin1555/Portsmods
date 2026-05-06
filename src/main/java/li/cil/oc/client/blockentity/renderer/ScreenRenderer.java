package li.cil.oc.client.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import li.cil.oc.common.blockentity.ScreenBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.core.Direction;

/**
 * Renders the OC screen text buffer in-world.
 *
 * 1.12.2 equivalent: li.cil.oc.client.renderer.tileentity.ScreenRenderer
 * which extended TileEntitySpecialRenderer<Screen>.
 *
 * 1.21.1 changes:
 * - Implements BlockEntityRenderer<T> (not TileEntitySpecialRenderer<T>)
 * - Registered via EntityRenderersEvent.RegisterRenderers (not ClientRegistry)
 * - PoseStack replaces the raw GL11 matrix stack; use ps.mulPose() not GL11.glRotate()
 * - Rendering uses VertexConsumer from MultiBufferSource, not Tessellator.getInstance()
 * - RenderType system replaces glEnable/glDisable calls
 *
 * Render method signature (unchanged since 1.16):
 *   render(T be, float partialTick, PoseStack ps, MultiBufferSource buf, int light, int overlay)
 */
public class ScreenRenderer implements BlockEntityRenderer<ScreenBlockEntity> {

    @Override
    public void render(ScreenBlockEntity be, float partialTick, PoseStack ps,
                       MultiBufferSource buf, int light, int overlay) {
        if (!be.hasPower()) return;

        // In the full implementation:
        // 1. Push matrix, translate to block face center
        // 2. Apply block facing rotation (Direction stored in BlockState)
        // 3. Render character grid using a custom RenderType (translucent or solid)
        //    via buf.getBuffer(OCRenderTypes.SCREEN_TEXT)
        // 4. For each cell: render background quad, then foreground character quad
        //    using the GPU's color palette
        // 5. Pop matrix
        //
        // The text buffer data lives in the ScreenBlockEntity and is updated via
        // TextBufferSet/Copy/Fill packets from the server.
        ps.pushPose();
        // TODO: full text-grid rendering
        ps.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(ScreenBlockEntity be) {
        // Screens can be large (multi-block), so always render even if the
        // origin block is off-screen. Equivalent to old isGlobalRenderer().
        return true;
    }
}
