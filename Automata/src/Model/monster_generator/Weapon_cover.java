package Model.monster_generator;

import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Model.World;
import Model.entities.Entity;
import Model.entities.enemies.Plane;
import Model.entities.weapon.AssaultRifle;
import Model.entities.weapon.*;
import Model.entities.weapon.Weapon;
import Model.entities.weapon.enum_weapon;
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

public class  Weapon_cover extends Entity {
	Weapon w;
	
	public Weapon_cover(int nb_weapon,World world) throws IOException {
		
		super(AutomataLoader.get("Dead"), 3);
		HitBox h = new HitBox();
		h.add(new PrimitiveInstance(new Square(), AffineTransform.getScaleInstance(3.1f, 5.2f)));
		this.body = new PhysicsBody(h, ColliderType.Area, 15.0f, 40.0f, this);
		w=new TommyGun("Bullet");
//		enum_weapon weapon = enum_weapon.values()[9];
		Template tmpPlane = TemplatesLoader.get("Plane");
		new Avatar(this, tmpPlane);
		this.getTransform().concatenate(AffineTransform.getTranslateInstance(10, 10));
		world.addEntity(this);
		
	}

	public Weapon getW() {
		return w;
	}

	public void setW(Weapon w) {
		this.w = w;
	}
	
}
