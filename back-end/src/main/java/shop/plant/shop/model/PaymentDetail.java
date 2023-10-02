package shop.plant.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class represents payment details for a transaction.
 * It is mapped to a database table and contains information about payments, including:
 * - Unique identifier (id)
 * - Payment provider (provider)
 * - Amount of the payment (amount)
 * - Timestamp for when the payment was made (paymentDate)
 * - Status of the payment (status)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String provider;
    private double amount;
    private LocalDateTime paymentDate;
    private String status;

}

