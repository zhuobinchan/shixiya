package com.ehighsun.shixiya.trainer.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ehighsun.shixiya.pojo.BProductTeacher;
import com.ehighsun.shixiya.pojo.TrainWEProduct;
import com.ehighsun.shixiya.pojo.TrainWETeacher;
import com.ehighsun.shixiya.service.TrainWEService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TrainerTeacherAction extends ActionSupport {
	
	private TrainWETeacher trainer;
	private List<TrainWEProduct> trainWeProducts;
	private TrainWEProduct trainWeProduct;
	private Integer trainWeId;
	
	Map<String, Object> session = ActionContext.getContext().getSession();
	@Resource(name = "trainWEService")
	private TrainWEService trainWEService;
	
	public String getWeCourseByTrainer(){
		
		try {
			trainer = (TrainWETeacher) session.get("trainer");
			trainWeProducts = trainWEService
					.findTrainWeProductByTrainer(trainer);

			return "getWeCourseByTrainer";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	
	}

//	public String getWeCourseById() {
//		try {
//			trainWeProduct = trainWEService.findTrainWeProductById(trainWeId);
//			return "getWeCourseById";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//	}


	public TrainWETeacher getTrainer() {
		return trainer;
	}



	public void setTrainer(TrainWETeacher trainer) {
		this.trainer = trainer;
	}



	public List<TrainWEProduct> getTrainWeProducts() {
		return trainWeProducts;
	}



	public void setTrainWeProducts(List<TrainWEProduct> trainWeProducts) {
		this.trainWeProducts = trainWeProducts;
	}



	public TrainWEProduct getTrainWeProduct() {
		return trainWeProduct;
	}



	public void setTrainWeProduct(TrainWEProduct trainWeProduct) {
		this.trainWeProduct = trainWeProduct;
	}



	public Integer getTrainWeId() {
		return trainWeId;
	}



	public void setTrainWeId(Integer trainWeId) {
		this.trainWeId = trainWeId;
	}
}
