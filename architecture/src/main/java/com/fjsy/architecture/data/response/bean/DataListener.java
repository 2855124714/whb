package com.fjsy.architecture.data.response.bean;

public interface DataListener {
	void dataStart();
	
	void dataStop();
	
	void dataOther(StatusInfo statusInfo);
}
