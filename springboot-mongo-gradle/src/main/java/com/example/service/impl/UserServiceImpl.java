package com.example.service.impl;

import com.example.common.model.ApiMessage;
import com.example.common.model.dto.UserDto;
import com.example.common.model.response.ApiResponse;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangy
 * @Time 2020/4/3 23:52
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserDto> findUserListService(Pageable pageable) {

//        User user = mongoTemplate.findById(new ObjectId("5cc7b36382b46a0001f46d1b"), User.class);
//        Optional<User> user = userRepository.findById("5cc7b36282b46a0001f46d19");
//        return new ApiResponse<>(ApiMessage.OK,user);


//        List<User> list = userRepository.findAll();

//        long count = userRepository.count();
        Query query = new Query();
        query.with(pageable);
        List<User> userList = mongoTemplate.findAll(User.class);
        long count = mongoTemplate.count(query, User.class);

//        return new ApiResponse<>(ApiMessage.OK,count);
        return PageableExecutionUtils.getPage(userList,pageable,() -> count).map(user -> UserDto.of(user));


    }
}
