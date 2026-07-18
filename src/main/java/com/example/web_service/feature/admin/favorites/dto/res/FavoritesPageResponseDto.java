package com.example.web_service.feature.admin.favorites.dto.res;

import lombok.Data;
import java.util.List;

@Data
public class FavoritesPageResponseDto<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
