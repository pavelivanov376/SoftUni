import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Engine implements Runnable {
    private final EntityManager entityManager;
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void run() {

        // Task 1
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Town t SET t.name = upper(t.name) WHERE length(t.name) >= 5");
        int affectedRows = query.executeUpdate();
        entityManager.getTransaction().commit();

        System.out.println(affectedRows);
        

        //Task 2
        String fName = "Rob";

        Employee employee = entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.firstName = :f_name", Employee.class)
                .setParameter("f_name", fName)
                .getSingleResult();
        
    }
}
