package Model.entities;

import java.awt.geom.AffineTransform;

import Model.World;
import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.creation.DirectionExtension;
import Model.automata.creation.CategoryExtension;
import Model.automata.creation.KeyExtension;
import Model.physics.PhysicsBody;
import Utils.Vector2;
import View.Avatar;
import Utils.Functions;


public class Entity {
	protected long id;
	protected Avatar avatar;
	protected AutomatonState state;
	protected Automaton automaton;
	protected World world;
	protected PhysicsBody body;
	protected float acceleration = 20.0f;
	
	double lastshot;
	
	public Entity(Automaton a, World w, long id) {
		this.id = id;
		automaton = a;
		state = automaton.getInit();
		world = w;
		lastshot = System.currentTimeMillis();
	}
	
	public void setAvatar(Avatar av) {
		avatar = av;
	}
	public AffineTransform getTransform() {
		return body.getTransform();
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
		if (dir.ordinal() < 4) {
			Vector2 direction = new Vector2((float)body.getTransform().getShearX(), (float)body.getTransform().getScaleY());
			vect = Functions.getRelativeDir(dir, direction);
		}
		else {
			vect = Functions.getAbsoluteDir(dir);
		}
		body.accelerate(world.getElapsed(), vect.scale(acceleration));
		
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
	public boolean GotPower() {
		double now = System.currentTimeMillis();
		if(now - lastshot > 100) {
			lastshot = now;
			return true;
		}
		return false;
		
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
