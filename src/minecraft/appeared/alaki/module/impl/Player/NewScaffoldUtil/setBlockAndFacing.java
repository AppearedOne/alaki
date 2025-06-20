package appeared.alaki.module.impl.Player.NewScaffoldUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;

public class setBlockAndFacing {
  Minecraft mc = Minecraft.getMinecraft();
  
  private BlockPos currentPos;
  
  private void setBlockAndFacing(BlockPos var1) {
    if (mc.theWorld.getBlockState(var1.add(0, -1, 0)).getBlock() != Blocks.air) {
      this.currentPos = var1.add(0, -1, 0);
      EnumFacing currentFacing = EnumFacing.UP;
    } else if (mc.theWorld.getBlockState(var1.add(-1, 0, 0)).getBlock() != Blocks.air) {
      this.currentPos = var1.add(-1, 0, 0);
      EnumFacing currentFacing = EnumFacing.EAST;
    } else if (mc.theWorld.getBlockState(var1.add(1, 0, 0)).getBlock() != Blocks.air) {
      this.currentPos = var1.add(1, 0, 0);
      EnumFacing currentFacing = EnumFacing.WEST;
    } else if (mc.theWorld.getBlockState(var1.add(0, 0, -1)).getBlock() != Blocks.air) {
      this.currentPos = var1.add(0, 0, -1);
      EnumFacing currentFacing = EnumFacing.SOUTH;
    } else if (mc.theWorld.getBlockState(var1.add(0, 0, 1)).getBlock() != Blocks.air) {
      this.currentPos = var1.add(0, 0, 1);
      EnumFacing currentFacing = EnumFacing.NORTH;
    } else {
      this.currentPos = null;
      EnumFacing currentFacing = null;
    } 
  }
  
  public static class BlockUtil {
    public static void placeHeldItemUnderPlayer() {
      BlockPos blockPos = new BlockPos(minecraft.thePlayer.posX, (minecraft.thePlayer.getEntityBoundingBox()).minY - 1.0D, 
    		  minecraft.thePlayer.posZ);
      Vec3d vec = (new Vec3d((Vec3i)blockPos)).addVector(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
      minecraft.playerController.onPlayerRightClick(minecraft.thePlayer, minecraft.theWorld, null, blockPos, EnumFacing.UP, 
          vec.scale(0.4D));
    }
    
    private static Minecraft minecraft = Minecraft.getMinecraft();
    
    public static float[] getDirectionToBlock(int var0, int var1, int var2, EnumFacing var3) {
      EntityEgg var4 = new EntityEgg((World)minecraft.theWorld);
      var4.posX = var0 + 0.5D;
      var4.posY = var1 + 0.5D;
      var4.posZ = var2 + 0.5D;
      var4.posX += var3.getDirectionVec().getX() * 0.25D;
      var4.posY += var3.getDirectionVec().getY() * 0.25D;
      var4.posZ += var3.getDirectionVec().getZ() * 0.25D;
      return getDirectionToEntity((Entity)var4);
    }
    
    private static float[] getDirectionToEntity(Entity var0) {
      return new float[] { getYaw(var0) + minecraft.thePlayer.rotationYaw, getPitch(var0) + minecraft.thePlayer.rotationPitch };
    }
    
    public static float[] getRotationNeededForBlock(EntityPlayer paramEntityPlayer, BlockPos pos) {
      double d1 = pos.getX() - paramEntityPlayer.posX;
      double d2 = pos.getY() + 0.5D - paramEntityPlayer.posY + paramEntityPlayer.getEyeHeight();
      double d3 = pos.getZ() - paramEntityPlayer.posZ;
      double d4 = Math.sqrt(d1 * d1 + d3 * d3);
      float f1 = (float)(Math.atan2(d3, d1) * 180.0D / Math.PI) - 90.0F;
      float f2 = (float)-(Math.atan2(d2, d4) * 180.0D / Math.PI);
      return new float[] { f1, f2 };
    }
    
    public static Rotation limitAngleChange(final Rotation currentRotation, final Rotation targetRotation, final float turnSpeed) {
		final float yawDifference = getAngleDifference(targetRotation.getYaw(), currentRotation.getYaw());
		final float pitchDifference = getAngleDifference(targetRotation.getPitch(), currentRotation.getPitch());

		return new Rotation(
				currentRotation.getYaw() + (yawDifference > turnSpeed ? turnSpeed : Math.max(yawDifference, -turnSpeed)),
				currentRotation.getPitch() + (pitchDifference > turnSpeed ? turnSpeed : Math.max(pitchDifference, -turnSpeed)
				));
	}
    
	private static float getAngleDifference(final float a, final float b) {
		return ((((a - b) % 360F) + 540F) % 360F) - 180F;
	}
    
    public static float getYaw(Entity var0) {
      double var5, var1 = var0.posX - minecraft.thePlayer.posX;
      double var3 = var0.posZ - minecraft.thePlayer.posZ;
      if (var3 < 0.0D && var1 < 0.0D) {
        var5 = 90.0D + Math.toDegrees(Math.atan(var3 / var1));
      } else if (var3 < 0.0D && var1 > 0.0D) {
        var5 = -90.0D + Math.toDegrees(Math.atan(var3 / var1));
      } else {
        var5 = Math.toDegrees(-Math.atan(var1 / var3));
      } 
      return MathHelper.wrapAngleTo180_float(-(minecraft.thePlayer.rotationYaw - (float)var5));
    }
    
    public static float getPitch(Entity var0) {
      double var1 = var0.posX - minecraft.thePlayer.posX;
      double var3 = var0.posZ - minecraft.thePlayer.posZ;
      double var5 = var0.posY - 1.6D + var0.getEyeHeight() - minecraft.thePlayer.posY;
      double var7 = MathHelper.sqrt_double(var1 * var1 + var3 * var3);
      double var9 = -Math.toDegrees(Math.atan(var5 / var7));
      return -MathHelper.wrapAngleTo180_float(minecraft.thePlayer.rotationPitch - (float)var9);
    }
  }
}