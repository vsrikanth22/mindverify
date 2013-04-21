package verify.jboss.drools.benchmark;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class GoldCopyTxn {

	private Long sourceId;
	private String fundId;
	private String gcClassCode;
	private String settleCcyCd;
	private String dbCrCd;
	private String sourceTransCd;
	private Calendar postedDate;
	private Double tranAmt;

	private Map<String, String> attributes = new HashMap<String, String>();

	public String getAttrByName(String name) {
		return attributes.get(name);
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public String getFundId() {
		return fundId;
	}

	public void setFundId(String fundId) {
		this.fundId = fundId;
	}

	public String getGcClassCode() {
		return gcClassCode;
	}

	public void setGcClassCode(String gcClassCode) {
		this.gcClassCode = gcClassCode;
	}

	public String getSettleCcyCd() {
		return settleCcyCd;
	}

	public void setSettleCcyCd(String settleCcyCd) {
		this.settleCcyCd = settleCcyCd;
	}

	public String getDbCrCd() {
		return dbCrCd;
	}

	public void setDbCrCd(String dbCrCd) {
		this.dbCrCd = dbCrCd;
	}

	public String getSourceTransCd() {
		return sourceTransCd;
	}

	public void setSourceTransCd(String sourceTransCd) {
		this.sourceTransCd = sourceTransCd;
	}

	public Calendar getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Calendar postedDate) {
		this.postedDate = postedDate;
	}

	public Double getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(Double tranAmt) {
		this.tranAmt = tranAmt;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

}
