package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){ //아이템은 jpa에 저장하기 전까지 id값이없음
                                  // 아이템값이 값다는건 새로생성한 객체라는것
            em.persist(item);     // 따라서 persist로 신규 등록
        } else {                  //item값이 있다는건 db에 이미등록한걸 가저온것
            em.merge(item);       //이 경우에는 merge로 update(비슷)
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
