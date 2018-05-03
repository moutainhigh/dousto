package com.ibm.oms.domain.persist;

public class AreaBean {
	
	private String id;
	
	private String name;
	
	private boolean checked;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public AreaBean() {
		super();
	}

	public AreaBean(String id) {
		super();
		this.id = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
