function change(img){
	img.src="getCode?"+new Date().getTime();
}
var username=1;//标记位
var password=1;
var code=1;

function FocusItem(obj){
	
	if($(obj).attr('name') == 'code'){
		$(obj).next().next().html('').removeClass('error');
	}else{
		$(obj).next('span').html('').removeClass('error');
	}
}
function CheckItem(obj){
	var msgBox = $(obj).next('span');
	switch($(obj).attr('name')){
	case "userName":
	    if(obj.value == ""){
	    	msgBox.html('用户id不能为空');
	    	msgBox.addClass('error');
	    	username=0;
	    }else{
	    	var url="user_namecheck?name="+encodeURI($(obj).val())+"&"+new Date().getTime();
	    	//"false" "true"
	    	$.get(url, function(date){
	    		if(date == "false"){
	    			msgBox.html('用户id已存在');
	    			msgBox.addClass('error');
	    			username=0;
	    		}else{
	    			msgBox.html('用户名可使用');
	    			username=1;
	    		}
	    	});
	    }
	    break;
	case "password":
		if(obj.value == ""){
	    	msgBox.html('密码不能为空');
	    	msgBox.addClass('error');
	    	password=0;
		}
	    break;
	case "rePassWord":
		if(obj.value == ""){
	    	msgBox.html('确认密码不能为空');
	    	msgBox.addClass('error');
	    	password=0;
	    	
		}else if($(obj).val()!=$('input[name="password"]').val()){
			msgBox.html('密码不一致');
	    	msgBox.addClass('error');
	    	password=0;
		}else{
			password=1;
		}
	    break;
	    
	case "veryCode":
		var numshow  = $(obj).next().next();
		if(obj.value == ""){
	    	numshow.html('验证码不能为空');
	    	numshow.addClass('error');
	    	code=0;
	    }else {
	    	var url="checkCode?num="+encodeURI($(obj).val())+"&"+new Date().getTime();
	    	$.get(url, function(numdata){
	    		if(numdata == 'false'){
	    			numshow.html('验证码输入有误');
	    			numshow.addClass('error');
	    			code=0;
	    		}else{
	    			numshow.html('验证码输入正确');
	    			code=1;
	    		}
	    	})
	    }
		break;
	}
}



function checkForm(frm){
	var els = frm.getElementsByTagName('input');
	//onblur 这个属性才需要验证
	for(var i=0; i<els.length; i++){
		
		if(els[i] !=null ){
			
			if(els[i].getAttribute("onblur")){
				
				CheckItem(els[i]);
			}
		}
	}
	console.log(password+"+"+code+"+"+username);
	if(password==1 && code==1 && username==1){
		return true;
	}else{
		return false;
	}
}