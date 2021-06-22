package Model.entities;

import Model.automata.Automaton;

/**
 * add life for entities, 
 * @author cyprien
 *
 */
public class LivingEntity extends Entity{
		protected float life;
		protected float damage;


	
		public LivingEntity(Automaton a, int equipe) {
			super(a, equipe);
		
		}


		public float getLife() {
			return life;
		}


		public void setLife(float life) {
			this.life = life;
		}

		/**
		 * applied the damage to entity life
		 * @param damage
		 */
		public void damage(float damage) {
			//negative damage will heal
			this.life -= damage;
		}


		public float getDamage() {
			return damage;
		}


		public void setDamage(float damage) {
			this.damage = damage;
		}
		
		

}
