package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.NotEnoughStockException;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    
    @PersistenceContext
    EntityManager em;
    
    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    
    @Test
    void 상품주문(){
        // given
        Member member = createMember();
        Book book = createBook();
        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order order = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, order.getStatus(), "상태는 ORDER");
        assertEquals(8, book.getStockQuantity());
    }

    @Test
    void 상품주문_재고수량초과(){
        // given
        Member member = createMember();
        Book book = createBook();

        // when

        // then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), book.getId(), 11);
        });
        
    }

    @Test
    void 주문취소(){
        // given
        Member member = createMember();
        Book book = createBook();

        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        // when
        orderService.cancelOrder(orderId);

        // then
        assertEquals(10, book.getStockQuantity());

    }

    private Book createBook() {
        Book book = new Book();
        book.setName("책");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("강남", "강가", "123"));
        em.persist(member);
        return member;
    }
}