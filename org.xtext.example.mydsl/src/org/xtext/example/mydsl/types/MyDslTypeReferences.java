package org.xtext.example.mydsl.types;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.util.TypeReferences;

public class MyDslTypeReferences extends TypeReferences {

//    @Override
//    public JvmType findDeclaredType(String typeName, Notifier context) {
////        System.out.println("MyDslTypeReferences.findDeclaredType: " + typeName);
//        if ("Text".equals(typeName)) {
//            System.out.println("Intercepting Text -> String in findDeclaredType");
//            return super.findDeclaredType(String.class, context);
//        }
//        return super.findDeclaredType(typeName, context);
//    }
//    
//    @Override
//    public JvmTypeReference getTypeForName(Class<?> clazz, Notifier context, JvmTypeReference... params) {
//        System.out.println("MyDslTypeReferences.getTypeForName(Class): " + clazz.getName());
//        return super.getTypeForName(clazz, context, params);
//    }
//    
//    @Override
//    public JvmTypeReference getTypeForName(String typeName, Notifier context, JvmTypeReference... typeArguments) {
//        System.out.println("MyDslTypeReferences.getTypeForName(String): " + typeName);
//        if ("Text".equals(typeName)) {
//            System.out.println("Intercepting Text -> String in getTypeForName(String)");
//            return getTypeForName(String.class, context, typeArguments);
//        }
//        return super.getTypeForName(typeName, context, typeArguments);
//    }
}