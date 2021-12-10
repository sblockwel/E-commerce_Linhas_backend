package com.lb.ecommerce.entity;

import com.lb.ecommerce.models.OrderStatus;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private People people;

    private Date creationDate;

    //botar um numero aleatorio unico
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderNumber;

    //verificar se e 0 a 4
    @NotNull
    //@Size(min = 0, max = 4)
    private OrderStatus status; //pega de um Enumerador

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
