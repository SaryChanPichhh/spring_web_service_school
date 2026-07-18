package com.example.web_service.feature.admin.restaurant.dto.res;

import lombok.Data;
import java.util.List;

@Data
public class RestaurantPageResponseDto<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
