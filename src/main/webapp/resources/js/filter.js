(function ($) {
	var constants = {showMoreLength : 6};
	var isIE = (navigator.userAgent.toUpperCase().indexOf('MSIE') >= 0 ? true:false);
	// Provision kept to fix ie specific issue
	var filterContentDescLength = (isIE==true ? 23:23);
    var methods = {
        init: function () {
			//alert('init !!!');
            $f = $('.filter');
			if(!$f.hasClass('filter-done')){
				$f.addClass('filter-done');
				$filter = $f;
				var $res = this.closest(".results");
				var $fb = $f.find(".body");
				var $fbc = $f.find(".body-content")

				function updateHeight() {
					 $fb.css("height", $fbc.height());
				}
				if ($res.hasClass("filter-closed")) {
					$res.addClass("filter-open");
					updateHeight();
					$res.removeClass("filter-open");
				}
				$filter.on('click','.btn-filter',function () {
					var open = $res.hasClass("filter-open");
					if (open) {
						updateHeight();
						$res.removeClass("filter-open").addClass("filter-closed");
					} else {
						$fb.css("height", "auto");
						$res.addClass("filter-open").removeClass("filter-closed");
					}
				});
				$filter.on('click','h3 a',function () {
					$cat = $(this).closest("li");
					if($cat.find('.filter-sub-list').hasClass('date-list')){
						$cat.find('.date-list').toggle();
						$cat.toggleClass("open");
					}else if($cat.find('.filter-sub-list').length > 0){
					  $cat.toggleClass("open");
					}
					if($cat.hasClass('open')) {
						$cat.find('>div').show();
					} else {
						$cat.find('>div').hide();
					}
					
					return false;
				});
				$filter.on('click','.help',function () {
					return false;
				});
				$filter.on('keypress','.hasInputType',function (e) {
					var keycode = (e.keyCode ? e.keyCode : e.which);
					if(keycode == '13') {
						$(this).parents('li.category').find('.filterSearchBtn').trigger('click');
					}
				});
				// select all / none buttons
				$f.find("li button.all").click(function () {
					// also open the panel, which feels like a good usability extra
					this.closest("li").addClass("open").find("input[type=checkbox]").attr("checked", "checked");
				});
				$f.find("li button.none").click(function () {
					this.closest("li").find("input[type=checkbox]").removeAttr("checked");
				});

				function filterResults($cat) {
					$input = $cat.find(".search-control input");
					var val = $.trim($input.val()).toLowerCase();
					var $labels = $cat.find("label");
					var $li = $cat.find("li");
					$li.removeClass("no-match");
					if (val != "") {
						$labels.each(function () {
							var $el = $(this);
							var $elText;
							if($el.attr('title')=='' || $el.attr('title')==undefined || $el.attr('title')=='undefined'){
								if($el.siblings('div.toolDiv').length > 0) {
									$elText = $el.siblings('div.toolDiv').text();
								} else {
									$elText = $el.text();
								}
							}else{
								$elText=$el.attr('title');
							}
							if ($elText.toLowerCase().indexOf(val)==-1) {
								$el.closest("li").addClass("no-match");
							}
						});
					}
				}
				var handler = function () {
					var $cat = $(this).closest("li.category");
					filterResults($cat);
				};
				$f.on({
					keyup: handler,
					searchclear: handler
				}, '.search-control input');
				$f.find("li.category").each(function () {
					filterResults(this);
				});

				// clear all checkboxes - TO DO
				/*$f.find(".clear-all").click(function () {
					$f.find("input[type=checkbox]").removeAttr("checked");
				});*/
				/*---Added on 17/12/2012-------*/
				$('body').on('click','.clear-all',function () {
					$f.find("input[type=checkbox]").removeAttr("checked");
					$f.find("input[type=radio]").removeAttr("checked");
					$('.filter button.clear').trigger('click');
					
					});
				$('body').on('click', '.btn-more', function () {
					$(this).closest("li.category").addClass("with-more").find("input.search").focus();
					$('input, textarea').placeholder();
					
				});
			}else{
				$f.find('ul.categories').empty();
			}
        },
        draw: function (options) {
            if (options.buildFilter) {
                buildFilter(options.filterArr, $('.filter'), options.preSelected, options.filterParentId);
            }else if(options.dynamicFilter){
				appendFilter(options.filterArr, this);
			}else if(options.insertFilter){
				inserFilterAfter(options.filterArr, this);
			}
			clearSearch(this);
        }
    };


    function buildFilter(filterArr, $this, preSelected, filterParentId) {
        //alert('Build Filter');
        var htmlStr = '',
            nextJson = false, hasDate = false;
        for (var index = 0, len = filterArr.length; index < len; index++) {
            var temp = filterArr[index], mandatory = (temp.manadatory && temp.jsonSource !='undefined' && $.trim(temp.jsonSource) !='' )?true:false;
			var tempId = (typeof temp.filterId !== 'undefined')? temp.filterId:temp.filterHeader.split(' ')[0];
            if (mandatory || temp.firstFilter || nextJson) {
                htmlStr += '<li class="category open">';
            } else {
                htmlStr += '<li class="category">';
            }
			if(temp.showAllNone){
				htmlStr += '<h3><a href="javascript:void(0);">' + temp.filterHeader + '</a><span class="buttons"><button class="text all click_all" type="button">All</button><button class="text none last click_none" type="button">None</button></span></h3>';
			}else{
				htmlStr += '<h3><a href="javascript:void(0);">' + temp.filterHeader + '</a></h3>';
			}          
			if(!temp.dateFilter && !temp.textFilter){
				htmlStr += '<div id="' + tempId + '"><div class="search-control"><input type="text" class="search text" value="" placeholder="Search..." autocomplete="off" style=""><button class="icon-only clear" type="button" style="display: none;">Clear Search</button></div>';
				if (temp.independentFilter || temp.firstFilter || nextJson || mandatory) {
					htmlStr += '<ul class="filter-sub-list">';
					for (var count = 0, length = temp.jsonSource.length; count < length; count++) {
						var jsonLen = temp.jsonSource.length;
						nextJson = (jsonLen === 1 && mandatory === true) ? true : false;
						var tempJson = temp.jsonSource[count];
						var tempKey = (typeof tempJson.strKey === 'undefined')?tempJson.key:tempJson.strKey;
						var desc = ((tempJson.description == undefined)?((tempJson.desc == undefined)?'':tempJson.desc):(tempJson.description));
						var showDesc = ((desc.length > filterContentDescLength)? desc.substring(0,filterContentDescLength)+'...':desc), titleReq = ((desc.length > filterContentDescLength)? true:false);
						var liClass = '';
						if(titleReq) {
							liClass = 'litool';
						}
						if(count < constants.showMoreLength){
							if(temp.isLink){
								htmlStr += '<li class="'+liClass+'"><label><a name="" value="' + tempKey + '" id="'+tempKey+'" style="cursor:pointer;">' + showDesc + '</a></label><div class="toolDiv">'+desc+'</div></li>';
							}else{
								htmlStr += '<li class="'+liClass+'"><label><input name="'+ ((temp.isRadio) ? tempId : '') + '" type="' + ((temp.isRadio) ? 'radio' : 'checkbox') + '" value="' + tempKey + '" id="' + tempKey + '"'+ ((nextJson || (preSelected === true))?'checked="checked"':'')+ '>' + showDesc + '</label><div class="toolDiv">'+desc+'</div></li>';
							}
						}
						if(count ===  constants.showMoreLength) {
							htmlStr += '<li class="show-more"><button type="button" class="text btn-more">show more</button></li>';
						}
						if(count >=  constants.showMoreLength) {
							if(temp.isLink){
								htmlStr += '<li class="more"><label '+((titleReq == true)?'title="'+desc+'"':'')+'><a name="" value="' + tempKey + '" id="' + tempKey + '" style="cursor:pointer;">' + showDesc + '</label><div class="toolDiv">'+desc+'</a></div></li>';
							}else{
								htmlStr += '<li class="more"><label '+((titleReq == true)?'title="'+desc+'"':'')+'><input name="'+ ((temp.isRadio) ? tempId : '') + '" type="' + ((temp.isRadio) ? 'radio' : 'checkbox') + '" value="' + tempKey + '" id="' + tempKey + '" '+ ((preSelected === true)?'checked="checked"':'')+'>' + showDesc + '</label><div class="toolDiv">'+desc+'</div></li>';
							}
						}
					}
					htmlStr += '</ul></div></li>';
				} else {
					htmlStr += '<ul></ul></div></li>';
				}
			 }else if(temp.dateFilter){
				 var dateObj = temp.dateObj;
				 hasDate = true;
				 htmlStr +=	 '<div id="' + tempId + '"><div class="filter-sub-list date-list" style="padding-bottom:7px;'+((temp.manadatory != true)?'display:none;':'')+'">';
				 for(var loop =0,tolen = dateObj.length;loop<tolen;loop++){
					 var tempDt = dateObj[loop],
						 label = typeof tempDt.dateFilterId === 'undefined' ? tempDt.label.split(' ')[0] : tempDt.dateFilterId;
				     htmlStr +='<div class="f" style="padding-top: 7px;">';
					 if(tempDt.label.length > 0){
						htmlStr +='<label style="width:65px;" for="'+label+'">'+tempDt.label+'</label>';
					 }
					 htmlStr +='<input type="text" id="'+label+'" placeholder="MM/DD/YYYY" name="'+label+'" class="text date hasDatepicker dateFilter"'+((typeof tempDt.selectedDate !== 'undefined')?'data-value="'+tempDt.selectedDate+'"':'')+'></div>'; 		 
				 }
				 htmlStr +=	'</div></div></li>';	
			 }else if(temp.textFilter){				 
					if(temp.inputId.length==0){
					htmlStr +=	 '<div id="' + tempId + '"><div class="filter-sub-list ">';
					htmlStr +=	 '<ul><li><span>';
						if(typeof temp.labelName != 'undefined'){
							htmlStr +=	 '<label style="margin-bottom: 2px;font-weight:bold;width:150px;">'+temp.labelName+'</label>';
						}
					htmlStr +=	 '<input type="text" class="hasInputType text"  id="'+temp.inputId[0]+'"> </input></span><span><button  class="filterSearchBtn"  id="'+temp.buttonId+'" type="button">Go</button></span></li></ul>';
					htmlStr +=	 '</div>';
				}else {
					htmlStr +=	 '<div id="' + tempId + '"><div class="filter-sub-list "><ul>';
					for(var index1=0,tempLen=temp.inputId.length;index1<tempLen;index1++){
						htmlStr +=	 '<li><span>';
						if(typeof temp.labelName != 'undefined'){
							htmlStr +=	 '<label style="margin-bottom: 2px;font-weight:bold;width:150px;">'+temp.labelName[index1]+'</label>';
						}
						htmlStr +=	 '<input type="text" class="hasInputType text"  id="'+temp.inputId[index1]+'"> </input></span></li>';
					}
					htmlStr = htmlStr.substring(0,htmlStr.length-'</li>'.length);
					htmlStr +=	 '<span><button  class="filterSearchBtn"  id="'+temp.buttonId+'" type="button">Go</button></span></li></ul></div></div></li>';
				}
			 }

        }
		//console.log(htmlStr);
		if(typeof filterParentId !== 'undefined'){
			$('#'+filterParentId+' .filter .categories').append(htmlStr);
		}else{
			$this.find('.categories').append(htmlStr);
		}        
        if(hasDate){
			initDateFilters();
		}
    }

	$('body').on({mouseenter: function() {

		$(this).find('.toolDiv').show();

	},
		mouseleave: function() {

		$(this).find('.toolDiv').hide();

	}}, 'ul.filter-sub-list li.litool');


	function appendFilter(filterArr, $this) {
	    var htmlStr = '', hasDate = false,
	        $tempFilter;
	    for (var index = 0, len = filterArr.length; index < len; index++) {
			htmlStr = '';
	        var temp = filterArr[index],mandatory = (temp.manadatory && temp.jsonSource !='undefined' && $.trim(temp.jsonSource) !='' )?true:false;
			var tempId = (typeof temp.filterId !== 'undefined')? temp.filterId:temp.filterHeader.split(' ')[0];
	        if (mandatory) {
                htmlStr += '<li class="category open">';
            } else {
                htmlStr += '<li class="category">';
            }
	       if(typeof temp.showAllNone !== 'undefined'){
				htmlStr += '<h3><a href="javascript:void(0);">' + temp.filterHeader + '</a><span class="buttons"><button class="text all click_all" type="button">All</button><button class="text none last click_none" type="button">None</button></span></h3>';
			}else{
				htmlStr += '<h3><a href="javascript:void(0);">' + temp.filterHeader + '</a></h3>';
			}
			if(!temp.dateFilter && !temp.textFilter){
	        htmlStr += '<div id="' + tempId + '"><div class="search-control"><input type="text" class="search text" value="" placeholder="Search..." autocomplete="off" style=""><button class="icon-only clear" type="button" style="display: none;">Clear Search</button></div>';
	        htmlStr += '<ul class="filter-sub-list">';
	        for (var count = 0, length = temp.jsonSource.length; count < length; count++) {
	            var jsonLen = temp.jsonSource.length;
	            var tempJson = temp.jsonSource[count];
				var tempKey = (typeof tempJson.strKey === 'undefined')?tempJson.key:tempJson.strKey;
				var desc = ((tempJson.description == undefined)?((tempJson.desc == undefined)?'':tempJson.desc):(tempJson.description));
				var showDesc = ((desc.length > filterContentDescLength)? desc.substring(0,filterContentDescLength)+'...':desc), titleReq = ((desc.length > filterContentDescLength)? true:false);
	            var liClass = '';
				if(titleReq) {
					liClass = 'litool';
				}
				if (count < constants.showMoreLength) {
	                htmlStr += '<li class="'+liClass+'"><label><input name="' + ((temp.isRadio) ? tempId : '') + '" type="' + ((temp.isRadio) ? 'radio' : 'checkbox') + '" value="' + tempKey + '" id="' + tempKey + '"'+ ((jsonLen === 1 && mandatory)?'checked="checked"':'')+'>' + showDesc + '</label><div class="toolDiv">'+desc+'</div></li>';
	            }
	            if (count === constants.showMoreLength) {
	                htmlStr += '<li class="show-more"><button type="button" class="text btn-more">show more</button></li>';
	            }
	            if (count >= constants.showMoreLength) {
	                htmlStr += '<li class="more"><label '+((titleReq == true)?'title="'+desc+'"':'')+'><input name="'+ ((temp.isRadio) ? tempId : '') + '" type="' + ((temp.isRadio) ? 'radio' : 'checkbox') + '" value="' + tempKey + '" id="' + tempKey + '">' + showDesc + '</label><div class="toolDiv">'+desc+'</div></li>';
	            }
	        }
	        htmlStr += '</div></ul></li>';
			}else if(temp.dateFilter){
				 var dateObj = temp.dateObj;
				 hasDate = true;
				 htmlStr +=	 '<div id="' + tempId + '"><div class="filter-sub-list date-list" style="padding-bottom:7px;'+((temp.manadatory != true)?'display:none;':'')+'">';
				 for(var loop =0,tolen = dateObj.length;loop<tolen;loop++){
					 var tempDt = dateObj[loop],
						 label = typeof tempDt.dateFilterId === 'undefined' ? tempDt.label.split(' ')[0] : tempDt.dateFilterId;
				     htmlStr +='<div class="f" style="padding-top: 7px;">';
					 if(tempDt.label.length > 0){
						htmlStr +='<label style="width:65px;" for="'+label+'">'+tempDt.label+'</label>';
					 }
					 htmlStr +='<input type="text" id="'+label+'" placeholder="MM/DD/YYYY" name="'+label+'" class="text date hasDatepicker dateFilter"'+((typeof tempDt.selectedDate !== 'undefined')?'data-value="'+tempDt.selectedDate+'"':'')+'></div>'; 		 
				 }
				 htmlStr +=	'</div></div></li>';	
			}else if(temp.textFilter){
					if(temp.inputId.length==0){
					htmlStr +=	 '<div id="' + tempId + '"><div class="filter-sub-list ">';
					htmlStr +=	 '<ul><li><span>';
					if(typeof temp.labelName != 'undefined'){
							htmlStr +=	 '<label style="margin-bottom: 2px;font-weight:bold;width:150px;">'+temp.labelName+'</label>';
						}
					htmlStr +=	 '<input type="text" class="hasInputType text"  id="'+temp.inputId[0]+'"> </input></span><span><button  class="filterSearchBtn"  id="'+temp.buttonId+'" type="button">Go</button></span></li></ul>';
					htmlStr +=	 '</div>';
				}else {
					htmlStr +=	 '<div id="' + tempId + '"><div class="filter-sub-list "><ul>';
					for(var index1=0,tempLen=temp.inputId.length;index1<tempLen;index1++){
						htmlStr +=	 '<li><span>';
						if(typeof temp.labelName  != 'undefined'){
							htmlStr +=	 '<label style="margin-bottom: 2px;font-weight:bold;width:150px;">'+temp.labelName[index1]+'</label>';
						}
						htmlStr +=	 '<input type="text" class="hasInputType text"  id="'+temp.inputId[index1]+'"> </input></span></li>';
					}
					htmlStr = htmlStr.substring(0,htmlStr.length-'</li>'.length);
					htmlStr +=	 '<span><button  class="filterSearchBtn"  id="'+temp.buttonId+'" type="button">Go</button></span></li></ul></div>';
				}
			}
	        $tempFilter = $('#' + tempId);
	        if ($tempFilter.length > 0) {
	            $tempFilter.closest('li.category').remove();
	        }
	        $this.find('.categories').append(htmlStr);
			 //$('.filter .categories').append(htmlStr);		
			  if(hasDate){
				initDateFilters();
			  }
			   if( $tempFilter.closest('li.category').hasClass('open')) {
				 $tempFilter.closest('li.category').find('>div').show();
			}
		}
		
	}

	function inserFilterAfter(filterArr, $this) {
		//alert('Insert Filter');
	    var htmlStr = '',
	        $tempFilter, filterHeader;
	    for (var index = 0, len = filterArr.length; index < len; index++) {
	        var temp = filterArr[index],mandatory = (temp.manadatory && temp.jsonSource !='undefined' && $.trim(temp.jsonSource) !='' )?true:false;
			var filterHeader = (typeof temp.filterId !== 'undefined')? temp.filterId:temp.filterHeader;
            htmlStr += '<div class="search-control"><input type="text" class="search text" value="" placeholder="Search..." autocomplete="off" style=""><button class="icon-only clear" type="button" style="display: none;">Clear Search</button></div>';
		    htmlStr += '<ul class="filter-sub-list">';
	        for (var count = 0, length = temp.jsonSource.length; count < length; count++) {
	            var jsonLen = temp.jsonSource.length;
	            var tempJson = temp.jsonSource[count];
				var tempKey = (typeof tempJson.strKey === 'undefined')?tempJson.key:tempJson.strKey;
				var desc = ((tempJson.description == undefined)?((tempJson.desc == undefined)?'':tempJson.desc):(tempJson.description));
				var showDesc = ((desc.length > filterContentDescLength)? desc.substring(0,filterContentDescLength)+'...':desc), titleReq = ((desc.length > filterContentDescLength)? true:false);
	            var liClass = '';
				if(titleReq) {
					liClass = 'litool';
				}
				if (count < constants.showMoreLength) {
					if(temp.isLink){
						htmlStr += '<li class="'+liClass+'"><label><a name="" value="' + tempKey + '" id="'+tempKey+'" style="cursor:pointer;">' + showDesc + '</a></label><div class="toolDiv">'+desc+'</div></li>';
					}else{
						htmlStr += '<li class="'+liClass+'"><label><input name="' + ((temp.isRadio) ? filterHeader : '') + '" type="' + ((temp.isRadio) ? 'radio' : 'checkbox') + '" value="' + tempKey + '" id="' + tempKey + '"'+ ((jsonLen === 1)?'checked="checked"':'')+'>' + showDesc + '</label><div class="toolDiv">'+desc+'</div></li>';
					}
	            }
	            if (count === constants.showMoreLength) {
	                htmlStr += '<li class="show-more"><button type="button" class="text btn-more">show more</button></li>';
	            }
	            if (count >= constants.showMoreLength) {
					if(temp.isLink){
						htmlStr += '<li class="more"><label '+((titleReq == true)?'title="'+desc+'"':'')+'><a name="" value="' + tempKey + '" id="' + tempKey + '" style="cursor:pointer;">' + showDesc + '</label><div class="toolDiv">'+desc+'</a></div></li>';
					}else{
						htmlStr += '<li class="more"><label '+((titleReq == true)?'title="'+desc+'"':'')+'><input name="'+ ((temp.isRadio) ? filterHeader : '') + '" type="' + ((temp.isRadio) ? 'radio' : 'checkbox') + '" value="' + tempKey + '" id="' + tempKey + '">' + showDesc + '</label><div class="toolDiv">'+desc+'</div></li>';
					}
	            }
	        }
	        htmlStr += '</ul>';
	        $tempFilter = $('#' + filterHeader);
			if ($tempFilter.length > 0) {
				$tempFilter.closest('li.category').addClass('open');
				if( $tempFilter.closest('li.category').hasClass('open')) {
					$tempFilter.closest('li.category').find('>div').show();
				}
			}
			if(temp.manadatory != undefined){
				if (mandatory) {
					$tempFilter.closest('li.category').addClass('open');
				}else{
					$tempFilter.closest('li.category').removeClass('open');
				}
			}
			//console.log('insert after::'+htmlStr);
			$this.find('div#'+filterHeader).html(htmlStr);
			//$('.filter div#'+filterHeader).html(htmlStr);			
		}
		//commonInit();
		
	}

    $.fn.createFilter = function (method) {
        if (methods[method] && methods[method] != 'draw') {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object') {
            return methods.draw.apply(this, arguments);
        } else {
            $.error('Method ' + method + ' does not exist');
        }
    };

	$.fn.getSelectedFilter = function(options,parentId){
		var ret = {};
		for (var index =0, len = options.length; index < len; index ++) {
			var temp = options[index];
			var type = temp.isRadio?'radio':'checkbox';
			var linkDataArr = [], dataSeparator;
			if(temp.isDate === true){
				type = 'date';
			}
			if(temp.isText === true){
				type = 'text';
			}
			if(temp.isLink === true){
				type = 'link';
				linkDataArr = temp['linkData'];
			}
			for (var opt in temp){
				if(opt !== 'isRadio' && opt !== 'isDate' && opt !== 'isText' && opt !== 'isLink' && opt !== 'linkData' && opt !== 'dataSeparator'){
					if(type === 'radio'){
						if(typeof parentId !== 'undefined'){
							ret[opt] = ($('#'+parentId+' #'+temp[opt]+' input[type="radio"]:checked').val() != undefined)?$('#'+temp[opt]+' input[type="radio"]:checked').val():'';
						}else{
							ret[opt] = ($('#'+temp[opt]+' input[type="radio"]:checked').val() != undefined)?$('#'+temp[opt]+' input[type="radio"]:checked').val():'';
						}						
					}else if(type === 'checkbox'){
						var tempArr = [];
						if(typeof parentId !== 'undefined'){
							$('#'+parentId+' #'+temp[opt]+' input[type="checkbox"]:checked').each(function(){
								tempArr.push($(this).val());
							});
						}else{
							$('#'+temp[opt]+' input[type="checkbox"]:checked').each(function(){
								tempArr.push($(this).val());
							});
						}
						
						if(tempArr.length === 0){
							ret[opt] = '';
						}else{
							ret[opt] = tempArr.join(',');
						}
					}else if(type === 'date'){
						var tempArr = [];
						if(typeof parentId !== 'undefined'){
							$('#'+parentId+' #'+temp[opt]+' input.hasDatepicker').each(function(){
								tempArr.push($(this).val());
							});
						}else{
							$('#'+temp[opt]+' input.hasDatepicker').each(function(){
								tempArr.push($(this).val());
							});
						}
						
						if(tempArr.length === 0){
							ret[opt] = '';
						}else{
							ret[opt] = tempArr.join(',');
						} 
					}else if(type === 'text'){
						var tempArr = [];
						if(typeof parentId !== 'undefined'){
							$('#'+parentId+' .'+temp[opt]+'.hasInputType').each(function(){
								tempArr.push($(this).val());
							});
						}else{
							$('#'+temp[opt]+'.hasInputType').each(function(){
								tempArr.push($(this).val());
							});
						}
						
						if(tempArr.length === 0){
							ret[opt] = '';
						}else{
							ret[opt] = tempArr.join(',');
						} 
					}else if(type === 'link'){
						var finalObj = {};
						$('#'+temp[opt]+' a').each(function(index){
							var tempObj = {}, $tempLink = $(this);
							for(var cnt=0, len=linkDataArr.length; cnt<len; cnt++){
								var tmpLinkData = linkDataArr[cnt];
								if(typeof $tempLink.attr(tmpLinkData) !== 'undefined'){
									tempObj[tmpLinkData] = $tempLink.attr(tmpLinkData);
								}
							}
							if(!$.isEmptyObject(tempObj)){
								finalObj[index] = tempObj;
							}
						});
						if($.isEmptyObject(finalObj)){
							ret[opt] = '';
						}else{
							ret[opt] = JSON.stringify(finalObj);
						} 
					}
				}	
			}
		}
		return ret;
	};

	$.fn.getSelectedFilterArr = function(options){
		var ret = {};
		for (var index =0, len = options.length; index < len; index ++) {
			var temp = options[index];
			var type = temp.isRadio?'radio':'checkbox';
			if(temp.isDate === true){
				type = 'date';
			}
			if(temp.isText === true){
				type = 'text';
			}
			for (var opt in temp){
				if(opt !== 'isRadio' && opt !== 'isDate' && opt !== 'isText'){
					if(type === 'radio'){
						ret[opt] = ($('#'+temp[opt]+' input[type="radio"]:checked').val() != undefined)?$('#'+temp[opt]+' input[type="radio"]:checked').val():'';
					}else if(type === 'checkbox'){
						var tempArr = [];
						$('#'+temp[opt]+' input[type="checkbox"]:checked').each(function(){
							tempArr.push($(this).val());
						});
						if(tempArr.length === 0){
							ret[opt] = '';
						}else{
							ret[opt] = tempArr;
						}
					}else if(type === 'date'){
						var tempArr = [];
						$('#'+temp[opt]+' input.hasDatepicker').each(function(){
							tempArr.push($(this).val());
						});
						if(tempArr.length === 0){
							ret[opt] = '';
						}else{
							ret[opt] = tempArr.join(',');
						} 
					}else if(type === 'text'){
						var tempArr = [];
						$('#'+temp[opt]+'.hasInputType').each(function(){
							tempArr.push($(this).val());
						});
						if(tempArr.length === 0){
							ret[opt] = '';
						}else{
							ret[opt] = tempArr.join(',');
						} 
					}
				}	
			}
		}
		return ret;
	};

	$.fn.persistSelectedFilter = function (targetArr, selecteObj) {
	   var constArr = {
	        0: 'One',
	        1: 'Two',
	        2: 'Three',
	        3: 'Four',
	        4: 'Five',
	        5: 'Six',
	        6: 'Seven',
	        7: 'Eight',
	        8: 'Nine',
	        9: 'Ten',
			10:'Eleven',
			11:'Tweleve',
			12:'Thirteen'
	    };
	    for (var index = 0, len = targetArr.length; index < len; index++) {
	        var temp = targetArr[index],
	            selectStr = 'selectedFilterLevel' + constArr[index],
	            type = temp.isRadio ? 'radio' : 'checkbox';
			if(temp.isDate === true){
				type = 'date';
			}
	        if(temp.isMultipleDate === true) {
				type = 'MultipleDate';
			}
			if(temp.isText === true) {
				type = 'Text';
			}
			if(temp.isLink === true) {
				type = 'Link';
			}
			if (typeof selecteObj[selectStr] !== 'undefined' && selecteObj[selectStr] != undefined && selecteObj[selectStr] != null) {
	            if(selecteObj[selectStr] != '' && selecteObj[selectStr].length > 0){
					$('#' + temp.target).parent().addClass('open');
				}
				if (type === 'radio') {
					var tempTargetArr = $('#' + temp.target + ' input[type=radio]').get();
	                $('#' + temp.target + ' input[id=' + selecteObj[selectStr] + ']').attr('checked', 'checked');
					var selIndex = $.inArray($('#' + temp.target + ' input[id=' + selecteObj[selectStr] + ']') .get()[0], tempTargetArr);
						if(selIndex >= 6){
							$('#' + temp.target).closest("li.category").addClass("with-more").find("input.search").focus();
							$('input, textarea').placeholder();
							 
						}
	            } else if(type === 'checkbox') {
					$('#' + temp.target+' input').removeAttr('checked');
	                $(selecteObj[selectStr].split(',')).each(function (index, val) {
						var tempTargetArr = $('#' + temp.target + ' input[type=checkbox]').get();
						$('#' + temp.target + ' input[id=' + val + ']').attr('checked', 'checked');
						var selIndex = $.inArray($('#' + temp.target + ' input[id=' + val + ']') .get()[0], tempTargetArr);
						if(selIndex >= 6){
							$('#' + temp.target).closest("li.category").addClass("with-more").find("input.search").focus();
							$('input, textarea').placeholder();
							 
						}
	                });
	            } else if (type === 'MultipleDate') {
					$(selecteObj[selectStr].split(',')).each(function (index, val) {
						$('#' + temp.target).find('input').eq(index).val(val);
					});
				} else if(type === 'Link'){
					var filterData = $.parseJSON(selecteObj[selectStr]);
					if(filterData!=null){
						$.each(filterData, function(k,v){
							for(var key in filterData[k]){
								$('#' + temp.target).find('a').eq(k).attr(key,(filterData[k])[key]);
							}
						});
					}
				}else {
					 $('#' + temp.target).val(selecteObj[selectStr]);
				}
	        }
	    }
	};

	function clearSearch($this) {
	     //$('div.filter .search-control').each(function () {
	     $this.find('.search-control').each(function () {
	        var $el = $(this);
	        var $input = $(this).find("input");
	        var $bt = $(this).find("button");
			var $li = $el.closest('li.category');
	        // $el.css("width", $input.outerWidth() + parseInt($input.css("margin-left")) + parseInt($input.css("margin-right")));
	        var updateDisplay = function () {
	            var left = $input.position().left;
	            var width = $input.width();
	            var top = $input.position().top;

	            if ($.trim($input.val()) != "") {
	                $bt.css("right", left + 6);
	                $bt.css("top", top + 6);
	                $bt.show();
	            } else {
	                $bt.hide();
				}
	        };
	        $bt.click(function () {
	            $input.val('');
				$li.removeClass('with-more');
				updateDisplay();
	            $input.trigger("searchclear");
				
			});
	        $input.keyup(updateDisplay);
			 $input.click(function() {
				 $(this).val('');
		   });
	        updateDisplay();

	    });
		
	}

	function initDateFilters() {
		$('input.dateFilter').each(function () {
			var $el = $(this);
			var $input = $('<input>').attr({
				id: $el.attr("id"),
				placeholder: $el.attr("placeholder"),
				title: $el.attr("title"),
				name: $el.attr("name"),
				type: "text"
			}).attr("class", "text date");
			$input.val($el.data("value"));
			$el.replaceWith($input);
			$input.datepicker({
				changeMonth: true,
				changeYear: true
			});
		 });
	}

})(jQuery);