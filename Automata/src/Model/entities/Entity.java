package Model.entities;

import java.awt.geom.AffineTransform;

import Model.World;
import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.automata.creation.KeyExtension;
import Utils.Vector2;
import View.Avatar;

public class Entity {
	protected Avatar avatar;
	protected AutomatonState state;
	protected Automaton automaton;
	protected World world;
	protected int id;
	protected AffineTransform transform;
	protected Vector2 velocity;
	
	public Entity(int id, Avatar av, Automaton a, World w) {
		this.id = id;
		avatar = av;
		automaton = a;
		world = w;
		transform = new AffineTransform();
		world.addEntity(this, id);
		
	}
	public AffineTransform getTransform() {
		return transform;
	}
	public int getID() {
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
		// TODO Auto-generated method stub
		
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
	public void key(KeyExtension touche) {
		// TODO Auto-generated method stub
		
	}
	public void MyDir(DirectionExtension direction) {
		// TODO Auto-generated method stub
		
	}
}
