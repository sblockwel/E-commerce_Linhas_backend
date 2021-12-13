package com.lb.ecommerce.entity;

import com.lb.ecommerce.models.OrderStatus;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Person person;

    private Date creationDate;

    private Long orderNumber;

    @NotNull
    private OrderStatus status; //pega de um Enumerador

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPeople() {
        return person;
    }

    public void setPeople(Person person) {
        this.person = person;
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

    public void setOrderNumber(Long orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
