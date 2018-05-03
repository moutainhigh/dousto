/*
 * cTabs 0.1
 * Copyright (c) 2012 Yan YuChen  http://yanyuchen.com/
 * Date: 2012-09-14
 * jQuery Tabs ���
 */
 (function($){
	$.fn.cTabs=function(options){
		var defaults = {selected:"selected",event:"click"}
		var options = $.extend(defaults, options);
		this.each(function(){
			$.cTabs(this,options);
		});
	}
	$.cTabs=function(tabs,options){
		var e=function(){
			$("a[href*='#']",tabs).removeClass(options.selected).each(function(){
				$($(this).attr("href")).hide();
			});
			
			$(this).addClass(options.selected);
			$($(this).attr("href")).show();	
			return false;
		}
		$("a[href*='#']",tabs).unbind(options.event,e).bind(options.event,e).each(function(){
			$($(this).attr("href")).hide();
		}).filter('.'+options.selected).trigger(options.event);
	}
})(jQuery);