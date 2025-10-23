package org.xtext.example.mydsl.launch;

import java.util.ArrayList;
import java.util.List;
import org.xtext.example.mydsl.myDsl.Domainmodel;
import org.xtext.example.mydsl.myDsl.AbstractElement;
import org.xtext.example.mydsl.myDsl.PackageDeclaration;

public class MyDslUtils {

    // Recursively collect all packages (yigims)
    public static void collectPackages(AbstractElement element, List<PackageDeclaration> list) {
        if (element instanceof PackageDeclaration pkg) {
            list.add(pkg);
            for (AbstractElement child : pkg.getElements()) {
                collectPackages(child, list);
            }
        }
    }

    // Build full qualified name for a package (yigim1.yigim2)
    public static String getFullPackageName(PackageDeclaration pkg) {
        if (pkg.eContainer() instanceof Domainmodel) {
            return pkg.getName(); // top-level package
        } else if (pkg.eContainer() instanceof PackageDeclaration parent) {
            return getFullPackageName(parent) + "." + pkg.getName();
        } else {
            return pkg.getName(); // fallback
        }
    }

    // Collect all packages from the domain model
    public static List<PackageDeclaration> getAllPackages(Domainmodel model) {
        List<PackageDeclaration> result = new ArrayList<>();
        for (AbstractElement element : model.getElements()) {
            collectPackages(element, result);
        }
        return result;
    }
}
