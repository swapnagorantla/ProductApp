package com.hackerrank.eshopping.product.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("category")
    private String category;
    @JsonProperty("retail_price")
    private Double retail_price;

    public Response(Long id, String name, String category, Double retail_price, Double discounted_price, Boolean availability) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.retail_price = retail_price;
        this.discounted_price = discounted_price;
        this.availability = availability;
    }
    @JsonProperty("discounted_price")
    private Double discounted_price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRetail_price() {
        return retail_price;
    }

    public void setRetail_price(Double retail_price) {
        this.retail_price = retail_price;
    }

    public Double getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(Double discounted_price) {
        this.discounted_price = discounted_price;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @JsonProperty("availability")
    private Boolean availability;
}
