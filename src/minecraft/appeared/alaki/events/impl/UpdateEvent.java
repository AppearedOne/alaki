package appeared.alaki.events.impl;

import appeared.alaki.events.Event;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UpdateEvent extends Event {
    public double posX, posY, posZ;
    public boolean onGround;
    public float rotationYaw, rotationPitch;
	private float yaw;
	private float lastyaw;

    public UpdateEvent(double posX, double posY, double posZ, float rotationYaw, float rotationPitch, boolean onGround) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.onGround = onGround;
        this.yaw = yaw;
        this.lastyaw = lastyaw;
    }

    public void setX(double newPos){
        this.posX = newPos;
    }

    public void setY(double newPos){
        this.posY = newPos;
    }

    public void setZ(double newPos){
        this.posZ = newPos;
    }

    public void setYaw(float newYaw){
        this.rotationYaw = newYaw;
    }

    public void setPitch(float newPitch){
        this.rotationPitch = newPitch;
    }

    public void setOnGround(boolean newGround){
        this.onGround = newGround;
    }

    public double getPosX(){
        return this.posX;
    }

    public double getPosY(){
        return this.posY;
    }

    public double getPosZ(){
        return this.posZ;
    }

    public float getRotationYaw(){
        return this.rotationYaw;
    }

    public float getRotationPitch(){
        return this.rotationPitch;
    }

    public boolean isOnGround(){
        return this.onGround;
    }

	
	public float getLastYaw() {
		return lastyaw;
	}
}
