package com.pilot.inventory.service;

import com.pilot.inventory.dto.OrdersDto;
import com.pilot.inventory.dto.OrdersRequestDto;
import com.pilot.inventory.exception.InsufficientQuantityException;
import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.mapper.UsersDtoMapper;
import com.pilot.inventory.mapper.ProductDtoMapper;
import com.pilot.inventory.mapper.OrdersRequestDtoMapper;
import com.pilot.inventory.mapper.ProductOrderDtoMapper;
import com.pilot.inventory.mapper.UsersRequestDtoMapper;
import com.pilot.inventory.model.Orders;
import com.pilot.inventory.model.Product;
import com.pilot.inventory.repository.OrdersRepository;
import com.pilot.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService{
    private final UsersDtoMapper usersDtoMapper;
    private final ProductDtoMapper productDtoMapper;
    private final OrdersRequestDtoMapper ordersRequestDtoMapper;
    private final ProductOrderDtoMapper productOrderDtoMapper;
    private final UsersRequestDtoMapper usersRequestDtoMapper;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrdersRepository ordersRepository;


    public OrdersServiceImpl(UsersDtoMapper usersDtoMapper, ProductDtoMapper productDtoMapper, OrdersRequestDtoMapper ordersRequestDtoMapper, ProductOrderDtoMapper productOrderDtoMapper, UsersRequestDtoMapper usersRequestDtoMapper) {
        this.usersDtoMapper = usersDtoMapper;
        this.productDtoMapper = productDtoMapper;
        this.ordersRequestDtoMapper = ordersRequestDtoMapper;
        this.productOrderDtoMapper = productOrderDtoMapper;
        this.usersRequestDtoMapper = usersRequestDtoMapper;
    }

    @Override
    public Orders addOrders(OrdersRequestDto ordersRequestDto) {
        Product product = productOrderDtoMapper.apply(ordersRequestDto.productOrderDto());
        int orderedQuantity = ordersRequestDto.quantity();

        Product existingProduct = productRepository.findByNameAndDeletedFalse(product.getName());

        int remainingQuantity = existingProduct.getQuantity() - orderedQuantity;

        if (remainingQuantity <= 0) {
            throw new InsufficientQuantityException();
        }

        existingProduct.setQuantity(remainingQuantity);
        productService.deleteProductIfQuantityZero(existingProduct);
        productRepository.save(existingProduct);

        Orders orders=new Orders();
        orders.setUsers(usersRequestDtoMapper.apply(ordersRequestDto.usersRequestDto()));
        orders.setProduct(product);
        orders.setOrderTime(ordersRequestDto.orderTime());
        orders.setQuantity(ordersRequestDto.quantity());
        orders.setTotalPrice(ordersRequestDto.totalPrice());
        return ordersRepository.save(orders);
    }

    @Override
    public String deleteOrders(int id) {
        String status=null;
        Optional<Orders> optional=ordersRepository.findById(id);
        if(optional.isPresent())
        {
            Orders orders = optional.get();
            orders.setDeleted(true);
            ordersRepository.save(orders);
            return "Order deleted";
        }
        else {
            status="Order not deleted,Please check your id is valid";
        }
        return status;
    }

    @Override
    public List<OrdersDto> displayAllOrders() {
        List<OrdersDto> ordersList=ordersRepository.findByDeletedFalse()
                .stream()
                .map(orders->new OrdersDto(
                        usersDtoMapper.apply(orders.getUsers()),
                        productDtoMapper.apply(orders.getProduct()),
                        orders.getQuantity(),
                        orders.getTotalPrice(),
                        orders.getOrderTime()))
                .collect(Collectors.toList());;
        if(ordersList.isEmpty()){
            throw new NoEntriesFound();
        }
        return ordersList;
    }


}
