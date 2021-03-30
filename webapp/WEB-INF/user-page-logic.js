document.addEventListener('DOMContentLoaded', signInProcess());
document.querySelectorAll('.pagination-button')[0].addEventListener('click', nextPage());
document.querySelectorAll('.pagination-button')[1].addEventListener('click', previousPage());

//REMOVE AFTER FINISH SERVER PART.
const users = document.querySelectorAll('.user-list tr td button');

users.forEach(user => addContextMenuToElement(user));
//REMOVE AFTER FINISH SERVER PART.

const menu = document.querySelector('.right-click-menu');

menu.children[0].addEventListener('click', makeAdmin());

document.addEventListener('click', event => {
    if (event.button !== 2) {
        menu.classList.remove('active');
    }
}, false);

menu.addEventListener('click', event => {
    event.stopPropagation();
}, false);

var jwtToken = undefined;

var serviceEndpoint = 'http://localhost:8081';
var loginEndpoint = '/login';
var usersEndpoint = '/users';
var changeRoleEndpoint = '/change-role';
var usersCountEndpoint = '/users-count';
var userStart = 0;
var userEnd = 20;
var userStep = 20;

async function signInProcess() {
    let username = document.querySelector('#username-input');
    let password = document.querySelector('#password-input');

    let response = await fetch(serviceEndpoint + loginEndpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: username, password: password })
    });

    let jsonResponse = await response.json();

    jwtToken = jsonResponse['jwt'];
    username = jsonResponse['username'];
    let role = jsonResponse['role'];

    if (role !== 'ADMIN') {
        alert('incorect role');
        signInProcess();
    }

    getAndShowUsers();
}

function getAndShowUsers() {
    let response = fetch(serviceEndpoint + usersEndpoint + '?usersStart=' + userStart + '&usersEnd=' + userEnd, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    let jsonResponse = response.json;

    if (response.ok) {
        let users = {};

        for (let i = 0; i < jsonResponse.length(); i++) {
            users.put({ username: jsonResponse[i] });
        }

        displayUsers(users);
    }
}

function displayUsers(users) {
    let table = document.querySelector('.user-list');

    removeAllChild(table);

    // TODO FINISH
}

function removeAllChild(tag) {
    while (tag.firstChild) {
        tag.removeChild(tag.firstChild);
    }
}

function formChildTableElement(user) {
    let tr = document.createElement('tr');
    let td = document.createElement('td');
    let button = document.createElement('button');

    button.textContent = user.username;
    td.appendChild(button);
    tr.appendChild(td);

    addContextMenuToElement(button);
}

function addContextMenuToElement(tagElement) {
    tagElement.addEventListener('contextmenu', event => {
        event.preventDefault();
        menu.style.top = `${event.clientY}px`;
        menu.style.left = `${event.clientX}px`;
        menu.classList.add('active');
    }, false);
}

function changeRole() {
    let username = fileUsername();
    let response = fetch(serviceEndpoint + '/' + username + changeRoleEndpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    if (!response.ok) {
        alert('can\'t change role');
    }

    getAndShowUsers();
}

function findUsername() {
    let menu = document.querySelector(".right-click-menu.active");
    if (menu !== null) {
        let box = menu.getBoundingClientRect();
        let element = elementFromPoint(box.top + clientY, box.left + clientX);
        return element.children[1].textContent;
    } else {
        return undefined;
    }
}

function nextPage() {
    let usersCount = findUsersCount()
    if (userEnd + userStep > usersCount) {
        userEnd = usersCount
    }

    userStart += userStep;
    userEnd += userStep;

    getAndShowUsers();
}

function findUsersCount() {
    let response = fetch(serviceEndpoint + usersCountEndpoint);

    if (response.ok) {
        let jsonResponse = response.json();

        return jsonResponse['count'];
    }
}

function previousPage() {
    if (userStart > 0) {
        userStart -= userStep;
        userEnd -= userStep;
    }

    getAndShowUsers();
}

function makeAdmin() {
    let username = findUsername();

    let response = fetch(serviceEndpoint + '/' + username + changeRoleEndpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authentication': jwtToken
        },
        body: JSON.stringify({ username: username })
    });

    if (response.ok) {
        getAndShowUsers();
    } else {
        alert('can\t change role');

        let jsonResponse = response.json;

        log(jsonResponse['message']);
    }
}

function findUsername() {
    let menu = document.querySelector(".right-click-menu.active");
    if (menu !== null) {
        let box = menu.getBoundingClientRect();
        let element = elementFromPoint(box.top + clientY, box.left + clientX);
        return element.children[1].textContent;
    } else {
        return undefined;
    }
}