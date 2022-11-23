package org.example.Client.Models;

import lombok.Getter;
import lombok.Setter;

public class Account {

    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private  String name;

    @Getter @Setter
    private Integer amount;

    @Getter @Setter
    private  Integer user_id;

    public Account(Integer id, String name, Integer amount, Integer user_id) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", user_id=" + user_id +
                '}';
    }
}
