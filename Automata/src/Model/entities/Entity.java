package Model.entities;

import java.awt.geom.AffineTransform;

import Model.World;
import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.actions.EnumAction;
import Model.automata.creation.DirectionExtension;
import Model.automata.creation.CategoryExtension;
import Model.automata.creation.KeyExtension;
import Utils.Vector2;
import View.Avatar;
import Utils.Functions;

public class Entity {
	protected Avatar avatar;
	protected AutomatonState state;
	protected EnumAction action;
	protected Automaton automaton;
	protected World world;
	protected long id;
	protected AffineTransform transform;
	protected float velocity = 40.0f;

	public Entity(Automaton a, World w) {
		this.id = w.getNextId();
		automaton = a;
		state = automaton.getInit();
		world = w;
		transform = new AffineTransform();
		world.addEntity(this, id);

	}

	public void setAvatar(Avatar av) {
		avatar = av;
	}

	public AffineTransform getTransform() {
		return transform;
	}

	public void setAction(EnumAction action) {
		this.action = action;
	}

	public EnumAction getAction() {
		return action;
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

	public void Cell(DirectionExtension direction, CategoryExtension categorie) {
		// TODO Auto-generated method stub

	}

	public void Closest(DirectionExtension direction, CategoryExtension categorie) {
		// TODO Auto-generated method stub

	}

	public void GotPower() {
		// TODO Auto-generated method stub

	}

	public void GotStuff() {
		// TODO Auto-generated method stub

	}

	public boolean getKey(KeyExtension key) {
		return world.getKey(key);

	}

	public void MyDir(DirectionExtension direction) {
		// TODO Auto-generated method stub

	}
}
