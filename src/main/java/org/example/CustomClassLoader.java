package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CustomClassLoader extends ClassLoader {

    private String path;
    private final String resourcePath;


    public CustomClassLoader(String path, String resourcePath) {
        this.path = path;
        this.resourcePath = resourcePath;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            byte[] classData = loadClassData(className);
            return defineClass(className, classData, 0, classData.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Class not found: " + className, e);
        }
    }

    private byte[] loadClassData(String className) throws IOException {
        String filePath = path + File.separator + className.replace('.', File.separatorChar) + ".class";
        FileInputStream inputStream = new FileInputStream(filePath);
        byte[] data = inputStream.readAllBytes();
        inputStream.close();
        return data;
    }

    public File getRCustomResource(String resourceName) {
        Path resourcePathObj = Paths.get(resourcePath + "\\" + resourceName);
        File resourceFile = resourcePathObj.toFile();
        return resourceFile.exists() ? resourceFile : null;
    }
}