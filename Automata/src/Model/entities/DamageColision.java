package Model.entities;

public abstract class DamageColision {
	
	/**
	 * use to change the life of entities
	 * @param e1 first entity of colision
	 * @param e2 second entity of colision
	 */
	public void colision(Entity e1, Entity e2) {
		//check if damaging entity and have life:
		//2 cases
		int cas; 
		if(e1 instanceof DamagingEntity && e2 instanceof LivingEntity) {
			cas = 1; //entity 1 which is giving damage
		}else if(e1 instanceof LivingEntity && e2 instanceof DamagingEntity){
			cas = 2; //entity 2 which is giving damage
		}
		else {
			return;
		}
		//check the team:
		if(e1.getEquipe() == e2.getEquipe()) {
			//si dans la même équipe, pas de dégats
			return;
		}
		if((e1.getEquipe() == 1 && e2.getEquipe() == 2) || (e1.getEquipe() == 1 && e2.getEquipe() == 3)	//celui qui donne les dégats appartient à l'equipe joueur
		|| (e1.getEquipe() == 2 && e2.getEquipe() == 1) || (e1.getEquipe() == 2 && e2.getEquipe() == 3) ){ //celui qui donne les dégats appartient à l'equipe des méchants
			//need to find who receive the damages
			float damage;
			if(cas == 1) {
				damage = ((DamagingEntity) e1).getDamage();
				((LivingEntity) e2).damage(damage);
				System.out.println("Entity vie restante : "+ ((LivingEntity) e2).getLife());
				
			}else if(cas == 2) {
				damage = ((DamagingEntity) e2).getDamage();
				((LivingEntity) e1).damage(damage);
				System.out.println("Entity vie restante : "+ ((LivingEntity) e1).getLife());

				
			}
			else {
				System.out.println("Erreur au niveau du damage collision et de l'application des damages");
			}
			//il faut supprimer l'entité qui fait des dommages

			
			checkDeath(e1, e2);
		}
	}
	
	public void checkDeath(Entity e1, Entity e2) {
		
	}
}
