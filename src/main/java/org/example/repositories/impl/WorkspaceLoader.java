package org.example.repositories.impl;

import org.example.repositories.WorkspaceLoadRepository;
import org.example.utils.CustomClassLoader;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WorkspaceLoader implements WorkspaceLoadRepository {

    private final Object workspaceService;
    private final Class<?> workspaceClass;
    private final Class<?> workspaceServiceClass;

    public WorkspaceLoader(CustomClassLoader customClassLoader) throws Exception {
        this.workspaceClass = customClassLoader.loadClass("org.example.domains.Workspace");
        this.workspaceServiceClass = customClassLoader.loadClass("org.example.services.WorkspaceService");

        Object workspaceRepo = customClassLoader.loadClass
                ("org.example.interfaces.impl.WorkspaceServiceImpl").getDeclaredConstructor().newInstance();

        this.workspaceService = workspaceServiceClass.getDeclaredConstructor(customClassLoader.loadClass
                ("org.example.interfaces.WorkspaceRepository")).newInstance(workspaceRepo);
    }


    @Override
    public void addWorkspace(String type, BigDecimal price) throws Exception {
        Object workspace = workspaceClass.getDeclaredConstructor().newInstance();

        workspaceClass.getMethod("setType", String.class).invoke(workspace, type);
        workspaceClass.getMethod("setPrice", BigDecimal.class).invoke(workspace, price);

        Method addMethod = workspaceServiceClass.getMethod("addWorkspace", workspaceClass);
        addMethod.invoke(workspaceService, workspace);

        System.out.println("Workspace added successfully!");
    }

    @Override
    public List<?> getAllWorkspaces() throws Exception {
        Method getAllMethod = workspaceServiceClass.getMethod("getAllWorkspaces");
        return (List<?>) getAllMethod.invoke(workspaceService);
    }

    @Override
    public void saveWorkspaces() throws Exception {


        Method saveMethod = workspaceServiceClass.getMethod("saveWorkspaces", List.class);
        saveMethod.invoke(workspaceService, getAllWorkspaces());

        System.out.println("Workspaces saved successfully!");
    }
}
