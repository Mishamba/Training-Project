// Creating custom context menu.
const menuArea = document.querySelectorAll('.element');
const menu = document.querySelector('#right-click-menu');
for (let i = 0; i < menuArea.length; i++) {
    menuArea[i].addEventListener('contextmenu', event => {
        event.preventDefault();
        menu.style.top = `${event.clientY}px`;
        menu.style.left = `${event.clientX}px`;
        menu.classList.add('active');
    }, false)
}

document.addEventListener('click', event => {
    if (event.button !== 2) {
        menu.classList.remove('active');
    }
}, false);

menu.addEventListener('click', event => {
    event.stopPropagation();
}, false);

// Main program logic.
let jwtToken = undefined;
let username = 'guest';
let role = 'guest';
let serviceEndpoint = 'http://localhost:8081/';

let currentEndpoint = '/home/mishamba';

const signInButton = document.querySelector('.sign-in-button');
signInButton.addEventListener('click', signInProcessor());

async function signInProcessor() {
    let username = document.querySelector('#username-input');
    let password = document.querySelector('#password-input');

    let responce = await fetch(serviceEndpoint + 'login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: username, password: password })
    });

    let jsonResponce = await responce.json();

    jwtToken = jsonResponce['jwt'];
    username = jsonResponce['username'];
    role = jsonResponce['role'];
}

let deleteFileElement = document.createAttribute('li');
deleteFileElement.className = 'delete-file';
deleteFileElement.innerHTML = 'Delete file';

function configurePageFunctions() {
    let deleteFileOption = document.querySelector('#delete-file');
    let createFileButton = document.querySelector('.create-file-button');
    let activeCreateFileButton = document.querySelector('.create-file-button.active');

    if (role === 'ADMIN') {
        // Appending delete file option to context menu.
        if (deleteFileOption === null) {
            menu.append(deleteFileElement);
        }

        // Making create file button visible.
        if (activeCreateFileButton === null) {
            createFileButton.classList.add('active');
        }
    } else {
        // Removing delete file option from context menu.
        if (deleteFileOption !== null) {
            menu.remove(deleteFileElement);
        }

        // Making create file button non-visible.
        if (activeCreateFileButton !== null) {
            activeCreateFileButton.parentElement.remove(activeCreateFileButton);
        }
    }
}