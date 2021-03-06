package top.javaguo.utils.pay.alipay;

/**
 * Alipay配置
 *
 * @author 孙
 * @date 2018-08-08
 */
public class AlipayConfig {

	// 支付宝网关（固定）
	public static String URL = "https://openapi.alipay.com/gateway.do";

	// 参数返回格式，只支持json
	public static String FORMAT = "json";

	// 请求和签名使用的字符编码格式，支持GBK和UTF-8
	public static String CHARSET = "UTF-8";

	// 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
	public static String SIGN_TYPE = "RSA2";

	// 每个项目需要变动的地方为下面四个配置属性

	// 通知正式服
	// public static String NOTIFY_URL =
	// "http://47.97.190.18:8080/api/alipayNotify";
	// 通知测试服
	// 回调方法
	public static String NOTIFY_URL = "http://118.31.72.169:8080/api/alipayNotify";

	// APPID即创建应用后生成
	public static String APP_ID = "2018083061154870";// wuchan
	// public static String APP_ID = "2017122401125553";// jst

	// 开发者应用私钥，由开发者自己生成
	public static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCdaodL7GbgH4KXlaUmajqTX2aoVncMsZQjeoCXCSj0i31JbZTQJYKrtzbOInXAvE79Sjhm9E82enParUaRJZJBDcNN7OboP1TsCgVWY4IDTQdYqC7Vz30pDx4GYXqCcPnAsY8qXMdmNadsmSBZKzYBS8Qz5m4Bwokx2E+1VOwK+3wtIgizmawVV99rP0gnQJN3kkXyT4CgOP6AMAU5XLN2IKeLSmZWts0sd0bTx0mYc5xuO+iJBUNyBKQdq7NQQ9/SLMpql6QJG+NvPhG+r8G/fIs242rRt0I+Z2zXxLgi7YcP20X7prV+KdvqblohdteynHmA5uoHQCnKxL8Qgit1AgMBAAECggEAc+wtMmYm5EmQHB0hf4afGEQbD0CNbxWep/nglFk0hdHrM5W1DwtwjQ/hFjAeUw7ZhIGRw4+Kfe3zP0h3PRBUAgAmoU4iRUnOOfyvLY0eA1xIWquPwfiIjSIK1FwYodKcuaCIc4Pbe5NrdffV8JR3ssCe/jFlNXqTGdfX6wtcwECBf+D0IyMs27EW+qb6iGpGxbrN/vU3b7f49RjKugC6EFxDNwdiWX315OYlmxQPr3cJBOqd1EL00c+rF65DyQ8//6rGW4pF7TlV+o4gJujpEpz+BjtQsYbqcTyt6WV72cxDdEJ4p4B5ssWc3Bt5p9ciAvtwbPtJFuwvSb2NeDcYCQKBgQDzAUMddehHCUX3IL9bcKtmU7dAP4SBgSUvqSH2EPL2/IgvM3Kh9UuLeVxz9OQ1mSafSMjqu6/D/iqsH91ciMtLt6UeiTFm2evYQdwDj2YH31Hg0aO6fWrnMsNTVn61QmMBILlkjkp+TSiL486QoLswRkTRmBsx48BzCzJjC2OEmwKBgQCl1Y41FozeaCIPm8NWSMGsfNKnRWZLzqJgIcjLqSSDgsKDzepIXr5xdKlRYlz3/h6NPs5ZoHnZLWgcVyrBNbjs9+YcR1My+IOXhWn59XxfVRiYcxjCpWo4LyymRa/xlrFjFODzOpSoj4Wwz6MemW/H6s/2XpjUJQp/HctVKwApLwKBgHGxF3uDHKePmSJkOeNINYjshgITNaV5jLJdyTvc9i/EOtsKkQlhQHUC1NSTsOm6u60uWoIoSbXDBqf9SI3wmlzUkPZWbbyXHJbTSWKJBs2MGDy9GIM6Bngg8e3du+6GHtf6PYLkZ8nrw5qffJeB+9I8Lhp5tc+DpRiuFmbsfyUdAoGASg85VxQfDSRMMdRKRDpx4+pK2VDxAmHhujTcS7faCrFW1S4ctv0458pF44aeJlJiqoml9fjo8ciJej6jZW3sjEeZiH+B9H1vpzd5wJuVU63ZMhwUrrjY2waWVhtBHA9EYQ9t9rHuueh+uBvCqWO0u/9tqIVOk0+HhIV1zS9K/xkCgYBAPEe5+WkmGw0OChvznnBmCPeyH6oPHNXPrihKTDmTz3Am1HYbKRoO8HzopPPBuW40bgANG9LVT5oPOEA/ZLzxLCfIY/gt8H8zU6AgihX+KkDkGLP06EY/RcD9Skjy+JS5iowin4qCmKIGsmbCGjdCzvP26A7214jHPwHPj44YlA==";
	// public static String APP_PRIVATE_KEY =
	// "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOJtyMC27x7MzcbUpxs1qWleZzjNn80uA9uuTwafjuoThnojCX3n0xE1waoeVSkq/rXAO7Kxk5CU+MVz8m6CJ2dZG9ueflURYqzIDWfwv0Mu4ITZoyPHdslq69ab4qQVjGEOfe0TBpfaOVGEtwZqBY9/AQx3sY1nQcWFtcrtwoT7BasVZ1aP3+QGf9nRhkxFjcVjOw4ggYpZaRxrXoe5IiJ3kPqgoEK/BPf8JppQOM32StfHiF9pDfmkBxFhhpoI0JomVgBrA+WFU27LhIWDzWbJ1d/9Hlhcag6Ah+4YFM3UZheVy7F3FVyOYO5cpKjwWcSrUJZvi2nAP/XGWyq5l9AgMBAAECggEASz5iy3ZZ2ZDgt2TaXOj08HSUMWHDGUJ+E+7TtDZD3/xSoPiHBDxuxAVdYXOEsXqXx9tC1lXv2fMFumGMOgnyBAYjiOvYxddwBmONZvyE1VQIDzkUf7bl+AKip/xK/o7scJsOQTE1kQFCD292rx6DjdRfrGIK1JDfkQlsAtlEC8mkoZxcHB5+Um2aqDx/jCxjI9TTVE4+nd+vsrY/3Hjtj0Z4RBbVqbGZUA2dlDMLtN3NXclMO3cQTgFdjkSPQa82qoysfR0V10vjeNBXb93UZ3BjsaEpoTxUvtQNTtEfzZA13lDyvkHkMS3V3gG9qklBDw/2XUjw+8QoYpVmeL9auQKBgQDpPPa4l23LDDc9/ZPG2bOXnM14BNnVLu/8bQ9weaNmGvtbmCDb+f+cH832/2MDQCUhQU7HuVUZp4yp8K/1XMcVZVf99rkeTNPZrVlJWUclw1RLotaNijmUA3nnRY0KfKEcsVgr+Tvh4tELupXMuZp0GLQIL3Dx2SWGF+30YpkbKwKBgQCcBkTmCVq3yX4YDdPrxd3xlVC+FYykWh0r2CFbWF/8nqHLW1osXjfRpJTPXzaXyi9rw0Sn7GIXSlefrz5/OF8FihU/gPceKy8ftvKGj9wlg7tqd7CsnQVlwxrTyk6shHXvVzLolxSd0RaHmsp+vUkVwUZfxOeXGju0VLIx3Imp9wKBgFwpIrQ94bxhz1JHaKUiULyP0QCjqg96xIlR7s/awJ4P+1JuAk3psv7V8mSu9UjLh+BkFeUFboL8mbBs0pp5VpE1wlcvrmYypWNWA/nftSVO0Xgxl+XFwIqj9P9VlC2YoXDdAqIdkXPoVAH2cEq87DMDr9+sH0zxKMqbmgL/EtCRAoGASONE3JISJzmiBmqvTb2p6Wnvxl0azsyqRAk7zAiFDuasfuu2PHPnb7CLNDUus8poTNd0A16SlH7OYHLlp1kWSugu9POyfg1+fjphIiuilIPt0dcIWhsgomF67uXbyww2bBE4Xx/1KaS7+a1+W6bhtaAM2ECMUDKZIa1PcautRBECgYEA1MQ39AizsGjEDo4BozAAt22LXw1e5PlIRc5EN/muDgNsnLGFKLU5epW23YsyNn6VWtHhJxXE2LCto2c7/DxsHBxILDftRR0TvwWR3Crgyu8pmLs62uLmuHVfCy/FM/In+s0/lO9KOLn5YnmCwvNVsSzYDqoQRKT42QFAmfu285c=";

	// 支付宝公钥，由支付宝生成
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAteOe+kxOsWDRTiypktoebvDvUItIfQBqLKVC6zJ4Kd5dvz4Nvm29j1ctZuazrAglSTeA+fRY+IIvR7HYp+AxCMmpPRRb4rZG/tbTud+V9SlGXLKm+r9jkuURC6I3xc5cAQkSUn8wnubg/3Xgehy0EMJ4Tu08H5fSWEe88nqMmbSuvERvM5bYuls1yYw+PV3E5w3yfkU2XdgDWzDN7yA0L/528TKVPl/iEW+fpXVVN/aoZhtgeuLLUxI9oeG7ElLGmqD3xize/oZld9wxvg5FohuDoP1FqzsaJ2O55qNT5VfY4DWdrlTzIFKmz3NBOA7fXQCAx9WvnYJ7hARgHst4fQIDAQAB";
	// public static String ALIPAY_PUBLIC_KEY =
	// "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvJL5fNAmCqu0j+ghksU2TPLY2IfFtZjWKNhWKLOtNYidba8t2TBjums5J2uftXdPPfflYSGe5Qzno3q5RFQ02fIqQOlrCfBnP8y7J8/mHHzobbXtFNKOc94x8cvvaPfyoxkQyP1y0cP6RO9tyTvaZUYgYt7UEiKD9W3avyZxcgfsX2KQNCR/xvlvEk8dQ6R1StUIqqHU9sxyaGifN/XcDUT4P8W2Vfml4WRSbe09dB+/e8vx9kEZv3qZ4L+aCH0osgfgdbP4WSwBrtSISiJbOHpphg1yi4wFQDEZKcGmgaC8k4iJPit/EgieP+OtiezFPYhw6K0gnKaENANskDWqKQIDAQAB";

}
