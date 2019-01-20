package at.leonschloemmer.carwarehouse.model.people;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {

    //region Properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private Long id;
    private String name;
    //endregion

    //region Constructors
    public Person() {
    }

    public Person(String name) {
        this.setName(name);
    }
    //endregion

    //region Getters and Setters

//    public Long getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}
