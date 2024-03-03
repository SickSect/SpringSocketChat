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

function displayMessage(senderId, content) {
    const messageContainer = document.createElement('div');
    messageContainer.classList.add('message');
    if (senderId === nickname) {
        messageContainer.classList.add('sender');
    } else {
        messageContainer.classList.add('receiver');
    }
    const message = document.createElement('p');
    message.textContent = content;
    messageContainer.appendChild(message);
    chatArea.appendChild(messageContainer);

}
function displayInfo(recipientId, status) {
    const infoContainer = document.createElement('div');
    infoContainer.classList.add('info');
    // checks!
    const userImage = document.createElement('img');
    userImage.src = '../img/user_icon.png';
    userImage.alt = user.fullName;
    const userNameSpan = document.createElement('span');
    userNameSpan.textContent = recipientId;

    const listItem = document.createElement('li');
    listItem.classList.add('user-info');
    listItem.id = user.nickName;

    const usernameSpan = document.createElement('span');
    usernameSpan.textContent = user.fullName;

    /*const receivedMsgs = document.createElement('span');
    receivedMsgs.textContent = '0';
    receivedMsgs.classList.add('nbr-msg', 'hidden');*/

    listItem.appendChild(userImage);
    listItem.appendChild(usernameSpan);
    //listItem.appendChild(receivedMsgs);

    listItem.addEventListener('click', userItemClick);

}

async function onMessageReceived(payload) {
    await findAndDisplayUsers();
    console.log('Message received', payload);
    const message = JSON.parse(payload.body);
    if (selectedUserId && selectedUserId === message.senderId) {
        displayMessage(message.senderId, message.content);
        chatArea.scrollTop = chatArea.scrollHeight;
    }
}


async function onInfoReceived(payload){
    console.log('INFO RECEIVED', payload);
    const info = JSON.parse(payload.body);
    if (selectedUserId && selectedUserId == info.recipientId){
        displayInfo(info.recipientId, info.status);
    }
}

function OnError() {
    console.log('ERROR occured');
}

function connect(event){
    var socket = new SockJS("/chat-app");
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
    stompClient.subscribe(`/topic/${nickname}/queue/messages`, onMessageReceived);
    //stompClient.subscribe(`/topic/${nickname}/queue/online`, onInfoReceived);
    stompClient.subscribe(`/topic/${nickname}/queue/get-chat`, fetchAndDisplayUserChat);
    stompClient.subscribe(`/topic/${nickname}/queue/get-info`, onInfoReceived);
    stompClient.send("/main/user.addUser",
        {},
        JSON.stringify({nickName: nickname, fullName: fullname, status: 'ONLINE'})
    );
    document.querySelector('#connected-user-fullname').textContent = fullname;
    console.log('looking for online users...')
    findAndDisplayUsers().then();
}

async function findAndDisplayUsers() {
    const connectedUsersResponse = await fetch('/users');
    let connectedUsers = await connectedUsersResponse.json();
    connectedUsers = connectedUsers.filter(user => user.nickName !== nickname);
    const connectedUsersList = document.getElementById('connectedUsers');
    connectedUsersList.innerHTML = '';
    console.log("refresh list...")
    connectedUsers.forEach(user => {
        appendUserElement(user, connectedUsersList);
        if (connectedUsers.indexOf(user) < connectedUsers.length - 1) {
            const separator = document.createElement('li');
            separator.classList.add('separator');
            connectedUsersList.appendChild(separator);
        }
    });
}

function sendMessage(event) {
    const messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        const chatMessage = {
            senderId: nickname,
            recipientId: selectedUserId,
            content: messageInput.value.trim(),
            timestamp: new Date()
        };
        stompClient.send('/main/private-chat', {}, JSON.stringify(chatMessage));
        displayMessage(nickname, messageInput.value.trim());
        messageInput.value = '';
    }
    chatArea.scrollTop = chatArea.scrollHeight;
    event.preventDefault();
}

async function fetchAndDisplayUserChat(payload) {
    console.log('RECEIVED PAYLOAD \n' + payload);
    const userChat = JSON.parse(payload.body);
    chatArea.innerHTML = '';
    userChat.forEach(chat => {
        displayMessage(chat.senderId, chat.content);
    });
    chatArea.scrollTop = chatArea.scrollHeight;
}

function userItemClick(event) {
    document.querySelectorAll('.user-item').forEach(item => {
        item.classList.remove('active');
    });
    messageForm.classList.remove('hidden');
    const clickedUser = event.currentTarget;
    clickedUser.classList.add('active');
    selectedUserId = clickedUser.getAttribute('id');
    //user info
    userInfo.classList.remove('hidden');
    const info =  {
        senderId: nickname,
        recipientId: selectedUserId,
        content: 'get info'
    }
    // clean last info
    stompClient.send("/main/user.info", info);
    //fetchAndDisplayUserChat().then();
    stompClient.send(`/main/messages/${nickname}/${selectedUserId}`, {}, {});
    const nbrMsg = clickedUser.querySelector('.nbr-msg');
    nbrMsg.classList.add('hidden');
    nbrMsg.textContent = '0';

}

/*function userItemInfo(event){
    const userImage = document.createElement('img');
    userImage.src = '../img/user_icon.png';
    userImage.alt = selectedUserId;

    const usernameSpan = document.createElement('span');
    usernameSpan.textContent = selectedUserId;
    userInfo.appendChild();
}*/

function appendUserElement(user, connectedUsersList) {
    const listItem = document.createElement('li');
    listItem.classList.add('user-item');
    listItem.id = user.nickName;

    const userImage = document.createElement('img');
    userImage.src = '../img/user_icon.png';
    userImage.alt = user.fullName;

    const usernameSpan = document.createElement('span');
    usernameSpan.textContent = user.fullName;

    /*const receivedMsgs = document.createElement('span');
    receivedMsgs.textContent = '0';
    receivedMsgs.classList.add('nbr-msg', 'hidden');*/

    listItem.appendChild(userImage);
    listItem.appendChild(usernameSpan);
    //listItem.appendChild(receivedMsgs);

    listItem.addEventListener('click', userItemClick);

    connectedUsersList.appendChild(listItem);
}

function apendUserInfoElement()

function onLogout() {
    usernamePage.classList.remove('hidden');
    chatPage.classList.add('hidden');
    stompClient.disconnect();
}

usernameForm.addEventListener('submit', connect, true); // step 1
messageForm.addEventListener('submit', sendMessage, true);
logout.addEventListener('click', onLogout, true);
window.onbeforeunload = () => onLogout();