package com.blogapplication.blogapplication.blog.serviceImpl.serviceMethods;

import com.blogapplication.blogapplication.authentication.dto.ResponseDto;
import com.blogapplication.blogapplication.blog.dto.request.ReactBlogRequestDto;
import com.blogapplication.blogapplication.blog.entity.Blog;
import com.blogapplication.blogapplication.blog.entity.BlogReactionDetails;
import com.blogapplication.blogapplication.blog.enums.Reaction;
import com.blogapplication.blogapplication.blog.repositoty.BlogReactionDetailsRepository;
import com.blogapplication.blogapplication.blog.repositoty.BlogRepository;
import com.blogapplication.blogapplication.blog.repositoty.BlogViewDetailsRepository;
import com.blogapplication.blogapplication.blog.repositoty.CommentRepository;
import com.blogapplication.blogapplication.common.exceptiom.ServiceException;
import com.blogapplication.blogapplication.common.utility.AuthenticationUtil;
import com.blogapplication.blogapplication.kafka.Producer.KafkaProducer;
import com.blogapplication.blogapplication.kafka.common.BlogActivityProducer;
import com.blogapplication.blogapplication.kafka.common.UserActivityProducer;
import com.blogapplication.blogapplication.kafka.dto.BlogLikeLogDto;
import com.blogapplication.blogapplication.kafka.enums.BlogActivity;
import com.blogapplication.blogapplication.kafka.enums.UserActivity;
import com.blogapplication.blogapplication.user.entity.User;
import com.blogapplication.blogapplication.user.serviceImpl.serviceMethods.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReactBlog {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private Environment environment;


    @Autowired
    private AuthenticationUtil authenticationUtil;


    @Autowired
    private BlogReactionDetailsRepository blogReactedDetailsRepository;
    @Autowired
    private BlogViewDetailsRepository blogViewDetailsRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ValidateRequest validateRequest;

    @Autowired
    private LoggedInUser loggedInUser;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private BlogActivityProducer blogActivityProducer;

    @Autowired
    private UserActivityProducer userActivityProducer;

    public ResponseDto reactABlog(ReactBlogRequestDto request){

        validateRequest.validateIncomingRequest(request);

        User user = loggedInUser.getLoggedInUser();

        Blog existedBlog = blogRepository.findByIdAndStatus(request.getId(),Integer.parseInt(Objects.requireNonNull(environment.getProperty("active")))).orElseThrow(() -> new ServiceException("BLOG_NOT_FOUND"));

        Optional<BlogReactionDetails> reactionOptional = blogReactedDetailsRepository.findByBlogIdAndReactedById(request.getId(), user.getId());

        BlogReactionDetails reaction = new BlogReactionDetails();

        if(reactionOptional.isEmpty()){
            reaction = new BlogReactionDetails();
            reaction.setBlog(existedBlog);
            reaction.setReactedBy(user);
            reaction.setReactedAt(LocalDateTime.now(ZoneId.of("UTC")));
            reaction.setIsReacted(request.isReacted());
        }else {
            reaction = reactionOptional.get();

        }


        if(request.isReacted() && !Objects.isNull(request.getReactionValue())){

            switch (request.getReactionValue()){

                case 1 : {
                    reaction.setReaction(Reaction.LIKE);
                    break;
                }
                case 2 : {
                    reaction.setReaction(Reaction.LOVE);
                    break;
                }
                case 3 : {
                    reaction.setReaction(Reaction.SUPPORT);
                    break;
                }
                case 4 : {
                    reaction.setReaction(Reaction.FUNNY);
                    break;
                }
                default: {
                    reaction.setReaction(null);
                }


            }
            reaction.setIsReacted(true);
        }else {

            if(!reaction.getIsReacted()){
                throw new ServiceException("NOT_REACTED");
            }
            reaction.setReaction(null);
            reaction.setIsReacted(false);
        }

        reaction.setReactedAt(LocalDateTime.now(ZoneId.of("UTC")));
        blogReactedDetailsRepository.save(reaction);

        BlogLikeLogDto blogLikeLogDto = BlogLikeLogDto.builder()
                .blogId(existedBlog.getId())
                .likedBy(user.getId())
                .likedAt(LocalDateTime.now())
                .build();

        kafkaProducer.sendMessage(blogLikeLogDto,"blog-like-details");
        blogActivityProducer.sendBlogActivity(existedBlog.getId(), user.getId(), BlogActivity.LIKE.getValue());
        userActivityProducer.sendUserActivity(user.getId(), UserActivity.LIKE_BLOG.getValue());

        ResponseDto responseDto = new ResponseDto();
        responseDto.setData(environment.getProperty("reactionAdded"));
        responseDto.setMessage(environment.getProperty("successResponse"));
        responseDto.setStatus(true);

        return responseDto;
    }
}
