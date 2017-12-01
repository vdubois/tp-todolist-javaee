package fr.iut.bordeaux.todolist.controller;

import fr.iut.bordeaux.todolist.entity.Item;
import fr.iut.bordeaux.todolist.service.ItemsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsRestController {

    private ItemsService itemsService;

    public ItemsRestController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public Iterable<Item> getItems() {
        return itemsService.findAllItems();
    }

    @PostMapping("/todos")
    public ResponseEntity createItem(@RequestBody Item item) {
        Long itemId = itemsService.addItem(item);
        item.setId(itemId);
        return new ResponseEntity(item, HttpStatus.CREATED);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity updateItem(@PathVariable Long id, @RequestBody Item item) {
        item.setId(id);
        itemsService.updateItem(item);
        return new ResponseEntity("", HttpStatus.OK);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id) {
        itemsService.deleteItem(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
