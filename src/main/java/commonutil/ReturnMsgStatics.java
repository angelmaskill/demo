/**
 *
 */
package commonutil;

/***
 * @title 接口返回码消息常量类
 * @author TongZhaozhe
 * @date 2017年12月22日
 */
public final class ReturnMsgStatics {
	/**
	 * 调用信息
	 */
	public static final String SUCCESS_MSG = "调用成功";
	public static final String PAYSUCCESS_MSG = "支付成功";
	public static final String BALANCE_NOT_ENOUGH = "余额不足";
	public static final String ERROR_MSG = "调用失败";
	public static final String ADAPTER_CLOSE = "服务器网络波动，请重试";

	/**
	 * 参数验证
	 */
	public static final String PARAMETER_CANNOT_EMPTY = "{0}不能为空";
	public static final String FIELD_TOO_LONG = "{0}字段长度应小于{1}";
	public static final String PARAMETER_CANNOT_ALL_EMPTY = "{0}和{1}不能同时为空";
	/**
	 * 商户信息
	 */

	public static final String MERCHANTNOTEXISTS_MSG = "商户不存在";
	public static final String MERCHANT_EXISTS_REPEAT = "商户存在多条";


	public static final String PAUSEPAY_MSG = "系统正在结算,{0}暂停支付";

	/**
	 * 支付参数
	 */
	public static final String PAYPARAMNOTEXISTS_MSG = "商户未在该渠道配置支付参数";
	public static final String CONTRACT_UNABLE_PAY = "该支付方式暂不支持签约/代扣";
	public static final String PAYWAYNOTEXISTS_MSG = "支付方式不存在";
	public static final String PAYWAY_IS_DISABLE = "支付方式尚未生效";
	public static final String NOTFINDTOKENBYCOOKIE_MSG = "从cookie未获取到token或sign";
	public static final String PASSWDDISAFFINITY_MSG = "密码不同";
	public static final String PASSWD_ERROR="密码错误";
	public static final String PASSWD_NO_SET = "未设置支付密码";

	/**
	 * 身份验证:卡, 码,短信,银行卡
	 */
	public static final String UNSUPPORT_CODE_TYPE = "不支持的码类型";
	public static final String NO_CARD_BIND_INFO = "未获取到绑卡信息";
	public static final String NOT_SUPPORT_SMS = "该支付方式暂不支持短信支付";
	public static final String SMS_EXPIRED = "短信已失效";
	public static final String QC_NOT_CONTRACT = "未获取到银行卡信息，请先进行圈存签约";		// 圈存支付，未签约提示语


	/**
	 * 用户信息异常
	 */

	public static final String USERPAYING_MSG = "用户正在支付中";
	public static final String TOKENNOTEXISTS_MSG = "用户token不存在";
	public static final String INVALIDTOKEN_MSG = "用户token已失效";
	public static final String EXIST_CONTRACT = "该用户已经存在签约关系";
	public static final String NOT_EXIST_CONTRACT = "该用户不存在签约协议关系";


	public static final String EXIST_CONTRACT_ENG = "EXIST_CONTRACT";//已经存在签约关系
	public static final String NOT_SUPPORT_SOURCE_USER_CONTRACT = "不支持此用户来源";
	public static final String NOT_SUPPORT_USER_TOKENTYPE = "不支持用户标识类型";
	public static final String NOT_SUPPORT_PAYWAYID = "不支持该支付方式";
	public static final String NOT_SUPPORT_PAYWAYID_NOTICE = "该支付方式不支持接收签约/解约通知";
	public static final String USER_EXIST_CONTRACT = "用户已签约";
	public static final String USER_NOT_EXIST = "用户不存在";
	public static final String INVALID_TOKEN = "token已失效";
	public static final String NBIND_ACCOUNT = "未开通账户";
	public static final String USER_NOT_TREATY_EXIST = "用户协议不存在";
	public static final String SERIALNUMBER = "返回序列号与查询序列号不一致";


	/**
	 * 订单信息异常
	 */
	public static final String NOT_SUPPORT_MORE_JOURNO = "商户订单编号超过最大数量";
	public static final String NOSUPPORT_CANCEL = "{0}的订单暂不支持撤销"; // 如：支付宝的订单暂不支持撤销
	public static final String NOT_EXIST_REFUND_ORDER = "退款订单不存在";
	public static final String REFUND_REFUSED_STATE = "订单未支付成功，无法退款";
	public static final String REFUND_UNABLE_PAYWAY = "该支付方式暂不能退款";
	public static final String REFUND_REPEAT = "订单已经退款成功，请勿重复退款";
	public static final String REFUND_REFUSED_CHECK = "订单校验未通过，无法退款";
	public static final String REFUND_REFUSED_TXAMT = "订单金额错误，无法退款";
	public static final String REFUND_REFUSED_RFDAMT = "退款金额大于订单金额，无法退款";
	public static final String REFUND_REFUSED_MERCHANT = "订单与商户不匹配，无法退款";
	public static final String RECHARGE_ORDER_REPEAT = "订单重复，无法充值，请选择金额后重试";
	public static final String PRE_CANCEL_ORDER = "订单已撤销，无法预下单";
	public static final String PRE_REFUND_ORDER = "订单已退款，无法预下单";
	public static final String PAY_CANCEL_ORDER = "订单已撤销，无法支付";
	public static final String PAY_REFUND_ORDER = "订单已退款，无法支付";
	public static final String ORDERNOTEXIST_MSG = "订单不存在";
	public static final String ORDERSTATUSOFUNKNOWN_MSG = "未知的订单状态";
	public static final String DONOTREPAY_MSG = "订单已经支付成功,请勿重复支付";
	public static final String ORDERNOTPREPAY = "未进行预支付的订单不允许更新";
	public static final String REFUNDNOTUPDATE = "不允许更新已退款的订单";
	public static final String CANCELNOTUPDATE = "不允许更新已撤销的订单";
	public static final String ORDER_USER_NOT_MATCH = "订单与用户不匹配";
	public static final String UPDATE_REFUSED_CHECK = "订单校验未通过，无法更新";
	public static final String ORDER_CREATE_FAIL = "订单创建失败";
	public static final String ORDER_UPDATE_FAIL = "订单更新失败";

	/**
	 * 合作者信息
	 */
	public static final String NOT_FIND_PARTNERVO = "支付系统异常，未从上下文中获得合作者信息";
	public static final String PARTNER_MERCHANTNO_NOT_MATCH = "合作者商户没有权限";
	public static final String PARTNER_CONFIG_MORE_THAN_ONE_CHANNEL = "合作者配置了多个渠道";

	/**
	 * 账号信息
	 */
	public static final String NOT_FIND_ACCOUNT = "无法获取账号";
	public static final String PAYACCOUNTNOTMATCH_MSG = "收款账户不匹配";
	public static final String UNBIND_ACCOUNT = "未开通账户";
}
