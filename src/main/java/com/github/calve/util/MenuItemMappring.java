package com.github.calve.util;

public class MenuItemMappring {
    private Integer restaurantId;
    private Long count;


    public MenuItemMappring(Integer restaurantId, Long count) {
        this.restaurantId = restaurantId;
        this.count = count;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MenuItemMappring{");
        sb.append("restaurantId=").append(restaurantId);
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
