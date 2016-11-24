package com.ehighsun.shixiya.teacher.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ehighsun.shixiya.pojo.BProductCourse;
import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.service.BProductChapterService;
import com.ehighsun.shixiya.service.BProductCourseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BPTeacherAction extends ActionSupport {
	private BProductTeacher teacher;
	private List<BProductCourse> bpCourses;
	private BProductCourse bpCourse;
	private Integer courseId;

	Map<String, Object> session = ActionContext.getContext().getSession();
	@Resource(name = "bProductCourseService")
	private BProductCourseService bProductCourseService;
	@Resource(name = "bProductChapterService")
	private BProductChapterService bProductChapterService;

	public BProductTeacher getTeacher() {
		return teacher;
	}

	public void setTeacher(BProductTeacher teacher) {
		this.teacher = teacher;
	}

	public List<BProductCourse> getBpCourses() {
		return bpCourses;
	}

	public void setBpCourses(List<BProductCourse> bpCourses) {
		this.bpCourses = bpCourses;
	}

	public BProductCourse getBpCourse() {
		return bpCourse;
	}

	public void setBpCourse(BProductCourse bpCourse) {
		this.bpCourse = bpCourse;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseByTeacher() {
		try {
			teacher = (BProductTeacher) session.get("bpTeacher");
			System.out.println(teacher.getId());
			bpCourses = bProductCourseService
					.findBProductCoursesByTeacher(teacher);

			return "getCourseByTeacher";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String getCourseById() {
		try {
			bpCourse = bProductCourseService.findBCourseById(courseId);
			return "getCourseById";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String findBpStuApply(){
		
		return null;
	}

}
