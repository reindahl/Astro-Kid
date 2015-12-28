package pddl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import pddl.Hypothesis.PlanType;
import pddl.Litereal.litType;
import pddl.Predicate.Ptype;

public class Action {

	public String name;
	Hypothesis hypo;
	ArrayList<Litereal> parametersGeneralised= new ArrayList<>(); 

	HashSet<Predicate> notPreconditions = new HashSet<>();
	HashSet<Predicate> preconditions = new HashSet<>();

	//candidates present on failure
	//at least one precondition is in this set (failures)
	public ArrayList<HashSet<Predicate>> candidatesPressent = new ArrayList<>();
	public ArrayList<HashSet<Predicate>> candidatesNotPressent = new ArrayList<>();


	//all preconditions is in this set (success) (if all fulfilled guaranteed success)
	HashSet<Predicate> possiblePreconditions;


	public HashSet<Predicate> effects = new HashSet<>();
	HashSet<Predicate> notEffects = new HashSet<>();
	HashSet<Predicate> possibleEffects = new HashSet<>();

	HashSet<Predicate> disjunction = null;
	HashSet<Predicate> notDisjunction = null;

	HashSet<Predicate> newPredicats = new HashSet<>();
	HashSet<Predicate> newPredicatsDis = new HashSet<>();



	Predicate effect;
	HashMap<Predicate, Action> subactions= new HashMap<>();

	public Action(String name, litType[] types, Hypothesis hypo){
		this.hypo=hypo;
		this.name=name;
		for (int i = 0; i < types.length; i++) {
			parametersGeneralised.add(new Litereal("p"+i, types[i]));
		}
		ArrayList<Predicate> possiblePrediacates = hypo.possiblePrediacates(parametersGeneralised);
		possiblePrediacates.forEach(e -> e.generalised=true);

		possiblePrediacates.removeIf(pre -> effects.contains(pre)|| effects.contains(new Predicate(pre,true)) || notEffects.contains(pre));

		possiblePrediacates.removeIf(e->e.type!=Ptype.at);

		possibleEffects.addAll(possiblePrediacates);

	}

	/**
	 * subaction constructor
	 * @param effect
	 * @param types
	 * @param hypo
	 */
	public Action(Predicate effect, litType[] types, Hypothesis hypo) {
		this.hypo=hypo;
		this.effect=effect;
		for (int i = 0; i < types.length; i++) {
			parametersGeneralised.add(new Litereal("p"+i, types[i]));
		}
		ArrayList<Predicate> possiblePrediacates = hypo.possiblePrediacates(parametersGeneralised);
		possiblePrediacates.forEach(e -> e.generalised=true);

		possiblePrediacates.removeIf(pre -> effects.contains(pre)|| effects.contains(new Predicate(pre,true)) || notEffects.contains(pre));

		possibleEffects.addAll(possiblePrediacates);
	}
	public Action(Predicate effect, ArrayList<Litereal> types, Hypothesis hypo) {
		this.hypo=hypo;
		this.effect=effect;
		parametersGeneralised=types;
		
		ArrayList<Predicate> possiblePrediacates = hypo.possiblePrediacates(parametersGeneralised);
		possiblePrediacates.forEach(e -> e.generalised=true);

		possiblePrediacates.removeIf(pre -> effects.contains(pre)|| effects.contains(new Predicate(pre,true)) || notEffects.contains(pre));


		possibleEffects.addAll(possiblePrediacates);
	}
	/**
	 * action fails
	 * @param parametersGrounded
	 * @param before
	 */
	public void addKnowledge(ArrayList<Litereal> parametersGrounded, PddlProblem before) {
		ArrayList<Predicate> candidatePreconditions;


		//all possible predicates
		candidatePreconditions= hypo.possiblePrediacates(parametersGrounded);
//				System.out.println(candidatePreconditions);

		//		//filter down to what is seen so far in the domain
		//		candidatePreconditions=hypo.FilterpossiblePrediacates(candidatePreconditions);

		//sort into whats in the state and whats not
		ArrayList<Predicate> present = new ArrayList<>();
		ArrayList<Predicate> notPresent = new ArrayList<>();
		ArrayList<Predicate> newPresent = new ArrayList<>();
		
		for (Predicate predicate : candidatePreconditions) {
			if(before.isPresent(predicate)){
				present.add(predicate);
				
				//check if its a new predicate is pressent
				if(newPredicatsDis.contains(predicate)){
					newPresent.add(predicate);
					newPredicatsDis.remove(predicate);
				}
			}else{
				notPresent.add(predicate);
			}
		}


		//generalise
		HashMap<Litereal, Litereal> hParam = new HashMap<>();

		for (int i = 0; i < parametersGrounded.size(); i++) {
			hParam.put(parametersGrounded.get(i), parametersGeneralised.get(i));
		}
		present=generalise(hParam, present);
		notPresent=generalise(hParam, notPresent);
		newPresent=generalise(hParam, newPresent);
		
		HashSet<Predicate> hPresent =new HashSet<>(present);
		hPresent.removeAll(notPreconditions);
		candidatesPressent.add(hPresent);
		candidatesNotPressent.add(new HashSet<>(notPresent));



		//check for contradictions
		if(hPresent.containsAll(possiblePreconditions) && hPresent.containsAll(preconditions)){
			System.out.println("act: Contradiction "+name);
			System.out.println("act: effect missing "+effect);
			if(disjunction==null){
				disjunction= new HashSet<>(notPresent);
				notDisjunction= new HashSet<>(present);
				newPredicatsDis.clear();
			}else{
				for (Predicate predicate : newPresent) {
					Predicate pre = new Predicate(predicate, true);
					if(notPresent.contains(pre)){
						disjunction.add(pre);
					}
				}
				disjunction.retainAll(notPresent);
				notDisjunction.addAll(present);
			}
		}
		for (Action subaction : subactions.values()) {
			subaction.addKnowledge(parametersGrounded, before);
		}
	}

	/**
	 * action succeed
	 * @param parametersGrounded
	 * @param before
	 * @param effect
	 */
	public void addKnowledge(ArrayList<Litereal> parametersGrounded, PddlProblem before, Set<Predicate> effect) {
//		System.out.println("act: "+this.effect+"....................................");
		//		System.out.println(params);
		//all possible predicates
//		System.out.println("hypo: "+parametersGrounded);
		ArrayList<Predicate> possiblePredicates= hypo.possiblePrediacates(parametersGrounded);
//		possiblePredicates.stream().filter(e-> e.type==Ptype.at).forEach(e->System.out.println("poss "+e));
		//System.out.println(possiblePredicates);
		//		System.out.println(before.predicates);
		//filter down to what is seen so far in the domain
//		possiblePredicates=hypo.FilterpossiblePrediacates(possiblePredicates);






		//sort into what's in the state and whats not
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
		for (int i = 0; i < parametersGrounded.size(); i++) {
			hParam.put(parametersGrounded.get(i), this.parametersGeneralised.get(i));
		}
		
		present=generalise(hParam, present);
		notPresent=generalise(hParam, notPresent);
		newPresent=generalise(hParam, newPresent);

		if(this.possiblePreconditions==null){
			//initialise
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
//			present.stream().filter(pre->pre.type==Ptype.at).forEach(e-> System.out.println("act: add: "+e));
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
		ArrayList<Predicate> possiblePrediacates = hypo.possiblePrediacates(parametersGrounded);
		possiblePrediacates.removeIf(pre -> before.isPresent(pre) && !effect.contains(pre) && !effect.contains(new Predicate(pre,true)));

		notEffects.addAll(generalise(hParam, possiblePrediacates));
		possibleEffects.removeIf(pre -> effects.contains(pre)|| effects.contains(new Predicate(pre,true)) || notEffects.contains(pre));


		//add new sub actions
		if(this.effect==null){

			for (Predicate predicate : effects) {
				if(!subactions.containsKey(predicate)){
					System.out.println("Act: new effect "+name+" "+predicate);
					subactions.put(predicate, new Action(predicate, parametersGeneralised, hypo));

				}
			}
		}

		//updates subactions
		for (Entry<Predicate, Action> pair : subactions.entrySet()) {
			if(effects.contains(pair.getKey())){

				pair.getValue().addKnowledge(parametersGrounded, before, effect);
			}else if(!present.contains(pair.getKey()) && !effects.contains(new Predicate(pair.getKey(),true))){
				
				
				pair.getValue().addKnowledge(parametersGrounded, before);
			}
		}

	}

	//new grounded Predicate found 
	public void addPredicate(Predicate predicate) {

		newPredicats.add(predicate);
		newPredicatsDis.add(predicate);
//TODO:
//		HashMap<litType, ArrayList<Litereal>> tmp= new HashMap<>();
//		for (int i = 0; i < parameters.size(); i++) {
//			for (litType childs : parameters.get(i).getTypes()) {
//				ArrayList<Litereal> hmm= tmp.get(childs);
//				if(hmm==null){
//					hmm=new ArrayList<>();
//					tmp.put(childs, hmm);
//				}
//				hmm.add(parameters.get(i));
//			}
//		}
//		
//
//		ArrayList<Predicate> generalised=predicate.generalise2(tmp);
		
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
		for (int i = 0; i < parametersGeneralised.size(); i++) {
			action.add("			?p"+i+" - "+parametersGeneralised.get(i).type);
		}
		action.add("		)");

		action.add("		:precondition 	(and");
		for (Predicate predicate : preconditions) {
				action.add("			"+predicate.toString());
		}
		if(disjunction!=null && !disjunction.isEmpty()){
			action.add("			(or");
			for (Predicate predicate : disjunction) {
				action.add("				"+predicate.toString());
			}
			action.add("			)");
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
			System.out.println("act: relaxing "+name);

			if(relaxDegree/(double)possiblePreconditions.size()>0.25){
				probChance=4;

			}else{
				probChance=possiblePreconditions.size()-relaxDegree;
			}
			System.out.println("act: relax "+(100-(probChance/((double) possiblePreconditions.size()))*100)+"%");
			System.out.println("act: relax probChan "+probChance);
			System.out.println("act: relax possiblePreconditions: "+possiblePreconditions.size());
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
				//FIXME: tmp filter
//				if(predicate.type!=Ptype.relativDir || !predicate.negate)
				action.add("			"+predicate.toString());
			}
		}



		action.add("		)");
		action.add("		:effect 	(and");
		if(subactions.isEmpty()){
			for (Predicate predicate : effects) {
				action.add("			"+predicate.toString());
			}
		}else{
			for (Action subaction : subactions.values()) {
				action.addAll(subaction.getEffect(possiblePreconditions));
			}
		}


		if(relaxDegree!=0){

			for (Predicate predicate : possibleEffects) {
				action.add("			"+predicate.toString());
			}
		}


		return action;
	}
	private Collection<String> getEffect(HashSet<Predicate> possiblePreconditions) {
		
		return getEffect(possiblePreconditions,0);
	}
	private Collection<String> getEffect(HashSet<Predicate> mainPreconditions, int relaxDegree) {
		ArrayList<String> effect= new ArrayList<>();

		HashSet<Predicate> conditions = new HashSet<>(this.possiblePreconditions);
		conditions.removeAll(mainPreconditions);
		if(!conditions.isEmpty() || disjunction!= null){
			effect.add("\t\t\t(when (and");
			if(!conditions.isEmpty() ){
				if(relaxDegree==0){
					for (Predicate predicate : conditions) {
						//FIXME: tmp filter
//						if(predicate.type!=Ptype.relativDir || !predicate.negate)
						effect.add("\t\t\t\t\t"+predicate);
					}
				}else{
					int probChance;
					if(relaxDegree/(double)conditions.size()>0.25){
						probChance=4;

					}else{
						probChance=conditions.size()-relaxDegree;
					}
					for (Predicate predicate : conditions) {
						int chance =ThreadLocalRandom.current().nextInt(0, probChance );
						if(chance!=0){
							
							effect.add("\t\t\t\t\t"+predicate);
						}else{

							System.out.println("predicate relaxed "+ predicate);
						}
						
					}
				}
			}
			if(disjunction!=null){
				effect.add("			(or");
				for (Predicate predicate : disjunction) {
					effect.add("				"+predicate.toString());
				}
				effect.add("			)");
			}
			effect.add("\t\t\t\t)");
			effect.add("\t\t\t\t"+this.effect);
			effect.add("\t\t\t)");
			
			
		}else{
			effect.add("\t\t\t"+this.effect);
		}

		return effect;
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
