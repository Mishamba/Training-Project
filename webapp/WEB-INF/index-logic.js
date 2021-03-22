/*
    menus[0] - open file
    menus[1] - delete file
    menus[2] - open file
               delete file
*/
const menus = document.querySelectorAll('.right-click-menu');

menus[0].children[0].addEventListener('click', openForlder());
menus[1].children[0].addEventListener('click', deleteFile());
menus[2].children[0].addEventListener('click', openForlder());
menus[2].children[1].addEventListener('click', deleteFile());

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

const FOLDER = 'folder';
const FILE = 'file';
const GUEST = 'guest';
const USER = 'user';
const ADMIN = 'admin';

let jwtToken = undefined;
let username = GUEST;
let role = GUEST;

var serviceEndpoint = 'http://localhost:8081';
var loginEndpoint = '/login';
var createFileEndpoint = '/create-file';
var deleteFileEndpoint = '/delete-file';

var currentPath = '/home/mishamba';
let currentFiles = {};

document.addEventListener('DOMContentLoaded', openFolderByPath(currentPath));

const signInButton = document.querySelector('.sign-in-button');
signInButton.addEventListener('click', signInProcessor());

async function signInProcessor() {
    let usrnm = document.querySelector('#username-input');
    let pswd = document.querySelector('#password-input');

    let response = await fetch(serviceEndpoint + loginEndpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: usrnm, password: pswd })
    });

    let jsonResponse = await response.json();

    jwtToken = jsonResponse['jwt'];
    username = jsonResponse['username'];
    role = jsonResponse['role'];

    signInButton.classList.remove('active');

    showFiles();
}

async function deleteFile() {
    let fileName = findFileName();

    let response = await fetch(serviceEndpoint + deleteFileEndpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authentication': jwtToken
        },
        body: JSON.stringify({ fileName: fileName, path: currentPath })
    });

    if (response.ok) {
        alert('Deleted file!');
        openFolderByPath(currentPath);
    } else {
        alert('Can\'t delete file');
    }
}

async function createFile() {
    let fileName = prompt('Enter file name', 'smth.txt');

    let response = await fetch(serviceEndpoint + createFileEndpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authentication': jwtToken
        },
        body: JSON.stringify({ fileName: fileName, path: currentPath })
    });

    if (response.ok) {
        alert('Created file!');
        openFolderByPath(currentPath);
    } else {
        alert('can\'t create file');
    }

    openForlderByPath(currentPath);
}

function openForlder() {
    openFolderByPath(currentPath + '/' + findFileName());
}

function findFileName() {
    let menu = document.querySelector(".right-click-menu.active");
    if (menu !== null) {
        let box = menu.getBoundingClientRect();
        let element = elementFromPoint(box.top + clientY, box.left + clientX);
        return element.children[1].textContent;
    } else {
        return undefined;
    }
}

async function openFolderByPath(path) {
    let response = await fetch(serviceEndpoint + path)

    if (response.ok) {
        let jsonResponse = await response.json();

        for (let i = 0; i < jsonResponse.length; i++) {
            currentFiles.put({ fileName: jsonResponse[i]['name'], type: jsonResponse[i]['type'] });
        }

        showFiles();
    } else {
        alert('can\'t open folder.');
    }
}

function showFiles() {
    let table = document.querySelector('.table');

    removeAllChild(table);

    let lines = {};

    // TODO : looks like no td tag formed
    for (let i = 0; i < currentFiles.length; i++) {
        let line = document.createAttribute('tr');

        // This sub cycle makes each line 9 elements length.
        for (; i % 9 <= 9 && i < currentFiles.length; i++) {
            line.appendChild(formTagFromFile(currentFiles[i]));
        }

        lines.put(line);
    }

    lines.forEach(element => {
        table.appendChild(element);
    })
}

function removeAllChild(tag) {
    while (tag.firstChild) {
        tag.removeChild(tag.firstChild);
    }
}

function formTagFromFile(file) {
    let element = document.createAttribute('button');

    addPictureToElement(element, file);
    addFileNameToElement(element, file);

    element.addEventListener('click', openFolderByPath(currentPath + '/' + file.fileName));

    return element;
}

function addPictureToElement(tagElement, file) {
    let elementImage = document.createAttribute('img');

    if (file.type === FOLDER) {
        elementImage.setAttribute('src', 'folder.png');
    } else {
        elementImage.setAttribute('src', 'file.png');
    }

    tagElement.appendChild(elementImage);
}

function addFileNameToElement(tagElement, file) {
    let elementName = document.createAttribute('p');
    elementName.textContent = file.fileName;
    elementName.classList.add("element");

    element.appendChild(elementName);
}

function addContextMenuToElement(tagElement, file) {
    let menu = provideMenu(file);

    if (!menu === undefined) {
        tagElement.addEventListener('contextmenu', event => {
            event.preventDefault();
            menu.style.top = `${event.clientY}px`;
            menu.style.left = `${event.clientX}px`;
            menu.classList.add('active');
        }, false)
    }
}

function provideMenu(file) {
    let menu = undefined;

    if (file.type === FOLDER) {
        if (role === GUEST || role === USER) {
            menu = menus[0];
        } else if (role === ADMIN) {
            menu = menus[2];
        }
    } else if (file.type === FILE && role === ADMIN) {
        menu = menus[1];
    }

    return menu;
}