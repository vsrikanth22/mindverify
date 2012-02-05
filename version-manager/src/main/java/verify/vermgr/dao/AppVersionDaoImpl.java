package verify.vermgr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import verify.vermgr.model.AppVersion;
import verify.vermgr.model.Scope;

public class AppVersionDaoImpl implements AppVersionDao {

	private JdbcTemplate jdbcTemplate = null;

	public AppVersionDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void register(final AppVersion appVersion) {

		jdbcTemplate.update(
				"insert into app_version(app_name, scope, major_version, minor_version) values (?, ?, ?, ?)",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, appVersion.getAppCode());
						ps.setString(2, appVersion.getScope().getScope());
						ps.setString(3, appVersion.getMajorVersion());
						ps.setLong(4, appVersion.getMinorVersion());

					}
				});

	}

	@Override
	public AppVersion get(final String appName, final String scope) {
		return jdbcTemplate.queryForObject("", new Object[] { appName, scope }, new int[] { Types.VARCHAR,
				Types.VARCHAR }, new RowMapper<AppVersion>() {

			@Override
			public AppVersion mapRow(ResultSet rs, int rowNum) throws SQLException {

				AppVersion appVersion = new AppVersion();
				appVersion.setAppCode(rs.getString("app_name"));
				appVersion.setScope(Scope.valueOf(rs.getString("scope")));
				appVersion.setMajorVersion(rs.getString("major_version"));
				appVersion.setMinorVersion(rs.getLong("minor_version"));
				return appVersion;
			}
		});
	}

	@Override
	public void update(final AppVersion appVersion) {
		jdbcTemplate.update(
				"update app_version set major_version = ?, minor_version =? where app_name =? and scope = ?",
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setString(1, appVersion.getMajorVersion());
						ps.setLong(2, appVersion.getMinorVersion());
						ps.setString(3, appVersion.getAppCode());
						ps.setString(4, appVersion.getScope().getScope());

					}
				});

	}

}
