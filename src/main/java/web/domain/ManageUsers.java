package web.domain;

import java.util.List;

public class ManageUsers
{
    private List<Integer> freelancers;
    private List<Integer> employers;
    private List<Integer> matches;

    public ManageUsers() {}

    public List<Integer> getEmployers()
    {
        return employers;
    }

    public void setEmployers(List<Integer> employers) {
        this.employers = employers;
    }

    public List<Integer> getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(List<Integer> freelancers) {
        this.freelancers = freelancers;
    }

    public List<Integer> getMatches() {
        return matches;
    }

    public void setMatches(List<Integer> matches) {
        this.matches = matches;
    }
}