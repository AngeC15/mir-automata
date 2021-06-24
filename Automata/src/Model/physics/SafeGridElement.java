package Model.physics;

import Utils.SafeMapElement;

public interface SafeGridElement extends SafeMapElement {
	void setPos(int x, int y);
	int getPosX();
	int getPosY();
}
