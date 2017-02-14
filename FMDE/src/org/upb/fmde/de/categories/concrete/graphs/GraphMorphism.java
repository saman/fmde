package org.upb.fmde.de.categories.concrete.graphs;

import static org.upb.fmde.de.categories.concrete.finsets.FinSets.FinSets;

import org.upb.fmde.de.categories.Category;
import org.upb.fmde.de.categories.ComparableArrow;
import org.upb.fmde.de.categories.LabelledArrow;
import org.upb.fmde.de.categories.concrete.finsets.TotalFunction;
import org.upb.fmde.de.categories.concrete.pfinsets.PartialFunction;
import org.upb.fmde.de.categories.concrete.pgraphs.PGraphMorphism;

public class GraphMorphism extends PGraphMorphism{
	
	public GraphMorphism(String label, Graph srcGraph, Graph trgGraph, TotalFunction f_E, TotalFunction f_V) {
		super(label, srcGraph, trgGraph, f_E, f_V);
		ensureValidity();
	}

	private void ensureValidity(){
		String message = "GraphMorphism " + label + ": " + source.label() + " -> " + target.label() + " is not valid: ";
		Category.ensure(super.f_E.src().equals(source.edges()), message + f_E.src().label() + " does not match " + source.edges().label());
		Category.ensure(f_E.trg().equals(target.edges()) , message + f_E.trg().label() + " does not match " + target.edges().label());
		Category.ensure(f_V.src().equals(source.vertices()) , message + f_V.src().label() + " does not match " + source.vertices().label());
		Category.ensure(f_V.trg().equals(target.vertices()) , message + f_V.trg().label() + " does not match " + target.vertices().label());
		Category.ensure(isStructurePreserving() , message + "It is not structure preserving!");		
	}

	private boolean isStructurePreserving() {
		return FinSets.compose(f_E, target.src()).
				isTheSameAs(FinSets.compose(source.src(), f_V)) &&
			   FinSets.compose(f_E, target.trg()).
				isTheSameAs(FinSets.compose(source.trg(), f_V));
	}

	public boolean isTheSameAs(GraphMorphism a) {
		return a._E().isTheSameAs(f_E) &&
			   a._V().isTheSameAs(f_V);	
	}
	
	public TotalFunction _E() {
		return (TotalFunction)f_E;
	}

	public TotalFunction _V() {
		return (TotalFunction)f_V;
	}

}
