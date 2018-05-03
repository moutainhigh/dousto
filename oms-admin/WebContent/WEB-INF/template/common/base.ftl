<script>
var IBM = {
	base: "${base}",
	currencySign: "${systemConfig.currencySign}",// 货币符号
	currencyUnit: "${systemConfig.currencyUnit}",// 货币单位
	priceScale: "${systemConfig.priceScale}",// 商品价格精确位数
	priceRoundType: "${systemConfig.priceRoundType}",// 商品价格精确方式
	orderScale: "${systemConfig.orderScale}",// 订单金额精确位数
	orderRoundType: "${systemConfig.orderRoundType}"// 订单金额精确方式
};

// 格式化商品价格货币
function priceCurrencyFormat(price) {
	price = setScale(price, IBM.priceScale, IBM.priceRoundType);
	return IBM.currencySign + price;
}

// 格式化商品价格货币（包含货币单位）
function priceUnitCurrencyFormat(price) {
	price = setScale(price, IBM.priceScale, IBM.priceRoundType);
	return IBM.currencySign + price + IBM.currencyUnit;
}

// 格式化订单金额货币
function orderCurrencyFormat(price) {
	price = setScale(price, IBM.orderScale, IBM.orderRoundType);
	return IBM.currencySign + price;
}

// 格式化订单金额货币（包含货币单位）
function orderUnitCurrencyFormat(price) {
	price = setScale(price, IBM.orderScale, IBM.orderRoundType);
	return IBM.currencySign + price + IBM.currencyUnit;
}
</script>