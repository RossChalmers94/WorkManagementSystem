package domainTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import web.domain.Worker;

import java.util.Arrays;

/**
 * Created by RossChalmers on 05/03/2017.
 */

public class WorkerTest {

    Worker currentWorker;
    Worker worker1;

    @Before
    public void setUp(){
        currentWorker = new Worker();
        worker1 = new Worker();
        currentWorker.setSalary(1); currentWorker.setLocation(1); currentWorker.setJobLength(1); currentWorker.setRating(1);
        currentWorker.setSkill(Arrays.asList(1,2,3,4));
        worker1.setSalary(1); worker1.setLocation(1); worker1.setJobLength(1); worker1.setRating(1);
        worker1.setSkill(Arrays.asList(1,2,3,4));
    }

    @Test
    public void compareTo100Test(){
        int compareValue = Math.round(currentWorker.compareTo(worker1));
        Assert.assertSame(compareValue, 100);
    }

    @Test
    public void compareTo80Test(){
        worker1.setLocation(2);
        int compareValue = Math.round(currentWorker.compareTo(worker1));
        Assert.assertSame(compareValue, 80);
    }

    @Test
    public void compareTo88Test(){
        worker1.setSkill(Arrays.asList(1,2));
        int compareValue = Math.round(currentWorker.compareTo(worker1));
        Assert.assertSame(compareValue, 88);
    }

    @Test
    public void compareTo50Test(){
        worker1.setSkill(Arrays.asList(5,6,7,8));
        int compareValue = Math.round(currentWorker.compareTo(worker1));
        Assert.assertSame(compareValue, 50);
    }

    @Test
    public void compareTo30Test(){
        worker1.setSkill(Arrays.asList(5,6,7,8));
        worker1.setLocation(2);
        int compareValue = Math.round(currentWorker.compareTo(worker1));
        Assert.assertSame(compareValue, 30);
    }



}
