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
import Model.physics.PhysicsBody;
import Utils.Vector2;
import View.Avatar;

public class Entity {
	protected long id;
	protected Avatar avatar;
	protected AutomatonState state;
	protected Automaton automaton;
	protected World world;
	protected ArrayList<EnumAction> actions;
	protected PhysicsBody body;
	protected float acceleration = 20.0f;
	double lastshot;
	
	
	public Entity(Automaton a) {
		this.id = -1;
		automaton = a;
		state = automaton.getInit();
		actions = new ArrayList<EnumAction>();
		lastshot = System.currentTimeMillis();
	}

	public void setAvatar(Avatar av) {
		avatar = av;
	}

	public AffineTransform getTransform() {
		return body.getTransform();
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

	public void setID(long id) {
		this.id = id;
	}
	public AutomatonState getState() {
		return state;
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World w) {
		world = w;
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

	public PhysicsBody getBody() {
		return body;
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
		//DirectionExtension dir2 = DirectionExtension.RelToAbsolute(this.directionEntite, dir);
		if (dir.ordinal() < 4) {
			Vector2 direction = new Vector2((float)body.getTransform().getShearX(), (float)body.getTransform().getScaleY());
			vect = Functions.getRelativeDir(dir, direction);
		} else {
			vect = Functions.getAbsoluteDir(dir);
		}
		//2 next lines commented during the merge phase
		//vect.scale(world.getElapsed()*velocity/1000.0f);
		//transform.concatenate(AffineTransform.getTranslateInstance(vect.x, vect.y));
		//System.out.println("BOuge vers " + dir2);
		body.accelerate(world.getElapsed(), vect.scale(acceleration));
	}

	public void Pick(DirectionExtension dir) {
		// TODO Auto-generated method stub

	}

	public void Apply(DirectionExtension dir) {
	
	}
	public void Pop(DirectionExtension dir) {
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
	public boolean GotPower() {
		double now = System.currentTimeMillis();
		if(now - lastshot > 500) {
			lastshot = now;
			return true;
		}
		return false;
	}

	public boolean GotStuff() {
		return false;

	}

	public boolean getKey(KeyExtension key) {
		return world.getKey(key);

	}

	public void MyDir(DirectionExtension direction) {
		// TODO Auto-generated method stub

	}
}
