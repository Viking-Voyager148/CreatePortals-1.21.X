package net.voyager.createportals.content.brassPortal;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.voyager.createportals.AllBlockEntityTypes;

import java.util.List;


public class BrassPortalCoreBlockEntity extends KineticBlockEntity implements IHaveGoggleInformation {

    private static final int MAX_LINKS = 9;
    private static final int MAX_FLUID = 8000;

	BrassPortalCoreBlockTank = internalTank;
	protected LazyOptional<IFluidHandler> fluidCapability;

    public BrassPortalCoreBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
		fluidCapability = LazyOptional.of(() -> internalTank)
    }
    
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
		if (isFluidHandlerCap(cap) 
			&& (side == null || BrassPortalCoreBlock.hasPipeTowards(side)))
			return this.fluidCapability
				.cast();
		return super.getCapability(cap, side);
	}
    
    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        return containedFluidTooltip(tooltip, isPlayerSneaking, tank.getCapability());
    }
}
