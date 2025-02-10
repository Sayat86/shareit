package com.example.sayat_shareit.item_request;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRequestRepository extends JpaRepository<ItemRequest, Integer> {
    List<ItemRequest> findByRequestorId(int requestorId);
}
