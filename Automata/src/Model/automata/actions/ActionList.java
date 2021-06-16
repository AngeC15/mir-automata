package Model.automata.actions;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Model.entities.Entity;

/**
 * 
 * @author Julian
 *
 */
public class ActionList extends Action{
	
	private ArrayList<Action> weighted_actions;
	private ArrayList<Action> default_actions;
	
	private float total_weight;
	private ArrayList<Float> cumulative_weights;
	
	public ActionList(float weight) {
		super(weight);
		total_weight = 0;
		this.cumulative_weights = new ArrayList<Float>();
		this.default_actions = new ArrayList<Action>();
		this.weighted_actions = new ArrayList<Action>();
	}
	
	public void addAction(Action a) {
		float p = a.getWeigth();
		if(p < 0) {
			p = 1-total_weight;
			a.setWeight(p);
		}
		
		total_weight += p;
		
		float last = 0.0f;
		if(cumulative_weights.size() > 0)
			last = cumulative_weights.get(cumulative_weights.size()-1);
		
		weighted_actions.add(a);
		cumulative_weights.add(last + p);
	}
	
	@Override
	public boolean apply(Entity e) {
		/*
		 * When we want to choose a random action among all available, we need to take all -1's weight
		 *with the same percentage depending on the number of total_weight and how many action with -1's weight left
		*/
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
