package Model.automata.creation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Model.automata.Automaton;
import Model.automata.AutomatonState;
import Model.automata.actions.Egg;
import Model.automata.actions.Explode;
import Model.automata.actions.Get;
import Model.automata.actions.Hit;
import Model.automata.actions.Jump;
import Model.automata.actions.Move;
import Model.automata.actions.Pick;
import Model.automata.actions.Pop;
import Model.automata.actions.Power;
import Model.automata.actions.Protect;
import Model.automata.actions.Store;
import Model.automata.actions.Throw;
import Model.automata.actions.Turn;
import Model.automata.actions.Wait;
import Model.automata.actions.Wizz;
import Model.automata.ast.AST;
import Model.automata.ast.Action;
import Model.automata.ast.Behaviour;
import Model.automata.ast.BinaryOp;
import Model.automata.ast.Category;
import Model.automata.ast.Condition;
import Model.automata.ast.Direction;
import Model.automata.ast.FunCall;
import Model.automata.ast.IVisitor;
import Model.automata.ast.Key;
import Model.automata.ast.Mode;
import Model.automata.ast.State;
import Model.automata.ast.Transition;
import Model.automata.ast.UnaryOp;
import Model.automata.ast.Underscore;
import Model.automata.ast.Value;
import Model.automata.conditions.Cell;
import Model.automata.conditions.Closest;
import Model.automata.conditions.GotPower;
import Model.automata.conditions.GotStuff;
import Model.automata.conditions.MyDir;
import Model.automata.conditions.True;
import Model.automata.conditions.operators.AndOperator;
import Model.automata.conditions.operators.NotOperator;
import Model.automata.conditions.operators.OrOperator;

public class AstToObject implements IVisitor {
	
	private HashMap<String, AutomatonState> state_names;

	@Override
	public Object visit(Category cat) {
		CategoryExtension categorie = null;
		// We take back what the parser detected
		switch (cat.terminal.content) {
		case "A":
			categorie = CategoryExtension.A;
			break;
		case "C":
			categorie = CategoryExtension.C;
			break;
		case "D":
			categorie = CategoryExtension.D;
			break;
		case "G":
			categorie = CategoryExtension.G;
			break;
		case "J":
			categorie = CategoryExtension.J;
			break;
		case "M":
			categorie = CategoryExtension.M;
			break;
		case "O":
			categorie = CategoryExtension.O;
			break;
		case "P":
			categorie = CategoryExtension.P;
			break;
		case "T":
			categorie = CategoryExtension.T;
			break;
		case "V":
			categorie = CategoryExtension.V;
			break;
		case "@":
			categorie = CategoryExtension.PLAYER;
			break;
		case "_":
			categorie = CategoryExtension.ENTITY;
			break;
		default:
			System.out.println("Should never happen, category not recognized");
			System.out.println("By default, void value used");
			categorie = CategoryExtension.V;
			break;
		}
		return categorie;
	}

	@Override
	public Object visit(Direction dir) {
		// Same as category, but for directions
		DirectionExtension orientation = null;
		switch (dir.terminal.content) {
		case "N":
			orientation = DirectionExtension.N;
			break;
		case "NE":
			orientation = DirectionExtension.NE;
			break;
		case "E":
			orientation = DirectionExtension.E;
			break;
		case "SE":
			orientation = DirectionExtension.E;
			break;
		case "S":
			orientation = DirectionExtension.S;
			break;
		case "SO":
			orientation = DirectionExtension.SO;
			break;
		case "O":
			orientation = DirectionExtension.O;
			break;
		case "NO":
			orientation = DirectionExtension.NO;
			break;
		case "F":
			orientation = DirectionExtension.F;
			break;
		case "R":
			orientation = DirectionExtension.R;
			break;
		case "B":
			orientation = DirectionExtension.B;
			break;
		case "L":
			orientation = DirectionExtension.L;
			break;
		default:
			System.out.println("Should never happen, direction not recognized");
			System.out.println("By default, Est value used");
			orientation = DirectionExtension.E;
			break;
		}
		return orientation;
	}

	@Override
	public Object visit(Key key) {
		KeyExtension cle = null;
		switch (key.terminal.content) {
		case "a":
			cle = KeyExtension.A;
			break;
		case "z":
			cle = KeyExtension.Z;
			break;
		case "e":
			cle = KeyExtension.E;
			break;
		case "r":
			cle = KeyExtension.R;
			break;
		case "t":
			cle = KeyExtension.T;
			break;
		case "y":
			cle = KeyExtension.Y;
			break;
		case "u":
			cle = KeyExtension.U;
			break;
		case "i":
			cle = KeyExtension.I;
			break;
		case "o":
			cle = KeyExtension.O;
			break;
		case "p":
			cle = KeyExtension.P;
			break;
		case "q":
			cle = KeyExtension.Q;
			break;
		case "s":
			cle = KeyExtension.S;
			break;
		case "d":
			cle = KeyExtension.D;
			break;
		case "f":
			cle = KeyExtension.F;
			break;
		case "g":
			cle = KeyExtension.G;
			break;
		case "h":
			cle = KeyExtension.H;
			break;
		case "j":
			cle = KeyExtension.J;
			break;
		case "k":
			cle = KeyExtension.K;
			break;
		case "l":
			cle = KeyExtension.L;
			break;
		case "m":
			cle = KeyExtension.M;
			break;
		case "w":
			cle = KeyExtension.W;
			break;
		case "x":
			cle = KeyExtension.X;
			break;
		case "c":
			cle = KeyExtension.C;
			break;
		case "v":
			cle = KeyExtension.V;
			break;
		case "b":
			cle = KeyExtension.B;
			break;
		case "n":
			cle = KeyExtension.N;
			break;
		case "0":
			cle = KeyExtension.ZERO;
			break;
		case "1":
			cle = KeyExtension.UN;
			break;
		case "2":
			cle = KeyExtension.DEUX;
			break;
		case "3":
			cle = KeyExtension.TROIS;
			break;
		case "4":
			cle = KeyExtension.QUATRE;
			break;
		case "5":
			cle = KeyExtension.CINQ;
			break;
		case "6":
			cle = KeyExtension.SIX;
			break;
		case "7":
			cle = KeyExtension.SEPT;
			break;
		case "8":
			cle = KeyExtension.HUIT;
			break;
		case "9":
			cle = KeyExtension.NEUF;
			break;
		case "SPACE":
			cle = KeyExtension.SPACE;
			break;
		case "ENTER":
			cle = KeyExtension.ENTER;
			break;
		case "FU":
			cle = KeyExtension.AU;
			break;
		case "FR":
			cle = KeyExtension.AR;
			break;
		case "FD":
			cle = KeyExtension.AD;
			break;
		case "FL":
			cle = KeyExtension.AL;
			break;
		default:
			System.out.println("Should never happen, Key not recognized");
			System.out.println("By default, ENTER value used");
			cle = KeyExtension.ENTER;
			break;
		}
		return cle;
	}

	@Override
	public Object visit(Value v) {
		// We return an integer with the value of v.
		return v.value;
	}

	@Override
	public Object visit(Underscore u) {
		// We return what is the underscore in our category.
		return CategoryExtension.ENTITY;
	}

	@Override
	public void enter(FunCall funcall) {
		System.out.println("Not yet implemented need help");

	}

	@Override
	public Object exit(FunCall funcall, List<Object> parameters) {
		//reglages de paramètres
		//il faut integrer les conditions
		Object param1 = null,param2 = null;
		//parameters length:
		int longParam = parameters.size();
		Object parametrePremier = null;;
		if(longParam > 0) {
			parametrePremier = parameters.get(0);
			if(parametrePremier instanceof DirectionExtension || parametrePremier instanceof CategoryExtension) {
				param1 = parameters.get(0);
				if( longParam > 1) {
					//on a forcément en deuxième une catégorie ou une direction, rien d'autre n'a que 2 arguments
					param2 = parameters.get(1);
				}
			}else if(parametrePremier instanceof KeyExtension) {
				param1 = parameters.get(0);
			}
		}
		switch (funcall.name) {
		case "Wizz":
				return new Wizz((DirectionExtension) param1);
		case "Pop":
				return new Pop((DirectionExtension) param1);
		case "Wait":
				return new Wait();
		case "Move":
				return new Move((DirectionExtension) param1);
		case "Jump":
				return new Jump((DirectionExtension) param1);
		case "Turn":
				return new Turn((DirectionExtension) param1);
		case "Hit":
				return new Hit((DirectionExtension) param1);
		case "Protect":
				return new Protect((DirectionExtension) param1);
		case "Pick":
				return new Pick((DirectionExtension) param1);
		case "Throw":
				return new Throw((DirectionExtension) param1);
		case "Store":
				return new Store();
		case "Get":
				return new Get();
		case "Power":
				return new Power();
		case "Explode":
				return new Explode();
		case "Egg":
				return new Egg(DirectionExtension.F);
		case "Cell":
				return new Cell((DirectionExtension) param1, (CategoryExtension) param2);
		case "Closest":
				return new Closest((DirectionExtension) param2, (CategoryExtension) param1);
		case "GotPower":
				return new GotPower();
		case "GotStuff":
				return new GotStuff();
		case "Key":
				return new Model.automata.conditions.Key((KeyExtension) param1);
		case "MyDir":
				return new MyDir((DirectionExtension) param1);
		case "True":
				return new True();
		default:
			throw new RuntimeException("Die");
		}

	}

	@Override
	public Object visit(BinaryOp operator, Object left, Object right) {
		Model.automata.conditions.Condition result = null;
		switch (operator.operator) { // Récupération de l'operation, le not n'est pas pris en compte, vu
										// que c'est un operateur unitaire
		case "/": // Cas du ou
			try {
				result = new OrOperator(right, left);
			} catch (Exception e) {
				System.out.println("Erreur dans visit avec l'operateur ou");
			}
			break;
		case "&": // cas du et
			try {
				result = new AndOperator(right, left);
			} catch (Exception e) {
				System.out.println("Erreur dans visit avec l'operateur et");
			}
			break;
		}
		return result;
	}

	@Override
	public Object visit(UnaryOp operator, Object expression) {
		Model.automata.conditions.Condition result = null;
		switch (operator.operator) {
		case "!":
			result = new NotOperator((Model.automata.conditions.Condition) expression);
			break;
		default:
			System.out.println("Should never happen, Unary Operator not recognized");
			System.out.println("By default, not value used");
			result = new NotOperator((Model.automata.conditions.Condition) expression);
			break;
		}
		return result;
	}

	@Override
	public Object visit(State state) {
		//Here we check if the name is already in the hashset
		String name = state.name;
		if(state_names.containsKey(name)) {
			return state_names.get(name);
		}
		AutomatonState as = new AutomatonState(name); 
		state_names.put(name, as);
		return as;

	}

	@Override
	public void enter(Mode mode) {
		System.out.println("Not yet implemented need help");
	}

	@Override
	public Object exit(Mode mode, Object source_state, Object behaviour) {
		// quand on quitte le "noeud" on veut en obtenir un mode (CAD un comportement
		// associer à un état)
		//return new ModeExtension((AutomatonState) source_state, (BehaviourExtension) behaviour);
		for(Model.automata.Transition t : (ArrayList<Model.automata.Transition>) behaviour) {
			((AutomatonState)source_state).setTransition((ArrayList<Model.automata.Transition>) behaviour);
		}
		return source_state;

	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		List<Model.automata.Transition> transitionList = new ArrayList<Model.automata.Transition>();
		// Création d'un iterateur pour parcourir les transition (qui sont de type
		// object, il faudra donc transtyper)
		Iterator iterateur = transitions.iterator();
		while (iterateur.hasNext()) {
			transitionList.add((Model.automata.Transition) iterateur.next());
		}
		// on transforme cette liste de Transitions en comportement (behaviour) et on y
		// retourne

		return transitionList;
	}

	@Override
	public void enter(Condition condition) {
		System.out.println("Not yet implemented need help");
	}

	@Override
	public Object exit(Condition condition, Object expression) {
		System.out.println("Not yet implemented need help");
		return null;
	}

	@Override
	public void enter(Action action) {
		System.out.println("Not yet implemented need help");

	}

	@Override
	public Object exit(Action action, List<Object> funcalls) {
		System.out.println("Not yet implemented need help");
		return null;
	}

	@Override
	public Object visit(Transition transition, Object condition, Object action, Object target_state) {
		return new Model.automata.Transition((AutomatonState) target_state,
				(Model.automata.conditions.Condition) condition, (Model.automata.actions.Action) action);
	}

	@Override
	public void enter(Model.automata.ast.Automaton automaton) {
		//System.out.println("Not yet implemented need help");
		//TODO voir si ce n'est pas plutôt à laisser vide
		state_names = new HashMap<String, AutomatonState>();

	}

	@Override
	public Object exit(Model.automata.ast.Automaton automaton, Object initial_state, List<Object> modes) {
		//ArrayList<AutomatonState> li =  new ArrayList<AutomatonState>((List<AutomatonState>)modes);
		Automaton auto = new Automaton();
		for(Object li2 : modes) {
			auto.addState((AutomatonState) li2);
		}
		auto.setInit((AutomatonState) initial_state);
		return auto;
		
	}

	@Override
	public Object visit(AST bot, List<Object> automata) {
		List<Automaton> listAutomaton = new LinkedList<Automaton>();
		//on parcours les différents automates de l'AST
		Iterator iterateurAutomate = automata.iterator();
		while(iterateurAutomate.hasNext()) {
			//on ajoute à notre liste l'automate recontré
			listAutomaton.add((Automaton) iterateurAutomate.next());
		}
		return listAutomaton;
	}

}
