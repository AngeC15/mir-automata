package Controller;

import java.awt.geom.NoninvertibleTransformException;

import Model.automata.creation.KeyExtension;
import Utils.Vector2;
import View.GameView;

public class VirtualInput {
	private boolean[] keys;
	private Vector2 mouseWorld;
	private Vector2 mousePlayer;
	private GameView view;
	
	private static final int letters_code = 65;
	private static final int numbers_code = 48;
	private static final int special_code = 16; //shift, control, alt...
	private static final int space_code = 32;
	private static final int enter_code = 10;
	private static final int arrows_code = 37; // L, U, R, D
	
	private static final int letters_offset = 0;
	private static final int numbers_offset = 26;
	private static final int blank_offset = 36;
	private static final int arrows_offset = 38;
	
	private static final int[] codes = {letters_code, numbers_code, special_code, space_code, enter_code, arrows_code};
	private static final int[] sizes = {26, 5, 2, 1, 1, 4};
	private static final int[] offsets = {letters_offset, numbers_offset, numbers_offset + 8, blank_offset, blank_offset+1, arrows_offset};
	
	public VirtualInput() {
		keys = new boolean[42];
	}
	public void setView(GameView v) {
		view = v;
	}
	public void updateKeys(int keycode, boolean value) {
		if(keycode < 0) {
			keys[numbers_offset + 5 + (-keycode) - 1] = value;
			return;
		}
		for(int i=0; i < codes.length; i++) {
			if(keycode >= codes[i] && keycode < codes[i] + sizes[i]) {
				keys[keycode - codes[i] + offsets[i]] = value;
				break;
			}
		}
	}
	public void updateMouse(int x, int y) {
		
		try {
			mouseWorld = view.getMouseWorld(x, y);
			mousePlayer = view.getMousePlayer(x, y);
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Vector2 getMouseWorld() {
		return mouseWorld;
	}
	public Vector2 getMousePlayer() {
		return mousePlayer;
	}
	public boolean getKey(KeyExtension key) {
		return keys[key.ordinal()];
	}
	
}
