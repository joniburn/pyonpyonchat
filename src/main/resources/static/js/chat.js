pyonpyonchat = {};

$(function() {
    pyonpyonchat.submit = function(form) {
        //console.log("submit!");

        var name     = $(form).find('input[name="name"]').val();
        var chatText = $(form).find('input[name="chatText"]').val();

        if (name === "") {
            name = "Noname";
        }

        if (chatText === "") {
            //alert("チャットを入力してね");
            return;
        }

        var postData = {
            name:     name,
            chatText: chatText
        }

        $.ajax({
            url: 'chat-api',
            type: 'POST',
            //dataType: 'json',
            data : JSON.stringify(postData),
            contentType: 'application/json',
            success: pyonpyonchat.refresh,
        })

        $(form).find('input[name="chatText"]').val("");
    };

    pyonpyonchat.refresh = function() {
        console.log('refresh');
        $.ajax({
            url: 'chat-api',
            success: function(chatList) {
                $('#chatContainer').empty();
                for (var i = 0; i < chatList.length; i++) {
                    var chat = chatList[i];
                    var chatElm = $('<div>');
                    chatElm.append($('<span>').text(chat.name));
                    chatElm.append($('<pre>').text(chat.chatText));
                    $('#chatContainer').prepend(chatElm);
                }
            }
        })
    };

    pyonpyonchat.refresh();
    setInterval(pyonpyonchat.refresh, 10000);
});
