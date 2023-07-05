package com.nocountry.ecommerce.model;

import com.nocountry.ecommerce.util.enums.Role;
import com.nocountry.ecommerce.util.enums.TransactionState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DiscriminatorColumn(name="transaction", discriminatorType= DiscriminatorType.STRING)
@Entity
@Table(name = "TRANSACTIONS")
public class Transactions {

    @Id
    @Column(name = "transaction_uuid")
    private String transactionUuid;
    @Column(name = "total", nullable = false, updatable = false)
    private Double total;
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private TransactionState transactionState;
}
