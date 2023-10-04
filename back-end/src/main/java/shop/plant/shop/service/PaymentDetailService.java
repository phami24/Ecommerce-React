package shop.plant.shop.service;

import shop.plant.shop.model.PaymentDetail;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing payment details.
 */
public interface PaymentDetailService {
    /**
     * Save a new payment detail or update an existing one.
     *
     * @param paymentDetail The PaymentDetail object to be saved or updated.
     * @return The saved PaymentDetail object.
     */
    PaymentDetail savePaymentDetail(PaymentDetail paymentDetail);

    /**
     * Retrieve payment detail by its unique identifier (ID).
     *
     * @param id The ID of the payment detail to retrieve.
     * @return An Optional containing the PaymentDetail object if found, or empty if not found.
     */
    Optional<PaymentDetail> getPaymentDetailById(Long id);

    /**
     * Retrieve all payment details.
     *
     * @return A list of PaymentDetail objects representing all payment details.
     */
    List<PaymentDetail> getAllPaymentDetails();

    /**
     * Update existing payment detail.
     *
     * @param paymentDetail The PaymentDetail object to be updated.
     */
    void updatePaymentDetail(PaymentDetail paymentDetail);

    /**
     * Delete payment detail by its unique identifier (ID).
     *
     * @param id The ID of the payment detail to delete.
     */
    void deletePaymentDetail(Long id);

    // You can add more methods as needed for payment detail management.
}
