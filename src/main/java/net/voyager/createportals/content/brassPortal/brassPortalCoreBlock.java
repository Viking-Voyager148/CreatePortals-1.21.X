package net.voyager.createportals.content.brassPortal;

import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;


public class brassPortalCoreBlock extends HorizontalKineticBlock {
    public brassPortalCoreBlock(Properties properties) {
        super(properties);
    }

    @Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState()
            .setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
	}
    
    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return null;
    }

    public static boolean hasPipeTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return state.getValue(HorizontalDirectionalBlock.FACING).getOpposite() == face;
    }
}
