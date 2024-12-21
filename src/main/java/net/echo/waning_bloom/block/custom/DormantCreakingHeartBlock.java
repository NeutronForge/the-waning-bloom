package net.echo.waning_bloom.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.CreakingHeartBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionImpl;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class DormantCreakingHeartBlock extends Block {
    public static final MapCodec<DormantCreakingHeartBlock> CODEC = createCodec(DormantCreakingHeartBlock::new);
    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;
    public static final BooleanProperty ACTIVE = Properties.ACTIVE;


    @Override
    public MapCodec<DormantCreakingHeartBlock> getCodec() {
        return CODEC;
    }

    public DormantCreakingHeartBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y).with(ACTIVE, Boolean.valueOf(false)));
    }


    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {

            if ((Boolean)state.get(ACTIVE)) {
                if (random.nextInt(16) == 0 && isSurroundedByPaleOakLogs(world, pos)) {
                    world.playSound((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.BLOCK_CREAKING_HEART_IDLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
                }
            }

    }


    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state,
            WorldView world,
            ScheduledTickView tickView,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            Random random
    ) {
        BlockState blockState = super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        return enableIfValid(blockState, world, pos);
    }

    private static BlockState enableIfValid(BlockState state, WorldView world, BlockPos pos) {
        boolean bl = shouldBeEnabled(state, world, pos);
        boolean bl2 = !(Boolean)state.get(ACTIVE);
        return bl && bl2 ? state.with(ACTIVE, Boolean.valueOf(true)) : state;
    }

    public static boolean shouldBeEnabled(BlockState state, WorldView world, BlockPos pos) {
        Direction.Axis axis = state.get(AXIS);

        for (Direction direction : axis.getDirections()) {
            BlockState blockState = world.getBlockState(pos.offset(direction));
            if (!blockState.isIn(BlockTags.PALE_OAK_LOGS) || blockState.get(AXIS) != axis) {
                return false;
            }
        }

        return true;
    }

    private static boolean isSurroundedByPaleOakLogs(WorldAccess world, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            if (!blockState.isIn(BlockTags.PALE_OAK_LOGS)) {
                return false;
            }
        }

        return true;
    }


    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return enableIfValid(this.getDefaultState().with(AXIS, ctx.getSide().getAxis()), ctx.getWorld(), ctx.getBlockPos());
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return PillarBlock.changeRotation(state, rotation);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, ACTIVE);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
            super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        if (!(Boolean)state.get(ACTIVE)) {
            return 0;
        } else {
            return 15;
        }
    }
}
