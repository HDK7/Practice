package Study.datajpa.repository;

import Study.datajpa.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired ItemRepository itemRepository;

    @Test
    public void save(){
        Item item = new Item("A");
        itemRepository.save(item);
        //key인 id는 persist시 생성 id가 null이면 새로운 객체인것
    }

}