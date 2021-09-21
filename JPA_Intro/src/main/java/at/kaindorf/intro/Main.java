package at.kaindorf.intro;

import at.kaindorf.intro.pojos.Address;
import at.kaindorf.intro.pojos.SchoolClass;
import at.kaindorf.intro.pojos.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_JPA_Intro");
        //Verwaltet die Entities
        EntityManager em = emf.createEntityManager();
        Student student = new Student("5DHIF",1L,"Nico", "Baumann", LocalDate.now());
        //Objekt kommt in den "Persistence context (noch nicht in der Datenbank)
        //em.persist(student);
        Address address = new Address("Kaindorf", "Grazerstr", "220");
        student.setAddress(address);
        address.setStudent(student);

        Student student2 = new Student("5DHIF",2L,"Adrian", "Berner", LocalDate.now().minusDays(1000));
        //em.persist(student2);
        Address address2 = new Address("Graz", "Herrengasse", "10");
        student2.setAddress(address2);
        address2.setStudent(student2);

        SchoolClass sc = new SchoolClass("5DHIF");
        sc.addStudent(student);
        sc.addStudent(student2);
        em.persist(sc);

        //Mit transaction wird das Objekt in die Datenbank gespeichert.
        em.getTransaction().begin();
        em.getTransaction().commit();
        //em.detach(student); --> Hier wird das Objekt aus dem Persistence Context herausgenommen
        //em.remove(student); --> Hier wird das Objekt aus dem Persistence Context gelöscht
        em.close();
        emf.close();
    }
}
