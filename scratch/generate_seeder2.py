import os
import re

base_dir = r"E:\SETEC INSTITUTE\Semester IIV\Java\web_service\src\main\java\com\example\web_service\feature"
seeder_file = r"E:\SETEC INSTITUTE\Semester IIV\Java\web_service\src\main\java\com\example\web_service\seeder\DatabaseSeeder.java"

fields_info = {} # model -> { relation_type: field_name } e.g. 'Delivery' -> {'User': 'users'}

for root, dirs, files in os.walk(base_dir):
    if "model" in root.split(os.sep):
        for file in files:
            if file.endswith(".java"):
                file_path = os.path.join(root, file)
                with open(file_path, "r", encoding="utf-8") as f:
                    content = f.read()
                
                model_name = file[:-5]
                fields_info[model_name] = {}
                lines = content.split('\n')
                for i, line in enumerate(lines):
                    if "@ManyToOne" in line:
                        for j in range(i+1, min(i+4, len(lines))):
                            match = re.search(r'private\s+(\w+)\s+(\w+);', lines[j])
                            if match:
                                type_name = match.group(1)
                                field_name = match.group(2)
                                fields_info[model_name][type_name] = field_name
                                break

print("Fields Info:", fields_info)

# I can just rewrite the seeder file manually here
java_content = """package com.example.web_service.seeder;

import com.example.web_service.feature.admin.user.model.User;
import com.example.web_service.feature.admin.user.repository.UserRepository;
import com.example.web_service.feature.category.model.Category;
import com.example.web_service.feature.category.repository.CategoryRepository;
import com.example.web_service.feature.admin.exchangerate.model.ExchangeRate;
import com.example.web_service.feature.admin.exchangerate.repository.ExchangeRateRepository;
import com.example.web_service.feature.admin.permission.model.Permission;
import com.example.web_service.feature.admin.permission.repository.PermissionRepository;
import com.example.web_service.feature.admin.restaurant.model.Restaurant;
import com.example.web_service.feature.admin.restaurant.repository.RestaurantRepository;
import com.example.web_service.feature.admin.menu.model.Menu;
import com.example.web_service.feature.admin.menu.repository.MenuRepository;
import com.example.web_service.feature.admin.delivery.model.Delivery;
import com.example.web_service.feature.admin.delivery.repository.DeliveryRepository;
import com.example.web_service.feature.admin.saleheader.model.SaleHeader;
import com.example.web_service.feature.admin.saleheader.repository.SaleHeaderRepository;
import com.example.web_service.feature.admin.saledetail.model.SaleDetail;
import com.example.web_service.feature.admin.saledetail.repository.SaleDetailRepository;
import com.example.web_service.feature.admin.coupon.model.Coupon;
import com.example.web_service.feature.admin.coupon.repository.CouponRepository;
import com.example.web_service.feature.admin.review.model.Review;
import com.example.web_service.feature.admin.review.repository.ReviewRepository;
import com.example.web_service.feature.admin.favorites.model.Favorites;
import com.example.web_service.feature.admin.favorites.repository.FavoritesRepository;
import com.example.web_service.feature.admin.faq.model.Faq;
import com.example.web_service.feature.admin.faq.repository.FaqRepository;
import com.example.web_service.feature.admin.feedback.model.Feedback;
import com.example.web_service.feature.admin.feedback.repository.FeedbackRepository;
import com.example.web_service.feature.admin.couponassignment.model.CouponAssignment;
import com.example.web_service.feature.admin.couponassignment.repository.CouponAssignmentRepository;
import com.example.web_service.feature.admin.freedeliveryassignment.model.FreeDeliveryAssignment;
import com.example.web_service.feature.admin.freedeliveryassignment.repository.FreeDeliveryAssignmentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final PermissionRepository permissionRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final DeliveryRepository deliveryRepository;
    private final SaleHeaderRepository saleHeaderRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final CouponRepository couponRepository;
    private final ReviewRepository reviewRepository;
    private final FavoritesRepository favoritesRepository;
    private final FaqRepository faqRepository;
    private final FeedbackRepository feedbackRepository;
    private final CouponAssignmentRepository couponAssignmentRepository;
    private final FreeDeliveryAssignmentRepository freeDeliveryAssignmentRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = null;
        if (userRepository.count() == 0) {
            user = new User();
            user.setUserName("admin");
            user.setFirstName("Super");
            user.setLastName("Admin");
            user.setEmail("admin@example.com");
            user.setPhone("1234567890");
            user.setPassword("password");
            user.setRole("ADMIN");
            user.setIsActive(true);
            user = userRepository.save(user);
        } else {
            user = userRepository.findAll().get(0);
        }

        Category category = null;
        if (categoryRepository.count() == 0) {
            category = new Category();
            category.setTitle("Fast Food");
            category.setDescription("Quick and tasty");
            category = categoryRepository.save(category);
        } else {
            category = categoryRepository.findAll().get(0);
        }

        ExchangeRate exchangeRate = null;
        if (exchangeRateRepository.count() == 0) {
            exchangeRate = new ExchangeRate();
            exchangeRate.setCurrencyCode("USD");
            exchangeRate.setCurrencyName("US Dollar");
            exchangeRate.setRate(1.0);
            exchangeRate.setSymbol("$");
            exchangeRate = exchangeRateRepository.save(exchangeRate);
        } else {
            exchangeRate = exchangeRateRepository.findAll().get(0);
        }

        Permission permission = null;
        if (permissionRepository.count() == 0) {
            permission = new Permission();
            permission.setChildName("READ_ALL");
            permission.setParentName("ADMIN");
            permission = permissionRepository.save(permission);
        } else {
            permission = permissionRepository.findAll().get(0);
        }

        Faq faq = null;
        if (faqRepository.count() == 0) {
            faq = new Faq();
            faq.setQuestion("How to order?");
            faq.setAnswer("Use the app.");
            faq.setActive(true);
            faq = faqRepository.save(faq);
        }

        Feedback feedback = null;
        if (feedbackRepository.count() == 0) {
            feedback = new Feedback();
            feedback.setUsers(user);
            feedback.setSubject("Great App");
            feedback.setMessage("Loved the experience.");
            feedback = feedbackRepository.save(feedback);
        }

        Delivery delivery = null;
        if (deliveryRepository.count() == 0) {
            delivery = new Delivery();
            delivery.setUsers(user);
            delivery.setName("Speedy Delivery");
            delivery.setPhone("0987654321");
            delivery.setStatus(true);
            delivery = deliveryRepository.save(delivery);
        } else {
            delivery = deliveryRepository.findAll().get(0);
        }

        Restaurant restaurant = null;
        if (restaurantRepository.count() == 0) {
            restaurant = new Restaurant();
            restaurant.setUser(user);
            restaurant.setResName("Burger King");
            restaurant.setAddress("123 Main St");
            restaurant.setIsOpen(true);
            restaurant.setRating(4.5);
            restaurant = restaurantRepository.save(restaurant);
        } else {
            restaurant = restaurantRepository.findAll().get(0);
        }

        Menu menu = null;
        if (menuRepository.count() == 0) {
            menu = new Menu();
            menu.setRestaurants(restaurant);
            menu.setCategories(category);
            menu.setName("Whopper");
            menu.setPrice(5.99);
            menu.setRating(4.8); 
            menu = menuRepository.save(menu);
        } else {
            menu = menuRepository.findAll().get(0);
        }

        Coupon coupon = null;
        if (couponRepository.count() == 0) {
            coupon = new Coupon();
            coupon.setCode("DISCOUNT10");
            coupon.setDiscountType("PERCENTAGE");
            coupon.setDiscountValue(10.0);
            coupon.setStatus("ACTIVE");
            coupon = couponRepository.save(coupon);
        } else {
            coupon = couponRepository.findAll().get(0);
        }

        if (reviewRepository.count() == 0) {
            Review review = new Review();
            review.setUsers(user);
            review.setRestaurants(restaurant);
            review.setRating(5.0);
            review.setComment("Amazing!");
            reviewRepository.save(review);
        }

        if (favoritesRepository.count() == 0) {
            Favorites favorites = new Favorites();
            favorites.setUsers(user);
            favorites.setRestaurants(restaurant);
            favorites.setStatus(true);
            favoritesRepository.save(favorites);
        }

        SaleHeader saleHeader = null;
        if (saleHeaderRepository.count() == 0) {
            saleHeader = new SaleHeader();
            saleHeader.setUsers(user);
            saleHeader.setDeliverys(delivery);
            saleHeader.setExchangeRates(exchangeRate);
            saleHeader.setTotal(5.99);
            saleHeader.setStatus("COMPLETED");
            saleHeader = saleHeaderRepository.save(saleHeader);
        } else {
            saleHeader = saleHeaderRepository.findAll().get(0);
        }

        if (saleDetailRepository.count() == 0) {
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setSaleHeaders(saleHeader);
            saleDetail.setCoupons(coupon);
            saleDetail.setRestaurants(restaurant);
            saleDetail.setQty(1);
            saleDetail.setSalePrice(5.99);
            saleDetail.setTotal(5.99);
            saleDetailRepository.save(saleDetail);
        }

        if (couponAssignmentRepository.count() == 0) {
            CouponAssignment ca = new CouponAssignment();
            ca.setCoupons(coupon);
            ca.setRestaurants(restaurant);
            ca.setMenus(menu);
            ca.setAssignmentType("GLOBAL");
            ca.setStatus("ACTIVE");
            couponAssignmentRepository.save(ca);
        }

        if (freeDeliveryAssignmentRepository.count() == 0) {
            FreeDeliveryAssignment fda = new FreeDeliveryAssignment();
            fda.setRestaurants(restaurant);
            fda.setMenus(menu);
            fda.setAssignmentType("GLOBAL");
            fda.setStatus("ACTIVE");
            freeDeliveryAssignmentRepository.save(fda);
        }

        System.out.println("Database seeded with sample data!");
    }
}
"""

with open(seeder_file, "w", encoding="utf-8") as f:
    f.write(java_content)
    
print("Updated DatabaseSeeder.java")
