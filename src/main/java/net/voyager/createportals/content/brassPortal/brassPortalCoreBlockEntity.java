package net.voyager.createportals.content.brassPortal;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.voyager.createportals.utils.PortalEntry;
import net.voyager.createportals.utils.PortalRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class brassPortalCoreBlockEntity extends KineticBlockEntity implements IHaveGoggleInformation {

    private static final int MAX_LINKS = 9;
    private static final int MAX_FLUID = 8000;

    SmartFluidTankBehaviour tank;
    // private int selectedIndex = 4;

    // private UUID networkID = null;

    public brassPortalCoreBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        List<PortalEntry> portalLinks;
        portalLinks = new ArrayList<>(Collections.nCopies(MAX_LINKS, ""));
    }

    public void registerPortal(String portalName, UUID ownerUUID) {
        if (this.level == null) return;
        ResourceKey<Level> dim = this.level.dimension();

        PortalEntry entry = new PortalEntry(portalName, this.worldPosition, dim, ownerUUID);

        PortalRegistry.getInstance().registerPortal(entry);

        setChanged();
    }
    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
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
            boolean correctSide = direction == blockFacing.getOpposite();
            boolean isEnderFluid = fluidStack.getFluid().isSame(ModFluids.ENDER_FLUID.get());

            return correctSide && isEnderFluid;
        });
        behaviours.add(tank);
    }
}
