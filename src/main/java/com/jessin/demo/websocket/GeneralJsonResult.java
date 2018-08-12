package com.jessin.demo.websocket;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

public class GeneralJsonResult<T> implements Serializable {
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 是否成功
	 */
	private Boolean ret = Boolean.TRUE;
	/**
	 * 错误信息
	 */
	private String errmsg;
	/**
	 * 错误码
	 */
	private Integer errcode;
	/**
	 * 版本
	 */
	private Integer ver;
	/**
	 * 数据
	 */
	private T data;
	
	/**
	 * 当前服务器时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Timestamp currentTime = new Timestamp(System.currentTimeMillis());

	public static <T> GeneralJsonResult<T> newResult(){
		return new GeneralJsonResult<T>();
	}

    /**
     * 创建结果model（失败），ret=false，errmsg为传入参数，data=null
     * @param errmsg 失败原因
     */
    public static <T> GeneralJsonResult<T> newFailResult(String errmsg) {
        return newResult(false, errmsg, null);
    }

	public static <T> GeneralJsonResult<T> newFailResult(String errmsg, Integer errcode) {
		return newResult(false, errmsg, errcode, null, null);
	}

    /**
     * 创建结果model（成功），ret=true，errmsg=null，data=传入参数
     * @param data 数据
     */
    public static <T> GeneralJsonResult<T> newSuccessResult(T data) {
        return newResult(true, null, data);
    }
    
    public static <T> GeneralJsonResult<T> newResult(Boolean ret, String errmsg, T data) {
        return new GeneralJsonResult<T>(ret, errmsg, data);
    }

    public static <T> GeneralJsonResult<T> newResult(Boolean ret, String errmsg, Integer errcode, Integer ver, T data) {
        return new GeneralJsonResult<T>(ret, errmsg, errcode, ver, data);
    }

	protected GeneralJsonResult() {
	}

    protected GeneralJsonResult(Boolean ret, String errmsg, T data) {
		super();
		this.ret = ret;
		this.errmsg = errmsg;
		this.data = data;
	}

    protected GeneralJsonResult(Boolean ret, String errmsg, Integer errcode,
			Integer ver, T data) {
		super();
		this.ret = ret;
		this.errmsg = errmsg;
		this.errcode = errcode;
		this.ver = ver;
		this.data = data;
	}

	public Boolean getRet() {
		return ret;
	}

	public void setRet(Boolean ret) {
		this.ret = ret;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "GeneralJsonResult [ret=" + ret + ", errmsg=" + errmsg + ", errcode=" + errcode + ", ver=" + ver
                + ", data=" + data + ", currentTime=" + currentTime + "]";
    }


}
