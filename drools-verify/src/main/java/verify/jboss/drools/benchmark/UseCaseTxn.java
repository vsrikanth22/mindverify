package verify.jboss.drools.benchmark;

public class UseCaseTxn {

	private GoldCopyTxn goldCopyTxn;
	private String gcGuid;

	public void setGoldCopyTxn(GoldCopyTxn goldCopyTxn) {
		this.goldCopyTxn = goldCopyTxn;
	}

	public GoldCopyTxn getGoldCopyTxn() {
		return goldCopyTxn;
	}

	public String getGcGuid() {
		return gcGuid;
	}

	public void setGcGuid(String gcGuid) {
		this.gcGuid = gcGuid;
	}

}
