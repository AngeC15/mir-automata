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
	protected float velocity = 40.0f;
	double lastshot;
	protected PhysicsBody body;
	protected float acceleration = 20.0f;
	public int team;	//équipe: 1  = joueur
						//équipe: 2 = ennemis
						//équipe: 3 = neutre


	
	public Entity(Automaton a, int equipe) {
		System.out.println("new entity");
		this.id = -1;
		automaton = a;
		state = automaton.getInit();
		this.team = equipe;
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
	
	public int getEquipe() {
		return team;
	}
	public void setEquipe(int equipe) {
		this.team = equipe;
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
		}
		else {
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
