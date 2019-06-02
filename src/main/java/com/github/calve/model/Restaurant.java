package com.github.calve.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.StringJoiner;

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @JsonIgnore
    protected List<MenuItem> items;

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant() {
    }

    public Restaurant(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Restaurant.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
    public String toStringWithItems() {
        StringJoiner stringJoiner = new StringJoiner(", ", Restaurant.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'");
        for (MenuItem menuItem : getItems()) {
            stringJoiner.add(System.lineSeparator());
            stringJoiner.add(menuItem.toString());
        }

        return stringJoiner.toString();
    }
}
