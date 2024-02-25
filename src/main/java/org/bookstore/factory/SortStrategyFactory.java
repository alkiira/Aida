package org.bookstore.factory;

import org.bookstore.strategy.SortByPriceStrategy;
import org.bookstore.strategy.SortByTitleStrategy;
import org.bookstore.strategy.SortStrategy;

public class SortStrategyFactory {
    public static SortStrategy createSortStrategy(String criteria) {
        if (criteria.equalsIgnoreCase("title")) {
            return new SortByTitleStrategy();
        } else if (criteria.equalsIgnoreCase("price")) {
            return new SortByPriceStrategy();
        } else {
            throw new IllegalArgumentException("Error");
        }
    }
}