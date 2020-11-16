/**
 * @author 何杰
 * @date 2016-08-01 12:00:00
 * @address www.yitong.com.cn
 * @mail hj@yitong.com.cn
 * @desc: 屹通客户端组件api
 */
var Fw = {};
Fw.device = {};
Fw.device.Device = function() {
	var userAgent = navigator.userAgent;
	// ios 事件队列
	var eventQueue = [];
	return {
		// 是否iOS设备
		is_ios : userAgent.indexOf('iPhone') > -1
				|| userAgent.indexOf('iPad') > -1,
		// 是否Android设备
		is_android : userAgent.indexOf('Android') > -1,
		/**
		 * ios 注册事件
		 *
		 * @param code
		 *            事件编码
		 * @param options
		 *            参数
		 */
		addEvent : function(code, options) {
			if (options && code) {
				eventQueue.push(JSON.stringify({
					code : code,
					name : options
				}));
			}
		},
		/**
		 * ios 原生调用，从队列中获取事件
		 *
		 * @returns {string}
		 */
		getEvent : function() {
			return eventQueue.length > 0 ? eventQueue.shift() : '0';
		}
	};
}();
/**
 * 供第三方调用API
 */
Fw.device.api = function() {
	var device = Fw.device.Device;
	return {
		/**
		 * 跳转到手机银行
		 */
		goToIndex : function(fn) {
			if (!device.is_ios && !device.is_android) {
				alert("请在ios/android设备上使用！");
				return;
			}
			device.is_ios && device.addEvent("31", JSON.stringify(""));
			device.is_android && SysClientJs.gotoIndex();
		}

	}
}();
