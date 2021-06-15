package top.javaguo.utils.pay.alipay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Alipay通知
 *
 * @author 孙浩
 * @date 2018-08-08
 */
@RestController
@RequestMapping("/api")
@SuppressWarnings("all")
public class AlipayNotify {

	/**
	 * Alipay通知 参考：https://docs.open.alipay.com/204/105301/
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "alipayNotify", method = RequestMethod.POST)
	@ResponseBody
	public void alipayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("\n====================>Alipay Notify");
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		System.out.println("====================>params：" + params);
		// ————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,
				AlipayConfig.SIGN_TYPE);// 调用SDK验证签名
		if (signVerified) {
			// 验签成功
			System.out.println("====================>验签成功");
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");// 交易状态
			if ("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) {
				// 支付成功 || 交易完成
				System.out.println("====================>支付成功 || 交易完成");
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");// 商户网站唯一订单号
				String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");// 订单金额
				System.out.println("====================>商户订单号：" + out_trade_no);
				System.out.println("====================>订单总金额：" + total_amount + "（元）");

				// 下面开始处理项目业务逻辑

			}
			String returnStr = "success";
			response.getWriter().write(returnStr);
		} else {
			String returnStr = "failure";
			response.getWriter().write(returnStr);
		}
	}

}
