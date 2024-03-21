package com.t3h.web_demo.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController // xác định file điều hướg req/res dạng restful
@Slf4j // log in ra console thay cho system.out
public class OrderController {

    /**
     * nhung module de chay truy van truc tiep
     */
    @PersistenceContext
    EntityManager em;

    /**
     * xây truy vấn them  theo filter
     * ko dung repository & entity co san
     * dung native query
     */
    @GetMapping("/api/v1/order")
    public ResponseEntity findAll(@RequestParam(required = false) String searchText,
                                  @RequestParam(required = false) LocalDate bookingDateFrom,
                                  @RequestParam(required = false) LocalDate bookingDateTo,
                                  @RequestParam(required = false) String city,
                                  @RequestParam(required = false) LocalDate paymentDateFrom,
                                  @RequestParam(required = false) LocalDate paymentDateTo,
                                  @RequestParam(required = false) LocalDate shippedDateFrom,
                                  @RequestParam(required = false) LocalDate shippedDateTo
    ) {


        String sqlWhere = "";

        /*
        dat tam alias ten bang
        order o
        payment p
        customer c
        searchText: tim theo order number, customer phone, customerName -> like % %
         */
        if (shippedDateFrom != null && shippedDateTo != null) {
            if(!sqlWhere.isBlank()){
                sqlWhere += " OR ";
            }

            sqlWhere += String.format(" o.shippedDate between '%s' AND '%s' ", shippedDateFrom, shippedDateTo);
        }

        if (paymentDateFrom != null && paymentDateTo != null) {
            if(!sqlWhere.isBlank()){
                sqlWhere += " OR ";
            }
            sqlWhere += String.format(" p.paymentDate between '%s' AND '%s' ", paymentDateFrom, paymentDateTo);
        }

        if (bookingDateFrom != null && bookingDateTo != null) {
            if(!sqlWhere.isBlank()){
                sqlWhere += " OR ";
            }
            sqlWhere += String.format(" o.orderDate between '%s' AND '%s' ", bookingDateFrom, bookingDateTo);
        }

        if (city != null) {
            if(!sqlWhere.isBlank()){
                sqlWhere += " OR ";
            }
            sqlWhere += String.format(" c.city = '%s' ", city);
        }

        if (searchText != null) {
            if(!sqlWhere.isBlank()){
                sqlWhere += " OR ";
            }
            sqlWhere += "( o.orderNumber LIKE '%" + searchText + "%'  " +
                    " OR c.phone LIKE '%" + searchText + "%'  " +
                    " OR c.customerName LIKE '%" + searchText + "%' )"
            ;
        }

        if (!sqlWhere.isBlank()){
            sqlWhere  = " WHERE " + sqlWhere;
        }

        String sqlSelect = """
                SELECT c.customerName, 
                    o.orderNumber, 
                    o.orderDate, 
                    c.country, 
                    c.state, 
                    c.city, 
                    c.addressLine1, 
                    p.amount, 
                    p.paymentDate 
                FROM orders o 
                LEFT JOIN customers c ON c.customerNumber = o.customerNumber 
                LEFT JOIN payments p ON p.customerNumber = c.customerNumber 
                """
                + sqlWhere
                ;


        Query query = em.createNativeQuery(sqlSelect, OrderDTO.class);

        List<OrderDTO> dto = query.getResultList();
        System.out.println(dto);


        return ResponseEntity.ok(dto);
    }



}

class OrderDTO{
    String customerName;
    Integer orderNumber;
    Date orderDate;
    String country;
    String state;
    String city;
    String addressLine1;
    BigDecimal amount;
    Date paymentDate;

    public OrderDTO(String customerName, Integer orderNumber, Date orderDate, String country, String state, String city, String addressLine1, BigDecimal amount, Date paymentDate) {
        this.customerName = customerName;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.country = country;
        this.state = state;
        this.city = city;
        this.addressLine1 = addressLine1;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
