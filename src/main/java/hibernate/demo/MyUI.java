package hibernate.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/** http://localhost:8080/LestStart-1.0-SNAPSHOT/StudentUIServlet
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        GridLayout layout = new GridLayout(4,4);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student tempStudent = new Student("Rosmerry", "Blue", "iDunno@yay.com");

            session.beginTransaction();
            session.save(tempStudent);
            session.getTransaction().commit();
//
//            session.beginTransaction();
//            Student myStudent = session.get(Student.class, tempStudent.getId());
//            session.getTransaction().commit();

            int searchedId = 10;
            session.beginTransaction();
            Student studentToUpdate = session.get(Student.class, searchedId);
            studentToUpdate.setFirstName("Scooby");
            session.getTransaction().commit();

            List<Student> students = session.createQuery("from Student", Student.class).getResultList();

            Student studentSmallestId = students.stream().min(Comparator.comparingInt(Student::getId)).get();

            session.beginTransaction();
            session.delete(studentSmallestId);
            session.getTransaction().commit();

            students.remove(studentSmallestId);

            Grid<Student> gridStudents = new Grid<>(Student.class);
            gridStudents.setItems(students);
            layout.addComponent(gridStudents,0,0,3,2);

            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        setContent(layout);

    }

    @WebServlet(urlPatterns = "/*", name = "StudentUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }



}
