package com.example.service;

import com.example.common.model.dto.UserDto;
import com.example.common.model.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    Page<UserDto> findUserListService(Pageable pageable);

}
