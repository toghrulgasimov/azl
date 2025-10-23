package org.xtext.example.mydsl.launch;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.*;
import org.eclipse.debug.core.*;
import org.eclipse.debug.core.model.*;
import org.eclipse.jdt.launching.*;
import org.eclipse.jdt.core.*;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.xtext.example.mydsl.myDsl.Domainmodel;
import org.xtext.example.mydsl.myDsl.Entity;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.xtext.example.mydsl.myDsl.Domainmodel;
import org.xtext.example.mydsl.myDsl.PackageDeclaration;
import org.eclipse.xtext.resource.XtextResource;

public class MyDslLaunchShortcut implements ILaunchShortcut {

    @Override
    public void launch(ISelection selection, String mode) {
        runCurrentFile(mode);
    }

    @Override
    public void launch(IEditorPart editor, String mode) {
        runCurrentFile(mode);
    }

    private void runCurrentFile(String mode) {
        try {
            // Get the open DSL file
            IFile dslFile = org.eclipse.ui.PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow()
                    .getActivePage()
                    .getActiveEditor()
                    .getEditorInput()
                    .getAdapter(IFile.class);

            if (dslFile == null) {
                System.err.println("⚠️ No DSL file selected.");
                return;
            }

            IProject project = dslFile.getProject();
            
            project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, null);
            
            Domainmodel m = loadModel(dslFile);
            
            List<PackageDeclaration> allYigims = MyDslUtils.getAllPackages(m);
            String pname = "";
            for (PackageDeclaration yigim : allYigims) {
                String fullName = MyDslUtils.getFullPackageName(yigim);
                System.out.println("Yigim full name: " + fullName);
                pname = fullName;
                break;
            }
            
            
            

            // Example: main.az -> org.example.Main
            System.out.println(dslFile.getFullPath() + "--full path");
            System.out.println(dslFile.getName() + "----name");
            String aa = dslFile.getFullPath().toString().replaceFirst("\\.az$", "");
            String[] a = aa.split("/");
            
            String mc = a[3];
            for(int i = 4; i < a.length; i++) {
            	mc = mc + "." + a[i];
            }
            
            System.out.println(mc + "-----mc");
            
            if(!pname.equals("")) mc = pname + "." + mc;
            
            System.out.println(mc + " with p name");
            
            String baseName = dslFile.getName().replaceFirst("\\.az$", "");
            //String mainClass = "org.example." + Character.toUpperCase(baseName.charAt(0)) + baseName.substring(1);
            String mainClass = "org.example.Mydsl1";

            // Create or reuse a Java launch configuration
            ILaunchConfigurationType configType = DebugPlugin.getDefault()
                    .getLaunchManager()
                    .getLaunchConfigurationType(IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION);

            ILaunchConfigurationWorkingCopy workingCopy = configType.newInstance(null, "Run " + baseName);
            workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, project.getName());
            workingCopy.setAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME, mc);

            ILaunchConfiguration config = workingCopy.doSave();
            config.launch(mode, new NullProgressMonitor());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Domainmodel loadModel(IFile dslFile) throws Exception {
        XtextResourceSet resourceSet = new XtextResourceSet();
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        URI fileUri = URI.createPlatformResourceURI(dslFile.getFullPath().toString(), true);
        Resource resource = resourceSet.getResource(fileUri, true);
        
        if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof Domainmodel) {
            return (Domainmodel) resource.getContents().get(0);
        } else {
            throw new IllegalStateException("Could not load Domainmodel from: " + dslFile.getFullPath());
        }
    }
}
