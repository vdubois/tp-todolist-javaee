package fr.iut.bordeaux.todolist.service;


import fr.iut.bordeaux.todolist.entity.Item;

public interface ItemsService {

    Long addItem(Item item);

    void updateItem(Item item);

    void deleteItem(Long itemId);

    Iterable<Item> findAllItems();

    Item findItemById(Long itemId);
}
