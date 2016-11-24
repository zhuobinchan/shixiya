package com.ehighsun.shixiya.service.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.CProductBroadcast;
import com.ehighsun.shixiya.pojo.CProductHR;
import com.ehighsun.shixiya.pojo.CProductStudentApplyId;
import com.ehighsun.shixiya.pojo.Lable;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.Student;
import com.ehighsun.shixiya.service.CProductBroadcastService;
import com.ehighsun.shixiya.util.FileUploadUtil;
import com.ehighsun.shixiya.util.PageUtil;
import com.ehighsun.shixiya.util.StringUtil;

@Service("cProductBroadcastService")
public class CProductBroadcastServiceImpl implements CProductBroadcastService {

	@Resource(name = "baseDao")
	private BaseDao<CProductStudentApplyId> cpsApplyIdDao;
	@Resource(name = "baseDao")
	private BaseDao<CProductBroadcast> baseDao;
	@Resource(name = "baseDao")
	private BaseDao<Lable> lableDao;
	@Resource(name = "baseDao")
	private BaseDao<CProductHR> hrDao;

	@Override
	public List<CProductBroadcast> findByCondition(String name, Integer hrId,
			HttpServletRequest request, String page) {
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 6);

		Long count = baseDao.countByMap(
				"select count(*) from CProductBroadcast",
				getConditionByMap(name, hrId));

		String pageCode = PageUtil.genPagination(request.getContextPath()
				+ "/admin/findByName_AdminCPBroadcastAction.action", count,
				Integer.parseInt(page), 6, "broadcast.name=" + name
						+ "&broadcast.CProductHR.hrId="
						+ (hrId == null ? "" : hrId));

		request.getSession().removeAttribute("pageCode");
		request.getSession().setAttribute("pageCode", pageCode);

		return baseDao.findPageByMap("from CProductBroadcast",
				getConditionByMap(name, hrId), pageBean);
	}

	private Map<String, Object> getConditionByMap(String name, Integer hrId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(name)) {
			System.out.println(name + "//////////");
			map.put("name", name);
		}
		if (StringUtil.isNotEmpty(hrId)) {
			System.out.println(hrId + "//////////");
			map.put("CProductHR", hrDao.get(CProductHR.class, hrId));
		}
		return map;
	}

	@Override
	public List<CProductBroadcast> getAllCPBroadcast() {

		return baseDao.find("from CProductBroadcast order by stick desc, publicTime desc");

	}

	@Override
	public List<CProductBroadcast> getAllCPBroadcast(PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find("from CProductBroadcast order by publicTime desc",
				new Object[] {}, pageBean);
	}

	@Override
	public void saveOrUpdateCPBroadcast(CProductBroadcast cpBroadcast) {

		if (StringUtil.isNotEmpty(cpBroadcast.getId())) {
			CProductBroadcast broadcast = baseDao.get(CProductBroadcast.class,
					cpBroadcast.getId());

			String oldImgUrl = ServletActionContext.getRequest().getRealPath(
					broadcast.getImgUrl());
			String oldVedioUrl = ServletActionContext.getRequest().getRealPath(
					broadcast.getVideoUrl());
			baseDao.evict(broadcast);

			FileUploadUtil.deleteFile(oldImgUrl);
			FileUploadUtil.deleteFile(oldVedioUrl);
		}

		baseDao.saveOrUpdate(cpBroadcast);

	}

	@Override
	public void saveCPBroadcast(CProductBroadcast cpBroadcast) {

		baseDao.save(cpBroadcast);

	}

	@Override
	public void deleteCPBroadcastById(Integer id) {
		CProductBroadcast index = baseDao.get(CProductBroadcast.class, id);
		System.out.println(index.getId());
		
	
		if(StringUtil.isNotEmpty(index.getImgUrl())){
				
//			String resUrl = index.getImgUrl().replace("http://sxyres.000861.com/", "");
			
			String pathname = ServletActionContext.getRequest().getSession().getServletContext()
					.getRealPath("/"+index.getImgUrl());
			
			FileUploadUtil.deleteFile(pathname);
				
		}
		if(StringUtil.isNotEmpty(index.getVideoUrl())){
				
			String resUrl = index.getVideoUrl().replace("http://sxyres.000861.com/", "");
			FileUploadUtil.DelRemoteResource(resUrl);
				
		}

		
		baseDao.executeHql("delete from CProductBroadcast where 直播间ID = " + id + " order by publicTime desc");

	}

	@Override
	public void deleteBroadcastList(String ids) {
		String[] idList = ids.split(",");
		for (String string : idList) {
			System.out.println(string);
			
			CProductBroadcast index = baseDao.get(CProductBroadcast.class, Integer.parseInt(string));
			
			if(StringUtil.isNotEmpty(index.getImgUrl())){
				
				String pathname = ServletActionContext.getRequest().getSession().getServletContext()
						.getRealPath("/"+index.getImgUrl());
				
				FileUploadUtil.deleteFile(pathname);
					
			}
			if(StringUtil.isNotEmpty(index.getVideoUrl())){
					
				String resUrl = index.getVideoUrl().replace("http://sxyres.000861.com/", "");
				FileUploadUtil.DelRemoteResource(resUrl);
					
			}
			
			baseDao.delete(index);
		}

	}

	@Override
	public CProductBroadcast findById(int id) {
		return baseDao.get(CProductBroadcast.class, id);
	}

	@Override
	/* 判断某课程是否报名或报名成功 */
	public String judgeApplyStatus(int studentId, int broadcastId) {

		String sql = "select 审核状态  from c组团报名表  " + " where 学生ID='" + studentId
				+ "'" + " && 直播ID='" + broadcastId + "'";

		List result = baseDao.executeOurSql(sql);

		if (result == null || result.size() == 0) {
			return "noApply";
		} else if (result.get(0).toString().equals("0")) {
			return "waitingApply";
		} else if (result.get(0).toString().equals("1")) {
			return "sccessApply";
		} else if (result.get(0).toString().equals("2")) {
			return "failApply";
		} else if (result.get(0).toString().equals("3")){
			return "applyWithoutResume";
		}

		return "noApply";
	}

	@Override
	public List<CProductBroadcast> findBroadcastsByLable(String lableName) {

		String hql = "from CProductBroadcast where lable like '%" + lableName
				+ "%' order by publicTime desc";

		List<CProductBroadcast> lists = baseDao.find(hql);

		return lists;
	}

	@Override
	public List<CProductBroadcast> getCProductBroadcastByStudent(Student student) {
		List<CProductStudentApplyId> cpsaId = cpsApplyIdDao.find(
				"from CProductStudentApplyId where telephone = ? order by publicTime desc",
				new Object[] { student.getTelephone() });

		List<CProductBroadcast> result = new ArrayList<>();
		for (CProductStudentApplyId cProductStudentApplyId : cpsaId) {
			result.add(cProductStudentApplyId.getCProductBroadcast());
		}
		return result;
	}

	@Override
	public List<CProductBroadcast> getBroadcastByLableNames(String lableNames) {
		lableNames = lableNames.replaceAll(" ", "");
		String[] lalbes = StringUtil.ToArray(lableNames);
		StringBuffer hql = new StringBuffer();
		hql.append("from CProductBroadcast where 1=0  ");

		for (String string : lalbes) {
			if (StringUtil.isNotEmpty(string)) {
				hql.append("OR lable like '%").append(string).append("%'");
			}

		}
		return baseDao.find(hql.toString());
	}

	@Override
	public List<CProductBroadcast> getCPBroadcastByHrId(Integer hrId) {
		return baseDao.find("from CProductBroadcast where HRID = " + hrId +" order by publicTime desc");
	}

	@Override
	public Long countBroadcastMap(String name, Integer hrId) {
		return baseDao.countByMap("select count(*) from CProductBroadcast",
				getConditionByMap(name, hrId));

	}

	@Override
	public Long countBroadcast(String hql) {
		return baseDao.count(hql);
	}

}
