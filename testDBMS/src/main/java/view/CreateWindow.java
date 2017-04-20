package view;

import controller.DishesController;
import controller.EmployeeController;
import model.Dishes;
import model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by lyoumi on 24.12.2016.
 */


public class CreateWindow extends JFrame{

    private int y = 110;
    private Boolean isOpen = false;

    private EmployeeController employeeController;

    private DishesController dishesController;

    private JPanel panelShowEmployee;

    private JPanel panelShowDishes;

    public static void main(String[] args) {
        ApplicationContext applicationContext;
        applicationContext = new ClassPathXmlApplicationContext("application-context.xml", "hibernate-context.xml");
        CreateWindow createWindow = applicationContext.getBean(CreateWindow.class);
        createWindow.start();
    }


    private void start() {

        super.setVisible(true);                                                                                         //устанавливаем параметры окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension dimension = new Dimension(500, 400);
        super.setMinimumSize(dimension);
        super.setSize(500, 400);
        super.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Verdana", Font.PLAIN, 10);
        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(font);
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        JPanel buttons = new JPanel();
        content.add(buttons, BorderLayout.NORTH);

        Button buttonAdd = new Button("A");                                                                       //кнопка добавления нового сотрудника
        Button buttonRemove = new Button("D");                                                                    //кнопка удаления сотрудника
        Button buttonSearch = new Button("F");                                                                    //кнопка поиска сотрудника
        Button buttonUpdate = new Button("U");                                                                    //кнопка обновления данных о сотруднике
        Button buttonAddDishes = new Button("A");


        JTextField fieldId = new JTextField(10);                                                                //поле ввода id для добавления/поиска/обновления данных
        JLabel fid = new JLabel("Номер");
        JTextField fieldIdforRem = new JTextField(10);                                                          //поле ввода id для удаления
        JLabel fidfR = new JLabel("Номер");
        JTextField fieldName = new JTextField(10);                                                              //поле ввода имени для добавления/обновления данных
        JLabel fn = new JLabel("Имя");
        JTextField fieldSurName = new JTextField(10);                                                           //поле ввода фамилии для добавления/обновления данных
        JLabel fsn = new JLabel("Фамилия");
        JTextField fieldPhoneNumber = new JTextField(10);                                                       //поле ввода контактных данных
        JLabel fpn = new JLabel("Контакты");
        String[] positions = {
                "NIGHTWATCHER",
                "KING",
                "PLEB"
        };
        JComboBox fieldPosition = new JComboBox(positions);                                                             //комбобокс с выбором существующей должности
        JLabel fp = new JLabel("Должность");
        JTextField fieldSalary = new JTextField(10);                                                            //поле ввода заработной платы для добавления/обновления данных
        JLabel fs = new JLabel("ЗП");

        JTextField idDish = new JTextField(10);
        JLabel labelIdDish = new JLabel("ID");
        JTextField nameDish = new JTextField(10);
        JLabel labelNameDish = new JLabel("Dish");
        JTextField costDish = new JTextField(10);
        JLabel labelCostDish = new JLabel("Cost");


        buttonRemove.setBounds( 300, 290, 30, 30);
        buttonAdd.setBounds( 250, 290, 30, 30);                                                     //устанавливаем расположение кнопок и полей в окне
        buttonSearch.setBounds(200, 290, 30, 30);
        buttonUpdate.setBounds(150, 290, 30, 30);
        buttonAddDishes.setBounds(250, 290, 30, 30);
        fieldId.setBounds(30, 30, 100, 30);
        fid.setBounds(140, 30, 100, 30);
        fieldName.setBounds(30, 70, 100, 30);
        fn.setBounds(140, 70, 100, 30);
        fieldSurName.setBounds(30, 110, 100, 30);
        fsn.setBounds(140, 110, 100,30);
        fieldPhoneNumber.setBounds(30, 150, 100, 30);
        fpn.setBounds(140, 150, 100,30);
        fieldPosition.setBounds(30, 190, 100, 30);
        fp.setBounds(140, 190, 100,30);
        fieldSalary.setBounds(30, 230, 100, 30);
        fs.setBounds(140, 230, 100,30);
        fieldIdforRem.setBounds(250, 30, 100, 30);

        idDish.setBounds(30, 30, 100, 30);
        labelNameDish.setBounds(140, 30, 100, 30);
        nameDish.setBounds(30,70, 100,30);
        labelNameDish.setBounds(140, 70, 100,30);
        costDish.setBounds(30, 110, 100,30);
        labelCostDish.setBounds(30,150,100,30);

        JPanel employeePanel = new JPanel(false);                                                  //создаем страницу для добавления работника
        JPanel dishesPanel = new JPanel(false);

        employeePanel.setLayout(new GroupLayout(employeePanel));                                            //устанавливаем распоожение на странице

        employeePanel.add(buttonAdd);                                                                             //добавляем все компоненты на страницу
        employeePanel.add(buttonSearch);
        employeePanel.add(buttonUpdate);
        employeePanel.add(buttonRemove);
        employeePanel.add(fieldId);
        employeePanel.add(fid);
        employeePanel.add(fieldName);
        employeePanel.add(fn);
        employeePanel.add(fieldSurName);
        employeePanel.add(fsn);
        employeePanel.add(fieldPhoneNumber);
        employeePanel.add(fpn);
        employeePanel.add(fieldPosition);
        employeePanel.add(fp);
        employeePanel.add(fieldSalary);
        employeePanel.add(fs);
        employeePanel.add(fieldIdforRem);

        dishesPanel.add(idDish);
        dishesPanel.add(labelIdDish);
        dishesPanel.add(nameDish);
        dishesPanel.add(labelNameDish);
        dishesPanel.add(costDish);
        dishesPanel.add(labelCostDish);
        dishesPanel.add(buttonAddDishes);

        JButton addEmployee = new JButton("Employee");                                                             //кнопка, вызывающая страницу для добавления работника
        addEmployee.setFont(font);
        addEmployee.addActionListener(e -> tabbedPane.addTab("Employee" , employeePanel));
        buttons.add(addEmployee);

        JButton deleteEmployee = new JButton("Dishes");                                                           //кнопка, вызывающая страницу для удаления работника
        deleteEmployee.setFont(font);
        deleteEmployee.addActionListener(e -> tabbedPane.addTab("Dish" , dishesPanel));
        buttons.add(deleteEmployee);

        JButton showAllEmployee = new JButton("Работники");
        showAllEmployee.setFont(font);
        showAllEmployee.addActionListener(e -> {

            if (isOpen) tabbedPane.remove(panelShowEmployee);                                                           //если окно уже открыто ранее - мы его удаляем

            panelShowEmployee = new JPanel(false);                                                          //переопределяем
            isOpen = true;                                                                                              //говорим что окно открыто
            y=110;

            for (Employee employee :
                    employeeController.findAll()) {
                JLabel label = new JLabel(employee.getId().toString() + " | " + employee.getName() + " | " + employee.getSurname() + " | " + employee.getPhoneNumber() + " | " + employee.getPosition() + " | " + employee.getSalary());
                label.setBounds(140,y,200,20);
                y+=20;
                panelShowEmployee.add(label);
            }

            tabbedPane.addTab("Работники", panelShowEmployee);
        });
        buttons.add(showAllEmployee);

        JButton showAllDishes = new JButton("Блюда");
        showAllDishes.setFont(font);
        showAllDishes.addActionListener(e -> {
            if (isOpen) tabbedPane.remove(panelShowDishes);
            y=110;
            panelShowDishes = new JPanel(false);
            for (Dishes dishes :
                    dishesController.findAll()) {
                JLabel label = new JLabel("Dishes: [ id: " + dishes.getId() + ", " + dishes.getName() + ", " + dishes.getCost() + "usd]");
                label.setBounds(140,y,200,20);
                y+=20;
                panelShowDishes.add(label);
            }
            tabbedPane.add("Блюда",panelShowDishes);
        });
        buttons.add(showAllDishes);

        JButton remove = new JButton("Закрыть");                                                                   //кнопка, закрывающая выбранную страницу
        remove.setFont(font);
        remove.addActionListener(e -> {
            int select = tabbedPane.getSelectedIndex();
            y = 110;
            if (select >= 0) {
                tabbedPane.removeTabAt(select);
            }
        });
        buttons.add(remove);
        content.add(tabbedPane, BorderLayout.CENTER);

        getContentPane().add(content);

        setPreferredSize(new Dimension(400, 450));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        //обработчик нажатия клавиши добавления работника
        buttonAdd.addActionListener((ActionEvent event) -> employeeController.createEmployee(fieldId, fieldName, fieldSurName, fieldPhoneNumber, fieldPosition, fieldSalary));

        //обработцик нажатия клавиши удаления работника
        buttonRemove.addActionListener((ActionEvent event) -> employeeController.removeEmployee(Long.parseLong(fieldIdforRem.getText())));

        //обработчик нажатия клавиши поиска
        //поиск осуществляется по id
        //после нахождения нужного нам работника мы сетим его данные в поля на странице
        buttonSearch.addActionListener((ActionEvent event) -> {
            Employee employee = employeeController.findEmployee(Long.parseLong(fieldId.getText()));
            fieldName.setText(employee.getName());
            fieldSurName.setText(employee.getSurname());
            fieldPhoneNumber.setText(employee.getPhoneNumber());
            fieldPosition.setSelectedItem(employee.getPosition().toString());
            fieldSalary.setText(employee.getSalary().toString());
        });

        //обработчик нажатия клавиши обновления данных о работнике
        buttonUpdate.addActionListener((ActionEvent event) -> employeeController.updateEmployee(fieldId, fieldName, fieldSurName, fieldPhoneNumber, fieldPosition, fieldSalary));

        buttonAddDishes.addActionListener(e -> dishesController.createDish(idDish, nameDish, costDish));
    }


    public java.util.List<Employee> findAllEmployee(){
        return employeeController.findAll();
    }



    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void setDishesController(DishesController dishesController){
        this.dishesController = dishesController;
    }
}
