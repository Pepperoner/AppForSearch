package com.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQuery(name = "RegistrationNumber.getAll",query = "select c from RegistrationNumber c")
@Table(name = "registration_number")
public class RegistrationNumber implements Serializable {

    @Id
    @Column(name = "id_registr", nullable = false)
    private long id;
    @Column(name = "name_registr")
    private String name;

 //   @OneToOne
 //   //@PrimaryKeyJoinColumn
 //   //@MapsId
 //   @JoinTable(name = "registr_country",
 //           joinColumns = @JoinColumn(name = "registr_id"),
 //           inverseJoinColumns = @JoinColumn(name = "country_id"))
 //   private Country country;

    public RegistrationNumber() {
    }


    public RegistrationNumber(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationNumber)) return false;
        RegistrationNumber that = (RegistrationNumber) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "RegistrationNumber{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
