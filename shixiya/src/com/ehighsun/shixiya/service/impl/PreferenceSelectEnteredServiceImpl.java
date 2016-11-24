package com.ehighsun.shixiya.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.PageBean;
import com.ehighsun.shixiya.pojo.PreferenceSelect;
import com.ehighsun.shixiya.pojo.PreferenceSelectEntered;
import com.ehighsun.shixiya.service.PreferenceSelectEnteredService;

@Service("preferenceSelectEnteredServiceImpl")
public class PreferenceSelectEnteredServiceImpl implements
		PreferenceSelectEnteredService {

	@Resource(name = "baseDao")
	private BaseDao<PreferenceSelectEntered> baseDao;

	@Override
	public void addPreferenceSelectEntered(PreferenceSelectEntered pse) {
		// TODO Auto-generated method stub
		baseDao.save(pse);
	}

	@Override
	public void deletePreferenceSelectEntered(Integer id) {
		// TODO Auto-generated method stub
		PreferenceSelectEntered pse = baseDao.get(
				PreferenceSelectEntered.class, id);
		if (pse != null) {
			baseDao.delete(pse);
		}

	}

	@Override
	public void deletePreferenceSelectEntereds(String ids) {

		String[] psEntered = ids.split(",");
		for (int i = 0; i < psEntered.length; i++) {
			PreferenceSelectEntered pse = baseDao.get(
					PreferenceSelectEntered.class,
					Integer.parseInt(psEntered[i]));
			if (pse != null) {
				baseDao.delete(pse);
			}
		}
	}

	@Override
	public void updatePreferenceSelectEntered(PreferenceSelectEntered pse) {
		// TODO Auto-generated method stub
		baseDao.update(pse);
	}

	@Override
	public PreferenceSelectEntered getPreferenceSelectEnteredById(Integer id) {
		// TODO Auto-generated method stub
		return baseDao.get(PreferenceSelectEntered.class, id);
	}

	@Override
	public List<PreferenceSelectEntered> findPreferenceSelectEntereds(
			String hql, Object[] param, PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.find(hql, param, pageBean);
	}

	@Override
	public Long countPreferenceSelectEntered(String hql) {
		// TODO Auto-generated method stub
		return baseDao.count(hql);
	}

	@Override
	public List<PreferenceSelectEntered> findByCondition(String hql,
			Map<String, Object> o, PageBean pageBean) {
		// TODO Auto-generated method stub
		return baseDao.findPageByMap(hql, o, pageBean);
	}

	@Override
	public Long countByCondition(String hql, Map<String, Object> o) {
		// TODO Auto-generated method stub
		return baseDao.countByMap(hql, o);

	}

	@Override
	public List<PreferenceSelectEntered> findByPreferenceSelectId(Integer id) {
		// TODO Auto-generated method stub
		PreferenceSelect pSelect = new PreferenceSelect();
		pSelect.setId(id);
		return baseDao.find(
				"from PreferenceSelectEntered where preferenceSelect = ?",
				new Object[] { pSelect });
	}

	@Override
	public List<PreferenceSelectEntered> findByNoSendEmail(Integer id) {
		// TODO Auto-generated method stub
		PreferenceSelect pSelect = new PreferenceSelect();
		pSelect.setId(id);
		return baseDao
				.find("from PreferenceSelectEntered where preferenceSelect = ? and emailState = ?",
						new Object[] { pSelect, 0 });
	}

	@Override
	public List<PreferenceSelectEntered> findByPSelectId(Integer id) {
		// TODO Auto-generated method stub
		PreferenceSelect pSelect = new PreferenceSelect();
		pSelect.setId(id);
		return baseDao.find(
				"from PreferenceSelectEntered where preferenceSelect = ?",
				new Object[] { pSelect });
	}

}
