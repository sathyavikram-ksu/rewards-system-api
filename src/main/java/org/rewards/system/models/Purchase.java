package org.rewards.system.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Purchase extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private ItemType itemType;
    @NotNull
    private PurchaseType purchaseType;
    @NotNull
    private Double amount;
    @NotNull
    private Long points;
    private Long pickUpDate;
    private Integer pickUpSlot;
    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(PurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Long getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Long pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Integer getPickUpSlot() {
        return pickUpSlot;
    }

    public void setPickUpSlot(Integer pickUpSlot) {
        this.pickUpSlot = pickUpSlot;
    }
}
