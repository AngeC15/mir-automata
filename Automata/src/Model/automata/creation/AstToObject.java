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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object exit(FunCall funcall, List<Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(BinaryOp operator, Object left, Object right) {
		operator result = null;
		switch(operator.operator) {
			case "":
				break;
		}
		return result;
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
