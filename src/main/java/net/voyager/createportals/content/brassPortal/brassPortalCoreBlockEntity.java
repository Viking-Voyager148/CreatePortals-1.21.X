package net.voyager.createportals.content.brassPortal;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class brassPortalCoreBlockEntity extends KineticBlockEntity implements IHaveGoggleInformation {

    private static final int MAX_LINKS = 9;
    private static final int MAX_FLUID = 8000;

    SmartFluidTankBehaviour tank;
    private final List<String> portalLinks = new ArrayList<>(Collections.nCopies(MAX_LINKS, ""));
    private int selectedIndex = 4;
    private String portalName = "";
    private boolean isNetworkLocked = false;
    private UUID networkID = null;

    public brassPortalCoreBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    protected void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        tank = SmartFluidTankBehaviour.single(this, MAX_FLUID);
            tank.allowInsertion((Direction direction, FluidStack fluidStack) -> {
            if (direction == null) return false;

            Direction blockFacing = getBlockState().getValue(HorizontalDirectionalBlock.FACING);
            boolean correctSide = direction == blockFacing.getOpposite();
            boolean isEnderFluid = fluidStack.getFluid().isSame(ModFluids.ENDER_FLUID.get());

            return correctSide && isEnderFluid;
        });
        tank.allowExtraction((Direction direction, FluidStack fluidStack) -> {
            if (direction == null) return false;

            Direction blockFacing = getBlockState().getValue(HorizontalDirectionalBlock.FACING);
            return direction == blockFacing.getOpposite();
        });
        behaviours.add(tank);
    }
}
