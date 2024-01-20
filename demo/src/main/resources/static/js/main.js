var stompClient = null;
var message = document.querySelector('#message');
var sendButton = document.querySelector('#send');


$(document).ready(function () {
    console.log("Main page was loaded.");
    connect();


    $("#send").click(function() {
        sendMessage();
    });

})

function connect() {
    var socket = new SockJS('/chat-app');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (message){
            showMessage(JSON.parse(message.body).content);
        })
    })
}

function sendMessage() {
    console.log("start sending message...")
    stompClient.send("/main/message", {}, JSON.stringify({'messageContent': message.val()}));
}

function showMessage(message){
    $("#messages").append("<tr><td>" + message + "</td></td>");
}