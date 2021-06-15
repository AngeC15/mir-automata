package Model.entities;

import Model.World;
import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.creation.DirectionExtension;
import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.automata.creation.KeyExtension;
import Utils.Vector2;
import Utils.Functions;

public class Entity {
	protected AutomatonState state;
	protected Automaton automaton;
	protected World world;
	protected int id;
	protected Vector2 pos;
	protected Vector2 currentdir;
	protected float velocity = 3;
	
	public Entity(int id, Automaton a, World w, Vector2 p) {
		this.id = id;
		automaton = a;
		world = w;
		pos = p;
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
	
	public void move(DirectionExtension dir) {
		float coeff = world.getElapsed()*velocity/1000.0f;
		if (dir.ordinal() < 4) {
			pos.add(Functions.getRelativeDir(dir, currentdir).scale(coeff));
		}
		else {
			pos.add(Functions.getAbsoluteDir(dir).scale(coeff));
		}
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
