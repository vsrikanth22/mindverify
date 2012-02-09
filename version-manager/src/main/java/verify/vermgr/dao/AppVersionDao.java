package verify.vermgr.dao;

import java.util.List;

import verify.vermgr.model.AppVersion;

public interface AppVersionDao {

	/**
	 * register the new app version of application.
	 * 
	 * @param appVersion
	 *            the app version object.
	 */
	public void register(AppVersion appVersion);

	public AppVersion get(String appName);

	public void update(AppVersion appVersion);
	
	public List<String> getAppkeys();

}
