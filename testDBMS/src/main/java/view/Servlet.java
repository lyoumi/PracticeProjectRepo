//package view;
//
//import controller.EmployeeController;
//import model.Employee;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import javax.annotation.*;
//import javax.jws.WebService;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * Created by lyoumi on 18.02.2017.
// */
//
//
//public class Servlet extends HttpServlet {
//
//    private EmployeeController employeeController;
//
//    public static void main(String[] args) {
//        ApplicationContext applicationContext;
//        applicationContext = new ClassPathXmlApplicationContext("application-context.xml", "hibernate-context.xml");
//        Servlet servlet = applicationContext.getBean(Servlet.class);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        PrintWriter out = response.getWriter();
//
//        out.print("Boooo!");
//
////        for (Employee employee :
////                employeeController.findAll()) {
////            out.print("<tr><td>" + employee.getId() + "</td>");
////            out.print("<td>" + employee.getName() + "</td>");
////            out.print("<td>" + employee.getSurname() + "</td>");
////            out.print("<td>" + employee.getPhoneNumber() + "</td>");
////            out.print("<td>" + employee.getPosition() + "</td>");
////            out.print("<td>" + employee.getSalary() + "</td>");
////        }
//    }
//
//    public void setEmployeeController(EmployeeController employeeController) {
//        this.employeeController = employeeController;
//    }
//}
