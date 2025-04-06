package com.example.sayat_shareit.item_request;

import java.util.List;

public interface ItemRequestService {
    ItemRequest create(ItemRequest itemRequest, int requestorId);

    ItemRequest findById(int itemRequestId);

    List<ItemRequest> findAllByRequestorId(int requestorId, int page, int size);

    List<ItemRequest> findAllByRequestorIdNot(int requestorId, int page, int size);
}
