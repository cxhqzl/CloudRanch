package net.cloudranch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.cloudranch.utils.PageEnums;

@Controller
public class ChangePageController {
	/**
	 * ҳ����ת����
	 * @param pageName
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/skipPage")
	public ModelAndView changePage(@RequestParam("pageName") String pageName,ModelAndView mv) throws Exception {
		mv.setViewName(PageEnums.valueOf("error").getName());//Ĭ�Ϸ���404
		try {
			if(PageEnums.contains(pageName)) {
				mv.setViewName(PageEnums.valueOf(pageName).getName());
			}
		}catch(Exception e) {
			
		}
		return mv;
	}
	
}
