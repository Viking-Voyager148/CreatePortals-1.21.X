package net.voyager.createportals.content.brassPortal;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.voyager.createportals.AllBlockEntityTypes;


public class BrassPortalCoreBlock extends HorizontalKineticBlock implements IWrenchable, IBE<BrassPortalCoreBlockEntity> {
    public BrassPortalCoreBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(HORIZONTAL_FACING)
                .getClockWise()
                .getAxis();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction preferredHorizontalFacing = getPreferredHorizontalFacing(context);
        return this.defaultBlockState()
                .setValue(HORIZONTAL_FACING,
                        preferredHorizontalFacing != null ? preferredHorizontalFacing.getCounterClockWise()
                                : context.getHorizontalDirection()
                                .getOpposite());
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return state.getValue(HORIZONTAL_FACING)
                .getClockWise() == face;
    }

    public boolean hasPipeTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return state.getValue(HORIZONTAL_FACING)
                .getCounterClockWise() == face;
    }

    @Override
    public Direction getPreferredHorizontalFacing(BlockPlaceContext context) {
        Direction fromParent = super.getPreferredHorizontalFacing(context);
        if (fromParent != null)
            return fromParent;

        Direction prefferedSide = null;
        for (Direction facing : Iterate.horizontalDirections) {
            BlockPos pos = context.getClickedPos()
                    .relative(facing);
            BlockState blockState = context.getLevel()
                    .getBlockState(pos);
            if (FluidPipeBlock.canConnectTo(context.getLevel(), pos, blockState, facing))
                if (prefferedSide != null && prefferedSide.getAxis() != facing.getAxis()) {
                    prefferedSide = null;
                    break;
                } else
                    prefferedSide = facing;
        }
        return prefferedSide == null ? null : prefferedSide.getOpposite();
    }

    @Override
    public Class<BrassPortalCoreBlockEntity> getBlockEntityClass() {
        return BrassPortalCoreBlockEntity.class;
    }
    @Override
    public BlockEntityType<BrassPortalCoreBlockEntity> getBlockEntityType() {
        return AllBlockEntityTypes.BRASS_PORTAL_CORE_BE.get();
    }
}
