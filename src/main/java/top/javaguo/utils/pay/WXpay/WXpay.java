package top.javaguo.utils.pay.WXpay;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * WXPay支付
 * 
 * @author 孙
 * @date 2018-08-08
 */
public class WXpay {

	/**
	 * App支付 参考：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
	 * 
	 * @param body
	 *            商品描述交易字段格式根据不同的应用场景按照以下格式：APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
	 * @param outTradeNo
	 *            商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
	 * @param totalFee
	 *            订单总金额，单位为分，详见支付金额
	 * @return
	 * @throws Exception
	 *             字段注释 appid 应用ID 是 String(32) wxd678efh567hg6787
	 *             微信开放平台审核通过的应用APPID mch_id 商户号 是 String(32) 1230000109 微信支付分配的商户号
	 *             nonce_str 随机字符串 是 String(32) 5K8264ILTKCH16CQ2502SI8ZNMTM67VS
	 *             随机字符串，不长于32位。推荐随机数生成算法 sign 签名 是 String(32)
	 *             C380BEC2BFD727A4B6845133519F3AD6 签名，详见签名生成算法 body 商品描述 是
	 *             String(128) 腾讯充值中心-QQ会员充值
	 *             商品描述交易字段格式根据不同的应用场景按照以下格式：APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
	 *             out_trade_no 商户订单号 是 String(32) 20150806125346
	 *             商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
	 *             total_fee 总金额 是 Int 888 订单总金额，单位为分，详见支付金额 spbill_create_ip 终端IP 是
	 *             String(16) 123.12.12.123 用户端实际ip notify_url 通知地址 是 String(256)
	 *             http://www.weixin.qq.com/wxpay/pay.php
	 *             接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。 trade_type 交易类型 是
	 *             String(16) APP 支付类型
	 */
	public static Map<String, String> doAppPay(String body, String outTradeNo, String totalFee) throws Exception {
		WXpayConfig config = new WXpayConfig();
		System.out.println(config.getAppID());
		System.out.println(config.getMchID());
		System.out.println(config.getKey());
		WXPay wxpay = new WXPay(config);
		Map<String, String> data = new HashMap<String, String>();
		data.put("body", body);
		data.put("out_trade_no", outTradeNo);
		data.put("total_fee", (int) (new BigDecimal(totalFee).multiply(new BigDecimal("100")).doubleValue()) + "");
		data.put("spbill_create_ip", "0.0.0.0");
		data.put("notify_url", WXpayConfig.NOTIFY_URL);
		data.put("trade_type", "APP");
		try {
			Map<String, String> resp = wxpay.unifiedOrder(data);
			System.out.println("\n==========>统一下单resp：" + resp);
			/**
			 * 出参app
			 * 调起支付接口：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_12&index=2
			 */
			Map<String, String> resultMap = new LinkedHashMap<String, String>();
			resultMap.put("appid", config.getAppID()); // 应用ID
			resultMap.put("partnerid", config.getMchID()); // 商户号
			resultMap.put("prepayid", resp.get("prepay_id")); // 预支付交易会话ID
			resultMap.put("package", "Sign=WXPay"); // 扩展字段
			resultMap.put("noncestr", WXPayUtil.generateNonceStr()); // 随机字符串
			resultMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000)); // 时间戳
			resultMap.put("sign", WXPayUtil.generateSignature(resultMap, config.getKey())); // 签名
			System.out.println("\n==========>调起支付resp：" + resultMap);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 扫码支付 参考：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
	 * 
	 * @param body
	 *            商品简单描述，该字段请按照规范传递，具体请见参数规定
	 * @param outTradeNo
	 *            商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一。详见商户订单号
	 * @param totalFee
	 *            订单总金额，单位为分，详见支付金额
	 * @return
	 * @throws Exception
	 *             字段注释 appid 公众账号ID 是 String(32) wxd678efh567hg6787
	 *             微信支付分配的公众账号ID（企业号corpid即为此appId） mch_id 商户号 是 String(32)
	 *             1230000109 微信支付分配的商户号 nonce_str 随机字符串 是 String(32)
	 *             5K8264ILTKCH16CQ2502SI8ZNMTM67VS 随机字符串，长度要求在32位以内。推荐随机数生成算法 sign
	 *             签名 是 String(32) C380BEC2BFD727A4B6845133519F3AD6
	 *             通过签名算法计算得出的签名值，详见签名生成算法 body 商品描述 是 String(128) 腾讯充值中心-QQ会员充值
	 *             商品简单描述，该字段请按照规范传递，具体请见参数规定 out_trade_no 商户订单号 是 String(32)
	 *             20150806125346 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*
	 *             且在同一个商户号下唯一。详见商户订单号 total_fee 标价金额 是 Int 88 订单总金额，单位为分，详见支付金额
	 *             spbill_create_ip 终端IP 是 String(16) 123.12.12.123
	 *             APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。 notify_url 通知地址 是
	 *             String(256) http://www.weixin.qq.com/wxpay/pay.php
	 *             异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。 trade_type 交易类型 是
	 *             String(16) JSAPI JSAPI 公众号支付、NATIVE 扫码支付、APP APP支付，说明详见参数规定
	 */
	public static String doNativePay(String body, String outTradeNo, String totalFee) throws Exception {
		WXpayConfig config = new WXpayConfig();
		WXPay wxpay = new WXPay(config);
		Map<String, String> data = new HashMap<String, String>();
		data.put("body", body);
		data.put("out_trade_no", outTradeNo);
		data.put("total_fee", (int) (new BigDecimal(totalFee).multiply(new BigDecimal("100")).doubleValue()) + "");
		data.put("spbill_create_ip", "0.0.0.0");
		data.put("notify_url", WXpayConfig.NOTIFY_URL);
		data.put("trade_type", "NATIVE");
		String str = "";
		try {
			Map<String, String> resp = wxpay.unifiedOrder(data);
			System.out.println("\n==========>统一下单resp：" + resp);
			str = resp.get("code_url");
			System.out.println("\n==========>扫码支付resp：" + str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * H5支付：unfinished....
	 * 
	 * @param body
	 * @param outTradeNo
	 * @param totalFee
	 * @param spbillCreateIp
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> doMwebPay(String body, String outTradeNo, String totalFee, String spbillCreateIp)
			throws Exception {
		WXpayConfig config = new WXpayConfig();
		WXPay wxpay = new WXPay(config);
		Map<String, String> data = new HashMap<String, String>();
		data.put("body", body);
		data.put("out_trade_no", outTradeNo);
		data.put("total_fee", (int) (new BigDecimal(totalFee).multiply(new BigDecimal("100")).doubleValue()) + "");
		data.put("spbill_create_ip", spbillCreateIp);
		data.put("notify_url", WXpayConfig.NOTIFY_URL);
		data.put("trade_type", "MWEB");
		try {
			Map<String, String> resp = wxpay.unifiedOrder(data);
			System.out.println("\n==========>统一下单resp：" + resp);
			// 出参
			Map<String, String> resultMap = new LinkedHashMap<String, String>();
			resultMap.put("mwebUrl", resp.get("mweb_url"));// mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
			System.out.println("\n==========>H5支付resp：" + resultMap);
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
//		doAppPay("App支付test", "1123456718901222", "0.01");
//		 doNativePay("扫码支付test", "111234567890123141", "0.01");
//		doMwebPay("H5支付test", "43243243221", "0.01", "211.138.116.238");
		doMwebPay("H5支付test", "43243243221", "0.01", "http://www.yichuangpt.com");
	}

}
