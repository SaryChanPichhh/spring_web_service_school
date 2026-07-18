package com.example.web_service.feature.admin.user.dto.res;

import lombok.Data;
import java.util.List;

@Data
public class UserPageResponseDto<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
