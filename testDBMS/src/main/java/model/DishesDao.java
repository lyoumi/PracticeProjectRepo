package model;

import java.util.List;

/**
 * Created by lyoumi on 13.02.2017.
 */
public interface DishesDao {

    void save(Dishes dish);

    Dishes getDishById(Long id);

    List finalAll();

    void remove (Dishes dish);

    void update (Dishes dish);

}
