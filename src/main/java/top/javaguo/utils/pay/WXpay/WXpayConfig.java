package top.javaguo.utils.pay.WXpay;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

/**
 * WXPay配置
 * 
 * @author 孙
 * @date 2018-08-08
 */
public class WXpayConfig implements WXPayConfig {

	// private byte[] certData;
	//
	// public WXpayConfig() throws Exception {
	// String certPath = "/path/to/apiclient_cert.p12";
	// File file = new File(certPath);
	// InputStream certStream = new FileInputStream(file);
	// this.certData = new byte[(int) file.length()];
	// certStream.read(this.certData);
	// certStream.close();
	// }

	@Override
	public InputStream getCertStream() {
		// ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
		// return certBis;
		return null;
	}

	@Override
	public int getHttpConnectTimeoutMs() {
		return 8000;
	}

	@Override
	public int getHttpReadTimeoutMs() {
		return 10000;
	}
	
	// 每个项目需要变动的地方为下面四个配置属性

	// 正式服
	// public static String NOTIFY_URL = "http://47.97.190.18:8080/api/wxpayNotify";
	// 测试服
	public static String NOTIFY_URL = "http://118.31.72.169:8080/api/wxpayNotify";

	// appId
	@Override
	public String getAppID() {
		// return "wx5686cd6af8358d72";// wuchan
		// return "wx9ce8e7c531fcf45a";// jst
		// return "wx11443ea027afc031";// xinshidai
		// return "wxc80aaea8ae131f66";// 云充电
		return "wxc9353d09a0d1f197";// 衣创
	}

	// mchID
	@Override
	public String getMchID() {
		// return "1513467881";// wuchan
		// return "1497788802";// jst
		// return "1501882511";// xinshidai
		// return "1514587301";// 云充电
		return "1496506752";// 衣创
	}

	// Key
	@Override
	public String getKey() {
		// return "9z3f0m02RdAG0FGzcfwQjrChHLIohYNW";// wuchan
		// return "URqy9Gw0InRobbZ2Dhak4tu4g5gOC8HA";// jst
		// return "ASRXsd125AExcerdgc245564612qweqw";// xinshidai
		// return "3B6Ar9JGHxcJr0gfed3WvcnKkeTUqi8m";// 云充电
		return "27cd913c4ba180ce5bca2631e24a986e";// 衣创
	}

}
