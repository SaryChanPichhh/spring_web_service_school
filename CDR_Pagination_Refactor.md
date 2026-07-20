# Change Data Request: Pagination Parameter Refactor

## Overview
**Request ID:** CDR-2024-001  
**Date:** 2024-01-XX  
**Priority:** Medium  
**Status:** Pending Approval  

## Summary
Change all paginated API endpoints from accepting Spring's `Pageable` object as a method parameter to accepting explicit `page` and `size` query parameters (`@RequestParam Integer page, @RequestParam Integer size`).

## Business Justification
- **API Clarity**: Explicit parameters make the API contract clearer for consumers
- **Consistency**: Aligns with REST API best practices for pagination
- **Flexibility**: Allows custom validation and default value handling
- **Documentation**: Easier to document in OpenAPI/Swagger specifications

## Technical Scope

### Files Affected
The following file types will be modified across all features:

1. **Controller Classes** (20 files)
   - Change method signature from `Pageable pageable` to `@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size`
   - Create `PageRequest` object inside the method

2. **Service Interfaces** (20 files)
   - Change method signature from `Pageable pageable` to `Integer page, Integer size`

3. **Service Implementation Classes** (20 files)
   - Change method signature to match interface
   - Create `PageRequest.of(page, size)` before calling repository

### Features Impacted
- admin/permission
- admin/feedback
- admin/exchangerate
- admin/favorites
- admin/menu
- admin/couponassignment
- admin/delivery
- admin/restaurant
- admin/faq
- admin/saleheader
- admin/freedeliveryassignment
- admin/user
- admin/review
- admin/coupon
- admin/saledetail
- category

## Change Details

### Before (Current Implementation)

**Controller:**
```java
@GetMapping("/paginated")
public ResponseEntity<ResponseDto<T>> getPaginated(Pageable pageable) {
    return ResponseEntity.ok(service.getPaginated(pageable));
}
```

**Service Interface:**
```java
ResponseDto<T> getPaginated(Pageable pageable);
```

**Service Implementation:**
```java
@Override
public ResponseDto<T> getPaginated(Pageable pageable) {
    Page<Entity> pageResult = repository.findAll(pageable);
    // ... mapping logic
}
```

### After (Proposed Implementation)

**Controller:**
```java
@GetMapping("/paginated")
public ResponseEntity<ResponseDto<T>> getPaginated(
    @RequestParam(defaultValue = "0") Integer page,
    @RequestParam(defaultValue = "10") Integer size
) {
    return ResponseEntity.ok(service.getPaginated(page, size));
}
```

**Service Interface:**
```java
ResponseDto<T> getPaginated(Integer page, Integer size);
```

**Service Implementation:**
```java
@Override
public ResponseDto<T> getPaginated(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Entity> pageResult = repository.findAll(pageable);
    // ... mapping logic
}
```

## API Contract Changes

### Query Parameters

| Parameter | Type   | Required | Default | Description                    |
|-----------|--------|----------|---------|--------------------------------|
| page      | Integer| No       | 0       | Page number (0-indexed)        |
| size      | Integer| No       | 10      | Number of items per page       |

### Example Requests

**Before:**
```
GET /api/v1/admin/permissions/paginated?page=0&size=20&sort=name,asc
```

**After:**
```
GET /api/v1/admin/permissions/paginated?page=0&size=20
```

*Note: Sorting functionality may need to be addressed in a separate CDR if required.*

## Risk Assessment

| Risk                          | Likelihood | Impact | Mitigation                              |
|-------------------------------|------------|--------|-----------------------------------------|
| Breaking change for clients   | High       | Medium | Version API or provide migration period |
| Loss of sort functionality    | Medium     | Low    | Add sort parameters if needed           |
| Inconsistent implementation   | Low        | Low    | Use consistent template across files    |
| Missing default values        | Low        | Low    | Always specify defaultValue in annotation|

## Rollback Plan
If issues are discovered post-deployment:
1. Revert all changed files using Git
2. Redeploy previous version
3. Notify API consumers of rollback

## Testing Requirements

### Unit Tests
- [ ] Verify default values work correctly (page=0, size=10)
- [ ] Verify custom page and size values are respected
- [ ] Verify edge cases (negative values, zero size, etc.)

### Integration Tests
- [ ] Test all affected endpoints with various page/size combinations
- [ ] Verify response structure remains unchanged
- [ ] Verify total count and pagination metadata are correct

### Regression Tests
- [ ] Ensure non-paginated endpoints are unaffected
- [ ] Verify other query parameters still work correctly

## Implementation Checklist

- [ ] Review and approve this CDR
- [ ] Create backup branch
- [ ] Update Controller classes (20 files)
- [ ] Update Service interfaces (20 files)
- [ ] Update Service implementations (20 files)
- [ ] Run unit tests
- [ ] Run integration tests
- [ ] Update API documentation
- [ ] Code review
- [ ] Deploy to staging
- [ ] QA verification
- [ ] Deploy to production
- [ ] Monitor for errors

## Approval

| Role            | Name | Signature | Date |
|-----------------|------|-----------|------|
| Requestor       |      |           |      |
| Tech Lead       |      |           |      |
| Product Owner   |      |           |      |
| QA Lead         |      |           |      |

---

## Appendix: File List

### Controllers to Modify
1. `/workspace/src/main/java/com/example/web_service/feature/admin/permission/controller/PermissionController.java`
2. `/workspace/src/main/java/com/example/web_service/feature/admin/feedback/controller/FeedbackController.java`
3. `/workspace/src/main/java/com/example/web_service/feature/admin/exchangerate/controller/ExchangeRateController.java`
4. `/workspace/src/main/java/com/example/web_service/feature/admin/favorites/controller/FavoritesController.java`
5. `/workspace/src/main/java/com/example/web_service/feature/admin/menu/controller/MenuController.java`
6. `/workspace/src/main/java/com/example/web_service/feature/admin/couponassignment/controller/CouponAssignmentController.java`
7. `/workspace/src/main/java/com/example/web_service/feature/admin/delivery/controller/DeliveryController.java`
8. `/workspace/src/main/java/com/example/web_service/feature/admin/restaurant/controller/RestaurantController.java`
9. `/workspace/src/main/java/com/example/web_service/feature/admin/faq/controller/FaqController.java`
10. `/workspace/src/main/java/com/example/web_service/feature/admin/saleheader/controller/SaleHeaderController.java`
11. `/workspace/src/main/java/com/example/web_service/feature/admin/freedeliveryassignment/controller/FreeDeliveryAssignmentController.java`
12. `/workspace/src/main/java/com/example/web_service/feature/admin/user/controller/UserController.java`
13. `/workspace/src/main/java/com/example/web_service/feature/admin/review/controller/ReviewController.java`
14. `/workspace/src/main/java/com/example/web_service/feature/admin/coupon/controller/CouponController.java`
15. `/workspace/src/main/java/com/example/web_service/feature/admin/saledetail/controller/SaleDetailController.java`
16. `/workspace/src/main/java/com/example/web_service/feature/category/controller/CategoryController.java`

### Service Interfaces to Modify
(Corresponding service files in each feature package)

### Service Implementations to Modify
(Corresponding serviceImpl files in each feature package)
