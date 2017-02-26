package web.repository;

import java.util.*;
import web.domain.*;
/**
 * Created by RossChalmers on 26/02/2017.
 */
public interface MatchDAO {

    List<Integer> getSkills(String storedProc, int id);
    List<Worker> getFreelancers();
    List<Worker> getEmployers();
    Match getMatch(Worker worker);
    int insertMatch(int employerID, int freelancerID);
    int getEmployerMatch(int matchID);
    int getFreelancerMatch(int matchID);
}
