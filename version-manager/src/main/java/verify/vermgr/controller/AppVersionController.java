package verify.vermgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/apps/{appId}")
public class AppVersionController {

	@RequestMapping(value = "/nextversion/{scope}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8") //
	public @ResponseBody
	String getNextAppVersion(@PathVariable String appId, @PathVariable String scope) {
		return appId + "-" + scope + "-1.0.0";
	}
	
	
	
	
	
	

}
