package Model.entities;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import Model.World;
import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.actions.EnumAction;
import Model.automata.creation.CategoryExtension;
import Model.automata.creation.DirectionExtension;
import Model.automata.creation.KeyExtension;
import Model.entities.Bullet.Bullet;
import Model.entities.Bullet.SwordStrick;
import Model.entities.weapon.Weapon;
import Model.monster_generator.Weapon_cover;
import Model.physics.ColliderType;
import Model.physics.PhysicsBody;
import Utils.Functions;
import Utils.SafeMapElement;
import Utils.Vector2;
import View.Avatar;

public class Entity implements SafeMapElement {

	protected long id;
	protected Avatar avatar;
	protected AutomatonState state;
	protected Automaton automaton;
	protected World world;
	protected ArrayList<EnumAction> actions;
	protected PhysicsBody body;
	protected float acceleration = 20.0f;

	public int team; // équipe: 1 = joueur
						// équipe: 2 = ennemis
						// équipe: 3 = neutre
						// Equipe 4 : cacheArme

	public Entity(Automaton a, int equipe) {
		// System.out.println("new entity");
		this.id = -1;
		automaton = a;
		state = automaton.getInit();
		actions = new ArrayList<EnumAction>();

		this.team = equipe;

	}

	public void tick(long elapsed) {
		
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

	@Override
	public long getID() {
		return id;
	}

	@Override
	public void setID(long id) {
		this.id = id;
	}

	public AutomatonState getState() {
		return state;
	}

	public World getWorld() {
		return world;
	}

	public Automaton getAutomaton() {
		return automaton;
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
		// DirectionExtension dir2 =
		// DirectionExtension.RelToAbsolute(this.directionEntite, dir);
		if (dir.ordinal() < 4) {
			Vector2 direction = new Vector2((float) body.getTransform().getShearX(),
					(float) body.getTransform().getScaleY());
			vect = Functions.getRelativeDir(dir, direction);
		} else {
			vect = Functions.getAbsoluteDir(dir);
		}
		// 2 next lines commented during the merge phase
		// vect.scale(world.getElapsed()*velocity/1000.0f);
		// transform.concatenate(AffineTransform.getTranslateInstance(vect.x, vect.y));
		// System.out.println("BOuge vers " + dir2);
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
		double angle;
		switch (dir) {
		case F:
			angle = Math.toRadians(0);
			break;
		case B:
			angle = Math.toRadians(180);
			break;
		case L:
			angle = Math.toRadians(90);
			break;
		case R:
			angle = Math.toRadians(-90);
			break;
		default:
			return;
		}
		getTransform().rotate(angle);
	}

	public void Wait() {
		// TODO Auto-generated method stub

	}

	public void Wizz(DirectionExtension dir) {
		// TODO Auto-generated method stub

	}

	public boolean Cell(DirectionExtension direction, CategoryExtension categorie) {

		// PROTOTYPE
		/*
		 * double angle = Math.atan2(getTransform().getShearY(),
		 * getTransform().getScaleY()); switch (direction) { case F: angle +=
		 * Math.toRadians(0); break; case B: angle += Math.toRadians(180); break; case
		 * L: angle += Math.toRadians(90); break; case R: angle += Math.toRadians(-90);
		 * break; default: return false; }
		 * 
		 * int x = (int) (Math.cos(angle) + getTransform().getTranslateX()); int y =
		 * (int) (Math.sin(angle) + getTransform().getTranslateY());
		 */

		return false;
	}

	public boolean Closest(DirectionExtension direction, CategoryExtension categorie) {
		return false;
		// TODO Auto-generated method stub

	}

	public boolean GotPower() {
		return true;
	}

	public boolean GotStuff() {
		return true;

	}

	public boolean getKey(KeyExtension key) {
		return world.getKey(key);

	}

	public boolean MyDir(DirectionExtension direction) {
		return false;
		// TODO Auto-generated method stub

	}

	public void colisionHappened(Entity other, ColliderType c) {
		//System.out.println("Collision de type " + c.toString()+ " entre l'entité " +
		//this.toString()+ " et " + other.toString());


		if (this instanceof Player && other instanceof Weapon_cover) {
			Weapon weapon = ((Weapon_cover) other).getW();
			if (weapon.isCac()) {
				((Player) this).setArmeCac(weapon);
			} else {
				((Player) this).setArmeDist(weapon);
			}
			world.removeEntity(other.getID());
		}
		// if the bullet meet a wall, destroy it
		if ((this instanceof Bullet && other instanceof Decor) || (this instanceof SwordStrick && other instanceof Decor)) {
			((LivingEntity) this).death();

		}
		
		else if(!(this.team == other.team)) {
			//System.out.println("equipe differente");
		// we check if both have life and enventually damages
			if ((this instanceof LivingEntity) && (other instanceof LivingEntity)) {
				// on regarde les teams:
				// we apply the damage on the life
				float damageEntity1 = ((LivingEntity) this).getDamage();
				float damageEntity2 = ((LivingEntity) other).getDamage();
				//System.out.println("Damage 1  = " + damageEntity1 + " damage 2 = " + damageEntity2 );
				((LivingEntity) this).damage(damageEntity2);
				((LivingEntity) other).damage(damageEntity1);

				if (this instanceof Bullet || this instanceof SwordStrick) {
					((LivingEntity) this).death();
				}
				
			}
		}
	}

	public Color getColor() {
		return Color.gray;
	}

	@Override
	public String toString() {
		return this.getClass().getName();
	}

	public boolean hasLifeBar() {
		return false;
	}

	protected void rotate() {
		Entity player = world.getPlayer();

		double relativeX = player.getTransform().getTranslateX() - getTransform().getTranslateX();
		double relativeY = player.getTransform().getTranslateY() - getTransform().getTranslateY();
		double relativeAngle = Math.atan2(relativeY, relativeX);

		relativeAngle -= Math.atan2(getTransform().getShearY(), getTransform().getScaleY());
		getTransform().rotate(relativeAngle - Math.toRadians(90));
	}
	
	public double getXRelatif() {
        return(getTransform().getTranslateX()%world.getGame_w());
    }
    public double getYRelatif() {
        return(getTransform().getTranslateY()%world.getGame_h());
    }
}
