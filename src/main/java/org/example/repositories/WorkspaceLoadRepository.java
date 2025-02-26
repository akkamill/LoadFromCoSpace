package org.example.repositories;

import java.math.BigDecimal;
import java.util.List;

public interface WorkspaceLoadRepository {

    void addWorkspace(String type, BigDecimal price) throws Exception;

    List<?> getAllWorkspaces() throws Exception;

    void saveWorkspaces() throws Exception;
}
