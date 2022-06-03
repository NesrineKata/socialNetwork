const URL = 'ws://localhost:8080/ws-chat';
let client = null;

function showMessage(value) {
    const responseContainer = document.getElementById('response');
    const responseFragment = document.createDocumentFragment();

    const newMessage = createElementFromHTML('<div class="message-container">\n' +
        ' <div class="row">' +
        '  <img class="col-sm-2" src="img/user.svg" width="50px" height="50px" alt="Avatar">\n' +
        '  <p class="col-sm-8">' + value + '</p>\n' +
        ' </div>' +
    '  <span class="time-right">11:00</span>\n' +
    '</div>');

    responseFragment.appendChild(newMessage);
    responseContainer.appendChild(responseFragment);

    // scroll down
    let chatContainer = document.getElementById('response');
    chatContainer.scrollTop = chatContainer.scrollHeight;
}

function connect() {
    client = Stomp.client(URL);
    client.connect({}, function (frame) {
        client.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body).value);
        });
    });
}

function sendMessage() {
    let messageInput = USERNAME + ': ' + document.getElementById('message-input').value;
    client.send('/app/ws-chat', {}, JSON.stringify({'value':messageInput}));
}

