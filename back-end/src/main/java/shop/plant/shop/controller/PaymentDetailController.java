package shop.plant.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.plant.shop.model.PaymentDetail;
import shop.plant.shop.service.PaymentDetailService;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing payment details.
 */
@RestController
@RequestMapping("/api/payment-details")
@RequiredArgsConstructor
public class PaymentDetailController {
    /**
     * The service responsible for managing payment details, including creation, retrieval, and deletion.
     */
    private final PaymentDetailService paymentDetailService;


    /**
     * Endpoint to create a new payment detail.
     *
     * @param paymentDetail The PaymentDetail object to be created.
     * @return A ResponseEntity containing the created PaymentDetail entity or an error response.
     */
    @PostMapping("/create")
    public ResponseEntity<PaymentDetail> createPaymentDetail(@RequestBody PaymentDetail paymentDetail) {
        PaymentDetail savedPaymentDetail = paymentDetailService.savePaymentDetail(paymentDetail);
        return new ResponseEntity<>(savedPaymentDetail, HttpStatus.CREATED);
    }

    /**
     * Endpoint to get payment detail by its ID.
     *
     * @param id The ID of the payment detail to retrieve.
     * @return A ResponseEntity containing the requested PaymentDetail entity or a not found error response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDetail> getPaymentDetailById(@PathVariable Long id) {
        Optional<PaymentDetail> paymentDetail = paymentDetailService.getPaymentDetailById(id);
        return paymentDetail.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint to get all payment details.
     *
     * @return A ResponseEntity containing a list of PaymentDetail entities or an error response if there are no payment details.
     */
    @GetMapping("/all")
    public ResponseEntity<List<PaymentDetail>> getAllPaymentDetails() {
        List<PaymentDetail> paymentDetailList = paymentDetailService.getAllPaymentDetails();
        return new ResponseEntity<>(paymentDetailList, HttpStatus.OK);
    }

    /**
     * Endpoint to update payment detail.
     *
     * @param paymentDetail The updated PaymentDetail object.
     * @return A ResponseEntity containing the updated PaymentDetail entity or a not found error response.
     */
    @PutMapping("/update")
    public ResponseEntity<PaymentDetail> updatePaymentDetail(@RequestBody PaymentDetail paymentDetail) {
        paymentDetailService.updatePaymentDetail(paymentDetail);
        return new ResponseEntity<>(paymentDetail, HttpStatus.OK);
    }

    /**
     * Endpoint to delete payment detail by its ID.
     *
     * @param id The ID of the payment detail to delete.
     * @return A ResponseEntity indicating success or a not found error response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentDetail(@PathVariable Long id) {
        paymentDetailService.deletePaymentDetail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
