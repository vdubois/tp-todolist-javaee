package fr.iut.bordeaux.todolist.repository;

import fr.iut.bordeaux.todolist.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long> {

    @Modifying
    @Query("update Item i set i.title = :title, i.completed = :completed where i.id = :itemId")
    void update(@Param("title") String title, @Param("completed") boolean completed, @Param("itemId") Long itemId);

    Item findById(Long itemId);
}
