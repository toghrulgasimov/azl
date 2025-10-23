package org.xtext.example.mydsl.types;

import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.typesystem.computation.ITypeComputationState;
import org.eclipse.xtext.xbase.typesystem.computation.XbaseTypeComputer;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XExpression;

import com.google.inject.Inject;

public class MyDslTypeComputer extends XbaseTypeComputer {

    @Override
    public void computeTypes(XExpression expression, ITypeComputationState state) {
    	
    	System.out.println("Custom Type Computer Invoked for expression: ");
        super.computeTypes(expression, state);
        // you can add special cases here
    }
}