package verify.vermgr.service;

import verify.vermgr.model.AppVersion;

public interface AppVersionMgr {

	public AppVersion getNextVersion(String appId);

	public void register(AppVersion appVersion);

	public void updateMajorVersion(String appId, String majorVersion);

}
