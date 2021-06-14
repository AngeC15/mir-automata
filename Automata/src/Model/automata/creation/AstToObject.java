package Model.automata.creation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Model.automata.Automaton;
import Model.automata.ast.AST;
import Model.automata.ast.Action;
import Model.automata.ast.Behaviour;
import Model.automata.ast.BinaryOp;
import Model.automata.ast.Category;
import Model.automata.ast.Condition;
import Model.automata.ast.Direction;
import Model.automata.ast.Expression;
import Model.automata.ast.FunCall;
import Model.automata.ast.IVisitor;
import Model.automata.ast.Key;
import Model.automata.ast.Mode;
import Model.automata.ast.State;
import Model.automata.ast.Transition;
import Model.automata.ast.UnaryOp;
import Model.automata.ast.Underscore;
import Model.automata.ast.Value;
import Model.automata.conditions.operators.AndOperator;
import Model.automata.conditions.operators.NotOperator;
import Model.automata.conditions.operators.OrOperator;
import Model.entities.Entity;

public class AstToObject implements IVisitor {

	@Override
	public Object visit(Category cat) {
		CategoryExtension categorie = null;
		//on récupère ce que le parser a detecté
		switch(cat.terminal.content) {
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
		//same as category, but for directions
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
		switch(key.terminal.content) {
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
		//on retourne un integer avec la value de v
		return v.value;
	}

	@Override
	public Object visit(Underscore u) {
		//on return ce qui correspond à l'underscore dans notre catégorie
		return CategoryExtension.ENTITY;
	}

	@Override
	public void enter(FunCall funcall) {
		System.out.println("Not yet implemented need help");
		
	}

	@Override
	public Object exit(FunCall funcall, List<Object> parameters) {
		System.out.println("Not yet implemented need help");
		return null;
	}

	@Override
	public Object visit(BinaryOp operator, Object left, Object right) {
		Model.automata.conditions.Condition result = null;
		switch(operator.operator) { //Récupération de l'operation, le not n'est pas pris en compte, vu
									//que c'est un operateur unitaire
			case "/": //Cas du ou
				try {
					result = new OrOperator(right, left);
				} catch (Exception e) {
					System.out.println("Erreur dans visit avec l'operateur ou");
				}
				break;
			case "&": //cas du et
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
		switch(operator.operator) {
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
		String nom = state.name;
		return new StateExtension(nom);
		
	}

	@Override
	public void enter(Mode mode) {
		System.out.println("Not yet implemented need help");		
	}

	@Override
	public Object exit(Mode mode, Object source_state, Object behaviour) {
		//quand on quitte le "noeud" on veut en obtenir un mode (CAD un comportement associer à un état)
		return new ModeExtension((StateExtension)source_state,(BehaviourExtension)behaviour);
		
	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		List<Transition> TransitionList = new LinkedList<Transition>();
		//Création d'un iterateur pour parcourir les transition (qui sont de type object, il faudra donc transtyper)
		Iterator iterateur = transitions.iterator();
		while(iterateur.hasNext()) {
			TransitionList.add((Transition) iterateur.next());
		}
		//on transforme cette liste de Transitions en comportement (behaviour) et on y retourne
		
		return new Behaviour(TransitionList);
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
		Model.automata.Transition trans = new Model.automata.Transition((StateExtension)target_state,(Model.automata.conditions.Condition)condition, (Model.automata.actions.Action)action);
		return trans;
	}

	@Override
	public void enter(Model.automata.ast.Automaton automaton) {
		System.out.println("Not yet implemented need help");
		
	}

	@Override
	public Object exit(Model.automata.ast.Automaton automaton, Object initial_state, List<Object> modes) {
		System.out.println("Not yet implemented need help");
		return null;
	}

	@Override
	public Object visit(AST bot, List<Object> automata) {
		System.out.println("Not yet implemented need help");
		return null;
	}

	
		}
