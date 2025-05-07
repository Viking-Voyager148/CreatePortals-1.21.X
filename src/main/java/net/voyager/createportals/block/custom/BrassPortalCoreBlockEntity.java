package net.voyager.createportals.block.custom;

import com.mojang.serialization.MapCodec;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;


public class BrassPortalCoreBlockEntity extends KineticBlockEntity {

    private float dialAngle = 0;
    private int selectionIndex = 0;  // Current selected destination index
    private int lastValidSelectionIndex = 0;  // Last valid destination index

    public BrassPortalCoreBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tick() {
        super.tick();

        if (level.isClientSide)
            return;

        float rpm = this.getSpeed();
        dialAngle += rpm * 0.3f; // Update based on RPM

        // Wrap around dialAngle to stay between 0 and 360 degrees
        if (dialAngle < 0) dialAngle += 360;
        if (dialAngle >= 360) dialAngle -= 360;

        // Determine the nearest multiple of 40 degrees for destination selection
        int newSelection = (int) (dialAngle / 40);

        // Ensure the selection wraps around from 0 to 8 (mod 9)
        newSelection = newSelection % 9;

        // If the new selection is the same as the previous, reset angle to previous increment
        if (newSelection == selectionIndex) {
            dialAngle = lastValidSelectionIndex * 40; // Reset to the last valid increment
        } else {
            // Update the selection if the angle has passed a valid destination
            selectionIndex = newSelection;
            lastValidSelectionIndex = selectionIndex; // Update the last valid increment
            // setSelectionIndex(selectionIndex); // Update or trigger any other actions for destination change
        }
    }

    public float getDialAngle() {
        return dialAngle;
    }
}
