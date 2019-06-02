package com.github.calve.to;

import com.github.calve.model.MenuItem;

import java.time.LocalDate;
import java.util.List;

public class MenuTo extends BaseTo {
    private LocalDate localDate;

    private List<MenuItem> items;

    public MenuTo() {
    }

    public MenuTo(LocalDate localDate, List<MenuItem> items) {
        this.localDate = localDate;
        this.items = items;
    }

    public MenuTo(Integer id, LocalDate localDate, List<MenuItem> items) {
        super(id);
        this.localDate = localDate;
        this.items = items;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
}
