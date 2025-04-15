package com.example.sayat_shareit.item_request;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface ItemRequestRepository extends JpaRepository<ItemRequest, Integer> {
    Page<ItemRequest> findByRequestorId(int requestorId, Pageable pageable);

    Page<ItemRequest> findAllByRequestorIdNot(int requestorId, Pageable pageable);
}
