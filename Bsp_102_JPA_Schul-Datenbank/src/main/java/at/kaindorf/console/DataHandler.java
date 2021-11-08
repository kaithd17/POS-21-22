package at.kaindorf.console;

import at.kaindorf.beans.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.xml.bind.JAXB;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void open(){
        emf = Persistence.createEntityManagerFactory("PU_schooldb");
        em = emf.createEntityManager();
    }

    public static void close(){
        em.close();
        emf.close();
    }

    public static void importTables(){
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "schooldata.xml");
        School school = JAXB.unmarshal(path.toString(), School.class);
        List<Teacher> teachers = school.getClassTeachers();
        List<ClassTeacher> classTeachers = new ArrayList<>();

        teachers.forEach(teacher -> {
            Room room;
            if (teacher.getRoom().charAt(0) == '1'){
                room = new Room(
                    teacher.getRoom(),
                    Floor.GROUND
                );
            }else{
                room = new Room(
                        teacher.getRoom(),
                        Floor.FIRST
                );
            }

            Classname classname = new Classname(
              teacher.getClassName(),
                    Integer.parseInt(teacher.getClassName().charAt(0)+""),
                    teacher.getSize()
            );

            room.setClassname(classname);
            classname.setRoom(room);

            ClassTeacher classTeacher = new ClassTeacher(
                    teacher.getInitials(),
                    teacher.getFirstname(),
                    teacher.getLastname(),
                    teacher.getTitle()
            );
            classname.setClassTeacher(classTeacher);
            classTeacher.setClassname(classname);
            classTeachers.add(classTeacher);
        });

        em.getTransaction().begin();
        classTeachers.forEach(classTeacher -> {
            em.persist(classTeacher);
        });
        em.getTransaction().commit();
    }

    public static void queries() {
        TypedQuery<Room> typedQuery = em.createNamedQuery("Room.findByName", Room.class);
        typedQuery.setParameter("name","2.7.4");
        List<Room> roomList = typedQuery.getResultList();

        TypedQuery<Room> typedQuery1 = em.createNamedQuery("Room.findAll", Room.class);
        List<Room> roomList1 = typedQuery1.getResultList();


    }

    public static void main(String[] args) {
        open();
        importTables();
        close();
    }
}
