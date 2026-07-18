package com.example.web_service.feature.category.dto.res;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CategoryPageResponseDto<T> {
    private List<T> content;
    private int totalElement;
    private int totalPages;
    private boolean last;
    private boolean first;
}
