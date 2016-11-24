package jiabin.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jiabin.entity.Reply;

import com.ehighsun.shixiya.pojo.PageBean;
@Transactional
public interface ReplyService {

	public Reply findLastReplyByTopicId(int topicId);
	
	public Long getReplyCountByTopicId(int topicId);
	
	public List<Reply> findReplyListByTopicId(int topicId, PageBean pageBean);
	
	
	
	public void saveReply(Reply reply);
	
	public void deleteReply(Reply reply);
	
	public Reply findReplyById(int replyId);
}
