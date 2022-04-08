package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemRepositoryTest {

    private ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clear();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);
        // when
        Item savedItem = itemRepository.save(item);
        // then
        Assertions.assertThat(item).isEqualTo(savedItem);
    }

    @Test
    void findById() {
        // given
        Item item = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item);
        // when
        Item findItem = itemRepository.findById(item.getId());
        // then
        Assertions.assertThat(item).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);
        // when
        List<Item> result = itemRepository.findAll();
        // then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item);
        // when
        itemRepository.update(savedItem.getId(), new Item("itemA", 20000, 20));
        // then
        Assertions.assertThat(savedItem.getPrice()).isEqualTo(20000);
        Assertions.assertThat(savedItem.getQuantity()).isEqualTo(20);
    }
}
