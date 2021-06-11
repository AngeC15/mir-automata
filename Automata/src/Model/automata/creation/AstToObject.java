package Model.automata.creation;

import java.util.List;

import Model.Entity;
import Model.automata.Automaton;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Value v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Underscore u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(FunCall funcall) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object exit(FunCall funcall, List<Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(BinaryOp operator, Object left, Object right) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(UnaryOp operator, Object expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(Mode mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object exit(Mode mode, Object source_state, Object behaviour) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(Condition condition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object exit(Condition condition, Object expression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(Action acton) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object exit(Action action, List<Object> funcalls) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Transition transition, Object condition, Object action, Object target_state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(Model.automata.ast.Automaton automaton) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object exit(Model.automata.ast.Automaton automaton, Object initial_state, List<Object> modes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AST bot, List<Object> automata) {
		// TODO Auto-generated method stub
		return null;
	}

	
		}
