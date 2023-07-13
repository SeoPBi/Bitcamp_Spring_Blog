package com.spring.blog.service;

import com.spring.blog.entity.Reply;
import com.spring.blog.repository.ReplyJPARepository;
import com.spring.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{

    ReplyRepository replyRepository;

    ReplyJPARepository replyJPARepository;

    @Autowired
    public ReplyServiceImpl(ReplyRepository replyRepository,
                            ReplyJPARepository replyJPARepository){
        this.replyRepository = replyRepository;
        this.replyJPARepository = replyJPARepository;
    }

    @Override
    public List<Reply> findAllByBlogId(long blogId) {
        return replyJPARepository.findAllByBlogId(blogId);
    }

    @Override
    public Reply findByReplyId(long replyId) {
//        return replyRepository.findByReplyId(replyId);
        return replyJPARepository.findByReplyId(replyId);
    }
    

    @Override
    public void deleteByReplyId(long replyId) {
//        replyRepository.deleteByReplyId(replyId);
        replyJPARepository.deleteById(replyId);
    }

    @Override
    public void save(Reply reply) {
//        replyRepository.save(replyInsertDTO);
        replyJPARepository.save(reply);
    }

    @Override
    public void update(Reply reply) {
//        replyRepository.update(replyUpdateDTO);
        Reply updatedReply = replyJPARepository.findById(reply.getReplyId()).get();
        updatedReply.setReplyContent(reply.getReplyContent());
        updatedReply.setReplyWriter(reply.getReplyWriter());
        replyJPARepository.save(updatedReply);
    }
}
