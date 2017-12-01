package fr.iut.bordeaux.todolist.service;

import fr.iut.bordeaux.todolist.entity.Item;
import fr.iut.bordeaux.todolist.repository.ItemsRepository;
import fr.iut.bordeaux.todolist.service.exception.ItemDoesNotExistException;
import fr.iut.bordeaux.todolist.service.exception.ItemTitleIsMandatoryException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {

    private ItemsRepository itemsRepository;

    public ItemsServiceImpl(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public Long addItem(Item item) {
        if (StringUtils.isEmpty(item.getTitle())) {
            throw new ItemTitleIsMandatoryException();
        }
        return itemsRepository.save(item).getId();
    }

    @Override
    public void updateItem(Item item) {
        itemsRepository.update(item.getTitle(), item.isCompleted(), item.getId());
    }

    @Override
    public void deleteItem(Long itemId) {
        Item item = findItemById(itemId);
        if (item == null) {
            throw new ItemDoesNotExistException();
        }
        itemsRepository.delete(item);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Item> findAllItems() {
        return itemsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Item findItemById(Long itemId) {
        return itemsRepository.findById(itemId);
    }
}
