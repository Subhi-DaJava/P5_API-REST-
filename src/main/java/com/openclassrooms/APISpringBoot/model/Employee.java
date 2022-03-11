package com.openclassrooms.APISpringBoot.model;

import lombok.Data;

import javax.persistence.*;

//Data est une annotation Lombok. Nul besoin d’ajouter les getters et les setters
@Data
//Entity est une annotation qui indique que la classe correspond à une table de la base de données.
@Entity
//Table(name=”employees”) indique le nom de la table associée.
@Table(name = "employees")
public class Employee {
    //L’attribut id correspond à la clé primaire de la table, et est donc annoté @Id. D’autre part,
    // comme l’id est auto-incrémenté, j’ai ajouté l’annotation @GeneratedValue(strategy = GenerationType.IDENTITY).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    //le nom du champ est identique, pas de l'annotation
    private String mail;
    //le nom du champ est identique, pas de l'annotation
    private String password;

}
