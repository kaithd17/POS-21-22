package at.kaindorf.console;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataImport {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Kunden_Datenbank");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
