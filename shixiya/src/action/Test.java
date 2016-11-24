package action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ehighsun.shixiya.dao.BaseDao;
import com.ehighsun.shixiya.pojo.HrComment;

public class Test {

	private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	static BaseDao<HrComment> hrCommentDao = (BaseDao<HrComment>) ctx
			.getBean("baseDao");

	public static void main(String[] args) throws ParseException {
		System.out.println(hrCommentDao.find("From HrComment").get(0)
				.getHrCommentList().getCProductHR().getName());

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try

		{
			
			Date d1 = df.parse(df.format(new Date()));
//			System.out.println(df.format(new Date()));
			Date d2 = df.parse("2016-08-04 18:00:00");
			long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);

			long hours = (diff - days * (1000 * 60 * 60 * 24))
					/ (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
					* (1000 * 60 * 60))
					/ (1000 * 60);
			System.out.println(diff+"秒" + days + "天" + hours + "小时" + minutes + "分");

		} catch (Exception e) {
		}
	}

}
