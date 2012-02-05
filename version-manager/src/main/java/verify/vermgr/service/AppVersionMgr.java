package verify.vermgr.service;

import verify.vermgr.model.AppVersion;

public interface AppVersionMgr {
	
	public AppVersion getNextVersion(String appId, String scope);

}
