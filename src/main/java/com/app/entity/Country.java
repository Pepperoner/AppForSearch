package com.app.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
@NamedQuery(name = "Country.getAll",query = "select c from Country c")
@Table(name = "country_name")
public class Country implements Serializable {

    @Id
    @Column(name = "id_country", nullable = false)
    private long id;
    @Column(name = "name_country")
    private String name;

 //   @OneToOne
 //   //@PrimaryKeyJoinColumn
 //   //@MapsId
 //   @JoinTable(name = "registr_country",
 //           joinColumns = @JoinColumn(name = "country_id"),
 //           inverseJoinColumns = @JoinColumn(name = "registr_id"))
 //   private RegistrationNumber registrationNumber;

    public Country() {
    }

    public Country(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private long getId() {

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
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return getId() == country.getId() &&
                Objects.equals(getName(), country.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
