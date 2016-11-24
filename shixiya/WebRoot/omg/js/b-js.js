var B={};
B.showAll=function() {
    var showAll=$(".show-all");
    var txt=$(".txt");
    for(var i=0;i<txt.length;i++){
        if($(txt[i]).height()>47){
            $(txt[i]).css("height","47px");
            $(showAll[i]).text("展开全文");
            $(showAll[i]).click(function(){
                var k=i;
                return function(){
                    if($(txt[k]).css("height")=="47px"){
                        $(txt[k]).css("height","auto");
                        $(this).text("收起");
                    }
                    else{
                        $(txt[k]).css("height","47px");
                        $(this).text("展开全文");
                    }
                }
            }());
        }
    }
};
B.navChoose=function(){
    var nav1=$(".boss");
    var nav2=$(".hr");
    var boss=$(".boss-1");
    var hr=$(".hr-1");
    nav1.click(function(){
        $(this).addClass("visited").siblings("li").removeClass("visited");
    });
    nav2.click(function(){
        $(this).addClass("visited").siblings("li").removeClass("visited");
    });
};
B.menuChoose=function(){
    var lis=$(".classify li");
    var ctn=$(".tuwen-model");
    for(var i=0;i<lis.length;i++){
        $(lis[i]).click(function(){
            var k=i;
            return function(){
                $(this).addClass("visited").siblings("li").removeClass("visited");
//                $(ctn[k]).css("display","block").siblings(".tuwen-model").css("display","none");
            }
        }());
    }
};

window.addEventListener("load", function(){
    B.showAll();
    B.navChoose();
    B.menuChoose();
},false);