var stompClient = null;
var sendButton = document.querySelector('#send');
var loginPage = document.querySelector('#usernameForm');
var chatContainer = document.querySelector('#chatContainer');

$(document).ready(function () {
    console.log("Main page was loaded.");
    connect();

    sendButton.click(function() {
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

function openChat() {
    chatContainer.classList.remove('hidden');
    loginPage.classList.add('hidden')
}

function sendMessage(message) {
    console.log("start sending message...");
    stompClient.send("/main/message", {}, JSON.stringify({'content': $("#message").val()}));
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

loginPage.addEventListener('submit', openChat, true);
