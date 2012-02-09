package verify.vermgr.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.InitializingBean;

import verify.vermgr.dao.AppVersionDao;
import verify.vermgr.model.AppVersion;

public class AppVersionMgrImpl implements AppVersionMgr, InitializingBean {

	private Map<String, Lock> locks = new ConcurrentHashMap<String, Lock>();

	private AppVersionDao appVersionDao;

	public void setAppVersionDao(AppVersionDao appVersionDao) {
		this.appVersionDao = appVersionDao;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		List<String> keys = appVersionDao.getAppkeys();
		for (String key : keys) {
			locks.put(key, new ReentrantLock());
		}

	}

	@Override
	public AppVersion getNextVersion(String appId) {
		AppVersion appVersion = null;
		Lock lock = locks.get(appId);
		try {

			lock.lock();
			appVersion = appVersionDao.get(appId).increment();
			appVersionDao.update(appVersion);

		} finally {
			lock.unlock();
		}

		return appVersion;
	}

	@Override
	public void register(AppVersion appVersion) {
		appVersionDao.register(appVersion);
		locks.put(appVersion.getAppCode(), new ReentrantLock());
	}

	@Override
	public void updateMajorVersion(String appId, String majorVersion) {
		Lock lock = locks.get(appId );
		try {
			lock.lock();
			AppVersion appVersion = appVersionDao.get(appId);
			appVersion.setMajorVersion(majorVersion);
			appVersion.setMinorVersion(0);
			appVersionDao.update(appVersion);
		} finally {
			lock.unlock();
		}
	}

}
