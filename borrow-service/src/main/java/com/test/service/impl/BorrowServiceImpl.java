package com.test.service.impl;

import com.test.client.BookClient;
import com.test.client.UserClient;
import com.test.entity.Book;
import com.test.entity.Borrow;
import com.test.entity.User;
import com.test.entity.UserBorrowDetail;
import com.test.mapper.BorrowMapper;
import com.test.service.BorrowService;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Resource
    BorrowMapper mapper;

    @Resource
    BookClient bookClient;

    @Resource
    UserClient userClient;

    @Resource
    OAuth2RestTemplate template;

    @Override
    public UserBorrowDetail getUserBorrowDetailByUid(int uid) {
        List<Borrow> borrow = mapper.getBorrowsByUid(uid);
        User user = template.getForObject("http://user-service/user/"+uid, User.class); //获取每一本书的详细信息
                List<Book> bookList = borrow
                        .stream()
                        .map(b -> template.getForObject("http://localhost:8101/book/", Book.class))
                        .collect(Collectors.toList());
        return new UserBorrowDetail(user, bookList);
    }
}
