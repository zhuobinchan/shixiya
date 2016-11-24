package com.ehighsun.shixiya.thirdparty;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ehighsun.shixiya.pojo.PreferenceSelect;
import com.ehighsun.shixiya.pojo.PreferenceSelectEntered;
import com.ehighsun.shixiya.service.PreferenceSelectEnteredService;
import com.ehighsun.shixiya.service.PreferenceSelectService;
import com.ehighsun.shixiya.util.SendEmailUtil;

@Component("sendEmailToCompany")
public class SendEmailToCompany {

	@Resource(name = "preferenceSelectServiceImpl")
	private PreferenceSelectService preferenceSelectService;
	@Resource(name = "preferenceSelectEnteredServiceImpl")
	private PreferenceSelectEnteredService preferenceSelectEnteredService;

	// @Scheduled(cron = "0 0 12 * * ?")
	public void run() {

		List<PreferenceSelect> lists = preferenceSelectService
				.findAllPreferenceSelect();
		if (lists != null) {
			for (int i = 0; i < lists.size(); i++) {

				List<PreferenceSelectEntered> noSends = preferenceSelectEnteredService
						.findByNoSendEmail(lists.get(i).getId());

				String text = "";
				if (noSends != null) {
					// 拼text;

					SendEmailUtil.sendEmailByHtml(lists.get(i).getWorkEmail(),
							"海印股份", text);
					changeSendState(noSends);
				}

			}
		}

	}

	public void changeSendState(List<PreferenceSelectEntered> noSends) {

		for (int q = 0; q < noSends.size(); q++) {

			PreferenceSelectEntered entered = noSends.get(q);
			entered.setEmailState(1);
			preferenceSelectEnteredService
					.updatePreferenceSelectEntered(entered);

		}

	}
}
