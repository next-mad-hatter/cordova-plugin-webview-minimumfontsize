var exec = require('cordova/exec');
module.exports = {
	setMinFontSize: function (minSize, callback) {
		exec(function(res) {
			callback && callback(null, res);
		}, function(error) {
			callback && callback(error);
		}, "WebviewSetting", "set", [minSize]);
	}
};
