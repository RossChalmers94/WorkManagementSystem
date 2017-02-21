package web.repository;

import java.util.*;

/**
 * Created by RossChalmers on 20/02/2017.
 */
public interface WorkerDAO {
    Map<String, Object> insertFreelancer(String storedProc, Map<String, Object> inParameters);
    Map<String, Object> insertEmployer(String storedProc, Map<String, Object> inParameters);
}
