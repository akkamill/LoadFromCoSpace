package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
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

            Class<?> workspaceRepositoryClass = customClassLoader.loadClass("org.example.interfaces.WorkspaceRepository");
            Class<?> workspaceServiceImplClass = customClassLoader.loadClass("org.example.interfaces.impl.WorkspaceServiceImpl");
            Class<?> workspaceServiceClass = customClassLoader.loadClass("org.example.services.WorkspaceService");
            Class<?> workspaceClass = customClassLoader.loadClass("org.example.domains.Workspace");

            Object workspaceServiceImpl = workspaceServiceImplClass.getDeclaredConstructor().newInstance();
            Object workspaceService = workspaceServiceClass.getDeclaredConstructor(workspaceRepositoryClass).newInstance(workspaceServiceImpl);
            Object workspace = workspaceClass.getDeclaredConstructor().newInstance();


            // Loading the JSON file
            File workspaceFile = customClassLoader.getRCustomResource("workspaces.json");
            if (workspaceFile != null) {
                System.out.println("File found: " + workspaceFile.getAbsolutePath());
            } else {
                System.out.println("File not found.");
            }

            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize JSON content into List of Workspace objects
            List<?> workspaces = objectMapper.readValue(workspaceFile, List.class);
            System.out.println(workspaces);





//                Object result = workspaceServiceClass.getMethod("getAllWorkspaces").invoke(workspaceService);
            Object result = workspaceServiceImplClass.getMethod("loadWorkspaces").invoke(workspaceServiceImpl);

            System.out.println(result);
            System.out.println("Done");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}