package Model.automata.actions;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Model.entities.Entity;

public class ActionList extends Action{
	
	private ArrayList<Action> weighted_actions;
	private ArrayList<Action> default_actions;
	
	private float total_weight;
	private ArrayList<Float> cumulative_weights;
	
	public ActionList() {
		total_weight = 0;
	}
	public void addAction(Action a) {
		default_actions.add(a);
	}
	
	public void addAction(Action a, float p) {
		total_weight += p;
		
		float last = 0.0f;
		if(cumulative_weights.size() > 0)
			last = cumulative_weights.get(cumulative_weights.size()-1);
		
		weighted_actions.add(a);
		cumulative_weights.add(last + p);
	}
	
	@Override
	public boolean apply(Entity e) {
		float r = (float)Math.random();
		if(r >= total_weight) {
			int randIdx = ThreadLocalRandom.current().nextInt(0, default_actions.size());
			return default_actions.get(randIdx).apply(e);
		}
		else {
			for(int i=weighted_actions.size(); i >= 0 ; i--) {
				if(cumulative_weights.get(i) < r)
					return weighted_actions.get(i).apply(e);
			}
		}
		System.out.println("ActionList.apply(): error");
		return false;
	}

}
