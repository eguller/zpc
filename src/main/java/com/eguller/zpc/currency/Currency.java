package com.eguller.zpc.currency;

import javax.persistence.*;

/**
 * (comment)
 *
 * @author eguller
 */
@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String symbol;

    @Column
    private String name;

    protected Currency(){}
    public Currency(String symbol, String name){
        this.symbol = symbol;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        return symbol.equals(currency.symbol);

    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }
}
