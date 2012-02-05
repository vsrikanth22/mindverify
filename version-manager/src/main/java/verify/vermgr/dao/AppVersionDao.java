package verify.vermgr.dao;

import verify.vermgr.model.AppVersion;

public interface AppVersionDao {

	/**
	 * register the new app version of application.
	 * 
	 * @param appVersion
	 *            the app version object.
	 */
	public void register(AppVersion appVersion);

	public AppVersion get(String appName, String scope);

	public void update(AppVersion appVersion);

}
