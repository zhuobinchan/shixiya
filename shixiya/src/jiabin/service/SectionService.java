package jiabin.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jiabin.entity.Section;

import com.ehighsun.shixiya.pojo.PageBean;

public interface SectionService {
	@Transactional
	public void saveSection(Section section);
	@Transactional
	public void deleteSection(Section section);
	@Transactional
	public List<Section> findSectionList(Section s_section,PageBean pageBean);
	
	@Transactional
	public Long getSectionCount(Section s_section);
	@Transactional
	public Section findSectionById(int sectionId);
	List<Section> findSection(PageBean pageBean, Integer zoneId);
	
	
}
