package jiabin.dao;

import java.io.Serializable;

import java.util.List;

import com.ehighsun.shixiya.pojo.PageBean;



/**
 * ����ݿ������
 * 
 * @author ss
 * 
 */
public interface BaseDAO<T> {

	/**
	 * ����һ������
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(T o);

	/**
	 * ɾ��һ������
	 * 
	 * @param o
	 */
	public void delete(T o);

	/**
	 * ����һ������
	 * 
	 * @param o
	 */
	public void update(T o);

	/**
	 * �������¶���
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o);
	
	/**
	 * �ϲ�����
	 * @param o
	 */
	public void merge(T o);

	/**
	 * ��ѯ
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * ��ѯ����
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 * ��ѯ����
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * ��ѯ����(���ҳ)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 *            ��ѯ�ڼ�ҳ
	 * @param rows
	 *            ÿҳ��ʾ������¼
	 * @return
	 */
	public List<T> find(String hql, Object[] param, PageBean pageBean);

	/**
	 * ��ѯ����(���ҳ)
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, List<Object> param, PageBean pageBean);

	/**
	 * ���һ������
	 * 
	 * @param c
	 *            ��������
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * ���һ������
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param);

	/**
	 * ���һ������
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public T get(String hql, List<Object> param);

	/**
	 * select count(*) from ��
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * select count(*) from ��
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, Object[] param);

	/**
	 * select count(*) from ��
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, List<Object> param);

	/**
	 * ִ��HQL���
	 * 
	 * @param hql
	 * @return ��Ӧ��Ŀ
	 */
	public Integer executeHql(String hql);

	/**
	 * ִ��HQL���
	 * 
	 * @param hql
	 * @param param
	 * @return ��Ӧ��Ŀ
	 */
	public Integer executeHql(String hql, Object[] param);

	/**
	 * ִ��HQL���
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, List<Object> param);

	/**
	 * ִ��SQL���
	 * @param sql
	 * @return
	 */
	public Integer executeSql(String sql);
	
	public List<T> findTopN(String hql, List<Object> param, int N);
	
}
