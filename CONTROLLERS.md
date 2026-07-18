# Controllers Documentation

This document provides a comprehensive overview of all the controllers in the `spring_web_service_school` project, detailing their descriptions and purposes.

## Global Controllers

### `GlobalExceptionController`
- **Description:** A global exception handler annotated with `@RestControllerAdvice`.
- **Purpose:** Centralizes the handling of exceptions across the entire application, ensuring consistent API responses for errors (e.g., Validation failures, 404 Not Found, 500 Internal Server Error).

## Category Management

### `CategoryController`
- **Description:** Exposes RESTful endpoints for Category entities.
- **Purpose:** Manages the creation, retrieval, updating, and deletion (CRUD) of categories, typically used to group menus, restaurants, or other items.

## Admin Features

### `CouponController`
- **Description:** REST controller for Coupon management.
- **Purpose:** Handles CRUD operations for discount coupons, including fetching paginated lists and individual coupon details.

### `CouponAssignmentController`
- **Description:** REST controller for assigning coupons.
- **Purpose:** Manages the assignment and distribution of coupons to specific users or orders.

### `DeliveryController`
- **Description:** REST controller for delivery operations.
- **Purpose:** Manages delivery settings, options, tracking, and associated delivery configurations for orders.

### `ExchangeRateController`
- **Description:** REST controller for currency exchange rates.
- **Purpose:** Handles the configuration and retrieval of exchange rates, which may be used for multi-currency pricing or conversions.

### `FaqController`
- **Description:** REST controller for Frequently Asked Questions (FAQ).
- **Purpose:** Manages the content of the FAQ section, allowing administrators to add, update, delete, or fetch FAQ items.

### `FavoritesController`
- **Description:** REST controller for user favorites.
- **Purpose:** Manages the items (such as restaurants or menus) that users have marked as favorites.

### `FeedbackController`
- **Description:** REST controller for application feedback.
- **Purpose:** Handles the submission, retrieval, and management of user feedback regarding the application or service.

### `FreeDeliveryAssignmentController`
- **Description:** REST controller for free delivery promotions.
- **Purpose:** Manages the rules and assignments that determine when and to whom free delivery applies.

### `MenuController`
- **Description:** REST controller for menu items.
- **Purpose:** Handles CRUD operations for food or service menus offered by restaurants.

### `PermissionController`
- **Description:** REST controller for permissions.
- **Purpose:** Manages system permissions and access control, allowing administrators to configure user roles and rights.

### `RestaurantController`
- **Description:** REST controller for restaurants.
- **Purpose:** Manages restaurant profiles, details, and listings within the platform.

### `ReviewController`
- **Description:** REST controller for user reviews.
- **Purpose:** Handles the submission and retrieval of reviews and ratings for restaurants or specific menu items.

### `SaleDetailController`
- **Description:** REST controller for sale details.
- **Purpose:** Manages the detailed, line-item records of sales or orders (e.g., individual items purchased in an order).

### `SaleHeaderController`
- **Description:** REST controller for sale headers.
- **Purpose:** Manages the high-level summary records of sales or orders (e.g., total price, order status, customer info).

### `UserController`
- **Description:** REST controller for user management.
- **Purpose:** Manages user accounts, profiles, authentication-related details, and general user data within the system.
