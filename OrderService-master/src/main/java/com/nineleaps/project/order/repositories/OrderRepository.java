package com.nineleaps.project.order.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.nineleaps.project.order.entities.Order;

@Repository
public interface OrderRepository extends CassandraRepository<Order, UUID>{

}
