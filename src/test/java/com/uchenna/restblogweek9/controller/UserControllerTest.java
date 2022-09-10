package com.uchenna.restblogweek9.controller;

import com.uchenna.restblogweek9.dto.*;
import com.uchenna.restblogweek9.model.Comment;
import com.uchenna.restblogweek9.model.Like;
import com.uchenna.restblogweek9.model.Post;
import com.uchenna.restblogweek9.model.User;
import com.uchenna.restblogweek9.repository.CommentRepository;
import com.uchenna.restblogweek9.repository.LikeRepository;
import com.uchenna.restblogweek9.repository.PostRepository;
import com.uchenna.restblogweek9.repository.UserRepository;
import com.uchenna.restblogweek9.pojo.*;
import com.uchenna.restblogweek9.service.userServiceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.time.Month.AUGUST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private LikeRepository likeRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private LocalDateTime localDateTime;
    private User user;
    private Comment comment;
    private Like like;
    private Post post;
    List<Like> likeList = new ArrayList<>();
    List<Post> postList = new ArrayList<>();
    List<Comment> commentList = new ArrayList<>();
    List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        localDateTime = LocalDateTime.of(2022, AUGUST,3,6,30,40,50000);
        user = new User(1 , "vincent" , "enwerevincent@gmail.com" , "Admin" , "12345" , localDateTime , localDateTime  , postList , commentList , likeList);
       post = new Post(1 , "title of post" , "small description" , "title-of-post" , "0xEsddfmk.png", localDateTime, localDateTime, user, commentList , likeList);
        like = new Like(1 , true , localDateTime , localDateTime , user , post);
        comment = new Comment(1 , "lovely" , localDateTime , localDateTime , post , user);
    }

    @Test
    void register() {
        UserDTO userDTO = new UserDTO( "vincent" , "enwerevincent@gmail.com" , "Admin" , "12345");
        when(userRepository.save(user)).thenReturn(user);
       // when(userRepository.findByEmail("enwerevincent@gmail.com")).thenReturn(Optional.ofNullable(user));
        RegisterResponse registerResponse = new RegisterResponse("SUCCESS" , localDateTime , user);
        var actual =  userService.register(userDTO);
        actual.setTimeStamp(localDateTime);
        actual.getUser().setCreatedAt(localDateTime);
        actual.getUser().setUpdatedAt(localDateTime);
        actual.getUser().setId(1);
        assertEquals(registerResponse , actual);
    }


    @Test
    void createPost() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        PostDTO postDto = new PostDTO( "title of post" , "small description" , "0xEsddfmk.png",  1);
        PostResponse postResponse = new PostResponse("SUCCESS" , localDateTime , post);
        var actual = userService.createPost(postDto);
        actual.setTimeStamp(localDateTime);
        actual.getPost().setCreatedAt(localDateTime);
        actual.getPost().setUpdatedAt(localDateTime);
        actual.getPost().setId(1);
        actual.getPost().setSlug("title-of-post");
        assertEquals(postResponse, actual);
    }

    @Test
    void like() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        when(postRepository.findById(1)).thenReturn(Optional.ofNullable(post));
        List<Like> likes = new ArrayList<>(Arrays.asList(like));
        when(likeRepository.likeList(1)).thenReturn(likes);
        LikeDTO likeDto = new LikeDTO(true);
        LikeResponse likeResponse = new LikeResponse("SUCCESS" , localDateTime , like , 1);
        var actual = userService.liked(1 , 1  , likeDto);
        actual.setTimeStamp(localDateTime);
        actual.setLike(like);
        likeResponse.getLike().setUser(user);
        likeResponse.getLike().setPost(post);
        assertEquals(likeResponse , actual);
    }

    @Test
    void comment() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        when(postRepository.findById(1)).thenReturn(Optional.ofNullable(post));
        CommentDTO commentDto = new CommentDTO("lovely");
        CommentResponse commentResponse = new CommentResponse("SUCCESS" , localDateTime , comment , post);
        var actual = userService.comment(1 , 1  , commentDto);
        actual.setTimeStamp(localDateTime);
        actual.setComment(comment);
        commentResponse.setComment(comment);
        commentResponse.setPost(post);
        assertEquals(commentResponse , actual);
    }



    @Test
    void findUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        assertEquals(user , userService.findUserById(1));
    }

    @Test
    void findPostById() {
        when(postRepository.findById(1)).thenReturn(Optional.ofNullable(post));
        assertEquals(post , userService.findPostById(1));
    }

    @Test
    void findUserByEmail() {
        when(userRepository.findByEmail("enwerevincent@gmail.com")).thenReturn(Optional.ofNullable(user));
        assertEquals(user , userService.findByEmail("enwerevincent@gmail.com"));
    }
}