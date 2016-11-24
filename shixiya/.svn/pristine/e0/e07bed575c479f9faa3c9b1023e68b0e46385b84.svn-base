package com.ehighsun.shixiya.weixin.quartz;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.BProductComment;
import com.ehighsun.shixiya.pojo.CProductComment;
import com.ehighsun.shixiya.pojo.HrComment;
import com.ehighsun.shixiya.weixin.pojo.Token;

@Component("UpdateDataFromWXJob")
public class UpdateDataFromWXJob {
	private static Token token;
	@Resource(name = "baseDao")
	private BaseDao<CProductComment> cpCommentDao;
	@Resource(name = "baseDao")
	private BaseDao<BProductComment> bpCommentDao;
	@Resource(name = "baseDao")
	private BaseDao<HrComment> hrCommentDao;

	// @Scheduled(cron = "0/10 * * * * ? ")
	// public void job() {
	// token = TokenThread.token;
	// if (null != token) {
	// System.out.println("UpdateDataFromWXThread 获取 accessToken:"
	// + token.getAccessToken());
	// List<CProductComment> cpComments = cpCommentDao
	// .find("select new CProductComment(id,content,name,type,commentTime,headUrl,role,localUrl) from CProductComment where type <> 1 and localUrl is null");
	// List<BProductComment> bpComments = bpCommentDao
	// .find("select new BProductComment(id,content,name,type,commentTime,headUrl,role,localUrl) from BProductComment where type <> 1 and localUrl is null");
	// List<HrComment> hrComments = hrCommentDao
	// .find("select new HrComment(id,content,type,hrId,commentTime,headUrl,name,role,localUrl) from HrComment where type <> 1 and localUrl is null");
	// if (cpComments.size() != 0) {
	// for (CProductComment cpComment : cpComments) {
	//
	// }
	// System.out.println(cpComments.size());
	// }
	// System.out.println(cpComments.size());
	// if (bpComments.size() != 0) {
	// for (BProductComment bpComment : bpComments) {
	//
	// }
	// System.out.println(bpComments.size());
	// }
	// System.out.println(bpComments.size());
	// if (hrComments.size() != 0) {
	// for (HrComment hrComment : hrComments) {
	//
	// }
	// System.out.println(hrComments.size());
	// }
	// System.out.println(hrComments.size());
	// }
	// }

}
