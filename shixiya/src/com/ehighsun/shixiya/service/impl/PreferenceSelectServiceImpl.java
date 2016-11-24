package com.ehighsun.shixiya.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.PreferSelectImgs;
import com.ehighsun.shixiya.pojo.PreferenceSelect;
import com.ehighsun.shixiya.pojo.PreferenceSelectEntered;
import com.ehighsun.shixiya.service.PreferSelectImgsService;
import com.ehighsun.shixiya.service.PreferenceSelectEnteredService;
import com.ehighsun.shixiya.service.PreferenceSelectService;

@Service("preferenceSelectServiceImpl")
public class PreferenceSelectServiceImpl implements PreferenceSelectService {

	@Resource(name = "baseDao")
	private BaseDao<PreferenceSelect> baseDao;
	@Resource(name = "preferSelectImgsServiceImpl")
	private PreferSelectImgsService preferSelectImgsService;
	@Resource(name = "preferenceSelectEnteredServiceImpl")
	private PreferenceSelectEnteredService preferenceSelectEnteredService;

	@Override
	public void addPreferenceSelect(PreferenceSelect ps) {
		// TODO Auto-generated method stub
		baseDao.merge(ps);

	}

	@Override
	public void deletePreferenceSelect(Integer id) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		PreferenceSelect ps = baseDao.get(PreferenceSelect.class, id);

		if (ps != null && ps.getWorkLogo() != null) {
			String oldPath1 = request.getSession().getServletContext()
					.getRealPath(ps.getWorkLogo());
			File f1 = new File("/" + oldPath1);
			f1.delete();
		}
		// 删除图片
		List<PreferSelectImgs> imgs = preferSelectImgsService.findImgs(
				"from PreferSelectImgs where preferenceSelect.id = ?",
				new Object[] { ps.getId() });
		if (imgs != null) {
			for (int i = 0; i < imgs.size(); i++) {
				preferSelectImgsService.deletePreferSelectImg(imgs.get(i)
						.getId());
			}
		}
		// 删除报名
		List<PreferenceSelectEntered> entereds = preferenceSelectEnteredService
				.findByPSelectId(ps.getId());
		if (entereds != null) {
			for (int i = 0; i < entereds.size(); i++) {
				preferenceSelectEnteredService
						.deletePreferenceSelectEntered(entereds.get(i).getId());
			}

		}

		if (ps != null) {
			baseDao.delete(ps);
		}

	}

	@Override
	public void deletePreferenceSelects(String[] ids) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ids.length; i++) {
			this.deletePreferenceSelect(Integer.parseInt(ids[i]));
		}
	}

	@Override
	public void updatePreferenceSelect(PreferenceSelect ps) {
		// TODO Auto-generated method stub
		baseDao.update(ps);
	}

	@Override
	public PreferenceSelect getPreferenceSelectById(Integer id) {

		return baseDao.get(PreferenceSelect.class, id);
	}

	@Override
	public List<PreferenceSelect> findPreferenceSelects(String hql,
			List<Object> param, PageBean pageBean) {
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public Long countPreferenceSelect(String hql, List<Object> param) {

		return baseDao.count(hql, param);
	}

	@Override
	public List<PreferenceSelect> findAllPreferenceSelect() {
		// TODO Auto-generated method stub
		return baseDao.find("from PreferenceSelect");
	}

	@Override
	public void evitPreferenceSelect(PreferenceSelect preferenceSelect) {
		baseDao.evict(preferenceSelect);
	}

}
