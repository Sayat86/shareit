package com.example.sayat_shareit.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByOwnerId(int ownerId);

    @Query("select i from Item i where i.available " +
            "and (upper(i.name) like upper(concat('%', :text, '%')) " +
            "or upper(i.description) like upper(concat('%', :text, '%'))) ")
    List<Item> search(String text);
}
