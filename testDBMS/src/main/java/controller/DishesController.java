package controller;

import model.Dishes;
import model.DishesDao;
import model.EmployeeDao;

import javax.swing.*;
import java.util.List;

/**
 * Created by lyoumi on 13.02.2017.
 */
public class DishesController {

    private DishesDao dishesDao;

    public void createDish(JTextField id, JTextField name, JTextField cost){
        Dishes dish = new Dishes();

        dish.setId(Long.parseLong(id.getText()));
        dish.setName(name.getText());
        dish.setCost(Float.parseFloat(cost.getText()));

        dishesDao.save(dish);
    }

    public void removeDish(Long id){
        dishesDao.remove(dishesDao.getDishById(id));
    }


    public List<Dishes> findAll(){
        return dishesDao.finalAll();
    }

    public Dishes  findEmployee(Long id){
        return dishesDao.getDishById(id);
    }

    public void updateDish (Long id){

        Dishes dish = dishesDao.getDishById(id);

        dish.setId(2L);
        dish.setName("Soap");
        dish.setCost(4F);
    }


    public void setDishesDao (DishesDao dishesDao){
        this.dishesDao = dishesDao;
    }


}
