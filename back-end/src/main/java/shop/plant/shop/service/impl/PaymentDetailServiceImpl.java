package shop.plant.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.model.PaymentDetail;
import shop.plant.shop.repositories.PaymentDetailRepository;
import shop.plant.shop.service.PaymentDetailService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the PaymentDetailService interface for managing payment details.
 */
@Service
public class PaymentDetailServiceImpl implements PaymentDetailService {
    private final PaymentDetailRepository paymentDetailRepository;

    @Autowired
    public PaymentDetailServiceImpl(PaymentDetailRepository paymentDetailRepository) {
        this.paymentDetailRepository = paymentDetailRepository;
    }

    /**
     * Save a new payment detail or update an existing one.
     *
     * @param paymentDetail The PaymentDetail object to be saved or updated.
     * @return The saved PaymentDetail object.
     */
    @Override
    public PaymentDetail savePaymentDetail(PaymentDetail paymentDetail) {
        return paymentDetailRepository.save(paymentDetail);
    }

    /**
     * Retrieve payment detail by its unique identifier (ID).
     *
     * @param id The ID of the payment detail to retrieve.
     * @return An Optional containing the PaymentDetail object if found, or empty if not found.
     */
    @Override
    public Optional<PaymentDetail> getPaymentDetailById(Long id) {
        return paymentDetailRepository.findById(id);
    }

    /**
     * Retrieve all payment details.
     *
     * @return A list of PaymentDetail objects representing all payment details.
     */
    @Override
    public List<PaymentDetail> getAllPaymentDetails() {
        return paymentDetailRepository.findAll();
    }

    /**
     * Update existing payment detail.
     *
     * @param paymentDetail The PaymentDetail object to be updated.
     */
    @Override
    public void updatePaymentDetail(PaymentDetail paymentDetail) {
        paymentDetailRepository.save(paymentDetail);
    }

    /**
     * Delete payment detail by its unique identifier (ID).
     *
     * @param id The ID of the payment detail to delete.
     */
    @Override
    public void deletePaymentDetail(Long id) {
        paymentDetailRepository.deleteById(id);
    }

}
