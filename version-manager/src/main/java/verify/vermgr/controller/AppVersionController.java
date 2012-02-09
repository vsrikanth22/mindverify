package verify.vermgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import verify.vermgr.model.AppVersion;
import verify.vermgr.service.AppVersionMgr;

@Controller
@RequestMapping("/apps/{appId}")
public class AppVersionController {

	@Autowired
	private AppVersionMgr appVersionMgr = null;

	public void setAppVersionMgr(AppVersionMgr appVersionMgr) {
		this.appVersionMgr = appVersionMgr;
	}

	@RequestMapping(value = "/nextversion/", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String getNextAppVersion(@PathVariable("appId") String appId) {
		AppVersion appVersion = appVersionMgr.getNextVersion(appId);
		return appVersion.getMajorVersion() + "." + appVersion.getMinorVersion();
	}

	@RequestMapping(value = "/register/{majorVersion}/", method = RequestMethod.GET)
	public @ResponseBody
	void registerApp(@PathVariable("appId") String appId, @PathVariable("majorVersion") String majorVersion) {
		AppVersion appVersion = new AppVersion(appId,  majorVersion);
		appVersionMgr.register(appVersion);
	}

}
