package com.uchenna.restblogweek9.service.userServiceImpl;

import com.uchenna.restblogweek9.dto.*;
import com.uchenna.restblogweek9.exception.PostAlreadyLikedException;
import com.uchenna.restblogweek9.exception.PostNotFoundException;
import com.uchenna.restblogweek9.exception.UserNotFoundException;
import com.uchenna.restblogweek9.model.Comment;
import com.uchenna.restblogweek9.model.Like;
import com.uchenna.restblogweek9.model.Post;
import com.uchenna.restblogweek9.model.User;
import com.uchenna.restblogweek9.repository.CommentRepository;
import com.uchenna.restblogweek9.repository.LikeRepository;
import com.uchenna.restblogweek9.repository.PostRepository;
import com.uchenna.restblogweek9.repository.UserRepository;
import com.uchenna.restblogweek9.response.*;
import com.uchenna.restblogweek9.service.UserService;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;



    //slug regex
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[^\\s]");

    public UserServiceImpl(CommentRepository commentRepository, LikeRepository likeRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RegisterResponse register(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return new RegisterResponse("SUCCESS", LocalDateTime.now(), user);
    }

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
       User guest = findByEmail(loginDTO.getEmail());
       LoginResponse loginResponse = null;
       if (guest != null) {
           if (guest.getPassword().equals(loginDTO.getPassword())) {
               loginResponse = new LoginResponse("SUCCESS", LocalDateTime.now());
           }else {
               loginResponse = new LoginResponse("Password mismatch", LocalDateTime.now());
           }
       }else {
           loginResponse = new LoginResponse("User email does not exist", LocalDateTime.now());
       }
        return loginResponse;
    }

    @Override
    public CreatePostResponse createPost(PostDTO postDTO) {
        Post post = new Post();
        User user = findUserById(postDTO.getUser_id());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setSlug(makeSlug(postDTO.getTitle()));
        post.setFeaturedImage(postDTO.getFeaturedImage());
        post.setUser(user);
        postRepository.save(post);
        return new CreatePostResponse("SUCCESS", LocalDateTime.now(), post);
    }

    @Override
    public CommentResponse comment(int user_id, int post_id, CommentDTO commentDTO) {
        User user = findUserById(user_id);
        Post post = findPostById(post_id);
        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return new CommentResponse("SUCCESS", LocalDateTime.now(), comment);
    }

    @Override
    public LikeResponse liked(int user_id, int post_id, LikeDTO likeDTO) {
        Like like = new Like();
        Like duplicateLike = likeRepository.findLikeByUserIdAndPostId(user_id, post_id);
        LikeResponse likeResponse = null;
        if (duplicateLike == null) {
            User user = findUserById(user_id);
            Post post = findPostById(post_id);
            like.setLiked(likeDTO.isLiked());
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
            List<Like> likeList = likeRepository.likeList(post_id);
            likeResponse = new LikeResponse("SUCCESS", LocalDateTime.now(), like, likeList.size());
        }else {
            likeRepository.delete(duplicateLike);
            throw new PostAlreadyLikedException("This post has been liked. It is now unliked (");
        }




        return null;
    }

    @Override
    public SearchCommentResponse searchComment(String keyword) {
        List<Comment> commentList = commentRepository.findByCommentContaining(keyword);
        return new SearchCommentResponse("SUCCESS", LocalDateTime.now(), commentList);
    }

    @Override
    public SearchPostResponse searchPost(String keyword) {
        List<Post> postList = postRepository.findPostByTitleContaining(keyword);
        return new SearchPostResponse("SUCCESS", LocalDateTime.now(), postList);
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + "not found"));
    }

    public Post findPostById(int id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post with id: " + id + "not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + "not found"));
    }

    public String makeSlug(String input) {
        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("-");
        String nomalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(nomalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

}
