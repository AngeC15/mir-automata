package Model.automata;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Model.Entity;
import Model.GameState;
import Model.automata.actions.Action;
import Model.automata.conditions.Condition;

public class Automaton {
	private ArrayList<ArrayList<Transition>> states;
	public Automaton() {
		states = new ArrayList<ArrayList<Transition>>();
	}
	public int addState() {
		states.add(new ArrayList<Transition>());
		return states.size();
	}
	public int addTransition(int src, int dst, Condition cond, Action act){
		states.get(src).add(new Transition(dst, cond, act));
		return states.get(src).size();
	}
	public boolean step(Entity entity, GameState gs) {
		ArrayList<Transition> transitions = states.get(entity.getState());
		ArrayList<Transition> valid = new ArrayList<Transition>();
		for(int i=0; i < transitions.size(); i++) {
			Transition t = transitions.get(i);
			if(t.condition.eval(entity, gs))
				valid.add(t);
		}
		if(valid.size() > 0) {
			int randIdx = ThreadLocalRandom.current().nextInt(0, valid.size() + 1);
			boolean r = valid.get(randIdx).action.apply(entity, gs);
			entity.setState(valid.get(randIdx).destination);
			return r;
		}
		return false;
	}
}
