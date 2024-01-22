package ilhan.ensar.ReadingIsGood.service;

import ilhan.ensar.ReadingIsGood.controller.request.OrderItemPostRequest;
import ilhan.ensar.ReadingIsGood.model.Book;
import ilhan.ensar.ReadingIsGood.model.Order;
import ilhan.ensar.ReadingIsGood.model.OrderItem;
import ilhan.ensar.ReadingIsGood.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository repository;
    private final BookService bookService;

    @Transactional
    public void saveAll(Order order, List<OrderItemPostRequest> requestList) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemPostRequest request : requestList) {
            // Update: Stock
            Book book = bookService.decreaseStock(request.getBookId(), request.getAmount());

            // Prepare
            OrderItem orderItem = new OrderItem(order, book, request);
            orderItems.add(orderItem);
        }

        // Save
        repository.saveAll(orderItems);
    }
}
