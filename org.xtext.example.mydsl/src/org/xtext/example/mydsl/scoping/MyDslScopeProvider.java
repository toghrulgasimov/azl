package org.xtext.example.mydsl.scoping;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.SimpleScope;
import org.eclipse.xtext.xbase.scoping.batch.XbaseBatchScopeProvider;

public class MyDslScopeProvider extends XbaseBatchScopeProvider {

	
	@Override
	public IScope getScope(EObject context, EReference reference) {
	    IScope parent = super.getScope(context, reference);
	    if (reference == TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE) {
	        IEObjectDescription desc = EObjectDescription.create("Text",
	            parent.getSingleElement(QualifiedName.create("java","lang","String")).getEObjectOrProxy());
	        IEObjectDescription descEded = EObjectDescription.create("Eded",
		            parent.getSingleElement(QualifiedName.create("java","lang","Integer")).getEObjectOrProxy());
	        
	        IEObjectDescription descHeqiqiEded = EObjectDescription.create("Heqiqi",
		            parent.getSingleElement(QualifiedName.create("java","lang","Double")).getEObjectOrProxy());
	        return new SimpleScope(parent, java.util.Arrays.asList(desc, descEded, descHeqiqiEded));
	    }
	    return parent;
	}
}
