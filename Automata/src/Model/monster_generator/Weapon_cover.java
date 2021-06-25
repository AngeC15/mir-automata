package Model.monster_generator;

import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.World;
import Model.entities.Entity;
import Model.entities.enemies.Plane;
import Model.entities.weapon.*;
import Model.loader.AutomataLoader;
import Model.loader.TemplatesLoader;
import Model.physics.ColliderType;
import Model.physics.HitBox;
import Model.physics.PhysicsBody;
import Model.physics.PrimitiveInstance;
import Model.physics.primitives.Circle;
import Model.physics.primitives.Square;
import View.Avatar;
import View.Template;

public class Weapon_cover extends Entity {
	Weapon w;

	public Weapon_cover(int nb_weapon,World world,double x,double y) throws IOException {
		super(AutomataLoader.get("Dead"), 3);
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Square(), AffineTransform.getScaleInstance(3.1f, 5.2f)));
		this.body = new PhysicsBody(h, ColliderType.Area, 15.0f, 40.0f, this);
		
		switch (nb_weapon) {
		case 0:
			w=new Axe();
			break;
		case 1:
			w=new Croissant();
			break;
		case 2:
			w=new Guisarm();
			break;
		case 3:
			w=new Halberd();
			break;
		case 4:
			w=new Juliette("Bullet");
			break;
		case 5:
			w=new Longsword();
			break;
		case 6:
			w=new MachineGun("Bullet");
			break;
		case 7:
			w=new Pistol("Bullet");
			break;
		case 8:
			w=new Revolver("Bullet");
			break;
		case 9:
			w=new Romeo();
			break;
		case 10:
			w=new Scythe();
			break;
		case 11:
			w=new Shotgun("Bullet");
			break;
		case 12:
			w=new Sniper("Bullet");
			break;
		case 13:
			w=new Sword();
			break;
		case 14:
			w=new TommyGun("Bullet");
			break;
		}
		
		Template tmpPlane = TemplatesLoader.get("Plane");
		new Avatar(this, tmpPlane);
		this.getTransform().concatenate(AffineTransform.getTranslateInstance(x,y));
		world.addEntity(this);
		
	}

	public Weapon getW() {
		return w;
	}

	public void setW(Weapon w) {
		this.w = w;
	}

}
