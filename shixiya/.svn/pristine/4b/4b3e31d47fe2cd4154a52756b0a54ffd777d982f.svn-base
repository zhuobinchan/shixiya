package jiabin.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jiabin.entity.Zone;

import com.ehighsun.shixiya.pojo.PageBean;

public interface ZoneService {

	@Transactional
	public void saveZone(Zone zone);
	@Transactional
	public void deleteZone(Zone zone);
	@Transactional
	public List<Zone> findZoneList(Zone s_zone,PageBean pageBean);
	@Transactional
	public Long getZoneCount(Zone s_zone);
	@Transactional
	public Zone findZoneById(int zoneId);
	public List<Zone> findAllZoneList();
}
