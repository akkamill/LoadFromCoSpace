package org.example;

import org.example.repositories.WorkspaceLoadRepository;
import org.example.repositories.impl.WorkspaceLoader;
import org.example.utils.CustomClassLoader;

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            CustomClassLoader customClassLoader = new CustomClassLoader(
                    "C:\\Users\\ASUS\\Desktop\\CoSpaceApp\\target\\classes",
                    "C:\\Users\\ASUS\\Desktop\\CoSpaceApp\\target\\classes\\files"
            );

//            Class<?> workspaceRepositoryClass = customClassLoader.loadClass("org.example.interfaces.WorkspaceRepository");
//            Class<?> workspaceServiceImplClass = customClassLoader.loadClass("org.example.interfaces.impl.WorkspaceServiceImpl");
//            Class<?> workspaceServiceClass = customClassLoader.loadClass("org.example.services.WorkspaceService");
//            Class<?> workspaceClass = customClassLoader.loadClass("org.example.domains.Workspace");
//
//            Object workspaceServiceImpl = workspaceServiceImplClass.getDeclaredConstructor().newInstance();
//            Object workspaceService = workspaceServiceClass.getDeclaredConstructor(workspaceRepositoryClass).newInstance(workspaceServiceImpl);
//            Object workspace = workspaceClass.getDeclaredConstructor().newInstance();
//
//
//
////
//            File workspaceFile = (customClassLoader.getCustomResource("workspaces.json"));
//
//            if (workspaceFile.exists()) {
//                Method getAllWorkspacesMethod = workspaceServiceClass.getMethod("getAllWorkspaces");
//                List<?> result = (List<?>) getAllWorkspacesMethod.invoke(workspaceService);
//                System.out.println(result);
//            } else {
//                System.out.println("Workspace file not found.");
//            }
//
//            BigDecimal price = new BigDecimal("3223.23");
//            workspaceClass.getMethod("setPrice", BigDecimal.class).invoke(workspace, price);
//            workspaceClass.getMethod("setType", String.class).invoke(workspace, "Loaded Workspace");
//
//
//            workspaceServiceClass.getMethod("addWorkspace", workspaceClass).invoke(workspaceService, workspace);
//
//
//
//
//            Class<?> listClass = Class.forName("java.util.ArrayList");
//            Object workspaceList = listClass.getDeclaredConstructor().newInstance();
//
//            Method saveWorkspacesMethod = workspaceServiceClass.getMethod("saveWorkspaces", List.class);
//            saveWorkspacesMethod.invoke(workspaceService, workspaceList);
//
//            System.out.println("Workspace added successfully!");

            WorkspaceLoadRepository workspaceService = new WorkspaceLoader(customClassLoader);

            // Add a workspace
            workspaceService.addWorkspace("Loaded Workspace", new BigDecimal("322323.23"));

            // Get all workspaces
            List<?> workspaces = workspaceService.getAllWorkspaces();
            System.out.println("Workspaces: " + workspaces);

            // Save all workspaces
            workspaceService.saveWorkspaces();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}