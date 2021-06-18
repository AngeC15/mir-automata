package Model.entities;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import Model.World;
import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.actions.EnumAction;
import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.automata.creation.KeyExtension;
import Utils.Functions;
import Utils.Vector2;
import View.Avatar;

public class Entity {
	protected Avatar avatar;
	protected AutomatonState state;
	protected Automaton automaton;
	protected World world;
	protected long id;
	protected AffineTransform transform;
	protected float velocity = 40.0f;
	protected ArrayList<EnumAction> actions;

	public Entity(Automaton a, World w) {
		this.id = w.getNextId();
		automaton = a;
		state = automaton.getInit();
		world = w;
		transform = new AffineTransform();
		world.addEntity(this, id);
		actions = new ArrayList<EnumAction>();
	}

	public void setAvatar(Avatar av) {
		avatar = av;
	}

	public AffineTransform getTransform() {
		return transform;
	}

	public void addAction(EnumAction action) {
		actions.add(action);
	}

	public ArrayList<EnumAction> getActions() {
		@SuppressWarnings("unchecked")
		ArrayList<EnumAction> returnList = (ArrayList<EnumAction>) actions.clone();
		actions.clear();
		return returnList;
	}

	public long getID() {
		return id;
	}

	public AutomatonState getState() {
		return state;
	}

	public void setState(AutomatonState state) {
		this.state = state;
	}

	public boolean step() {
		return automaton.step(this);
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void Egg(DirectionExtension dir) {
		// TODO Auto-generated method stub

	}

	public void Explode() {
		// TODO Auto-generated method stub

	}

	public void Get() {
		// TODO Auto-generated method stub

	}

	public void Hit(DirectionExtension dir) {
		// TODO Auto-generated method stub

	}

	public void Jump(DirectionExtension dir) {
		// TODO Auto-generated method stub

	}

	public void Move(DirectionExtension dir) {
		Vector2 vect;
		if (dir.ordinal() < 4) {
			Vector2 direction = new Vector2((float) transform.getShearX(), (float) transform.getScaleY());
			vect = Functions.getRelativeDir(dir, direction);
		} else {
			vect = Functions.getAbsoluteDir(dir);
		}
		vect.scale(world.getElapsed() * velocity / 1000.0f);
		transform.concatenate(AffineTransform.getTranslateInstance(vect.x, vect.y));
	}

	public void Pick(DirectionExtension dir) {
		// TODO Auto-generated method stub

	}

	public void Apply(DirectionExtension dir) {
		// TODO Auto-generated method stub

	}

	public void Power() {
		// TODO Auto-generated method stub

	}

	public void Protect(DirectionExtension dir) {
		// TODO Auto-generated method stub

	}

	public void Store() {
		// TODO Auto-generated method stub

	}

	public void Throw(DirectionExtension dir) {
		// TODO Auto-generated method stub

	}

	public void Turn(DirectionExtension dir) {
		// TODO Auto-generated method stub

	}

	public void Wait() {
		// TODO Auto-generated method stub

	}

	public void Wizz(DirectionExtension dir) {
		// TODO Auto-generated method stub

	}

	public boolean Cell(DirectionExtension direction, CategoryExtension categorie) {
		return false;
		// TODO Auto-generated method stub

	}

	public boolean Closest(DirectionExtension direction, CategoryExtension categorie) {
		return false;
		// TODO Auto-generated method stub

	}

	public boolean GotPower() {
		return false;
		// TODO Auto-generated method stub

	}

	public boolean GotStuff() {
		return false;
		// TODO Auto-generated method stub

	}

	public boolean getKey(KeyExtension key) {
		return world.getKey(key);

	}

	public boolean MyDir(DirectionExtension direction) {
		return false;
		// TODO Auto-generated method stub

	}
}
