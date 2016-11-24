package com.ehighsun.shixiya.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jiabin.entity.Section;
import jiabin.entity.User;
import jiabin.entity.Zone;
import jiabin.service.SectionService;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.Administer;
import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.pojo.HrComment;
import com.ehighsun.shixiya.pojo.HrCommentList;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.RoleInfoTransform;
import com.ehighsun.shixiya.service.HrAdminServer;
import com.ehighsun.shixiya.service.RoleService;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.StringUtil;

@Service("HrAdminServer")
public class HrAdminServerImpl implements HrAdminServer {

	@Resource(name = "baseDao")
	BaseDao<HrCommentList> hrCommentListDao;
	@Resource(name = "baseDao")
	BaseDao<HrComment> hrCommentDao;
	@Resource(name = "baseDao")
	BaseDao<CProductHR> hrBaseDao;
	@Resource(name = "baseDao")
	BaseDao<Lable> lableDao;
	@Resource(name="sectionService")
	private SectionService sectionService;
	@Resource(name="roleService")
	private RoleService roleService;

	private FileUploadUtil fileUploadUtil = new FileUploadUtil();

	@Override
	@Transactional
	/* 筛选功能，通过标题title和HrId，返回对应的Hr周点评列表 */
	public List<HrCommentList> hrCommentListFilter(HttpServletRequest request,String title, String hrId,
			String startTime,String page) {
		String hql1 = "From HrCommentList where 标题 like '%" + title
				+ "%' and HRID='" + hrId + "' ";
		String hql2 = "From HrCommentList where 标题 like '%" + title + "%'";
		String hql;

		hql = hrId.equals("") ? hql2 : hql1;
		
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);
		
		Long count = hrCommentListDao.count("select count(*) from HrCommentList");
		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/hrCommentFilter_hr.action", count,
				Integer.parseInt(page), 6, "title="+title+"&hrId="+hrId);
		
		request.getSession().setAttribute("pageCode", pageCode);
		

		return hrCommentListDao.find(hql, new Object[]{}, pageBean);
	}

	
	
	@Override
	@Transactional
	public void delHrcommentList(String id) {
		
		HrCommentList hrCommentList = hrCommentListDao.get(HrCommentList.class, id);
		
		String resUrl = hrCommentList.getVideoUrl().replace("http://sxyres.000861.com/", "");
		
		FileUploadUtil.DelRemoteResource(resUrl);
		
		String sql = "delete from 周点评列表 where 点评列表ID='" + id + "'";
		hrCommentListDao.executeSql(sql);
	}

	@Override
	@Transactional
	public void delGroupHrcommentList(String idsString) {
		String[] ids = idsString.split(",");
		for (String id : ids) {

			HrCommentList hrCommentList = hrCommentListDao.get(HrCommentList.class, id);
			
			String resUrl = hrCommentList.getVideoUrl().replace("http://sxyres.000861.com/", "");
			
			FileUploadUtil.DelRemoteResource(resUrl);
			
			String sql = "delete from 周点评列表 where 点评列表ID='" + id + "'";
			hrCommentListDao.executeSql(sql);
		}
	}

	@Override
	public List<HrCommentList> getAll() {

		return hrCommentListDao.find("from HrCommentList");
	}

	@Override
	public List<HrCommentList> findHrCommentListByLable(int lableId) {
		String hql = "from HrCommentList where lable = ?";

		Lable lable = lableDao.get(Lable.class, lableId);

		List<HrCommentList> lists = hrCommentListDao.find(hql,
				new Object[] { lable });

		return lists;
	}

	@Override
	public List<HrComment> getHrCommentsByHrid(int hrId) {

		String hql = "From HrComment where hrId=?";

		return hrCommentDao.find(hql, new Object[] { hrId });

	}

	@Override
	public List<HrCommentList> getHrCommentListsByHrId(Integer hrId) {

		String hql = "From HrCommentList where HRID=?";

		return hrCommentListDao.find(hql, new Object[] { hrId });

	}

	@Override
	public void saveHrcommentList(String title, String hrId,
			String introduction, File img, String imgFileName, File video,
			String videoFileName, Integer sectionId,Integer visitnum,Integer state,Integer mode) {

		HrCommentList hrCommentList = new HrCommentList();
		CProductHR hr = hrBaseDao.get(CProductHR.class, Integer.parseInt(hrId));

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String publicTime = df.format(new Date());

		hrCommentList.setTitle(title);
		hrCommentList.setIntroduction(introduction);
		hrCommentList.setCProductHR(hr);
		hrCommentList.setPublicTime(publicTime);
		if(imgFileName != null) hrCommentList.setImageUrl(fileUploadUtil.updateFile(img, imgFileName,"images"));
		if(videoFileName != null) hrCommentList.setVideoUrl(fileUploadUtil.updateSmbFile(video,videoFileName,"media"));
		hrCommentList.setVisitnum(visitnum);
		hrCommentList.setState(state);
		hrCommentList.setMode(mode);
		
		hrCommentListDao.save(hrCommentList);
		if(sectionId!=null){
			
			saveSection(sectionId, title,img==null?"": fileUploadUtil.updateFile(img, imgFileName,"images"), introduction);
		}
	}

	@Override
	public void updateHrcommentList(String id, String title, String hrId,
			String introduction, File img, String imgFileName, File video,
			String videoFileName, Integer sectionId,Integer visitnum,Integer state,Integer mode) {

		HrCommentList hrCommentList = hrCommentListDao.get(HrCommentList.class,
				Integer.parseInt(id));

		CProductHR hr = hrBaseDao.get(CProductHR.class, Integer.parseInt(hrId));

		hrCommentList.setTitle(title);
		hrCommentList.setIntroduction(introduction);
		hrCommentList.setCProductHR(hr);
		if(imgFileName != null)	{
			
			String pathname = ServletActionContext.getRequest().getSession().getServletContext()
					.getRealPath("/"+hrCommentList.getImageUrl());
			
			FileUploadUtil.deleteFile(pathname);
			
			hrCommentList.setImageUrl(fileUploadUtil.updateFile(img, imgFileName,"images"));
		}
		if(videoFileName != null){
			
			String resUrl = hrCommentList.getVideoUrl().replace("http://sxyres.000861.com/", "");
			
			FileUploadUtil.DelRemoteResource(resUrl);
			
			hrCommentList.setVideoUrl(fileUploadUtil.updateSmbFile(video,videoFileName,"media"));
		}
		hrCommentList.setVisitnum(visitnum);
		hrCommentList.setState(state);
		hrCommentList.setMode(mode);
		
		hrCommentListDao.update(hrCommentList);
		
		if(sectionId!=null && imgFileName != null){
			saveSection(sectionId, title, fileUploadUtil.updateFile(img, imgFileName,"images"), introduction);
		}
	}
	
	public void saveSection(Integer sectionId,String title,String logo,String introduction){
		
		Section section = new Section();
		
		Zone zone = new Zone();
		zone.setId(sectionId);
		
		Administer admin = (Administer) this.getSession().getAttribute("user");
	    RoleInfoTransform role = roleService.findByRoseId(admin.getId()+"", 4);
	    User u = new User();
	    u.setId(role.getUserId());
	    
		section.setMaster(u);
		section.setName(title);
		section.setLogo(logo);
		section.setZone(zone);
		Date date = new Date();
		section.setPublishTime(date);
		section.setIntroduction(introduction);
		section.setLookCount(0);
		section.setPraiseCount(0);
		
		sectionService.saveSection(section);
	}
	
	public HttpSession getSession(){
		
		return ServletActionContext.getRequest().getSession();
	}

}
