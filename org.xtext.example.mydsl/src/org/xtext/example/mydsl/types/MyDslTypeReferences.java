package org.xtext.example.mydsl.types;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.util.TypeReferences;

public class MyDslTypeReferences extends TypeReferences {

    @Override
    public JvmType findDeclaredType(String typeName, Notifier context) {
        // Intercept before the default logic runs
    	System.out.println("Custom Type References Invoked for type: " + typeName);
        if ("Text".equals(typeName)) {
            // Redirect Text → java.lang.String
            return super.findDeclaredType("java.lang.String", context);
        }
        return super.findDeclaredType(typeName, context);
    }
}
