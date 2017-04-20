package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lyoumi on 13.02.2017.
 */

@Entity
@Table(name = "dishes")
public class Dishes {

    @Id
    @Column (name = "id")
    Long id;

    @Column (name = "name")
    String name;

    @Column (name = "cost")
    Float cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }
}
