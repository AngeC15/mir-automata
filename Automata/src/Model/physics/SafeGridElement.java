package Model.physics;

import Utils.SafeMapElement;

public interface SafeGridElement extends SafeMapElement {
	void setPos(long i);
	long getPos();
	float getPosX_f();
	float getPosY_f();
	
}
