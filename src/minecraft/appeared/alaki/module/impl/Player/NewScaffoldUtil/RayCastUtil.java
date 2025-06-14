package appeared.alaki.module.impl.Player.NewScaffoldUtil;

import java.util.List;

import com.google.common.base.Predicates;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;




public class RayCastUtil {
  private static final Minecraft mc = Minecraft.getMinecraft();
  
  public static Entity rayCast(double range, float yaw, float pitch) {
    double d0 = range;
    double d1 = d0;
    Vec3 vec3 = mc.thePlayer.getPositionEyes(1.0F);
    boolean flag = false;
    boolean flag1 = true;
    if (d0 > 3.0D)
      flag = true; 
    Vec3 vec31 = getVectorForRotation(pitch, yaw);
    Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
    Entity pointedEntity = null;
    Vec3 vec33 = null;
    float f = 1.0F;
    List<Entity> list = mc.theWorld.getEntitiesInAABBexcluding(mc.getRenderViewEntity(), mc.getRenderViewEntity().getEntityBoundingBox().addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand(f, f, f), Predicates.and(EntitySelectors.NOT_SPECTATING, Entity::canBeCollidedWith));
    double d2 = d1;
    for (int i = 0; i < list.size(); i++) {
      Entity entity1 = list.get(i);
      float f1 = entity1.getCollisionBorderSize();
      AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f1, f1, f1);
      MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);
      if (axisalignedbb.isVecInside(vec3)) {
        if (d2 >= 0.0D) {
          pointedEntity = entity1;
          vec33 = (movingobjectposition == null) ? vec3 : movingobjectposition.hitVec;
          d2 = 0.0D;
        } 
      } else if (movingobjectposition != null) {
        double d3 = vec3.distanceTo(movingobjectposition.hitVec);
        if (d3 < d2 || d2 == 0.0D) {
          boolean flag2 = false;
          
          if (entity1 == (mc.getRenderViewEntity()).ridingEntity && !flag2) {
            if (d2 == 0.0D) {
              pointedEntity = entity1;
              vec33 = movingobjectposition.hitVec;
            } 
          } else {
            pointedEntity = entity1;
            vec33 = movingobjectposition.hitVec;
            d2 = d3;
          } 
        } 
      } 
    } 
    return pointedEntity;
  }
  
  public static Vec3 getVectorForRotation(float pitch, float yaw) {
    float f = MathHelper.cos(-yaw * 0.017453292F - 3.1415927F);
    float f1 = MathHelper.sin(-yaw * 0.017453292F - 3.1415927F);
    float f2 = -MathHelper.cos(-pitch * 0.017453292F);
    float f3 = MathHelper.sin(-pitch * 0.017453292F);
    return new Vec3((f1 * f2), f3, (f * f2));
  }
}
