'use strict';

const usernamePage = document.querySelector('#username-page');
const chatPage = document.querySelector('#chat-page');
const usernameForm = document.querySelector('#usernameForm');
const messageForm = document.querySelector('#messageForm');
const messageInput = document.querySelector('#message');
const connectingElement = document.querySelector('.connecting');
const chatArea = document.querySelector('#chat-messages');
const logout = document.querySelector('#logout');
const userInfo = document.querySelector('#user-info');

let stompClient = null;
let nickname = null;
let fullname = null;
let selectedUserId = null;

function connect(event){
    var socket = new SockJS("/app");
    stompClient = Stomp.over(socket);
    nickname = document.querySelector('#nickname').value.trim();
    fullname = document.querySelector('#fullname').value.trim();
    usernamePage.classList.add('hidden');
    chatPage.classList.remove('hidden');
    stompClient.connect({}, onConnected, OnError)
    event.preventDefault();
}

function onConnected(){
    console.log('Connected: ');
    const user = {
    }
    //stompClient.subscribe(`/topic/${nickname}/queue/chat.messages`, chatReceived);
    stompClient.send(`/chat-app/user.login`, {}, )
}

usernameForm.addEventListener('submit', connect, true); // step 1
