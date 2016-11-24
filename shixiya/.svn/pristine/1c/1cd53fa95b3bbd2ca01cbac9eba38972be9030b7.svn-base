$(function(){
    var init=function(){
        var btnSound=$(".send img");
        var btnSend=$(".btn-send");
        var sendSound=$(".send-sound");
        var chat=$(".chat");
        btnSound.click(function(){
            var sendWord=$(".send-word");
            var sd=sendSound.css("display");
            if(sd=="none"){
                sendSound.css("display","block");
                sendWord.css("display","none");
            }
            else if(sd=="block"){
                sendSound.css("display","none");
                sendWord.css("display","block");
            }
        });

        var ly=$(".ly");
        sendSound.click(function() {
            if(ly.css("display")=="none"){
                sending();
            }
            else{
                sended();
                chat.scrollTop(chat[0].scrollHeight);
            }
        });
        function sending(){
            $(".ly").css("display","block");
            sendSound.text("点击结束录音");
        }
        function sended(){
            $(".ly").css("display","none");
            chat.append('<div class="chat-content"><img src="../../images/head-img.png" class="head1"><div class="content"><p class="ni-name">1234</p> <img src="../../images/yy2.png" class="yy"> </div> </div>');
            sendSound.text("点击开始录音");
        }

        btnSend.click(function(){
            var inputWord=$(".input-word").val();
            if(inputWord==""){
                alert("输入内容不能为空");
                return false;
            }
            else{
                chat.append('<div class="chat-content"><img src="../../images/head-img.png" class="head1"><div class="content"><p class="ni-name">1234</p> <div class="word">'+inputWord+'</div> </div> </div>');
                $(".input-word").val("");
                chat.scrollTop(chat[0].scrollHeight);
            }
        });
    };

    //左边聊天内容
    var sendChat=function(){
        var inputWord="";  //聊天内容
        $(".chat").append('<div class="chat-content"><img src="../../images/head-img.png" class="head2"><div class="content2"><p class="ni-name2">1234</p> <div class="word2">'+inputWord+'</div> </div> </div>')
    };

    $(window).load(function(){
        init();
    });
});


