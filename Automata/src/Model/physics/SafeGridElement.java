package Model.physics;

import Utils.SafeMapElement;

public interface SafeGridElement extends SafeMapElement {
	void setPos(long i);
	void setPos(int x, int y);
	long getPos();
	int getPosX();
	int getPosY();
	float getPosX_f();
	float getPosY_f();
	
}
