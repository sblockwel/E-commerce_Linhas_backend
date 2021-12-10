package com.lb.ecommerce.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
//@Table(name = "clients")
public class People implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    @Pattern(regexp="^[a-zA-Z ]*$")
    private String nome;

    @NotNull
    @Pattern(regexp="^[0-9.-]*$")
    @Size(min = 14,max = 14)
    private String document; //pode ser CPF ou CNPJ (para empresas)

    @NotNull
    private String email;

    @NotNull
    private String usernome;

    @NotNull
    private String password;

    @NotNull
    @Pattern(regexp="^[0-9-]*$")
    @Size(min = 9, max = 9)
    private String ZipCode;

    @NotNull
    @Min(0)
    private int number;

    private char type; // C para cliente e A para o adminstrador

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsernome() {
        return usernome;
    }

    public void setUsernome(String usernome) {
        this.usernome = usernome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

}
