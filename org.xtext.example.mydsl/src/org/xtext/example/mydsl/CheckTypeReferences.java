package org.xtext.example.mydsl;

import org.eclipse.xtext.common.types.util.TypeReferences;
import org.xtext.example.mydsl.scoping.MyDslScopeProvider;
import org.xtext.example.mydsl.types.MyDslTypeComputer;
import org.xtext.example.mydsl.types.MyDslTypeReferences;

import com.google.inject.Injector;

public class CheckTypeReferences {

    public static void main(String[] args) {
        Injector injector = new MyDslStandaloneSetup().createInjectorAndDoEMFRegistration();

        MyDslTypeComputer refs = injector.getInstance(MyDslTypeComputer.class);

        System.out.println("Injected TypeReferences: " + refs.getClass().getName());
        
        System.out.println(injector.getInstance(MyDslScopeProvider.class).getClass().getName());

        if (refs instanceof MyDslTypeComputer) {
            System.out.println("✅ Custom MyDslTypeReferences is active!");
        } else {
            System.out.println("❌ Default TypeReferences still in use!");
        }
        
//        var stringType = refs.findDeclaredType("Text", null);
//        System.out.println(stringType.getQualifiedName());
    }
}