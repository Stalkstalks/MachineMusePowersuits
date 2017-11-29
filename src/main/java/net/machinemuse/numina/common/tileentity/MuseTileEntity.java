package net.machinemuse.numina.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Author: MachineMuse (Claire Semple)
 * Created: 2:46 AM, 11/13/13
 *
 * Ported to Java lehjr on 10/10/16.
 */
public abstract class MuseTileEntity extends TileEntity {
    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.loadNBTData(pkt.getNbtCompound());
        this.world.markBlockRangeForRenderUpdate(this.getPos(), this.getPos());
//        IBlockState state = getWorld().getBlockState(getPos());
//        getWorld().notifyBlockUpdate(getPos(), state, state, 3);
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound dataTag = new NBTTagCompound();
        dataTag = this.writeNBTData(dataTag);
        return new SPacketUpdateTileEntity(this.pos, 1, dataTag);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound tags = super.getUpdateTag();
        return this.writeNBTData(tags);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.loadNBTData(nbt);
    }

    public abstract void loadNBTData(NBTTagCompound nbt);


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt = this.writeNBTData(nbt);
        return nbt;
    }

    public abstract NBTTagCompound writeNBTData(NBTTagCompound nbt);

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }




    /* ==============================================================================
    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
        IBlockState state = getWorld().getBlockState(getPos());
        getWorld().notifyBlockUpdate(getPos(), state, state, 3);
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), 1, getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    public Integer getInteger(NBTTagCompound nbt, String name) {
        if (nbt.hasKey(name))
            return nbt.getInteger(name);
        else
            return null;    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }
    }

    public Double getDouble(NBTTagCompound nbt, String name) {
        if (nbt.hasKey(name))
            return nbt.getDouble(name);
        else
            return null;
    }

    public Boolean getBoolean(NBTTagCompound nbt, String name) {
        if (nbt.hasKey(name))
            return nbt.getBoolean(name);
        else
            return null;
    }

    public ItemStack getItemStack(NBTTagCompound nbt, String name) {
        if (nbt.hasKey(name))
            return new ItemStack(nbt.getCompoundTag(name));
        else
            return null;
    }

    public void writeItemStack(NBTTagCompound nbt, String name, ItemStack stack) {
        NBTTagCompound itemnbt = new NBTTagCompound();
        stack.writeToNBT(itemnbt);
        nbt.setTag(name, itemnbt);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }
    */
}