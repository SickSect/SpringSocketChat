'use strict';

const usernameForm = document.querySelector('#usernameForm');
const messageForm = document.querySelector('#messageForm');
const usersOnline = document.querySelector('#usersOnline');

const loginButton = document.querySelector('#loginButton');
const sendButton = document.querySelector('#sendButton');

let username = null;
let stompClient = null;

function connect() {
    console.log('start connecting ...')
    username = document.querySelector('#nickname').value.trim();
    if (username){
        usernameForm.hide();
        usersOnline.show();
        messageForm.show();

        const socket = new SockJS('/chat-app');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
}

function onPublicMessage() {
    
}

function onConnected() {
    stompClient.subscribe('/topic/messages', onPublicMessage);
    stompClient.send('/main/user.addUser',
        {},
        JSON.stringify(nickname: username, status: 'ONLINE'));
    document.querySelector('#connected-user-username').textContent = username;
    findAndDisplayConnectedUsers().then();
}

async function findAndDisplayConnectedUsers() {
    const connectedUsersResponse = await fetch('/users');
    let connectedUsers = await connectedUsersResponse.json();
    connectedUsers = connectedUsers.filter(user => user.nickName !== nickname);
    const connectedUsersList = document.getElementById('connectedUsers');
    connectedUsersList.innerHTML = '';

    connectedUsers.forEach(user => {
        appendUserElement(user, connectedUsersList);
        if (connectedUsers.indexOf(user) < connectedUsers.length - 1) {
            const separator = document.createElement('li');
            separator.classList.add('separator');
            connectedUsersList.appendChild(separator);
        }
    });
}

function onError() {

}

loginButton.addEventListener('submit', connect, true);
messageForm.addEventListener('submit', sendMessage, true);

/*
'use strict'

let stompClient = null;
let username = null;


function openUI() {
    username = document.querySelector('#nickname').value.trim();
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
    const chatMessage = {
        content: message.content,
        sender: username
    };
    console.log("MESSAGE CONTENT IS " + chatMessage.content + " " + chatMessage.sender);
    stompClient.send("/main/message", {}, JSON.stringify({content: chatMessage.content, sender: chatMessage.sender}));
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}*/
