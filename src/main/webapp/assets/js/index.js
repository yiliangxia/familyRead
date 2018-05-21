function support() {
	var oDiv = $(".supp")[0];
	var oTab = $("#list").height();
	var oHeight = $(window).height() - 375;
	oDiv.style.width = "100%";
	if (oTab <= oHeight) {
		oDiv.style.height = oHeight - oTab + 'px';
	} else {
		oDiv.style.height = 0;
	}
	var oPag = $(".Paging");
	var aPli = $(".Paging a");
	oPag.css('width', aPli.length * aPli[0].offsetWidth + 'px');
}

function fenye() {
	$(".Paging .pag a").click(function() {
		var i = $(this).index();
		$(this).addClass('cur').siblings().removeClass('cur');
	});

}

function inputborder() {
	$("input[type=text],textarea").focus(function() {
		$(this).css("border", "1px solid #453a7d");
	});

	$("input[type=text],textarea").blur(function() {
		$(this).css("border", "1px solid #ccc");
	});
}

function leftHeight() {
	var oHeight = $(window).height() - 102;
	var oLeft = $('#left');
	var oRight = $('#page-wrapper');
	var oRight1 = $('.right_fra');
	if (oRight.outerHeight() > oHeight) {
		oLeft.css('height', oRight.outerHeight() + 'px');
	} else {
		oLeft.css('height', oHeight + 'px');
		oRight1.css('height', oHeight + 'px');
	}
}

function picly() {
	var piclyHeight = $(window).height() - 160;
	var picly = $('.picly')[0];
	picly.style.height = piclyHeight + 'px';
	console.log(picly.offsetHeight);
	var aDiv = $('.picly div');
	aDiv.click(function() {
		if (picly.offsetHeight > piclyHeight) {
			picly.className = 'picly';
			picly.style.height = piclyHeight + 'px';
		}
	});
	var oTree = $('#tree')[0];
	oTree.style.height = (piclyHeight - 70) + 'px';
	oTree.style.overflow = 'auto';
}

function pi() {
	var piclyHeight = $(window).height() - 160;
	var picly = $('.pi')[0];
	picly.style.height = piclyHeight + 'px';
	console.log(picly.offsetHeight);
	var aDiv = $('.pi div');
	aDiv.click(function() {
		if (picly.offsetHeight > piclyHeight) {
			picly.className = 'pi';
			picly.style.height = piclyHeight + 'px';
		}
	});
	var oTree = $('#tree')[0];
	oTree.style.height = (piclyHeight - 70) + 'px';
	oTree.style.overflow = 'auto';
}

function flex() {
	var aH3 = $('#left h3');
	var aUl = $('#left ul');
	aH3.click(function() {
		var oUl = $(this).next();
		var aLi = oUl.children();
		var oB = $(this).children('b');
		if ($(this).hasClass('a')) {
			$(this).removeClass('a');
			oB.removeClass('downly');
			oB.addClass('uply');
			oUl.stop().animate({
				height : aLi.height() * aLi.length
			});
		} else {
			$(this).addClass('a');
			oB.removeClass('uply');
			oB.addClass('downly');
			oUl.stop().animate({
				height : 0
			});
		}
	});
}
function tabControl() {
	aLi = $('#left li');
	aLi.hover(function() {
		$(this).addClass('active1');
	}, function() {
		$(this).removeClass('active1');
	});
};

(function() {
	window.myselect = function(sName) {
		var oSele = document.getElementsByName(sName)[0];
		var oDiv = document.createElement('div');
		oDiv.className = 'selely';
		var oUl = document.createElement('ul');
		var oP = document.createElement('p');
		var oB = document.createElement('b');
		oP.innerHTML = oSele.options[0].text;
		oDiv.appendChild(oB);
		oDiv.appendChild(oP);
		oDiv.appendChild(oUl);
		oSele.style.display = 'none';
		for (var i = 0; i < oSele.options.length; i++) {
			var oLi = document.createElement('li');
			oLi.innerHTML = oSele.options[i].text;
			oUl.appendChild(oLi);
			(function(index) {
				oLi.onclick = oB.onclick = function() {
					oP.innerHTML = this.innerHTML;
					oSele.selectedIndex = index;
					oUl.style.display = 'none';
				};
			})(i);
		}
		oP.onclick = oB.onclick = function(ev) {
			oEvent = ev || event;
			oUl.style.display = 'block';
			oUl.style.zIndex++;
			window.oEvent.cancelBubble = true;
		};
		var aLi = oUl.children;
		for (var i = 0; i < aLi.length; i++) {
			(function(index) {
				aLi[i].onmouseover = function() {
					for (var i = 0; i < aLi.length; i++) {
						aLi[i].className = '';
					}
					this.className = 'show';
				};
			})(i);
		}
		oSele.parentNode.insertBefore(oDiv, oSele);
		addEvent(document, 'click', function() {
			oUl.style.display = 'none';
		});
	}
})();

function modified(obj1, obj2, obj3) {
	obj1.click(function() {
		if (isNaN(obj3.val())) {
			return;
		}
		obj3.val(parseInt(obj3.val()) - 1);
		if (obj3.val() <= 1) {
			obj3.val(1);
		}
	});
	obj2.click(function() {
		if (isNaN(obj3.val())) {
			return;
		}
		obj3.val(parseInt(obj3.val()) + 1);
	});
	obj3.keydown(function(event) {
		if ((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode != 8)
				&& (event.keyCode < 96 || event.keyCode > 105)) {
			obj3.val(1);
			return false;
		}
	});
}

function right() {
	var aEm = $('.content .picly .framely_in em');
	for (var i = 0; i < aEm.length; i++) {
		var oParent = aEm.parent();
		var oNext = oParent.next();
		var aNdiv = oNext.children();
		if (oNext.hasClass('hide')) {
			aEm.css('height', aNdiv.outerHeight() + 2 + 'px');
		} else {
			aEm.css('height', (aNdiv.outerHeight() + 10) * aNdiv.length
					+ aNdiv.outerHeight() / 2 + 'px');
		}

	}
}

function rPic(obj) {
	obj.click(function() {
		if ($(this).next().hasClass('hide')) {
			$(this).next().removeClass('hide');
			$(this).children('span').addClass('active');
		} else {
			$(this).next().addClass('hide');
			$(this).children('span').removeClass('active');
		}
		right();
	});

}

;
(function() {
	window.mycheckbox = function(sName) {
		var aSex = document.getElementsByName(sName);
		var aSpan = [];
		for (var i = 0; i < aSex.length; i++) {
			var oS = document.createElement('strong');
			oS.className = 'off_ly';
			aSpan.push(oS);
			aSex[i].parentNode.insertBefore(oS, aSex[i]);
			aSex[i].style.display = 'none';
			(function(index) {
				oS.onclick = function() {
					if (this.className == 'off_ly') {
						this.className = 'on_ly';
						aSex[index].checked = true;
					} else {
						this.className = 'off_ly';
						aSex[index].checked = false;
					}
				};
			})(i);
		}
	};
})();

function tree() {
	var aA = $('.picly a');
	aA.parent().click(function() {
		aA.parent().removeClass('pitchon');
		$(this).addClass('pitchon');
	});
}

function addEvent(obj, sEvent, fn) {
	if (obj.addEventListener) {
		obj.addEventListener(sEvent, fn, false);
	} else {
		obj.attachEvent('on' + sEvent, fn);
	}
}

function role() {
	var aLi = $('.casebotly li');
	aLi.hover(function() {
		$(this).addClass('active');
	}, function() {
		$(this).removeClass('active');
	});
}

function but() {
	var oSer = $('.ser')[0];
	var oFou = $('.fou')[0];
	var oPic = $('.fou .pl')[0];
	oSer.onfocus = oPic.onclick = oSer.onclick = oFou.onclick = function(ev) {
		oEvent = ev || event;
		oFou.style.display = 'block';
		window.oEvent.cancelBubble = true;
	}
	addEvent(document, 'click', function() {
		oFou.style.display = 'none';
	});
}
