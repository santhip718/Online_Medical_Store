package com.project.restapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.restapi.model.Payment;


public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}