document.addEventListener('DOMContentLoaded', signInProcess());

const menu = document.querySelectorAll('.right-click-menu');

menu[0].children[0].addEventListener('click', makeAdmin());

document.addEventListener('click', event => {
    if (event.button !== 2) {
        menus.forEach(menu => {
            menu.classList.remove('active');
        });
    }
}, false);

menus.forEach(menu => {
    menu.addEventListener('click', event => {
        event.stopPropagation();
    }, false);
});

// TODO
// change role
// sign_in DONE
// getAndShowUsers DONE
// add next user page button to users.html

let jwtToken = undefined;
let username = undefined;

var serviceEndpoint = 'http://localhost:8081';
var loginEndpoint = '/login';
var usersEndpoint = '/users';
var changeRoleEndpoint = '/change-role'
let userStart = 0;
let userEnd = 20;
let userStep = 20;

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
    let response = fetch(serviceEndpoint + usersEndpoint + '?userStart=' + userStart + '&userEnd=' + userEnd, {
        method: 'POST', 
        headers: {
            'Content-Type': 'application/json'
        }
    });

    let jsonResponse = response.json();

    let users = {};

    for(let i = 0;i<jsonResponse.length;i++) {
        users.put({username: jsonResponse[i]});
    }

    displayUsers(users);
}

function displayUsers(users) {
    let table = document.querySelector('.user-list');

    removeAllChild(table);


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