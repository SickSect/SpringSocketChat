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

const registrationForm = document.querySelector('#registration');

let stompClient = null;
let nickname = null;
let fullname = null;
let password = null;
let selectedUserId = null;

function OnError() {

}

async function registration(){
    usernamePage.classList.add('hidden');
    registrationForm.classList.remove('hidden');
    if (stompClient == null)
        connect();
}

async function finishRegistration(){
    nickname = document.querySelector('#nickname-reg').value.trim();
    fullname = document.querySelector('#fullname-reg').value.trim();
    password = document.querySelector('#password-reg').value.trim();
    if (stompClient == null)
        connect();
    stompClient.send(`/chat-public/registration`, {}, JSON.stringify({nickname: nickname, fullname: fullname, password: password}));
}

function login(event){
    nickname = document.querySelector('#nickname-reg').value.trim();
    password = document.querySelector('#password-reg').value.trim();
}

function connect(event){
    console.log('CONNECTED')
    var socket = new SockJS("/app");
    stompClient = Stomp.over(socket);
    //document.querySelector('#connected-user-fullname').textContent = fullname;
    stompClient.connect({}, onConnected, {});
}

async function successRegistration(payload) {
    //console.log('get register payload:'  + payload);
}

function onConnected(){
    console.log('Connected: ');
    //stompClient.subscribe(`/topic/${nickname}/queue/chat.messages`, chatReceived);
/*    stompClient.send(`/chat-public/user.login`, {}, JSON.stringify({
        nickname: nickname,
        fullname: fullname,
        password: password,
        status: 'ONLINE'}));*/
    stompClient.subscribe(`/topic/registration`, successRegistration);
    document.querySelector('#connected-user-fullname').textContent = fullname;
    console.log('looking for online users...');
}
window.onload = () => connect();

