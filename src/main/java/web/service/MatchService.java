package web.service;

import web.domain.User;
import web.domain.Match;

/**
 * Created by RossChalmers on 26/02/2017.
 */
public interface MatchService {

    Match getEmployerMatch(User user);
    Match getFreelancerMatch(User user);
    Match getExistingEmployerMatch(int matchID);
    Match getExistingFreelancerMatch(int matchID);
}
