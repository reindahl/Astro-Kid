package pddl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import pddl.Litereal.litType;

public class Predicate {

	ArrayList<Litereal> objects = new ArrayList<>();
	ArrayList<litType> objTypes = new ArrayList<>();
	public enum Ptype {at, moving, clear, goal, closed, relativDir, ground, ladder, facing, boarder, teleport, button, remote, boot, gate}
	boolean generalised =false;
	boolean negate = false;
	public final Ptype type;



	public Predicate(Predicate predicate){
		this.negate=predicate.negate;
		this.objects= new ArrayList<>(predicate.objects);
		this.objTypes= new ArrayList<>(predicate.objTypes);
		this.type= predicate.type;
		this.generalised=predicate.generalised;
	}

	public Predicate(Ptype type, Litereal... litereals) {
		this.type =type;
		objTypes=getTypes(type);
		for (Litereal litereal : litereals) {
			objects.add(litereal);
			if(litereal==null){
				System.out.println("Literal is null (have no name) "+ type);
				System.out.println(this);
			}
		}

	}

	public Predicate(Ptype type, boolean negate, Litereal... litereals) {
		this.type =type;
		objTypes=getTypes(type);
		this.negate=negate;
		for (Litereal litereal : litereals) {
			objects.add(litereal);
		}

	}
	
	public static ArrayList<litType> getTypes(Ptype type){
		ArrayList<litType> result = new ArrayList<>();
		switch (type) {
		case at:
			result.add(litType.thing);
			result.add(litType.location);
			break;
		case boarder:
			result.add(litType.location);
			break;
		case boot:
			result.add(litType.colour);
			result.add(litType.location);
			break;
		case button:
			result.add(litType.colour);
			result.add(litType.location);
			break;
		case clear:
			result.add(litType.location);
			break;
		case closed:
			result.add(litType.location);
			break;
		case facing:
			result.add(litType.thing);
			result.add(litType.direction);
			break;
		case gate:
			result.add(litType.colour);
			result.add(litType.location);
			break;
		case goal:
			result.add(litType.location);
			break;
		case ground:
			result.add(litType.colour);
			result.add(litType.location);
			break;
		case ladder:
			result.add(litType.location);
			break;
		case moving:
			result.add(litType.thing);
			result.add(litType.direction);
			break;
		case relativDir:
			result.add(litType.location);
			result.add(litType.location);
			result.add(litType.direction);
			break;
		case teleport:
			result.add(litType.location);
			break;
		default:
			System.err.println("pre: unknown "+type);
			System.exit(-1);
			break;
		}
		
		return result;
	}
	public Predicate(Predicate predicate, boolean flip) {
		this.negate=predicate.negate;
		this.objects= new ArrayList<>(predicate.objects);
		this.objTypes= new ArrayList<>(predicate.objTypes);
		this.type= predicate.type;
		this.generalised=predicate.generalised;
		negate();
	}

	@Override
	public String toString(){
		String s="";
		if(negate){
			s+="(not ";
		}
		if(type == Ptype.relativDir){
			s+= "(relativ-dir ";
		}else{
			s+= "("+type+" ";
		}

		for (int i = 0; i < objects.size(); i++) {
			if(generalised && !PddlProblem.consObjects.contains(objects.get(i))){
				s+="?";
			}
			s+=objects.get(i);
			if(i<objects.size()-1){
				s+=" ";
			}
		}
		s+=")";		
		if(negate){
			s+=")";
		}
		return s;
	}

	public String toStringflipped(){
		String s="";
		if(!negate){
			s+="(not ";
		}
		if(type == Ptype.relativDir){
			s+= "(relativ-dir ";
		}else{
			s+= "("+type+" ";
		}

		for (int i = 0; i < objects.size(); i++) {
			if(generalised){
				s+="?";
			}
			s+=objects.get(i);
			if(i<objects.size()-1){
				s+=" ";
			}
		}
		s+=")";		
		if(!negate){
			s+=")";
		}
		return s;
	}
	@Override
	public int hashCode() {

		return toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		return toString().equals(obj.toString());
	}
	public void negate() {
		negate=!negate;

	}

	/**
	 * generate all possible groundings with given parameters
	 * @param param
	 * @return
	 */
	public ArrayList<Predicate> generateGroundings(final Collection<Litereal> param){
		ArrayList<Litereal> objects = new ArrayList<>();
		objects.addAll(PddlProblem.consObjects);
		for (Litereal litereal : param) {
			if(!PddlProblem.consObjects.contains(litereal)){
				objects.add(litereal);
			}
		}


		ArrayList<Predicate> result = new ArrayList<>();

		Litereal litereals[]= new Litereal[this.objects.size()];
		generate(result, objects, litereals, 0);

		return result;
	}
	public static ArrayList<Predicate> generateGroundings(Ptype type, final Collection<Litereal> param){
		ArrayList<litType> objTypes= getTypes(type);
		
		ArrayList<Litereal> objects = new ArrayList<>();
		objects.addAll(PddlProblem.consObjects);
		for (Litereal litereal : param) {
			if(!PddlProblem.consObjects.contains(litereal)){
				objects.add(litereal);
			}
		}


		ArrayList<Predicate> result = new ArrayList<>();

		Litereal litereals[]= new Litereal[objTypes.size()];
		generate2(result, objects, litereals, 0, type, objTypes);

		return result;
	}
	private static void generate2(ArrayList<Predicate> predicates, final Collection<Litereal> param, Litereal litereals[] , int position, Ptype type, ArrayList<litType> objTypes){
		if(position<litereals.length){
			for (Litereal litereal : param) {
				//				System.out.println(objects.get(position).getTypes());
				if(Litereal.getTypes(objTypes.get(position)).contains(litereal.type)){
//				if(objects.get(position).getTypes().contains(litereal.type)){
					//				if(objects.get(position).type == litereal.type){
					litereals[position]=litereal;
					generate2(predicates, param, litereals, position+1, type, objTypes);
				}
			}

		}else{
			predicates.add(new Predicate(type, true, litereals));
			predicates.add(new Predicate(type, false, litereals));
		}


	}
	private void generate(ArrayList<Predicate> predicates, final Collection<Litereal> param, Litereal litereals[] , int position){
		if(position<litereals.length){
			for (Litereal litereal : param) {
				//				System.out.println(objects.get(position).getTypes());
				if(Litereal.getTypes(objTypes.get(position)).contains(litereal.type)){
//				if(objects.get(position).getTypes().contains(litereal.type)){
					//				if(objects.get(position).type == litereal.type){
					litereals[position]=litereal;
					generate(predicates, param, litereals, position+1);
				}
			}

		}else{
			predicates.add(new Predicate(type, true, litereals));
			predicates.add(new Predicate(type, false, litereals));
		}


	}

//	public Predicate generalise(HashMap<Litereal, Litereal> params) {
//		Litereal[] lits = new Litereal[objects.size()];
//		for (int i = 0; i < objects.size(); i++) {
//			lits[i]=params.get(objects.get(i));
//		}
//		Predicate gen = new Predicate(type,negate, lits);
//		gen.generalised=true;
//		System.out.println(gen);
//		return gen;
//	}

	
	
	public ArrayList<Predicate> generalise(HashMap<Litereal, Litereal> params) {
		ArrayList<Predicate> result= new ArrayList<>();
		
		ArrayList<Litereal[]> lits =generalise(params, new Litereal[objects.size()], 0);
		
		for (Litereal[] litereals : lits) {
			Predicate gen = new Predicate(type, negate, litereals);
			gen.generalised=true;
			result.add(gen);
//			System.out.println(gen);
		}	

		return result;
	}
	private ArrayList<Litereal[]> generalise(HashMap<Litereal, Litereal> params, Litereal[] lits, int index) {

		ArrayList<Litereal[]> result= new ArrayList<>();
		if(index>=objects.size()){
			result.add(lits);
			return result;
		}
		
		
		
		if(PddlProblem.consObjects.contains(objects.get(index))){
			Litereal[] tmpLits= Arrays.copyOf(lits,objects.size());
			tmpLits[index]=objects.get(index);

			result.addAll(generalise(params, tmpLits, index+1));
		}
		if(params.containsKey(objects.get(index))){
			lits[index]=params.get(objects.get(index));
			result.addAll(generalise(params, lits, index+1));
		}
		return result;
	}

	public ArrayList<Predicate> generalise2(HashMap<litType, ArrayList<Litereal>> tmp) {
		// TODO Auto-generated method stub
		return null;
	}


}



