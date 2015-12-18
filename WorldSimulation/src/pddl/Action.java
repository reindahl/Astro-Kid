package pddl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import pddl.Hypothesis.PlanType;
import pddl.Litereal.litType;
import pddl.Predicate.Ptype;

public class Action {

	public String name;
	Hypothesis hypo;
	ArrayList<litType> param;
	HashMap<Integer, Litereal> Hpram= new HashMap<>(); 

	HashSet<Predicate> notPreconditions = new HashSet<>();
	HashSet<Predicate> preconditions = new HashSet<>();

	//at least one precondition is in this set (failures)
	public ArrayList<HashSet<Predicate>> candidatesPressent = new ArrayList<>();
	public ArrayList<HashSet<Predicate>> candidatesNotPressent = new ArrayList<>();


	//all preconditions is in this set (success) (if all fulfilled guaranteed success)
	HashSet<Predicate> possiblePreconditions;


	public HashSet<Predicate> effects = new HashSet<>();
	HashSet<Predicate> notEffects = new HashSet<>();
	HashSet<Predicate> possibleEffects = new HashSet<>();

	
	HashSet<Predicate> newPredicats = new HashSet<>();
	
	
	
	
	String effect;
	HashMap<String, Action> subactions= new HashMap<>();
	
	public Action(String name, litType[] types, Hypothesis hypo){
		this.hypo=hypo;
		//		System.out.println("----------");
		this.name=name;
		param = new ArrayList<>(Arrays.asList(types));
		for (int i = 0; i < types.length; i++) {
			Hpram.put(i, new Litereal("p"+i, types[i]));
		}
		//		System.out.println(Hpram);


		ArrayList<Predicate> possiblePrediacates = hypo.possiblePrediacates(Hpram.values());
		possiblePrediacates.forEach(e -> e.generalised=true);
		//		System.out.println(possiblePrediacates );
		//		System.out.println(possiblePrediacates.get(0).generalised);
		possiblePrediacates.removeIf(pre -> effects.contains(pre)|| effects.contains(new Predicate(pre,true)) || notEffects.contains(pre));
		//		possiblePrediacates.removeIf(pre -> !initial.isPresent(pre));
		//		System.out.println(name);
		//		System.out.println(possiblePrediacates.size());
		possiblePrediacates.removeIf(e->e.type!=Ptype.at);

		possibleEffects.addAll(possiblePrediacates);

		//		System.out.println(possiblePrediacates.size());
		//		System.out.println(possibleEffects);
	}


	public void addKnowledge(ArrayList<Litereal> params, PddlProblem before) {
		ArrayList<Predicate> candidatePreconditions;


		//all possible predicates
		candidatePreconditions= hypo.possiblePrediacates(params);
		//		System.out.println(candidatePreconditions);

		//		//filter down to what is seen so far in the domain
		//		candidatePreconditions=hypo.FilterpossiblePrediacates(candidatePreconditions);

		//sort into whats in the state and whats not
		ArrayList<Predicate> present = new ArrayList<>();
		ArrayList<Predicate> notPresent = new ArrayList<>();

		for (Predicate predicate : candidatePreconditions) {
			if(before.isPresent(predicate)){
				present.add(predicate);
			}else{
				notPresent.add(predicate);
			}
		}


		//generalise
		HashMap<Litereal, Litereal> hParam = new HashMap<>();
		for (int i = 0; i < params.size(); i++) {
			hParam.put(params.get(i), this.Hpram.get(i));
		}
		present=generalise(hParam, present);
		notPresent=generalise(hParam, notPresent);
		HashSet<Predicate> hPresent =new HashSet<>(present);
		hPresent.removeAll(notPreconditions);
		candidatesPressent.add(hPresent);
		candidatesNotPressent.add(new HashSet<>(notPresent));



	}

	public void addKnowledge(ArrayList<Litereal> params, PddlProblem before, Set<Predicate> effect) {

		//		System.out.println(params);
		//all possible predicates	
		ArrayList<Predicate> possiblePredicates= hypo.possiblePrediacates(params);
		//		System.out.println(possiblePredicates);
		//		System.out.println(before.predicates);
		//filter down to what is seen so far in the domain
		//possiblePredicates=hypo.FilterpossiblePrediacates(possiblePredicates);

		
		

		

		//sort into whats in the state and whats not
		ArrayList<Predicate> present = new ArrayList<>();
		ArrayList<Predicate> notPresent = new ArrayList<>();
		ArrayList<Predicate> newPresent = new ArrayList<>();
		
		for (Predicate predicate : possiblePredicates) {
			
			if(before.isPresent(predicate)){
				present.add(predicate);
				
				//check if its a new predicate is pressent
				if(newPredicats.contains(predicate)){
					newPresent.add(predicate);
					newPredicats.remove(predicate);
				}
			}else{
				notPresent.add(predicate);
			}
		}


		//generalise
		HashMap<Litereal, Litereal> hParam = new HashMap<>();
		for (int i = 0; i < params.size(); i++) {
			hParam.put(params.get(i), this.Hpram.get(i));
		}
		present=generalise(hParam, present);
		notPresent=generalise(hParam, notPresent);
		newPresent=generalise(hParam, newPresent);

		if(this.possiblePreconditions==null){
			//			initialise
			this.possiblePreconditions = new HashSet<>();
			this.possiblePreconditions.addAll(present);
			this.possiblePreconditions.addAll(newPresent);
			//			System.out.println(present);
		}else{
			//predicates not pressent cant be preconditions
			notPreconditions.addAll(notPresent);

			//only predicates present in both sets can be preconditions (intersection of the sets)
			this.possiblePreconditions.retainAll(present);
			this.possiblePreconditions.addAll(newPresent);
		}

		//predicates pressent cant be the cause of failure
		for (HashSet<Predicate> candidates : candidatesPressent) {
			candidates.removeAll(present);
		}
		//predicates not pressent cant be the cause of failure
		for (HashSet<Predicate> candidates : candidatesNotPressent) {
			candidates.removeAll(notPresent);
		}




		ArrayList<Predicate> effects= generalise(hParam,effect);
		this.effects.addAll(effects);

		//find disproven effects
		ArrayList<Predicate> possiblePrediacates = hypo.possiblePrediacates(params);
		possiblePrediacates.removeIf(pre -> before.isPresent(pre) && !effect.contains(pre) && !effect.contains(new Predicate(pre,true)));

		notEffects.addAll(generalise(hParam, possiblePrediacates));
		possibleEffects.removeIf(pre -> effects.contains(pre)|| effects.contains(new Predicate(pre,true)) || notEffects.contains(pre));

	}

	//new grounded Predicate found 
	public void addPredicate(Predicate predicate) {
		
		newPredicats.add(predicate);
		
		
		//generate possible predicates

		//generelise


		//add the new negated predicate to possible preconditions

	}
	public void cleanUpCandidatesPressent(){


		for (int i = 0; i < candidatesPressent.size(); i++) {
			for (int j = 0; j < candidatesPressent.size(); j++) {
				if(i!=j && isSubset(candidatesPressent.get(i), candidatesPressent.get(j))){
					candidatesPressent.get(i).removeAll(candidatesPressent.get(j));
				}
			}
		}
		candidatesPressent.removeIf(set->set.isEmpty());

	}


	private ArrayList<Predicate> generalise(HashMap<Litereal, Litereal> params, Collection<Predicate> array) {
		ArrayList<Predicate> result=new ArrayList<>();
		for (Predicate predicate : array) {
			//			result.add(predicate.generalise(params));
			result.addAll(predicate.generalise(params));
		}
		return result;
	}

	public Boolean isSubset(HashSet<Predicate> set1, HashSet<Predicate> set2){
		for (Predicate predicate : set2) {
			if (!set1.contains(predicate)) {
				return false;
			}
		}

		return true;
	}
	public ArrayList<String> toPDDL(){
		return toPDDL(0);
	}
	/**
	 * the chance of increased relaxation increases with relaxDegree
	 * chance of change relaxDegree/n
	 * max chance 25%
	 * @param relaxDegree
	 * @return
	 */
	public ArrayList<String> toPDDL(int relaxDegree){
		ArrayList<String> action= new ArrayList<>();
		if (hypo.plantype==PlanType.pessimistic && possiblePreconditions == null) {
			System.out.println(name+" possiblePreconditions " +possiblePreconditions);
			return action;
		}
		if(PlanType.optimistic == hypo.plantype && relaxDegree != 0){
			System.out.println("optimistic cant be relaxed");
			return null;
		}

		action.add("	(:action "+name);
		action.add("		:parameters  	(");
		for (int i = 0; i < param.size(); i++) {
			action.add("			?p"+i+" - "+param.get(i));
		}
		action.add("		)");

		action.add("		:precondition 	(and");
		for (Predicate predicate : preconditions) {
			action.add("			"+predicate.toString());
		}

		switch (hypo.plantype) {
		case optimistic:
			action.addAll(optimistic());
			break;
		case pessimistic:
			action.addAll(pessimistic(relaxDegree));
			break;
		default:
			break;
		}

		action.add("			(increase (total-cost) 1)");
		action.add("		)");

		action.add("	)");
		return action;
	}
	public ArrayList<String> pessimistic(int relaxDegree){
		ArrayList<String> action= new ArrayList<>();
		int probChance;
		if(relaxDegree!=0){
			System.out.println("relaxing");

			if(relaxDegree/(double)possiblePreconditions.size()>0.25){
				probChance=4;

			}else{
				probChance=possiblePreconditions.size()-relaxDegree;
			}
			System.out.println(probChance);
			for (Predicate predicate : possiblePreconditions) {

				int chance =ThreadLocalRandom.current().nextInt(0, probChance );
				if(chance!=0){
					action.add("			"+predicate.toString());
				}else{
					System.out.println("predicate relaxed "+ predicate);
				}

			}
			
			
			for (HashSet<Predicate> can : candidatesPressent) {
				action.add("			(not");
				action.add("				(and");
				for (Predicate predicate : can) {
					action.add("					"+predicate.toString());
				}
				action.add("				)");
				action.add("			)");
			}
		}else{
			for (Predicate predicate : possiblePreconditions) {
				action.add("			"+predicate.toString());
			}
		}



		action.add("		)");
		action.add("		:effect 	(and");

		for (Predicate predicate : effects) {
			action.add("			"+predicate.toString());
		}

		if(relaxDegree!=0){
//			if(relaxDegree/(double)possibleEffects.size()>0.25){
//				probChance=4;
//			}else{
//				probChance=possibleEffects.size()-relaxDegree;
//			}
			for (Predicate predicate : possibleEffects) {
//
//				int chance =ThreadLocalRandom.current().nextInt(0, probChance );
//				if(chance!=0){
					action.add("			"+predicate.toString());
//					System.out.println("effect add: "+predicate);
//				}
			}
		}


		return action;
	}
	public ArrayList<String> optimistic(){
		ArrayList<String> action= new ArrayList<>();
		action.add("		:precondition 	(and");
		for (Predicate predicate : preconditions) {
			action.add("			"+predicate.toString());
		}
		//			cleanUpCandidatesPressent();cleanUpCandidatesPressent();
		for (HashSet<Predicate> can : candidatesPressent) {
			action.add("			(not");
			action.add("				(and");
			for (Predicate predicate : can) {
				action.add("					"+predicate.toString());
			}
			action.add("				)");
			action.add("			)");
		}



		action.add("		)");
		action.add("		:effect 	(and");

		for (Predicate predicate : effects) {
			action.add("			"+predicate.toString());
		}

		for (Predicate predicate : possibleEffects) {
			action.add("			"+predicate.toString());
		}


		return action;
	}


}
