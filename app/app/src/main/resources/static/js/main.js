const usernamePage = document.querySelector('#username-page');
const chatPage = document.querySelector('#chat-page');
const usernameForm = document.querySelector('#usernameForm');
const messageForm = document.querySelector('#messageForm');
const messageInput = document.querySelector('#message');
const connectingElement = document.querySelector('.connecting');
const chatArea = document.querySelector('#chat-messages');
const logout = document.querySelector('#logout');
const userInfo = document.querySelector('#user-info');
const popupOverlay = document.getElementById("popup-overlay");
const popup = document.getElementById("popup");
const errorBlock = document.getElementById("#error-form")
const registrationForm = document.querySelector('#registration');

let stompClient = null;
let nickname = null;
let fullname = null;
let password = null;
let selectedUserId = null;

function showPopup(error) {
    popupOverlay.style.display = "block";
    const errorSpan = document.createElement('span');
    errorSpan.textContent = error;
    errorBlock.appendChild(errorSpan);
}

function hidePopup() {
    popupOverlay.style.display = "none";
}

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

async function login(){
    console.log('LOGIN')
    nickname = document.querySelector('#nickname-reg').value.trim();
    password = document.querySelector('#password-reg').value.trim();
    stompClient.subscribe(`/topic/${nickname}/queue/user.login`, login);
}

function connect(event){
    console.log('CONNECTED')
    var socket = new SockJS("/app");
    stompClient = Stomp.over(socket);
    //document.querySelector('#connected-user-fullname').textContent = fullname;
    stompClient.connect({}, onConnected, {});
}

async function successRegistration(payload)  {
    //console.log('get register payload:'  + payload);
    const response = JSON.parse(payload.body);
    if (response.code == 201){
        console.log('SUCCESS REGISTRATION')
        usernamePage.classList.remove('hidden');
        registrationForm.classList.add('hidden');
    }
    else{
        console.log('FAILED REGISTRATION');
        showPopup('failed');
    }

}

function onConnected(){
    console.log('Connected: ');
    stompClient.subscribe(`/topic/registration`, successRegistration);
    //document.querySelector('#connected-user-fullname').textContent = fullname;
}

window.onload = () => connect();

