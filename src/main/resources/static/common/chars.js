(function($){
    //调用方法：$("body").myaccordion({selectedClz:"111"});
    //settings里的值，不传参数$("body").myaccordion()，即使用默认值
    $.fn.charls_pie = function(opts){
        var settings = $.extend({
        	data:[{}],
        	title:'大标题'
        	name:'第一题',
        },opts||{});
        // 图表配置
        var options = {
            chart: {
            	plotBackgroundColor: null,
        		plotBorderWidth: null,
        		plotShadow: false,
                type: 'pie'                          //指定图表的类型，默认是折线图（line）
            },
            plotOptions: {
        		pie: {
        			allowPointSelect: true,
        			cursor: 'pointer',
        			dataLabels: {
        				enabled: true,
        				format: '<b>{point.name}</b>: {point.percentage:.1f} %',
        				style: {
        					color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
        				}
        			}
        		}
        	},
            title: {
                text: settings.title                 // 标题
            },
            series: [{
        		name: settings.name,
        		colorByPoint: true,
        		data: settings.data
        	}]
        };
    }
})(jQuery)