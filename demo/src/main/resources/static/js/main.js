'use strict'

let stompClient = null;


function openUI() {
   $("#usernameForm").hide();
   $("#usersOnline").show();
   $("#messageForm").show();
}

$(document).ready(function (){
    console.log("Index page was loaded");
    connect();

    $("#loginButton").click(function (){
        openUI();
    })

    $("#sendButton").click(function (){
        sendMessage();
    })
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

function sendMessage(message) {
    console.log("start sending message...");
    stompClient.send("/main/message", {}, JSON.stringify({'content': $("#message").val()}));
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}