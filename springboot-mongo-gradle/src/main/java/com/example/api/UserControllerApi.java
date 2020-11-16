package com.example.api;

import com.example.common.model.response.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface UserControllerApi {

    public ApiResponse<?> findUserListController(@PageableDefault Pageable pageable);

}
