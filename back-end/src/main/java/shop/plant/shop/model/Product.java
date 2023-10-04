    package shop.plant.shop.model;

    import jakarta.persistence.*;
    import jdk.jfr.Category;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;

    /**
     * This class represents a product entity.
     * It is mapped to a database table and contains information about products, including:
     * - Unique identifier (id)
     * - Name of the product (name)
     * - Description or details of the product (desc)
     * - Price of the product (price)
     * - Maim image of product (mainImageUrl)
     * - Timestamp for when the product was created (createdAt)
     * - Products for gender : male || female || another  ( gender )
     * - Category ID (categoryId) - Foreign key linking to ProductCategory
     * - Inventory ID (inventoryId) - Foreign key linking to ProductInventory
     * - Discount ID (discountId) - Foreign key linking to Discount
     */
    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String description;
        private double price;
        private String mainImageUrl;
        private LocalDateTime createdAt = LocalDateTime.now();
        private String gender;

        @ManyToOne
        @JoinColumn(name = "categoryId", referencedColumnName = "id")
        private ProductCategory productCategory;

        @OneToOne
        @JoinColumn(name = "inventoryId", referencedColumnName = "id")
        private ProductInventory productInventory;

        @ManyToOne
        @JoinColumn(name = "discountId", referencedColumnName = "id")
        private Discount discount;
    }
